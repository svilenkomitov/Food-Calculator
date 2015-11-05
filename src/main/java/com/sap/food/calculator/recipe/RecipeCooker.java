package com.sap.food.calculator.recipe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.sap.food.calculator.cache.model.Product;
import com.sap.food.calculator.cache.model.ProductNutrientTable;
import com.sap.food.calculator.model.Recipe;
import com.sap.food.calculator.model.RecipeNutrient;
import com.sap.food.calculator.model.ThermalTreatment;
import com.sap.food.calculator.model.Unit;
import com.sap.food.calculator.ndb.model.RecipeContent;
import com.sap.food.calculator.utils.NutrientsEnumAPI;

/**
 * Used to calculate the recipe's nutrients from its products.
 *
 */
public class RecipeCooker {

	private static final String GRAM = "g";
	private static final String KCAL = "kcal";
	private static final Integer GMCONST = 100;
	private DecimalFormat decimalFormat = new DecimalFormat("########.###");

	public RecipeCooker() {
	}

	/**
	 * @param recipe
	 *            - Contains the products for recipe
	 * @return calculated recipe
	 */
	public Recipe cook(RecipeContent recipe) {
		Recipe recipeInfo = new Recipe();
		ArrayList<RecipeNutrient> newNutrients = new ArrayList<>();

		String servingUnit = recipe.getServing_unit().getUnit();
		String termalTreatment = recipe.getThermal_treatment().name();
		Double servingValue = recipe.getServing_value();
		Double totalValueRecipe = recipe.getValue();
		String unitRecipe = recipe.getUnit().getUnit();
		List<Product> products = recipe.getProducts();

		Double servingValueConverted = Converter.convertToGram(servingUnit, servingValue);

		Double totalValueConverted = Converter.convertToGram(unitRecipe, totalValueRecipe);

		NutrientCalculator nutrientsCalculator = new NutrientCalculator();

		for (Product product : products) {

			List<ProductNutrientTable> nutrients = product.getNutrients();

			Double valueConvertedProduct = Converter.convertToGram(product.getUnit().getUnit(), product.getValue());

			nutrientsCalculator = calculateNutrientCost(nutrientsCalculator, nutrients, valueConvertedProduct);

		}

		nutrientsCalculator = changeAfterCook(termalTreatment, nutrientsCalculator);

		newNutrients = createNutrients(nutrientsCalculator, totalValueConverted, servingValueConverted,
				termalTreatment);

		recipeInfo = new Recipe(recipe.getName(), recipe.getServing_unit(), recipe.getServing_value(),
				recipe.getThermal_treatment(), newNutrients);

		return recipeInfo;
	}

	private NutrientCalculator changeAfterCook(String termalTreatment, NutrientCalculator calculator) {

		NutrientCalculator nutrientCalculator = new NutrientCalculator(calculator);
		Hashtable<NutrientsEnumAPI, Double> table = null;

		for (ThermalTreatment cook : ThermalTreatment.values()) {
			if (cook.name().equals(termalTreatment))
				table = cook.getCookIdCoefTable();
		}

		double coef = table.get(NutrientsEnumAPI.CALORIES);
		nutrientCalculator.addCalories(coef * nutrientCalculator.getCalories());

		coef = table.get(NutrientsEnumAPI.CHOLESTEROL);
		nutrientCalculator.addCholesterol(coef * nutrientCalculator.getCholesterol());

		coef = table.get(NutrientsEnumAPI.FAT_SATURATED);
		nutrientCalculator.addFatSaturated(coef * nutrientCalculator.getFatSaturated());

		coef = table.get(NutrientsEnumAPI.FAT_UNSATURATED);
		nutrientCalculator.addFatUnSaturated(coef * nutrientCalculator.getFatUnSaturated());

		coef = table.get(NutrientsEnumAPI.FIBER);
		nutrientCalculator.addFiber(coef * nutrientCalculator.getFiber());

		coef = table.get(NutrientsEnumAPI.PROTEIN);
		nutrientCalculator.addProtein(coef * nutrientCalculator.getProtein());

		coef = table.get(NutrientsEnumAPI.SODIUM);
		nutrientCalculator.addSodium(coef * nutrientCalculator.getSodium());

		coef = table.get(NutrientsEnumAPI.SUGAR);
		nutrientCalculator.addSugar(coef * nutrientCalculator.getSugar());

		coef = table.get(NutrientsEnumAPI.TRANS_FAT);
		nutrientCalculator.addTransFat(coef * nutrientCalculator.getTransFat());

		return nutrientCalculator;
	}

	private NutrientCalculator calculateNutrientCost(NutrientCalculator calculator,
			List<ProductNutrientTable> nutrients, Double valueConvertedProduct) {

		NutrientCalculator nutrientsCalculator = new NutrientCalculator(calculator);

		for (ProductNutrientTable nutrient : nutrients) {
			Double valueNutrientConverted = Converter.convertToGram(nutrient.getNutrient().getUnit().getUnit(),
					nutrient.getGm());

			Double nutrientConst = (valueNutrientConverted / GMCONST) * valueConvertedProduct;

			String nutrientEnum = nutrient.getNutrient().getName();

			if (NutrientsEnumAPI.CALORIES.name().equals(nutrientEnum))
				nutrientsCalculator.addCalories(nutrientConst);

			if (NutrientsEnumAPI.CHOLESTEROL.name().equals(nutrientEnum))
				nutrientsCalculator.addCholesterol(nutrientConst);

			if (NutrientsEnumAPI.SODIUM.name().equals(nutrientEnum))
				nutrientsCalculator.addSodium(nutrientConst);

			if (NutrientsEnumAPI.PROTEIN.name().equals(nutrientEnum))
				nutrientsCalculator.addProtein(nutrientConst);

			if (NutrientsEnumAPI.SUGAR.name().equals(nutrientEnum))
				nutrientsCalculator.addSugar(nutrientConst);

			if (NutrientsEnumAPI.FIBER.name().equals(nutrientEnum))
				nutrientsCalculator.addFiber(nutrientConst);

			if (NutrientsEnumAPI.TRANS_FAT.name().equals(nutrientEnum))
				nutrientsCalculator.addTransFat(nutrientConst);

			if (NutrientsEnumAPI.FAT_UNSATURATED.name().equals(nutrientEnum))
				nutrientsCalculator.addFatUnSaturated(nutrientConst);

			if (NutrientsEnumAPI.FAT_SATURATED.name().equals(nutrientEnum))
				nutrientsCalculator.addFatSaturated(nutrientConst);

		}

		return nutrientsCalculator;

	}

	private ArrayList<RecipeNutrient> createNutrients(NutrientCalculator nutrientsCalculator,
			Double totalValueConverted, Double servingValueConverted, String termTreat) {

		ArrayList<RecipeNutrient> newNutrients = new ArrayList<>();

		Double portions = (servingValueConverted > 0.0) ? totalValueConverted / servingValueConverted : 0.0;

		Double valueNutrient = nutrientsCalculator.getCalories();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.CALORIES, KCAL, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getCholesterol();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.CHOLESTEROL, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getFatSaturated();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.FAT_SATURATED, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getFatUnSaturated();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.FAT_UNSATURATED, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getFiber();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.FIBER, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getProtein();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.PROTEIN, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getSodium();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.SODIUM, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getSugar();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.SUGAR, GRAM, valueNutrient, portions));

		valueNutrient = nutrientsCalculator.getTransFat();
		newNutrients.add(recipeNutrient(NutrientsEnumAPI.TRANS_FAT, GRAM, valueNutrient, portions));

		return newNutrients;
	}

	private RecipeNutrient recipeNutrient(NutrientsEnumAPI nutrientEnum, String unit, Double valueNutrient,
			Double portions) {
		Double portionValueConverted = (portions > 0.0) ? valueNutrient / portions : 0.0;
		return new RecipeNutrient(
				new com.sap.food.calculator.model.Nutrient(nutrientEnum.name(), fromStringToUnitEnum(unit)),
				Double.parseDouble(decimalFormat.format(portionValueConverted)));

	}

	private Unit fromStringToUnitEnum(String unit2) {
		for (Unit unit : Unit.values()) {
			if (unit.getUnit().equals(unit2))
				return unit;
		}
		return null;
	}

}
