package com.example.clinica.repository;

import com.example.clinica.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByCrm(String crm);
    boolean existsByCrm(String crm);
}