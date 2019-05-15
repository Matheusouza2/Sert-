package com.sert.tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		String status = table.getModel().getValueAt(row, 1).toString();
		
		System.out.println(table.getModel().getValueAt(row, 1).toString());

		if (status.equals("Baixado")) {
			comp.setBackground(Color.CYAN);
		} else if (status.equals("Atrasada")) {
			comp.setBackground(Color.RED);
		}else {
			comp.setBackground(Color.gray);
		}

		return comp;
	}
}