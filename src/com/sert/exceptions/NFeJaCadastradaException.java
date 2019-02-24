package com.sert.exceptions;

public class NFeJaCadastradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NFeJaCadastradaException() {
		super("A nota fiscal já se encontra cadastrada no sistema");
	}
}