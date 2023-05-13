package com.invoice.config.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProduct;


public interface ProductClient {

	/*
	 * Requerimiento 3
	 * Actualizar método getProduct para obtener la información necesaria de un producto
	 */
	public ResponseEntity<DtoProduct> getProduct(@PathVariable("gtin") String gtin);
	
	@PutMapping("product/{gtin}/stock/{stock}")
	public ResponseEntity<ApiResponse> updateProductStock(@PathVariable("gtin") String gtin, @PathVariable("stock") Integer stock);

}
