package com.ashu.MediSmart.service;

import com.ashu.MediSmart.DTO.UserDTO;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorRecommendationService {

    private final UserRepository userRepository;

    public DoctorRecommendationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> recommendDoctors(String specialty, String location, String availability) {
        List<User> doctors = userRepository.findDoctors(specialty, location, availability);

        return doctors.stream()
                .map(doc -> new UserDTO(
                        doc.getId(),
                        doc.getName(),
                        doc.getSpecialty(),
                        doc.getLocation(),
                        doc.getYearsOfExperience(),
                        doc.getRating(),
                        doc.getAvailability()
                ))
                .collect(Collectors.toList());
    }
    public List<String> getUniqueLocations() {
        return userRepository.findDistinctDoctorLocations();
    }


}
