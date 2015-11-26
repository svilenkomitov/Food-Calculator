package com.sap.food.calculator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import com.sap.core.connectivity.api.configuration.ConnectivityConfiguration;
import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

public class HTTPUtils {

	private static final String ERROR_DESTINATION_PROPERTIES_OR_URL_PARAMETERS_ARE_INVALID_MESSAGE = "Operation failed: destination properties: {} or URL parameters: {} are invalid.";
	private static final String REGEX = "//";
	private static final String PATH = "path";
	private static final String URL = "URL";
	private static final String SCHEME = "scheme";
	private static final String FORMAT = "format";
	private static final String API_KEY = "api_key";
	private static final String GET = "GET";
	private static final String ERROR_UNABLE_TO_CONNECT_TO_CONNECTIVITY_SERVICE_MESSAGE = "Operation failed: Unable to connect to connectivity service with destination name: \"{}\"";
	private static final String CONNECTIVITY_CONFIGURATION = "java:comp/env/connectivityConfiguration";
	private static final Logger LOGGER = LoggerFactory.getLogger(HTTPUtils.class);

	/**
	 * Returns the content of the response body for the given url
	 * 
	 * @param url
	 * @return the content of the response body
	 * @throws IOException
	 */
	public static String getResponse(URL url) throws IOException {

		StringBuffer response = new StringBuffer();
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(GET);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				response.append(sCurrentLine);
			}
		}
		return response.toString();
	}

	/**
	 * Returns the url build from the given properties and parameters
	 * 
	 * @param properties
	 *            the properties for the url to be created
	 * @param parameters
	 *            the parameters for the url
	 * @return URL build with the given properties and parameters
	 */
	public static URL createURL(Map<String, String> properties, List<NameValuePair> parameters) {

		URL url = null;
		parameters.add(new BasicNameValuePair(API_KEY, properties.get(API_KEY)));
		parameters.add(new BasicNameValuePair(FORMAT, properties.get(FORMAT)));
		URIBuilder builder = new URIBuilder().setScheme(properties.get(SCHEME))
				.setHost(properties.get(URL).split(REGEX)[1]).setPath(properties.get(PATH));
		for (NameValuePair parameter : parameters) {
			builder.addParameter(parameter.getName(), parameter.getValue());
		}

		try {

			url = builder.build().toURL();

		} catch (MalformedURLException | URISyntaxException e) {

			LOGGER.error(ERROR_DESTINATION_PROPERTIES_OR_URL_PARAMETERS_ARE_INVALID_MESSAGE, properties, parameters);
		}

		return url;
	}

	/**
	 * Connects to the destination by name and returns its configuration
	 * properties
	 * 
	 * @param destinationName
	 *            the destination name
	 * @return map of all properties for the given destination
	 */
	public static Map<String, String> getDestinationProperties(String destinationName) {

		DestinationConfiguration destinationConfiguration = null;

		try {

			Context context = new InitialContext();
			ConnectivityConfiguration configuration = (ConnectivityConfiguration) context
					.lookup(CONNECTIVITY_CONFIGURATION);
			destinationConfiguration = configuration.getConfiguration(destinationName);

		} catch (NamingException e) {

			String message = MessageFormatter
					.format(ERROR_UNABLE_TO_CONNECT_TO_CONNECTIVITY_SERVICE_MESSAGE, destinationName).getMessage();
			LOGGER.error(message, e);
		}

		return destinationConfiguration.getAllProperties();

	}
}
