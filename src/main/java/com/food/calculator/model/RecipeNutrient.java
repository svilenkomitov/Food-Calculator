package com.food.calculator.model;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: RecipeNutrient
 *
 */
@Entity
@XmlRootElement
public class RecipeNutrient implements Serializable {

	private static final String TOSTRING = "RecipeNutrient: [nutrient: {0}, value: {1}]";

	private static final long serialVersionUID = -5250945798131954293L;

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Nutrient nutrient; 
	private Double value;

	public RecipeNutrient() {
		super();
	}

	public RecipeNutrient(Nutrient nutrient, Double value) {
		super();
		this.nutrient = nutrient;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return MessageFormat.format(TOSTRING, nutrient, value);
	}

}
