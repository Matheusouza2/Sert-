package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Cliente;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Orcamento;
import com.sert.entidades.Usuario;

public class ConsignacaoDao {

	private Connection con;

	public ConsignacaoDao() throws ClassNotFoundException, SQLException, IOException {
		con = ConexaoDao.getInstacia().getConector();
	}

	public void cadastrar(Orcamento orcamento) throws SQLException {
		String sql = "INSERT INTO consignacao(id, vendedor, cliente, val_total, status, data) VALUES (?, ?, ?, ?, ?, ?);";
		String sql2 = "INSERT INTO consignacao_merc(id_merc, qnt, valor_merc, id_consignacao, status)VALUES (?, ?, ?, ?, ?);";
		String seq = "ALTER SEQUENCE consignacao_id_seq RESTART WITH " + orcamento.getId();
		String updateEstoque = null;

		PreparedStatement prepare = con.prepareStatement(sql);
		PreparedStatement prepare2 = null;

		prepare.setInt(1, orcamento.getId());
		prepare.setInt(2, orcamento.getUsuario().getId());
		prepare.setInt(3, orcamento.getCliente().getId());
		prepare.setFloat(4, orcamento.getValTotal());
		prepare.setString(5, orcamento.getStatus());
		prepare.setTimestamp(6, Timestamp.valueOf(orcamento.getData()));
		prepare.execute();
		prepare.close();

		prepare = con.prepareStatement(sql2);
		for (Mercadoria merc : orcamento.getMercadorias()) {
			prepare.setInt(1, merc.getId());
			prepare.setFloat(2, merc.getEstoque());
			prepare.setFloat(3, merc.getPrecoVenda());
			prepare.setInt(4, orcamento.getId());
			prepare.setString(5, merc.getStatus());
			prepare.execute();

			updateEstoque = "UPDATE cad_mercadorias SET estoque=estoque-" + merc.getEstoque() + " WHERE id=?;";
			prepare2 = con.prepareStatement(updateEstoque);
			prepare2.setInt(1, merc.getId());
			prepare2.executeUpdate();
		}
		prepare.close();
		prepare2.close();

		prepare = con.prepareStatement(seq);
		prepare.execute();

	}

	public List<Orcamento> listar() throws SQLException {
		String sql = "SELECT cons.id, cons.val_total, cons.status, to_char(cons.data, 'dd/MM/yyyy') as data, cl.nome nome_cliente, fun.nome nome_func FROM consignacao cons INNER JOIN clientes cl ON cl.id = cons.cliente INNER JOIN funcionario fun ON fun.id = cons.vendedor ORDER BY id desc";
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet result = prepare.executeQuery();

		Orcamento consignacao;
		Usuario usu;
		Cliente cliente;
		List<Orcamento> listCons = new ArrayList<Orcamento>();
		while (result.next()) {
			consignacao = new Orcamento();
			usu = new Usuario();
			cliente = new Cliente();

			consignacao.setId(result.getInt("id"));
			consignacao.setStatus(result.getString("status").trim());
			consignacao.setValTotal(result.getFloat("val_total"));
			consignacao.setData(result.getString("data"));

			cliente.setNome(result.getString("nome_cliente").trim());

			consignacao.setCliente(cliente);

			usu.setNome(result.getString("nome_func").trim());

			consignacao.setUsuario(usu);

			listCons.add(consignacao);
		}
		return listCons;
	}

	public void alterar(Orcamento orcamento) {

	}

	public void excluir(int id) {

	}

	public Orcamento consultarConsignacao(int id) throws SQLException {
		String sql = "SELECT cons.id, cons.val_total, cons.status, to_char(cons.data, 'dd/MM/yyyy') as data, cl.id id_cliente, cl.nome nome_cliente, cl.cpf, cl.rua, cl.bairro, cl.cidade, cl.uf, cl.numero, cl.contato, fun.nome nome_func, cad_m.id id_merc, cad_m.cod_barras, cad_m.nome_mercadoria, cons_m.qnt, cons_m.valor_merc, cons_m.status as status_merc FROM consignacao cons INNER JOIN consignacao_merc cons_m ON cons_m.id_consignacao = cons.id INNER JOIN clientes cl ON cl.id = cons.cliente INNER JOIN funcionario fun ON fun.id = cons.vendedor INNER JOIN cad_mercadorias cad_m ON cad_m.id = cons_m.id_merc WHERE cons.id = ?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, id);
		ResultSet result = prepare.executeQuery();

		Orcamento consignacao = null;
		Usuario usu;
		Cliente cliente;
		Mercadoria merc;
		List<Mercadoria> listMerc = new ArrayList<Mercadoria>();
		while (result.next()) {
			consignacao = new Orcamento();
			usu = new Usuario();
			cliente = new Cliente();
			merc = new Mercadoria();

			consignacao.setId(result.getInt("id"));
			consignacao.setStatus(result.getString("status").trim());
			consignacao.setValTotal(result.getFloat("val_total"));
			consignacao.setData(result.getString("data"));
			
			cliente.setId(result.getInt("id_cliente"));
			cliente.setNome(result.getString("nome_cliente").trim());
			cliente.setCpf(result.getLong("cpf"));
			cliente.setRua(result.getString("rua").trim());
			cliente.setBairro(result.getString("bairro").trim());
			cliente.setCidade(result.getString("cidade").trim());
			cliente.setUf(result.getString("uf").trim());
			cliente.setNumero(result.getInt("numero"));
			cliente.setContato(result.getLong("contato"));

			consignacao.setCliente(cliente);

			usu.setNome(result.getString("nome_func").trim());

			consignacao.setUsuario(usu);
			
			merc.setId(result.getInt("id_merc"));
			merc.setCodBarras(result.getLong("cod_barras"));
			merc.setMercadoria(result.getString("nome_mercadoria").trim());
			merc.setEstoque(result.getFloat("qnt"));
			merc.setPrecoVenda(result.getFloat("valor_merc"));
			merc.setStatus(result.getString("status_merc").trim());
			listMerc.add(merc);

			consignacao.setMercadorias(listMerc);

		}
		return consignacao;
	}

	public void devolucao(Orcamento orcamento) throws SQLException {
		String sql = "";
		String sql2 = "";
		PreparedStatement prepare = null;
		PreparedStatement prepare2 = null;
		for (Mercadoria merc : orcamento.getMercadorias()) {
			sql = "UPDATE cad_mercadorias SET estoque=estoque+? WHERE cod_barras=?;";
			prepare = con.prepareStatement(sql);
			prepare.setFloat(1, merc.getEstoque());
			prepare.setLong(2, merc.getCodBarras());
			prepare.execute();

			sql2 = "UPDATE consignacao_merc SET status=? WHERE id_consignacao=? AND id_merc=?";
			prepare2 = con.prepareStatement(sql2);
			prepare2.setString(1, merc.getStatus());
			prepare2.setInt(2, orcamento.getId());
			prepare2.setInt(3, merc.getId());
			prepare2.execute();
		}
		prepare2.close();
		prepare.close();

		sql = "UPDATE consignacao SET status=? WHERE id=?";
		prepare = con.prepareStatement(sql);
		prepare.setString(1, orcamento.getStatus());
		prepare.setInt(2, orcamento.getId());
		prepare.execute();
		prepare.close();
	}

	public int recuperaId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 AS idConsignacao FROM consignacao_id_seq";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			id = resultSet.getInt("idConsignacao");
		}
		return id;
	}
	
	public void faturarConsignacao(Orcamento consignacao) throws SQLException {
		String sql = "";
		String sql2 = "";
		PreparedStatement prepare = null;
		PreparedStatement prepare2 = null;
		for (Mercadoria merc : consignacao.getMercadorias()) {
			sql = "UPDATE cad_mercadorias SET estoque=estoque+? WHERE cod_barras=?;";
			prepare = con.prepareStatement(sql);
			prepare.setFloat(1, merc.getEstoque());
			prepare.setLong(2, merc.getCodBarras());
			prepare.execute();

			sql2 = "UPDATE consignacao_merc SET status=? WHERE id_consignacao=? AND id_merc=?";
			prepare2 = con.prepareStatement(sql2);
			prepare2.setString(1, merc.getStatus());
			prepare2.setInt(2, consignacao.getId());
			prepare2.setInt(3, merc.getId());
			prepare2.execute();
		}
		prepare2.close();
		prepare.close();

		sql = "UPDATE consignacao SET status=? WHERE id=?";
		prepare = con.prepareStatement(sql);
		prepare.setString(1, consignacao.getStatus());
		prepare.setInt(2, consignacao.getId());
		prepare.execute();
		prepare.close();
	}
}