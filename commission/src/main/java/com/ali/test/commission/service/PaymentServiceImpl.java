package com.ali.test.commission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ali.test.commission.dao.PaymentDAO;
import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;


@Service
@Scope("request")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDAO paymentDAO;
	
	public PaymentServiceImpl() {
	}

	public PaymentServiceImpl(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public Commission addPayment(Payment thePayment) {
		return paymentDAO.addPayment(thePayment);
	}

	@Override
	public Commission getMonthlyCommission(String id, int year, int month)
	{
		
		return paymentDAO.getMonthlyCommission(id,year, month);
	}
	
}
