package br.com.purplefire.crm.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.purplefire.crm.domain.Cliente;
import br.com.purplefire.crm.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
