package com.erggroup.test.services;

import com.erggroup.test.domain.Customer;

public interface CustomerService {
	
	public Long registerCustomer(Customer customer);
	
	public Customer findCustomer(Long customerRef);
}
