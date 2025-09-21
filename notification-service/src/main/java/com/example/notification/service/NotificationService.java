package com.example.notification.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public String sendNotification(String orderId, String email, String phone) {
        // Simulación de envío de email y SMS
        String msg = String.format("Email sent to %s; SMS sent to %s; order=%s", email, phone, orderId);
        // Aquí podrías integrar proveedores reales (SMTP, Twilio, etc.)
        return msg;
    }
}
