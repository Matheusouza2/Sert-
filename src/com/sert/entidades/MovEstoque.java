package com.sert.entidades;

public class MovEstoque {
	private int id;
	private String tipo;
	private int funcionario;
	private int mercadoria;
	private float quant;
	private String data;

	public MovEstoque() {
	}

	public MovEstoque(int id, String tipo, int funcionario, int mercadoria, float quant, String data) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.funcionario = funcionario;
		this.mercadoria = mercadoria;
		this.quant = quant;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(int funcionario) {
		this.funcionario = funcionario;
	}

	public int getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(int mercadoria) {
		this.mercadoria = mercadoria;
	}

	public float getQuant() {
		return quant;
	}

	public void setQuant(float quant) {
		this.quant = quant;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}