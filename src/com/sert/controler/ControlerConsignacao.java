package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.ConsignacaoDao;
import com.sert.entidades.Orcamento;

public class ControlerConsignacao {
	ConsignacaoDao consignacaoDao;
	
	
	public ControlerConsignacao() throws ClassNotFoundException, SQLException, IOException {
		consignacaoDao = new ConsignacaoDao();
	}
	
	public void lancarConsignacao(Orcamento consignacao) throws SQLException {
		consignacaoDao.cadastrar(consignacao);
	}
	
	public List<Orcamento> listarConsignacao() throws SQLException{
		return consignacaoDao.listar();
	}

	public int retornarId() throws SQLException {
		return consignacaoDao.recuperaId();
	}
	
	public Orcamento consultarConsignacao(int id) throws SQLException {
		return consignacaoDao.consultarConsignacao(id);
	}
	
	public void devolucaoConsig(Orcamento consignacao) throws SQLException {
		consignacaoDao.devolucao(consignacao);
	}
	
	public void faturarConsignacao(Orcamento consignacao) throws SQLException {
		consignacaoDao.faturarConsignacao(consignacao);
	}
}