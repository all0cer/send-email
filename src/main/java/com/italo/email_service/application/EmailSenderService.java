package com.italo.email_service.application;

import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.EmailSenderUser;

@Service
public class EmailSenderService implements EmailSenderUser{

    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String email, String subject, String message) {
        this.emailSenderGateway.sendEmail(email, subject, message);
    }

}
