package com.example.clinica.domain.model;

import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.EmailAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Cpf cpf;

    @Embedded
    private EmailAddress email;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments = new ArrayList<>();
}
