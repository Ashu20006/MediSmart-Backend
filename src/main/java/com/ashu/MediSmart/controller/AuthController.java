package com.ashu.MediSmart.controller;


import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/register/patient")
    public ResponseEntity<User> registerPatient(@RequestBody UserDTO userDTO) {
        userDTO.setRole("PATIENT");
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<User> registerDoctor(@RequestBody UserDTO userDTO) {
        userDTO.setRole("DOCTOR");
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody UserDTO userDTO) {
        userDTO.setRole("ADMIN");
        return ResponseEntity.ok(userService.registerUser(userDTO));
    }



}
