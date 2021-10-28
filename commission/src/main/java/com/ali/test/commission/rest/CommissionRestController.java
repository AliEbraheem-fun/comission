package com.ali.test.commission.rest;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;
import com.ali.test.commission.service.PaymentServiceImpl;

@RestController
@Scope(value = "request")  
@RequestMapping("/payments")
@Validated
public class CommissionRestController {
	

	@Autowired
	private PaymentServiceImpl paymentService;
	
	@GetMapping("/test")
	public String test()
	{
		return "All Ok!!!!";
	}
	
	@PostMapping("/addPayment")
	public Commission addPayment(@Valid @RequestBody Payment thePayment)
	{
		return paymentService.addPayment(thePayment);
	}
	
	@GetMapping("/getMonthlyCommission/{id}/{year}/{month}")
	public Commission getMonthlyCommission(
			@Pattern(regexp="^[+][7][0-9]{10}") @PathVariable String id,
			 @Min(1900) @Max(9999) @PathVariable int year,
			 @Min(1) @Max(12) @PathVariable int month)
	{
		return paymentService.getMonthlyCommission(id,year, month);
	}
}
