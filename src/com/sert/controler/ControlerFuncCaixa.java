package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.CaixaDao;
import com.sert.dao.ICaixaDao;
import com.sert.entidades.Caixa;

public class ControlerFuncCaixa {
	ICaixaDao caixaDao;

	public ControlerFuncCaixa() throws ClassNotFoundException, SQLException, IOException {
		caixaDao = new CaixaDao();
	}

	public void abrirCaixa(Caixa caixa) throws SQLException {
		caixaDao.abrirCaixa(caixa);
	}

	public List<Caixa> historicoCaixa() throws SQLException {
		return caixaDao.historicoCaixa();
	}

	public Caixa confereCaixa() throws SQLException {
		return caixaDao.confereCaixa();
	}
}