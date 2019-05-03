package com.sert.dao;

import java.sql.SQLException;
import java.util.List;

import com.sert.entidades.Cliente;

public interface IClienteDao {

	public void cadastrar(Cliente cliente) throws SQLException;

	public List<Cliente> listar() throws SQLException;

	public void atualizar(Cliente cliente) throws SQLException;

	public void excluir(int id) throws SQLException;

	public Cliente consulta(String id, String cnpj) throws SQLException;

	public int confereId() throws SQLException;

	Cliente consultaCad(long cnpj) throws SQLException;

	Cliente consultaAlter(int id) throws SQLException;
}