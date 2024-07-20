package com.ellenmateus.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ellenmateus.ecommerce.model.Email;
import com.ellenmateus.ecommerce.service.EmailService;

@RestController
@RequestMapping("email")
public class EmailController {

	private final EmailService emailService;

	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping
	public void sendEmail(@RequestBody Email email) {
		System.out.println(email);

	}

}
