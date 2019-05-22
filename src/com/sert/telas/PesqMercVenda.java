package com.sert.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.Color;

import javax.swing.border.LineBorder;

import com.sert.entidades.Mercadoria;
import com.sert.tables.TableModelMerc;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;

public class PesqMercVenda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnX;
	private JPanel contentPanel;
	private JTextField txtPesquisa;
	private JPanel panelPesq;
	private JLabel lblPesquisa;
	private JLabel lblAviso;
	private JLabel lblTitulo;
	private JTable table;
	private JPanel panelTable;
	private JScrollPane scrollPane;
	private BasicEventList<Mercadoria> mercadorias;
	private FilterList<Mercadoria> textFilteredIssues;
	private static List<Mercadoria> preencheTable;
	private AdvancedTableModel<Mercadoria> mercTableModel;

	public PesqMercVenda() {
		setBounds(100, 100, 1020, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBounds(0, 0, 1020, 700);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);

		listen();

		btnX = new JButton("X");
		btnX.setBounds(971, 11, 43, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelPesq = new JPanel();
		panelPesq.setBorder(new LineBorder(Color.YELLOW, 1, true));
		panelPesq.setBackground(Color.GRAY);
		panelPesq.setBounds(10, 46, 1000, 128);
		contentPanel.add(panelPesq);
		panelPesq.setLayout(null);

		lblPesquisa = new JLabel("pesquisar:");
		lblPesquisa.setForeground(Color.YELLOW);
		lblPesquisa.setFont(new Font("Gtek Technology", Font.BOLD, 18));
		lblPesquisa.setBounds(10, 42, 165, 43);
		panelPesq.add(lblPesquisa);

		txtPesquisa = new JTextField();
		txtPesquisa.setForeground(Color.WHITE);
		txtPesquisa.setBackground(Color.GRAY);
		txtPesquisa.setBorder(null);
		txtPesquisa.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtPesquisa.setBounds(185, 48, 234, 32);
		panelPesq.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		lblAviso = new JLabel(
				"<html><ul><li>No campo de pesquisa você pode colocar Código, cod. de barras, nome, preço ou estoque.</li><br><li>Ao escolher a mercadoria que quer vender tecle <b>F3</b></li></ul></html>");
		lblAviso.setForeground(Color.YELLOW);
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAviso.setBounds(708, 0, 282, 128);
		panelPesq.add(lblAviso);

		lblTitulo = new JLabel("pesquisar mercadorias");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitulo.setBounds(347, -1, 325, 35);
		contentPanel.add(lblTitulo);

		panelTable = new JPanel();
		panelTable.setBounds(10, 181, 1000, 508);
		contentPanel.add(panelTable);
		panelTable.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1000, 508);
		panelTable.add(scrollPane);

		mercadorias = new BasicEventList<Mercadoria>();

		for (Mercadoria merc : preencheTable) {
			mercadorias.add(merc);
		}

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		MatcherEditor<Mercadoria> textMatcherEditor = new TextComponentMatcherEditor<Mercadoria>(txtPesquisa,
				new Mercadoria());

		JSeparator separator = new JSeparator();
		separator.setBounds(185, 79, 234, 2);
		panelPesq.add(separator);

		textFilteredIssues = new FilterList<Mercadoria>(mercadorias, textMatcherEditor);
		mercTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelMerc());
		table.setModel(mercTableModel);

		table.getColumnModel().getColumn(0).setPreferredWidth(58);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(790);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);

	}

	public void enviarMerc() {
		PontoDeVenda.addMercPesq(Long.parseLong(table.getValueAt(table.getSelectedRow(), 1).toString()));
		dispose();

	}

	public static void setPreencheTable(List<Mercadoria> preencheTable1) {
		preencheTable = preencheTable1;
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE");
		escback.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JRootPane enterMerc = getRootPane();
		enterMerc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");
		enterMerc.getRootPane().getActionMap().put("F3", new AbstractAction("F3") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				enviarMerc();
			}
		});

	}
}