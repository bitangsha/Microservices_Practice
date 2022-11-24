package com.microservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.model.User;

@Service
public class UserDao {

private static int usersCount=0;

	private static List<User> usersList = new ArrayList<>();
	
	static {
		usersList.add(new User(++usersCount, "Matthew", LocalDate.now().minusYears(30)));
		usersList.add(new User(++usersCount, "Jon", LocalDate.now().minusYears(34)));
		usersList.add(new User(++usersCount, "Lewis", LocalDate.now().minusYears(42)));
	}
	
	public List<User> findAll(){
		return usersList;
	}
	
}
