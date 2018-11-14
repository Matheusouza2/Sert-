package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Mercadoria;

public interface IMercadoriaDao {

	public void cadastro(Mercadoria mercadoria) throws SQLException;
	public List<Mercadoria> listar() throws SQLException;
	public void alterar (Mercadoria mercadoria) throws SQLException;
	public void excluir(int id) throws SQLException;
	public Mercadoria procurarMerc(long codBarras) throws SQLException;
	public int confereId() throws SQLException;
}