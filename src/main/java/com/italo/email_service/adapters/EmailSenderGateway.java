package com.italo.email_service.adapters;

public interface EmailSenderGateway {
    void sendEmail(String email, String subject, String message);
}
