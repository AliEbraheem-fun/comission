package com.ali.test.commission.service;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;

public interface PaymentService {
	
	public Commission addPayment(Payment thePayment);
	public Commission getMonthlyCommission(String id, int year, int month);
	
}
