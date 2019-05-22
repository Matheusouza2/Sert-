package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Orcamento;

public interface IOrcamentoDAO {

	public void cadastrar(Orcamento orcamento)throws SQLException;
	public List<Orcamento> listar() throws SQLException;
	public void alterar(Orcamento orcamento)throws SQLException;
	public void excluir(int id) throws SQLException;
	public Orcamento consultaOrcamentoAlt(int id)throws SQLException;
	public int consultaIdOrcamento()throws SQLException;
}