package com.solution.service.data;

import com.solution.model.CompanyResource;
import com.solution.service.file.CsvFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataCleaner_UnitTest {

	private CsvFileReader csvFileReader;
	private DataCleaner dataCleaner;

	private final String INPUT_DIR = "./input/";

	@BeforeEach
	void beforeEach() {
		this.dataCleaner = new DataCleaner(this.csvFileReader);
	}

	@Test
	void testMethod_getCleanData_validPath() throws Exception {
		final String company = "A";
		List<String> files = Arrays.asList(INPUT_DIR + "catalogA.csv", INPUT_DIR + "barcodesA.csv", INPUT_DIR + "suppliersA.csv");

		assertEquals(CompanyResource.class, this.dataCleaner.getCleanData(company, files).getClass());
	}

	@Test
	void testMethod_getCleanData_invalidFilePath() {
		final String company = "A";
		List<String> files = Arrays.asList("cat1", "bar1", "sup1");

		Exception exception = assertThrows(FileNotFoundException.class, () -> this.dataCleaner.getCleanData(company, files));
		String expectedMsg = "File was not found.";
		String actualMsg = exception.getMessage();

		assertTrue(actualMsg.contains(expectedMsg));
	}

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

	@Test
	void testMethod_getCleanData_invalidCompanyName() {
		final String company = "test_A";
		List<String> files = Arrays.asList(INPUT_DIR + "catalogA.csv", INPUT_DIR + "barcodesA.csv", INPUT_DIR + "suppliersA.csv");

		Exception exception = assertThrows(FileNotFoundException.class, () -> this.dataCleaner.getCleanData(company, files));
		String expectedMsg = "File was not found.";
		String actualMsg = exception.getMessage();

		assertTrue(actualMsg.contains(expectedMsg));
	}
}