package com.ali.test.commission.ops;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;

public final class PaymentOperations {

	public static Commission getCommission(Payment thePayment)
	{
		double amount=thePayment.getAmount();
		if(amount <10000)
		{
			return new Commission(0.01*amount);
		}
		else if(amount <100000)
		{
			return new Commission(0.03*amount);
		}
		else
		{
			return new Commission(0.05*amount);
		}
	}
}
