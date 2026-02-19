package com.cognizant.cms.mappers;

import com.cognizant.cms.dtos.AddressDTO;
import com.cognizant.cms.dtos.CustomerRequest;
import com.cognizant.cms.dtos.CustomerResponse;
import com.cognizant.cms.dtos.FullNameDTO;
import com.cognizant.cms.models.Address;
import com.cognizant.cms.models.Customer;
import com.cognizant.cms.models.FullName;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  // Request -> Entity
  Customer toEntity(CustomerRequest request);

  // Entity -> Response
  CustomerResponse toResponse(Customer entity);
  
  List<CustomerResponse> toResponses(List<Customer> entities);

  // Value-object conversions
  FullName toEmbedded(FullNameDTO dto);
  FullNameDTO toDto(FullName name);

  Address toEmbedded(AddressDTO dto);
  AddressDTO toDto(Address address);

  
  // Partial update (ignore nulls)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromRequest(CustomerRequest request, @MappingTarget Customer entity);
}