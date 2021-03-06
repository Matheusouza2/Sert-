package com.sert.entidades;

public class MercadoriaNFe {

	private int id;
	private long codProd;
	private long codBarras;
	private String mercadoria;
	private String und;
	private float quantCompra;
	private float precoUn;
	private float precoTotal;
	private float valDesc;

	public MercadoriaNFe() {
	}

	public MercadoriaNFe(int id, long codProd, long codBarras, String mercadoria, String und, float quantCompra,
			float precoUn, float precoTotal, float valDesc) {
		super();
		this.id = id;
		this.codProd = codProd;
		this.codBarras = codBarras;
		this.mercadoria = mercadoria;
		this.und = und;
		this.quantCompra = quantCompra;
		this.precoUn = precoUn;
		this.precoTotal = precoTotal;
		this.valDesc = valDesc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCodProd() {
		return codProd;
	}

	public void setCodProd(long codProd) {
		this.codProd = codProd;
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

	public String getUnd() {
		return und;
	}

	public void setUnd(String und) {
		this.und = und;
	}

	public float getQuantCompra() {
		return quantCompra;
	}

	public void setQuantCompra(float quantCompra) {
		this.quantCompra = quantCompra;
	}

	public float getPrecoUn() {
		return precoUn;
	}

	public void setPrecoUn(float precoUn) {
		this.precoUn = precoUn;
	}

	public float getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}

	public float getValDesc() {
		return valDesc;
	}

	public void setValDesc(float valDesc) {
		this.valDesc = valDesc;
	}
}