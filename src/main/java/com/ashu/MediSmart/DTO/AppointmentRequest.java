package com.ashu.MediSmart.DTO;

import java.time.LocalDateTime;

public class AppointmentRequest {
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDate;

    // Getters & Setters
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
}
