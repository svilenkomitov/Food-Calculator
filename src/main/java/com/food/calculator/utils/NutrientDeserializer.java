package com.food.calculator.utils;

import java.lang.reflect.Type;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.food.calculator.cache.model.ProductNutrientTable;
import com.food.calculator.model.Nutrient;
import com.food.calculator.model.Unit;
import com.food.calculator.recipe.Converter;

public class NutrientDeserializer implements JsonDeserializer<ProductNutrientTable> {

	private static final String NUTRIENT_ID = "nutrient_id";

	private static final String DASH = "--";

	private static final Logger LOGGER = LoggerFactory.getLogger(Converter.class);
	private static final String PROBLEM_VALUE = "The nutrient({0})'s value is not correct";
	private static final String PROBLEM_GM = "The nutrient({0})'s gm is not correct";
	private static final String GM = "gm";
	private static final String NUTRIENT = "nutrient";
	private static final String UNIT = "unit";
	private static final String VALUE = "value";

	@Override
	public ProductNutrientTable deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject nutrientJson = json.getAsJsonObject();
		String name = nutrientJson.get(NUTRIENT).getAsString();
		String unit = nutrientJson.get(UNIT).getAsString();
		Integer id = nutrientJson.get(NUTRIENT_ID).getAsInt();
		Double gm = parseGM(nutrientJson.get(GM).getAsString(), name);
		Double value = parseValue(nutrientJson.get(VALUE).getAsString(), name);

		Nutrient nutrient = new Nutrient();
		nutrient.setName(fromIdToName(id));
		nutrient.setUnit(fromStringToUnitEnum(unit));
		return new ProductNutrientTable(nutrient, value, gm);
	}

	private Unit fromStringToUnitEnum(String unit2) {

		for (Unit unit : Unit.values()) {
			if (unit.getUnit().equals(unit2))
				return unit;
		}
		return null;
	}

	private String fromIdToName(Integer id) {

		for (NutrientsEnumAPI nutrient : NutrientsEnumAPI.values()) {
			if (id.equals(nutrient.getNutrientID())) {
				return nutrient.name();
			}
		}
		return null;
	}

	private Double parseValue(String value2, String nutrient) {

		Double value = 0.0;

		if (!value2.equals(DASH)) {
			try {
				value = Double.parseDouble(value2);
			} catch (Exception e) {

				LOGGER.debug(PROBLEM_VALUE);
				throw new NumberFormatException(MessageFormat.format(PROBLEM_VALUE, nutrient));
			}
		}

		return value;
	}

	private Double parseGM(String gm2, String nutrient) {
		Double gm = 0.0;

		if (!gm2.equals(DASH)) {
			try {
				gm = Double.parseDouble(gm2);
			} catch (Exception e) {
				LOGGER.debug(PROBLEM_GM);
				throw new NumberFormatException(MessageFormat.format(PROBLEM_GM, nutrient));
			}
		}

		return gm;
	}

}
