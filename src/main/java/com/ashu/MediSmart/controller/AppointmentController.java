package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.DTO.AppointmentRequest;
import com.ashu.MediSmart.entity.Appointment;
import com.ashu.MediSmart.entity.Status;
import com.ashu.MediSmart.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Patient books appointment
    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    // Doctor approves/rejects appointment
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }

    // Admin views all appointments
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
