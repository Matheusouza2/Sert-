package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sert.entidades.Empresa;

public class EmpresaDao implements IEmpresaDao{
	private Connection con;
	private List<Empresa> listEmpresa;
	private Empresa empresa;
	
	
	public EmpresaDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector(); 
	}
	
	@Override
	public void cadastra(Empresa empresa) throws SQLException {
		String sql = "INSERT INTO empresa(cnpj, nome, nome_fant, rua, numero_end, bairro, cidade, uf, contato, ie) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setLong(1, empresa.getCnpj());
		preparador.setString(2, empresa.getNome());
		preparador.setString(3, empresa.getNomeFant());
		preparador.setString(4, empresa.getRua());
		preparador.setInt(5, empresa.getNumero());
		preparador.setString(6, empresa.getBairro());
		preparador.setString(7, empresa.getCidade());
		preparador.setString(8, empresa.getUf());
		preparador.setLong(9, empresa.getContato());
		preparador.setInt(10, empresa.getIe());
		preparador.execute();
		preparador.close();
		
	}

	@Override
	public List<Empresa> listar() throws SQLException {
		String sql = "SELECT * FROM empresa";
		PreparedStatement preparador = con.prepareStatement(sql);
		ResultSet result = preparador.executeQuery();
		
		listEmpresa = new ArrayList<>();
		
		while (result.next()) {
			empresa = new Empresa();
			empresa.setCnpj(result.getLong("cnpj"));
			empresa.setNome(result.getString("nome"));
			empresa.setNomeFant(result.getString("nome_fant"));
			empresa.setRua(result.getString("rua"));
			empresa.setNumero(result.getInt("numero_end"));
			empresa.setBairro(result.getString("bairro"));
			empresa.setCidade(result.getString("cidade"));
			empresa.setUf(result.getString("uf"));
			empresa.setContato(result.getLong("contato"));
			empresa.setIe(result.getInt("ie"));
			listEmpresa.add(empresa);
		}
		return listEmpresa;
	}

	@Override
	public void alterar(Empresa empresa) throws SQLException {
		String sql = "UPDATE empresa SET cnpj=?, nome=?, nome_fant=?, rua=?, numero_end=?, bairro=?, cidade=?, uf=?, contato=?, ie=? WHERE cnpj = ?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setString(1, empresa.getNome());
		preparador.setString(2, empresa.getNomeFant());
		preparador.setString(3, empresa.getRua());
		preparador.setInt(4, empresa.getNumero());
		preparador.setString(5, empresa.getBairro());
		preparador.setString(6, empresa.getCidade());
		preparador.setString(7, empresa.getUf());
		preparador.setLong(8, empresa.getContato());
		preparador.setInt(9, empresa.getIe());
		preparador.setLong(10, empresa.getCnpj());
		preparador.executeUpdate();
		preparador.close();
	}

	@Override
	public Empresa consultar(long cnpj) throws SQLException {
		
		String sql = "SELECT * FROM empresa WHERE cnpj=?";
		PreparedStatement preparador = con.prepareStatement(sql);
		preparador.setLong(1, cnpj);
		ResultSet result = preparador.executeQuery();
		
		while (result.next()) {
			empresa = new Empresa();
			empresa.setCnpj(result.getLong("cnpj"));
			empresa.setNome(result.getString("nome"));
			empresa.setNomeFant(result.getString("nome_fant"));
			empresa.setRua(result.getString("rua"));
			empresa.setNumero(result.getInt("numero_end"));
			empresa.setBairro(result.getString("bairro"));
			empresa.setCidade(result.getString("cidade"));
			empresa.setUf(result.getString("uf"));
			empresa.setContato(result.getLong("contato"));
			empresa.setIe(result.getInt("ie"));
		}
		return empresa;
	}
}