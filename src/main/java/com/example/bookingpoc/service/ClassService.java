package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.repository.ClassRepository;
import com.example.bookingpoc.entity.Class;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    private static final String FIND_BOOKINGS_FOR_CLASS_QUERY = "SELECT b.customer FROM Booking b WHERE b.classEntity.id = :classId";

    @Autowired
    private ClassRepository classRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void createClass(Class newClass) {
        classRepository.save(newClass);
    }

    public List<Booking> getBookingsForClass(Long classId) {
        Query query = entityManager.createQuery(FIND_BOOKINGS_FOR_CLASS_QUERY);
        query.setParameter("classId", classId);
        return query.getResultList();
    }

    public List<Class> getClasses() {
        return classRepository.findAll();
    }
}
