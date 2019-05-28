package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.sert.entidades.Cliente;
import com.sert.entidades.DuplicataCliente;
import com.sert.entidades.Usuario;
import com.sert.entidades.Venda;

public class DuplicataDao {

	private Connection con;
	private List<DuplicataCliente> listDuplicata;
	private DuplicataCliente duplicata;
	private Cliente cliente;
	private Venda venda;
	private Usuario usuario;

	public DuplicataDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void lancarDuplicata(DuplicataCliente duplicata) throws SQLException {
		String sql = "INSERT INTO duplicatas(cliente, id_venda, valor_duplicata, situacao, data_vencimento, num_parcela, usuario_baixa, valor_baixa) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement prepare = con.prepareStatement(sql);

		for (int i = 0; i < duplicata.getNumParcela(); i++) {

			GregorianCalendar gc = new GregorianCalendar();
			Date diaAtual = new Date();

			gc.setTime(diaAtual);
			gc.add(GregorianCalendar.MONTH, i + 1);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = gc.getTime();

			prepare.setInt(1, duplicata.getCliente().getId());
			prepare.setInt(2, duplicata.getVenda().getId());
			prepare.setFloat(3, duplicata.getValor());
			prepare.setString(4, duplicata.getSituacao());
			prepare.setTimestamp(5, Timestamp.valueOf(df.format(d)));
			prepare.setInt(6, i + 1);
			prepare.setInt(7, duplicata.getUsuBaixa().getId());
			prepare.setFloat(8, duplicata.getValorBaixa());
			prepare.execute();
		}
		prepare.close();
	}

	public List<DuplicataCliente> verDuplicatas() throws SQLException {
		String sql = "SELECT dp.id, cl.nome as nome_cliente, cl.id as id_cliente, id_venda, valor_duplicata, situacao, to_char(v.data_venda, 'dd/MM/yyyy hh:MM:ss') data_venda, to_char(dp.data_vencimento, 'dd/MM/yyyy') data_vencimento, dp.num_parcela FROM duplicatas dp INNER JOIN clientes cl ON dp.cliente = cl.id INNER JOIN vendas v ON v.id = dp.id_venda ORDER BY data_vencimento ASC;";
		listDuplicata = new ArrayList<DuplicataCliente>();
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet result = prepare.executeQuery();

		while (result.next()) {
			duplicata = new DuplicataCliente();
			cliente = new Cliente();
			venda = new Venda();

			duplicata.setId(result.getInt("id"));
			cliente.setId(result.getInt("id_cliente"));
			cliente.setNome(result.getString("nome_cliente").trim());
			duplicata.setCliente(cliente);
			venda.setId(result.getInt("id_venda"));
			venda.setDataVenda(result.getString("data_venda").trim());
			duplicata.setVenda(venda);
			duplicata.setValor(result.getFloat("valor_duplicata"));
			duplicata.setSituacao(result.getString("situacao").trim());
			duplicata.setDataVencimento(result.getString("data_vencimento").trim());
			duplicata.setNumParcela(result.getInt("num_parcela"));

			listDuplicata.add(duplicata);
		}
		return listDuplicata;
	}

	public void baixarDuplicata(DuplicataCliente duplicata) throws SQLException {
		String sql = "UPDATE duplicatas SET situacao=?, usuario_baixa=?, data_baixa=?, valor_baixa=? WHERE id=?;";
		PreparedStatement statment = con.prepareStatement(sql);
		statment.setString(1, duplicata.getSituacao());
		statment.setInt(2, duplicata.getUsuBaixa().getId());
		statment.setTimestamp(3, Timestamp.valueOf(duplicata.getDataBaixa()));
		statment.setFloat(4, duplicata.getValorBaixa());
		statment.setInt(5, duplicata.getId());
		statment.executeUpdate();
	}

	public void cancelarDuplicata(int id) throws SQLException {

	}

	public DuplicataCliente consultarDuplicata(int id) throws SQLException {
		String sql = "SELECT dp.id, cl.nome as nome_cliente, cl.id as id_cliente, cl.cpf, fc.nome as nome_func, id_venda, valor_duplicata, situacao, to_char(v.data_venda, 'dd/MM/yyyy hh:MM:ss') data_venda, to_char(dp.data_vencimento, 'dd/MM/yyyy') data_vencimento, dp.num_parcela, to_char(dp.data_baixa, 'dd/MM/yyyy') data_baixa, (SELECT nome FROM funcionario WHERE id = dp.usuario_baixa), valor_baixa FROM duplicatas dp INNER JOIN clientes cl ON dp.cliente = cl.id INNER JOIN vendas v ON v.id = dp.id_venda INNER JOIN funcionario fc ON v.vendedor = fc.id WHERE dp.id = "
				+ id;
		duplicata = new DuplicataCliente();
		PreparedStatement prepare = con.prepareStatement(sql);
		ResultSet result = prepare.executeQuery();

		if (result.next()) {
			duplicata = new DuplicataCliente();
			cliente = new Cliente();
			usuario = new Usuario();
			venda = new Venda();

			duplicata.setId(result.getInt("id"));
			cliente.setId(result.getInt("id_cliente"));
			cliente.setNome(result.getString("nome_cliente").trim());
			cliente.setCpf(result.getLong("cpf"));
			duplicata.setCliente(cliente);
			venda.setId(result.getInt("id_venda"));
			venda.setVendedor(result.getString("nome_func").trim());
			venda.setDataVenda(result.getString("data_venda").trim());
			duplicata.setVenda(venda);
			duplicata.setValor(result.getFloat("valor_duplicata"));
			duplicata.setSituacao(result.getString("situacao").trim());
			duplicata.setDataVencimento(result.getString("data_vencimento").trim());
			duplicata.setNumParcela(result.getInt("num_parcela"));
			duplicata.setDataBaixa(result.getString("data_baixa"));
			duplicata.setValorBaixa(result.getFloat("valor_baixa"));
			usuario.setNome(result.getString("nome").trim());
			duplicata.setUsuBaixa(usuario);

		}
		return duplicata;
	}

	public void mudarStatusDuplicata(int id, String situacao) throws SQLException {
		String sql = "UPDATE duplicatas SET situacao=? WHERE id=?;";
		PreparedStatement statment = con.prepareStatement(sql);
		statment.setString(1, situacao);
		statment.setInt(2, id);
		statment.executeUpdate();
		statment.close();
	}
}