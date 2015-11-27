package com.sap.food.calculator.services;

import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.helpers.MessageFormatter;

import com.sap.food.calculator.dao.RecipeDAO;
import com.sap.food.calculator.model.Recipe;

@Path("/recipe")
public class RecipeServiceImpl implements RecipeService {

	private static final String RESPONSE_NO_FUNCTIONALITY_ADDED_YET_MESSAGE = "No functionality added yet";
	private static final String RESPONSE_RECIPE_SUCCESSFULLY_DELETED_MESSAGE = "Recipe with id: \"{}\" was successfully deleted.";
	private static final String RESPONSE_OPERATION_FAILED_MESSAGE = "Operation failed: {}";
	private static final String RESPONSE_RECIPE_SUCCESSFULLY_ADDED_MESSAGE = "Recipe with name: \"{}\" was successfully added.";
	private RecipeDAO recipeDAO = new RecipeDAO();

	@Override
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Recipe recipe) {

		Response response;
		String message;

		try {

			recipeDAO.add(recipe);
			message = MessageFormatter.format(RESPONSE_RECIPE_SUCCESSFULLY_ADDED_MESSAGE, recipe.getName())
					.getMessage();
			response = Response.status(Status.OK).entity(message).build();

		} catch (IllegalArgumentException e) {

			message = MessageFormatter.format(RESPONSE_OPERATION_FAILED_MESSAGE, e.getMessage()).getMessage();
			response = Response.status(Status.BAD_REQUEST).entity(message).build();

		}

		return response;
	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/findByName")
	public Response findByName(@QueryParam("name") String name) {

		try {

			List<Recipe> recipes = recipeDAO.findByName(name);
			return Response.status(Status.OK).entity(recipes).build();

		} catch (IllegalArgumentException e) {

			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@Override
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/findAll")
	public Response findAll() {

		List<Recipe> allRecipes = recipeDAO.findAll();
		return Response.status(Status.OK).entity(allRecipes).build();
	}

	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response delete(@QueryParam("id") Long id) {

		Response response;
		String message;

		try {

			recipeDAO.delete(id);
			message = MessageFormatter.format(RESPONSE_RECIPE_SUCCESSFULLY_DELETED_MESSAGE, id).getMessage();
			response = Response.status(Status.OK).entity(message).build();

		} catch (IllegalArgumentException e) {

			message = MessageFormatter.format(RESPONSE_OPERATION_FAILED_MESSAGE, e.getMessage()).getMessage();
			response = Response.status(Status.BAD_REQUEST).entity(message).build();

		} catch (NoResultException e) {

			message = MessageFormatter.format(RESPONSE_OPERATION_FAILED_MESSAGE, e.getMessage()).getMessage();
			response = Response.status(Status.NOT_FOUND).entity(message).build();
		}

		return response;
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(Recipe recipe) {

		recipeDAO.update(recipe);
		return Response.status(Status.OK).entity(RESPONSE_NO_FUNCTIONALITY_ADDED_YET_MESSAGE).build();
	}

}
