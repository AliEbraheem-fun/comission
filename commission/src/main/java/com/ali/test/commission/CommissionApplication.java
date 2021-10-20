package com.ali.test.commission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ali.test.commission", "com.ali.test.commssion.db",
		"com.ali.test.commission.rest" })
public class CommissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommissionApplication.class, args);
	}

}
