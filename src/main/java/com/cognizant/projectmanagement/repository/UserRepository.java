package com.cognizant.projectmanagement.repository;


import org.springframework.data.repository.CrudRepository;

import com.cognizant.projectmanagement.dao.User;


public interface UserRepository extends CrudRepository<User, Integer> {

}