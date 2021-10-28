package com.ali.test.commission.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@SpringBootTest
@AutoConfigureMockMvc
class CommissionRestControllerTest {
	private static final int year=2000;
	private static final int  month=2;
	
	@Autowired
	private MockMvc mockMvc;
	
	private LocalDateTime getRandomLocalDateTime()
	{
		Random random=new Random();
		return LocalDateTime.of(year,month,random.nextInt(27)+1,random.nextInt(22)+1,random.nextInt(58)+1);
	}
	
	@Test
	void testTest() throws Exception {
		this.mockMvc.perform(get("/payments/test"))
		  .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(content().contentType("text/plain;charset=UTF-8"))
	      .andExpect(content().string("All Ok!!!!"));		
	}

	@Test
	void testPaymentOperations() throws Exception{
	    ObjectMapper objectMapper = JsonMapper.builder()
	    	    .addModule(new JavaTimeModule())
	    	    .build();
	    LocalDateTime localDateTime=getRandomLocalDateTime();
	    double amount=5000;
		double expectedCommission=0.01*amount;
		Payment payment=new Payment("+79665861298",amount,localDateTime,"Spending money!!!");
		String jsonPayment=objectMapper.writeValueAsString(payment);
		String content = mockMvc.perform(post("/payments/addPayment")
											  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
												.andDo(print())
											  .andExpect(status().isOk())
											  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
											  .andReturn().getResponse().getContentAsString();
		Commission commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		
		content= mockMvc.perform(get("/payments/getMonthlyCommission/{id}/{year}/{month}","+79665861298",localDateTime.getYear(),localDateTime.getMonthValue()))
		 .andDo(print())
		 .andExpect(status().isOk())
		  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		 .andReturn().getResponse().getContentAsString();
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );

		
		amount=10000;
		expectedCommission=0.03*(5000+10000);// total amount now for the current month
		payment=new Payment("+79665861298",amount,localDateTime,"Spending money again!!!");
		jsonPayment=objectMapper.writeValueAsString(payment);
		content = mockMvc.perform(post("/payments/addPayment")
											  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
											  .andDo(print())
											  .andExpect(status().isOk())
											  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
											  .andReturn().getResponse().getContentAsString();;
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		
		content= mockMvc.perform(get("/payments/getMonthlyCommission/{id}/{year}/{month}","+79665861298",localDateTime.getYear(),localDateTime.getMonthValue()))
				 .andDo(print())
				 .andExpect(status().isOk())
				  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				 .andReturn().getResponse().getContentAsString();
				commission= objectMapper.readValue(content,Commission.class);
				assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );	
		
		amount=100000;
		expectedCommission=0.05*(5000+10000+100000);// total amount now for the current month
		payment=new Payment("+79665861298",amount,localDateTime,"Spending a big amount of money!!!");
		jsonPayment=objectMapper.writeValueAsString(payment);
		content = mockMvc.perform(post("/payments/addPayment")
											  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
											  .andDo(print())
											  .andExpect(status().isOk())
											  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
											  .andReturn().getResponse().getContentAsString();;
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		
		content= mockMvc.perform(get("/payments/getMonthlyCommission/{id}/{year}/{month}","+79665861298",localDateTime.getYear(),localDateTime.getMonthValue()))
				 .andDo(print())
				 .andExpect(status().isOk())
				  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				 .andReturn().getResponse().getContentAsString();
				commission= objectMapper.readValue(content,Commission.class);
				assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );	
		
	    localDateTime=getRandomLocalDateTime();
		localDateTime=localDateTime.plusMonths(2);
		amount=2800;
		expectedCommission=0.01*(2800);// total amount now for the current month
		payment=new Payment("+79665861298",amount,localDateTime,"Spending a little money!!!");
		jsonPayment=objectMapper.writeValueAsString(payment);
		content = mockMvc.perform(post("/payments/addPayment")
											  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
											  .andDo(print())
											  .andExpect(status().isOk())
											  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
											  .andReturn().getResponse().getContentAsString();
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		
		content= mockMvc.perform(get("/payments/getMonthlyCommission/{id}/{year}/{month}","+79665861298",localDateTime.getYear(),localDateTime.getMonthValue()))
				 .andDo(print())
				 .andExpect(status().isOk())
				  .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				 .andReturn().getResponse().getContentAsString();
				commission= objectMapper.readValue(content,Commission.class);
				assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );
		commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong commission!!" );	
		
		
	    localDateTime=getRandomLocalDateTime();
		localDateTime=localDateTime.plusMonths(3);
		//wrong telephone number format
		payment=new Payment("966588",amount,localDateTime,"Spending a big amount of money!!!");
		jsonPayment=objectMapper.writeValueAsString(payment);
		 mockMvc.perform(post("/payments/addPayment")
				  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
				  .andDo(print())
				  .andExpect(status().is4xxClientError());
		 	 
		//wrong date
		jsonPayment="{\r\n"
				+ "    \"id\":\"+79251794457\",\r\n"
				+ "    \"amount\":9000,\r\n"
				+ "    \"dateTime\":\"1999-88-28 21:22:59\",\r\n"
				+ "    \"comment\": \"life is good\"\r\n"
				+ "}";
		 mockMvc.perform(post("/payments/addPayment")
				  .contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
				  .andDo(print())
				  .andExpect(status().is4xxClientError());		
		 
		 /***************** Note **************************/
		 // Tests for path variables are conducted using Postman because they have caused exceptions rather than client error status 
		 // In Postman every thing went ok!!!
		 
		 
		 
		 
	}


}
