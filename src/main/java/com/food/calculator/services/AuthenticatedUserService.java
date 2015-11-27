package com.sap.food.calculator.services;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sap.food.calculator.exceptions.AuthenticatedUserException;
import com.sap.food.calculator.model.AuthenticatedUser;
import com.sap.food.calculator.services.modules.AuthenticatedUserManager;

@Path("/user")
public class AuthenticatedUserService {

	private static final String RESPONSE_LOGGING_OUT_WAS_UNSUCCESSFUL_MESSAGE = "Logging out was unsuccessful";
	private static final String RESPONSE_USER_ATTRIBUTE_NOT_FOUND_MESSAGE = "User attribute: firstname, lastname or email not found.";
	private static final String RESPONSE_SUCCESSFULLY_LOGGED_OUT_MESSAGE = "You have successfully logged out";

	@Context
	private HttpServletRequest request;

	@GET
	@Path("/getAttributes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttributes() {

		AuthenticatedUser user;
		Response response;

		try {

			user = AuthenticatedUserManager.getAuthenticatedUser(request);
			response = Response.status(Status.OK).entity(user).build();

		} catch (AuthenticatedUserException e) {

			response = Response.status(Status.NOT_FOUND).entity(RESPONSE_USER_ATTRIBUTE_NOT_FOUND_MESSAGE).build();
		}

		return response;
	}

	@GET
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {

		Response response;

		try {

			AuthenticatedUserManager.logout(request);
			response = Response.status(Status.OK).entity(RESPONSE_SUCCESSFULLY_LOGGED_OUT_MESSAGE).build();

		} catch (AuthenticatedUserException e) {

			response = Response.status(Status.UNAUTHORIZED).entity(RESPONSE_LOGGING_OUT_WAS_UNSUCCESSFUL_MESSAGE)
					.build();
		}

		return response;
	}
}
