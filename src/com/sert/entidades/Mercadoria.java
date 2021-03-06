package com.sert.entidades;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;

public class Mercadoria implements TextFilterator<Mercadoria> {

	private int id;
	private long codBarras;
	private String mercadoria;
	private float precoVenda;
	private String dataCadastro;
	private int usuCad;
	private String und;
	private float precoCompra;
	private String usuAlt;
	private String dataAlt;
	private float estoque;
	private String cadastrada;
	private String status;

	public Mercadoria() {
	}

	public Mercadoria(int id, long codBarras, String mercadoria, float precoVenda, String dataCadastro, int usuCad,
			String und, float precoCompra, String usuAlt, String dataAlt, float estoque) {
		super();
		this.id = id;
		this.codBarras = codBarras;
		this.mercadoria = mercadoria;
		this.precoVenda = precoVenda;
		this.dataCadastro = dataCadastro;
		this.usuCad = usuCad;
		this.und = und;
		this.precoCompra = precoCompra;
		this.usuAlt = usuAlt;
		this.dataAlt = dataAlt;
		this.estoque = estoque;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(long codBarras) {
		this.codBarras = codBarras;
	}

	public String getMercadoria() {
		return mercadoria;
	}

	public void setMercadoria(String mercadoria) {
		this.mercadoria = mercadoria;
	}

	public float getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getUsuCad() {
		return usuCad;
	}

	public void setUsuCad(int usuCad) {
		this.usuCad = usuCad;
	}

	public String getUnd() {
		return und;
	}

	public void setUnd(String und) {
		this.und = und;
	}

	public float getPrecoCompra() {
		return precoCompra;
	}

	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
	}

	public String getUsuAlt() {
		return usuAlt;
	}

	public void setUsuAlt(String usuAlt) {
		this.usuAlt = usuAlt;
	}

	public String getDataAlt() {
		return dataAlt;
	}

	public void setDataAlt(String dataAlt) {
		this.dataAlt = dataAlt;
	}

	public float getEstoque() {
		return estoque;
	}

	public void setEstoque(float estoque) {
		this.estoque = estoque;
	}

	public String getCadastrada() {
		return cadastrada;
	}

	public void setCadastrada(String cadastrada) {
		this.cadastrada = cadastrada;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public void getFilterStrings(List<String> baseList, Mercadoria mercList) {
		baseList.add(String.valueOf(mercList.getId()));
		baseList.add(String.valueOf(mercList.getCodBarras()));
		baseList.add(mercList.getMercadoria());
		baseList.add(String.valueOf(mercList.getPrecoVenda()));
		baseList.add(String.valueOf(mercList.getEstoque()));
	}
}