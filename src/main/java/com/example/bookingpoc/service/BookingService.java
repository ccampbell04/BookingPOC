package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Class;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.repository.BookingRepository;
import com.example.bookingpoc.repository.ClassRepository;
import com.example.bookingpoc.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private static final String COUNT_BOOKINGS_FOR_CLASS = "SELECT COUNT(b) FROM Booking b WHERE b.classEntity.id = :classId";
    private static final String FIND_BOOKING_QUERY = "SELECT b FROM Booking b WHERE b.customer = :customer AND b.classEntity = :classEntity";

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingsRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void bookClass(Long customerId, Long classId) {
        Class classToBook = classRepository.findById(classId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Booking booking = new Booking(customer, classToBook);
        bookingsRepository.save(booking);

        if (isFullAfterBooking(classToBook)) {
            classToBook.setFull(true);
            classRepository.save(classToBook);
        }
    }

    private boolean isFullAfterBooking(Class classToBook) {
        return classToBook.getCapacity() == getNumberOfBookingsForClass(classToBook.getId());
    }

    private int getNumberOfBookingsForClass(Long classId) {
        Query query = entityManager.createQuery(COUNT_BOOKINGS_FOR_CLASS);
        query.setParameter("classId", classId);
        return ((Long) query.getSingleResult()).intValue();
    }

    public void removeBooking(Long customerId, Long id) {
        Class classToBook = classRepository.findById(id).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        Booking bookingToRemove = findBookingByCustomerAndClass(customer, classToBook);
        if (bookingToRemove != null) {
            bookingsRepository.delete(bookingToRemove);
            if (classToBook.isFull()) {
                classToBook.setFull(false);
                classRepository.save(classToBook);
            }
        }
    }

    private Booking findBookingByCustomerAndClass(Customer customer, Class classToBook) {
        Query query = entityManager.createQuery(FIND_BOOKING_QUERY);
        query.setParameter("customer", customer);
        query.setParameter("classEntity", classToBook);
        List<Booking> bookings = query.getResultList();
        if (bookings.isEmpty()) {
            return null;
        }
        return bookings.get(0);
    }
}
