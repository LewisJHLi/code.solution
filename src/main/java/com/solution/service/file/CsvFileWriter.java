package com.solution.service.file;

import com.solution.model.CsvOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileWriter {

	public static final String OUTPUT_PATH = "./output/";

	/**
	 * Default Constructor.
	 */
	public CsvFileWriter() {
	}

	/**
	 * Returns a new builder that is capable of producing a {@link CsvFileWriter} instance.
	 *
	 * @return CsvFileWriter Builder class.
	 */
	public static CsvFileWriter builder(){
		return new CsvFileWriter();
	}

	/**
	 * Create the csv file output file.
	 *
	 * @param csvOutputs
	 *      Output data.
	 * @param fileName
	 *      Filename for output csv.
	 */
	public void writeFile(List<CsvOutput> csvOutputs, String fileName) throws Exception {
		File outputDir = new File(Paths.get(OUTPUT_PATH).normalize().toAbsolutePath().toString());
		if(!outputDir.exists()) {
			Files.createDirectories(Paths.get(OUTPUT_PATH).normalize().toAbsolutePath());
		}

		try(
				FileWriter csvWriter = new FileWriter(Paths.get(OUTPUT_PATH + fileName).normalize().toAbsolutePath().toString())
		) {
			csvWriter.append("SKU,Description,Source\n");
			for (CsvOutput line : csvOutputs) {
				csvWriter.append(line.getSku())
						.append(",")
						.append(line.getDescription())
						.append(",")
						.append(line.getSource())
						.append("\n");
			}

			csvWriter.flush();

			System.out.println("csv write complete!");
		} catch (IOException e) {
			throw new Exception("Csv output file cannot close. Error: " + e.getMessage());
		}
	}
}
