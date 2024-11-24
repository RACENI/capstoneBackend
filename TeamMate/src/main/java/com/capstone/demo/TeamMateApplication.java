package com.capstone.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.capstone")
@MapperScan("com.capstone.dao")
public class TeamMateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamMateApplication.class, args);
	}
}
