package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Staff;
import com.example.bookingpoc.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping()
    public String createStaff(@RequestBody Staff staff) {
        staffService.createStaff(staff);
        return "Staff created";
    }

    @GetMapping("/{id}")
    public Staff getStaff(@PathVariable Long id) {
        return staffService.getStaff(id);
    }

    @GetMapping()
    public List<Staff> getStaff() {
        return staffService.getAllStaff();
    }
}
