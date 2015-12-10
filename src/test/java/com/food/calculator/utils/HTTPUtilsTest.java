package com.food.calculator.utils;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.mockito.Mockito;

import com.sap.core.connectivity.api.configuration.DestinationConfiguration;

public class HTTPUtilsTest {

	@Test
	public void testCreateURL() throws MalformedURLException {

		Map<String, String> properties = new HashMap<>();
		properties.put("api_key", "DEMO_KEY");
		properties.put("format", "json");
		properties.put("scheme", "http");
		properties.put("path", "/ndb/search/");
		properties.put("URL", "http://api.nal.usda.gov");
		DestinationConfiguration destConf = Mockito.mock(DestinationConfiguration.class);
		Mockito.when(destConf.getAllProperties()).thenReturn(properties);
		List<NameValuePair> parameters = new ArrayList<>();
		parameters.add(new BasicNameValuePair("q", "tomatoes"));
		URL expectedURL = new URL(
				"http://api.nal.usda.gov/ndb/search/?q=tomatoes&api_key=DEMO_KEY&format=json");
		URL actualURL = HTTPUtils.createURL(destConf.getAllProperties(), parameters);
		assertEquals(expectedURL, actualURL);
	}

}