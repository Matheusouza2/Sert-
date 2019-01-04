package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.IMercadoriaDao;
import com.sert.dao.MercadoriaDao;
import com.sert.entidades.Mercadoria;

public class ControlerAjusteEstoque {

	IMercadoriaDao mercadoriaDao;

	public ControlerAjusteEstoque() throws ClassNotFoundException, SQLException, IOException {
		mercadoriaDao = new MercadoriaDao();
	}

	public void movEstoque(List<Mercadoria> mercList, int operacao) throws SQLException {
		for (Mercadoria merc : mercList) {
			if (operacao == 0) {
				float quant = mercadoriaDao.procurarMerc(merc.getCodBarras()).getEstoque();
				float novoEstoque = merc.getEstoque() + quant;
				mercadoriaDao.entradaNotaEstoque(novoEstoque, merc.getCodBarras());
			} else if (operacao == 1) {
				float quant = mercadoriaDao.procurarMerc(merc.getCodBarras()).getEstoque();
				float novoEstoque = merc.getEstoque() - quant;
				mercadoriaDao.entradaNotaEstoque(novoEstoque, merc.getCodBarras());
			}
		}
	}
}