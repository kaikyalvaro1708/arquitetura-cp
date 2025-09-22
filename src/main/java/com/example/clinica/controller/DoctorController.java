package com.example.clinica.controller;

import com.example.clinica.dto.request.DoctorCreateDTO;
import com.example.clinica.dto.response.DoctorResponseDTO;
import com.example.clinica.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @Operation(summary = "Cria um médico")
    @ApiResponse(responseCode = "201", description = "Criado")
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody DoctorCreateDTO dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create("/doctors/" + id)).build();
    }

    @Operation(summary = "Lista médicos com paginação")
    @GetMapping
    public Page<DoctorResponseDTO> listar(@ParameterObject Pageable pageable) {
        return service.list(pageable);
    }

    @Operation(summary = "Busca médico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> findById(@PathVariable Long id) {
        DoctorResponseDTO doctor = service.findById(id);
        return ResponseEntity.ok(doctor);
    }
}