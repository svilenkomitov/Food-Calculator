package com.sap.food.calculator.model;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Holds data for the authenticated user
 * 
 */
@XmlRootElement
@Entity
public class AuthenticatedUser implements Serializable {

	private static final long serialVersionUID = -1824505648564781837L;
	private static final String AUTHENTICATED_USER_TO_STRING = "User [fistName: {0}, lastName: {1}, email: {2}]";
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	/**
	 * Returns the first name of the authenticated user
	 * 
	 * @return the first name of the authenticated user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the authenticated user
	 * 
	 * @param firstName
	 *            the first name of the authenticated user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the authenticated user
	 * 
	 * @return the last name of the authenticated user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the authenticated user
	 * 
	 * @param lastName
	 *            the last name of the authenticated user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the email of the authenticated user
	 * 
	 * @return the email of the authenticated user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the authenticated user
	 * 
	 * @param email
	 *            the email of the authenticated user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {

		return MessageFormat.format(AUTHENTICATED_USER_TO_STRING, firstName, lastName, email);

	}

}
