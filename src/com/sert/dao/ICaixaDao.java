package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Caixa;

public interface ICaixaDao {
	
	public void abrirCaixa(Caixa caixa)throws SQLException;
	public void fecharCaixa(Caixa caixa) throws SQLException;
	public List<Caixa> historicoCaixa()throws SQLException;
	public Caixa confereCaixa() throws SQLException;
}