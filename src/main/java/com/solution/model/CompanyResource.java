package com.solution.model;

import com.solution.model.catalog.Catalog;

import java.util.List;

public class CompanyResource {

	private final String company;
	private final List<Catalog> catalogs;

	public CompanyResource(final String company, final List<Catalog> catalogs) {
		this.company = company;
		this.catalogs = catalogs;
	}

	public String getCompany() {
		return company;
	}

	public List<Catalog> getCatalogs() {
		return catalogs;
	}
}
