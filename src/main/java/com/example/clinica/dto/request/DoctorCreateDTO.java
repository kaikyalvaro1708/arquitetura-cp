package com.example.clinica.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DoctorCreateDTO(
        @NotBlank String name,
        @NotBlank String crm
) {}