package com.sert.entidades;

public class PermissoesFunc {
	private int idFunc;
	private boolean cadCliente;
	private boolean listCliente;
	private boolean altCliente;
	private boolean exclCliente;
	private boolean listFunc;
	private boolean cadFunc;
	private boolean altFunc;
	private boolean exclFunc;
	private boolean cadProd;
	private boolean listProd;
	private boolean altProd;
	private boolean exclProduto;
	private boolean cadNota;
	private boolean listNota;
	private boolean movEstoque;
	private boolean cadOrcamento;
	private boolean lancConsignacao;
	private boolean verConsig;
	private boolean dashEstoque;
	private boolean dashVenda;
	private boolean dashCaixa;
	private boolean dashCompra;
	private boolean lancarVendas;
	
	public PermissoesFunc() {
	
	}

	public PermissoesFunc(int idFunc, boolean cadCliente, boolean listCliente, boolean altCliente, boolean exclCliente,
			boolean listFunc, boolean cadFunc, boolean altFunc, boolean exclFunc, boolean cadProd, boolean listProd,
			boolean altProd, boolean exclProduto, boolean cadNota, boolean listNota, boolean movEstoque,
			boolean cadOrcamento, boolean lancConsignacao, boolean verConsig, boolean dashEstoque, boolean dashVenda,
			boolean dashCaixa, boolean dashCompra, boolean lancarVendas) {
		super();
		this.idFunc = idFunc;
		this.cadCliente = cadCliente;
		this.listCliente = listCliente;
		this.altCliente = altCliente;
		this.exclCliente = exclCliente;
		this.listFunc = listFunc;
		this.cadFunc = cadFunc;
		this.altFunc = altFunc;
		this.exclFunc = exclFunc;
		this.cadProd = cadProd;
		this.listProd = listProd;
		this.altProd = altProd;
		this.exclProduto = exclProduto;
		this.cadNota = cadNota;
		this.listNota = listNota;
		this.movEstoque = movEstoque;
		this.cadOrcamento = cadOrcamento;
		this.lancConsignacao = lancConsignacao;
		this.verConsig = verConsig;
		this.dashEstoque = dashEstoque;
		this.dashVenda = dashVenda;
		this.dashCaixa = dashCaixa;
		this.dashCompra = dashCompra;
		this.lancarVendas = lancarVendas;
	}

	public int getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(int idFunc) {
		this.idFunc = idFunc;
	}

	public boolean isCadCliente() {
		return cadCliente;
	}

	public void setCadCliente(boolean cadCliente) {
		this.cadCliente = cadCliente;
	}

	public boolean isListCliente() {
		return listCliente;
	}

	public void setListCliente(boolean listCliente) {
		this.listCliente = listCliente;
	}

	public boolean isAltCliente() {
		return altCliente;
	}

	public void setAltCliente(boolean altCliente) {
		this.altCliente = altCliente;
	}

	public boolean isExclCliente() {
		return exclCliente;
	}

	public void setExclCliente(boolean exclCliente) {
		this.exclCliente = exclCliente;
	}

	public boolean isListFunc() {
		return listFunc;
	}

	public void setListFunc(boolean listFunc) {
		this.listFunc = listFunc;
	}

	public boolean isCadFunc() {
		return cadFunc;
	}

	public void setCadFunc(boolean cadFunc) {
		this.cadFunc = cadFunc;
	}

	public boolean isAltFunc() {
		return altFunc;
	}

	public void setAltFunc(boolean altFunc) {
		this.altFunc = altFunc;
	}

	public boolean isExclFunc() {
		return exclFunc;
	}

	public void setExclFunc(boolean exclFunc) {
		this.exclFunc = exclFunc;
	}

	public boolean isCadProd() {
		return cadProd;
	}

	public void setCadProd(boolean cadProd) {
		this.cadProd = cadProd;
	}

	public boolean isListProd() {
		return listProd;
	}

	public void setListProd(boolean listProd) {
		this.listProd = listProd;
	}

	public boolean isAltProd() {
		return altProd;
	}

	public void setAltProd(boolean altProd) {
		this.altProd = altProd;
	}

	public boolean isExclProduto() {
		return exclProduto;
	}

	public void setExclProduto(boolean exclProduto) {
		this.exclProduto = exclProduto;
	}

	public boolean isCadNota() {
		return cadNota;
	}

	public void setCadNota(boolean cadNota) {
		this.cadNota = cadNota;
	}

	public boolean isListNota() {
		return listNota;
	}

	public void setListNota(boolean listNota) {
		this.listNota = listNota;
	}

	public boolean isMovEstoque() {
		return movEstoque;
	}

	public void setMovEstoque(boolean movEstoque) {
		this.movEstoque = movEstoque;
	}

	public boolean isCadOrcamento() {
		return cadOrcamento;
	}

	public void setCadOrcamento(boolean cadOrcamento) {
		this.cadOrcamento = cadOrcamento;
	}

	public boolean isLancConsignacao() {
		return lancConsignacao;
	}

	public void setLancConsignacao(boolean lancConsignacao) {
		this.lancConsignacao = lancConsignacao;
	}

	public boolean isVerConsig() {
		return verConsig;
	}

	public void setVerConsig(boolean verConsig) {
		this.verConsig = verConsig;
	}

	public boolean isDashEstoque() {
		return dashEstoque;
	}

	public void setDashEstoque(boolean dashEstoque) {
		this.dashEstoque = dashEstoque;
	}

	public boolean isDashVenda() {
		return dashVenda;
	}

	public void setDashVenda(boolean dashVenda) {
		this.dashVenda = dashVenda;
	}

	public boolean isDashCaixa() {
		return dashCaixa;
	}

	public void setDashCaixa(boolean dashCaixa) {
		this.dashCaixa = dashCaixa;
	}

	public boolean isDashCompra() {
		return dashCompra;
	}

	public void setDashCompra(boolean dashCompra) {
		this.dashCompra = dashCompra;
	}
	
	public boolean isLancarVendas() {
		return lancarVendas;
	}
	
	public void setLancarVendas(boolean lancarVendas) {
		this.lancarVendas = lancarVendas;
	}
}