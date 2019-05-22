package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Orcamento;

public class OrcamentoDao implements IOrcamentoDAO {

	Connection con;

	public OrcamentoDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void cadastrar(Orcamento orcamento) throws SQLException {
		String sql = "INSERT INTO public.orcamento(id, cliente, vendedor, val_total) VALUES (?, ?, ?, ?);";
		String sql2 = "INSERT INTO orcamento_merc(id_orcamento, id_merc, quant, val_un)	VALUES (?, ?, ?, ?);";
		String seq = "ALTER SEQUENCE orcamento_id_seq RESTART WITH " + orcamento.getId();
		
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setInt(1, orcamento.getId());
		prepare.setInt(2, orcamento.getCliente().getId());
		prepare.setInt(3, orcamento.getUsuario().getId());
		prepare.setFloat(4, orcamento.getValTotal());
		prepare.execute();
		prepare.close();
		
		prepare = con.prepareStatement(sql2);
		for (int i = 0; i < orcamento.getMercadorias().size(); i++) {
			prepare.setInt(1, orcamento.getId());
			prepare.setInt(2, orcamento.getMercadorias().get(i).getId());
			prepare.setFloat(3, orcamento.getMercadorias().get(i).getEstoque());
			prepare.setFloat(4, orcamento.getMercadorias().get(i).getPrecoVenda());
			prepare.execute();
		}
		
		prepare = con.prepareStatement(seq);
		prepare.execute();
		prepare.close();
	}

	@Override
	public List<Orcamento> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(Orcamento orcamento) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Orcamento consultaOrcamentoAlt(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int consultaIdOrcamento() throws SQLException {
		int id = 0;
		String sql = "SELECT last_value + 1 AS idOrcamento FROM orcamento_id_seq";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			id = resultSet.getInt("idOrcamento");
		}
		
		return id;
	}

}
