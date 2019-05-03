package com.sert.exceptions;

public class ClienteJaCadastradoException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClienteJaCadastradoException() {
		super("Já existe um cliente com esse CPF/CNPJ cadastrado no sistema!");
	}
}