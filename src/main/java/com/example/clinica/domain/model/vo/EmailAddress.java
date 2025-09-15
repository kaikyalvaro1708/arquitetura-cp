package com.example.clinica.domain.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class EmailAddress {
    @Email @NotBlank
    @Column(name = "email", nullable = false)
    private String value;

    public EmailAddress(String value) {
        this.value = value;
    }

//    protected Email() {} // JPA

    public String getValue() { return value; }
}
