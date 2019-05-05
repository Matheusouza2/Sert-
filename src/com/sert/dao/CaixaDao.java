package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.sert.entidades.Caixa;

public class CaixaDao implements ICaixaDao {

	Connection con;
	
	
	public CaixaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}
	
	@Override
	public void lancamentoCaixa(Caixa caixa) throws SQLException {
		String sql = "INSERT INTO caixa(historico, id_operador, id_usuario, data_operacao, retirada, dinheiro, val_dinheiro, cartao, val_cartao, duplicata) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement prepare = con.prepareStatement(sql);
		prepare.setString(1, caixa.getHistorico());
		prepare.setInt(2, caixa.getIdOperador());
		prepare.setInt(3, caixa.getIdUsuario());
		prepare.setTimestamp(4, Timestamp.valueOf(caixa.getDataOperacao()));
		prepare.setBoolean(5, caixa.isRetirada());
		prepare.setInt(6, caixa.getDinheiro());
		prepare.setFloat(7, caixa.getValorDinheiro());
		prepare.setInt(8, caixa.getCartao());
		prepare.setFloat(9, caixa.getValorCartao());
		prepare.setInt(10, caixa.getDuplicata());
		prepare.execute();
		prepare.close();
	}

	@Override
	public List<Caixa> historicoCaixa() throws SQLException {
		
		return null;
	}

	@Override
	public Caixa confereCaixa() throws SQLException {

		return null;
	}
	
}