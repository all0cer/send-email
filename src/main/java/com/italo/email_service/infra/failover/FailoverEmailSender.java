package com.italo.email_service.infra.failover;

import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.exceptions.EmailServiceException;
import com.italo.email_service.infra.grid.GridEmailSender;
import com.italo.email_service.infra.ses.SesEmailSender;

@Service
public class FailoverEmailSender implements EmailSenderGateway {

    private final GridEmailSender gridEmailSender;
    private final SesEmailSender sesEmailSender;

    public FailoverEmailSender(GridEmailSender gridEmailSender, SesEmailSender sesEmailSender) {
        this.gridEmailSender = gridEmailSender;
        this.sesEmailSender = sesEmailSender;
    }

    @Override
    public void sendEmail(String email, String subject, String message) {
        try {
            sesEmailSender.sendEmail(email, subject, message);
        } catch (Exception e) {
            System.out.println("Failed to send email using SES, trying with Grid" + e.getMessage());
        }
        try {
            gridEmailSender.sendEmail(email, subject, message);
        } catch (Exception ex) {
            throw new EmailServiceException("Failed to send email using SES and Grid", ex);
        }
    }
}
