package com.sert.entidades;

public class Empresa {
	private long cnpj;
	private int ie;
	private String nome;
	private String nomeFant;
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;
	private String uf;
	private long contato;
	
	public Empresa(){}

	public Empresa(long cnpj, int ie, String nome, String nomeFant, String rua, int numero, String bairro,
			String cidade, String uf, long contato) {
		this.cnpj = cnpj;
		this.ie = ie;
		this.nome = nome;
		this.nomeFant = nomeFant;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.contato = contato;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public int getIe() {
		return ie;
	}

	public void setIe(int ie) {
		this.ie = ie;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFant() {
		return nomeFant;
	}

	public void setNomeFant(String nomeFant) {
		this.nomeFant = nomeFant;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public long getContato() {
		return contato;
	}

	public void setContato(long contato) {
		this.contato = contato;
	}
}
