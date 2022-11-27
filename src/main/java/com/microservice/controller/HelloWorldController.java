package com.microservice.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}
	
	@GetMapping(path="/i18nhello")
	public String HelloWorldi18n() {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);
		
	}
	
	



}
