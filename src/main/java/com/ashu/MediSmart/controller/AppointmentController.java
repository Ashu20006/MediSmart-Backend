package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.DTO.AppointmentRequest;
import com.ashu.MediSmart.entity.Appointment;
import com.ashu.MediSmart.entity.Status;
import com.ashu.MediSmart.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    //  Only PATIENT can book appointment
    @PreAuthorize("hasAuthority('PATIENT')")
    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    //  Only DOCTOR can update status
    @PreAuthorize("hasAuthority('DOCTOR')")
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    //  Only ADMIN can view all appointments
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
