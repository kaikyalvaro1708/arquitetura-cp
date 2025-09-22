package com.example.clinica.repository;

import com.example.clinica.domain.model.Appointment;
import com.example.clinica.domain.model.Doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    Page<Appointment> findByDoctorId(Long doctorId, Pageable pageable);
    
    boolean existsByDoctorAndDateTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    
    boolean existsByDoctorIdAndDateTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
    
    boolean existsByDoctorAndDateTimeGreaterThanEqualAndDateTimeLessThanEqual(
            Doctor doctor, LocalDateTime start, LocalDateTime end);
}