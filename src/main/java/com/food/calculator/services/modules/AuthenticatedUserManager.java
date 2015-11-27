package com.food.calculator.services.modules;

import java.security.Principal;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.calculator.exceptions.AuthenticatedUserException;
import com.food.calculator.model.AuthenticatedUser;
import com.sap.security.auth.login.LoginContextFactory;
import com.sap.security.um.service.UserManagementAccessor;
import com.sap.security.um.user.PersistenceException;
import com.sap.security.um.user.UnsupportedUserAttributeException;
import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;

public class AuthenticatedUserManager {

	private static final String ERROR_AUTHENTICATED_USER_NOT_FOUND_MESSAGE = "Operation failed: authenticated user not found.";
	private static final String ERROR_USER_ATTRIBUTE_IS_INVALID_MESSAGE = "User attribute is invalid.";
	private static final String EMAIL = "email";
	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static final String ERROR_LOGOUT_FAILED_MESSAGE = "Logout failed:";
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatedUserManager.class);

	/**
	 * Returns data for the authenticated user (first name, last name, email)
	 * 
	 * @param request
	 *            the request for the authenticated user
	 * @return AuthenticatedUser - data for the authenticated user
	 * @throws AuthenticatedUserException
	 */
	public static AuthenticatedUser getAuthenticatedUser(HttpServletRequest request) throws AuthenticatedUserException {

		Principal userPrincipal = request.getUserPrincipal();
		UserProvider userProvider;
		AuthenticatedUser authenticatedUser = new AuthenticatedUser();
		User user;

		try {

			userProvider = UserManagementAccessor.getUserProvider();
			user = userProvider.getUser(userPrincipal.getName());
			authenticatedUser.setFirstName(user.getAttribute(FIRSTNAME));
			authenticatedUser.setLastName(user.getAttribute(LASTNAME));
			authenticatedUser.setEmail(user.getAttribute(EMAIL));

		} catch (PersistenceException e) {

			LOGGER.error(ERROR_AUTHENTICATED_USER_NOT_FOUND_MESSAGE, e);
			throw new AuthenticatedUserException();

		} catch (UnsupportedUserAttributeException e) {

			LOGGER.error(ERROR_USER_ATTRIBUTE_IS_INVALID_MESSAGE, e);
			throw new AuthenticatedUserException();
		}

		return authenticatedUser;
	}

	/**
	 * Used to logout the authenticated user.
	 * 
	 * @param request
	 *            the request for the authenticated user
	 * @throws AuthenticatedUserException
	 */
	public static void logout(HttpServletRequest request) throws AuthenticatedUserException {

		if (request.getUserPrincipal() != null) {

			try {

				LoginContext loginContext = LoginContextFactory.createLoginContext();
				loginContext.logout();

			} catch (LoginException e) {

				LOGGER.error(ERROR_LOGOUT_FAILED_MESSAGE, e);
				throw new AuthenticatedUserException();
			}

		}
	}
}
