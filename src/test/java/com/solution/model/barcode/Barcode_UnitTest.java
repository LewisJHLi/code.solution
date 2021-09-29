package com.solution.model.barcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Barcode_UnitTest {
	private final String supplierID = "test_id";
	private final String sku = "test_sku";
	private final String barcode_code = "test_barcode";

	Barcode barcode = new Barcode(supplierID, sku, barcode_code);

	@Test
	void testMethod_getSupplierId() {
		assertEquals(supplierID, barcode.getSupplierId());
	}

	@Test
	void testMethod_getSku() {
		assertEquals(sku, barcode.getSku());
	}

	@Test
	void testMethod_getBarcode() {
		assertEquals(barcode_code, barcode.getBarcode());
	}
}