package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Implementación de la clase ProductApplication
 * @author Vargas Bravo Paola
 * @version 1.0 (17 Febrero 2023)
 * @since DWB (2023-2)
 */
@EnableEurekaClient
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
