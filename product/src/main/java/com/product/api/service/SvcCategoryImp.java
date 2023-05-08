package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;


/**
 * Implementación de la clase SvcCategory
 * @author Vargas Bravo Paola
 * @version 1.0 (10 de marzo 2023)
 * @since DWB (2023-2)
 */
@Service
public class SvcCategoryImp implements SvcCategory {
  // Atributos
	@Autowired
	RepoCategory categoryRepository;
	 /**
     * Muestra la lista de categorias
     * @return --- List<Category>
     */
	@Override
	public List<Category> getCategories() {
		return categoryRepository.findByStatus(1);
	}
	 /**
	 * Obtiene la categoria que es pasada como Id
	 * @param id ---- Integer id
	 * @return ------ Category 
	 */
	@Override
	public Category readCategory(Integer category_id) {		
		Category readCategory = categoryRepository.findByCategoryId(category_id);
		if( readCategory == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "category does not exist"); 
		}else {
			return readCategory;
		}
	}
	 /**
	 * Crea una nueva categoria
	 * @param categoryIn ----- Category
	 * @return --- Message
	 */
	 @Override
	 public ApiResponse createCategory(Category category) {
	        Category categorySaved = (Category) categoryRepository.findByCategory(category.getCategory(),
	        		category.getAcronym(),category.getCategory_id());
	        if(categorySaved != null){
	            if(categorySaved.getStatus() == 0){
	                categoryRepository.activateCategory(category.getCategory_id());
	                return new ApiResponse("category has been activated");
	            } else {
	            	throw new ApiException(HttpStatus.BAD_REQUEST,"category alredy exists");
	            }
	        }else {
	        	 categoryRepository.createCategory(category.getCategory(),category.getAcronym());
	        	 return new ApiResponse("category created");
	        }
	       
	    }
	 /**
      * Actualiza una categoria
	  * @param category_id ---- Integer
	  * @param category ------- String
	  * @return --------------- Message
	 */
	@Override
	public ApiResponse updateCategory(Integer category_id, Category category) {
		Category categorySaved = (Category) categoryRepository.findAllByCategoryId(category_id);
		if(categorySaved == null) {
			throw new  ApiException(HttpStatus.BAD_REQUEST,"category does not exists");
			
		}
		else if(categorySaved.getStatus() == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST,"category is not active");
		} else if (categorySaved.sonIguales(category) == 0) {
			throw new ApiException(HttpStatus.BAD_REQUEST,"category already exists");
		} else {
			categoryRepository.updateCategory(category_id,category.getCategory(), category.getAcronym());
			return new ApiResponse("category updated");
		}	
	}
	 /**
     * Elimina una categoria
     * @param category_id ---- Integer
     * @return -------- message
     */
	@Override
	public ApiResponse deleteCategory(Integer category_id) {
		Category categorySaved = (Category) categoryRepository.findByCategoryId(category_id);
		if(categorySaved == null) 
			throw new ApiException(HttpStatus.NOT_FOUND,"category does not exist");
		else {
			categoryRepository.deleteById(category_id);
			return new ApiResponse("category removed"); 
		}
	}

}