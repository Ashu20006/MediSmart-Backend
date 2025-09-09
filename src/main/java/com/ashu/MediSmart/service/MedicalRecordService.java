package com.ashu.MediSmart.service;

import com.ashu.MediSmart.entity.Appointment;
import com.ashu.MediSmart.entity.MedicalRecord;
import com.ashu.MediSmart.entity.User;
import com.ashu.MediSmart.repository.AppointmentRepository;
import com.ashu.MediSmart.repository.MedicalRecordRepository;
import com.ashu.MediSmart.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository recordRepository;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;

    public MedicalRecordService(MedicalRecordRepository recordRepository,
                                UserRepository userRepository,
                                AppointmentRepository appointmentRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
    }

    // Doctor creates record linked with appointment
    public MedicalRecord createRecord(Long appointmentId, String notes, String prescription) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        User doctor = appointment.getDoctor();
        User patient = appointment.getPatient();

        if (doctor == null || patient == null) {
            throw new RuntimeException("Doctor or Patient missing in appointment");
        }

        MedicalRecord record = new MedicalRecord();
        record.setAppointment(appointment);
        record.setNotes(notes);
        record.setPrescription(prescription);

        return recordRepository.save(record);
    }

    // Patient views own records
    public List<MedicalRecord> getPatientRecords(Long patientId) {
        User patient = userRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return recordRepository.findByAppointment_Patient(patient);
    }

    // Doctor views records of their patients
    public List<MedicalRecord> getDoctorRecords(Long doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        return recordRepository.findByAppointment_Doctor(doctor);
    }

    // Admin views all records
    public List<MedicalRecord> getAllRecords() {
        return recordRepository.findAll();
    }
}
