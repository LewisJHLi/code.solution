package com.solution.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Utils {
	/**
	 * Create csv test files.
	 * @param filename
	 *      File name for csv.
	 * @param files
	 *      File contents.
	 */
	public static void createFile(String filename, List<String[]> files) throws Exception {
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
			throw new Exception("Unable to create test csv file " + filename + " for test. Error: " + e.getMessage());
		}
	}
}
