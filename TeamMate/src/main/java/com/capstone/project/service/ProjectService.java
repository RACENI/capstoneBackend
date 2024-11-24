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

import com.capstone.dao.ProjectScheduleMapper;
import com.capstone.dao.UserProjectMapper;
import com.capstone.dao.UsersInfoMapper;
import com.capstone.vo.CustomUserDetails;
import com.capstone.vo.ProjectSchedule;
import com.capstone.vo.UserProject;
import com.capstone.vo.UserProjectExample;

@Service
public class ProjectService {
	@Autowired
	UserProjectMapper userProjectDao;
	
	@Autowired
	ProjectScheduleMapper projectSchDao;
	
	public Map<String, Object> joinProject(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();
        
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		int projectId = Integer.parseInt(request.get("projectId").toString());
		
		UserProject record = new UserProject();
		record.setUserId(userDetails.getId());
		record.setProjectroleId(projectId);
		
		int insertCheck = userProjectDao.insert(record);
		
		if(insertCheck == 1) {
			map.put("result", "성공");
		} else {
			map.put("result", "실패");
		}
		
		return map;
	}
	
	public Map<String, Object> leaveProject(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();
        
		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		int projectId = Integer.parseInt(request.get("projectId").toString());
		
		UserProject record = new UserProject();
		record.setUserId(userDetails.getId());
		record.setProjectroleId(projectId);
		
		int insertCheck = userProjectDao.deleteById(record);
		
		if(insertCheck == 1) {
			map.put("result", "성공");
		} else {
			map.put("result", "실패");
		}
		
		return map;
	}
	
	public Map<String, Object> addScheduleItem(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();

		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
        //프로젝트id, 시작날짜, 종료날짜, 시작시간, 종료시간
		//파라미터 받기
		int projectId = Integer.parseInt(request.get("projectId").toString());
	    String startDt = request.get("startDt").toString();
	    String endDt = request.get("endDt").toString();
	    String startTime = request.get("startTime").toString();
	    String endTime = request.get("endTime").toString();
	    String title = request.get("title").toString();
	    String content = request.get("content").toString();
	    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateC = null;
        Date endDateC = null;
		try {
			startDateC = formatter.parse(startDt);
	        endDateC = formatter.parse(endDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    ProjectSchedule record = new ProjectSchedule();
	    record.setProjectId(projectId);
	    record.setStartDate(startDateC);
	    record.setEndDate(endDateC);
	    record.setStartTime(startTime);
	    record.setEndTime(endTime);
	    record.setTitle(title);
	    record.setContent(content);
	    
	    int insertCheck = projectSchDao.insertSelective(record);
	    
		if(insertCheck == 1) {
			map.put("result", "성공");
		} else {
			map.put("result", "실패");
		}
		
		return map;
	}
	
	public Map<String, Object> modifyScheduleItem(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();

		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
        //프로젝트id, 시작날짜, 종료날짜, 시작시간, 종료시간
		//파라미터 받기
		int schId = Integer.parseInt(request.get("schId").toString());
	    String startDt = request.get("startDt").toString();
	    String endDt = request.get("endDt").toString();
	    String startTime = request.get("startTime").toString();
	    String endTime = request.get("endTime").toString();
	    String title = request.get("title").toString();
	    String content = request.get("content").toString();
	    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDateC = null;
        Date endDateC = null;
		try {
			startDateC = formatter.parse(startDt);
	        endDateC = formatter.parse(endDt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    ProjectSchedule record = projectSchDao.selectByPrimaryKey(schId);
	    record.setStartDate(startDateC);
	    record.setEndDate(endDateC);
	    record.setStartTime(startTime);
	    record.setEndTime(endTime);
	    record.setTitle(title);
	    record.setContent(content);
	    
	    int updateCheck = projectSchDao.updateByPrimaryKeySelective(record);
	    
		if(updateCheck == 1) {
			map.put("result", "성공");
		} else {
			map.put("result", "실패");
		}
		
		return map;
	}
	
	public Map<String, Object> deleteScheduleItem(Map<String, Object> request) {
        HashMap<String, Object> map = new HashMap<>();

		//회원정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
        //프로젝트id, 시작날짜, 종료날짜, 시작시간, 종료시간
		//파라미터 받기
		int schId = Integer.parseInt(request.get("schId").toString());
	    
	    int deleteCheck = projectSchDao.deleteByPrimaryKey(schId);
	    
		if(deleteCheck == 1) {
			map.put("result", "성공");
		} else {
			map.put("result", "실패");
		}
		
		return map;
	}
}
