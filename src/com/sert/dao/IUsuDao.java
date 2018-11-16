package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Usuario;

public interface IUsuDao {

	public void cadastrar(Usuario usu)throws SQLException;
	public List<Usuario> listar() throws SQLException;
	public void atualizar(Usuario usu) throws SQLException;
	public void excluir(int id) throws SQLException;
	//public Usuario consulta() throws SQLException;
	public int confereId()throws SQLException;
	
}