package com.sap.food.calculator.services.modules;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.NoResultException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sap.food.calculator.cache.model.Product;
import com.sap.food.calculator.cache.model.ProductNutrientTable;
import com.sap.food.calculator.dao.ProductDAO;
import com.sap.food.calculator.ndb.model.RecipeContent;
import com.sap.food.calculator.utils.HTTPUtils;
import com.sap.food.calculator.utils.NutrientDeserializer;
import com.sap.food.calculator.utils.NutrientsEnumAPI;

public class RecipeNutrientData {

	private static final String DATE_IS_NULL = "Date is null!";
	private static final String DATE_NOT_AVAILABLE = "Date not available";
	private static final String FOODS = "foods";
	private static final String REPORT = "report";
	private static final String REPORT_DESTINATION = "report-destination";
	private static final String NUTRIENTS = "nutrients";
	private static final String NDBNO = "ndbno";
	private static final String OPERATION_FAILED_DATA_CAN_NOT_BE_EXTRACTED_FROM_THE_API_MESSAGE = "Operation failed: data can not be extracted from the API";
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeNutrientData.class);
	private static final long VALIDATE_DAYS = 30;
	private ProductDAO productDAO = new ProductDAO();

	public RecipeNutrientData() {

		super();
	}

	private List<ProductNutrientTable> getNutrientData(Long ndbno) {

		List<ProductNutrientTable> nutrientReport = new ArrayList<ProductNutrientTable>();
		try {

			nutrientReport = getProductNutrientReport(ndbno);

		} catch (IOException e) {

			LOGGER.error(OPERATION_FAILED_DATA_CAN_NOT_BE_EXTRACTED_FROM_THE_API_MESSAGE, e);
		}

		return nutrientReport;
	}

	private List<ProductNutrientTable> getProductNutrientReport(Long productNDBNumber) throws IOException {
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair(NDBNO, productNDBNumber.toString()));
		for (NutrientsEnumAPI nutrients : NutrientsEnumAPI.values()) {

			parameters.add(new BasicNameValuePair(NUTRIENTS, Integer.toString(nutrients.getNutrientID())));
		}
		URL url = HTTPUtils.createURL(HTTPUtils.getDestinationProperties(REPORT_DESTINATION), parameters);
		List<ProductNutrientTable> nutrients;
		try {
			nutrients = new GsonBuilder().registerTypeAdapter(ProductNutrientTable.class, new NutrientDeserializer())
					.create().fromJson(
							new JsonParser().parse(HTTPUtils.getResponse(url)).getAsJsonObject().getAsJsonObject(REPORT)
									.getAsJsonArray(FOODS).get(0).getAsJsonObject().getAsJsonArray(NUTRIENTS),
							new TypeToken<List<ProductNutrientTable>>() {
							}.getType());
		} catch (Exception e) {

			throw new NoResultException();
		}

		return nutrients;
	}

	/**
	 * @param rec
	 *            - contains all products for recipe
	 * @return nutriens's values for each product
	 * @throws ParseException
	 */
	public RecipeContent createRecipeNutrientReport(RecipeContent rec) throws ParseException {

		RecipeContent recipe = new RecipeContent();

		recipe.setName(rec.getName());
		recipe.setServing_unit(rec.getServing_unit());
		recipe.setServing_value(rec.getServing_value());
		recipe.setThermal_treatment(rec.getThermal_treatment());
		recipe.setUnit(rec.getUnit());
		recipe.setValue(rec.getValue());

		List<Product> products = rec.getProducts();
		List<Product> productsInfo = new ArrayList<>();

		for (int i = 0; i < products.size(); i++) {
			Product productObj = products.get(i);

			Long ndbno = productObj.getNdbno();
			Product currentProduct = productDAO.findById(ndbno);

			List<ProductNutrientTable> nutrientData = new ArrayList<>();

			if (currentProduct != null) {

				Date productDate = currentProduct.getDate();
				boolean isValidDate = isValidDate(productDate);

				if (!isValidDate) {

					productDAO.remove(currentProduct);
					nutrientData = getNutrientData(ndbno);
					productObj.setNutrients(nutrientData);
					productDAO.add(productObj);

				} else {

					productObj.setNutrients(currentProduct.getNutrients());
				}

			} else {

				nutrientData = getNutrientData(ndbno);
				productObj.setNutrients(nutrientData);
				productDAO.add(productObj);
			}

			productsInfo.add(productObj);

		}

		recipe.setProducts(productsInfo);

		return recipe;
	}

	private boolean isValidDate(Date productDate) throws ParseException {

		if (productDate != null) {
			try {

				Long currentMS = System.currentTimeMillis();
				Long productMS = productDate.getTime();

				return currentMS - productMS < TimeUnit.DAYS.toMillis(VALIDATE_DAYS);

			} catch (Exception e) {
				LOGGER.error(DATE_NOT_AVAILABLE, e);
				throw new ParseException(DATE_NOT_AVAILABLE, 1);
			}

		}
		LOGGER.error(DATE_IS_NULL);
		return false;
	}

}
