package com.ali.test.commission.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ali.test.commission.dao.PaymentDAOImpl;
import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;


@ExtendWith(SpringExtension.class)
public class PaymentServiceTest {

private PaymentService paymentService;
private static final int year=2021;
private static final int  month=3;

private LocalDateTime getRandomLocalDateTime()
{
	Random random=new Random();
	return LocalDateTime.of(year,month,random.nextInt(27)+1,random.nextInt(22)+1,random.nextInt(58)+1);
}
@BeforeEach
void init()
{
	paymentService=new PaymentServiceImpl(new PaymentDAOImpl(new ArrayList<Payment>()));
}

@Test
public void serviceTest()
{
	LocalDateTime localDateTime=getRandomLocalDateTime();
	Commission actual=paymentService.addPayment(new Payment("+79660938983", 2000, localDateTime ,"adding 2000"));
	assertEquals("Error in returned commission",20.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",month,actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	
	
	localDateTime=localDateTime.plusMonths(1);
	actual=paymentService.addPayment(new Payment("+79660938983", 2000, localDateTime ,"adding 2000 by +79660938983"));
	actual=paymentService.addPayment(new Payment("+79253387990", 9000, localDateTime ,"adding 1500 by +79253387990"));
	actual=paymentService.addPayment(new Payment("+79660938983", 3000, localDateTime ,"adding 3000 by +79660938983"));
	actual=paymentService.addPayment(new Payment("+79660938983", 1500, localDateTime ,"adding 1500 by +79660938983"));
	assertEquals("Error in returned commission",65.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",month+1,actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	actual=paymentService.getMonthlyCommission("+79660938983", year, month+1);
	assertEquals("Error in returned commission",65.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",month+1,actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	
	localDateTime=localDateTime.plusMonths(1);
	actual=paymentService.addPayment(new Payment("+79660938983", 5000, localDateTime ,"adding 5000 by +79660938983"));
	actual=paymentService.addPayment(new Payment("+79253387990", 200000, localDateTime ,"adding 200000 by +79253387990"));
	actual=paymentService.addPayment(new Payment("+79253387880", 9000, localDateTime ,"adding 9000 by +79253387880"));
	actual=paymentService.addPayment(new Payment("+79660938983", 50000, localDateTime ,"adding 50000 by +79660938983"));
	assertEquals("Error in returned commission",1650.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",(month+2),actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	actual=paymentService.getMonthlyCommission("+79660938983", year, month+2);
	assertEquals("Error in returned commission",1650.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",month+2,actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	
	localDateTime=localDateTime.plusMonths(1);
	actual=paymentService.addPayment(new Payment("+79660938983", 100000, localDateTime ,"adding 100000 by +79660938983"));
	actual=paymentService.addPayment(new Payment("+79253777980", 7800000, localDateTime ,"adding 7800000 by +79253777980"));
	actual=paymentService.addPayment(new Payment("+79660938983", 50000, localDateTime ,"adding 50000 by +79660938983"));
	assertEquals("Error in returned commission",7500.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",(month+3),actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());
	actual=paymentService.getMonthlyCommission("+79660938983", year, month+3);
	assertEquals("Error in returned commission",7500.0,actual.getCommission(),1e-20);
	assertEquals("Error in month",month+3,actual.getMonth());
	assertEquals("Error in year",year,actual.getYear());

}



}
