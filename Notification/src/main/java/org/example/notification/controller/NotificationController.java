package org.example.notification.controller;

import org.example.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;

    private final KafkaTemplate kafkaTemplate;

    public NotificationController(NotificationService notificationService, KafkaTemplate kafkaTemplate) {
        this.notificationService = notificationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail() {
        return notificationService.sendEmail();
    }

//    @GetMapping
//    public ResponseEntity<String> sendEmail() {
//        return ;
//    }
}
