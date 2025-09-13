package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.service.DoctorRecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class DoctorRecommendationController {

    private final DoctorRecommendationService recommendationService;

    public DoctorRecommendationController(DoctorRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<UserDTO>> recommendDoctors(
            @RequestParam String specialty,
            @RequestParam String location,
            @RequestParam String availability
    ) {
        return ResponseEntity.ok(recommendationService.recommendDoctors(specialty, location, availability));
    }
}
