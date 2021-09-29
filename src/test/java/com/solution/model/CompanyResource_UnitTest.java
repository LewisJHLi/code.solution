package com.solution.model;

import com.solution.model.catalog.Catalog;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyResource_UnitTest {

	private final String company = "companyName";

	@Test
	void testMethod_getCompany() {
		final List<Catalog> catalogs = new ArrayList<>();
		CompanyResource resource = new CompanyResource(company, catalogs);

		assertEquals(company, resource.getCompany());
	}

	@Test
	void testMethod_getCatalogs() {
		final List<Catalog> catalogs = new ArrayList<>();
		final Catalog catalogA = new Catalog("test_sku_A", "test_description_A");
		final Catalog catalogB = new Catalog("test_sku_B", "test_description_B");
		catalogs.add(catalogA);
		catalogs.add(catalogB);

		CompanyResource resource = new CompanyResource(company, catalogs);

		assertEquals(catalogs.size(), resource.getCatalogs().size());
	}
}