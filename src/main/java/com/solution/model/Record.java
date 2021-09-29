package com.solution.model;

import com.solution.model.barcode.Barcode;
import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;

public class Record {
	private final Catalog catalog;
	private final Barcode barcode;
	private final Supplier supplier;

	public Record(final Catalog catalog, final Barcode barcode, final Supplier supplier) {
		this.catalog = catalog;
		this.barcode = barcode;
		this.supplier = supplier;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public Barcode getBarcode() {
		return barcode;
	}

	public Supplier getSupplier() {
		return supplier;
	}
}
