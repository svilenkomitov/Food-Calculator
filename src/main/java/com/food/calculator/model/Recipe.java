package com.food.calculator.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds data for recipe's nutrient report
 * 
 */
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
		@NamedQuery(name = "Recipe.findByName", query = "SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(:name)") })
public class Recipe implements Serializable {

	private static final String RECIPE_TO_STRING = "Recipe [id: {0}, name: {1}, servingUnit: {2}, servingValue: {3}, nutrients: {4}, thermalTreatment: {5}";

	private static final long serialVersionUID = 1671928520919051175L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Enumerated
	private Unit servingUnit;
	private Double servingValue;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<RecipeNutrient> nutrients;
	@Enumerated
	private ThermalTreatment thermalTreatment;

	public Recipe() {
	}

	public Recipe(String name, Unit serving_unit, Double serving_value, ThermalTreatment thermal_treatment,
			List<RecipeNutrient> newNutrients) {
		setName(name);
		setNutrients(newNutrients);
		setServingUnit(serving_unit);
		setServingValue(serving_value);
		setThermalTreatment(thermal_treatment);
	}

	/**
	 * @return the id of the recipe
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id of the recipe
	 * 
	 * @param id
	 *            the id of the recipe
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name of the recipe
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the recipe
	 * 
	 * @param name
	 *            the name of the recipe
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the unit for a serving of the recipe
	 */
	public Unit getServingUnit() {
		return servingUnit;
	}

	/**
	 * Sets the unit for a serving of the recipe
	 * 
	 * @param servingUnit
	 */
	public void setServingUnit(Unit servingUnit) {
		this.servingUnit = servingUnit;
	}

	/**
	 * @return the value for a serving of the recipe
	 */
	public Double getServingValue() {
		return servingValue;
	}

	/**
	 * Sets the value for a serving of the recipe
	 * 
	 * @param servingValue
	 */
	public void setServingValue(Double servingValue) {
		this.servingValue = servingValue;
	}

	/**
	 * @return the thermal treatment type of the recipe
	 */
	public ThermalTreatment getThermalTreatment() {
		return thermalTreatment;
	}

	/**
	 * Sets the thermal treatment type of the recipe
	 * 
	 * @param thermalTreatment
	 */
	public void setThermalTreatment(ThermalTreatment thermalTreatment) {
		this.thermalTreatment = thermalTreatment;
	}

	/**
	 * @return list of all nutrients contained in the recipe
	 */
	public List<RecipeNutrient> getNutrients() {
		return nutrients;
	}

	/**
	 * Sets the nutrients contained in the recipe
	 * 
	 * @param nutrients
	 *            the nutrients contained in the recipe
	 */
	public void setNutrients(List<RecipeNutrient> nutrients) {
		this.nutrients = nutrients;
	}

	@Override
	public String toString() {

		return MessageFormat.format(RECIPE_TO_STRING, id, name, servingUnit, servingValue, nutrients, thermalTreatment);
	}

}
