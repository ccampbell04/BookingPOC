package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final String FIND_CLASS_IDS_FOR_CUSTOMER_QUERY = "SELECT b.classEntity FROM Booking b WHERE b.customer.id = :customerId";

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Customer> getUsers() {
        return customerRepository.findAll();
    }

    public void createUser(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Booking> getBookingsForCustomer(Long customerId) {
        Query query = entityManager.createQuery(FIND_CLASS_IDS_FOR_CUSTOMER_QUERY);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }

    public Customer getUser(Long userId) {
        return customerRepository.findById(userId).orElse(null);
    }
}
