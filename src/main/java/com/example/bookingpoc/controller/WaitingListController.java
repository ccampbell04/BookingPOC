package com.example.bookingpoc.controller;

import com.example.bookingpoc.entity.Customer;
import com.example.bookingpoc.entity.WaitingListEntry;
import com.example.bookingpoc.service.WaitingListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wait/")
public class WaitingListController {

    private final WaitingListService waitingListService;

    public WaitingListController(WaitingListService waitingListService) {
        this.waitingListService = waitingListService;
    }

    @PostMapping("/{id}")
    public String bookClass(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long customerId = requestBody.get("customerId");
        waitingListService.addToWaitingList(customerId, id);
        return "Added to waiting list";
    }

    @GetMapping("/{id}")
    @ResponseBody()
    public List<Customer> getWaitingList(@PathVariable Long id) {
        return waitingListService.getWaitingList(id);
    }

    @PostMapping("/remove/{id}")
    public String removeBooking(@PathVariable Long id, @RequestBody Map<String, Long> requestBody) {
        Long customerId = requestBody.get("customerId");
        waitingListService.removeBooking(customerId, id);
        return "Removed from waiting list";
    }

}
