package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Fornecedor;
import com.sert.entidades.MercadoriaNFe;
import com.sert.entidades.NFeEntrada;

public class NFeDao implements INFeDao {

	private Connection con;

	public NFeDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void cadastrar(NFeEntrada nFeEntrada) throws SQLException {
		String sql = "INSERT INTO nfe_compra(chave, numero, fornecedor) VALUES (?, ?, ?)";
		String sql2 = "INSERT INTO nfe_merc(id_nfe, id_merc, preco_compra, quant_compra) VALUES (?, ?, ?, ?)";

		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, nFeEntrada.getChave());
		prepare.setInt(2, nFeEntrada.getNumNota());
		prepare.setInt(3, nFeEntrada.getFornecedor().getId());
		prepare.execute();

		prepare = con.prepareStatement(sql2);
		for (int i = 0; i < nFeEntrada.getMercadorias().size(); i++) {
			prepare.setInt(1, nFeEntrada.getId());
			prepare.setLong(2, nFeEntrada.getMercadorias().get(i).getId());
			prepare.setFloat(3, nFeEntrada.getMercadorias().get(i).getPrecoUn());
			prepare.setFloat(4, nFeEntrada.getMercadorias().get(i).getQuantCompra());
		}

	}

	@Override
	public List<NFeEntrada> listarNota() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NFeEntrada pesq(String chave) throws SQLException {
		String sql = "SELECT nf.chave, nf.numero, nf.fornecedor, nf.id, f.nome AS nome_forn, f.cnpj, f.ie, f.rua, f.numero_end, f.cidade, f.uf, nfm.preco_compra, nfm.quant_compra, cdm.cod_barras, cdm.nome_mercadoria FROM nfe_compra nf INNER JOIN fornecedor f ON nf.fornecedor = f.id INNER JOIN nfe_merc nfm ON nf.id = nfm.id_nfe INNER JOIN cad_mercadorias cdm ON nfm.id_merc = cdm.id WHERE chave = '"
				+ chave + "'";
		PreparedStatement prepare = con.prepareStatement(sql);
		NFeEntrada nFeEntrada = new NFeEntrada();
		Fornecedor fornecedor = new Fornecedor();
		MercadoriaNFe mercadoria;
		List<MercadoriaNFe> mercadorias = new ArrayList<MercadoriaNFe>();
		ResultSet result = prepare.executeQuery();

		while (result.next()) {
			nFeEntrada.setChave(result.getString("chave"));
			nFeEntrada.setNumNota(result.getInt("numero"));
			fornecedor.setRazSocial(result.getString("nome_forn"));
			fornecedor.setCnpjForn(result.getString("cnpj"));
			fornecedor.setIeForn(result.getString("ie"));
			fornecedor.setLograForn(result.getString("rua"));
			fornecedor.setNumrEndForn(result.getInt("numero_end"));
			fornecedor.setCidadeForn(result.getString("cidade"));
			fornecedor.setUfForn(result.getString("uf"));
			nFeEntrada.setFornecedor(fornecedor);
			mercadoria = new MercadoriaNFe();
			mercadoria.setCodBarras(result.getLong("cod_barras"));
			mercadoria.setMercadoria(result.getString("nome_mercadoria"));
			mercadoria.setPrecoUn(result.getFloat("preco_compra"));
			mercadoria.setQuantCompra(result.getFloat("quant_compra"));
			mercadorias.add(mercadoria);
			nFeEntrada.setMercadorias(mercadorias);
		}
		return nFeEntrada;
	}

	@Override
	public int recuperaId() throws SQLException {
		int id = 0;

		String sql = "SELECT last_value + 1 AS id_nfe FROM nfe_compra_id_seq";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			id = resultSet.getInt("id_nfe");
		}

		return id;
	}
}