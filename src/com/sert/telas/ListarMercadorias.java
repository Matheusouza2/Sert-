package com.sert.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.PermissoesStatic;
import com.sert.entidades.Mercadoria;
import com.sert.tables.TableModelMerc;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class ListarMercadorias extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JPanel panelBtn;
	private JScrollPane spListaMerc;

	private JButton btnX;
	private JButton btnExcluir;
	private JButton btnEditar;
	private static JTable tabMerc;
	private JLabel lblListaDeMercadorias;
	private JTextField txtPesquisa;
	private JSeparator separator;
	private JLabel lblProcurar;
	private static FilterList<Mercadoria> textFilteredIssues;
	private JSeparator separator_1;
	private JButton btnAtualizar;
	private static JPanel panelAguarde;
	private JLabel label;
	private JLabel lblAguarde;
	private TextComponentMatcherEditor<Mercadoria> textMatcherEditor;
	private static AdvancedTableModel<Mercadoria> mercTableModel;
	private static BasicEventList<Mercadoria> mercadorias;
	private static List<Mercadoria> preencheTable;

	public ListarMercadorias() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(Color.YELLOW, 1, true));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		listen();

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelAguarde = new JPanel();
		panelAguarde.setBounds(305, 201, 223, 187);
		panelAguarde.setVisible(false);
		contentPanel.add(panelAguarde);
		panelAguarde.setLayout(null);

		label = new JLabel("");
		label.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/load.gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 223, 187);
		panelAguarde.add(label);

		lblAguarde = new JLabel("Aguarde...");
		lblAguarde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(0, 162, 223, 25);
		panelAguarde.add(lblAguarde);

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBounds(10, 34, 814, 113);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(ListarMercadorias.class.getResource("/com/sert/img/btnEditar.png")));
		btnEditar.setBounds(10, 11, 89, 91);
		btnEditar.setBackground(new Color(139, 0, 0));
		btnEditar.setBorderPainted(false);
		panelBtn.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabMerc.getSelectedRow() >= 0) {
					long idEditar = (long) tabMerc.getValueAt(tabMerc.getSelectedRow(), 1);
					new CadMercadoria(1, idEditar).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma mercadoria a ser editada", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnExcluir = new JButton();
		btnExcluir.setIcon(new ImageIcon(ListarMercadorias.class.getResource("/com/sert/img/btnExcluir.png")));
		btnExcluir.setBounds(208, 11, 89, 91);
		btnExcluir.setBackground(new Color(255, 0, 0));
		btnExcluir.setBorderPainted(false);
		panelBtn.add(btnExcluir);

		separator = new JSeparator();
		separator.setToolTipText("");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLUE);
		separator.setBounds(474, -1, 2, 116);
		panelBtn.add(separator);

		lblProcurar = new JLabel("procurar");
		lblProcurar.setForeground(new Color(0, 0, 128));
		lblProcurar.setFont(new Font("Gtek Technology", Font.BOLD, 13));
		lblProcurar.setBounds(499, 48, 108, 14);
		panelBtn.add(lblProcurar);

		txtPesquisa = new JTextField();
		txtPesquisa.setBackground(new Color(255, 255, 0));
		txtPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPesquisa.setBorder(null);
		txtPesquisa.setForeground(new Color(0, 0, 0));
		txtPesquisa.setBounds(607, 45, 197, 20);
		panelBtn.add(txtPesquisa);
		txtPesquisa.setColumns(10);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					if (tabMerc.getSelectedRow() >= 0) {
						int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir está mercadoria ?",
								"EXLUIR", JOptionPane.YES_NO_OPTION);

						if (resposta == JOptionPane.YES_OPTION) {
							int idExcluir = (int) tabMerc.getValueAt(tabMerc.getSelectedRow(), 0);
							mercadorias.remove(tabMerc.getSelectedRow());
							new ControlerMercadoria().excluirMercadoria(idExcluir);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Selecione uma mercadoria a ser excluida", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro SQL",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		mercadorias = new BasicEventList<Mercadoria>();

		spListaMerc = new JScrollPane();
		spListaMerc.setBounds(10, 158, 814, 421);
		spListaMerc.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(spListaMerc);
		tabMerc = new JTable();
		tabMerc.getTableHeader().setReorderingAllowed(false);
		tabMerc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabMerc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spListaMerc.setViewportView(tabMerc);

		lblListaDeMercadorias = new JLabel("lista de mercadorias");
		lblListaDeMercadorias.setForeground(new Color(255, 255, 255));
		lblListaDeMercadorias.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblListaDeMercadorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeMercadorias.setBounds(280, 0, 273, 35);
		contentPanel.add(lblListaDeMercadorias);

		for (Mercadoria merc : preencheTable) {
			mercadorias.add(merc);
		}

		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 128));
		separator_1.setBounds(607, 65, 197, 2);
		panelBtn.add(separator_1);

		btnAtualizar = new JButton();
		btnAtualizar.setIcon(new ImageIcon(ListarMercadorias.class.getResource("/com/sert/img/btnAtualizar.png")));
		btnAtualizar.setBorderPainted(false);
		btnAtualizar.setBackground(Color.LIGHT_GRAY);
		btnAtualizar.setBounds(109, 11, 89, 91);
		panelBtn.add(btnAtualizar);

		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				repagina();
				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		textMatcherEditor = new TextComponentMatcherEditor<Mercadoria>(txtPesquisa, new Mercadoria());

		textFilteredIssues = new FilterList<Mercadoria>(mercadorias, textMatcherEditor);
		mercTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelMerc());
		tabMerc.setModel(mercTableModel);

		tabMerc.getColumnModel().getColumn(0).setPreferredWidth(58);
		tabMerc.getColumnModel().getColumn(1).setPreferredWidth(140);
		tabMerc.getColumnModel().getColumn(2).setPreferredWidth(790);
		tabMerc.getColumnModel().getColumn(3).setPreferredWidth(100);

		getPermissoes();
	}

	public static void repagina() {
		panelAguarde.setVisible(true);
		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				new ControlerVenda().atualizarCadastros();
				for(int i = 0; i < mercadorias.size(); i++) {
					mercadorias.remove(0);
				}
				for (Mercadoria merc : preencheTable) {
					mercadorias.add(merc);
				}
				tabMerc.revalidate();
				return null;
			}

			protected void done() {
				panelAguarde.setVisible(false);
				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			};

		}.execute();
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

		JRootPane delete = getRootPane();
		delete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"DELETE");
		delete.getRootPane().getActionMap().put("DELETE", new AbstractAction("DELETE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				btnExcluir.doClick();
			}
		});
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isAltProd()) {
			btnEditar.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isExclProduto()) {
			btnExcluir.setEnabled(false);
		}
	}
}