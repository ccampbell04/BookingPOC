package com.example.bookingpoc.repository;

import com.example.bookingpoc.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
