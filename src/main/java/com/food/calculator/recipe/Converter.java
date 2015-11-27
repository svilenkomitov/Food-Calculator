package com.food.calculator.recipe;

import com.food.calculator.model.Unit;

public class Converter {

	/**
	 * Converts the given value from fromUnit to gram
	 * 
	 * @param fromUnit
	 *            - actual unit
	 * @param value
	 *            - value
	 * @return converted value from fromUnit to gram
	 */
	public static double convertToGram(String fromUnit, Double value) {

		for (Unit unit : Unit.values()) {
			if (unit.getUnit().equals(fromUnit))
				value = value * unit.getConsToGram();
		}
		return value;
	}

}
