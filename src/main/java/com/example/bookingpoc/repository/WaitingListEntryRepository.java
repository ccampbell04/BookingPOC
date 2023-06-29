package com.example.bookingpoc.repository;

import com.example.bookingpoc.entity.WaitingListEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListEntryRepository extends JpaRepository<WaitingListEntry, Long> {
}
