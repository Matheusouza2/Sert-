package com.sert.exceptions;

public class EmpresaDivergenteDaNotaException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmpresaDivergenteDaNotaException() {
		super("O destinatario da nota tem um CNPJ diferente do seu, continuar mesmo assim ?");
	}
}