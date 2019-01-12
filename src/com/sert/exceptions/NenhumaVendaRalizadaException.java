package com.sert.exceptions;

public class NenhumaVendaRalizadaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NenhumaVendaRalizadaException() {
		super("Não foi encontrada nenhuma venda no sistema");
	}
}