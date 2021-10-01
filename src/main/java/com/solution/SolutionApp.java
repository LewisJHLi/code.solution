package com.solution;

import com.solution.model.CsvOutput;
import com.solution.service.data.ResourceGenerator;
import com.solution.service.file.CsvFileWriter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SolutionApp {

	static ResourceGenerator resourceGenerator = new ResourceGenerator();
	static CsvFileWriter csvFileWriter = new CsvFileWriter();

	private static final String HEADQUARTER_NAME = "A";
	private static final String MERGED_NAME = "B";
	private static String OUTPUT_FILE = "result_output.csv";

	/*
	* Main method to run the solution and get output.
	* */
	public static void main(String[] args) throws Exception {
		Map<String, String> companies = new LinkedHashMap<>();
		companies.put("headquarter", HEADQUARTER_NAME);
		companies.put("merged", MERGED_NAME);

		List<CsvOutput> csvOutputs = resourceGenerator.generate(companies);
		csvFileWriter.writeFile(csvOutputs, OUTPUT_FILE);
	}
}
