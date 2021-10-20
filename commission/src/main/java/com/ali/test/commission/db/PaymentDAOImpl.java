package com.ali.test.commission.db;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.ali.test.commission.entity.Payment;

@Component
public class PaymentDAOImpl implements PaymentDAO {
	List<Payment> paymentList;
	
	@PostConstruct
	private void loadData()
	{
		paymentList=new ArrayList<Payment>();
	}
	
	@Override
	public void addPayment(Payment thePayment)
	{
		paymentList.add(thePayment);
	}

}
