package com.solution.model;

import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyResource_UnitTest {

	private final String company = "companyName";

	@Test
	void testMethod_getCompany() {
		final List<Record> records = new ArrayList<>();
		CompanyResource resource = new CompanyResource(company, records);

		assertEquals(company, resource.getCompany());
	}

	@Test
	void testMethod_getRecords() {
		final List<Record> records = new ArrayList<>();
		final Catalog catalog = new Catalog("test_sku", "test_description");
		final Supplier supplier = new Supplier("test_supplierId", "test_name");
		records.add(new Record(catalog, "new barcode", supplier));

		CompanyResource resource = new CompanyResource(company, records);

		assertEquals(records.size(), resource.getRecords().size());
	}
}