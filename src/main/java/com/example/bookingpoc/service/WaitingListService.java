package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Class;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.entity.WaitingListEntry;
import com.example.bookingpoc.repository.ClassRepository;
import com.example.bookingpoc.repository.CustomerRepository;
import com.example.bookingpoc.repository.WaitingListEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaitingListService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WaitingListEntryRepository waitingListEntryRepository;

    public void addToWaitingList(Long customerId, Long classId) {
        Class classToAdd = classRepository.findById(classId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();

        WaitingListEntry waitingListEntry = new WaitingListEntry(customer, classToAdd);

        classToAdd.addToWaitingList(waitingListEntry);
        classRepository.save(classToAdd);
    }

    public List<Customer> getWaitingList(Long classId) {
        Class classToBook = classRepository.findById(classId).orElseThrow();
        List<WaitingListEntry> waitingList = classToBook.getWaitingList();
        List<Customer> customers = new ArrayList<>();

        for (WaitingListEntry entry : waitingList) {
            customers.add(entry.getCustomer());
        }

        return customers;
    }

    public void removeBooking(Long customerId, Long classId) {
        Class classToBook = classRepository.findById(classId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        WaitingListEntry waitingListEntry;
        for (WaitingListEntry entry : classToBook.getWaitingList()) {
            if (entry.getCustomer().equals(customer)) {
                waitingListEntry = entry;
                classToBook.removeFromWaitingList(waitingListEntry);
                waitingListEntryRepository.delete(waitingListEntry);
                break;
            }
        }
        classRepository.save(classToBook);
    }
}
