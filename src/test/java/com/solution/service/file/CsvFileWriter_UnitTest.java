package com.solution.service.file;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvFileWriter_UnitTest {
	/**
	 * Executes a test to ensure the {@link CsvFileWriter#builder()} method never returns a {@code null} value.
	 */
	@Test
	void testMethod_builder_ensureNeverNull() {
		assertNotNull(CsvFileWriter.builder());
	}

	@Test
	void testMethod_writeFile() throws Exception {
		final String OUTPUT_PATH = "./output/result.csv";

		CsvFileWriter csvFileWriter = new CsvFileWriter();
		csvFileWriter.writeFile();

        BufferedReader csvReader = new BufferedReader(new FileReader(Paths.get(OUTPUT_PATH).normalize().toAbsolutePath().toString()));
        Stream<String> lines = csvReader.lines();
        int count = (int) lines.count();
        csvReader.close();

        assertEquals(2, count);
	}

}