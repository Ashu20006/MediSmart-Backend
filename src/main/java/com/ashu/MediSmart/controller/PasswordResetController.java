package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class PasswordResetController {

    private final PasswordResetService resetService;

    public PasswordResetController(PasswordResetService resetService) {
        this.resetService = resetService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        resetService.sendOtp(email);
        return ResponseEntity.ok("OTP sent to email!");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String otp,
                                                @RequestParam String newPassword) {
        resetService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok("Password reset successfully!");
    }
}
