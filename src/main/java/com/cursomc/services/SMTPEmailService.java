package com.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Pedido;

@Service
@Qualifier("smtp")
public class SMTPEmailService extends AbstractEmailService implements EmailService{
	
	private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailService.class);

	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido ped) {
		SimpleMailMessage sm = prepararMensagemSimplesDoPedido(ped);
		sendEmail(sm);
	}

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		mailSender.send(msg);
		LOG.info("Email enviado.");
		
	}

}
