package com.sert.entidades;

public class Fornecedor {

	private int id;
	private String cnpjForn;
	private String ieForn;
	private String nomeFant;
	private String razSocial;
	private String lograForn;
	private int numrEndForn;
	private String bairroForn;
	private long cepForn;
	private String cidadeForn;
	private String ufForn;
	private long foneForn;
	private long celularForn;

	public Fornecedor() {
	}

	public Fornecedor(int id, String cnpjForn, String ieForn, String nomeFant, String razSocial, String lograForn,
			int numrEndForn, String bairroForn, long cepForn, String cidadeForn, String ufForn, long foneForn,
			long celularForn) {
		super();
		this.id = id;
		this.cnpjForn = cnpjForn;
		this.ieForn = ieForn;
		this.nomeFant = nomeFant;
		this.razSocial = razSocial;
		this.lograForn = lograForn;
		this.numrEndForn = numrEndForn;
		this.bairroForn = bairroForn;
		this.cepForn = cepForn;
		this.cidadeForn = cidadeForn;
		this.ufForn = ufForn;
		this.foneForn = foneForn;
		this.celularForn = celularForn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnpjForn() {
		return cnpjForn;
	}

	public void setCnpjForn(String cnpjForn) {
		this.cnpjForn = cnpjForn;
	}

	public String getIeForn() {
		return ieForn;
	}

	public void setIeForn(String ieForn) {
		this.ieForn = ieForn;
	}

	public String getNomeFant() {
		return nomeFant;
	}

	public void setNomeFant(String nomeFant) {
		this.nomeFant = nomeFant;
	}

	public String getRazSocial() {
		return razSocial;
	}

	public void setRazSocial(String razSocial) {
		this.razSocial = razSocial;
	}

	public String getLograForn() {
		return lograForn;
	}

	public void setLograForn(String lograForn) {
		this.lograForn = lograForn;
	}

	public int getNumrEndForn() {
		return numrEndForn;
	}

	public void setNumrEndForn(int numrEndForn) {
		this.numrEndForn = numrEndForn;
	}

	public String getBairroForn() {
		return bairroForn;
	}

	public void setBairroForn(String bairroForn) {
		this.bairroForn = bairroForn;
	}

	public long getCepForn() {
		return cepForn;
	}

	public void setCepForn(long cepForn) {
		this.cepForn = cepForn;
	}

	public String getCidadeForn() {
		return cidadeForn;
	}

	public void setCidadeForn(String cidadeForn) {
		this.cidadeForn = cidadeForn;
	}

	public String getUfForn() {
		return ufForn;
	}

	public void setUfForn(String ufForn) {
		this.ufForn = ufForn;
	}

	public long getFoneForn() {
		return foneForn;
	}

	public void setFoneForn(long foneForn) {
		this.foneForn = foneForn;
	}

	public long getCelularForn() {
		return celularForn;
	}

	public void setCelularForn(long celularForn) {
		this.celularForn = celularForn;
	}

}