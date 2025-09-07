package com.ashu.MediSmart.service;

import com.ashu.MediSmart.DTO.AppointmentRequest;
import com.ashu.MediSmart.entity.Appointment;
import com.ashu.MediSmart.entity.Status;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.repository.AppointmentRepository;
import com.ashu.MediSmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    // Patient books appointment
    public Appointment bookAppointment(AppointmentRequest request) {
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        User doctor = userRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus(Status.PENDING);

        return appointmentRepository.save(appointment);
    }

    // Doctor updates status
    public Appointment updateStatus(Long appointmentId, Status status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    // Admin views all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
