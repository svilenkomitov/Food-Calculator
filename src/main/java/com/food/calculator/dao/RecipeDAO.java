package com.sap.food.calculator.dao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import com.sap.food.calculator.model.Recipe;
import com.sap.food.calculator.services.modules.EntityManagerProvider;

@Singleton
public class RecipeDAO {

	private static final String DEBUG_RECIPE_SUCCESSFULLY_DELETED_MESSAGE = "Recipe with id: \"{}\" and name: \"{}\" was successfully deleted.";
	private static final String ERROR_RECIPE_ID_MUST_NOT_BE_NULL_MESSAGE = "Recipe id must not be null.";
	private static final String ERROR_RECIPE_MUST_NOT_BE_NULL_MESSAGE = "Recipe must not be null.";
	private static final String ERROR_THE_RECIPE_NAME_MUST_NOT_BE_NULL_MESSAGE = "Recipe name must not be null.";
	private static final String ERROR_OPERATION_FAILED_MESSAGE = "Operation failed: ";
	private static final String DEBUG_RECIPE_WITH_ID_WAS_NOT_FOUND_MESSAGE = "Recipe with id: \"{}\" was not found.";
	private static final String DEBUG_RECIPES__FOUND_MESSAGE = "{} recipes containing: \"{}\" was found.";
	private static final String NAME = "name";
	private static final String UNCHECKED = "unchecked";
	private static final String RECIPE_FIND_ALL_QUERY_NAME = "Recipe.findAll";
	private static final String DEBUG_RECIPES_EXTRACTED_SUCCESSFULLY_MESSAGE = "{} recipes extracted successfully.";
	private static final String RECIPE_FIND_BY_NAME_QUERY_NAME = "Recipe.findByName";
	private static final String DEBUG_RECIPE_SUCCESSFULLY_ADDED_MESSAGE = "Recipe with name: \"{}\" was successfully added.";
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDAO.class);

	/**
	 * Adds recipe in the database
	 * 
	 * @param recipe
	 *            add recipe in database
	 */
	public void add(Recipe recipe) {

		if (recipe == null) {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
					ERROR_RECIPE_MUST_NOT_BE_NULL_MESSAGE);
			LOGGER.error(ERROR_OPERATION_FAILED_MESSAGE, illegalArgumentException);
			throw illegalArgumentException;
		}

		EntityManager em = new EntityManagerProvider().getEntityManager();
		em.getTransaction().begin();
		em.persist(recipe);
		em.getTransaction().commit();
		em.close();
		LOGGER.debug(DEBUG_RECIPE_SUCCESSFULLY_ADDED_MESSAGE, recipe.getName());
	}

	/**
	 * Returns all recipes saved in the database
	 * 
	 * @return list of all recipes in the database, if there is no recipes
	 *         returns empty list
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Recipe> findAll() {

		EntityManager em = new EntityManagerProvider().getEntityManager();
		List<Recipe> allRecipes = em.createNamedQuery(RECIPE_FIND_ALL_QUERY_NAME).getResultList();
		em.close();
		LOGGER.debug(DEBUG_RECIPES_EXTRACTED_SUCCESSFULLY_MESSAGE, allRecipes.size());

		return allRecipes;
	}

	/**
	 * Deletes the recipe with the given id from the database
	 * 
	 * @param id
	 *            - the id of the recipe to be deleted
	 */
	public void delete(Long id) {

		if (id == null) {

			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
					ERROR_RECIPE_ID_MUST_NOT_BE_NULL_MESSAGE);
			LOGGER.error(ERROR_OPERATION_FAILED_MESSAGE, illegalArgumentException);
			throw illegalArgumentException;
		}

		EntityManager em = new EntityManagerProvider().getEntityManager();
		Recipe recipe = em.find(Recipe.class, id);

		if (recipe == null) {

			String message = MessageFormatter.format(DEBUG_RECIPE_WITH_ID_WAS_NOT_FOUND_MESSAGE, id).getMessage();
			NoResultException noResultException = new NoResultException(message);
			LOGGER.error(ERROR_OPERATION_FAILED_MESSAGE, noResultException);
			throw noResultException;
		}

		em.getTransaction().begin();
		em.remove(recipe);
		em.getTransaction().commit();
		em.close();
		LOGGER.debug(DEBUG_RECIPE_SUCCESSFULLY_DELETED_MESSAGE, recipe.getId(), recipe.getName());

	}

	// add implementation
	/**
	 * @param recipe
	 */
	public void update(Recipe recipe) {

	}

	/**
	 * Returns all recipes by name from the database
	 * 
	 * @param name
	 *            the name of the searched recipe
	 * @return list of recipes with given name, if there is no recipes with such
	 *         name returns empty list
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Recipe> findByName(String name) {

		if (name == null) {

			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
					ERROR_THE_RECIPE_NAME_MUST_NOT_BE_NULL_MESSAGE);
			LOGGER.error(ERROR_OPERATION_FAILED_MESSAGE, illegalArgumentException);
			throw illegalArgumentException;
		}

		EntityManager em = new EntityManagerProvider().getEntityManager();
		List<Recipe> recipes = em.createNamedQuery(RECIPE_FIND_BY_NAME_QUERY_NAME).setParameter(NAME, "%" + name + "%")
				.getResultList();
		em.close();
		LOGGER.debug(DEBUG_RECIPES__FOUND_MESSAGE, recipes.size(), name);

		return recipes;
	}
}
