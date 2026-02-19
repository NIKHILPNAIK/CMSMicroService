package com.cognizant.cms.repositories;

import com.cognizant.cms.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> { 
	
	
}