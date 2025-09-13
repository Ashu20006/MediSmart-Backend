package com.ashu.MediSmart.repository;

import com.ashu.MediSmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // Find doctors by specialty, location and availability
    @Query("SELECT u FROM User u WHERE u.role.name = 'DOCTOR' " +
            "AND u.specialty = :specialty " +
            "AND u.location = :location " +
            "AND u.availability = :availability")
    List<User> findDoctors(String specialty, String location, String availability);
}
