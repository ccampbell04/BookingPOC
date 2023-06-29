package com.example.bookingpoc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "waiting_list")
public class WaitingListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class classEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public WaitingListEntry() {
    }

    public WaitingListEntry(Customer customer, Class classEntity) {
        this.customer = customer;
        this.classEntity = classEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Class classEntity) {
        this.classEntity = classEntity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
