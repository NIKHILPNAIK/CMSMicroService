package com.cognizant.gatewayapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayappApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayappApplication.class, args);
    }

}
