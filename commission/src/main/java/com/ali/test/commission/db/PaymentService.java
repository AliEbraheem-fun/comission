package com.ali.test.commission.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;
import com.ali.test.commission.ops.PaymentOperations;


@Service
public class PaymentService {

	@Autowired
	private PaymentDAO paymentDAO;
	
	public Commission addPayment(Payment thePayment) {
		paymentDAO.addPayment(thePayment);
		return PaymentOperations.getCommission(thePayment);
	}
	
}
