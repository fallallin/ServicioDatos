package com.webservice;


import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dto.request.RequestProductDetailDTO;
import com.dto.result.ResultProductDetailDTO;
import com.entity.Products;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.interfaces.ILProducts;
import com.interfaces.IRProducts;
import com.models.ProductModel;
import com.qualifier.Resource;

@Path("/dataService")
public class DataService {

	@EJB
	private IRProducts productsEJB;
	
	
	@Inject
	private EntityManager em;
	
	private ProductModel productModel = new ProductModel();
	
	@POST
	@Path("/createNewProduct")
	@Consumes(MediaType.APPLICATION_JSON )
	@Produces(MediaType.APPLICATION_JSON )
	public Response createNewProduct(String payload) {
		return this.productModel.createNewProduct(payload);
	}
	
	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		return this.productModel.getProducts();
	}
	
	@GET
	@Path("/getProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@QueryParam("idProduct") Integer idProduct) {
		return this.productModel.getProduct(idProduct);
	}
	
	@PUT
	@Path("/updateProduct")
	@Consumes(MediaType.APPLICATION_JSON )
	@Produces(MediaType.APPLICATION_JSON )
	public Response updateProduct(String payload){
		return this.updateProduct(payload);
	}
	
	
	@GET
	@Path("/getDetailProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResultProductDetailDTO> getDetailProduct(@QueryParam("idProduct") Integer idProduct) {
		
		RequestProductDetailDTO rpd = new RequestProductDetailDTO();
		rpd.setIdProduct(String.valueOf(idProduct));
		
		return this.productsEJB.getProductDetail(rpd);
	}
	
	

}
