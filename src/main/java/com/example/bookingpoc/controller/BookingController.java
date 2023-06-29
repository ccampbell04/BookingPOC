package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book/")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{id}")
    public String bookClass(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long customerId = requestBody.get("customerId");
        bookingService.bookClass(customerId, id);
        return "Class booked";
    }


    @PostMapping("/remove/{id}")
    public String removeBooking(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long customerId = requestBody.get("customerId");
        bookingService.removeBooking(customerId, id);
        return "Booking removed";
    }
}
