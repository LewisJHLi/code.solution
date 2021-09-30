package com.solution.model;

import com.solution.model.barcode.Barcode;
import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyResource_UnitTest {

	private final String company = "companyName";

	@Test
	void testMethod_getCompany() {
		final List<Catalog> catalogs = new ArrayList<>();
		final List<Record> records = new ArrayList<>();

		CompanyResource resource = new CompanyResource(company, catalogs, records);

		assertEquals(company, resource.getCompany());
	}

	@Test
	void testMethod_getCatalogs() {
		final List<Catalog> catalogs = new ArrayList<>();
		final List<Record> records = new ArrayList<>();
		catalogs.add(new Catalog("test_sku_A", "test_description_A"));
		catalogs.add(new Catalog("test_sku_B", "test_description_B"));

		CompanyResource resource = new CompanyResource(company, catalogs, records);

		assertEquals(catalogs.size(), resource.getCatalogs().size());
	}

	@Test
	void testMethod_getRecords() {
		final List<Catalog> catalogs = new ArrayList<>();
		final List<Record> records = new ArrayList<>();
		final Catalog catalog = new Catalog("test_sku", "test_description");
		final Barcode barcode = new Barcode("test_supplierId", "test_sku", "test_barcode");
		final Supplier supplier = new Supplier("test_supplierId", "test_name");
		records.add(new Record(catalog, barcode, supplier));

		CompanyResource resource = new CompanyResource(company, catalogs, records);

		assertEquals(records.size(), resource.getRecords().size());
	}
}