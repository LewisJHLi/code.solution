package com.solution.service.file;

import com.solution.model.CsvOutput;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

	/**
	 * Execute the tests against the writeFile method.
	 */
	@Test
	void testMethod_writeFile_ensureOutputFileIsCreated() throws Exception {
		final String path = "./output/";
		final String file = "test_output.csv";

		CsvFileWriter csvFileWriter = new CsvFileWriter();

		List<CsvOutput> csvOutputs = new ArrayList<>();
		CsvOutput csvOutput1 = new CsvOutput("this is sku", "this is description", "this is source");
		CsvOutput csvOutput2 = new CsvOutput("new sku", "new description", "new source");
		csvOutputs.add(csvOutput1);
		csvOutputs.add(csvOutput2);

		csvFileWriter.writeFile(csvOutputs, file);

        BufferedReader csvReader = new BufferedReader(new FileReader(Paths.get(path + file).normalize().toAbsolutePath().toString()));
        Stream<String> lines = csvReader.lines();
        int count = (int) lines.count();
        csvReader.close();

        assertEquals(3, count);
	}

}