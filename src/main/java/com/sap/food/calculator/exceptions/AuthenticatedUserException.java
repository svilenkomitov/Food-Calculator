package com.sap.food.calculator.exceptions;

/**
 * Thrown when fails to get user credentials.
 *
 */
public class AuthenticatedUserException extends Exception {

	private static final long serialVersionUID = -7944564837124268088L;

	public AuthenticatedUserException(String message) {

		super(message);
	}

	public AuthenticatedUserException() {

		super();
	}

}
