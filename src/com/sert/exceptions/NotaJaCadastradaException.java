package com.sert.exceptions;

public class NotaJaCadastradaException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotaJaCadastradaException() {
		super("Nota já cadastrada no sistema");
	}
}