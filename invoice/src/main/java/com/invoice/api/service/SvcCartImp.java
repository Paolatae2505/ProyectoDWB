package com.invoice.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoCustomer;
import com.invoice.api.dto.DtoProduct;
import com.invoice.api.entity.Cart;
import com.invoice.api.repository.RepoCart;
import com.invoice.config.client.CustomerClient;
import com.invoice.config.client.ProductClient;
import com.invoice.exception.ApiException;

import feign.FeignException;

@Service
public class SvcCartImp implements SvcCart {

	@Autowired
	RepoCart repo;
	
	@Autowired
	CustomerClient customerClt;
	
	@Autowired 
	ProductClient productClt;
	@Override
	public List<Cart> getCart(String rfc) {
		return repo.findByRfcAndStatus(rfc,1);
	}

	@Override
	public ApiResponse addToCart(Cart cart) {
		if(!validateCustomer(cart.getRfc()))
			throw new ApiException(HttpStatus.BAD_REQUEST, "customer does not exist");
			
		/*
		 * Requerimiento 3
		 * Validar que el GTIN exista. Si existe, asignar el stock del producto a la variable product_stock 
		 */
		DtoProduct product = getProduct(cart.getGtin());
		Integer productStock = product.getStock();
		
		if(cart.getQuantity() > productStock) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "invalid quantity");
		}
		
		/*
		 * Requerimiento 4
		 * Validar si el producto ya había sido agregado al carrito para solo actualizar su cantidad
		 */
		
		Cart savedCart = repo.findByGtinAndRfc(cart.getGtin(), cart.getRfc());
		if (savedCart != null) {
			int nQuantity = cart.getQuantity() + savedCart.getQuantity();
			savedCart.setQuantity(nQuantity);

			if (nQuantity > productStock || nQuantity <= 0) {
				throw new ApiException(HttpStatus.BAD_REQUEST, "invalid quantity");
			}
			repo.save(savedCart);
			return new ApiResponse("quantity updated");
		}

		if (cart.getQuantity() < 1) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "invalid quantity");
		}

		cart.setStatus(1);
		repo.save(cart);
		return new ApiResponse("item added");
	}

	@Override
	public ApiResponse removeFromCart(Integer cart_id) {
		if (repo.removeFromCart(cart_id) > 0)
			return new ApiResponse("item removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "item cannot be removed");
	}

	@Override
	public ApiResponse clearCart(String rfc) {
		if (repo.clearCart(rfc) > 0)
			return new ApiResponse("cart removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "cart cannot be removed");
	}
	
	private boolean validateCustomer(String rfc) {
		try {
			ResponseEntity<DtoCustomer> response = customerClt.getCustomer(rfc);
			
			if(response.getStatusCode() == HttpStatus.OK)
				return true;
			else
				return false;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve customer information");
		}
	}
	
	private DtoProduct getProduct(String gtin){
		try{
			ResponseEntity<DtoProduct> response =  productClt.getProduct(gtin);
			if (response.getStatusCode() == HttpStatus.OK){
				return response.getBody();
			} else {
				throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
			}
		} catch(FeignException e){
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
		} catch(Exception e){
			//System.err.println(e);
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve product information");
		}
	}

}
