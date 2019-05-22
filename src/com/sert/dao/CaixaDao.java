package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Caixa;
import com.sert.entidades.Usuario;

public class CaixaDao implements ICaixaDao {

	Connection con;

	public CaixaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void lancamentoCaixa(Caixa caixa) throws SQLException {
		String sql = "INSERT INTO caixa(historico, id_operador, id_usuario, data_operacao, retirada, dinheiro, val_dinheiro, cartao, val_cartao, duplicata) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
	public List<Caixa> historicoCaixa(String dataInicial, String dataFinal) throws SQLException {
		String sql = "SELECT historico, id_operador, f.nome, id_usuario, to_char(data_operacao, 'dd/MM/yyyy HH:mm:SS') data_operacao, retirada, dinheiro, val_dinheiro, cartao, val_cartao, duplicata FROM caixa cx INNER JOIN funcionario f ON f.id = cx.id_usuario WHERE data_operacao BETWEEN '"
				+ dataInicial + " 00:00:00' AND '" + dataFinal + " 23:59:59';";

		PreparedStatement prepare = con.prepareStatement(sql);

		ResultSet resultado = prepare.executeQuery();
		List<Caixa> caixaList = new ArrayList<Caixa>();
		Caixa caixa;
		Usuario usu;
		while (resultado.next()) {
			caixa = new Caixa();
			usu = new Usuario();
			caixa.setHistorico(resultado.getString("historico").trim());
			caixa.setCartao(resultado.getInt("cartao"));
			caixa.setDataOperacao(resultado.getString("data_operacao"));
			caixa.setDinheiro(resultado.getInt("dinheiro"));
			caixa.setDuplicata(resultado.getInt("duplicata"));
			caixa.setIdUsuario(resultado.getInt("id_usuario"));
			caixa.setRetirada(resultado.getBoolean("retirada"));
			caixa.setValorCartao(resultado.getFloat("val_cartao"));
			caixa.setValorDinheiro(resultado.getFloat("val_dinheiro"));
			usu.setNome(resultado.getString("nome").trim());
			caixa.setUsuario(usu);
			caixaList.add(caixa);
		}
		return caixaList;
	}

	@Override
	public Caixa confereCaixa() throws SQLException {

		return null;
	}

}