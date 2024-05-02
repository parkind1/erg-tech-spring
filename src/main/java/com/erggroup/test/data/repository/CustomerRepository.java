package com.erggroup.test.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.erggroup.test.data.entities.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{
	public Optional<CustomerEntity> findByCustomerRef(Long customerRef);
}
