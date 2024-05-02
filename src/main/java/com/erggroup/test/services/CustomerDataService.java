package com.erggroup.test.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erggroup.test.data.entities.CustomerEntity;
import com.erggroup.test.data.repository.CustomerRepository;
import com.erggroup.test.domain.Customer;

@Service
public class CustomerDataService implements CustomerService {

	CustomerRepository customerRepository;
	
	@Autowired
	public CustomerDataService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public Long registerCustomer(Customer customer) {
		CustomerEntity customerToSave = new CustomerEntity();
		customerToSave.setCustomerRef(Long.parseLong(customer.customerRef()));
		customerToSave.setCustomerName(customer.customerName());
		customerToSave.setAddressLine1(customer.addressLine1());
		customerToSave.setAddressLine2(customer.addressLine2());
		customerToSave.setTown(customer.town());
		customerToSave.setCounty(customer.county());
		customerToSave.setCountry(customer.country());
		customerToSave.setPostCode(customer.postCode());
		
		return customerRepository.save(customerToSave).getCustomerRef();
	}

	@Override
	public Customer findCustomer(Long customerRef) {
		Optional<CustomerEntity> customer = customerRepository.findByCustomerRef(customerRef);
		return customer.isEmpty() ? null : new Customer(Long.toString(customer.get().getCustomerRef()), customer.get().getCustomerName(), customer.get().getAddressLine1(), customer.get().getAddressLine2(), customer.get().getTown(), customer.get().getCounty(), customer.get().getCountry(), customer.get().getPostCode());
	}

}
