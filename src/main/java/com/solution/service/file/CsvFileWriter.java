package com.solution.service.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class CsvFileWriter {

	public static final String OUTPUT_PATH = "./output/result.csv";

	/**
	 * Default Constructor.
	 */
	public CsvFileWriter() {
	}

	public static CsvFileWriter builder(){
		return new CsvFileWriter();
	}

	public void writeFile() throws Exception {
		try(
				FileWriter csvWriter = new FileWriter(Paths.get(OUTPUT_PATH).normalize().toAbsolutePath().toString())
		) {
			csvWriter.append("SKU");
			csvWriter.append(",");
			csvWriter.append("Description");
			csvWriter.append(",");
			csvWriter.append("Source");
			csvWriter.append("\n");

			csvWriter.append("this SKU test");
			csvWriter.append(",");
			csvWriter.append("this Description test");
			csvWriter.append(",");
			csvWriter.append("this Source test");
			csvWriter.append("\n");

			csvWriter.flush();

			System.out.println("csv write complete!");
		} catch (IOException e) {
			throw new Exception("Csv output file cannot close. Error: " + e.getMessage());
		}
	}
}
