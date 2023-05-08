package com.product.api.service;


import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
/**
 * Interfaz de  SvcCategory
 * @author Vargas Bravo Paola
 * @version 1.0 (06 de marzo 2023)
 * @since DWB (2023-2)
 */

public interface SvcCategory {
	
	 /**
     * Muestra la lista de categorias
     * @return --- List<Category>
     */
	 List<Category> getCategories();
	  /**
	  * Obtiene la categoria que es pasada como Id
	  * @param id ---- Integer id
	  * @return ------ Category 
	  */
	 Category readCategory(Integer category_id);
	   /**
	   * Crea una nueva categoria
	   * @param categoryIn ----- Category
	   * @return --- Message
	   */
	 ApiResponse createCategory(Category category);
	 /**
      * Actualiza una categoria
	  * @param category_id ---- Integer
	  * @param category ------- String
	  * @return --------------- Message
	 */
	 ApiResponse  updateCategory(Integer category_id,Category category);	
	 /**
	  * Elimina una categoria
	  * @param category_id ---- Integer
	  * @return -------- message
	  */
	 ApiResponse  deleteCategory(Integer category_id);
}
