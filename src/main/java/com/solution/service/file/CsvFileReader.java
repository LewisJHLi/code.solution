package com.solution.service.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFileReader {

	/**
	 * Default Constructor.
	 */
	public CsvFileReader() {
	}

	/**
	 * Returns a new builder that is capable of producing a {@link CsvFileReader} instance.
	 *
	 * @return CsvFileReader Builder class.
	 */
	public static CsvFileReader builder() {
		return new CsvFileReader();
	}

	/*
	* Returns the csv file contents with given file path.
	* */
	public List<String[]> readFile(String path) throws Exception {
		try(
				BufferedReader csvFile = new BufferedReader(new FileReader(Paths.get(path).normalize().toAbsolutePath().toString()))
		) {
			Stream<String> lines = csvFile.lines();

			return lines.map(line -> line.split(","))
					.collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File was not found. Error: " + e.getMessage());
		}
	}
}
