package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.microservice.filters.FieldFilters;
import com.microservice.model.User;
import com.microservice.service.UserDao;

@RestController
public class FilteringController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	FieldFilters fieldFilters;
	
	//"USERFILTER" filtername is added in the User model @JsonFilter("USERFILTER")
	private final String USERFILTER="USERFILTER";
	
	//dynamic filtering, so for filter 1 some fields will be ignored and for filter 2, some other fields will be ignored
	
	@GetMapping("/users/all/filter1")
	public MappingJacksonValue dynamicFiltering1() {
			
		List<User> users = userDao.findAll();		
		
		//@Jsonignore is applied on AccountNo, so AccountNo will not show even if we add a filter
		
		MappingJacksonValue mappingJacksonValue = fieldFilters.getFilter(users, USERFILTER, "id", "user_name", "user_dob", "AccountNo");
		return mappingJacksonValue;
		
	}

	@GetMapping("/users/all/filter2")
	public MappingJacksonValue dynamicFiltering2() {
		List<User> users = userDao.findAll();		
		
		MappingJacksonValue mappingJacksonValue = fieldFilters.getFilter(users, USERFILTER, "id", "user_name");
		return mappingJacksonValue;
	}
	
	

}
