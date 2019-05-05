package com.sert.entidades;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;

public class DuplicataCliente implements TextFilterator<DuplicataCliente> {

	private int id;
	private int numParcela;
	private Cliente cliente;
	private Venda venda;
	private float valor;
	private String situacao;
	private Usuario usuBaixa;
	private String dataBaixa;
	private String dataVencimento;
	private float valorBaixa;

	public DuplicataCliente() {

	}

	public DuplicataCliente(int id, int numParcela, Cliente cliente, Venda venda, float valor, String situacao,
			Usuario usuBaixa, String dataBaixa, String dataVencimento, float valorBaixa) {
		super();
		this.id = id;
		this.numParcela = numParcela;
		this.cliente = cliente;
		this.venda = venda;
		this.valor = valor;
		this.situacao = situacao;
		this.usuBaixa = usuBaixa;
		this.dataBaixa = dataBaixa;
		this.dataVencimento = dataVencimento;
		this.valorBaixa = valorBaixa;
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Usuario getUsuBaixa() {
		return usuBaixa;
	}

	public void setUsuBaixa(Usuario usuBaixa) {
		this.usuBaixa = usuBaixa;
	}

	public String getDataBaixa() {
		return dataBaixa;
	}

	public void setDataBaixa(String dataBaixa) {
		this.dataBaixa = dataBaixa;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public int getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(int numParcela) {
		this.numParcela = numParcela;
	}

	public float getValorBaixa() {
		return valorBaixa;
	}

	public void setValorBaixa(float valorBaixa) {
		this.valorBaixa = valorBaixa;
	}

	@Override
	public void getFilterStrings(List<String> baseList, DuplicataCliente duplicata) {
		baseList.add(duplicata.getCliente().getNome());
		baseList.add(duplicata.dataVencimento);
		baseList.add(duplicata.situacao);
	}
}