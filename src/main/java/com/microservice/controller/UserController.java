package com.microservice.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public EntityModel<User> getUser(@PathVariable int id) {
		User user = userDao.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id: "+id);
		
		//Hateoas
		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
		
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		
		User savedUser = userDao.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{id}")	
	public void deleteUser(@PathVariable int id) {
		userDao.deleteById(id);		
	}
	
	
}
