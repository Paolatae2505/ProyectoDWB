package com.product.exception;

import org.springframework.http.HttpStatus;
/**
 * Implementación de la clase ApiException
 * @author Vargas Bravo Paola
 * @version 1.0 (12 de marzo 2023)
 * @since DWB (2023-2)
 */
public class ApiException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
/**
 * Crea una instaca de ApiException
 * @param status
 * @param message
 */
	public ApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;

	}
   /**
    * Regresa el atribito httpStatus
    * @return
    */
	public HttpStatus getStatus() {
		return status;
	}
    /**
     * Asigna un valor a el atributo HttpsStatus
     * @param status
     */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
 
}
