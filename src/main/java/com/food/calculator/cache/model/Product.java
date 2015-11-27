package com.food.calculator.cache.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.food.calculator.model.Unit;

/**
 * Product used in the cache
 *
 */
@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = Product.PRODUCT_FIND_ALL, query = Product.SELECT_P_FROM_PRODUCT_P),
		@NamedQuery(name = Product.PRODUCT_FIND_BYNDBNO, query = Product.SELECT_P_FROM_PRODUCT_P_WHERE_P_NDBNO_NDBNO) })
public class Product implements Serializable {

	static final String SELECT_P_FROM_PRODUCT_P_WHERE_P_NDBNO_NDBNO = "SELECT p FROM Product p WHERE p.ndbno = :ndbno";
	static final String PRODUCT_FIND_BYNDBNO = "Product.findByndbno";
	static final String SELECT_P_FROM_PRODUCT_P = "SELECT p FROM Product p";
	static final String PRODUCT_FIND_ALL = "Product.findAll";
	private static final String TOSTRING = "Product [ndbno= {0}, name= {1}, value= {2}, unit= {3}, nutrients= {4}, date= {5}]";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long ndbno;
	private String name;
	@Enumerated
	private Unit unit;
	private Double value;

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<ProductNutrientTable> nutrients;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	private static final long serialVersionUID = -4105907513027636953L;

	public Product() {
		super();
	}

	public Product(Product product) {

		this.ndbno = product.ndbno;
		this.name = product.name;
		this.nutrients = product.nutrients;
		this.unit = product.unit;
		this.value = product.value;
	}

	public Long getNdbno() {
		return ndbno;
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

	public void setNdbno(Long ndbno) {
		this.ndbno = ndbno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductNutrientTable> getNutrients() {
		return nutrients;
	}

	public void setNutrients(List<ProductNutrientTable> nutrients) {
		this.nutrients = nutrients;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@PrePersist
	private void initDate() {
		date = new Date();
	}

	@Override
	public String toString() {
		return MessageFormat.format(TOSTRING, ndbno, name, value, unit, nutrients, date);
	}
}
