package com.solution.service.data;

import com.solution.model.CsvOutput;
import com.solution.service.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceGenerator_UnitTest {

	private ResourceGenerator resourceGenerator;

	private final String companyA = "_testA";
	private final String companyB = "_testB";
	private final String INPUT_DIR = "./input/";

	@BeforeEach
	void beforeEach() {
		this.resourceGenerator = new ResourceGenerator();
	}

	/**
	 * Test for valid inputs.
	 */
	@Test
	void testMethod_generate_validParams() throws Exception {
		Map<String, String> companies = new LinkedHashMap<>();
		companies.put("headquarter", companyA);
		companies.put("merged", companyB);

		this.getTestData();
		List<CsvOutput> outputs = this.resourceGenerator.generate(companies);
		System.out.println(outputs);
		assertEquals(CsvOutput.class, outputs.get(0).getClass());
		assertEquals(2, outputs.size());
	}

	/**
	 * Prepare files for test.
	 */
	private void getTestData() throws Exception {

		String[] catalogHeaders = {"SKU", "Description"};
		String[] barcodeHeaders = {"SupplierID", "SKU", "Barcode"};
		String[] supplierHeaders = {"ID", "Name"};

		// Test company A files
		List<String[]> catalogFileA = new ArrayList<>();
		catalogFileA.add(catalogHeaders);
		String[] catLine1A = {"aaa", "test description A"};
		catalogFileA.add(catLine1A);
		String[] catLine2A = {"bbb", "test description B"};
		catalogFileA.add(catLine2A);
		final String catalogTestA = INPUT_DIR + "catalog" + companyA + ".csv";

		List<String[]> barcodeFileA = new ArrayList<>();
		barcodeFileA.add(barcodeHeaders);
		String[] barLine1A = {"111", "aaa", "first"};
		barcodeFileA.add(barLine1A);
		String[] barLine2A = {"222", "bbb", "second"};
		barcodeFileA.add(barLine2A);
		String[] barLine3A = {"111", "aaa", "third"};
		barcodeFileA.add(barLine3A);
		final String barcodeTestA = INPUT_DIR + "barcodes" + companyA + ".csv";

		List<String[]> supplierFileA = new ArrayList<>();
		supplierFileA.add(supplierHeaders);
		String[] supLine1A = {"111", "sup A"};
		supplierFileA.add(supLine1A);
		String[] supLine2A = {"222", "sup B"};
		supplierFileA.add(supLine2A);
		final String supplierTestA = INPUT_DIR + "suppliers" + companyA + ".csv";

		// Test company B files
		List<String[]> catalogFileB = new ArrayList<>();
		catalogFileB.add(catalogHeaders);
		String[] catLine1B = {"aaa", "test description A"};
		catalogFileB.add(catLine1B);
		String[] catLine2B = {"bbb", "test description B"};
		catalogFileB.add(catLine2B);
		final String catalogTestB = INPUT_DIR + "catalog" + companyB + ".csv";

		List<String[]> barcodeFileB = new ArrayList<>();
		barcodeFileB.add(barcodeHeaders);
		String[] barLine1B = {"111", "aaa", "first"};
		barcodeFileB.add(barLine1B);
		String[] barLine2B = {"222", "bbb", "second last"};
		barcodeFileB.add(barLine2B);
		final String barcodeTestB = INPUT_DIR + "barcodes" + companyB + ".csv";

		List<String[]> supplierFileB = new ArrayList<>();
		supplierFileB.add(supplierHeaders);
		String[] supLine1B = {"111", "sup A"};
		supplierFileB.add(supLine1B);
		String[] supLine2B = {"333", "sup C"};
		supplierFileB.add(supLine2B);
		final String supplierTestB = INPUT_DIR + "suppliers" + companyB + ".csv";

		// Create testing files
		Utils.createFile(catalogTestA, catalogFileA);
		Utils.createFile(barcodeTestA, barcodeFileA);
		Utils.createFile(supplierTestA, supplierFileA);

		Utils.createFile(catalogTestB, catalogFileB);
		Utils.createFile(barcodeTestB, barcodeFileB);
		Utils.createFile(supplierTestB, supplierFileB);
	}
}