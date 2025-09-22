package com.example.clinica.dto.response;

import com.example.clinica.domain.model.StatusAppointment;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        LocalDateTime dateTime,
        StatusAppointment status
) {}