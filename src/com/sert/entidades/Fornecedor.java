package com.sert.entidades;

public class Fornecedor {

	private long cnpjForn;
	private long ieForn;
	private String nomeFant;
	private String razSocial;
	private String lograForn;
	private String numrEndForn;
	private String bairroForn;
	private long cepForn;
	private String cidadeForn;
	private String ufForn;
	private long foneForn;

	public Fornecedor(long cnpjForn, long ieForn, String nomeFant, String razSocial, String lograForn, String numrEndForn,
			String bairroForn, long cepForn, String cidadeForn, String ufForn, long foneForn) {
		super();
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
	}

	public long getCnpjForn() {
		return cnpjForn;
	}

	public void setCnpjForn(long cnpjForn) {
		this.cnpjForn = cnpjForn;
	}

	public long getIeForn() {
		return ieForn;
	}

	public void setIeForn(long ieForn) {
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

	public String getNumrEndForn() {
		return numrEndForn;
	}

	public void setNumrEndForn(String numrEndForn) {
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
}