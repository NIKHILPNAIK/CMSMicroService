package com.cognizant.addrval.repositories;

import com.cognizant.addrval.models.AddressValidationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressValidationLogRepository extends JpaRepository<AddressValidationLog, Long> { }