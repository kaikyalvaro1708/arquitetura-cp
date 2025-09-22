package com.example.clinica.service;

import com.example.clinica.domain.model.Appointment;
import com.example.clinica.domain.model.Doctor;
import com.example.clinica.domain.model.Patient;
import com.example.clinica.domain.model.StatusAppointment;
import com.example.clinica.dto.request.AppointmentCreateDTO;
import com.example.clinica.dto.response.AppointmentResponseDTO;
import com.example.clinica.repository.AppointmentRepository;
import com.example.clinica.repository.DoctorRepository;
import com.example.clinica.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repo;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Long create(AppointmentCreateDTO dto) {
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        
        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));

        boolean hasConflict = repo.existsByDoctorAndDateTimeBetween(
                doctor, dto.dateTime().minusMinutes(30), dto.dateTime().plusMinutes(30));
        
        if (hasConflict) {
            throw new IllegalArgumentException("Horário indisponível para este médico");
        }

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .dateTime(dto.dateTime())
                .status(StatusAppointment.AGENDADO)
                .build();

        return repo.save(appointment).getId();
    }

    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> list(Pageable pageable) {
        return repo.findAll(pageable)
                .map(this::toResponseDTO);
    }

    @Transactional
    public void cancel(Long id) {
        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        
        if (appointment.getStatus() == StatusAppointment.REALIZADO) {
            throw new IllegalArgumentException("Não é possível cancelar consulta já realizada");
        }
        
        appointment.setStatus(StatusAppointment.CANCELADO);
        repo.save(appointment);
    }

    @Transactional
    public void confirm(Long id) {
        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        
        if (appointment.getStatus() == StatusAppointment.CANCELADO) {
            throw new IllegalArgumentException("Não é possível confirmar consulta cancelada");
        }
        
        appointment.setStatus(StatusAppointment.CONFIRMADO);
        repo.save(appointment);
    }

    private AppointmentResponseDTO toResponseDTO(Appointment a) {
        return new AppointmentResponseDTO(
                a.getId(),
                a.getPatient().getId(),
                a.getPatient().getName(),
                a.getDoctor().getId(),
                a.getDoctor().getName(),
                a.getDateTime(),
                a.getStatus()
        );
    }
}