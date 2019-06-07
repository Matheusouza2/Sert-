package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.PermissoesFunc;

public class PermissaoDao {

	private Connection con;

	public PermissaoDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void cadastrar(PermissoesFunc permissoes) throws SQLException {
		String sql = "INSERT INTO permissoes(id_func, cad_cliente, list_cliente, alt_cliente, excl_cliente, list_func, cad_func, alt_func, excl_func, cad_prod, list_prod, alt_prod, excl_prod, cad_nota, list_nota, mov_estoque, cad_orcamento, lanc_consig, ver_consig, dash_estoque, dash_venda, dash_caixa, dash_compra, lancar_vendas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, permissoes.getIdFunc());
		prepare.setBoolean(2, permissoes.isCadCliente());
		prepare.setBoolean(3, permissoes.isListCliente());
		prepare.setBoolean(4, permissoes.isAltCliente());
		prepare.setBoolean(5, permissoes.isExclCliente());
		prepare.setBoolean(6, permissoes.isListFunc());
		prepare.setBoolean(7, permissoes.isCadFunc());
		prepare.setBoolean(8, permissoes.isAltFunc());
		prepare.setBoolean(9, permissoes.isExclFunc());
		prepare.setBoolean(10, permissoes.isCadProd());
		prepare.setBoolean(11, permissoes.isListProd());
		prepare.setBoolean(12, permissoes.isAltProd());
		prepare.setBoolean(13, permissoes.isExclProduto());
		prepare.setBoolean(14, permissoes.isCadNota());
		prepare.setBoolean(15, permissoes.isListNota());
		prepare.setBoolean(16, permissoes.isMovEstoque());
		prepare.setBoolean(17, permissoes.isCadOrcamento());
		prepare.setBoolean(18, permissoes.isLancConsignacao());
		prepare.setBoolean(19, permissoes.isVerConsig());
		prepare.setBoolean(20, permissoes.isDashEstoque());
		prepare.setBoolean(21, permissoes.isDashVenda());
		prepare.setBoolean(22, permissoes.isDashCaixa());
		prepare.setBoolean(23, permissoes.isDashCompra());
		prepare.setBoolean(24, permissoes.isLancarVendas());
		prepare.execute();
		prepare.close();
	}

	public List<PermissoesFunc> listar() throws SQLException {
		String sql = "SELECT * FROM permissoes;";
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet result = prepare.executeQuery();
		PermissoesFunc permissoes;
		List<PermissoesFunc> listPerm = new ArrayList<PermissoesFunc>();
		while (result.next()) {
			permissoes = new PermissoesFunc();
			permissoes.setIdFunc(result.getInt("id_func"));
			permissoes.setCadCliente(result.getBoolean("cad_cliente"));
			permissoes.setListCliente(result.getBoolean("list_cliente"));
			permissoes.setAltCliente(result.getBoolean("alt_cliente"));
			permissoes.setExclCliente(result.getBoolean("excl_cliente"));
			permissoes.setListFunc(result.getBoolean("list_func"));
			permissoes.setCadFunc(result.getBoolean("cad_func"));
			permissoes.setAltFunc(result.getBoolean("alt_func"));
			permissoes.setExclFunc(result.getBoolean("excl_func"));
			permissoes.setCadProd(result.getBoolean("cad_prod"));
			permissoes.setListProd(result.getBoolean("list_prod"));
			permissoes.setAltProd(result.getBoolean("alt_prod"));
			permissoes.setExclProduto(result.getBoolean("excl_prod"));
			permissoes.setCadNota(result.getBoolean("cad_nota"));
			permissoes.setListNota(result.getBoolean("list_nota"));
			permissoes.setMovEstoque(result.getBoolean("mov_estoque"));
			permissoes.setCadOrcamento(result.getBoolean("cad_orcamento"));
			permissoes.setLancConsignacao(result.getBoolean("lanc_consig"));
			permissoes.setVerConsig(result.getBoolean("ver_consig"));
			permissoes.setDashEstoque(result.getBoolean("dash_estoque"));
			permissoes.setDashVenda(result.getBoolean("dash_venda"));
			permissoes.setDashCaixa(result.getBoolean("dash_caixa"));
			permissoes.setDashCompra(result.getBoolean("dash_compra"));
			listPerm.add(permissoes);
		}

		return listPerm;
	}

	public void alterar(PermissoesFunc permissoes) throws SQLException {
		String sql = "UPDATE permissoes SET cad_cliente=?, list_cliente=?, alt_cliente=?, excl_cliente=?, list_func=?, cad_func=?, alt_func=?, excl_func=?, cad_prod=?, list_prod=?, alt_prod=?, excl_prod=?, cad_nota=?, list_nota=?, mov_estoque=?, cad_orcamento=?, lanc_consig=?, ver_consig=?, dash_estoque=?, dash_venda=?, dash_caixa=?, dash_compra=?, lancar_vendas=? WHERE id_func = ?;";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setBoolean(1, permissoes.isCadCliente());
		prepare.setBoolean(2, permissoes.isListCliente());
		prepare.setBoolean(3, permissoes.isAltCliente());
		prepare.setBoolean(4, permissoes.isExclCliente());
		prepare.setBoolean(5, permissoes.isListFunc());
		prepare.setBoolean(6, permissoes.isCadFunc());
		prepare.setBoolean(7, permissoes.isAltFunc());
		prepare.setBoolean(8, permissoes.isExclFunc());
		prepare.setBoolean(9, permissoes.isCadProd());
		prepare.setBoolean(10, permissoes.isListProd());
		prepare.setBoolean(11, permissoes.isAltProd());
		prepare.setBoolean(12, permissoes.isExclProduto());
		prepare.setBoolean(13, permissoes.isCadNota());
		prepare.setBoolean(14, permissoes.isListNota());
		prepare.setBoolean(15, permissoes.isMovEstoque());
		prepare.setBoolean(16, permissoes.isCadOrcamento());
		prepare.setBoolean(17, permissoes.isLancConsignacao());
		prepare.setBoolean(18, permissoes.isVerConsig());
		prepare.setBoolean(19, permissoes.isDashEstoque());
		prepare.setBoolean(20, permissoes.isDashVenda());
		prepare.setBoolean(21, permissoes.isDashCaixa());
		prepare.setBoolean(22, permissoes.isDashCompra());
		prepare.setBoolean(23, permissoes.isLancarVendas());
		prepare.setInt(24, permissoes.getIdFunc());
		prepare.execute();
		prepare.close();

	}

	public PermissoesFunc permissaoUsu(int idUsu) throws SQLException {
		String sql = "SELECT * FROM permissoes WHERE id_func=?;";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, idUsu);
		ResultSet result = prepare.executeQuery();
		PermissoesFunc permissoes = null;
		if (result.next()) {
			permissoes = new PermissoesFunc();
			permissoes.setIdFunc(result.getInt("id_func"));
			permissoes.setCadCliente(result.getBoolean("cad_cliente"));
			permissoes.setListCliente(result.getBoolean("list_cliente"));
			permissoes.setAltCliente(result.getBoolean("alt_cliente"));
			permissoes.setExclCliente(result.getBoolean("excl_cliente"));
			permissoes.setListFunc(result.getBoolean("list_func"));
			permissoes.setCadFunc(result.getBoolean("cad_func"));
			permissoes.setAltFunc(result.getBoolean("alt_func"));
			permissoes.setExclFunc(result.getBoolean("excl_func"));
			permissoes.setCadProd(result.getBoolean("cad_prod"));
			permissoes.setListProd(result.getBoolean("list_prod"));
			permissoes.setAltProd(result.getBoolean("alt_prod"));
			permissoes.setExclProduto(result.getBoolean("excl_prod"));
			permissoes.setCadNota(result.getBoolean("cad_nota"));
			permissoes.setListNota(result.getBoolean("list_nota"));
			permissoes.setMovEstoque(result.getBoolean("mov_estoque"));
			permissoes.setCadOrcamento(result.getBoolean("cad_orcamento"));
			permissoes.setLancConsignacao(result.getBoolean("lanc_consig"));
			permissoes.setVerConsig(result.getBoolean("ver_consig"));
			permissoes.setDashEstoque(result.getBoolean("dash_estoque"));
			permissoes.setDashVenda(result.getBoolean("dash_venda"));
			permissoes.setDashCaixa(result.getBoolean("dash_caixa"));
			permissoes.setDashCompra(result.getBoolean("dash_compra"));
			permissoes.setLancarVendas(result.getBoolean("lancar_vendas"));
		}

		return permissoes;
	}
}