package com.sert.exceptions;

public class MercadoriaNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public MercadoriaNaoEncontradaException() {
		super("A mercadoria pesquisada não foi encontrada");
	}
}