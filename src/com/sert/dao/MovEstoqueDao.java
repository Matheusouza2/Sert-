package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.sert.entidades.MovEstoque;

public class MovEstoqueDao implements IDaoMovEstoque {

	private Connection con;
	private List<MovEstoque> movEstoque;
	private MovEstoque listMovEstoque;

	public MovEstoqueDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void movimentar(MovEstoque estoque) throws SQLException {
		String sql = "INSERT INTO mov_estoque(id, tipo, funcionario, mercadoria, quantidade, data_mov) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement preparedStatement = con.prepareStatement(sql);

		preparedStatement.setInt(0, estoque.getId());
		preparedStatement.setString(1, estoque.getTipo());
		preparedStatement.setInt(2, estoque.getFuncionario());
		preparedStatement.setInt(3, estoque.getMercadoria());
		preparedStatement.setFloat(4, estoque.getQuant());
		preparedStatement.setTimestamp(5, Timestamp.valueOf(estoque.getData()));
	}

	@Override
	public List<MovEstoque> listarMov() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}