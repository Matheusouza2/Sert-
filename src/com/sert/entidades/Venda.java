package com.sert.entidades;

import java.util.List;

import ca.odell.glazedlists.TextFilterator;

public class Venda implements TextFilterator<Venda> {

	// Cabeçalho da venda
	private int id;
	private int vendedorCad;
	private String vendedor;
	private int clienteCad;
	private String cliente;
	private String dataVenda;
	// Fim do cabeçalho

	// Mercadorias e seus totais
	private List<Mercadoria> mercadorias;
	private float valTotal;
	// Fim das mercadorias

	// Formas de pagamento
	private int dinheiro;
	private int cartao;
	private float valDInheiro;
	private float valCartao;
	private float acrescimo;
	private float desconto;
	// Fim formas de pagamento

	public Venda() {

	}

	public Venda(int id, int vendedorCad, String vendedor, int clienteCad, String cliente, String dataVenda,
			List<Mercadoria> mercadorias, float valTotal, int dinheiro, int cartao, float valDInheiro, float valCartao,
			float acrescimo, float desconto) {
		super();
		this.id = id;
		this.vendedorCad = vendedorCad;
		this.vendedor = vendedor;
		this.clienteCad = clienteCad;
		this.cliente = cliente;
		this.dataVenda = dataVenda;
		this.mercadorias = mercadorias;
		this.valTotal = valTotal;
		this.dinheiro = dinheiro;
		this.cartao = cartao;
		this.valDInheiro = valDInheiro;
		this.valCartao = valCartao;
		this.acrescimo = acrescimo;
		this.desconto = desconto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVendedorCad() {
		return vendedorCad;
	}

	public void setVendedorCad(int vendedorCad) {
		this.vendedorCad = vendedorCad;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public int getClienteCad() {
		return clienteCad;
	}

	public void setClienteCad(int clienteCad) {
		this.clienteCad = clienteCad;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
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

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getCartao() {
		return cartao;
	}

	public void setCartao(int cartao) {
		this.cartao = cartao;
	}

	public float getValDInheiro() {
		return valDInheiro;
	}

	public void setValDInheiro(float valDInheiro) {
		this.valDInheiro = valDInheiro;
	}

	public float getValCartao() {
		return valCartao;
	}

	public void setValCartao(float valCartao) {
		this.valCartao = valCartao;
	}

	public float getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(float acrescimo) {
		this.acrescimo = acrescimo;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

	@Override
	public void getFilterStrings(List<String> baseList, Venda vendaList) {
		baseList.add(String.valueOf(vendaList.getId()));
		baseList.add(vendaList.getDataVenda());
		baseList.add(String.valueOf(vendaList.getVendedor()));
		baseList.add(String.valueOf(vendaList.getCliente()));
	}
}