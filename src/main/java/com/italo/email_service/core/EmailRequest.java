package com.italo.email_service.core;

import java.util.Objects;

public record EmailRequest(String to, String subject, String message) {
    public EmailRequest {
        Objects.requireNonNull(to, "O campo 'to' não pode ser nulo");
        Objects.requireNonNull(subject, "O campo 'subject' não pode ser nulo");
        Objects.requireNonNull(message, "O campo 'message' não pode ser nulo");

        if (to.isBlank()) {
            throw new IllegalArgumentException("O campo 'to' não pode estar vazio");
        }

        if (!to.contains("@")) {
            throw new IllegalArgumentException("O campo 'to' precisa ser um email válido");
        }

        if (subject.isBlank()) {
            throw new IllegalArgumentException("O campo 'subject' não pode estar vazio");
        }

        if (message.isBlank()) {
            throw new IllegalArgumentException("O campo 'message' não pode estar vazio");
        }
    }
}
