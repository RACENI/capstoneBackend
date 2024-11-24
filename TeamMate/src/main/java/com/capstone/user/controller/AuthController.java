package com.capstone.user.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.capstone.jwt.JwtUtil;
import com.capstone.user.service.AuthService;

@Controller
public class AuthController {
    @Value("${service.ip}")
    private String allowedOrigins;
    
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/***
	 *메소드 용도: 회원가입 컨트롤러
	 *매개변수: Map<String, String>
	 *반환값: ResponseEntity<Map<String, Object>>
	***/
	@PostMapping("/join")
    public ResponseEntity<Map<String, Object>> join(
            @RequestPart("name") String name,
            @RequestPart("email") String email,
            @RequestPart("password") String password) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("email", email);
        request.put("password", password);
        
        Map<String, Object> response = authService.join(request);

        return ResponseEntity.ok(response);
    }
	
	/***
	 *메소드 용도: 로그인 컨트롤러
	 *매개변수: Authentication
	 *반환값: ResponseEntity<Map<String, Object>>
	***/
	@GetMapping("/axios/login")
	public ResponseEntity<Map<String, Object>> login(Authentication authentication) {
		Map<String, Object> response = new HashMap<>();

		String userId = authService.getLogin(authentication); // E-mail 반환

		if (userId != null) {
			String token = jwtUtil.generateToken(userId);
			response.put("token", token);
			try {
				String encodedToken = URLEncoder.encode(token, "UTF-8");
				return ResponseEntity.ok(response);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.ok(response);
			}
		} else {
			response.put("error", "error");
			return ResponseEntity.ok(response);
		}
	}
}