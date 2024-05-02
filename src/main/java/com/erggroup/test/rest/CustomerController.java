package com.erggroup.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erggroup.test.domain.Customer;
import com.erggroup.test.services.CustomerService;

@RestController
@RequestMapping("customer/v1")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity registerCustomer(@RequestBody Customer customerData) {
		return ResponseEntity.ok(customerService.registerCustomer(customerData));
	}
	
	@GetMapping("/findCustomer/ref/{reference}")
	public ResponseEntity findCustomerByReference(@PathVariable("reference") String reference) {
		Customer customer = customerService.findCustomer(Long.parseLong(reference));
		return (customer==null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(customer);
	}
}
