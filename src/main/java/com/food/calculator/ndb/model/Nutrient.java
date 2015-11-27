package com.food.calculator.ndb.model;

import java.io.Serializable;

/**
 * Used to parse nutrients from National Nutrition Database API
 */
public class Nutrient implements Serializable {

	private static final long serialVersionUID = 6923612687264978820L;
	private static final String TOSTRING = "Nutrient [nutrient= {0}, unit= {1}, nutrient_id= {2}, gm= {3}, value= {4}]";

	private String nutrient;
	private String unit;
	private Integer nutrient_id;
	private Double gm;
	private Double value;

	public Nutrient(String nutrient, String unit, Integer nutrient_id, Double gm, Double value) {
		super();
		this.nutrient = nutrient;
		this.unit = unit;
		this.nutrient_id = nutrient_id;
		this.gm = gm;
		this.value = value;
	}

	public Nutrient(String nutrient, String unit, Double value) {
		super();
		this.nutrient = nutrient;
		this.unit = unit;
		this.value = value;
	}

	public String getNutrient() {
		return nutrient;
	}

	public void setNutrient(String nutrient) {
		this.nutrient = nutrient;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getNutrient_id() {
		return nutrient_id;
	}

	public void setNutrient_id(Integer nutrient_id2) {
		this.nutrient_id = nutrient_id2;
	}

	public Double getGm() {
		return gm;
	}

	public void setGm(Double gm2) {
		this.gm = gm2;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value2) {
		this.value = value2;
	}

	@Override
	public String toString() {
		return String.format(TOSTRING, nutrient, unit, nutrient_id, gm, value);
	}

}
