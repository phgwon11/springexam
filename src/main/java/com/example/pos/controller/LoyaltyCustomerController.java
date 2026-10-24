package com.example.pos.controller;

import com.example.pos.entity.LoyaltyCustomer;
import com.example.pos.repository.LoyaltyCustomerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loyalty-customers")
public class LoyaltyCustomerController {

    private final LoyaltyCustomerRepository customerRepository;

    public LoyaltyCustomerController(
            LoyaltyCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public LoyaltyCustomer getCustomerByMobileNumber(
            @RequestParam String mobileNumber) {

        return customerRepository
                .findByContactNumber(mobileNumber)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }
}