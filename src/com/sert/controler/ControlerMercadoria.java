package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.MercadoriaDao;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

public class ControlerMercadoria {

	MercadoriaDao mercadoriaDao;
	private static long codBarras;
	private List<Mercadoria> listMercConfere;

	public ControlerMercadoria() throws ClassNotFoundException, SQLException, IOException {
		mercadoriaDao = new MercadoriaDao();
	}

	public void cadastrarMercadoria(Mercadoria mercadoria) throws SQLException, CodBarrasJaCadastradoException,
			MercadoriaNaoEncontradaException, ClassNotFoundException, IOException {
		if (consultaMercadoriaCad(mercadoria.getCodBarras()).getCodBarras() == 0) {
			mercadoriaDao.cadastro(mercadoria);
		} else {
			throw new CodBarrasJaCadastradoException();
		}
	}

	public List<Mercadoria> listarMercadorias() throws NenhumaMercadoriaCadastradaException, SQLException {
		if (mercadoriaDao.listar() == null) {
			throw new NenhumaMercadoriaCadastradaException();
		} else {
			return mercadoriaDao.listar();
		}

	}

	public void alterarMercadoria(Mercadoria mercadoria) throws SQLException, CodBarrasJaCadastradoException {
		listMercConfere = mercadoriaDao.listar();
		boolean atualiza = false;
		for (int i = 0; i < listMercConfere.size() - 1; i++) {
			if (listMercConfere.get(i).getCodBarras() == codBarras) {
				listMercConfere.remove(i);
			}
			if (mercadoria.getCodBarras() == listMercConfere.get(i).getCodBarras()) {
				atualiza = false;
				break;
			} else {
				atualiza = true;
			}
		}
		if (atualiza == true) {
			mercadoriaDao.alterar(mercadoria);
		} else {
			throw new CodBarrasJaCadastradoException();
		}
	}

	public void excluirMercadoria(int id) throws SQLException {
		mercadoriaDao.excluir(id);
	}

	public Mercadoria consultaMercadoria(long codBarras) throws SQLException, MercadoriaNaoEncontradaException {
		ControlerMercadoria.codBarras = codBarras;
		if (mercadoriaDao.procurarMerc(codBarras) == null)
			throw new MercadoriaNaoEncontradaException();

		return mercadoriaDao.procurarMerc(codBarras);
	}

	public Mercadoria consultaMercadoriaCad(long codBarras) throws SQLException {

		return mercadoriaDao.procurarMerc(codBarras);
	}

	public int confereId() throws SQLException {
		return mercadoriaDao.confereId();
	}

	public void entradaMercadoria(Mercadoria mercadoria, long codFornecedor) throws SQLException {
		Mercadoria merc = consultaMercadoriaCad(mercadoria.getCodBarras());
		float estoque = merc.getEstoque() + mercadoria.getEstoque();

		mercadoria.setEstoque(estoque);
		mercadoriaDao.entradaNotaEstoque(mercadoria, codFornecedor);
	}

	public void cadastrarMercadoriaNf(Mercadoria mercadoria) throws SQLException, ClassNotFoundException, IOException {
		mercadoria.setId(new ControlerMercadoria().confereId());
		mercadoriaDao.cadastro(mercadoria);
	}

	public void saidaMercadoria(Mercadoria mercadoria) throws SQLException {
		mercadoriaDao.entradaNotaEstoque(mercadoria, 0);
	}

	public void extratoMerc(int codMerc) throws SQLException {
		mercadoriaDao.extratoMerc(codMerc);		
	}
}