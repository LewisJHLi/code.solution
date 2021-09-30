package com.solution.service.data;

import com.solution.model.CompanyResource;
import com.solution.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataCleaner_UnitTest {

	private DataCleaner dataCleaner;

	private final String INPUT_DIR = "./input/";

	@BeforeEach
	void beforeEach() {
		this.dataCleaner = new DataCleaner();
	}

	/**
	 * Test for valid file path and company name.
	 */
	@Test
	void testMethod_getCleanData_validPath() throws Exception {
		final String company = "A";
		List<String> files = Arrays.asList(INPUT_DIR + "catalogA.csv", INPUT_DIR + "barcodesA.csv", INPUT_DIR + "suppliersA.csv");

		assertEquals(CompanyResource.class, this.dataCleaner.getCleanData(company, files).getClass());
	}

	/**
	 * Test for invalid file path. Should throw exceptions.
	 */
	@Test
	void testMethod_getCleanData_invalidFilePath() {
		final String company = "A";
		List<String> files = Arrays.asList("cat1", "bar1", "sup1");

		Exception exception = assertThrows(FileNotFoundException.class, () -> this.dataCleaner.getCleanData(company, files));
		String expectedMsg = "File was not found.";
		String actualMsg = exception.getMessage();

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * Test for invalid file names. Should throw exceptions.
	 */
	@Test
	void testMethod_getCleanData_invalidFileName() {
		final String company = "A";
		List<String> files = Collections.singletonList(INPUT_DIR + "catA.csv");

		Exception exception = assertThrows(FileNotFoundException.class, () -> this.dataCleaner.getCleanData(company, files));
		String expectedMsg = "File was not found.";
		String actualMsg = exception.getMessage();
		System.out.println(actualMsg);
		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * Test for invalid company name. Should throw exceptions.
	 */
	@Test
	void testMethod_getCleanData_invalidCompanyName() {
		final String company = "test_A";
		List<String> files = Arrays.asList(INPUT_DIR + "catalogA.csv", INPUT_DIR + "barcodesA.csv", INPUT_DIR + "suppliersA.csv");

		Exception exception = assertThrows(FileNotFoundException.class, () -> this.dataCleaner.getCleanData(company, files));
		String expectedMsg = "Cannot find file";
		String actualMsg = exception.getMessage();

		assertTrue(actualMsg.contains(expectedMsg));
	}

	/**
	 * Test for valid parameters.
	 */
	@Test
	void testMethod_getCleanData_validParams() throws Exception {
		final String company = "_test";

		List<String[]> catalogFile = new ArrayList<>();
		String[] catalogHeaders = {"SKU", "Description"};
		catalogFile.add(catalogHeaders);
		String[] catLine1 = {"aaa", "test description A"};
		catalogFile.add(catLine1);
		String[] catLine2 = {"bbb", "test description B"};
		catalogFile.add(catLine2);
		final String catalogTestCsv = INPUT_DIR + "catalog" + company + ".csv";

		List<String[]> barcodeFile = new ArrayList<>();
		String[] barcodeHeaders = {"SupplierID", "SKU", "Barcode"};
		barcodeFile.add(barcodeHeaders);
		String[] barLine1 = {"111", "aaa", "first"};
		barcodeFile.add(barLine1);
		String[] barLine2 = {"222", "bbb", "second"};
		barcodeFile.add(barLine2);
		String[] barLine3 = {"111", "aaa", "third"};
		barcodeFile.add(barLine3);
		final String barcodeTestCsv = INPUT_DIR + "barcodes" + company + ".csv";

		List<String[]> supplierFile = new ArrayList<>();
		String[] supplierHeaders = {"ID", "Name"};
		supplierFile.add(supplierHeaders);
		String[] supLine1 = {"111", "sup A"};
		supplierFile.add(supLine1);
		String[] supLine2 = {"222", "sup B"};
		supplierFile.add(supLine2);
		final String supplierTestCsv = INPUT_DIR + "suppliers" + company + ".csv";

		this.createFile(catalogTestCsv, catalogFile);
		this.createFile(barcodeTestCsv, barcodeFile);
		this.createFile(supplierTestCsv, supplierFile);

		List<String> files = Arrays.asList(catalogTestCsv, barcodeTestCsv, supplierTestCsv);
		CompanyResource companyResource = this.dataCleaner.getCleanData(company, files);

		assertEquals(company, companyResource.getCompany());

		List<Record> records = companyResource.getRecords()
				.stream()
				.filter(rec -> rec.getCatalog().getSku().equalsIgnoreCase("aaa"))
				.collect(Collectors.toList());
		System.out.println(records);

		assertEquals(2, records.size());
		assertEquals("sup A", records.get(0).getSupplier().getName());
		assertEquals("sup A", records.get(1).getSupplier().getName());
	}

	/**
	 * Create csv test files.
	 * @param filename
	 *      File name for csv.
	 * @param files
	 *      File contents.
	 */
	private void createFile(String filename, List<String[]> files) throws Exception {
		try(
				FileWriter csvWriter = new FileWriter(Paths.get(filename).normalize().toAbsolutePath().toString())
		) {

			for(String[] line : files) {
				String data = Arrays.toString(line)
						.replace("[", "")
						.replace("]", "")
						.replace(", ", ",");
				csvWriter.append(data);
				csvWriter.append("\n");
			}

			csvWriter.flush();
		} catch (IOException e) {
			throw new Exception("Unable to create test csv file " + filename + ". Error: " + e.getMessage());
		}
	}
}