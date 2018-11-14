package com.sert.exceptions;

public class MercadoriaSemEstoqueException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MercadoriaSemEstoqueException() {
		super("A mercadoria selecionada não tem estoque");
	}
}