package com.sert.exceptions;

public class VendaNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public VendaNaoEncontradaException() {
		super("A venda não foi encontrada");
	}
}