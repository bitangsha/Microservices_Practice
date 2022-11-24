package com.microservice.exceptions;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
														LocalDateTime.now(), 
														ex.getMessage(),
														request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), 
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		//Do this using Stream api later   vvvvvvvvvvv
		int errorCount=0;
		StringBuilder errors = new StringBuilder();
		for(int i=0; i<ex.getAllErrors().size(); i++) {
			errors.append(" [");
			errors.append(++errorCount);
			errors.append("] ");			
			errors.append(ex.getAllErrors().get(i).getDefaultMessage());			
		}
		//Do this using Stream api later  ^^^^^^^^^
		
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), 
				//ex.getMessage(), //This will produce very large message, so commenting it and using customized one below
				//ex.getFieldError().getDefaultMessage(),
				errors.toString(),
				request.getDescription(false));
		
		
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);		
	}

}
