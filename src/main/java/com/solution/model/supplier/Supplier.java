package com.solution.model.supplier;

public class Supplier {
	/*
	 * Supplier identifier.
	 * */
	private final String id;

	/*
	 * Supplier name.
	 * */
	private final String name;

	/**
	 * Default Constructor.
	 */
	public Supplier(final String id, final String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
