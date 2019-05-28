package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.FornecedorDAO;
import com.sert.entidades.Fornecedor;
import com.sert.exceptions.FornecedorJaCadastradoException;

public class ControlerFornecedor {

	private FornecedorDAO dao;

	public ControlerFornecedor() throws ClassNotFoundException, SQLException, IOException {
		dao = new FornecedorDAO();
	}

	public void cadadastrar(Fornecedor fornecedor) throws SQLException, FornecedorJaCadastradoException {
		fornecedor.setId(getIdForn());
		if (pesqFornecedor(fornecedor.getCnpjForn()).getCnpjForn() == null)
			dao.cadastrar(fornecedor);
		else
			throw new FornecedorJaCadastradoException();
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

	public int getIdForn() throws SQLException {
		return dao.recuperaId();
	}
}