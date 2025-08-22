package com.italo.email_service.infra.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.italo.email_service.application.EmailSenderService;
import com.italo.email_service.core.EmailRequest;

@Component
public class EmailConsumer {
    private final EmailSenderService emailSenderService;

    public EmailConsumer(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consume(@Payload EmailRequest emailRequest) {
        emailSenderService.sendEmail(emailRequest.to(), emailRequest.subject(), emailRequest.message());
    }
}
