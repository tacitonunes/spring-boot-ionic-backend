package com.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido ped);

	void sendEmail(SimpleMailMessage msg);
}