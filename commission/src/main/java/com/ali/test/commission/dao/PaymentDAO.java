package com.ali.test.commission.dao;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;

public interface PaymentDAO {
	public Commission addPayment(Payment thePayment);
	public Commission getMonthlyCommission(String id, int year, int month);
}
