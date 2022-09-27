package com.neosoft.poc.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.neosoft.poc.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {

		String message = ex.getMessage();

		ApiResponse apiRespone = new ApiResponse(message, false);

		return new ResponseEntity<ApiResponse>(apiRespone, HttpStatus.NOT_FOUND);

	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)  
	{

		Map<String,String>resp=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName=((FieldError)error).getField();
			
			String message=error.getDefaultMessage();
			
			resp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
}
