package com.product.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Implementación de la clase Category
 * @author Vargas Bravo Paola
 * @version 1.0 (28 Febrero 2023)
 * @since DWB (2023-2)
 */

@Entity
@Table(name = "category")
public class Category {
  
	   /// Atributos
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   @Column(name = "category_id")
	   private Integer category_id;
	   @NotNull
	   private String category;
	   private String acronym;
	   @JsonIgnore
	   @Min(value = 0)
	   @Max(value = 1)
	   private Integer status;
	  
	   /**
	    * Crea una instancia de Category por default
	    */
	   public Category() {
		   
	   }
	   /**	
	    * Crea una instancia de Category
	    * @param category_id --- Integer id de la categoria
	    * @param category ----- String nomnbre de la categoria
	    * @param acronym ------ String acronimo de la categoria
	    */
	   public Category(Integer category_id, String category, String acronym){
	      this.category_id = category_id;
	      this.category = category;
	      this.acronym = acronym;
	      this.status = 1; /// Categoria activada
	   }
	   /**
	    * Regresa el atributo category_id
	    * @return --- Integer category_id
	    */
	   public Integer getCategory_id(){
	    return category_id;
	   }
	   /**
	    * Regresa el atributo category
	    * @return -- String category
	    */
	   public String getCategory(){
	    return category;
	   }
	   /**
	    * Regresa el atributo acronym
	    * @return --- String acronym
	    */
	   public String getAcronym(){
	    return acronym;
	   }
	   
	   /**
	    * Regresa el atributo status
	    * @return --- Integer status
	    */
	   public Integer getStatus(){
	    return status;
	   }
	   /**
	    * Asigna el atributo status
	    * @param status --- Integer status
	    */
	   public void setStatus(int status) {
		   this.status = status;
	   }
	   /**
	    * Asigna el atributo id
	    * @param id ---- Integer id
	    */
	   public void setId(int id) {
		   this.category_id = id;
	   }
	   /**
	    * Compara si una categoria es igual a la otra
	    * @param ---- Category cat
	    * @return --- int 0 en caso de ser verdadero 1 en caso 
	    *             contrario
	    */
		public int sonIguales(Category cat) {
			return this.getCategory().equals(cat.getCategory()) ? 0 : 1;
		}
}
