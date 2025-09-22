package com.example.clinica.service;

import com.example.clinica.domain.model.Patient;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.EmailAddress;
import com.example.clinica.dto.request.PatientCreateDTO;
import com.example.clinica.dto.response.PatientResponseDTO;
import com.example.clinica.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class PatientService {

    private final PatientRepository repo;

    @Transactional
    public Long create(PatientCreateDTO dto) {
        repo.findByCpf_Value(dto.cpf()).ifPresent(p -> {
            throw new IllegalArgumentException("CPF já cadastrado");
        });
        Patient patient = Patient.builder()
                .name(dto.name())
                .cpf(new Cpf(dto.cpf()))
                .email(new EmailAddress(dto.email()))
                .build();
        return repo.save(patient).getId();
    }

    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> list(Pageable pageable) {
        return repo.findAll(pageable)
                .map(p -> new PatientResponseDTO(
                        p.getId(), p.getName(),
                        p.getCpf().getValue(), p.getEmail().getValue()));
    }
}
