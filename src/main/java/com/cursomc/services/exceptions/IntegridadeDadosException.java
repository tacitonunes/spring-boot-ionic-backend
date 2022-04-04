package com.cursomc.services.exceptions;

//1  - Extender Runtime Exception
public class IntegridadeDadosException extends RuntimeException {

	//2 - Criar a serialização
	private static final long serialVersionUID = 1L;
	
	//3 - Criar o construtor com a mensagem
	public IntegridadeDadosException(String msg) {
		super(msg);
	}
	
	public IntegridadeDadosException(String msg, Throwable causa) {
		super(msg, causa);
	}	

}
