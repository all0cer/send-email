package com.italo.email_service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.EmailSenderUser;

@Service
public class EmailSenderService implements EmailSenderUser{

    @Autowired
    private final EmailSenderGateway emailSenderGateway;

    public EmailSenderService(@Qualifier("failoverEmailSender") EmailSenderGateway emailSenderGateway) {
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String email, String subject, String message) {
        this.emailSenderGateway.sendEmail(email, subject, message);
    }

}
