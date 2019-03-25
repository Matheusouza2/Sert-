package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Fornecedor;

public interface IFornecedorDAO {
	
	public void cadastrar(Fornecedor fornecedor) throws SQLException;
	public List<Fornecedor> listar() throws SQLException;
	public void alterar(Fornecedor fornecedor) throws SQLException;
	public void excluir(int id)throws SQLException;
	public Fornecedor pesquisar(String cnpj)throws SQLException;
	public int recuperaId() throws SQLException;
}