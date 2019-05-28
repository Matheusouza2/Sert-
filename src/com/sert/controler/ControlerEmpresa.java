package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.EmpresaDao;
import com.sert.entidades.Empresa;

public class ControlerEmpresa {

	private EmpresaDao empresaDao;

	public ControlerEmpresa() throws ClassNotFoundException, SQLException, IOException {
		empresaDao = new EmpresaDao();
	}

	public void cadastrarEmpresa(Empresa empresa) throws SQLException {
		empresaDao.cadastra(empresa);
	}

	public List<Empresa> listEmpresa() throws SQLException {
		return empresaDao.listar();
	}

	public void alterarEmpresa(Empresa empresa) throws SQLException {
		empresaDao.alterar(empresa);
	}

	public Empresa consultarEmpresa(long cnpj) throws SQLException {
		return empresaDao.consultar(cnpj);
	}
}