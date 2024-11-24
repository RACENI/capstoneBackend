package com.capstone.project.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.capstone.dao.ProjectsMapper;
import com.capstone.vo.CustomUserDetails;
import com.capstone.vo.ProjectsWithBLOBs;

@Service
public class ProjectBoardService {
	
	@Autowired
	private ProjectsMapper projectDao;

	public Map<String, Object> createBoard(Map<String, Object> request) {
		Map<String, Object> map = new HashMap<>();
		
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			
		//파라미터 받기
        String name = request.get("name").toString();
        String desc = request.get("desc").toString();
        String content = request.get("content").toString();
        String topic = request.get("topic").toString();
        String region = request.get("region").toString();
        String startDate = request.get("startDate").toString();
        String endDate = request.get("endDate").toString();
		
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateC = null;
        Date endDateC = null;
		try {
			startDateC = formatter.parse(startDate);
	        endDateC = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        //VO객체 생성
        ProjectsWithBLOBs record = new ProjectsWithBLOBs();
        record.setName(name);
        record.setDesc(desc);
        record.setContent(content);
        record.setTopic(topic);
        record.setRegion(region);
        record.setStartDate(startDateC);
        record.setEndDate(endDateC);
        record.setUserId(userDetails.getId());
        
        //DAO 실행
        int result = projectDao.insertSelective(record);
		
        if(result > 0) map.put("result", "success");
        else map.put("result", "fail");
		return map;
	}
	
	public  Map<String, Object> modifyBoard(Map<String, Object> request) {
		Map<String, Object> map = new HashMap<>();
		
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		//파라미터 받기
		int id = Integer.parseInt(request.get("id").toString());
        String name = request.get("name").toString();
        String desc = request.get("desc").toString();
        String content = request.get("content").toString();
        String topic = request.get("topic").toString();
        String region = request.get("region").toString(); 
        String phase = request.get("phase").toString(); 
        String startDate = request.get("startDate").toString();
        String endDate = request.get("endDate").toString();
		
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateC = null;
        Date endDateC = null;
		try {
			startDateC = formatter.parse(startDate);
	        endDateC = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        //VO객체 생성
        ProjectsWithBLOBs record = projectDao.selectByPrimaryKey(id);
        record.setName(name);
        record.setDesc(desc);
        record.setContent(content);
        record.setTopic(topic);
        record.setRegion(region);
        record.setPhase(phase);
        record.setStartDate(startDateC);
        record.setEndDate(endDateC);
        
        //DAO 실행
        int result = 0;
        if(record.getUserId() == userDetails.getId()) {
        	result = projectDao.updateByPrimaryKeySelective(record);
        }       
        if(result > 0) map.put("result", "success");
        else map.put("result", "fail");
        
		return map;
	}
	
	public Map<String, Object> deleteBoard(Map<String, Object> request) {
		Map<String, Object> map = new HashMap<>();
		
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		//파라미터 받기
		int id = Integer.parseInt(request.get("id").toString());
		ProjectsWithBLOBs record = projectDao.selectByPrimaryKey(id);
		
        //DAO 실행
        int result = 0;
        if(record.getUserId() == userDetails.getId()) {
        	result = projectDao.deleteByPrimaryKey(id);
        }
        
        if(result > 0) map.put("result", "success");
        else map.put("result", "fail");
        
		return map;
	}
}
