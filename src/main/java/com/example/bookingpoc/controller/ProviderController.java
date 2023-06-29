package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Provider;
import com.example.bookingpoc.entity.Staff;
import com.example.bookingpoc.service.ProviderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping()
    public String createProvider(@RequestBody Provider provider) {
        providerService.createProvider(provider);
        return "Provider created";
    }

    @GetMapping("/{id}")
    public Provider getProvider(@PathVariable Long id) {
        return providerService.getProvider(id);
    }

    @GetMapping("/staff/{id}")
    public List<Staff> getStaffByProvider(@PathVariable Long id) {
        return providerService.getStaffByProvider(id);
    }

    @GetMapping()
    public List<Provider> getProviders() {
        return providerService.getProviders();
    }

    @PostMapping("/staff")
    public String addStaffToProvider(@RequestParam("provider") Long providerId, @RequestParam("staff") Long id) {
        providerService.addStaffToProvider(providerId, id);
        return "Staff added to provider";
    }
}
