package com.sap.food.calculator.services.modules;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sap.food.calculator.ndb.model.Product;
import com.sap.food.calculator.utils.HTTPUtils;

/**
 * Extract data for products from National Nutrition Database API.
 */
public class ProductManager {

	private static final String DEBUG_API_RETURNS_JSON_WITH_UNEXPECTED_PROPERTIES_MESSAGE = "API returns JSON with unexpected properties. Actual JSON Model: {} Expected JSON Model: { list: {item:[]}}";
	private static final String DEBUG_PRODUCTS_NOT_FOUND_MESSAGE = "Products with name: \"{}\" not found.";
	private static final String DEBUG_PRODUCTS_EXTRACTED_SUCCESSFULLY_MESSAGE = "{} products with name: \"{}\" extracted successfully.";
	private static final String ITEM = "item";
	private static final String LIST = "list";
	private static final String SEARCH_DESTINATION_NAME = "search-destination";
	private static final String QUERY = "q";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductManager.class);

	/**
	 * 
	 * @param name
	 *            the name of the searched product
	 * @return list of all products found by name in the National Nutrition
	 *         Database, if products with such name are not found return empty
	 *         list
	 */
	public static List<Product> findByName(String name) {

		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(QUERY, name));
		URL url = HTTPUtils.createURL(HTTPUtils.getDestinationProperties(SEARCH_DESTINATION_NAME), parameters);

		List<Product> products = new ArrayList<>();
		String json = null;
		try {
			json = HTTPUtils.getResponse(url);
			products = new Gson().fromJson(
					new JsonParser().parse(json).getAsJsonObject().getAsJsonObject(LIST).getAsJsonArray(ITEM),
					new TypeToken<List<Product>>() {
					}.getType());

		} catch (IOException e) {

			LOGGER.debug(DEBUG_PRODUCTS_NOT_FOUND_MESSAGE, name, e);

		} catch (JsonSyntaxException e) {

			LOGGER.debug(DEBUG_API_RETURNS_JSON_WITH_UNEXPECTED_PROPERTIES_MESSAGE, json);
		}

		LOGGER.debug(DEBUG_PRODUCTS_EXTRACTED_SUCCESSFULLY_MESSAGE, products.size(), name);

		return products;
	}
}
