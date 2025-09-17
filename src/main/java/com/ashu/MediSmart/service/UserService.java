package com.ashu.MediSmart.service;

import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.entity.Role;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.repository.RoleRepository;
import com.ashu.MediSmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match!");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());

        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userDTO.getRole()));
        user.setRole(role);

        if ("DOCTOR".equalsIgnoreCase(userDTO.getRole())) {
            user.setSpecialty(userDTO.getSpecialty());
            user.setLocation(userDTO.getLocation());
            user.setYearsOfExperience(userDTO.getYearsOfExperience());
            user.setRating(userDTO.getRating());
            user.setAvailability(userDTO.getAvailability());
            user.setQualification(userDTO.getQualification());
            user.setBio(userDTO.getBio());
        }

        if ("PATIENT".equalsIgnoreCase(userDTO.getRole())) {
            if (userDTO.getAge() == null || userDTO.getAge() <= 0) {
                throw new RuntimeException("Age is required for patient registration");
            }
            user.setAge(userDTO.getAge());
        }

        return userRepository.save(user);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
