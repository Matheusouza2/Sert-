package com.sert.tables;

import com.sert.entidades.DuplicataCliente;

import ca.odell.glazedlists.gui.TableFormat;

public class TableModelContasAReceber implements TableFormat<Object> {

	public int getColumnCount() {
		return 7;
	}

	public String getColumnName(int column) {
		if (column == 0)
			return "Parcela";
		if (column == 1)
			return "Situação";
		else if (column == 2)
			return "Cliente";
		else if (column == 3)
			return "Numero venda";
		else if (column == 4)
			return "Vencimento";
		else if (column == 5)
			return "Valor";
		else if(column == 6){
			return "";
		}
		else
			return null;
	}

	public Object getColumnValue(Object baseObject, int column) {
		DuplicataCliente duplicata = (DuplicataCliente) baseObject;
		if (column == 0)
			return duplicata.getNumParcela();
		else if (column == 1)
			return duplicata.getSituacao();
		else if (column == 2)
			return duplicata.getCliente().getNome();
		else if (column == 3)
			return duplicata.getVenda().getId();
		else if (column == 4)
			return duplicata.getDataVencimento();
		else if (column == 5)
			return String.valueOf("R$ " + String.format("%.2f", duplicata.getValor())).replace(".", ",");
		else if(column == 6) {
			return duplicata.getId();
		}
		else
			return null;
	}
}