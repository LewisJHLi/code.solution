package com.solution.model;

import java.util.List;

public class CompanyResource {

	private final String company;

	/**
	 * List of complete records for the product.
	 */
	private final List<Record> records;

	public CompanyResource(final String company, final List<Record> records) {
		this.company = company;
		this.records = records;
	}

	public String getCompany() {
		return company;
	}

	public List<Record> getRecords() {
		return records;
	}
}
