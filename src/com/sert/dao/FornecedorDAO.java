package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sert.entidades.Fornecedor;

public class FornecedorDAO implements IFornecedorDAO {

	private Connection con;

	public FornecedorDAO() throws ClassNotFoundException, SQLException, IOException {
		con = ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void cadastrar(Fornecedor fornecedor) throws SQLException {
		String sql = "INSERT INTO fornecedor(cnpj, ie, nome, nome_fant, rua, numero_end, bairro, cidade, uf, cep, telefone, celular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, fornecedor.getCnpjForn());
		preparedStatement.setString(2, fornecedor.getIeForn());
		preparedStatement.setString(3, fornecedor.getRazSocial());
		preparedStatement.setString(4, fornecedor.getNomeFant());
		preparedStatement.setString(5, fornecedor.getLograForn());
		preparedStatement.setInt(6, fornecedor.getNumrEndForn());
		preparedStatement.setString(7, fornecedor.getBairroForn());
		preparedStatement.setString(8, fornecedor.getCidadeForn());
		preparedStatement.setString(9, fornecedor.getUfForn());
		preparedStatement.setLong(10, fornecedor.getCepForn());
		preparedStatement.setLong(11, fornecedor.getFoneForn());
		preparedStatement.setLong(12, fornecedor.getCelularForn());

		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public List<Fornecedor> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Fornecedor fornecedor) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Fornecedor pesquisar(String cnpj) throws SQLException {
		String sql = "SELECT * FROM fornecedor WHERE cnpj = '" + cnpj+"'";
		PreparedStatement prepare = con.prepareStatement(sql);
		Fornecedor fornecedor = new Fornecedor();

		ResultSet result = prepare.executeQuery();

		while (result.next()) {
			fornecedor.setId(result.getInt("id"));
			fornecedor.setCnpjForn(result.getString("cnpj"));
			fornecedor.setIeForn(result.getString("ie"));
			fornecedor.setRazSocial(result.getString("nome"));
			fornecedor.setNomeFant(result.getString("nome_fant"));
			fornecedor.setLograForn(result.getString("rua"));
			fornecedor.setNumrEndForn(result.getInt("numero_end"));
			fornecedor.setBairroForn(result.getString("bairro"));
			fornecedor.setCidadeForn(result.getString("cidade"));
			fornecedor.setUfForn(result.getString("uf"));
			fornecedor.setCepForn(result.getLong("cep"));
			fornecedor.setFoneForn(result.getLong("telefone"));
			fornecedor.setCelularForn(result.getLong("celular"));
		}

		return fornecedor;
	}

}
