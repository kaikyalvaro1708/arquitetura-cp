package com.example.clinica.service;

import com.example.clinica.domain.model.Doctor;
import com.example.clinica.dto.request.DoctorCreateDTO;
import com.example.clinica.dto.response.DoctorResponseDTO;
import com.example.clinica.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repo;

    @Transactional
    public Long create(DoctorCreateDTO dto) {
        repo.findByCrm(dto.crm()).ifPresent(d -> {
            throw new IllegalArgumentException("CRM já cadastrado");
        });
        
        Doctor doctor = Doctor.builder()
                .name(dto.name())
                .crm(dto.crm())
                .build();
        
        return repo.save(doctor).getId();
    }

    @Transactional(readOnly = true)
    public Page<DoctorResponseDTO> list(Pageable pageable) {
        return repo.findAll(pageable)
                .map(d -> new DoctorResponseDTO(
                        d.getId(), d.getName(), d.getCrm()));
    }
    
    @Transactional(readOnly = true)
    public DoctorResponseDTO findById(Long id) {
        Doctor doctor = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        
        return new DoctorResponseDTO(doctor.getId(), doctor.getName(), doctor.getCrm());
    }
}
