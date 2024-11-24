package com.capstone.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.capstone.dao.ProjectsMapper;
import com.capstone.project.service.ProjectBoardService;

@Controller
public class ProjectBoardController {
	@Autowired
	private ProjectBoardService projectService;
	
	@PostMapping("/api/projectcreate")
    public ResponseEntity<Map<String, Object>> createBoard(
            @RequestPart("name") String name, // 프로젝트 이름
            @RequestPart("desc") String desc, // 프로젝트 소개
            @RequestPart("content") String content, // 아이디어 내용
            @RequestPart("topic") String topic, // 주제
            @RequestPart("region") String region, // 지역
            @RequestPart("startDate") String startDate, // 프로젝트 시작일
            @RequestPart("endDate") String endDate // 프로젝트 종료일
            ) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("desc", desc);
        request.put("content", content);
        request.put("topic", topic);
        request.put("region", region);
        request.put("startDate", startDate);
        request.put("endDate", endDate);

        Map<String, Object> response = projectService.createBoard(request);

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/api/projectmodify")
    public ResponseEntity<Map<String, Object>> modifyBoard(
            @RequestPart("id") int id,
            @RequestPart("name") String name, // 프로젝트 이름
            @RequestPart("desc") String desc, // 프로젝트 소개
            @RequestPart("content") String content, // 아이디어 내용
            @RequestPart("topic") String topic, // 주제
            @RequestPart("region") String region, // 지역
            @RequestPart("phase") String phase, // 현재진행단계
            @RequestPart("startDate") String startDate, // 프로젝트 시작일
            @RequestPart("endDate") String endDate // 프로젝트 종료일
            ) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", name);
        request.put("desc", desc);
        request.put("content", content);
        request.put("topic", topic);
        request.put("region", region);
        request.put("startDate", startDate);
        request.put("endDate", endDate);

        Map<String, Object> response = projectService.modifyBoard(request);

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/api/projectdelete")
    public ResponseEntity<Map<String, Object>> deleteBoard(@RequestPart("id") int id) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);

        Map<String, Object> response = projectService.deleteBoard(request);

        return ResponseEntity.ok(response);
    }
}