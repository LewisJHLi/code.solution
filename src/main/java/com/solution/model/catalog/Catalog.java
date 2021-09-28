package com.solution.model.catalog;

public class Catalog {
	/*
	 * SKU identifier for catalog.
	 * */
	private final String sku;

	/*
	 * Description for catalog.
	 * */
	private final String description;

	/**
	 * Default Constructor.
	 */
	public Catalog(final String sku, final String description) {
		this.sku = sku;
		this.description = description;
	}

	public String getSku() {
		return sku;
	}

	public String getDescription() {
		return description;
	}
}
