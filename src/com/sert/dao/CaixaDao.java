package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Caixa;

public class CaixaDao implements ICaixaDao {

	private Connection con;
	private List<Caixa> caixaList;
	private Caixa caixa;

	public CaixaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	@Override
	public void abrirCaixa(Caixa caixa) throws SQLException {
		String sql = "INSERT INTO func_caixa(data_abertura, valor_abertura, usuario_abertura, situacao) VALUES (?, ?, ?, ?)";
		PreparedStatement prepare = con.prepareStatement(sql);

		prepare.setString(1, caixa.getDataCaixaAbertura());
		prepare.setFloat(2, caixa.getValorAbertura());
		prepare.setInt(3, caixa.getUsuAbertura());
		prepare.setInt(4, caixa.getSituacao());

		prepare.execute();
		prepare.close();
	}

	@Override
	public List<Caixa> historicoCaixa() throws SQLException {
		String sql = "SELECT f.data_abertura, f.valor_abertura, func.nome, f.data_fechamento, f.valor_fechamento, func.nome, f.situacao FROM func_caixa f INNER JOIN funcionario func ON f.usuario_abertura = func.id AND f.usuario_fechamento = func.id";
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		caixaList = new ArrayList<>();

		while (resultado.next()) {
			caixa = new Caixa();

			caixa.setDataCaixaAbertura(resultado.getString("data_abertura"));
			caixa.setValorAbertura(resultado.getFloat("valor_abertura"));
			caixa.setUsuAberturaNome(resultado.getString("nome"));
			caixa.setDataCaixaFechamento(resultado.getString("data_fechamento"));
			caixa.setValorFechamento(resultado.getFloat("valor_fechamento"));
			caixa.setUsuFechamentoNome(resultado.getString("nome"));
			caixaList.add(caixa);
		}
		return caixaList;
	}

	@Override
	public Caixa confereCaixa() throws SQLException {
		String sql = "SELECT f.data_abertura, f.valor_abertura, func.nome, f.data_fechamento, f.valor_fechamento, func.nome, f.situacao FROM func_caixa f INNER JOIN funcionario func ON f.usuario_abertura = func.id AND f.usuario_fechamento = func.id";
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet resultado = prepare.executeQuery();
		caixa = new Caixa();
		if (resultado.next()) {
			caixa = new Caixa();
			caixa.setDataCaixaAbertura(resultado.getString("data_abertura"));
			caixa.setValorAbertura(resultado.getFloat("valor_abertura"));
			caixa.setUsuAberturaNome(resultado.getString("nome"));
			caixa.setDataCaixaFechamento(resultado.getString("data_fechamento"));
			caixa.setValorFechamento(resultado.getFloat("valor_fechamento"));
			caixa.setUsuFechamentoNome(resultado.getString("nome"));
		}

		resultado.close();
		return caixa;
	}

	@Override
	public void fecharCaixa(Caixa caixa) throws SQLException {
		String sql = "UPDATE func_caixa SET data_fechamento=?, valor_fechamento=?, usuario_fechamento=?, situacao=? WHERE situacao = 1";
		PreparedStatement prepare = con.prepareStatement(sql);

		prepare.setString(1, caixa.getDataCaixaFechamento());
		prepare.setFloat(2, caixa.getValorFechamento());
		prepare.setInt(3, caixa.getUsuFechamento());
		prepare.setInt(4, 0);
		prepare.executeUpdate();
		prepare.close();
	}
	
	
}