package com.sert.exceptions;

public class UsuarioNaoCadastradoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoCadastradoException() {
		super("Usuario ou senha invalido");
	}

}