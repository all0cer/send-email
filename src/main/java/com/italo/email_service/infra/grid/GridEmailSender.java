package com.italo.email_service.infra.grid;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.exceptions.EmailServiceException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class GridEmailSender implements EmailSenderGateway {

    private final SendGrid sendGrid;

    public GridEmailSender(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    public void sendEmail(String email, String subject, String message) {
        Email from = new Email("italoalmeinasc@gmail.com");
        String contentsubject = subject;
        Email to = new Email(email);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, contentsubject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if(response.getStatusCode() != 202) {
                throw new EmailServiceException("Failed to send email using GRID API");
            }
            log.info("Email using GRID API sent successfully" + response.getStatusCode());
        } catch (IOException ex) {
            log.error("Failed to send email using GRID API" + ex.getClass().getSimpleName() + ex.getMessage());
            throw new EmailServiceException("Failed to send email using GRID API", ex);
        }
    }
    

}
