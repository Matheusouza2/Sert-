package com.sert.tables;

import com.sert.entidades.NFeEntrada;

import ca.odell.glazedlists.gui.TableFormat;

public class TableModelNFe implements TableFormat<Object> {

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnName) {
		if (columnName == 0)
			return "Id";
		else if (columnName == 1)
			return "Numero NFe";
		else if (columnName == 2)
			return "Chave de acesso";
		else if (columnName == 3)
			return "Fornecedor";
		else
			return null;
	}

	@Override
	public Object getColumnValue(Object base, int column) {
		NFeEntrada entrada = (NFeEntrada) base;
		if (column == 0)
			return entrada.getId();
		else if (column == 1)
			return entrada.getNumNota();
		else if (column == 2)
			return entrada.getChave();
		else if (column == 3)
			return entrada.getFornecedor().getRazSocial();
		else
			return null;
	}
}