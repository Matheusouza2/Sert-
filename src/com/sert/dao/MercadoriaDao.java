package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Mercadoria;

public class MercadoriaDao {

	private Connection con;
	private List<Mercadoria> listaMercadoria;
	private Mercadoria mercadoria = null;

	public MercadoriaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void cadastro(Mercadoria mercadoria) throws SQLException {
		String sql = "INSERT INTO cad_mercadorias(nome_mercadoria, cod_barras, preco_venda, data_cadastro, unidade, preco_compra, usu_cad, estoque, cod_fornecedor) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, mercadoria.getMercadoria());
		prepare.setLong(2, mercadoria.getCodBarras());
		prepare.setFloat(3, mercadoria.getPrecoVenda());
		prepare.setString(4, mercadoria.getDataCadastro());
		prepare.setString(5, mercadoria.getUnd());
		prepare.setFloat(6, mercadoria.getPrecoCompra());
		prepare.setInt(7, mercadoria.getUsuCad());
		prepare.setFloat(8, mercadoria.getEstoque());
		prepare.setLong(9, confereId());
		prepare.execute();
		prepare.close();

	}

	public List<Mercadoria> listar() throws SQLException {
		String sql = "SELECT * FROM cad_mercadorias ORDER BY nome_mercadoria ASC";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		listaMercadoria = new ArrayList<>();
		while (resultado.next()) {
			mercadoria = new Mercadoria();
			mercadoria.setId(resultado.getInt("id"));
			mercadoria.setMercadoria(resultado.getString("nome_mercadoria").trim());
			mercadoria.setCodBarras(resultado.getLong("cod_barras"));
			mercadoria.setPrecoVenda(resultado.getFloat("preco_venda"));
			mercadoria.setDataCadastro(resultado.getString("data_cadastro").trim());
			mercadoria.setUnd(resultado.getString("unidade"));
			mercadoria.setEstoque(resultado.getFloat("estoque"));
			mercadoria.setPrecoCompra(resultado.getFloat("preco_compra"));
			listaMercadoria.add(mercadoria);
		}
		return listaMercadoria;
	}

	public void alterar(Mercadoria mercadoria) throws SQLException {
		String sql = "UPDATE cad_mercadorias SET nome_mercadoria=?, cod_barras=?, preco_venda=?, unidade=?, data_alteracao=?, preco_compra=?, usu_edit=? WHERE id=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, mercadoria.getMercadoria());
		preparedStatement.setLong(2, mercadoria.getCodBarras());
		preparedStatement.setFloat(3, mercadoria.getPrecoVenda());
		preparedStatement.setString(4, mercadoria.getUnd());
		preparedStatement.setString(5, mercadoria.getDataAlt());
		preparedStatement.setFloat(6, mercadoria.getPrecoCompra());
		preparedStatement.setString(7, mercadoria.getUsuAlt());
		preparedStatement.setInt(8, mercadoria.getId());
		preparedStatement.executeUpdate();
	}

	public void excluir(int id) throws SQLException {
		String sql = "DELETE FROM cad_mercadorias WHERE id=?";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, id);
		prepare.execute();
		prepare.close();
	}

	public Mercadoria procurarMerc(long codBarras) throws SQLException {
		String sql = "SELECT * FROM cad_mercadorias WHERE cod_barras = ?";

		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setLong(1, codBarras);
		ResultSet resultado = prepare.executeQuery();
		mercadoria = new Mercadoria();
		if (resultado.next()) {
			mercadoria = new Mercadoria();
			mercadoria.setId(resultado.getInt("id"));
			mercadoria.setMercadoria(resultado.getString("nome_mercadoria").trim());
			mercadoria.setCodBarras(resultado.getLong("cod_barras"));
			mercadoria.setPrecoVenda(resultado.getFloat("preco_venda"));
			mercadoria.setDataCadastro(resultado.getString("data_cadastro").trim());
			mercadoria.setUnd(resultado.getString("unidade"));
			mercadoria.setDataAlt(resultado.getString("data_alteracao"));
			mercadoria.setPrecoCompra(resultado.getFloat("preco_compra"));
			mercadoria.setUsuCad(resultado.getInt("usu_cad"));
			mercadoria.setUsuAlt(resultado.getString("usu_edit"));
			mercadoria.setEstoque(resultado.getFloat("estoque"));
		}
		resultado.close();
		return mercadoria;
	}

	public int confereId() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 as id_mercadoria FROM cad_mercadorias_id_seq;";

		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		while (resultado.next()) {
			id = resultado.getInt("id_mercadoria");
		}
		return id;
	}

	public void entradaNotaEstoque(Mercadoria mercadoria, long codFornecedor) throws SQLException {
		if (codFornecedor == 0) {
			String sql = "UPDATE cad_mercadorias SET estoque=? WHERE cod_barras=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setFloat(1, mercadoria.getEstoque());
			preparedStatement.setLong(2, mercadoria.getCodBarras());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} else {
			String sql = "UPDATE cad_mercadorias SET estoque=?, cod_fornecedor=?, preco_venda=? WHERE cod_barras=?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setFloat(1, mercadoria.getEstoque());
			preparedStatement.setLong(2, codFornecedor);
			preparedStatement.setFloat(3, mercadoria.getPrecoVenda());
			preparedStatement.setLong(4, mercadoria.getCodBarras());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}
	}

	public void movEstoque(float precoVenda, float estoque, long codBarras) throws SQLException {
		String sql = "UPDATE cad_mercadorias SET estoque=?, preco_venda=? WHERE cod_barras=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setFloat(1, estoque);
		preparedStatement.setFloat(2, precoVenda);
		preparedStatement.setLong(3, codBarras);
		preparedStatement.executeUpdate();
	}

	public List<Mercadoria> extratoMerc(int codMerc) throws SQLException{
		List<Mercadoria> mercadorias = new ArrayList<>();
		Mercadoria merc;
		
		String nfeEntrada = "SELECT quant_compra FROM nfe_merc WHERE id_merc = ";
		PreparedStatement prepared = con.prepareStatement(nfeEntrada);
		prepared.setInt(1, codMerc);
		ResultSet resultSet = prepared.executeQuery();
		
		while(resultSet.next()) {
			merc = new Mercadoria();
			merc.setEstoque(resultSet.getFloat("quant_compra"));
			mercadorias.add(merc);
		}
		
		String nfeEntrada = "SELECT quant_compra FROM nfe_merc WHERE id_merc = ";
		PreparedStatement prepared = con.prepareStatement(nfeEntrada);
		prepared.setInt(1, codMerc);
		ResultSet resultSet = prepared.executeQuery();
		
		while(resultSet.next()) {
			merc = new Mercadoria();
			merc.setEstoque(resultSet.getFloat("quant_compra"));
			mercadorias.add(merc);
		}
		
		return mercadorias;
	}
}