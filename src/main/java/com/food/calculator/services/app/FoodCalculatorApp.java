package com.food.calculator.services.app;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.food.calculator.services.AuthenticatedUserService;
import com.food.calculator.services.NewRecipeService;
import com.food.calculator.services.ProductService;
import com.food.calculator.services.RecipeServiceImpl;

/**
 * Group all services in one application
 * 
 */
public class FoodCalculatorApp extends Application {

	private Set<Object> singletons = new HashSet<>();

	public FoodCalculatorApp() throws ServletException {

		setSingletons();
	}

	/**
	 * Sets all services used in the application
	 */
	private void setSingletons() {

		getSingletons().add(new JacksonJaxbJsonProvider());
		getSingletons().add(new AuthenticatedUserService());
		getSingletons().add(new ProductService());
		getSingletons().add(new RecipeServiceImpl());
		getSingletons().add(new NewRecipeService());
	}

	@Override
	public Set<Object> getSingletons() {

		return singletons;
	}
}
