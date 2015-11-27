package com.food.calculator.utils;

/**
 * Holds National Nutrition Database IDs for all nutrients used.
 *
 */
public enum NutrientsEnumAPI {

	FAT_SATURATED(606), FAT_UNSATURATED(645), TRANS_FAT(605), FIBER(291), SUGAR(269), PROTEIN(203), SODIUM(
			307), CHOLESTEROL(601), CALORIES(208);

	private final int nutrientID;

	private NutrientsEnumAPI(int nutrientID) {

		this.nutrientID = nutrientID;

	}

	public int getNutrientID() {

		return this.nutrientID;

	}
}
