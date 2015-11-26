package com.sap.food.calculator.services.modules;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialize entity manager
 */
@Singleton
public class EntityManagerProvider {

	private static final String APPLICATION_NAME = "com.sap.food.calculator";
	private static final String ERROR_DATA_SOURCE_INITIALIZATION_FAILED_MESSAGE = "Data source initialization failed.";
	private static final String DATA_SOURCE = "java:comp/env/jdbc/DefaultDB";
	private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerProvider.class);
	private static EntityManagerFactory factory;

	/**
	 * Returns EntityManager instance and initialized it if its not.
	 * 
	 * @return EntityManager
	 */
	public EntityManager getEntityManager() {

		if (factory == null) {

			init();
		}

		return factory.createEntityManager();
	}

	private void init() {

		DataSource ds;
		try {

			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DATA_SOURCE);

			Map<String, DataSource> properties = new HashMap<>();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			setFactory(Persistence.createEntityManagerFactory(APPLICATION_NAME, properties));

		} catch (NamingException e) {

			LOGGER.error(ERROR_DATA_SOURCE_INITIALIZATION_FAILED_MESSAGE, e);
			throw new RuntimeException(ERROR_DATA_SOURCE_INITIALIZATION_FAILED_MESSAGE);
		}
	}

	private static void setFactory(EntityManagerFactory factory) {

		EntityManagerProvider.factory = factory;
	}
}
