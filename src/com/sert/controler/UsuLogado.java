package com.sert.controler;

public class UsuLogado {
	private static int id;
	private static String nome;
	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		UsuLogado.id = id;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		UsuLogado.nome = nome;
	}
}