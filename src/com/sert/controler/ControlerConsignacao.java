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
	
	public List<Orcamento> listarConsignacao(){
		return consignacaoDao.listar();
	}
	
	public void alterarConsignacao(Orcamento consignacao) {
		
	}
	
	public void excluirConsignacao(int id) {
		
	}
	
	public int retornarId() throws SQLException {
		return consignacaoDao.recuperaId();
	}
}