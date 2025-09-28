package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.service.DoctorRecommendationService;
import org.springframework.http.HttpStatus;
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
            @RequestParam String availability) {

        List<UserDTO> doctors = recommendationService.recommendDoctors(specialty, location, availability);

        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/locations")
    public ResponseEntity<?> getDoctorLocations() {
        try {
            List<String> locations = recommendationService.getUniqueLocations();
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching locations: " + e.getMessage());
        }
    }

}
