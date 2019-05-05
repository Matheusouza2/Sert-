package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.MovEstoque;

public interface IDaoMovEstoque {
	public void movimentar(MovEstoque estoque) throws SQLException;
	public List<MovEstoque> listarMov() throws SQLException;
}