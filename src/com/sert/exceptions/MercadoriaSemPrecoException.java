package com.sert.exceptions;

public class MercadoriaSemPrecoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MercadoriaSemPrecoException() {
		super("A mercadoria n�o tem pre�o de venda");
	}
}