package com.shopstyle.mscustomer.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){
		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not found.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> invalidFields(MethodArgumentNotValidException ex, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Field does not comply with policies.");
		error.setMessage("Incorrect field: " + ex.getFieldError().getField().toUpperCase());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> invalidFields(HttpMessageNotReadableException ex, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Invalid Input. Please select a valid entry.");
		error.setMessage("Select one of the following valid entries:" + ex.getCause().getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<StandardError> invalidLogin(LoginException ex, HttpServletRequest request){		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setError("Invalid data.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<StandardError> changePassword(InvalidPasswordException ex, HttpServletRequest request){		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setError("Invalid data.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> invalidFields(DataIntegrityViolationException ex, HttpServletRequest request){		
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Field does not comply with policies.");
		error.setMessage("Check the fields.");
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
