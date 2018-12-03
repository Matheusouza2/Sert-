package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;

public class VendaDao implements IVendasDao {

	private Connection con;
	private Venda venda;
	private Mercadoria mercadoria;
	private List<Venda> listVenda;
	private List<Mercadoria> listMercVenda;

	public VendaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void cadastrarVenda(Venda venda) throws SQLException {
		String sql = "INSERT INTO venda_merc(id, id_merc, quantidade, valor_un) VALUES (?, ?, ?, ?);";
		String sql2 = "INSERT INTO vendas(id, vendedor, cliente, data_venda, val_total, dinheiro, val_dinheiro, cartao, val_cartao)	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement statement = con.prepareStatement(sql2);
		statement.setInt(1, venda.getId());
		statement.setInt(2, venda.getVendedorCad());
		statement.setInt(3, venda.getClienteCad());
		statement.setString(4, venda.getDataVenda());
		statement.setFloat(5, venda.getValTotal());
		statement.setInt(6, venda.getDinheiro());
		statement.setFloat(7, venda.getValDInheiro());
		statement.setInt(8, venda.getCartao());
		statement.setFloat(9, venda.getValCartao());
		statement.execute();
		statement.close();
		
		PreparedStatement statement2 = con.prepareStatement(sql);
		for (int i = 0; i < venda.getMercadorias().size(); i++) {
			statement2.setInt(1, venda.getId());
			statement2.setInt(2, venda.getMercadorias().get(i).getId());
			statement2.setFloat(3, venda.getMercadorias().get(i).getEstoque());
			statement2.setFloat(4, venda.getMercadorias().get(i).getPrecoVenda());
			statement2.execute();
		}
		statement2.close();
	}

	@Override
	public List<Venda> listarVendas() throws SQLException {
		String sql = "SELECT v.id, f.nome as func_nome, cl.nome as cliente_nome, v.data_venda, merc.id as merc_id, merc.cod_barras, merc.nome_mercadoria, v.quantidade, v.valor_un, v.dinheiro, v.val_dinheiro, v.cartao, v.val_cartao FROM vendas v INNER JOIN funcionario f ON v.vendedor = f.id INNER JOIN clientes cl ON v.cliente = cl.id INNER JOIN cad_mercadorias merc ON v.idMerc = merc.id ORDER BY v.id ASC";
		listVenda = new ArrayList<>();
		listMercVenda = new ArrayList<>();
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			venda = new Venda();
			mercadoria = new Mercadoria();
			venda.setId(resultSet.getInt("id"));
			venda.setVendedor(resultSet.getString("func_nome"));
			venda.setCliente(resultSet.getString("cliente_nome"));
			venda.setDataVenda(resultSet.getString("data_venda"));
			venda.setDinheiro(resultSet.getInt("dinheiro"));
			venda.setValDInheiro(resultSet.getFloat("val_dinheiro"));
			venda.setCartao(resultSet.getInt("cartao"));
			venda.setValCartao(resultSet.getFloat("val_cartao"));
			venda.setValTotal(venda.getValCartao() + venda.getValDInheiro());
			mercadoria.setId(resultSet.getInt("merc_id"));
			mercadoria.setCodBarras(resultSet.getLong("cod_barras"));
			mercadoria.setMercadoria(resultSet.getString("nome_mercadoria"));
			mercadoria.setEstoque(resultSet.getFloat("quantidade"));
			mercadoria.setPrecoVenda(resultSet.getFloat("valor_un"));
			listMercVenda.add(mercadoria);
			venda.setMercadorias(listMercVenda);
			listVenda.add(venda);
		}
		return listVenda;
	}

	@Override
	public void alterarVenda(Venda venda) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelarVenda(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Venda> pesquisarVenda(String dtInicial, String dtFinal) throws SQLException {
		String sql = "SELECT v.id, f.nome as func_nome, v.data_venda, v.val_dinheiro, v.val_cartao FROM vendas v INNER JOIN funcionario f ON v.vendedor = f.id WHERE data_venda BETWEEN '"+dtInicial+" 00:00:00' AND '"+dtFinal+" 00:00:00' ORDER BY data_venda ASC;";
		PreparedStatement prepare = con.prepareStatement(sql);
		listVenda = new ArrayList<>();
		ResultSet resultado = prepare.executeQuery();
		while(resultado.next()){
			venda = new Venda();
			venda.setId(resultado.getInt("id"));
			venda.setVendedor(resultado.getString("func_nome"));
			venda.setDataVenda(resultado.getString("data_venda"));
			venda.setValCartao(resultado.getFloat("val_cartao"));
			venda.setValDInheiro(resultado.getFloat("val_dinheiro"));
			listVenda.add(venda);
		}
		return listVenda;
	}

	@Override
	public int getIdVenda() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value+1 as id_venda FROM vendas_gera_id_seq";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		while (resultado.next()) {
			id = resultado.getInt("id_venda");
		}
		return id;
	}
}