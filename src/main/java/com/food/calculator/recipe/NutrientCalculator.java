package com.food.calculator.recipe;

import java.text.MessageFormat;

/**
 * Used to calculate for each nutrient's value
 */
public class NutrientCalculator {

	private double fatSaturated;
	private double fatUnSaturated;
	private double transFat;
	private double fiber;
	private double sugar;
	private double protein;
	private double sodium;
	private double cholesterol;
	private double calories;

	private static final String TOSTRING = "NutrientCalculator [fatSaturated= {0}, fatUnSaturated= {1}, transFat= {2}, fiber={3},"
			+ " sugar= {4}, protein= {5}, sodium= {6}, cholesterol= {7}, calories= {8}]";

	public NutrientCalculator() {
		this(0, 0, 0, 0, 0, 0, 0, 0, 0);
	}

	public NutrientCalculator(double fatSaturated, double fatUnSaturated, double transFat, double fiber, double sugar,
			double protein, double sodium, double cholesterol, double calories) {
		this.fatSaturated = fatSaturated;
		this.fatUnSaturated = fatUnSaturated;
		this.transFat = transFat;
		this.fiber = fiber;
		this.sugar = sugar;
		this.protein = protein;
		this.sodium = sodium;
		this.cholesterol = cholesterol;
		this.calories = calories;
	}

	public NutrientCalculator(NutrientCalculator nutrientCalculator) {
		this(nutrientCalculator.fatSaturated, nutrientCalculator.fatUnSaturated, nutrientCalculator.transFat,
				nutrientCalculator.fiber, nutrientCalculator.sugar, nutrientCalculator.protein,
				nutrientCalculator.sodium, nutrientCalculator.cholesterol, nutrientCalculator.calories);
	}

	public void addFatSaturated(double x) {
		fatSaturated += x;
	}

	public void addFatUnSaturated(double x) {
		fatUnSaturated += x;
	}

	public void addTransFat(double x) {
		transFat += x;
	}

	public void addFiber(double x) {
		fiber += x;
	}

	public void addSugar(double x) {
		sugar += x;
	}

	public void addProtein(double x) {
		protein += x;
	}

	public void addSodium(double x) {
		sodium += x;
	}

	public void addCholesterol(double x) {
		cholesterol += x;
	}

	public void addCalories(double x) {
		calories += x;
	}

	public double getFatSaturated() {
		return fatSaturated;
	}

	public void setFatSaturated(double fatSaturated) {
		this.fatSaturated = fatSaturated;
	}

	public double getFatUnSaturated() {
		return fatUnSaturated;
	}

	public void setFatUnSaturated(double fatUnSaturated) {
		this.fatUnSaturated = fatUnSaturated;
	}

	public double getTransFat() {
		return transFat;
	}

	public void setTransFat(double transFat) {
		this.transFat = transFat;
	}

	public double getFiber() {
		return fiber;
	}

	public void setFiber(double fiber) {
		this.fiber = fiber;
	}

	public double getSugar() {
		return sugar;
	}

	public void setSugar(double sugar) {
		this.sugar = sugar;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getSodium() {
		return sodium;
	}

	public void setSodium(double sodium) {
		this.sodium = sodium;
	}

	public double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return MessageFormat.format(TOSTRING, fatSaturated, fatUnSaturated, transFat, fiber, sugar, protein, sodium,
				cholesterol, calories);
	}
}
