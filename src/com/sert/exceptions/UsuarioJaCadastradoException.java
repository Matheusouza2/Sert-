package com.sert.exceptions;

public class UsuarioJaCadastradoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UsuarioJaCadastradoException() {
		super("Já existe um usuario cadastrado com esse nome");
	}
}