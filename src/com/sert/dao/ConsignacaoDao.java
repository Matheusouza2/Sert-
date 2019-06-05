package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Mercadoria;
import com.sert.entidades.Orcamento;

public class ConsignacaoDao {

	private Connection con;
	
	public ConsignacaoDao() throws ClassNotFoundException, SQLException, IOException {
		con = ConexaoDao.getInstacia().getConector();
	}
	
	public void cadastrar(Orcamento orcamento) throws SQLException {
		String sql = "INSERT INTO consignacao(id, vendedor, cliente, val_total) VALUES (?, ?, ?, ?);";
		String sql2 = "INSERT INTO consignacao_merc(id_merc, qnt, valor_merc)VALUES (?, ?, ?);";
		PreparedStatement prepare = con.prepareStatement(sql);
		
		prepare.setInt(1, orcamento.getId());
		prepare.setInt(2, orcamento.getUsuario().getId());
		prepare.setInt(3, orcamento.getCliente().getId());
		prepare.setFloat(4, orcamento.getValTotal());
		prepare.execute();
		prepare.close();
		
		prepare = con.prepareStatement(sql2);
		for(Mercadoria merc : orcamento.getMercadorias()) {
			prepare.setInt(1, merc.getId());
			prepare.setFloat(2, merc.getEstoque());
			prepare.setFloat(3, merc.getPrecoVenda());
			prepare.execute();
		}
		prepare.close();
		
	}
	
	public List<Orcamento> listar(){
		return null;
	}
	
	public void alterar(Orcamento orcamento) {
		
	}
	
	public void excluir(int id) {
		
	}
	
	public int recuperaId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 AS idConsignacao FROM consignacao_id_seq";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			id = resultSet.getInt("idConsignacao");
		}
		return id;
	}
}