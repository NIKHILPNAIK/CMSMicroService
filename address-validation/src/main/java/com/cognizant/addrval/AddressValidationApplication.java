package com.cognizant.addrval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AddressValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressValidationApplication.class, args);
    }

}
