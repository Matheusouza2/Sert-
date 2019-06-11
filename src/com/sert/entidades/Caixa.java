package com.sert.entidades;

public class Caixa {

	private String historico;
	private int cartao;
	private int dinheiro;
	private float valorDinheiro;
	private float valorCartao;
	private float valorCompra;
	private int duplicata;
	private int idOperador;
	private int idUsuario;
	private String dataOperacao;
	private boolean retirada;

	public Caixa() {
	}

	public Caixa(String historico, int cartao, int dinheiro, float valorDinheiro, float valorCartao, float valorCompra,
			int duplicata, int idOperador, int idUsuario, String dataOperacao, boolean retirada) {
		super();
		this.historico = historico;
		this.cartao = cartao;
		this.dinheiro = dinheiro;
		this.valorDinheiro = valorDinheiro;
		this.valorCartao = valorCartao;
		this.valorCompra = valorCompra;
		this.duplicata = duplicata;
		this.idOperador = idOperador;
		this.idUsuario = idUsuario;
		this.dataOperacao = dataOperacao;
		this.retirada = retirada;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public int getCartao() {
		return cartao;
	}

	public void setCartao(int cartao) {
		this.cartao = cartao;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public float getValorDinheiro() {
		return valorDinheiro;
	}

	public void setValorDinheiro(float valorDinheiro) {
		this.valorDinheiro = valorDinheiro;
	}

	public float getValorCartao() {
		return valorCartao;
	}

	public void setValorCartao(float valorCartao) {
		this.valorCartao = valorCartao;
	}

	public int getDuplicata() {
		return duplicata;
	}

	public void setDuplicata(int duplicata) {
		this.duplicata = duplicata;
	}

	public int getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(int idOperador) {
		this.idOperador = idOperador;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(String dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public boolean isRetirada() {
		return retirada;
	}

	public void setRetirada(boolean retirada) {
		this.retirada = retirada;
	}

	public void setValorCompra(float valorCompra) {
		this.valorCompra = valorCompra;
	}

	public float getValorCompra() {
		return valorCompra;
	}
}