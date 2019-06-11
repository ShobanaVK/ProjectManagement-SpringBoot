package com.cognizant.projectmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProjectMangementFinalApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProjectMangementFinalApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources(ProjectMangementFinalApplication.class);
	}
}
