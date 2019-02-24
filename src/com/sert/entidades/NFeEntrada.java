package com.sert.entidades;

import java.util.List;

public class NFeEntrada {

	// Identificação do destinatario
	private long cnpjDest;
	// Fonecedor
	private Fornecedor fornecedor;
	// Informações de idetificação da nota
	private int id;
	private String chave;
	private int numNota;
	private float valNota;
	// Mercadorias da nota
	private List<MercadoriaNFe> mercadorias;

	public NFeEntrada() {
	}

	public NFeEntrada(long cnpjDest, Fornecedor fornecedor, int id, String chave, int numNota,
			List<MercadoriaNFe> mercadorias, float valNota) {
		super();
		this.cnpjDest = cnpjDest;
		this.fornecedor = fornecedor;
		this.id = id;
		this.chave = chave;
		this.numNota = numNota;
		this.mercadorias = mercadorias;
	}

	public long getCnpjDest() {
		return cnpjDest;
	}

	public void setCnpjDest(long cnpjDest) {
		this.cnpjDest = cnpjDest;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public int getNumNota() {
		return numNota;
	}

	public void setNumNota(int numNota) {
		this.numNota = numNota;
	}

	public List<MercadoriaNFe> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(List<MercadoriaNFe> mercadorias) {
		this.mercadorias = mercadorias;
	}

	public float getValNota() {
		return valNota;
	}

	public void setValNota(float valNota) {
		this.valNota = valNota;
	}
}