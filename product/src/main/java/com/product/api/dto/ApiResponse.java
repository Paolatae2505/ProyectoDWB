
package com.product.api.dto;

/**
 * Implementación de la clase ApiResponse
 * @author Vargas Bravo Paola
 * @version 1.0 (12 de marzo 2023)
 * @since DWB (2023-2)
 */
public class ApiResponse {
	  /// Atributos
	private String message;
	
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crea un objeti ApiResponse apartir de un mensaje
	 * @param message
	 */
	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	
    /**
     * Regresa el atributo mensaje
     * @return
     */
	public String getMessage() {
		return message;
	}
    /**
     * Le asigna un nuevo valor al atributo mensaje
     * @param message
     */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Genera un respresentacion en String 
	 * de un objeto 
	 */
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + "]";
	}

}
