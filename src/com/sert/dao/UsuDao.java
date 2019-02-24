package com.sert.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sert.entidades.Usuario;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class UsuDao implements IUsuDao {

	private Connection con;
	private Usuario usu;
	private List<Usuario> listUsu;

	public UsuDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void cadastrar(Usuario usu) throws SQLException {
		String sql = "INSERT INTO usuario(nome, senha) VALUES (?,?)";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, usu.getNome());
		preparador.setString(2, usu.getSenha());
		preparador.execute();
		preparador.close();
	}

	@Override
	public List<Usuario> listar() throws SQLException {
		String sql = "SELECT * FROM funcionario";
		PreparedStatement preparador = con.prepareStatement(sql);
		ResultSet result = preparador.executeQuery();

		listUsu = new ArrayList<>();

		while (result.next()) {
			usu = new Usuario();
			usu.setNome(result.getString("nome").trim());
			usu.setSenha(result.getString("senha").trim());
			listUsu.add(usu);
		}
		return listUsu;
	}

	@Override
	public void atualizar(Usuario usu) throws SQLException {
		String sql = "UPDATE usuario SET nome=?, senha=? WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, usu.getNome());
		prepare.setString(1, usu.getSenha());
		prepare.setInt(3, usu.getId());
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM usuario WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, id);
		prepare.execute();
		prepare.close();
	}

	@Override
	public int confereId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 as id_usuario FROM clientes_id_seq;";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		while (resultado.next()) {
			id = resultado.getInt("id_usuario");
		}
		return id;
	}

	@Override
	public Usuario consulta(String login, String senha) throws SQLException {
		String sql = "SELECT * FROM funcionario WHERE nome=? AND senha=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, login);
		preparador.setString(2, senha);
		ResultSet result = preparador.executeQuery();
		
		Usuario usuario = new Usuario();
		
		if(result.next()){
			usuario.setId(result.getInt("id"));
			usuario.setNome(result.getString("nome").trim());
			usuario.setSenha(result.getString("senha").trim());
		}
		return usuario;
	}
}