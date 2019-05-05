package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.DuplicataCliente;

public interface IDuplicataDao {

	public void lancarDuplicata(DuplicataCliente duplicata) throws SQLException;
	public List<DuplicataCliente> verDuplicatas() throws SQLException;
	public void baixarDuplicata(DuplicataCliente duplicata) throws SQLException;
	public void cancelarDuplicata(int id) throws SQLException;
	public DuplicataCliente consultarDuplicata(int id)throws SQLException;
}