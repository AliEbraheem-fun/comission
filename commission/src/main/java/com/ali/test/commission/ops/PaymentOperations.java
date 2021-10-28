package com.ali.test.commission.ops;

public final class PaymentOperations {
	public static double calculateCommission(double amount) {
		if(amount <10000)
		{
			return  0.01*amount;
		}
		else if(amount <100000)
		{
			return 0.03*amount;
		}
		else
		{
			return 0.05*amount;
		}
	}
}
