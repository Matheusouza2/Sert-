package com.sert.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JButton;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GerenciaPrecoView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnX;
	private JPanel panelSup;
	private JTable table;
	private JButton btnSalvar;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;

	public GerenciaPrecoView() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 760);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.YELLOW);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelSup = new JPanel();
		panelSup.setBounds(10, 44, 1280, 114);
		contentPane.add(panelSup);
		panelSup.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(GerenciaPrecoView.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setOpaque(false);
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBounds(10, 11, 89, 91);
		panelSup.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(GerenciaPrecoView.class.getResource("/com/sert/img/btnX.png")));
		btnX.setOpaque(false);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setBounds(1270, 3, 30, 30);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 1280, 580);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		modelo = new DefaultTableModel();

		table.setModel(modelo);

		modelo.addColumn("Código");
		modelo.addColumn("Cód. Barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Valor Compra");
		modelo.addColumn("Valor Venda");
		modelo.addColumn("");
		modelo.addColumn("Novo Valor (R$)");
		modelo.addColumn("Novo Valor (%)");

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(320);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(1);
		table.getColumnModel().getColumn(6).setPreferredWidth(85);
		table.getColumnModel().getColumn(7).setPreferredWidth(85);
		table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JNumberField()));
		table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JNumberField()));

		preencheTable();
	}

	private void preencheTable() {
		try {
			List<Mercadoria> mercs = new ControlerMercadoria().listarMercadorias();

			for (Mercadoria merc : mercs) {
				modelo.addRow(new Object[] { merc.getId(), merc.getCodBarras(), merc.getMercadoria(),
						String.format("%.2f", merc.getPrecoCompra()), String.format("%.2f", merc.getPrecoVenda()), "", String.format("%.2f", 0.00)});
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NenhumaMercadoriaCadastradaException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void salvar() {
		Mercadoria mercadoriaAlt;
		for (int i = 0; i < table.getRowCount(); i++) {
			mercadoriaAlt = new Mercadoria();
			mercadoriaAlt.setId(Integer.parseInt(table.getValueAt(i, 0).toString()));
			mercadoriaAlt.setCodBarras(Long.parseLong(table.getValueAt(i, 1).toString()));
			mercadoriaAlt.setMercadoria(table.getValueAt(i, 2).toString());
			mercadoriaAlt.setPrecoCompra(Float.parseFloat(table.getValueAt(i, 3).toString().replace(",", ".")));
			if (table.getValueAt(i, 6).toString().equals("0,00")) {
				mercadoriaAlt.setPrecoVenda(Float.parseFloat(table.getValueAt(i, 4).toString().replace(",", ".")));
			} else {
				mercadoriaAlt.setPrecoVenda(Float.parseFloat(table.getValueAt(i, 6).toString().replace(",", ".")));
			}
			mercadoriaAlt.setUsuAlt(UsuLogado.getNome());
			
			try {
				new ControlerMercadoria().alterarMercadoria(mercadoriaAlt);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CodBarrasJaCadastradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}