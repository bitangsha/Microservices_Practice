package com.microservice.model;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonFilter("USERFILTER")
public class User {
	
	private Integer id;
	
	@Size(min=2, message = "Name should have at least 2 chars")	//min size of name should be 2
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birthday should be in the past")	//bday should be in past
	@JsonProperty("user_dob")
	private LocalDate dob;
	
	@JsonIgnore		//this wont send this value in response
	private int AccountNo;
	
	private String mobileNo;
	
	private String address;

	
}
