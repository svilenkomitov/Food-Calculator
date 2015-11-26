package com.sap.food.calculator.recipe;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sap.food.calculator.cache.model.Product;
import com.sap.food.calculator.cache.model.ProductNutrientTable;
import com.sap.food.calculator.model.Nutrient;
import com.sap.food.calculator.model.Recipe;
import com.sap.food.calculator.model.RecipeNutrient;
import com.sap.food.calculator.model.ThermalTreatment;
import com.sap.food.calculator.model.Unit;
import com.sap.food.calculator.ndb.model.RecipeContent;
import com.sap.food.calculator.utils.NutrientsEnumAPI;

public class CalculatorTest {

	@Test
	public void testServlet() {
		ProductNutrientTable calTomato = new ProductNutrientTable(
				new Nutrient(NutrientsEnumAPI.CALORIES.name(), Unit.KILOCALORIES), 123.0, 20.0);
		ProductNutrientTable cholTomato = new ProductNutrientTable(
				new Nutrient(NutrientsEnumAPI.CHOLESTEROL.name(), Unit.MILIGRAM), 124.0, 30.0);

		ArrayList<ProductNutrientTable> nutrientsTomato = new ArrayList<>();

		nutrientsTomato.add(calTomato);
		nutrientsTomato.add(cholTomato);

		ProductNutrientTable calCheese = new ProductNutrientTable(
				new Nutrient(NutrientsEnumAPI.CALORIES.name(), Unit.KILOCALORIES), 123.0, 50.0);
		ProductNutrientTable cholCheese = new ProductNutrientTable(
				new Nutrient(NutrientsEnumAPI.CHOLESTEROL.name(), Unit.GRAM), 124.0, 40.0);

		ArrayList<ProductNutrientTable> nutrientsCheese = new ArrayList<>();

		nutrientsCheese.add(calCheese);
		nutrientsCheese.add(cholCheese);

		Product tomato = new Product();
		tomato.setName("Tomato");
		tomato.setNdbno(42932l);
		tomato.setNutrients(nutrientsTomato);
		tomato.setUnit(Unit.KILOGRAM);
		tomato.setValue(5.0);

		Product cheese = new Product();
		cheese.setName("Cheese");
		cheese.setNdbno(42933l);
		cheese.setNutrients(nutrientsCheese);
		cheese.setUnit(Unit.KILOGRAM);
		cheese.setValue(1.0);

		RecipeCooker cooker = new RecipeCooker();

		ArrayList<Product> products = new ArrayList<>();

		products.add(tomato);
		products.add(cheese);

		RecipeContent content = new RecipeContent("salata", Unit.KILOGRAM, 6.0, Unit.KILOGRAM, 3.0,
				ThermalTreatment.NONE, products);

		List<RecipeNutrient> nutrients = new ArrayList<>();

		nutrients.add(new RecipeNutrient(new Nutrient(NutrientsEnumAPI.CALORIES.name(), Unit.KILOCALORIES), 750.0));
		nutrients.add(new RecipeNutrient(new Nutrient(NutrientsEnumAPI.CHOLESTEROL.name(), Unit.GRAM), 200.75));

		Recipe recInfo = cooker.cook(content);

		for (RecipeNutrient nutrient : recInfo.getNutrients()) {
			if (nutrient.getNutrient().getName().equals(NutrientsEnumAPI.CALORIES.name())) {
				Double a = nutrient.getValue();
				Double b = 750.0;
				assertEquals(a, b);
			}
			if (nutrient.getNutrient().getName().equals(NutrientsEnumAPI.CHOLESTEROL.name())) {
				Double a = nutrient.getValue();
				Double b = 200.75;
				assertEquals(a, b);
			}

		}
	}

}
