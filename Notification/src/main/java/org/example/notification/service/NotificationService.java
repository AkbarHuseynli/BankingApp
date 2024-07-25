package org.example.notification.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailSenderService emailSenderService;

    public NotificationService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    public ResponseEntity<String> sendEmail() {
        emailSenderService.sendEmail("akbar.hnl27@gmail.com",
                "Prophylactic tests",
                "This is your email message by akbar");

        return ResponseEntity.ok("Email sent sucessfully");
    }
}
