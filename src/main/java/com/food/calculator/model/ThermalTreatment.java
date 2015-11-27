package com.food.calculator.model;

import java.util.Hashtable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.food.calculator.utils.NutrientsEnumAPI;

/**
 * Initialize thermal treatment type coefficients for nutrients
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ThermalTreatment {

	BOILING("boiling", initBoiling()), FRYING("frying", initFrying()), ROASTING("roasting",
			initRoasting()), GRILLING("grilling", initGrilling()), NONE("none", initNone());

	private static final double GRILL_CALORIES_CHANGE = 0.07;
	private static final double ROAST_CALORIES_CHANGE = 0.15;
	private static final double FRY_CALORIES_CHANGE = 0.25;
	private static final double BOIL_CALORIES_CHANGE = -0.05;
	private static final double NO_CHANGE = 0.0;

	@JsonIgnore
	private Hashtable<NutrientsEnumAPI, Double> cookIdCoefTable = new Hashtable<>();
	@JsonProperty("type")
	private final String type;

	private ThermalTreatment(String type, Hashtable<NutrientsEnumAPI, Double> cookIdCoefTable) {
		this.cookIdCoefTable = cookIdCoefTable;
		this.type = type;
	}

	@JsonCreator
	public static ThermalTreatment create(final JsonNode jsonNode) {

		for (ThermalTreatment thermalTreatment : ThermalTreatment.values()) {
			if (thermalTreatment.type.equals(jsonNode.get("type").asText().toLowerCase())) {
				return thermalTreatment;
			}
		}
		return NONE;
	}

	public Hashtable<NutrientsEnumAPI, Double> getCookIdCoefTable() {
		return cookIdCoefTable;
	}

	private static Hashtable<NutrientsEnumAPI, Double> initBoiling() {

		return setValue(NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE,
				BOIL_CALORIES_CHANGE);
	}

	private static Hashtable<NutrientsEnumAPI, Double> initFrying() {

		return setValue(NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE,
				FRY_CALORIES_CHANGE);

	}

	private static Hashtable<NutrientsEnumAPI, Double> initRoasting() {
		return setValue(NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE,
				ROAST_CALORIES_CHANGE);
	}

	private static Hashtable<NutrientsEnumAPI, Double> initGrilling() {
		return setValue(NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE, NO_CHANGE,
				GRILL_CALORIES_CHANGE);
	}

	private static Hashtable<NutrientsEnumAPI, Double> setValue(double fat_saturated, double fat_unsaturated,
			double trans_fat, double fiber, double sugar, double protein, double sodium, double cholesterol,
			double calories) {
		Hashtable<NutrientsEnumAPI, Double> coef = new Hashtable<>();
		coef.put(NutrientsEnumAPI.FAT_SATURATED, fat_saturated);
		coef.put(NutrientsEnumAPI.FAT_UNSATURATED, fat_unsaturated);
		coef.put(NutrientsEnumAPI.TRANS_FAT, trans_fat);
		coef.put(NutrientsEnumAPI.FIBER, fiber);
		coef.put(NutrientsEnumAPI.SUGAR, sugar);
		coef.put(NutrientsEnumAPI.PROTEIN, protein);
		coef.put(NutrientsEnumAPI.SODIUM, sodium);
		coef.put(NutrientsEnumAPI.CHOLESTEROL, cholesterol);
		coef.put(NutrientsEnumAPI.CALORIES, calories);
		return coef;
	}

	private static Hashtable<NutrientsEnumAPI, Double> initNone() {
		Hashtable<NutrientsEnumAPI, Double> none = new Hashtable<>();
		for (NutrientsEnumAPI unit : NutrientsEnumAPI.values()) {
			none.put(unit, NO_CHANGE);
		}
		return none;
	}
}
