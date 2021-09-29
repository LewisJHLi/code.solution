package com.solution.model;

public class CsvOutput {

	private final String sku;
	private final String description;
	private final String source;
	/**
	 * Default Constructor.
	 */
	public CsvOutput(final String sku, final String description, final String source) {
		this.sku = sku;
		this.description = description;
		this.source = source;
	}

	public String getSku() {
		return sku;
	}

	public String getDescription() {
		return description;
	}

	public String getSource() {
		return source;
	}
}
