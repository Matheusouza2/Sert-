package com.sert.exceptions;

public class FornecedorJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	public FornecedorJaCadastradoException() {
		super("Esse fornecedor já está cadastrado no sistema");
	}
}