package com.ali.test.commission.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ali.test.commission.entity.Commission;
import com.ali.test.commission.entity.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@SpringBootTest
@AutoConfigureMockMvc
class CommissionRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testTest() throws Exception {
		String uri = "/payments/test";
		MvcResult mvcResult = mockMvc.perform(get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		//System.out.println("status="+status);
		assertEquals(200, status, "user accounts were different");
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println("content="+content);

		assertEquals("All Ok!!!!", content, "Returned json is" + content);
		//System.out.flush();
	}

	@Test
	void testAddPayment() throws Exception{
	    ObjectMapper objectMapper = JsonMapper.builder()
	    	    .addModule(new JavaTimeModule())
	    	    .build();
		String uri = "/payments/addPayment";
		Payment payment=new Payment("+79665861298",15000,LocalDateTime.now(),"Spending money!!!");
		double expectedCommission=0.03*15000;
		String jsonPayment=objectMapper.writeValueAsString(payment);
		MvcResult mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonPayment))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		//System.out.println("status="+status);
		assertEquals(200, status, "user accounts were different");
		String content = mvcResult.getResponse().getContentAsString();
		//System.out.println("content="+content);
		Commission commission= objectMapper.readValue(content,Commission.class);
		assertEquals(expectedCommission, commission.getCommission(),"Wrong amount!!" );
		//System.out.flush();
		}

}
