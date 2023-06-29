package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Booking;
import com.example.bookingpoc.entity.Class;
import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes/")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PostMapping()
    public String createClass(@RequestBody Class newClass) {
        classService.createClass(newClass);
        return "Class created";
    }

    @GetMapping()
    @ResponseBody()
    public List<Class> getClasses() {
        return classService.getClasses();
    }

    @GetMapping("/{id}")
    public List<Booking> getBookingsForClass(@PathVariable Long id) {
        return classService.getBookingsForClass(id);
    }
}
