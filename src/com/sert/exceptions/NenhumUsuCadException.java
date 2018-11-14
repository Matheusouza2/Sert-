package com.sert.exceptions;

public class NenhumUsuCadException extends Exception {
	private static final long serialVersionUID = 1L;

	public NenhumUsuCadException() {
		super("Não há nenhum usuario cadastrado");
	}
}