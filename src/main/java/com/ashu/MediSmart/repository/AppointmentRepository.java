package com.ashu.MediSmart.repository;

import com.ashu.MediSmart.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
