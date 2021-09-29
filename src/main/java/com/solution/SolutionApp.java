package com.solution;

import com.solution.service.file.CsvFileReader;

import java.util.Arrays;
import java.util.List;

public class SolutionApp {

	static CsvFileReader csvFileReader = new CsvFileReader();

	/*
	* Main method to run the solution and get output.
	* */
	public static void main(String[] args) throws Exception {
		System.out.println("this is main app");

		String path = "./input/catalogA.csv";
		for (String[] lines : csvFileReader.readFile(path)) {
			System.out.println(Arrays.toString(lines));
		}
	}
}
