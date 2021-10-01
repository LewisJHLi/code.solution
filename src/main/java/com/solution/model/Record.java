package com.solution.model;

import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;

import java.util.Objects;

public class Record {
	private final Catalog catalog;
	private final String barcode;
	private final Supplier supplier;

	public Record(final Catalog catalog, final String barcode, final Supplier supplier) {
		this.catalog = catalog;
		this.barcode = barcode;
		this.supplier = supplier;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public String getBarcode() {
		return barcode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }
		final Record record = (Record) o;
		return Objects.equals(catalog.getSku(), record.catalog.getSku()) &&
				Objects.equals(barcode, record.barcode) &&
				Objects.equals(supplier.getId(), record.supplier.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(catalog, barcode, supplier);
	}
}
