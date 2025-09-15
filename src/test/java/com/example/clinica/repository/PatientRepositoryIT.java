package com.example.clinica.repository;

import com.example.clinica.domain.model.Patient;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.EmailAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class PatientRepositoryIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("clinica")
            .withUsername("clinica")
            .withPassword("secret");

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", postgres::getJdbcUrl);
        r.add("spring.datasource.username", postgres::getUsername);
        r.add("spring.datasource.password", postgres::getPassword);
        r.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }

    @Autowired
    PatientRepository repo;

    @Test
    void deveSalvarEEncontrarPorCpf() {
        Patient p = Patient.builder()
                .name("Ana")
                .cpf(new Cpf("12345678901"))
                .email(new EmailAddress("ana@example.com"))
                .build();
        repo.save(p);
        assertTrue(repo.findByCpfValue("12345678901").isPresent());
    }
}
