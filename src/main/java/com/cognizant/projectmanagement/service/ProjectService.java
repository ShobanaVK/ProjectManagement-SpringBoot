package com.cognizant.projectmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.projectmanagement.dao.Project;
import com.cognizant.projectmanagement.dao.User;
import com.cognizant.projectmanagement.model.ProjectObj;
import com.cognizant.projectmanagement.repository.ProjectRepository;
import com.cognizant.projectmanagement.repository.TaskRepository;
import com.cognizant.projectmanagement.repository.UserRepository;

@Service
public class ProjectService {

	@Autowired 
	private ProjectRepository projectRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	TaskRepository taskRepo;
	
//	@Autowired
//	private Project project;
	
//	@Autowired
//	private ProjectObj p;
	
//	@Autowired
//	private  User u;
	
	public ProjectObj addProject(ProjectObj p) {
		Project project = new Project();
		project.setProject(p.getProjectName());
		project.setStartDate(p.getStartDate());
		project.setEndDate(p.getEndDate());
		project.setPriority(p.getPriority());
		project.setUserId(p.getUserId());
		projectRepository.save(project);
		p.setCompletedTaskNumber(0);
		p.setTaskNumber(0);
		return p;
	}
	
	public ProjectObj updateProject(ProjectObj p) {
		Project project = projectRepository.findOne(p.getProjectId());
		if(project!= null){
			project.setProject(p.getProjectName());
			project.setStartDate(p.getStartDate());
			project.setEndDate(p.getEndDate());
			project.setPriority(p.getPriority());
			project.setUserId(p.getUserId());
			projectRepository.save(project);
			return p;
		}else{
			return null;
		}	
	}
	
	public List<ProjectObj> getAllProject() {
		 Iterable<Project> projectList =  projectRepository.findAll();
		 List<ProjectObj> projectResponseList = new ArrayList<>();
		 for(Project project: projectList){
			 ProjectObj  p = new ProjectObj();
			 p.setProjectId(project.getProjectId());
			 p.setProjectName(project.getProject());
			 p.setStartDate(project.getStartDate());
			 p.setEndDate(project.getEndDate());
			 p.setPriority(project.getPriority());
			 p.setTaskNumber(taskRepo.findAllByProjectId(project.getProjectId()).size());
			 p.setCompletedTaskNumber(taskRepo.findAllByProjectIdAndStatus(project.getProjectId(), "COMPLETED").size());
			 p.setUserId(project.getUserId());
			 if(project.getUserId() != null){
				 User  u = userRepository.findOne(project.getUserId());
				 if( u != null){
					 p.setManager(u.getFirstName());
				 }
			 }
			 
			 projectResponseList.add(p);
		 }
		
		return projectResponseList;
	}
	
}
