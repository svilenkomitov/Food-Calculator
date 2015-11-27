package com.food.calculator.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import com.food.calculator.ndb.model.Product;
import com.food.calculator.services.modules.ProductManager;


@Path("/products")
public class ProductService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findByName(@QueryParam("name") String name) {

		List<Product> products = ProductManager.findByName(name);

		return Response.status(Status.OK).entity(products).build();
	}

}
