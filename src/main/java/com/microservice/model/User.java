package com.microservice.model;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	private Integer id;
	
	@Size(min=2, message = "Name should have at least 2 chars")	//min size of name should be 2
	private String name;
	
	@Past(message = "Birthday should be in the past")	//bday should be in past
	private LocalDate dob;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}

	public User(int id, String name, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	

}
