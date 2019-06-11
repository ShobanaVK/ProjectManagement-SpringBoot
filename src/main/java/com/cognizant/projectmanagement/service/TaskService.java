package com.cognizant.projectmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.projectmanagement.dao.ParentTask;
import com.cognizant.projectmanagement.dao.Project;
import com.cognizant.projectmanagement.dao.Task;
import com.cognizant.projectmanagement.dao.User;
import com.cognizant.projectmanagement.model.TaskObj;
import com.cognizant.projectmanagement.repository.ParentTaskRepository;
import com.cognizant.projectmanagement.repository.ProjectRepository;
import com.cognizant.projectmanagement.repository.TaskRepository;
import com.cognizant.projectmanagement.repository.UserRepository;

@Service
public class TaskService {
	
	@Autowired 
	private TaskRepository taskRepo;
	
	@Autowired
	private ParentTaskRepository repo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	private ParentTask pTask;
//	
//	@Autowired
//	private Task t;
//	
//	@Autowired
//	private TaskObj obj;
//	
//	@Autowired
//	private Project p;
	
	public String addNewTask(TaskObj task) {
		if(task.isParentTask()){
			ParentTask pTask = new ParentTask();
			pTask.setParentTask(task.getTaskName());
			repo.save(pTask);
		}else{
			Task t = new Task();
			t.setParentId(task.getParentTaskId());
			t.setProjectId(task.getProjectId());
			t.setTask(task.getTaskName());
			t.setStartDate(task.getStartDate());
			t.setEndDate(task.getEndDate());
			t.setPriority(task.getPriority());
			t.setUserId(task.getUserId());	
			t.setStatus("STARTED");
			taskRepo.save(t);
		}
		
		return "Saved";
	}
	
	
	public List<TaskObj> getAllTasks() {
		List<TaskObj> taskObjList = new ArrayList<>();
		List<Task> taskList =  (List<Task>) taskRepo.findAll();
		for(Task t: taskList){
			TaskObj obj = new TaskObj();
			obj.setTaskId(t.getTaskId());
			obj.setParentTaskId(t.getParentId());
			obj.setProjectId(t.getProjectId());
			obj.setTaskName(t.getTask());
			obj.setStartDate(t.getStartDate());
			obj.setEndDate(t.getEndDate());
			obj.setPriority(t.getPriority());
			obj.setStatus(t.getStatus());
			obj.setUserId(t.getUserId());
			if(t.getParentId() != null){
				ParentTask	 pTask = repo.findOne(t.getParentId());
				if(pTask != null){
					obj.setParentTaskName(pTask.getParentTask());
				}
			}
			if(t.getProjectId() != null){
				Project	 p = projectRepo.findOne(t.getProjectId());
				if(p != null){
					obj.setProjectName(p.getProject());
				}	
			}
			if(t.getUserId() != null){
				User u = userRepo.findOne(t.getUserId());
				if(u != null){
					obj.setUserName(u.getFirstName());
				}
			}
			
			taskObjList.add(obj);
			
		}
		
		return taskObjList;
	}
	
	

	public Task updateTask(TaskObj task) {
		Task	t = taskRepo.findOne(task.getTaskId());
		t.setParentId(task.getParentTaskId());
		t.setProjectId(task.getProjectId());
		t.setTask(task.getTaskName());
		t.setStartDate(task.getStartDate());
		t.setEndDate(task.getEndDate());
		t.setPriority(task.getPriority());
	    t.setStatus(task.getStatus());     
		return taskRepo.save(t);
	}


	public Iterable<TaskObj> getTasksByProject(Integer id) {
		 List<TaskObj> taskObjList = new ArrayList<>();
			List<Task> taskList = taskRepo.findAllByProjectId(id);
			for(Task t: taskList){
				TaskObj obj = new TaskObj();
				obj.setTaskId(t.getTaskId());
				obj.setParentTaskId(t.getParentId());
				obj.setProjectId(t.getProjectId());
				obj.setTaskName(t.getTask());
				obj.setStartDate(t.getStartDate());
				obj.setEndDate(t.getEndDate());
				obj.setPriority(t.getPriority());
				obj.setStatus(t.getStatus());
				obj.setUserId(t.getUserId());
				if(t.getParentId() != null){
					ParentTask pTask = repo.findOne(t.getParentId());
					if(pTask != null){
						obj.setParentTaskName(pTask.getParentTask());
					}
				}
				if(t.getProjectId() != null){
					Project p = projectRepo.findOne(t.getProjectId());
					if(p != null){
						obj.setProjectName(p.getProject());
					}	
				}
				if(t.getUserId() != null){
					User u = userRepo.findOne(t.getUserId());
					if(u != null){
						obj.setUserName(u.getFirstName());
					}
				}
				
				taskObjList.add(obj);
				
			}
			return taskObjList;
	}
	


}
