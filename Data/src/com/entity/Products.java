package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "products")
public class Products {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private String price;
	
	@Column(name="externalidentifier")
	private Integer externalidentifier;
	
	@Column(name="idproducer")
	private Integer idproducer;

	public Products() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getExternalidentifier() {
		return externalidentifier;
	}

	public void setExternalidentifier(Integer externalidentifier) {
		this.externalidentifier = externalidentifier;
	}

	public Integer getIdproducer() {
		return idproducer;
	}

	public void setIdproducer(Integer idproducer) {
		this.idproducer = idproducer;
	}
	
}
