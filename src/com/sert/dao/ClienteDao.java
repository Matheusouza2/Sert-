package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Cliente;

public class ClienteDao implements IClienteDao {
	private Connection con;
	private Cliente cliente;
	private List<Cliente> listCliente;

	public ClienteDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override

	public void cadastrar(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO clientes(id, nome, cpf, rg, cep, rua, numero, bairro, cidade, uf, contato, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String seq = "ALTER SEQUENCE clientes_id_seq RESTART WITH " + cliente.getId();
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, cliente.getId());
		preparador.setString(2, cliente.getNome());
		preparador.setLong(3, cliente.getCpf());
		preparador.setInt(4, cliente.getRg());
		preparador.setInt(5, cliente.getCep());
		preparador.setString(6, cliente.getRua());
		preparador.setInt(7, cliente.getNumero());
		preparador.setString(8, cliente.getBairro());
		preparador.setString(9, cliente.getCidade());
		preparador.setString(10, cliente.getUf());
		preparador.setLong(11, cliente.getContato());
		preparador.setString(12, cliente.getObs());
		preparador.execute();
		preparador.close();
		
		preparador = con.prepareStatement(seq);
		preparador.execute();
		preparador.close();
		
	}

	@Override
	public List<Cliente> listar() throws SQLException {
		String sql = "SELECT * FROM clientes ORDER BY id ASC";
		PreparedStatement preparador = con.prepareStatement(sql);
		ResultSet result = preparador.executeQuery();

		listCliente = new ArrayList<>();

		while (result.next()) {
			cliente = new Cliente();
			cliente.setId(result.getInt("id"));
			cliente.setNome(result.getString("nome").trim());
			cliente.setCpf(result.getLong("cpf"));
			listCliente.add(cliente);
		}
		return listCliente;
	}

	@Override
	public void atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE clientes SET nome=?, cpf=?, rg=?, cep=?, rua=?, numero=?, bairro=?, cidade=?, uf=?, contato=?, observacao=? WHERE id=?;";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, cliente.getNome());
		prepare.setLong(2, cliente.getCpf());
		prepare.setInt(3, cliente.getRg());
		prepare.setInt(4, cliente.getCep());
		prepare.setString(5, cliente.getRua());
		prepare.setInt(6, cliente.getNumero());
		prepare.setString(7, cliente.getBairro());
		prepare.setString(8, cliente.getCidade());
		prepare.setString(9, cliente.getUf());
		prepare.setLong(10, cliente.getContato());
		prepare.setString(11, cliente.getObs());
		prepare.setInt(12, cliente.getId());
		prepare.executeUpdate();
		prepare.close();
	}

	@Override
	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM clientes WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, id);
		prepare.execute();
		prepare.close();
	}

	@Override
	public Cliente consulta(String id, String cnpj) throws SQLException {
		return null;
	}

	@Override
	public int confereId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 as id_cliente FROM clientes_id_seq;";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		if (resultado.next()) {
			id = resultado.getInt("id_cliente");
		}
		return id;
	}

	@Override
	public Cliente consultaCad(long cnpj) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE cpf=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setLong(1, cnpj);
		ResultSet result = preparador.executeQuery();

		cliente = new Cliente();

		if (result.next()) {
			cliente.setId(result.getInt("id"));
			cliente.setNome(result.getString("nome").trim());
			cliente.setCpf(result.getLong("cpf"));
		}
		return cliente;
	}

	@Override
	public Cliente consultaAlter(int id) throws SQLException {
		String sql = "SELECT * FROM clientes WHERE id=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setInt(1, id);
		ResultSet result = preparador.executeQuery();

		cliente = new Cliente();

		if (result.next()) {
			cliente.setId(result.getInt("id"));
			cliente.setNome(result.getString("nome").trim());
			cliente.setRg(result.getInt("rg"));
			cliente.setCpf(result.getLong("cpf"));
			cliente.setCep(result.getInt("cep"));
			cliente.setRua(result.getString("endereco").trim());
			cliente.setNumero(result.getInt("numero"));
			cliente.setBairro(result.getString("bairro").trim());
			cliente.setCidade(result.getString("cidade").trim());
			cliente.setUf(result.getString("estado"));
			cliente.setObs(result.getString("observacoes").trim());
			cliente.setContato(result.getLong("contato"));
		}
		return cliente;
	}
}