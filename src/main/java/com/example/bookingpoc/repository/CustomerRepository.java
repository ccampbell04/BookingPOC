package com.example.bookingpoc.repository;

import com.example.bookingpoc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
