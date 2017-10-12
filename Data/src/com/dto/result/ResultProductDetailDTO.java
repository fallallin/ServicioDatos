package com.dto.result;

import java.io.Serializable;

/**
 * DTO para servicio de imposicion de consultar detalleproducto
 * 
 */
@SuppressWarnings("serial")
public class ResultProductDetailDTO implements Serializable {

	private String name;
	private String description;
	private String price;
	private String externalidentifier;
	private String producer;
	private String category;
	private String image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExternalidentifier() {
		return externalidentifier;
	}

	public void setExternalidentifier(String externalidentifier) {
		this.externalidentifier = externalidentifier;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
