package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Caixa;

public interface ICaixaDao {
	
	public void lancamentoCaixa(Caixa caixa)throws SQLException;
	public List<Caixa> historicoCaixa(String dataInicial, String dataFinal)throws SQLException;
	public Caixa confereCaixa() throws SQLException;
}