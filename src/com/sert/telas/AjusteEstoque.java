package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerAjusteEstoque;
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.AutoCompletion;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class AjusteEstoque extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JScrollPane scrollPane;

	private JLabel lblUsuario;
	private JLabel lblTipo;
	private JLabel lblDataDeIncluso;

	private JRadioButton rdbtnEntrada;
	private JRadioButton rdbtnSaida;

	private JButton btnSalvar;
	private JButton btnX;

	private JTable table;
	private ButtonGroup bg = new ButtonGroup();

	private DefaultTableModel modelo;
	private JComboBox<String> cbMercDesc;
	private List<Mercadoria> mercList;
	private JComboBox<String> cbMercRef;
	private JLabel lblCadastroDeMercadoria;
	private JComboBox<Integer> cbId;
	private JLabel lblCodDeBarras;
	private JLabel lblMercadoria;
	private JLabel lblQuant;
	private JTextField txtQuant;
	private JLabel lblCod;
	private JLabel lblSelecioneOTipo;
	private JPanel panelAguarde;
	private JLabel label;
	private JLabel lblAguarde;

	public AjusteEstoque() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listen();

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		lblCadastroDeMercadoria = new JLabel("movimentacao de estoque");
		lblCadastroDeMercadoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeMercadoria.setForeground(Color.WHITE);
		lblCadastroDeMercadoria.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeMercadoria.setBounds(248, 0, 337, 35);
		contentPane.add(lblCadastroDeMercadoria);

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPane.add(btnX);
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
		contentPane.add(panelAguarde);
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
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					movEstoque();
					while (table.getRowCount() > 0) {
						modelo.removeRow(0);
					}
					table.setModel(modelo);

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblSelecioneOTipo = new JLabel(
				"<html><ul><li>Selecione o tipo de movimentação</li><li>Selecione a mercadoria</li><li>Coloque a quantidade e aperte ENTER</li><br><li>DELETE remove a mercadoria da lista de ajuste</li></ul></html>");
		lblSelecioneOTipo.setForeground(Color.RED);
		lblSelecioneOTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelecioneOTipo.setBounds(475, 0, 329, 113);
		panelBtn.add(lblSelecioneOTipo);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 814, 421);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 794, 320);
		panelForm.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);

		lblDataDeIncluso = new JLabel("Data de inclusão: " + JDateField.getDate());
		lblDataDeIncluso.setBounds(10, 406, 215, 14);
		panelForm.add(lblDataDeIncluso);

		lblUsuario = new JLabel("Usuario: " + UsuLogado.getNome());
		lblUsuario.setBounds(605, 406, 174, 14);

		cbId = new JComboBox<Integer>();
		cbId.setEditable(true);
		cbId.setVisible(true);
		cbId.setBounds(38, 44, 58, 20);
		panelForm.add(cbId);

		cbMercDesc = new JComboBox<String>();
		cbMercDesc.setEditable(true);
		cbMercDesc.setVisible(true);
		cbMercDesc.setBounds(380, 44, 272, 20);
		panelForm.add(cbMercDesc);

		cbMercRef = new JComboBox<String>();
		cbMercRef.setEditable(true);
		cbMercRef.setVisible(true);
		cbMercRef.setBounds(197, 44, 102, 20);
		panelForm.add(cbMercRef);

		try {
			mercList = new ControlerMercadoria().listarMercadorias();
			for (int i = 0; i < mercList.size(); i++) {

				cbId.addItem(mercList.get(i).getId());
				cbId.setSelectedItem(null);

				cbMercDesc.addItem(mercList.get(i).getMercadoria());
				cbMercDesc.setSelectedItem(null);

				cbMercRef.addItem(String.valueOf(mercList.get(i).getCodBarras()));
				cbMercRef.setSelectedItem(null);
			}
			AutoCompletion.enable(cbId);
			AutoCompletion.enable(cbMercDesc);
			AutoCompletion.enable(cbMercRef);
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (NenhumaMercadoriaCadastradaException e2) {
			e2.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		escutaComboMerc();

		panelForm.add(lblUsuario);
		modelo.addColumn("Cod");
		modelo.addColumn("Cod. barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço Venda");
		modelo.addColumn("Quant.");
		modelo.addColumn("Preço Total");
		table.setModel(modelo);

		lblCod = new JLabel("Cod:");
		lblCod.setBounds(10, 47, 38, 14);
		panelForm.add(lblCod);

		lblCodDeBarras = new JLabel("Cod. de Barras:");
		lblCodDeBarras.setBounds(106, 47, 89, 14);
		panelForm.add(lblCodDeBarras);

		lblMercadoria = new JLabel("Mercadoria:");
		lblMercadoria.setBounds(309, 47, 73, 14);
		panelForm.add(lblMercadoria);

		lblQuant = new JLabel("Quant.");
		lblQuant.setBounds(662, 47, 46, 14);
		panelForm.add(lblQuant);

		txtQuant = new JTextField();
		txtQuant.setBounds(718, 44, 51, 20);
		panelForm.add(txtQuant);
		txtQuant.setColumns(10);

		rdbtnSaida = new JRadioButton("Saída");
		rdbtnSaida.setBounds(472, 7, 109, 23);
		panelForm.add(rdbtnSaida);
		rdbtnSaida.setBackground(new Color(240, 240, 240));
		bg.add(rdbtnSaida);

		rdbtnEntrada = new JRadioButton("Entrada");
		rdbtnEntrada.setBounds(361, 7, 109, 23);
		panelForm.add(rdbtnEntrada);
		rdbtnEntrada.setBackground(new Color(240, 240, 240));
		rdbtnEntrada.setSelected(true);

		bg.add(rdbtnEntrada);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(297, 11, 38, 14);
		panelForm.add(lblTipo);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JNumberField()));
		table.getColumnModel().getColumn(5)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));

	}

	public void escutaComboMerc() {
		cbId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbId.isPopupVisible()) {
					if (cbId.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbId.getSelectedItem().equals(merc.getId())) {
								cbMercRef.setSelectedItem(merc.getCodBarras());
								cbMercDesc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbMercRef.setSelectedItem(null);
						cbMercDesc.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});

		cbMercRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercRef.isPopupVisible()) {

					if (cbMercRef.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbMercRef.getSelectedItem().equals(String.valueOf(merc.getCodBarras()))) {
								cbId.setSelectedItem(merc.getId());
								cbMercDesc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbId.setSelectedItem("");
						cbMercDesc.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		cbMercDesc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercDesc.isPopupVisible()) {

					if (cbMercDesc.getSelectedItem() != "") {
						for (Mercadoria merc : mercList) {
							if (cbMercDesc.getSelectedItem().equals(merc.getMercadoria())) {
								cbId.setSelectedItem(merc.getId());
								cbMercRef.setSelectedItem(merc.getCodBarras());
							}
						}
					} else {
						cbId.setSelectedItem("");
						cbMercRef.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	private void movEstoque() throws ClassNotFoundException, SQLException, IOException {
		List<Mercadoria> mercList = new ArrayList<Mercadoria>();
		int operacao = 0;
		Mercadoria merc;
		if (rdbtnSaida.isSelected()) {
			operacao = 1;
		}
		for (int i = 0; i < table.getRowCount(); i++) {
			merc = new Mercadoria();
			merc.setId(Integer.parseInt(table.getValueAt(i, 0).toString()));
			merc.setCodBarras(Long.parseLong(table.getValueAt(i, 1).toString()));
			merc.setMercadoria(table.getValueAt(i, 2).toString());
			merc.setPrecoVenda(Float.parseFloat(table.getValueAt(i, 3).toString().replace(",", ".").replace("R$", "")));
			merc.setEstoque(Float.parseFloat(table.getValueAt(i, 4).toString().replace(",", ".")));
			mercList.add(merc);
		}

		new ControlerAjusteEstoque().movEstoque(mercList, operacao);
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		panelAguarde.setVisible(true);
		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				new ControlerVenda().atualizarCadastros();
				ControlerVenda.mercadorias = new ControlerMercadoria().listarMercadorias();
				ListarMercadorias.setPreencheTable(ControlerVenda.mercadorias);
				PesqMercVenda.setPreencheTable(ControlerVenda.mercadorias);
				return null;
			}

			protected void done() {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panelAguarde.setVisible(false);
				JOptionPane.showMessageDialog(null, "Ajuste realizado com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			};

		}.execute();
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

		JRootPane enter = getRootPane();
		enter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		enter.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {
			private static final long serialVersionUID = 4394565308739162374L;

			public void actionPerformed(ActionEvent e) {
				if (txtQuant.hasFocus()) {
					try {
						if (cbMercRef.getSelectedItem().toString().equals(null) || txtQuant.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Selecione uma mercadoria e/ou coloque a quantidade");
						} else {
							long codBarras = Long.parseLong(cbMercRef.getSelectedItem().toString());
							Mercadoria mercadoria = new ControlerMercadoria().consultaMercadoria(codBarras);
							float quant = Float.parseFloat(txtQuant.getText().replace(",", "."));

							modelo.addRow(new Object[] { mercadoria.getId(), mercadoria.getCodBarras(),
									mercadoria.getMercadoria(), mercadoria.getPrecoVenda(), quant,
									mercadoria.getPrecoVenda() * quant });

							table.setModel(modelo);

							table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);

							cbId.setSelectedItem("");
							cbMercDesc.setSelectedItem("");
							cbMercRef.setSelectedItem("");
							txtQuant.setText("");
						}
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (MercadoriaNaoEncontradaException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione a mercadoria e depois coloque uma quantidade!");
				}
			}
		});

		JRootPane delete = getRootPane();
		delete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"DELETE");
		delete.getRootPane().getActionMap().put("DELETE", new AbstractAction("DELETE") {
			private static final long serialVersionUID = 4394565308739162374L;

			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					modelo.removeRow(table.getSelectedRow());
					table.setModel(modelo);
					table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
				} else {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada", "AVISO",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}
}