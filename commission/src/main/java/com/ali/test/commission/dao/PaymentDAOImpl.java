package com.ali.test.commission.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;
import com.ali.test.commission.ops.PaymentOperations;

@Component
@Scope("singleton")
public class PaymentDAOImpl implements PaymentDAO {
	private List<Payment> paymentList;
	
	public PaymentDAOImpl(List<Payment> paymentList) {
		this.paymentList = Collections.synchronizedList(paymentList);
	}

	public PaymentDAOImpl() {
	}

	@PostConstruct
	private void loadData()
	{
		paymentList=Collections.synchronizedList(new ArrayList<Payment>() );
	}
	
	@Override
	public synchronized Commission addPayment(Payment thePayment)
	{
		
		paymentList.add(thePayment);
		String id=thePayment.getId();
		int year=thePayment.getDateTime().getYear();
		int month=thePayment.getDateTime().getMonthValue();
		double amount=0;
		int tmpMonth,tmpYear;
		String tmpId;
		synchronized (paymentList) {
			for(Payment tempPayment:paymentList)
			{
				tmpId=tempPayment.getId();
				tmpMonth=tempPayment.getDateTime().getMonthValue();
				tmpYear=tempPayment.getDateTime().getYear();
				if(id.equals(tmpId) &&
						year==tmpYear &&
						month==tmpMonth)
				{
					amount+=tempPayment.getAmount();
				}
			}
		}
		
		return new Commission(PaymentOperations.calculateCommission(amount),year,month);
	}

	@Override
	public synchronized Commission getMonthlyCommission(String id,int year, int month) {
		double amount=0;
		int tmpMonth,tmpYear;
		String tmpId;
		synchronized (paymentList) {
			for(Payment tempPayment:paymentList)
			{
				tmpId=tempPayment.getId();
				tmpMonth=tempPayment.getDateTime().getMonthValue();
				tmpYear=tempPayment.getDateTime().getYear();
				if(id.equals(tmpId) &&
						year==tmpYear &&
						month==tmpMonth)
				{
					amount+=tempPayment.getAmount();
				}
			}
		}
		return new Commission(PaymentOperations.calculateCommission(amount),year,month);
	}

	
}
