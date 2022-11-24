package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
