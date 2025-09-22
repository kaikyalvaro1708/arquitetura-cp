package com.example.clinica.domain.model;

import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.EmailAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Cpf cpf;

    @Embedded
    private EmailAddress email;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    public String getCpfValue() {
        return cpf != null ? cpf.getValue() : null;
    }

    public String getEmailValue() {
        return email != null ? email.getValue() : null;
    }
}