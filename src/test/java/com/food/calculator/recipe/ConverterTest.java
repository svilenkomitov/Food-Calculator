package com.sap.food.calculator.recipe;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sap.food.calculator.model.Unit;


public class ConverterTest {
	
	@Test 
	public void testMililiter() {
		Double a = Converter.convertToGram(Unit.MILILITER.getUnit(), 12.34);
		Double b = 12.34;
		assertEquals(a,b);
	}
	
	@Test 
	public void testTeaspoon() {
		Double a = Converter.convertToGram(Unit.TEASPOON.getUnit(), 12.34);
		Double b = 61.7;
		assertEquals(a,b);
	}
	
	@Test 
	public void testTablespoon() {
		Double a = Converter.convertToGram(Unit.TABLESPOON.getUnit(), 12.34);
		Double b = 185.1;
		assertEquals(a,b);
	}
	
	@Test 
	public void testCup() {
		Double a = Converter.convertToGram(Unit.CUP.getUnit(), 12.34);
		Double b = 2924.58;
		assertEquals(a,b);
	}
	
	@Test 
	public void testLiter() {
		Double a = Converter.convertToGram(Unit.LITER.getUnit(), 12.34);
		Double b = 12340.0;
		assertEquals(a,b);
	}
	
	@Test 
	public void testGram() {
		Double a = Converter.convertToGram(Unit.GRAM.getUnit(), 12.34);
		Double b = 12.34;
		assertEquals(a,b);
	}
	
	@Test 
	public void testOunce() {
		Double a = Converter.convertToGram(Unit.OUNCE.getUnit(), 12.34);
		Double b = 345.52;
		assertEquals(a,b);
	}
	
	@Test 
	public void testKilogram() {
		Double a = Converter.convertToGram(Unit.KILOGRAM.getUnit(), 12.34);
		Double b = 12340.0;
		assertEquals(a,b);
	}
	
	@Test 
	public void testPound() {
		Double a = Converter.convertToGram(Unit.POUND.getUnit(), 12.34);
		Double b = 5602.36;
		assertEquals(a,b);
	}
	
	@Test 
	public void testEtto() {
		Double a = Converter.convertToGram(Unit.ETTO.getUnit(), 12.34);
		Double b = 1234.0;
		assertEquals(a,b);
	}
	
	@Test 
	public void testKilocalories() {
		Double a = Converter.convertToGram(Unit.CALORIES.getUnit(), 12.34);
		Double b = 0.01234;
		assertEquals(a,b);
	}
	
	@Test 
	public void testMiligram() {
		Double a = Converter.convertToGram(Unit.MILIGRAM.getUnit(), 12.34);
		Double b = 0.01234;
		assertEquals(a,b);
	}

}
