package com.hrworkforcerestapp.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptioHandler 
{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, Object> hmap=new HashMap<>();
		hmap.put("Time", new Date());
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) 	
		{
			hmap.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<Map<String,Object>>(hmap, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}

}
