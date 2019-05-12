package com.fatec.tcc.mensagem;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
	
}
