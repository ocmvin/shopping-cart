package com.ecart.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecart.customer.dto.response.ResponseBase;
import com.ecart.customer.dto.response.SingleRespDTO;
import com.ecart.customer.exception.RecordNotFound;

@RestControllerAdvice
public class ExceptionAdviceController {

	
	
	@ExceptionHandler(value = RecordNotFound.class)
	public ResponseEntity<ResponseBase> recordNotFound(Exception e){
		ResponseBase response =SingleRespDTO.builder().message(e.getMessage()).httpStatus(HttpStatus.NOT_FOUND).status(false).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
		
	}
}

