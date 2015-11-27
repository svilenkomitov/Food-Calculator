package com.sap.food.calculator.services;

import javax.ws.rs.core.Response;

import com.sap.food.calculator.model.Recipe;

/**
 * Interface for CRUD operations for recipe
 *
 */
public interface RecipeService {

	Response findByName(String name);

	Response findAll();

	Response delete(Long id);

	Response update(Recipe recipe);

	Response add(Recipe recipe);
}
