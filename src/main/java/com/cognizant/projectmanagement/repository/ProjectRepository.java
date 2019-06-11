package com.cognizant.projectmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.cognizant.projectmanagement.dao.Project;

public interface ProjectRepository extends CrudRepository<Project,Integer>{

}
