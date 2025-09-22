package com.example.clinica.repository;

import com.example.clinica.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    Optional<Patient> findByCpf_Value(String cpf);
    boolean existsByCpf_Value(String cpf);
    
    Optional<Patient> findByEmail_Value(String email);
    boolean existsByEmail_Value(String email);
}