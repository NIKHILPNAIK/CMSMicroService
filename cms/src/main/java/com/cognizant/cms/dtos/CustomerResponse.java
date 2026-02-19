package com.cognizant.cms.dtos;

import com.cognizant.cms.models.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
  private Long id;
  private FullNameDTO fullName;
  private Gender gender;
  private int age;
  private String nickName;
  private String qualification;
  private AddressDTO permanentAddress;
  private AddressDTO communicationAddress;
  private String notes;
}