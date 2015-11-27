package com.food.calculator.model;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Nutrient
 *
 */
@Entity
@XmlRootElement
public class Nutrient implements Serializable {

	private static final String TOSTRING = "Nutrient: [name: {0}, unit: {1}]";

	private static final long serialVersionUID = 4347547238593040070L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Enumerated
	private Unit unit;

	public Nutrient(String name, Unit unit) {
		super();
		this.name = name;
		this.unit = unit;
	}

	public Nutrient() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return MessageFormat.format(TOSTRING, name, unit);
	}

}
