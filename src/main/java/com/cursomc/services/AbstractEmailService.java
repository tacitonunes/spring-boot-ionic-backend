package com.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.cursomc.domain.Pedido;

public abstract class AbstractEmailService {

	@Value("${default.sender}")
	private String sender;

	protected SimpleMailMessage prepararMensagemSimplesDoPedido(Pedido ped) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(ped.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Seu Pedido nº " + ped.getId() + " foi confirmado!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(ped.toString());
		return sm;
	}

}
