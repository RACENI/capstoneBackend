package com.capstone.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.capstone.user.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/***
	 *메소드 용도: 내정보 수정 컨트롤러
	 *매개변수: Map<String, String>
	 *반환값: ResponseEntity(Map<String, Object>)
	***/
	@PostMapping("/api/modifyMyInfo")
    public ResponseEntity<Map<String, Object>> modifyMyInfo(
            @RequestPart("name") String name,
            @RequestPart("contact") String contact,
            @RequestPart("intro") String intro,
            @RequestPart("strength") String strength,
            @RequestPart("region") String region) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("contact", contact);
        request.put("intro", intro);
        request.put("strength", strength);
        request.put("region", region);

        Map<String, Object> response = userService.modifyMyInfo(request);

        return ResponseEntity.ok(response);
    }
	
	/***
	 *메소드 용도: 내정보 보기 컨트롤러
	 *반환값: ResponseEntity(Map<String, Object>)
	***/
	@GetMapping("/api/viewMyInfo")
	public ResponseEntity<Map<String, Object>> viewMyInfo() {
		Map<String, Object> response = userService.viewMyInfo();

		return ResponseEntity.ok(response);
	}
}
