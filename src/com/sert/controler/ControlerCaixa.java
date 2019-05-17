package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.CaixaDao;
import com.sert.dao.ICaixaDao;
import com.sert.entidades.Caixa;

public class ControlerCaixa {
	ICaixaDao caixaDao;

	public ControlerCaixa() throws ClassNotFoundException, SQLException, IOException {
		caixaDao = new CaixaDao();
	}

	public void lancamentoCaixa(Caixa caixa) throws SQLException {
		caixaDao.lancamentoCaixa(caixa);
	}

	public List<Caixa> historicoCaixa(String dataInicial, String dataFinal) throws SQLException {
		return caixaDao.historicoCaixa(dataInicial, dataFinal);
	}
}