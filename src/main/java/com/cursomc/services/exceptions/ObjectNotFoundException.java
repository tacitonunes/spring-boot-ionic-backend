package com.cursomc.services.exceptions;

//1  - Extender Runtime Exception
public class ObjectNotFoundException extends RuntimeException {

	//2 - Criar a serialização
	private static final long serialVersionUID = 1L;
	
	//3 - Criar o construtor com a mensagem
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable causa) {
		super(msg, causa);
	}	

}
