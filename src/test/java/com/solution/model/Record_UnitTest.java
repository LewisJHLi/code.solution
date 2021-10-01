package com.solution.model;

import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Record_UnitTest {
	private final Catalog catalog = new Catalog("test_sku", "test_description");
	private final String barcode = "test_barcode";
	private final Supplier supplier = new Supplier("test_supplierId", "test_name");

	Record record = new Record(catalog, barcode, supplier);

	@Test
	void testMethod_getCatalog() {
		Assertions.assertEquals(catalog, record.getCatalog());
		Assertions.assertEquals(catalog.getSku(), record.getCatalog().getSku());
		Assertions.assertEquals(catalog.getDescription(), record.getCatalog().getDescription());
	}

	@Test
	void testMethod_getBarcode() {
		Assertions.assertEquals(barcode, record.getBarcode());
	}

	@Test
	void testMethod_getSupplier() {
		Assertions.assertEquals(supplier, record.getSupplier());
		Assertions.assertEquals(supplier.getId(), record.getSupplier().getId());
		Assertions.assertEquals(supplier.getName(), record.getSupplier().getName());
	}
}