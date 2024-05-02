package com.erggroup.test.rest;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.erggroup.test.domain.Customer;
import com.erggroup.test.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testThatServiceSavesACustomer() throws Exception {
		Customer customer = new Customer("1", "Petra FiedOfSpiders", "1 Some Street", "Some Town", "Some District", "Some County", "Some Country", "AB1 2CD");
		when(customerService.registerCustomer(isA(Customer.class))).thenReturn(1L);
		this.mockMvc.perform(post("/customer/v1/registerCustomer").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(customer))).andExpect(status().isOk());
	}

	@Test
	void testThatControllerReturnsACustomer() throws Exception {		
		Customer customer = new Customer("1", "Petra FiedOfSpiders", "1 Some Street", "Some Town", "Some District", "Some County", "Some Country", "AB1 2CD");

		when(customerService.findCustomer(isA(Long.class))).thenReturn(customer);
		this.mockMvc.perform(get("/customer/v1/findCustomer/ref/1")).andExpect(status().isOk());
	}

	@Test
	void testThatControllerDoesntFindACustomer() throws Exception {		
		when(customerService.findCustomer(isA(Long.class))).thenReturn(null);
		this.mockMvc.perform(get("/customer/v1/findCustomer/ref/1")).andExpect(status().isNotFound());
	}
}
