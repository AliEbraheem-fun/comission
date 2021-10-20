package com.ali.test.commission.ops;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.ali.test.commission.entity.Payment;

class PaymentOperationsTest {

	@Test
	void testLessThan10000() {
		Payment payment=new Payment("+78997776666",5000.0,LocalDateTime.now(),"Spending money is good");
		assertEquals(50, PaymentOperations.getCommission(payment).getCommission(), "Error in commission calculations for a amount less than 5000");
	}
	
	@Test
	void testLessThan100000() {
		Payment payment=new Payment("+78997776666",20000.0,LocalDateTime.now(),"Spending money is good");
		assertEquals(600, PaymentOperations.getCommission(payment).getCommission(), "Error in commission calculations for a amount less than 5000");
	}
	@Test
	void testLessThanOther() {
		Payment payment=new Payment("+78997776666",500000.0,LocalDateTime.now(),"Spending money is good");
		assertEquals(25000, PaymentOperations.getCommission(payment).getCommission(), "Error in commission calculations for a amount less than 5000");
	}
}
