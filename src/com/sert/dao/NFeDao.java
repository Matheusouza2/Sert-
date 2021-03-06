package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Fornecedor;
import com.sert.entidades.MercadoriaNFe;
import com.sert.entidades.NFeEntrada;

public class NFeDao {

	private Connection con;

	public NFeDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void cadastrar(NFeEntrada nFeEntrada) throws SQLException, ClassNotFoundException, IOException {
		String sql = "INSERT INTO nfe_compra(chave, numero, fornecedor, id, val_nota, data_entrada) VALUES (?, ?, ?, ?, ?, ?)";
		String sql2 = "INSERT INTO nfe_merc(id_nfe, id_merc, preco_compra, quant_compra) VALUES (?, ?, ?, ?)";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, nFeEntrada.getChave());
		prepare.setInt(2, nFeEntrada.getNumNota());
		prepare.setInt(3, nFeEntrada.getFornecedor().getId());
		prepare.setInt(4, nFeEntrada.getId());
		prepare.setFloat(5, nFeEntrada.getValNota());
		prepare.setTimestamp(6, Timestamp.valueOf(nFeEntrada.getDataEntrada()));
		prepare.execute();

		prepare = con.prepareStatement(sql2);
		for (int i = 0; i < nFeEntrada.getMercadorias().size(); i++) {
			prepare.setInt(1, nFeEntrada.getId());
			prepare.setLong(2, nFeEntrada.getMercadorias().get(i).getCodProd());
			prepare.setFloat(3, nFeEntrada.getMercadorias().get(i).getPrecoUn());
			prepare.setFloat(4, nFeEntrada.getMercadorias().get(i).getQuantCompra());
			prepare.execute();
		}
		prepare.close();

		String seq = "ALTER SEQUENCE nfe_compra_id_seq RESTART WITH " + nFeEntrada.getId();
		prepare = con.prepareStatement(seq);
		prepare.execute();
		prepare.close();
	}

	public List<NFeEntrada> listarNota() throws SQLException {
		String sql = "SELECT nf.id, nf.numero, nf.chave, f.nome AS nome_forn, to_char(nf.data_entrada, 'dd/MM/yyyy HH:mm:SS') data_entrada FROM nfe_compra nf INNER JOIN fornecedor f ON nf.fornecedor = f.id ORDER BY id ASC";
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultSet = prepare.executeQuery();
		Fornecedor fornecedor;
		NFeEntrada nFeEntrada;
		List<NFeEntrada> listNota = new ArrayList<>();
		while (resultSet.next()) {
			fornecedor = new Fornecedor();
			nFeEntrada = new NFeEntrada();
			nFeEntrada.setId(resultSet.getInt("id"));
			nFeEntrada.setDataEntrada(resultSet.getString("data_entrada"));
			nFeEntrada.setNumNota(resultSet.getInt("numero"));
			nFeEntrada.setChave(resultSet.getString("chave").trim());
			fornecedor.setRazSocial(resultSet.getString("nome_forn").trim());
			nFeEntrada.setFornecedor(fornecedor);
			listNota.add(nFeEntrada);
		}
		return listNota;
	}

	public NFeEntrada pesq(String chave) throws SQLException {
		String sql = "SELECT nf.chave, nf.numero, nf.fornecedor, nf.id, f.nome AS nome_forn, f.cnpj, f.ie, f.rua, f.numero_end, f.cidade, f.uf, nfm.preco_compra, nfm.quant_compra, cdm.cod_barras, cdm.nome_mercadoria FROM nfe_compra nf INNER JOIN fornecedor f ON nf.fornecedor = f.id INNER JOIN nfe_merc nfm ON nf.id = nfm.id_nfe INNER JOIN cad_mercadorias cdm ON nfm.id_merc = cdm.cod_fornecedor WHERE chave='"
				+ chave + "'";
		PreparedStatement prepare = con.prepareStatement(sql);
		NFeEntrada nFeEntrada = null;
		Fornecedor fornecedor = new Fornecedor();
		MercadoriaNFe mercadoria;
		List<MercadoriaNFe> mercadorias = new ArrayList<MercadoriaNFe>();
		ResultSet result = prepare.executeQuery();

		while (result.next()) {
			nFeEntrada = new NFeEntrada();
			nFeEntrada.setChave(result.getString("chave").trim());
			nFeEntrada.setNumNota(result.getInt("numero"));
			fornecedor.setRazSocial(result.getString("nome_forn").trim());
			fornecedor.setCnpjForn(result.getString("cnpj").trim());
			fornecedor.setIeForn(result.getString("ie").trim());
			fornecedor.setLograForn(result.getString("rua").trim());
			fornecedor.setNumrEndForn(result.getInt("numero_end"));
			fornecedor.setCidadeForn(result.getString("cidade").trim());
			fornecedor.setUfForn(result.getString("uf").trim());
			nFeEntrada.setFornecedor(fornecedor);
			mercadoria = new MercadoriaNFe();
			mercadoria.setCodBarras(result.getLong("cod_barras"));
			mercadoria.setMercadoria(result.getString("nome_mercadoria").trim());
			mercadoria.setPrecoUn(result.getFloat("preco_compra"));
			mercadoria.setQuantCompra(result.getFloat("quant_compra"));
			mercadorias.add(mercadoria);
			nFeEntrada.setMercadorias(mercadorias);
		}
		return nFeEntrada;
	}

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

	public List<NFeEntrada> nfePeriodo(String dtInicial, String dtFinal) throws SQLException {
		String sql = "SELECT nf.id, nf.numero, nf.val_nota, to_char(nf.data_entrada, 'dd/MM/yyyy') data_entrada, f.nome AS nome_forn FROM nfe_compra nf INNER JOIN fornecedor f ON nf.fornecedor = f.id WHERE data_entrada BETWEEN '"
				+ dtInicial + " 00:00:00' AND '" + dtFinal + " 23:59:59' ORDER BY nf.id ASC;";

		List<NFeEntrada> nfeEntrada = new ArrayList<>();
		Fornecedor fornecedor;
		NFeEntrada entrada;
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			entrada = new NFeEntrada();
			fornecedor = new Fornecedor();
			entrada.setId(resultSet.getInt("id"));
			entrada.setNumNota(resultSet.getInt("numero"));
			fornecedor.setRazSocial(resultSet.getString("nome_forn").trim());
			entrada.setFornecedor(fornecedor);
			entrada.setValNota(resultSet.getFloat("val_nota"));
			entrada.setDataEntrada(resultSet.getString("data_entrada"));

			nfeEntrada.add(entrada);
		}

		return nfeEntrada;
	}

	public boolean consultarChaveNfe(String cod) throws SQLException {
		boolean cadastrado = false;
		String sql = "SELECT * FROM nfe_compra WHERE chave=?;";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, cod);
		ResultSet result = prepare.executeQuery();
		if (result.next()) {
			cadastrado = true;
		}
		return cadastrado;
	}
}