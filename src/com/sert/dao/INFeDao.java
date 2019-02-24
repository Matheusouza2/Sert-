package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.NFeEntrada;

public interface INFeDao {

	public void cadastrar(NFeEntrada nFeEntrada) throws SQLException;
	public List<NFeEntrada> listarNota() throws SQLException;
	public NFeEntrada pesq(String chave) throws SQLException;
	public int recuperaId() throws SQLException;
}