package com.ashu.MediSmart.repository;

import com.ashu.MediSmart.entity.MedicalRecord;
import com.ashu.MediSmart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // Find records by patient (via appointment)
    List<MedicalRecord> findByAppointment_Patient(User patient);

    // Find records by doctor (via appointment)
    List<MedicalRecord> findByAppointment_Doctor(User doctor);
}
