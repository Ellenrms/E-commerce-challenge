package com.ellenmateus.ecommerce.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ellenmateus.ecommerce.model.Email;

@Service
public class EmailService {
	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmail(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(email.to());
        message.setSubject(email.subject());
        message.setText(email.body());

        mailSender.send(message);
	}

	public void sendPasswordResetEmail(String to, String token) {
        var message = new SimpleMailMessage();
        message.setFrom("noreply@email.com");
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, click the link below:\n"
                + "http://localhost:8080/auth/reset-password?token=" + token);
        mailSender.send(message);
    }
}
