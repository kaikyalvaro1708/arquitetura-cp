package com.example.clinica.controller;

import com.example.clinica.dto.request.PatientCreateDTO;
import com.example.clinica.dto.response.PatientResponseDTO;
import com.example.clinica.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;

    @Operation(summary = "Cria um paciente")
    @ApiResponse(responseCode = "201", description = "Criado")
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody PatientCreateDTO dto) {
        Long id = service.criar(dto);
        return ResponseEntity.created(URI.create("/patients/" + id)).build();
    }

    @Operation(summary = "Lista pacientes com paginação")
    @GetMapping
    public Page<PatientResponseDTO> listar(@ParameterObject Pageable pageable) {
        return service.listar(pageable);
    }
}
