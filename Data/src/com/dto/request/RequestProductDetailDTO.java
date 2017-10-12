package com.dto.request;

import java.io.Serializable;

/**
 * DTO
 * @author FALLALLIN
 */
@SuppressWarnings("serial")
public class RequestProductDetailDTO implements Serializable {
	
	String idProduct;

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}
	
	
	

}
