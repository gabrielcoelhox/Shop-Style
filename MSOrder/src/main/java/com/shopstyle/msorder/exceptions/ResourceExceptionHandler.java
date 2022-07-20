package com.shopstyle.msorder.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException.FeignClientException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> objectNotFound(MethodArgumentNotValidException ex, HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not found.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<StandardError> objectNotFound(FeignClientException ex, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Not found.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}	
	
	@ExceptionHandler(QuantityUnavailableException.class)
	public ResponseEntity<StandardError> invalidFields(MethodArgumentNotValidException ex, HttpServletRequest request){
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Quantity unavailable.");
		error.setMessage(ex.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
