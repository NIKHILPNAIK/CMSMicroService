package com.cognizant.cms.dtos;

import com.cognizant.cms.models.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

  @Valid @NotNull
  private FullNameDTO fullName;

  @NotNull
  private Gender gender;

  @Min(0) @Max(120)
  private int age;

  @Size(max = 100)
  private String nickName;

  @NotBlank @Size(max = 100)
  private String qualification;

  @Valid @NotNull
  private AddressDTO permanentAddress;

  @Valid @NotNull
  private AddressDTO communicationAddress;

  @Size(max = 250)
  private String notes;
}