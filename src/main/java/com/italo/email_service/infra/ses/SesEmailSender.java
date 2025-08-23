package com.italo.email_service.infra.ses;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.italo.email_service.adapters.EmailSenderGateway;
import com.italo.email_service.core.exceptions.EmailServiceException;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.SesException;

@Log4j2
@Service
public class SesEmailSender implements EmailSenderGateway{

    private final SesClient sesClient;

    @Value("${from.email}")
    private String fromEmail;

    SesEmailSender(SesClient sesClient) {
        this.sesClient = sesClient;
    }

    @Override
    public void sendEmail(String email, String subject, String contentmessage) {

        Destination destination = Destination.builder().toAddresses(email).build();

        Content subjectContent = Content.builder().data(subject).build();
        Content messageContent = Content.builder().data(contentmessage).build();

        Body body = Body.builder().text(messageContent).build();

        Message message = Message.builder()
            .subject(subjectContent)
            .body(body)
            .build();
        
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
            .destination(destination)
            .message(message)
            .source(fromEmail) 
            .build();
        
        try {
            SendEmailResponse request = sesClient.sendEmail(sendEmailRequest);
            log.info("Email using SES API sent successfully ", request.sdkHttpResponse().statusCode());
        } catch (SesException e) {
            log.error("Failed to send email using SES API" + e.getClass().getSimpleName() + e.getMessage());
            throw new EmailServiceException("Failed to send email", e);
        }
            
    }

}
