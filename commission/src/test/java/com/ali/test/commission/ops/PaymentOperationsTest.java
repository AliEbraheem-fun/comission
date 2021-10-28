package com.ali.test.commission.ops;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentOperationsTest {
	@Test
	void testLessThan10000() {
		assertEquals("Error in commission calculations for a amount less than 10000",50.0, PaymentOperations.calculateCommission(5000.0),1e-10);
	}
	
	@Test
	void testLessThan100000() {
		assertEquals( "Error in commission calculations for a amount greater than 100000",600, PaymentOperations.calculateCommission(20000),1e-10);
	}
	@Test
	void testGreaterThan100000() {
		assertEquals("Error in commission calculations for a amount greater than 100000",25000, PaymentOperations.calculateCommission(500000.0),1e-10);
	}
	
}
