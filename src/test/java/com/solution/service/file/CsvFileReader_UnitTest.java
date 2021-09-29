package com.solution.service.file;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvFileReader_UnitTest {

	private final String correctPath = "./input/catalogA.csv";
	private final String incorrectPath = "./";

	/**
	 * Executes a test to ensure the {@link CsvFileReader#builder()} method never returns a {@code null} value.
	 */
	@Test
	void testMethod_builder_ensureNeverNull() {
		assertNotNull(CsvFileReader.builder());
	}

	@Test
	void testMethod_readFile_ensureFileReaderIsReading() throws Exception {
		CsvFileReader csvFileReader = new CsvFileReader();

		BufferedReader testReader = new BufferedReader(new FileReader(Paths.get(correctPath).normalize().toAbsolutePath().toString()));
		Stream<String> lines = testReader.lines();
		int count = (int) lines.count();
		testReader.close();

		assertEquals(count, csvFileReader.readFile(correctPath).size());
	}

	@Test
	void testMethod_readFile_ensureIncorrectPathHasException() {
		CsvFileReader csvFileReader = new CsvFileReader();

		Exception exception = assertThrows(FileNotFoundException.class, () -> csvFileReader.readFile(incorrectPath));

		String expected = "File was not found.";
		String actual = exception.getMessage();

		assertTrue(actual.contains(expected));
	}
}