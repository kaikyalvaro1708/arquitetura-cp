package com.example.clinica.controller;

import com.example.clinica.dto.request.AppointmentCreateDTO;
import com.example.clinica.dto.response.AppointmentResponseDTO;
import com.example.clinica.service.AppointmentService;
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
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @Operation(summary = "Agenda uma consulta")
    @ApiResponse(responseCode = "201", description = "Consulta agendada")
    @PostMapping
    public ResponseEntity<Void> schedule(@Valid @RequestBody AppointmentCreateDTO dto) {
        Long id = service.create(dto);
        return ResponseEntity.created(URI.create("/appointments/" + id)).build();
    }

    @Operation(summary = "Lista consultas com paginação")
    @GetMapping
    public Page<AppointmentResponseDTO> list(@ParameterObject Pageable pageable) {
        return service.list(pageable);
    }

    @Operation(summary = "Cancela uma consulta")
    @ApiResponse(responseCode = "204", description = "Consulta cancelada")
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Confirma uma consulta")
    @ApiResponse(responseCode = "204", description = "Consulta confirmada")
    @PutMapping("/{id}/confirm")
    public ResponseEntity<Void> confirm(@PathVariable Long id) {
        service.cancel(id);
        return ResponseEntity.noContent().build();
    }
}