package com.example.pos.repository;

import com.example.pos.entity.LoyaltyCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyCustomerRepository extends JpaRepository<LoyaltyCustomer, Long> {

    Optional<LoyaltyCustomer> findByContactNumber(String contactNumber);

    boolean existsByContactNumber(String contactNumber);

    boolean existsByEmail(String email);
}