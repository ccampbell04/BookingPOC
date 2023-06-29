package com.example.bookingpoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<com.example.bookingpoc.entity.Booking, Long> {
}
