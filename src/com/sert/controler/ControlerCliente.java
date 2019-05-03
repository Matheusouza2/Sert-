package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.ClienteDao;
import com.sert.dao.IClienteDao;
import com.sert.entidades.Cliente;
import com.sert.exceptions.ClienteJaCadastradoException;

public class ControlerCliente {
	
	private IClienteDao clienteDao;
	private Cliente cliente;
	
	public ControlerCliente() throws ClassNotFoundException, SQLException, IOException {
		clienteDao = new ClienteDao();
	}
	
	public void cadastrarCliente(Cliente cliente) throws SQLException, ClienteJaCadastradoException {
		this.cliente = consultaClienteCad(cliente.getCpf());
		if(this.cliente.getNome() != null) throw new ClienteJaCadastradoException();
		clienteDao.cadastrar(cliente);
	}
	
	public List<Cliente> listCliente() throws SQLException{
		return clienteDao.listar();
	}
	
	public void atualizarCliente(Cliente cliente) throws SQLException {
		clienteDao.atualizar(cliente);
	}
	
	public void excluirCliente(int id) throws SQLException {
		clienteDao.excluir(id);
	}
	
	public int confereId() throws SQLException {
		return clienteDao.confereId();
	}
	
	public Cliente consultaClienteAlter(int id) throws SQLException {
		return clienteDao.consultaAlter(id);
	}
	
	public Cliente consultaClienteCad(long cpf) throws SQLException {
		return clienteDao.consultaCad(cpf);
	}
}