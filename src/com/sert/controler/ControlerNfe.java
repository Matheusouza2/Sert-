package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.INFeDao;
import com.sert.dao.NFeDao;
import com.sert.entidades.NFeEntrada;

public class ControlerNfe {

	INFeDao dao;

	public ControlerNfe() throws ClassNotFoundException, SQLException, IOException {
		dao = new NFeDao();
	}

	public void cadastrarNfe(NFeEntrada entrada) throws SQLException{
		dao.cadastrar(entrada);
	}

	public List<NFeEntrada> listarNfe() {
		return null;
	}

	public void alterarNfe(NFeEntrada entrada) {

	}

	public void excluirNfe(int id) {

	}

	public NFeEntrada pesqNfe(String chave) throws SQLException {
		return dao.pesq(chave);
	}
	
	public int recuperaId() throws SQLException {
		return dao.recuperaId();
	}
}