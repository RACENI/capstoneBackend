package com.capstone.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.capstone.dao.UsersInfoMapper;
import com.capstone.vo.UsersInfo;

@Service
public class AuthService {
	
	@Autowired
	private UsersInfoMapper userDao;
	
	/***
	 *메소드 용도: 회원가입 서비스
	 *매개변수: Map<String, Object>
	 *반환값: Map<String, Object>
	***/
	public Map<String, Object> join(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();
        
		//파라미터 받기
        String name = request.get("name").toString();
        String email = request.get("email").toString();
        String password = request.get("password").toString();
		
        //VO객체 생성
		UsersInfo record = new UsersInfo();
        record.setUserId(email);
        record.setName(name);
        record.setPassword(password);
        
        //DAO 실행
        int updateCheck = userDao.insert(record);
        
        if(updateCheck == 1) {
        	map.put("result", "성공");
        } else {
        	map.put("result", "실패");
        }
        return map;
	}
	
	/***
	 *메소드 용도: 로그인
	 *매개변수: Authentication
	 *반환값: String(email)
	***/
	public String getLogin(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof OAuth2User) {
            // 파라미터 받기
            OAuth2User oauth2User = (OAuth2User) principal;
            String userId = oauth2User.getAttribute("userId");

            return userId;
            
        } else {
            return null;
        }
	}
}