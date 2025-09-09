package com.ashu.MediSmart.controller;

import com.ashu.MediSmart.DTO.MedicalRecordRequest;
import com.ashu.MediSmart.entity.MedicalRecord;
import com.ashu.MediSmart.service.MedicalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    private final MedicalRecordService recordService;

    public MedicalRecordController(MedicalRecordService recordService) {
        this.recordService = recordService;
    }

    // Doctor creates medical record for an appointment
    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping("/create")
    public ResponseEntity<MedicalRecord> createRecord(@RequestBody MedicalRecordRequest request) {
        return ResponseEntity.ok(
                recordService.createRecord(request.getAppointmentId(), request.getNotes(), request.getPrescription())
        );
    }


    // Patient views own records
    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getPatientRecords(@PathVariable Long patientId) {
        return ResponseEntity.ok(recordService.getPatientRecords(patientId));
    }

    // Doctor views own records
    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecord>> getDoctorRecords(@PathVariable Long doctorId) {
        return ResponseEntity.ok(recordService.getDoctorRecords(doctorId));
    }

    // Admin views all records
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<MedicalRecord>> getAllRecords() {
        return ResponseEntity.ok(recordService.getAllRecords());
    }
}
