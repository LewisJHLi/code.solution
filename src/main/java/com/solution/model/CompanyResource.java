package com.solution.model;

import com.solution.model.catalog.Catalog;

import java.util.List;

public class CompanyResource {

	private final String company;
	private final List<Catalog> catalogs;
	private final List<Record> records;

	public CompanyResource(final String company, final List<Catalog> catalogs, final List<Record> records) {
		this.company = company;
		this.catalogs = catalogs;
		this.records = records;
	}

	public String getCompany() {
		return company;
	}

	public List<Catalog> getCatalogs() {
		return catalogs;
	}

	public List<Record> getRecords() {
		return records;
	}
}
