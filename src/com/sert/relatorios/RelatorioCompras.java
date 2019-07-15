package com.sert.relatorios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerNfe;
import com.sert.entidades.NFeEntrada;

import javax.swing.JTable;

public class RelatorioCompras extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JLabel lblTitle;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_footer;
	private JLabel lblTotal;
	private JLabel lblPeriodo;
	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblValTotal;

	public RelatorioCompras(String dataInicial, String dataFinal) {
		setBounds(100, 100, 1020, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBounds(0, 0, 1020, 700);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(967, 11, 43, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblTitle = new JLabel("relatorio de compras");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(347, 11, 325, 35);
		contentPanel.add(lblTitle);

		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panel.setBounds(10, 45, 1000, 110);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblPeriodo = new JLabel("Periodo: " + dataInicial + " à " + dataFinal);
		lblPeriodo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPeriodo.setBounds(10, 11, 311, 41);
		panel.add(lblPeriodo);

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.YELLOW, 2, true));
		scrollPane.setBounds(10, 165, 1000, 478);
		contentPanel.add(scrollPane);

		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("Id");
		tableModel.addColumn("Numero");
		tableModel.addColumn("Fornecedor");
		tableModel.addColumn("Data Entrada");
		tableModel.addColumn("Valor");

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);

		panel_footer = new JPanel();
		panel_footer.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		panel_footer.setBounds(10, 654, 1000, 35);
		contentPanel.add(panel_footer);
		panel_footer.setLayout(null);

		lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotal.setBounds(10, 11, 46, 13);
		panel_footer.add(lblTotal);
		
		lblValTotal = new JLabel("");
		lblValTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValTotal.setBounds(839, 11, 140, 14);
		panel_footer.add(lblValTotal);

		try {
			ControlerNfe controlerNfe = new ControlerNfe();
			float valTotal = 0;
			List<NFeEntrada> nfeList = controlerNfe.nfePorPeriodo(dataInicial, dataFinal);
			for (int i = 0; i < nfeList.size(); i++) {
				tableModel.addRow(new Object[] { nfeList.get(i).getId(), nfeList.get(i).getNumNota(),
						nfeList.get(i).getFornecedor().getRazSocial(), nfeList.get(i).getDataEntrada(),
						String.format("R$ %.2f", nfeList.get(i).getValNota())});
				valTotal += nfeList.get(i).getValNota();
			}
			
			lblValTotal.setText(String.format("R$ %.2f", valTotal));
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}