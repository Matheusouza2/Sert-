package com.sert.entidades;

import java.util.List;

public class Orcamento {

	private int id;
	private Cliente cliente;
	private Usuario usuario;
	private List<Mercadoria> mercadorias;
	private float valTotal;
	private String status;
	private String data;

	public Orcamento() {

	}

	public Orcamento(int id, Cliente cliente, Usuario usuario, List<Mercadoria> mercadorias, float valTotal) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.usuario = usuario;
		this.mercadorias = mercadorias;
		this.valTotal = valTotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public float getValTotal() {
		return valTotal;
	}

	public void setValTotal(float valTotal) {
		this.valTotal = valTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}