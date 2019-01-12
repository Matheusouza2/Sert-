package com.sert.tables;

import com.sert.entidades.Mercadoria;

import ca.odell.glazedlists.gui.TableFormat;

public class TableModelMerc implements TableFormat<Object> {

	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int column) {
		if (column == 0)
			return "Código";
		else if (column == 1)
			return "Cod. Barras";
		else if (column == 2)
			return "Descrição";
		else if (column == 3)
			return "Preço venda";
		else if (column == 4)
			return "Estoque";
		else
			return null;
	}

	public Object getColumnValue(Object baseObject, int column) {
		Mercadoria mercadoria = (Mercadoria) baseObject;
		if (column == 0)
			return mercadoria.getId();
		else if (column == 1)
			return mercadoria.getCodBarras();
		else if (column == 2)
			return mercadoria.getMercadoria();
		else if (column == 3)
			return String.valueOf("R$ "+String.format("%.2f",mercadoria.getPrecoVenda())).replace(".", ",");
		else if (column == 4)
			return mercadoria.getEstoque();
		else
			return null;
	}
}