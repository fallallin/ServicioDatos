package com.models;

import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.core.Response;

import com.entity.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualifier.Resource;

public class ProductModel extends Model{

	/**
	 * 
	 * @param payload
	 * @return Response
	 */
	public Response createNewProduct(String payload) {
		
		System.out.println("payload - " + payload);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Gson gson = gsonBuilder.create();
		
		Products product = gson.fromJson(payload, Products.class);
		String returnCode = "200";
		em = Resource.getEntityManager();
		
		//Insert using JTA persistence whit hibernate
		try{
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
			em.close();
			returnCode = "{\"status\":\"OK\","
					+ "\"message\":\"Product '" + product.getName() + "' created\"" + "}";
		}catch(Exception err){
			err.printStackTrace();
			returnCode = "{\"status\":\"500\","
					+ "\"message\":\"Resourse not created.\","
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(500).entity(returnCode).build();
		}
		
		return Response.status(201).entity(returnCode).build();
	}
	
	/**
	 * 
	 * @return
	 */
	public Response getProducts() {
		String response = null;
		try{

		em = Resource.getEntityManager();
		Query query = em.createQuery("FROM com.entity.Products");
		List<Products> list = query.getResultList();
		em.close();
		response = toJSONString(list);
		}catch(Exception err){
			response = "{\"status\":\"401\","
					+ "\"message\":\"No content found\","
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(401).entity(response).build();
		}
		return Response.ok(response).build();
	}
	
	/**
	 * 
	 * @param idProduct
	 * @return Response
	 */
	public Response getProduct(Integer idProduct) {
		
		String response = null;
		try{

		em = Resource.getEntityManager();
		Products existingProduct = em.find(Products.class, idProduct);

		if(null == existingProduct){
			response = "{\"status\":\"401\","
					+ "\"message\":\"No content found\","
					+ "\"developerMessage\":\"Product - " + idProduct + " not found\"" + "}";
			return Response.status(401).entity(response).build();
		}
		response = toJSONString(existingProduct);

		}catch(Exception err){
			response = "{\"status\":\"401\","
					+ "\"message\":\"No content found\","
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(401).entity(response).build();
		} finally {
			em.close();
		    System.gc();
		}
		return Response.ok(response).build();
		
	}
	
	/**
	 * 
	 * @param payload
	 * @return
	 */
	public Response updateProduct(String payload){
		
		System.out.println("payload - " + payload);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Gson gson = gsonBuilder.create();
		
		Products product = gson.fromJson(payload, Products.class);
		String returnCode = "200";
		em = Resource.getEntityManager();
		
		//Insert using JTA persistence whit hibernate
		try{
			em.getTransaction().begin();
			em.merge(product);
			em.getTransaction().commit();
			em.close();
			returnCode = "{\"status\":\"OK\","
					+ "\"message\":\"Product '" + product.getName() + "' updated\"" + "}";
		}catch(Exception err){
			err.printStackTrace();
			returnCode = "{\"status\":\"500\","
					+ "\"message\":\"Resourse not updated.\","
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(500).entity(returnCode).build();
		}
		
		return Response.status(201).entity(returnCode).build();
		
	}
	
	
	
	
}
