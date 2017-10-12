package com.models;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Model {
	@Inject
	protected EntityManager em;
	
	public String toJSONString(Object object){
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
		
	}
}
