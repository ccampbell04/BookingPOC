package com.example.bookingpoc.service;

import com.example.bookingpoc.entity.Provider;
import com.example.bookingpoc.entity.Staff;
import com.example.bookingpoc.repository.ProviderRepository;
import com.example.bookingpoc.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    // Circular issue when using staff service
    @Autowired
    private StaffRepository staffRepository;

    public void createProvider(Provider provider) {
        providerRepository.save(provider);
    }

    public Provider getProvider(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }

    public void addStaffToProvider(Long providerId, Long staffId) {
        // Retrieve the provider and staff member from the repositories
        Provider provider = providerRepository.findById(providerId).orElse(null);
        Staff staff = staffRepository.findById(staffId).orElse(null);

        if (provider != null && staff != null) {
            // Add the staff member to the provider's staff list
            provider.getStaff().add(staff);
            staff.setProvider(provider);

            // Update the provider and staff in the repositories
            providerRepository.save(provider);
            staffRepository.save(staff);
        } else {
            // Handle the case where either the provider or staff member is not found
            throw new IllegalArgumentException("Invalid providerId or staffId");
        }
    }

    public List<Staff> getStaffByProvider(Long id) {
        Provider provider = providerRepository.findById(id).orElse(null);
        assert provider != null;
        return provider.getStaff();
    }
}
