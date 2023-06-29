package com.example.bookingpoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "provider")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private Long id;

    @Column(name = "provider_name")
    private String name;

    @Column(name = "provider_email")
    private String email;

    @Column(name = "provider_phone")
    private String phone;

    @Column(name = "provider_website")
    private String website;

    @ElementCollection
    @Column(name = "provider_social_media")
    private List<String> socialMedia;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "provider_staff")
    private List<Staff> staff;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(List<String> socialMedia) {
        this.socialMedia = socialMedia;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }
}
