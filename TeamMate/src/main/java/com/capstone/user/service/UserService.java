package com.capstone.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.capstone.dao.UsersInfoMapper;
import com.capstone.vo.CustomUserDetails;
import com.capstone.vo.UsersInfo;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;

@Service
public class UserService {
	@Autowired
	UsersInfoMapper userDao;
	
	/***
	 *메소드 용도: 회원정보 수정 서비스
	 *매개변수: Authentication
	 *반환값: Map<String, Object>
	***/
	public Map<String, Object> modifyMyInfo(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();
        
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		//파라미터 받기
        String name = request.get("name").toString();
        String contact = request.get("contact").toString();
        String intro = request.get("intro").toString();
        String strength = request.get("strength").toString();
        String region = request.get("region").toString();
        
		if(name.equals("")) {
			map.put("result", "회원정보 수정에 실패하였습니다.");
			return map;
		}
		
        //VO객체 생성
		UsersInfo record = userDao.selectByPrimaryKey(userDetails.getId());
        record.setName(name);
        record.setContact(contact);
        record.setStrength(strength);
        record.setRegion(region);
        
        //DAO 실행
        int updateCheck = userDao.updateByPrimaryKeySelective(record);
        
        if(updateCheck == 1) {
            // 업데이트된 사용자 정보로 새로운 UserDetails 생성
            CustomUserDetails updatedUserDetails = new CustomUserDetails(null, record.getPassword(), record.getName(), record.getUserId(), record.getId());
            // 새로운 Authentication 객체 생성
            Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedUserDetails, null, updatedUserDetails.getAuthorities());
            // SecurityContext에 새로운 Authentication 객체 설정
            SecurityContextHolder.getContext().setAuthentication(newAuth);
            
        	map.put("result", "회원정보가 수정되었습니다.");
        	
        } else {
        	map.put("result", "회원정보 수정에 실패하였습니다.");
        }
        
        return map;
	}
	
	/***
	 *메소드 용도: 회원정보 보기 서비스
	 *반환값: Map<String, Object>
	***/
	public Map<String, Object> viewMyInfo() {
        HashMap<String, Object> map = new HashMap<>();
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		UsersInfo record = userDao.selectByPrimaryKey(userDetails.getId());
		
		
		
		map.put("name", record.getName());
		map.put("contact", record.getContact());
		map.put("intro", record.getIntro());
		map.put("strength", record.getStrength());
		map.put("region", record.getRegion());
		

		return map;
	}
}
