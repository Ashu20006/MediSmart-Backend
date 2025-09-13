package com.ashu.MediSmart.controller;


import com.ashu.MediSmart.DTO.LoginRequest;
import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.service.AuthService;
import com.ashu.MediSmart.service.TokenBlacklistService;
import com.ashu.MediSmart.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    private final TokenBlacklistService tokenBlacklistService;

    public AuthController(TokenBlacklistService tokenBlacklistService) {
        this.tokenBlacklistService = tokenBlacklistService;
    }

    // ✅ Logout API
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);
            return ResponseEntity.ok("User logged out successfully!");
        }

        return ResponseEntity.badRequest().body("No token found in request!");
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest request) {
//        return authService.login(request);
//    }

    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
