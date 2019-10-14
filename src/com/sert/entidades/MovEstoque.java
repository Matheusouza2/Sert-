package com.sert.entidades;

import java.util.List;

public class MovEstoque {
	private int id;
	private boolean tipo;
	private Usuario usuario;
	private List<Mercadoria> mercadorias;
	private String data;

	public MovEstoque() {
	}

	public MovEstoque(int id, boolean tipo, Usuario usuario, List<Mercadoria> mercadorias, String data) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.usuario = usuario;
		this.mercadorias = mercadorias;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Mercadoria> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(List<Mercadoria> mercadorias) {
		this.mercadorias = mercadorias;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}