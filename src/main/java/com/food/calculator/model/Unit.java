package com.food.calculator.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Contains all units and constants to gram
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Unit {

	MILILITER("ml", 1.0), MILIGRAM("mg", 0.001), TEASPOON("tsp", 5.0), TABLESPOON("tbsp", 15.0), CUP("cup",
			237.0), LITER("liter", 1000.0), GRAM("g", 1.0), OUNCE("oz", 28.0), ETTO("etto", 100.0), POUND("lb",
					454.0), KILOGRAM("kg", 1000.0), KILOCALORIES("kcal", 1.0), CALORIES("cal", 0.001);

	@JsonProperty("unit")
	private final String unit;
	@JsonIgnore
	private final Double consToGram;

	private Unit(String unit, Double consToGram) {

		this.unit = unit;
		this.consToGram = consToGram;
	}

	public final String getUnit() {

		return this.unit;
	}

	public final Double getConsToGram() {
		return consToGram;
	}

	@JsonCreator
	public static Unit create(final JsonNode jsonNode) {

		for (Unit type : Unit.values()) {
			if (type.unit.equals(jsonNode.get("unit").asText())) {
				return type;
			}
		}
		return GRAM;
	}
}
