package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.MercadoriaDao;
import com.sert.dao.MovEstoqueDao;
import com.sert.entidades.Mercadoria;

public class ControlerAjusteEstoque {

	MercadoriaDao mercadoriaDao;
	MovEstoqueDao movEstoqueDao;

	public ControlerAjusteEstoque() throws ClassNotFoundException, SQLException, IOException {
		mercadoriaDao = new MercadoriaDao();
		movEstoqueDao = new MovEstoqueDao();
	}

	public void movEstoque(List<Mercadoria> mercList, int operacao) throws SQLException {
		float novoEstoque = 0;
		float quant;
		for (Mercadoria merc : mercList) {
			quant = mercadoriaDao.procurarMerc(merc.getCodBarras()).getEstoque();
			if (operacao == 0) {
				novoEstoque = merc.getEstoque() + quant;
			} else if (operacao == 1) {
				novoEstoque = quant - merc.getEstoque();
			}
			mercadoriaDao.movEstoque(merc.getPrecoVenda(), novoEstoque, merc.getCodBarras());
		}
	}
}