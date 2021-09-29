package com.solution;

import com.solution.model.CsvOutput;
import com.solution.service.file.CsvFileReader;
import com.solution.service.file.CsvFileWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionApp {

	static CsvFileReader csvFileReader = new CsvFileReader();
	static CsvFileWriter csvFileWriter = new CsvFileWriter();

	/*
	* Main method to run the solution and get output.
	* */
	public static void main(String[] args) throws Exception {
		System.out.println("this is main app");

		String inputPath = "./input/catalogA.csv";
		for (String[] lines : csvFileReader.readFile(inputPath)) {
			System.out.println(Arrays.toString(lines));
		}

		String outputPath = "./output/";
		String outputFile = "result_output.csv";

		List<CsvOutput> csvOutputs = new ArrayList<>();
		CsvOutput csvOutput1 = new CsvOutput("this is sku", "this is description", "this is source");
		CsvOutput csvOutput2 = new CsvOutput("new sku", "new description", "new source");
		csvOutputs.add(csvOutput1);
		csvOutputs.add(csvOutput2);
		csvFileWriter.writeFile(csvOutputs, outputFile);

		for (String[] lines : csvFileReader.readFile(outputPath + outputFile)) {
			System.out.println(Arrays.toString(lines));
		}
	}
}
