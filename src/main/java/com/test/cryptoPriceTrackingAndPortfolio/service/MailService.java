package com.test.cryptoPriceTrackingAndPortfolio.service;

import com.test.cryptoPriceTrackingAndPortfolio.exception.MailSendException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendWelcomeMail(String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setSubject("Welcome to CryptoPrice Tracking And Portfolio!");
            message.setTo(to);
            message.setText("Thank you for signing up! We're excited to have you as part of our community.");

            mailSender.send(message);
        } catch (Exception e) {
            throw new MailSendException(e.getMessage());
        }
    }
}
