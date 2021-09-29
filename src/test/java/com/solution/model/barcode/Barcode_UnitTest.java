package com.solution.model.barcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Barcode_UnitTest {
	private final String supplierID = "test_id";
	private final String sku = "test_sku";
	private final String barcode = "test_barcode";

	Barcode oBarcode = new Barcode(supplierID, sku, barcode);

	@Test
	void testMethod_getSupplierId() {
		assertEquals(supplierID, oBarcode.getSupplierId());
	}

	@Test
	void testMethod_getSku() {
		assertEquals(sku, oBarcode.getSku());
	}

	@Test
	void testMethod_getBarcode() {
		assertEquals(barcode,oBarcode.getBarcode());
	}
}