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
		String sql = "INSERT INTO public.vendas(id_venda, vendedor, cliente, data_venda, idmerc, quantidade, valor_un, dinheiro, val_dinheiro, cartao, val_cartao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = con.prepareStatement(sql);
		for(int i = 0; i < venda.getMercadorias().size(); i++){
			statement.setInt(1, venda.getId());
			statement.setInt(2, venda.getVendedorCad());
			statement.setInt(3, venda.getClienteCad());
			statement.setString(4, venda.getDataVenda());
			statement.setInt(5, venda.getMercadorias().get(i).getId());
			statement.setFloat(6, venda.getMercadorias().get(i).getEstoque());
			statement.setFloat(7, venda.getMercadorias().get(i).getPrecoVenda());
			statement.setInt(8, venda.getDinheiro());
			statement.setFloat(9, venda.getValDInheiro());
			statement.setInt(10, venda.getCartao());
			statement.setFloat(11, venda.getValCartao());
			statement.execute();
		}
		statement.close();
	}

	@Override
	public List<Venda> listarVendas() throws SQLException {
		String sql = "SELECT v.id, f.nome as func_nome, cl.nome as client_nome, v.data_venda, merc.id as merc_id, merc.cod_barras, merc.nome_mercadoria, v.quantidade, v.valor_un, v.dinheiro, v.val_dinheiro, v.cartao, v.val_cartao FROM vendas v INNER JOIN funcionario f ON v.vendedor = f.id INNER JOIN clientes cl ON v.cliente = cl.id INNER JOIN cad_mercadorias merc ON v.idMerc = merc.id ORDER BY v.id ASC";
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
			venda.setDataVenda(resultSet.getString("dataVenda"));
			venda.setDinheiro(resultSet.getInt("dinheiro"));
			venda.setValDInheiro(resultSet.getFloat("valDinheiro"));
			venda.setCartao(resultSet.getInt("cartao"));
			venda.setValCartao(resultSet.getFloat("valCartao"));
			mercadoria.setId(resultSet.getInt("merc_id"));
			mercadoria.setCodBarras(resultSet.getLong("cod_barras"));
			mercadoria.setMercadoria(resultSet.getString("nome_mercadoria"));
			mercadoria.setEstoque(resultSet.getFloat("quantidade"));
			mercadoria.setPrecoVenda(resultSet.getFloat("valorUn"));
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
	public Venda pesquisarVenda(int id, String nomeCliente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIdVenda() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value+1 as id_venda FROM vendas_id_seq";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		while (resultado.next()) {
			id = resultado.getInt("id_venda");
		}
		return id;
	}
}