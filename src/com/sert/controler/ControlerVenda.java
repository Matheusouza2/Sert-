package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.IVendasDao;
import com.sert.dao.VendaDao;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.MercadoriaSemEstoqueException;
import com.sert.exceptions.MercadoriaSemPrecoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.NenhumaVendaRalizadaException;

public class ControlerVenda {

	private IVendasDao vendaDao;
	private List<Venda> vendas;
	private static List<Mercadoria> mercadorias;

	public ControlerVenda()
			throws ClassNotFoundException, SQLException, IOException, NenhumaMercadoriaCadastradaException {
		vendaDao = new VendaDao();
		if (!mercadorias.isEmpty()) {
			mercadorias = new ControlerMercadoria().listarMercadorias();
		}
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

	public Mercadoria consultaMercVenda(long codBarras, float quant) throws MercadoriaSemEstoqueException, MercadoriaSemPrecoException {

		for (int i = 0; i < mercadorias.size(); i++) {
			Mercadoria mercadoria = new Mercadoria();
			mercadoria.setId(mercadorias.get(i).getId());
			mercadoria.setCodBarras(mercadorias.get(i).getCodBarras());
			mercadoria.setMercadoria(mercadorias.get(i).getMercadoria());
			mercadoria.setPrecoVenda(mercadorias.get(i).getPrecoVenda());
			if (codBarras == mercadorias.get(i).getCodBarras()) {
				if(mercadorias.get(i).getPrecoVenda() != 0) {
					if (mercadorias.get(i).getEstoque() >= quant) {
						mercadorias.get(i).setEstoque(mercadorias.get(i).getEstoque() - quant);
						return mercadoria;
					} else {
						throw new MercadoriaSemEstoqueException();
					}
				}else {
					throw new MercadoriaSemPrecoException();
				}
				
			}
		}
		return null;
	}

	public void cancelVenda(List<Mercadoria> merc) {
		for (int i = 0; i < mercadorias.size(); i++) {
			for (int j = 0; j < merc.size(); j++) {
				if (mercadorias.get(i).getId() == merc.get(j).getId()) {
					mercadorias.get(i).setEstoque(mercadorias.get(i).getEstoque() + merc.get(j).getEstoque());
				}
			}
		}
	}
}