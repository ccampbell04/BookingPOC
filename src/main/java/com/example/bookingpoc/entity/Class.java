package com.example.bookingpoc.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @Column(name = "class_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_full")
    private boolean isFull;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<WaitingListEntry> waitingList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public List<WaitingListEntry> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(List<WaitingListEntry> waitingList) {
        this.waitingList = waitingList;
    }

    public void addToWaitingList(WaitingListEntry entry) {
        waitingList.add(entry);
    }

    public void removeFromWaitingList(WaitingListEntry entry) {
        waitingList.remove(entry);
    }
}
