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
		String sql = "INSERT INTO public.funcionario(nome, senha, rg, cpf, cep, endereco, numero, bairro, cidade, estado, observacoes)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, usu.getNome());
		preparador.setString(2, usu.getSenha());
		preparador.setInt(3, usu.getRg());
		preparador.setLong(4, usu.getCpf());
		preparador.setInt(5, usu.getCep());
		preparador.setString(6, usu.getRua());
		preparador.setInt(7, usu.getNumero());
		preparador.setString(8, usu.getBairro());
		preparador.setString(9, usu.getCidade());
		preparador.setString(10, usu.getUf());
		preparador.setString(11, usu.getObs());
		preparador.execute();
		preparador.close();
	}

	@Override
	public List<Usuario> listar() throws SQLException {
		String sql = "SELECT * FROM funcionario ORDER BY id ASC";
		PreparedStatement preparador = con.prepareStatement(sql);
		ResultSet result = preparador.executeQuery();

		listUsu = new ArrayList<>();

		while (result.next()) {
			usu = new Usuario();
			usu.setId(result.getInt("id"));
			usu.setNome(result.getString("nome").trim());
			usu.setSenha(result.getString("senha").trim());
			listUsu.add(usu);
		}
		return listUsu;
	}

	@Override
	public void atualizar(Usuario usu) throws SQLException {
		String sql = "UPDATE funcionario SET nome=?, senha=?, rg=?, cpf=?, cep=?, endereco=?, numero=?, bairro=?, cidade=?, estado=?, observacoes=? WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, usu.getNome());
		prepare.setString(2, usu.getSenha());
		prepare.setInt(3, usu.getRg());
		prepare.setLong(4, usu.getCpf());
		prepare.setInt(5, usu.getCep());
		prepare.setString(6, usu.getRua());
		prepare.setInt(7, usu.getNumero());
		prepare.setString(8, usu.getBairro());
		prepare.setString(9, usu.getCidade());
		prepare.setString(10, usu.getUf());
		prepare.setString(11, usu.getObs());
		prepare.setInt(12, usu.getId());
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM funcionario WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, id);
		prepare.execute();
		prepare.close();
	}

	@Override
	public int confereId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 as id_usuario FROM funcionario_id_seq;";

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

		if (result.next()) {
			usuario.setId(result.getInt("id"));
			usuario.setNome(result.getString("nome").trim());
			usuario.setSenha(result.getString("senha").trim());
		}
		return usuario;
	}

	@Override
	public Usuario consultaCad(String login) throws SQLException {
		String sql = "SELECT * FROM funcionario WHERE nome=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, login);
		ResultSet result = preparador.executeQuery();

		usu = new Usuario();

		if (result.next()) {
			usu.setId(result.getInt("id"));
			usu.setNome(result.getString("nome").trim());
			usu.setSenha(result.getString("senha").trim());
		}
		return usu;
	}

	@Override
	public Usuario consultaAlter(int id) throws SQLException {
		String sql = "SELECT * FROM funcionario WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, id);
		ResultSet result = preparador.executeQuery();

		usu = new Usuario();

		if (result.next()) {
			usu.setId(result.getInt("id"));
			usu.setNome(result.getString("nome").trim());
			usu.setSenha(result.getString("senha").trim());
			usu.setRg(result.getInt("rg"));
			usu.setCpf(result.getLong("cpf"));
			usu.setCep(result.getInt("cep"));
			usu.setRua(result.getString("endereco").trim());
			usu.setNumero(result.getInt("numero"));
			usu.setBairro(result.getString("bairro").trim());
			usu.setCidade(result.getString("cidade").trim());
			usu.setUf(result.getString("estado"));
			usu.setObs(result.getString("observacoes").trim());
		}
		return usu;
	}
}