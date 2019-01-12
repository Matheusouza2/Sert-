package com.sert.exceptions;

public class NenhumaMercadoriaCadastradaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NenhumaMercadoriaCadastradaException() {
		super("Nenhuma mercadoria encontrada");
	}
}