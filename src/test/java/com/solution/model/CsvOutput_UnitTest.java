package com.solution.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvOutput_UnitTest {

	private final String sku = "test_sku";
	private final String description = "test_description";
	private final String source = "test_source";

	CsvOutput oCsvOutput = new CsvOutput(sku, description, source);

	@Test
	void testMethod_getSku() {
		assertEquals(sku, oCsvOutput.getSku());
	}

	@Test
	void testMethod_getDescription() {
		assertEquals(description, oCsvOutput.getDescription());
	}

	@Test
	void testMethod_getSource() {
		assertEquals(source, oCsvOutput.getSource());
	}

}