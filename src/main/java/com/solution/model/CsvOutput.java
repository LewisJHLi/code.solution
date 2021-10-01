package com.solution.model;

import java.util.Objects;

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }
		final CsvOutput csvOutput = (CsvOutput) o;
		return Objects.equals(sku, csvOutput.sku) && Objects.equals(description, csvOutput.description) && Objects.equals(
				source, csvOutput.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sku, description, source);
	}
}
