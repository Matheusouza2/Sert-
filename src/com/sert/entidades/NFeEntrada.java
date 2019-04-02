package com.sert.entidades;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;

public class NFeEntrada implements TextFilterator<NFeEntrada> {

	// Identificação do destinatario
	private long cnpjDest;
	// Fonecedor
	private Fornecedor fornecedor;
	// Informações de idetificação da nota
	private int id;
	private String chave;
	private int numNota;
	private float valNota;
	private String dataEntrada;
	// Mercadorias da nota
	private List<MercadoriaNFe> mercadorias;

	public NFeEntrada() {
	}

	public NFeEntrada(long cnpjDest, Fornecedor fornecedor, int id, String chave, int numNota,
			List<MercadoriaNFe> mercadorias, float valNota, String dataEntrada) {
		super();
		this.cnpjDest = cnpjDest;
		this.fornecedor = fornecedor;
		this.id = id;
		this.chave = chave;
		this.numNota = numNota;
		this.mercadorias = mercadorias;
		this.valNota = valNota;
		this.dataEntrada = dataEntrada;
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

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@Override
	public void getFilterStrings(List<String> baseList, NFeEntrada nfeList) {
		baseList.add(String.valueOf(nfeList.getNumNota()));
		baseList.add(nfeList.getChave());
		baseList.add(nfeList.getFornecedor().getRazSocial());
		baseList.add(nfeList.getDataEntrada());

	}
}