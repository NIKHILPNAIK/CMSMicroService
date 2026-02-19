package com.cognizant.cms.services;

import com.cognizant.cms.dtos.CustomerRequest;
import com.cognizant.cms.dtos.CustomerResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {
  CustomerResponse create(CustomerRequest request);
  List<CustomerResponse> getAll(CustomerRequest request);
  CompletableFuture<CustomerResponse> getById(Long id);
  CustomerResponse update(Long id, CustomerRequest request);
  boolean delete(Long id);

}