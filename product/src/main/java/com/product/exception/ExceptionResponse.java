package com.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * Implementación de la clase ExceptionResponse
 * @author Vargas Bravo Paola
 * @version 1.0 (14 de marzo 2023)
 * @since DWB (2023-2)
 */
public class ExceptionResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss")
	private LocalDateTime timestamp;
	private Integer status;
	private HttpStatus error;
	private String message;
	private String path;
    
	/**
	 * Crea una instancia de ExceptionResponse
	 */
	public ExceptionResponse() {
		super();
	}
    /**
     * Regresa el atributo TimeStamp
     * @return
     */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
    /**
     * Asigna un nuevo valor al atributo timestamp
     * @param timestamp
     */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
    /**
     * Regresa el atributo status
     * @return
     */
	public Integer getStatus() {
		return status;
	}
    /**
     * Asigna un nuevo valor al atributo status
     * @param status
     */
	public void setStatus(Integer status) {
		this.status = status;
	}
    /**
     * Regresa el atributo Error
     * @return
     */
	public HttpStatus getError() {
		return error;
	}
     /**
      * Asigna un nuevo valor al atributo error
      * @param error
      */
	public void setError(HttpStatus error) {
		this.error = error;
	}
    /**
     * Regresa el atributo mensaje
     * @return
     */
	public String getMessage() {
		return message;
	}
    /**
     * Asigna un nuevo valor al atributo message
     * @param message
     */
	public void setMessage(String message) {
		this.message = message;
	}
    /**
     * Regresa el atributo Path
     */
	public String getPath() {
		return path;
	}
    /**
     * Asigna un nuevo valor al atributo path
     * @param path
     */
	public void setPath(String path) {
		this.path = path;
	}
}
