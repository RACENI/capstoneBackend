package com.capstone.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.capstone.dao.ProjectScheduleMapper;
import com.capstone.project.service.ProjectService;
import com.capstone.vo.ProjectSchedule;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@PostMapping("/api/joinProject")
    public ResponseEntity<Map<String, Object>> joinProject(
            @RequestPart("projectId") String projectId) {
        Map<String, Object> request = new HashMap<>();
        request.put("projectId", projectId);

        Map<String, Object> response = projectService.joinProject(request);

        return ResponseEntity.ok(response);
    }

	@PostMapping("/api/leaveProject")
	public ResponseEntity<Map<String, Object>> leaveProject(
			@RequestPart("projectId") String projectId) {
		Map<String, Object> request = new HashMap<>();
		request.put("projectId", projectId);
		
		Map<String, Object> response = projectService.leaveProject(request);
		
		return ResponseEntity.ok(response);
	}

	@PostMapping("/api/addScheduleItem")
	public ResponseEntity<Map<String, Object>> addScheduleItem(
			@RequestPart("projectId") String projectId,
			@RequestPart("startDt") String startDt,
			@RequestPart("endDt") String endDt,
			@RequestPart("startTime") String startTime,
			@RequestPart("endTime") String endTime,
			@RequestPart("title") String title,
			@RequestPart("content") String content) {
		Map<String, Object> request = new HashMap<>();
		request.put("projectId", projectId);
		request.put("startDt", startDt);
		request.put("endDt", endDt);
		request.put("startTime", startTime);
		request.put("endTime", endTime);
		request.put("title", title);
		request.put("content", content);
		
		Map<String, Object> response = projectService.addScheduleItem(request);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/api/modifyScheduleItem")
	public ResponseEntity<Map<String, Object>> modifyScheduleItem(
			@RequestPart("schId") String schId,
			@RequestPart("startDt") String startDt,
			@RequestPart("endDt") String endDt,
			@RequestPart("startTime") String startTime,
			@RequestPart("endTime") String endTime,
			@RequestPart("title") String title,
			@RequestPart("content") String content) {
		Map<String, Object> request = new HashMap<>();
		request.put("schId", schId);
		request.put("startDt", startDt);
		request.put("endDt", endDt);
		request.put("startTime", startTime);
		request.put("endTime", endTime);
		request.put("title", title);
		request.put("content", content);
		
		Map<String, Object> response = projectService.modifyScheduleItem(request);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/api/deleteScheduleItem")
	public ResponseEntity<Map<String, Object>> deleteScheduleItem(
			@RequestPart("schId") String schId) {
		Map<String, Object> request = new HashMap<>();
		request.put("schId", schId);
		
		Map<String, Object> response = projectService.deleteScheduleItem(request);
		
		return ResponseEntity.ok(response);
	}
}
