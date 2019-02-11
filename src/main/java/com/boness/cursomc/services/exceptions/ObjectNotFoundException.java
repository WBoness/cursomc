package com.boness.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException (String msg) {
		super (msg);
	}
	
	public ObjectNotFoundException (String msg, Throwable  cause) { //sobrecarga que envia para a superclasse RuntimeException a causa da mensagem
		super (msg, cause);
	}
}
