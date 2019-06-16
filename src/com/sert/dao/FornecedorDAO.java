package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sert.entidades.Fornecedor;

public class FornecedorDAO {

	private Connection con;

	public FornecedorDAO() throws ClassNotFoundException, SQLException, IOException {
		con = ConexaoDao.getInstacia().getConector();
	}

	public void cadastrar(Fornecedor fornecedor) throws SQLException {
		String sql = "INSERT INTO fornecedor(id, cnpj, ie, nome, nome_fant, rua, numero_end, bairro, cidade, uf, cep, telefone, celular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String seq = "ALTER SEQUENCE fornecedor_id_seq RESTART WITH " + fornecedor.getId();

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, fornecedor.getId());
		preparedStatement.setString(2, fornecedor.getCnpjForn());
		preparedStatement.setString(3, fornecedor.getIeForn());
		preparedStatement.setString(4, fornecedor.getRazSocial());
		preparedStatement.setString(5, fornecedor.getNomeFant());
		preparedStatement.setString(6, fornecedor.getLograForn());
		preparedStatement.setInt(7, fornecedor.getNumrEndForn());
		preparedStatement.setString(8, fornecedor.getBairroForn());
		preparedStatement.setString(9, fornecedor.getCidadeForn());
		preparedStatement.setString(10, fornecedor.getUfForn());
		preparedStatement.setLong(11, fornecedor.getCepForn());
		preparedStatement.setLong(12, fornecedor.getFoneForn());
		preparedStatement.setLong(13, fornecedor.getCelularForn());

		preparedStatement.execute();

		preparedStatement = con.prepareStatement(seq);
		preparedStatement.execute();
		preparedStatement.close();
	}

	public List<Fornecedor> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void alterar(Fornecedor fornecedor) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void excluir(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Fornecedor pesquisar(String cnpj) throws SQLException {
		String sql = "SELECT * FROM fornecedor WHERE cnpj = ?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, cnpj);
		Fornecedor fornecedor = null;
		ResultSet result = prepare.executeQuery();

		if (result.next()) {
			fornecedor = new Fornecedor();
			fornecedor.setId(result.getInt("id"));
			fornecedor.setCnpjForn(result.getString("cnpj").trim());
			fornecedor.setIeForn(result.getString("ie").trim());
			fornecedor.setRazSocial(result.getString("nome").trim());
			fornecedor.setNomeFant(result.getString("nome_fant").trim());
			fornecedor.setLograForn(result.getString("rua").trim());
			fornecedor.setNumrEndForn(result.getInt("numero_end"));
			fornecedor.setBairroForn(result.getString("bairro").trim());
			fornecedor.setCidadeForn(result.getString("cidade").trim());
			fornecedor.setUfForn(result.getString("uf").trim());
			fornecedor.setCepForn(result.getLong("cep"));
			fornecedor.setFoneForn(result.getLong("telefone"));
			fornecedor.setCelularForn(result.getLong("celular"));
		}

		return fornecedor;
	}

	public int recuperaId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 AS idFornecedor FROM fornecedor_id_seq";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			id = resultSet.getInt("idFornecedor");
		}

		return id;
	}
}