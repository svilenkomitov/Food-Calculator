package com.food.calculator.ndb.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.food.calculator.cache.model.Product;
import com.food.calculator.model.ThermalTreatment;
import com.food.calculator.model.Unit;

/**
 * Recipe used for calculating final Recipe report
 *
 */
@XmlRootElement(name = "Recipe")
public class RecipeContent {

	private static final String TOSTRING = "Recipe [name= {0}, unit= {1}, value= {2}, serving_unit= {3}, serving_value= {4}, thermal_treatment= {5}, products= {6}]";
	private String name;
	private Unit unit;
	private Double value;
	private Unit serving_unit;
	private Double serving_value;
	private ThermalTreatment thermal_treatment;
	private List<Product> products;

	public RecipeContent() {
	}

	public RecipeContent(String name, Unit unit, Double value, Unit serving_unit, Double serving_value,
			ThermalTreatment thermal_treatment, List<Product> products) {
		this.name = name;
		this.unit = unit;
		this.value = value;
		this.serving_unit = serving_unit;
		this.serving_value = serving_value;
		this.thermal_treatment = thermal_treatment;
		this.products = products;
	}

	public RecipeContent(RecipeContent recipe) {
		setName(recipe.getName());
		setProducts(recipe.getProducts());
		setServing_unit(recipe.getServing_unit());
		setServing_value(recipe.getServing_value());
		setThermal_treatment(recipe.getThermal_treatment());
		setUnit(recipe.getUnit());
		setValue(recipe.getValue());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Unit getServing_unit() {
		return serving_unit;
	}

	public void setServing_unit(Unit serving_unit) {
		this.serving_unit = serving_unit;
	}

	public Double getServing_value() {
		return serving_value;
	}

	public void setServing_value(Double serving_value) {
		this.serving_value = serving_value;
	}

	public ThermalTreatment getThermal_treatment() {
		return thermal_treatment;
	}

	public void setThermal_treatment(ThermalTreatment thermal_treatment) {
		this.thermal_treatment = thermal_treatment;
	}

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		for (Product product : this.products) {
			products.add(new Product(product));
		}
		return products;
	}

	public void setProducts(List<Product> products) {
		if (products != null) {
			this.products = new ArrayList<>();
			for (Product product : products) {
				this.products.add(new Product(product));
			}
		}
	}

	@Override
	public String toString() {
		return MessageFormat.format(TOSTRING, name, unit, value, serving_unit, serving_value, thermal_treatment,
				products);
	}

}
