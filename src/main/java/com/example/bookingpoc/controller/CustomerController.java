package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public String createUser(@RequestBody Customer customer) {
        System.out.println(customer);
        customerService.createUser(customer);
        return "User created";
    }

    @GetMapping()
    @ResponseBody()
    public List<Customer> getUsers() {
        return customerService.getUsers();
    }

    @GetMapping("/{id}")
    public Customer getUser(@PathVariable Long id) {
        return customerService.getUser(id);
    }

    @GetMapping("/bookings/{id}")
    public List<Booking> getBookingsForCustomer(@PathVariable Long id) {
        return customerService.getBookingsForCustomer(id);
    }
}
