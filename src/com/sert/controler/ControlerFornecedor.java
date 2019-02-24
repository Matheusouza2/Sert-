package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.FornecedorDAO;
import com.sert.dao.IFornecedorDAO;
import com.sert.entidades.Fornecedor;
import com.sert.exceptions.FornecedorJaCadastradoException;

public class ControlerFornecedor {

	private IFornecedorDAO dao;

	public ControlerFornecedor() throws ClassNotFoundException, SQLException, IOException {
		dao = new FornecedorDAO();
	}

	public void cadadastrar(Fornecedor fornecedor) throws SQLException, FornecedorJaCadastradoException {
		if (pesqFornecedor(fornecedor.getCnpjForn()).getCnpjForn() == fornecedor.getCnpjForn())
			throw new FornecedorJaCadastradoException();
		dao.cadastrar(fornecedor);
	}

	public List<Fornecedor> listarFornecedor() {

		return null;
	}

	public void alterarFornecedor(Fornecedor fornecedor) {

	}

	public void excluirFornecedor(int id) {

	}

	public Fornecedor pesqFornecedor(String cnpj) throws SQLException {
		return dao.pesquisar(cnpj);
	}
}