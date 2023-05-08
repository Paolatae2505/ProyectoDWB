package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
/**
 * Implementación de la clase RestExceptionHandler
 * @author Vargas Bravo Paola
 * @version 1.0 (06 de marzo 2023)
 * @since DWB (2023-2)
 */
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ApiException.class)
    protected ResponseEntity<ExceptionResponse> handleApiException(ApiException exception, WebRequest request){       
        ExceptionResponse response = new ExceptionResponse(); 
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(exception.getStatus().value());
        response.setError(exception.getStatus());
        response.setMessage(exception.getMessage());
        response.setPath(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        
        return new ResponseEntity<>(response, response.getError());
    }
}
