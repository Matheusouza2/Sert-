package com.sert.entidades;

public class Caixa {
	
	private String dataCaixa;
	private float valorVenda;
	private float totalCaixa;

	public Caixa(String dataCaixa, float valorVenda, float totalCaixa) {
		this.dataCaixa = dataCaixa;
		this.valorVenda = valorVenda;
		this.totalCaixa = totalCaixa;
	}
	
	public String getDataCaixa() {
		return dataCaixa;
	}
	public void setDataCaixa(String dataCaixa) {
		this.dataCaixa = dataCaixa;
	}
	public float getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(float valorVenda) {
		this.valorVenda = valorVenda;
	}
	public float getTotalCaixa() {
		return totalCaixa;
	}
	public void setTotalCaixa(float totalCaixa) {
		this.totalCaixa = totalCaixa;
	}
}