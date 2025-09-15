package com.example.clinica.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PatientCreateDTO(
        @NotBlank String name,
        @NotBlank @Pattern(regexp="\\d{11}") String cpf,
        @NotBlank @Email String email
) {}
