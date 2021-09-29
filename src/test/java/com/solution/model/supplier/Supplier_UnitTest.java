package com.solution.model.supplier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Supplier_UnitTest {

	private final String id = "test_id";
	private final String name = "test_name";

	Supplier oSupplier = new Supplier(id, name);

	@Test
	void testMethod_getId() {
		assertEquals(id, oSupplier.getId());
	}

	@Test
	void testMethod_getName() {
		assertEquals(name, oSupplier.getName());
	}
}