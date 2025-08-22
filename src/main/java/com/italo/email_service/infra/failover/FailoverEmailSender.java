package com.italo.email_service.infra.failover;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.exceptions.EmailServiceException;
import com.italo.email_service.infra.grid.GridEmailSender;
import com.italo.email_service.infra.ses.SesEmailSender;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
@Primary
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
            log.error("Failed to send email using SES, trying with Grid" + e.getClass().getSimpleName() + e.getMessage());
        }
        try {
            gridEmailSender.sendEmail(email, subject, message);
        } catch (Exception ex) {
            log.fatal("Failed to send email using Grid" + ex.getClass().getSimpleName()  +  ex.getMessage());
            throw new EmailServiceException("Failed to send email using SES and Grid", ex);
        }
    }
}
