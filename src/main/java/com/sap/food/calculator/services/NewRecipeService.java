package com.sap.food.calculator.services;

import java.text.ParseException;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sap.food.calculator.model.Recipe;
import com.sap.food.calculator.ndb.model.RecipeContent;
import com.sap.food.calculator.recipe.RecipeCooker;
import com.sap.food.calculator.services.modules.RecipeNutrientData;

@Path(NewRecipeService.NEW_RECIPE)
public class NewRecipeService {

	private static final String CREATE = "/create";
	static final String NEW_RECIPE = "/newRecipe";

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CREATE)
	public Recipe create(RecipeContent rawRecipe) throws ParseException {

		RecipeNutrientData data = new RecipeNutrientData();
		Recipe newRecipe;
		try {
			RecipeContent createRecipeNutrientReport = data.createRecipeNutrientReport(rawRecipe);

			RecipeCooker cooker = new RecipeCooker();

			newRecipe = cooker.cook(createRecipeNutrientReport);
		} catch (NoResultException e) {
			newRecipe = new Recipe();
		}

		return newRecipe;

	}

}
