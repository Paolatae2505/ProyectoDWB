package com.product.api.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.exception.ApiException;


/**
 * Implementación de la clase CltrCategory
 * @author Vargas Bravo Paola
 * @version 1.0 (06 de marzo 2023)
 * @since DWB (2023-2)
 */
@RestController
public class CltrCategory {
	
	/// Atributos
	@Autowired
	SvcCategory categoryService;

    /**
     * Muestra la lista de categorias
     * @return --- List<Category>
     */
	@GetMapping("/category")
	public ResponseEntity<List<Category>> listCategories() {
		return new ResponseEntity<>(categoryService.getCategories(),
				HttpStatus.OK);	
	}
	
    /**
     * Obtiene la categoria que es pasada como Id
     * @param id ---- Integer id
     * @return ------ Category 
     */
	@GetMapping( "/category/{id}")
	public ResponseEntity<Category> readCategory(@PathVariable Integer id) {	
			return new ResponseEntity<>(categoryService.readCategory(id),
					HttpStatus.OK);
	}
    /**
     * Crea una nueva categoria
     * @param categoryIn ----- Category
     * @return --- Message
     */
	@PostMapping("/category")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category categoryIn,
			BindingResult bindingResult) {	
		if(bindingResult.hasErrors()) {
       throw new ApiException(HttpStatus.BAD_REQUEST,
    		  bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
	  return new ResponseEntity<>(categoryService.createCategory(categoryIn), HttpStatus.OK);
		 
	}
    
	/**
	 * Actualiza una categoria
	 * @param category_id ---- Integer
	 * @param category ------- String
	 * @return --------------- Message
	 */
	@PutMapping("/category/{category_id}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer category_id, @RequestBody Category category,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>( categoryService.updateCategory(category_id, category),
				HttpStatus.OK);
		
	}
    /**
     * Elimina una categoria
     * @param category_id ---- Integer
     * @return -------- message
     */
	@DeleteMapping("/category/{category_id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer category_id) {
		
		return new ResponseEntity<>(categoryService.deleteCategory(category_id),
				HttpStatus.OK);
		
	}

}