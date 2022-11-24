package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.exceptions.UserNotFoundException;
import com.microservice.model.User;
import com.microservice.service.UserDao;

@RestController
public class UserController {
	@Autowired
	UserDao userDao;
	
	@GetMapping(path = "/users/all")
	public List<User> getAllUsers(){
		return userDao.findAll();
	}
	
	@GetMapping(path="/users/{id}")	
	public User getUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id: "+id);
		}else {
			return user;
		}
	}
}
