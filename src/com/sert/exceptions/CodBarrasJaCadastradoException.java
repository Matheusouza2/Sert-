package com.sert.exceptions;

public class CodBarrasJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CodBarrasJaCadastradoException() {
		super("O código de barras informado já foi cadastrado em outra mercadoria");
	}
}