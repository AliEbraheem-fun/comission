package com.ali.test.commission.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.test.commission.db.PaymentService;
import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;

@RestController
@RequestMapping("/payments")
public class CommissionRestController {
	

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/test")
	public String test()
	{
		return "All Ok!!!!";
	}
	
	@PostMapping("/addPayment")
	public Commission addPayment(@Valid @RequestBody Payment thePayment)
	{
		System.out.println(thePayment);
		return paymentService.addPayment(thePayment);
	}
	
}
