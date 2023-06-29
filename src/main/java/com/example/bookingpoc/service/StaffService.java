package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Provider;
import com.example.bookingpoc.entity.Staff;
import com.example.bookingpoc.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProviderService providerService;

    public void createStaff(Staff staff) {
        staffRepository.save(staff);
    }

    public Staff getStaff(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
}
