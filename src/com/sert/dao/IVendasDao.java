package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Venda;

public interface IVendasDao {

	public void cadastrarVenda(Venda venda)throws SQLException;
	public List<Venda> listarVendas() throws SQLException;
	public void alterarVenda(Venda venda)throws SQLException;
	public void cancelarVenda(int id) throws SQLException;
	public List<Venda> pesquisarVenda(String dtInicial, String dtFinal)throws SQLException;
	public int getIdVenda() throws SQLException;
	public Venda imprimirVenda(int id)throws SQLException;
}