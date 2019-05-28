package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.MovEstoque;
import com.sert.entidades.Usuario;

public class MovEstoqueDao {

	private Connection con;
	private List<MovEstoque> listMovEstoque;
	private MovEstoque movEstoque;

	public MovEstoqueDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void movimentar(MovEstoque estoque) throws SQLException {
		String sql = "INSERT INTO mov_estoque(id, tipo, funcionario, data_mov) VALUES (?, ?, ?, ?, ?, ?);";

		PreparedStatement preparedStatement = con.prepareStatement(sql);

		preparedStatement.setInt(1, estoque.getId());
		preparedStatement.setString(2, estoque.getTipo());
		preparedStatement.setInt(3, estoque.getUsuario().getId());
		preparedStatement.setTimestamp(4, Timestamp.valueOf(estoque.getData()));
	}

	public List<MovEstoque> listarMov() throws SQLException {
		String sql = "";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet result = preparedStatement.executeQuery();

		listMovEstoque = new ArrayList<MovEstoque>();
		Usuario usuario = null;
		while (result.next()) {
			movEstoque = new MovEstoque();
			usuario = new Usuario();
			movEstoque.setId(result.getInt("id"));
			movEstoque.setTipo(result.getString("tipo"));
			usuario.setNome(result.getString("usu_nome").trim());
			movEstoque.setUsuario(usuario);
			movEstoque.setData(result.getString("data_mov"));
			
			listMovEstoque.add(movEstoque);
		}
		return listMovEstoque;
	}

}