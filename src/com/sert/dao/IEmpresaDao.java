package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Empresa;

public interface IEmpresaDao {

	public void cadastra(Empresa empresa) throws SQLException;
	public List<Empresa> listar() throws SQLException;
	public void alterar(Empresa empresa) throws SQLException;
	public Empresa consultar(long cnpj) throws SQLException;
}