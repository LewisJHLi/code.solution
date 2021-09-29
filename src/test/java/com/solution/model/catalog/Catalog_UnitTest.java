package com.solution.model.catalog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Catalog_UnitTest {

	private final String sku = "sku_test";
	private final String description = "description_test";

	Catalog oCatalog = new Catalog(sku, description);

	@Test
	void testMethod_getSku() {
		assertEquals(sku, oCatalog.getSku());
	}

	@Test
	void testMethod_getDescription() {
		assertEquals(description, oCatalog.getDescription());
	}
}