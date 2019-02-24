package com.sert.tables;

import com.sert.entidades.Venda;

import ca.odell.glazedlists.gui.TableFormat;

public class TableModelVenda implements TableFormat<Object> {

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int column) {
		if (column == 0)
			return "Nº Venda";
		else if (column == 1)
			return "Data";
		else if (column == 2)
			return "Vendedor";
		else if (column == 3)
			return "Cliente";
		else if (column == 4)
			return "Valor Total";
		else
			return null;
	}

	public Object getColumnValue(Object baseObject, int column) {
		Venda venda = (Venda) baseObject;
		float vendaTotal = venda.getValTotal() + venda.getAcrescimo() - venda.getDesconto() - venda.getValCartao();
		if (column == 0)
			return venda.getId();
		else if (column == 1)
			return venda.getDataVenda();
		else if (column == 2)
			return venda.getVendedor().trim();
		else if (column == 3)
			return venda.getCliente().trim();
		else if (column == 4)
			return String.valueOf("R$ " + String.format("%.2f", vendaTotal).replace(".", ","));
		else
			return null;
	}
}