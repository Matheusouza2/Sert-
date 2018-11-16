package com.sert.entidades;

import java.util.List;

public class NFeEntrada extends Fornecedor 	 {

	//Identificação do destinatario
	private long cnpjDest;
	// Informações de idetificação da nota
	private String chave;
	private int numNota;
	//Mercadorias da nota
	private List<MercadoriaNFe> mercadorias;
	
	public NFeEntrada() {
		super(0, 0, null, null, null, null, null, 0, null, null, 0);
	}

	public NFeEntrada(long cnpjDest, long cnpjForn, long ieForn, String nomeFant, String razSocial, String lograForn, String numrEndForn,
			String bairroForn, long cepForn, String cidadeForn, String ufForn, long foneForn, String chave, int numNota,
			int codProduto, long codBarras, String descProd, String und, float quant, float valUnit, float valTotal, List<MercadoriaNFe>mercadorias) {
		super(cnpjForn, ieForn, nomeFant, razSocial, lograForn, numrEndForn, bairroForn, cepForn, cidadeForn, ufForn,
				foneForn);
		this.cnpjDest = cnpjDest;
		this.chave = chave;
		this.numNota = numNota;
		this.mercadorias = mercadorias;
	}
	
	public long getCnpjDest() {
		return cnpjDest;
	}

	public void setCnpjDest(long cnpjDest) {
		this.cnpjDest = cnpjDest;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public int getNumNota() {
		return numNota;
	}

	public void setNumNota(int numNota) {
		this.numNota = numNota;
	}

	public List<MercadoriaNFe> getMercadorias() {
		return mercadorias;
	}

	public void setMercadorias(List<MercadoriaNFe> mercadorias) {
		this.mercadorias = mercadorias;
	}
}