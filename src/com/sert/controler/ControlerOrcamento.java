package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;

import com.sert.dao.IOrcamentoDAO;
import com.sert.dao.OrcamentoDao;
import com.sert.entidades.Orcamento;

public class ControlerOrcamento {
	IOrcamentoDAO orcamentoDao;
	
	public ControlerOrcamento() throws ClassNotFoundException, SQLException, IOException {
		orcamentoDao = new OrcamentoDao();
	}
	
	public void salvarOrcamento(Orcamento orcamento) throws SQLException {
		orcamentoDao.cadastrar(orcamento);
	}
	
	public int confereId() throws SQLException {
		return orcamentoDao.consultaIdOrcamento();
	}
}