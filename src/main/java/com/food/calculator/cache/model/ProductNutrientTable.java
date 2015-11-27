package com.food.calculator.cache.model;

import java.text.MessageFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.food.calculator.model.Nutrient;

/**
 * Table for connection between nutrient and product
 *
 */
@Entity
@XmlRootElement
public class ProductNutrientTable {

	private static final String TOSTRING = "ProductNutrientTable: [gm: {0}, nutrient: {1}, value: {2}]";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double gm;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Nutrient nutrient;
	private Double value;

	public ProductNutrientTable() {
		super();
	}

	public ProductNutrientTable(Nutrient nutrient, Double value, Double gm) {
		setGm(gm);
		setNutrient(nutrient);
		setValue(value);
	}

	public Double getGm() {
		return gm;
	}

	public void setGm(Double gm) {
		this.gm = gm;
	}

	public Nutrient getNutrient() {
		return nutrient;
	}

	public void setNutrient(Nutrient nutrient) {
		this.nutrient = nutrient;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return MessageFormat.format(TOSTRING, gm, nutrient, value);
	}

}
