package com.invoice.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProduct;
import com.invoice.api.entity.Cart;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.repository.RepoCart;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;
import com.invoice.config.client.ProductClient;
import com.invoice.exception.ApiException;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;
	
	@Autowired
	RepoCart repoCart;
	
	@Autowired
	ProductClient productCtl;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	@Override
	public ApiResponse generateInvoice(String rfc) {
		/*
		 * Requerimiento 5
		 * Implementar el método para generar una factura 
		 */
		List<Cart> cartItems = repoCart.findByRfcAndStatus(rfc, 1);

		if(cartItems.isEmpty()){
			throw new ApiException(HttpStatus.NOT_FOUND, "the cart has no items");
		}
			
		Map<Cart, DtoProduct> data = new HashMap<>();
		for (Cart cart : cartItems) {
			data.put(cart, getDtoProduct(cart));
		}

		repoCart.clearCart(rfc);
		
		return new ApiResponse("invoice generated");
	}

	
	
	private DtoProduct getDtoProduct(Cart cart){
		try{
			ResponseEntity<DtoProduct> response = productCtl.getProduct(cart.getGtin());
			if (response.getStatusCode() == HttpStatus.OK){
				return  (DtoProduct) response.getBody();
			} else {
				String message = String.format("product %s does not exist", cart.getGtin());
				throw new ApiException(HttpStatus.NOT_FOUND, message);
			}
		} catch(Exception e){
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve product information");
		}
	}

}
