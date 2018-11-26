package com.sert.entidades;

public class Caixa {

	private String dataCaixaAbertura;
	private float valorAbertura;
	private float totalCaixa;
	private int usuAbertura;
	private String usuAberturaNome;
	private String dataCaixaFechamento;
	private float valorFechamento;
	private int usuFechamento;
	private String usuFechamentoNome;
	private int situacao;
	
	public Caixa() {}
	
	public Caixa(String dataCaixaAbertura, float valorAbertura, float totalCaixa, int usuAbertura,
			String usuAberturaNome, String dataCaixaFechamento, float valorFechamento, int usuFechamento,
			String usuFechamentoNome, int situacao) {
		super();
		this.dataCaixaAbertura = dataCaixaAbertura;
		this.valorAbertura = valorAbertura;
		this.totalCaixa = totalCaixa;
		this.usuAbertura = usuAbertura;
		this.usuAberturaNome = usuAberturaNome;
		this.dataCaixaFechamento = dataCaixaFechamento;
		this.valorFechamento = valorFechamento;
		this.usuFechamento = usuFechamento;
		this.usuFechamentoNome = usuFechamentoNome;
		this.situacao = situacao;
	}

	public String getDataCaixaAbertura() {
		return dataCaixaAbertura;
	}

	public void setDataCaixaAbertura(String dataCaixaAbertura) {
		this.dataCaixaAbertura = dataCaixaAbertura;
	}

	public float getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(float valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public float getTotalCaixa() {
		return totalCaixa;
	}

	public void setTotalCaixa(float totalCaixa) {
		this.totalCaixa = totalCaixa;
	}

	public int getUsuAbertura() {
		return usuAbertura;
	}

	public void setUsuAbertura(int usuAbertura) {
		this.usuAbertura = usuAbertura;
	}

	public String getUsuAberturaNome() {
		return usuAberturaNome;
	}

	public void setUsuAberturaNome(String usuAberturaNome) {
		this.usuAberturaNome = usuAberturaNome;
	}

	public String getDataCaixaFechamento() {
		return dataCaixaFechamento;
	}

	public void setDataCaixaFechamento(String dataCaixaFechamento) {
		this.dataCaixaFechamento = dataCaixaFechamento;
	}

	public float getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(float valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

	public int getUsuFechamento() {
		return usuFechamento;
	}

	public void setUsuFechamento(int usuFechamento) {
		this.usuFechamento = usuFechamento;
	}

	public String getUsuFechamentoNome() {
		return usuFechamentoNome;
	}

	public void setUsuFechamentoNome(String usuFechamentoNome) {
		this.usuFechamentoNome = usuFechamentoNome;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
}