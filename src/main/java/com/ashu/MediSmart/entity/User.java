package com.ashu.MediSmart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Many users can have one role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // 🔹 Doctor-specific fields
    private String specialty;           // e.g. "Cardiologist"
    private String location;            // city or pincode
    private int yearsOfExperience;
    private double rating;              // average rating given by patients
    private String availability;        // e.g. "2025-09-15"

    // 🔹 Patient-specific field
    private Integer age;  // nullable, required only for PATIENT

    // 🔹 Constructors
    public User() {}

    public User(Long id, String name, String email, String password, Role role,
                String specialty, String location, int yearsOfExperience,
                double rating, String availability, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.specialty = specialty;
        this.location = location;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
        this.availability = availability;
        this.age = age;
    }

    // 🔹 Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
