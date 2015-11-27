package com.food.calculator.ndb.model;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

/**
 * Holds data for products contained in a recipe
 *
 */
@XmlRootElement
public class Product implements Serializable {

	private static final String PRODUCT_TO_STRING = "Product [name: {0}, id: {1}]";

	private static final long serialVersionUID = -6516306189536883725L;

	private String name;

	@SerializedName("ndbno")
	private String id;

	public Product(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	/**
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the product
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id of the product from the used API
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id of the product from the used API
	 * 
	 * @param id
	 *            the id of the product
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {

		return MessageFormat.format(PRODUCT_TO_STRING, name, id);
	}
}
