package com.ashu.MediSmart.DTO;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String role;  // "ADMIN", "DOCTOR", "PATIENT"

    // 🔹 Doctor-specific fields
    private String specialty;
    private String location;
    private int yearsOfExperience;
    private double rating;
    private String availability;

    // 🔹 Patient-specific field
    private Integer age;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

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

    // inside UserDTO.java

    // Constructor for doctor recommendation response
    public UserDTO(Long id, String name, String specialty, String location,
                   int yearsOfExperience, double rating, String availability) {
        this.name = name;
        this.specialty = specialty;
        this.location = location;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
        this.availability = availability;
    }

}
