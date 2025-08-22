package com.italo.email_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.italo.email_service.application.EmailSenderService;
import com.italo.email_service.core.EmailRequest;
import com.italo.email_service.core.exceptions.EmailServiceException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/email")
public class EmailSenderController {

    @Autowired
    private final EmailSenderService emailSenderService;

    public EmailSenderController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            this.emailSenderService.sendEmail(request.to(), request.subject(), request.message());
            log.info("Email sent successfully");
            return ResponseEntity.ok("Email sent successfully");
        } catch (EmailServiceException e) {
            log.error("Fail to send email: " + e.getClass().getSimpleName() + e.getMessage());
            return ResponseEntity.internalServerError().body("Fail to send email: " + e.getMessage());
        }
    }
}
