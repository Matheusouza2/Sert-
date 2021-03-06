package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.VendaDao;
import com.sert.entidades.Caixa;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.MercadoriaSemEstoqueException;
import com.sert.exceptions.MercadoriaSemPrecoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.NenhumaVendaRalizadaException;
import com.sert.exceptions.VendaNaoEncontradaException;
import com.sert.telas.ListarMercadorias;
import com.sert.telas.PesqMercVenda;

public class ControlerVenda {

	private VendaDao vendaDao;
	private List<Venda> vendas;
	public static List<Mercadoria> mercadorias;

	public ControlerVenda()
			throws ClassNotFoundException, SQLException, IOException, NenhumaMercadoriaCadastradaException {
		vendaDao = new VendaDao();
		if (mercadorias == null || mercadorias.isEmpty()) {
			mercadorias = new ControlerMercadoria().listarMercadorias();
		}
	}

	public void finalizarVenda(Venda venda) throws SQLException, ClassNotFoundException,
			NenhumaMercadoriaCadastradaException, IOException, MercadoriaNaoEncontradaException {
		vendaDao.cadastrarVenda(venda);
		Mercadoria mercadoriaBaixa;
		List<Mercadoria> baixaEstoque = new ControlerMercadoria().listarMercadorias();
		for (int i = 0; i < baixaEstoque.size(); i++) {
			for (int j = 0; j < venda.getMercadorias().size(); j++) {
				if (baixaEstoque.get(i).getCodBarras() == venda.getMercadorias().get(j).getCodBarras()) {
					mercadoriaBaixa = new Mercadoria();
					float estoque = baixaEstoque.get(i).getEstoque() - venda.getMercadorias().get(j).getEstoque();
					mercadoriaBaixa.setCodBarras(venda.getMercadorias().get(j).getCodBarras());
					mercadoriaBaixa.setEstoque(estoque);
					new ControlerMercadoria().saidaMercadoria(mercadoriaBaixa);
				}
			}
		}

		if (venda.getDuplicata() == 1) {
			new ControlerDuplicata().lancarDuplicataVenda(venda);
		}
		
		if(venda.getCartao() == 1 || venda.getDinheiro() == 1) {
			Caixa caixa = new Caixa();
			caixa.setHistorico("Venda número: "+venda.getId());
			caixa.setIdUsuario(venda.getVendedorCad());
			caixa.setDataOperacao(venda.getDataVenda());
			caixa.setRetirada(false);
			caixa.setIdOperador(venda.getId());
			if (venda.getCartao() == 1) {
				caixa.setCartao(1);
				caixa.setValorCartao(venda.getValCartao());
			}
			if (venda.getDinheiro() == 1) {
				caixa.setDinheiro(1);
				caixa.setValorDinheiro(venda.getValDInheiro());
			}
			new ControlerCaixa().lancamentoCaixa(caixa);
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

	public Mercadoria consultaMercVenda(long codBarras, float quant)
			throws MercadoriaSemEstoqueException, MercadoriaSemPrecoException {
		Mercadoria mercadoria = null;
		for (int i = 0; i < mercadorias.size(); i++) {
			if (codBarras == mercadorias.get(i).getCodBarras()) {
				if (mercadorias.get(i).getPrecoVenda() > 0) {
					if (mercadorias.get(i).getEstoque() >= quant) {
						mercadoria = new Mercadoria();
						mercadoria.setId(mercadorias.get(i).getId());
						mercadoria.setCodBarras(mercadorias.get(i).getCodBarras());
						mercadoria.setMercadoria(mercadorias.get(i).getMercadoria());
						mercadoria.setPrecoVenda(mercadorias.get(i).getPrecoVenda());
						mercadoria.setPrecoCompra(mercadorias.get(i).getPrecoCompra());
						mercadorias.get(i).setEstoque(mercadorias.get(i).getEstoque() - quant);
						return mercadoria;
					} else {
						throw new MercadoriaSemEstoqueException();
					}
				} else {
					throw new MercadoriaSemPrecoException();
				}

			}
		}
		return mercadoria;
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

	public void atualizarCadastros()
			throws ClassNotFoundException, NenhumaMercadoriaCadastradaException, SQLException, IOException {
		mercadorias.clear();
		mercadorias = new ControlerMercadoria().listarMercadorias();
		PesqMercVenda.setPreencheTable(mercadorias);
		ListarMercadorias.setPreencheTable(mercadorias);
	}

	public List<Venda> pesquisarVenda(String dtInicial, String dtFinal) throws SQLException {
		return vendaDao.pesquisarVenda(dtInicial, dtFinal);
	}

	public Venda imprimirVenda(int id) throws SQLException, VendaNaoEncontradaException {
		if (vendaDao.imprimirVenda(id).getMercadorias() == null)
			throw new VendaNaoEncontradaException();

		return vendaDao.imprimirVenda(id);
	}
}