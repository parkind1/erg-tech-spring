package com.erggroup.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.erggroup.test.data.entities.CustomerEntity;
import com.erggroup.test.data.repository.CustomerRepository;
import com.erggroup.test.domain.Customer;

@ExtendWith(MockitoExtension.class)
class CustomerDataServiceTest {

	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerDataService customerService;
	
	@Test
	void testCustomerRegisteredSuccessfully() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerRef(1L);
		
		when(customerRepository.save(isA(CustomerEntity.class))).thenReturn(customerEntity);
		Customer customer = new Customer("1", "Petra FiedOfSpiders", "1 Some Street", "Some Town", "Some District", "Some County", "Some Country", "AB1 2CD");
		Long customerRef = customerService.registerCustomer(customer);
		assertNotNull(customerRef, "Customer reference can not be null");
		assertEquals(1, customerRef);
	}

	
	@Test
	void testCustomerFoundByReference() {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setCustomerRef(1L);
		customerEntity.setCustomerName("Petra FiedofSpiders");
		customerEntity.setAddressLine1("1 Some Street");
		customerEntity.setAddressLine2("Some District");
		customerEntity.setTown("Some Town");
		customerEntity.setCounty("Some County");
		customerEntity.setCountry("Some Country");
		customerEntity.setPostCode("AB1 2CD");
		
		when(customerRepository.findByCustomerRef(1L)).thenReturn(Optional.of(customerEntity));
		Customer customer = customerService.findCustomer(1L);
		
		Customer customerComparison = new Customer("1", "Petra FiedOfSpiders", "1 Some Street", "Some Town", "Some District", "Some County", "Some Country", "AB1 2CD");
		
		assertEquals(customer.customerRef(), customerComparison.customerRef());
		
	}
	
	@Test
	void testCustomerNotFoundByReference() {
		when(customerRepository.findByCustomerRef(1L)).thenReturn(Optional.empty());
		Customer customer = customerService.findCustomer(1L);
		assertNull(customer);
	}

}
