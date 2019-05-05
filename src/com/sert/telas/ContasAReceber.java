package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerDuplicata;
import com.sert.controler.Log;
import com.sert.entidades.DuplicataCliente;
import com.sert.tables.TableModelContasAReceber;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class ContasAReceber extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JLabel lblTitle;
	private JPanel panelBtn;
	private JButton btnLancarConta;
	private JTextField txtPesquisa;
	private static JTable table;
	private JLabel lblProcurar;
	private JSeparator separator;
	private JScrollPane scrollPane;
	private static BasicEventList<DuplicataCliente> duplicatas;
	private FilterList<DuplicataCliente> textFilteredIssues;
	private JButton btnBaixarConta;
	private JButton btnVerDuplicata;

	public ContasAReceber() {
		setBounds(100, 100, 834, 618);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		listen();

		lblTitle = new JLabel();
		lblTitle.setText("contas a receber");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(33, 0, 768, 33);
		contentPanel.add(lblTitle);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(788, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelBtn = new JPanel();
		panelBtn.setLayout(null);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBackground(Color.YELLOW);
		panelBtn.setBounds(10, 34, 814, 113);
		contentPanel.add(panelBtn);

		btnBaixarConta = new JButton();
		btnBaixarConta.setIcon(new ImageIcon(ContasAReceber.class.getResource("/com/sert/img/btnBaixarConta.png")));
		btnBaixarConta.setToolTipText("Selecione uma duplicata para dar baixa");
		btnBaixarConta.setBackground(new Color(175, 238, 238));
		btnBaixarConta.setBounds(109, 11, 89, 91);
		panelBtn.add(btnBaixarConta);
		btnBaixarConta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() >= 0) {
					if (table.getValueAt(table.getSelectedRow(), 1).equals("Baixado")) {
						JOptionPane.showMessageDialog(null, "Parcela já baixada");
						return;
					}
					int idParcela = (int) table.getValueAt(table.getSelectedRow(), 6);
					new BaixarParcela(idParcela, 1, false).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma duplicata para baixar");
				}
			}
		});

		btnLancarConta = new JButton();
		btnLancarConta.setIcon(new ImageIcon(ContasAReceber.class.getResource("/com/sert/img/btnLancarConta.png")));
		btnLancarConta.setBackground(Color.GREEN);
		btnLancarConta.setBounds(10, 11, 89, 91);
		panelBtn.add(btnLancarConta);
		
		btnVerDuplicata = new JButton();
		btnVerDuplicata
				.setIcon(new ImageIcon(ContasAReceber.class.getResource("/com/sert/img/btnVerDuplicata.png")));
		btnVerDuplicata.setBackground(new Color(255, 99, 71));
		btnVerDuplicata.setBounds(208, 11, 89, 91);
		panelBtn.add(btnVerDuplicata);
		btnVerDuplicata.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					int idParcela = (int) table.getValueAt(table.getSelectedRow(), 6);
					new BaixarParcela(idParcela, 1, true).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma duplicata para ser vizualizada");
				}
			}
		});

		lblProcurar = new JLabel("procurar:");
		lblProcurar.setForeground(new Color(0, 0, 128));
		lblProcurar.setBackground(new Color(0, 0, 128));
		lblProcurar.setFont(new Font("Gtek Technology", Font.BOLD, 14));
		lblProcurar.setBounds(501, 51, 112, 14);
		panelBtn.add(lblProcurar);

		txtPesquisa = new JTextField();
		txtPesquisa.setBackground(new Color(255, 255, 0));
		txtPesquisa.setBorder(null);
		txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisa.setBounds(616, 44, 174, 24);
		panelBtn.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setBounds(616, 67, 174, 3);
		panelBtn.add(separator);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 158, 814, 449);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		duplicatas = new BasicEventList<DuplicataCliente>();

		try {
			for (DuplicataCliente duplicata : new ControlerDuplicata().listDuplicata()) {
				duplicatas.add(duplicata);
			}

			MatcherEditor<DuplicataCliente> textMatcherEditor = new TextComponentMatcherEditor<DuplicataCliente>(
					txtPesquisa, new DuplicataCliente());

			textFilteredIssues = new FilterList<DuplicataCliente>(duplicatas, textMatcherEditor);
			AdvancedTableModel<DuplicataCliente> mercTableModel = GlazedListsSwing
					.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelContasAReceber());
			table.setModel(mercTableModel);

			table.getColumnModel().getColumn(2).setPreferredWidth(400);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setMaxWidth(0);
			table.getColumnModel().getColumn(6).setMinWidth(0);
			table.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
			table.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		}
	}

	public static void atualizarLista() {
		try {
			duplicatas.clear();
			for (DuplicataCliente duplicata : new ControlerDuplicata().listDuplicata()) {
				duplicatas.add(duplicata);
			}

			table.revalidate();

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		}

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
	}
}