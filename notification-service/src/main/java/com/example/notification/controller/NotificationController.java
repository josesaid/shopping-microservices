package com.example.notification.controller;

import com.example.notification.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService svc;

    public NotificationController(NotificationService svc) {
        this.svc = svc;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String orderId,
                                       @RequestParam String email,
                                       @RequestParam String phone) {
        return ResponseEntity.ok(svc.sendNotification(orderId, email, phone));
    }
}
