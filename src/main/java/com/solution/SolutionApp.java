package com.solution;

import com.solution.service.file.CsvFileReader;
import com.solution.service.file.CsvFileWriter;

import java.util.Arrays;

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

		csvFileWriter.writeFile();
	}
}
