package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sert.dao.DuplicataDao;
import com.sert.dao.IDuplicataDao;
import com.sert.entidades.Caixa;
import com.sert.entidades.Cliente;
import com.sert.entidades.DuplicataCliente;
import com.sert.entidades.Usuario;
import com.sert.entidades.Venda;

public class ControlerDuplicata {

	private IDuplicataDao duplicataDao;
	private Cliente cliente;
	private Usuario usuario;

	public ControlerDuplicata() throws ClassNotFoundException, SQLException, IOException {
		duplicataDao = new DuplicataDao();
	}

	public void lancarDuplicata(DuplicataCliente duplicata) throws SQLException {
		duplicataDao.lancarDuplicata(duplicata);
	}

	public void lancarDuplicataVenda(Venda venda) throws SQLException {
		cliente = new Cliente();
		usuario = new Usuario();
		cliente.setId(venda.getClienteCad());
		usuario.setId(0);
		DuplicataCliente duplicata = new DuplicataCliente();
		duplicata.setVenda(venda);
		duplicata.setCliente(cliente);
		duplicata.setSituacao("A vencer");
		duplicata.setNumParcela(venda.getParcelasDuplicata());
		duplicata.setValor(venda.getValDuplicata() / venda.getParcelasDuplicata());
		duplicata.setValorBaixa(0);
		duplicata.setUsuBaixa(usuario);

		duplicataDao.lancarDuplicata(duplicata);
	}

	public List<DuplicataCliente> listDuplicata() throws SQLException {
		return duplicataDao.verDuplicatas();
	}

	public void baixarDuplicata(DuplicataCliente duplicata) throws SQLException, ClassNotFoundException, IOException {
		duplicataDao.baixarDuplicata(duplicata);
		Caixa caixa = new Caixa();
		caixa.setHistorico("Baixa na duplicata número: "+duplicata.getId());
		caixa.setDataOperacao(duplicata.getDataBaixa());
		caixa.setValorDinheiro(duplicata.getValorBaixa());
		caixa.setDuplicata(1);
		caixa.setIdOperador(duplicata.getId());
		caixa.setIdUsuario(duplicata.getUsuBaixa().getId());
		caixa.setRetirada(false);
		new ControlerCaixa().lancamentoCaixa(caixa);
	}

	public DuplicataCliente consultaDuplicata(int idDuplicata) throws SQLException {
		return duplicataDao.consultarDuplicata(idDuplicata);
	}
}