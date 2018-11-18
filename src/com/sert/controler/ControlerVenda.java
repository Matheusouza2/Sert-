package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.IVendasDao;
import com.sert.dao.VendaDao;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.NenhumaVendaRalizadaException;

public class ControlerVenda {

	private IVendasDao vendaDao;
	private List<Venda> vendas;

	public ControlerVenda() throws ClassNotFoundException, SQLException, IOException {
		vendaDao = new VendaDao();
	}

	public void finalizarVenda(Venda venda) throws SQLException, ClassNotFoundException,
			NenhumaMercadoriaCadastradaException, IOException, MercadoriaNaoEncontradaException {
		vendaDao.cadastrarVenda(venda);
		List<Mercadoria> baixaEstoque = new ControlerMercadoria().listarMercadorias();
		for (int i = 0; i < baixaEstoque.size(); i++) {
			for (int j = 0; j < venda.getMercadorias().size(); j++) {
				if (baixaEstoque.get(i).getCodBarras() == venda.getMercadorias().get(j).getCodBarras()) {
					float estoque = baixaEstoque.get(i).getEstoque() - venda.getMercadorias().get(j).getEstoque();
					new ControlerMercadoria().entradaMercadoria(estoque, venda.getMercadorias().get(j).getCodBarras());
				}
			}
		}
	}

	public List<Venda> listarVendas() throws SQLException, NenhumaVendaRalizadaException {
		vendas = vendaDao.listarVendas();
		if (vendas.isEmpty())
			throw new NenhumaVendaRalizadaException();

		return vendas;
	}

	public int getIdVenda() throws SQLException {
		return vendaDao.getIdVenda();
	}
}