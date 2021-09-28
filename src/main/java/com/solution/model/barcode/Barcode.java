package com.solution.model.barcode;

public class Barcode {
	/*
	 * Belongs to supplier
	 * */
	private final String supplierId;

	/*
	 * SKU for the barcode
	 * */
	private final String sku;

	/*
	 * Barcode
	 * */
	private final String barcode;

	/**
	 * Default Constructor.
	 */
	public Barcode(final String supplierId, final String sku, final String barcode) {
		this.supplierId = supplierId;
		this.sku = sku;
		this.barcode = barcode;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public String getSku() {
		return sku;
	}

	public String getBarcode() {
		return barcode;
	}
}
