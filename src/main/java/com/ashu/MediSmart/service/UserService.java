package com.ashu.MediSmart.service;

import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.entity.Role;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.repository.RoleRepository;
import com.ashu.MediSmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // hash password

        // fetch Role entity by role name
        Role role = roleRepository.findByName(userDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + userDTO.getRole()));

        user.setRole(role);

        return userRepository.save(user);
    }
}
