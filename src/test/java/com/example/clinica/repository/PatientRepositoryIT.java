package com.example.clinica.repository;

import com.example.clinica.domain.model.Patient;
import com.example.clinica.domain.model.vo.Cpf;
import com.example.clinica.domain.model.vo.EmailAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
class PatientRepositoryIT {

    @Container
    static OracleContainer oracle = new OracleContainer("gvenzl/oracle-xe:21-slim-faststart")
            .withDatabaseName("XEPDB1")
            .withUsername("clinica")
            .withPassword("secret")
            .withReuse(true);

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", oracle::getJdbcUrl);
        r.add("spring.datasource.username", oracle::getUsername);
        r.add("spring.datasource.password", oracle::getPassword);
        r.add("spring.datasource.driver-class-name", () -> "oracle.jdbc.OracleDriver");
        r.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
        r.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.OracleDialect");
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
        assertTrue(repo.findByCpf_Value("12345678901").isPresent());
    }
}