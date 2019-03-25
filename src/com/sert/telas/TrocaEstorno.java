package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.Log;
import com.sert.editableFields.AutoCompletion;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.VendaNaoEncontradaException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class TrocaEstorno extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodVenda;
	private JComboBox<String> txtMerc;
	private JPanel panel_merc_troc;
	private JLabel lblTroca;
	private JPanel panel;
	private JLabel lblMsg;
	private JButton btnConfirmar;
	private JButton btnX;
	private JLabel lblInstrucoes;
	private JPanel panel_merc_venda;
	private JPanel panelBtn2;
	private JLabel lblMercadoria;
	private JLabel lblInstrucao2;
	private JPanel panelBtn1;
	private JLabel lblNumeroDaVenda;
	private JButton btnBuscar;
	private int codVenda;
	private JTable tableMercTroca;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPaneTroca;
	private JTable tableMerc;
	private JScrollPane scrollPane;
	private JTextField txtQuant;
	private JLabel lblQuant;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private DefaultTableModel tableModelMercTroca;
	private List<Mercadoria> mercadorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TrocaEstorno dialog = new TrocaEstorno();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TrocaEstorno() {
		setBounds(100, 100, 900, 709);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		setUndecorated(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(854, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		listen();
		panelBtn1 = new JPanel();
		panelBtn1.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panelBtn1.setBackground(new Color(255, 255, 0));
		panelBtn1.setBounds(10, 38, 880, 79);
		contentPanel.add(panelBtn1);
		panelBtn1.setLayout(null);

		lblNumeroDaVenda = new JLabel("Numero da venda:");
		lblNumeroDaVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNumeroDaVenda.setBounds(10, 31, 136, 16);
		panelBtn1.add(lblNumeroDaVenda);

		txtCodVenda = new JNumberField();
		txtCodVenda.setBorder(null);
		txtCodVenda.setBackground(new Color(255, 255, 0));
		txtCodVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodVenda.setBounds(156, 29, 106, 20);
		panelBtn1.add(txtCodVenda);
		txtCodVenda.isFocusable();
		txtCodVenda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBuscar.setBounds(272, 28, 89, 23);
		panelBtn1.add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarVenda();
			}
		});

		lblInstrucoes = new JLabel(
				"<html><ul><li>Digite o numero da venda e clique em buscar.</li><br><li>Escolha a mercadoria que será trocada.</li></uSl></html>");
		lblInstrucoes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrucoes.setForeground(new Color(255, 0, 0));
		lblInstrucoes.setBounds(550, 0, 330, 79);
		panelBtn1.add(lblInstrucoes);

		separator_2 = new JSeparator();
		separator_2.setBounds(156, 49, 106, 2);
		panelBtn1.add(separator_2);

		panel_merc_venda = new JPanel();
		panel_merc_venda.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panel_merc_venda.setBounds(10, 128, 880, 208);
		contentPanel.add(panel_merc_venda);
		panel_merc_venda.setLayout(null);

		scrollPaneTroca = new JScrollPane();
		scrollPaneTroca.setBounds(0, 0, 880, 208);
		panel_merc_venda.add(scrollPaneTroca);

		panelBtn2 = new JPanel();
		panelBtn2.setLayout(null);
		panelBtn2.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panelBtn2.setBackground(Color.YELLOW);
		panelBtn2.setBounds(10, 347, 880, 79);
		contentPanel.add(panelBtn2);

		lblMercadoria = new JLabel("Mercadoria: ");
		lblMercadoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMercadoria.setBounds(10, 32, 97, 14);
		panelBtn2.add(lblMercadoria);

		txtMerc = new JComboBox<String>();
		txtMerc.setEditable(true);
		txtMerc.setVisible(true);
		txtMerc.setBackground(new Color(255, 255, 224));
		txtMerc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMerc.setBorder(null);
		txtMerc.setBounds(117, 29, 232, 20);
		panelBtn2.add(txtMerc);

		lblInstrucao2 = new JLabel(
				"<html><ul><li>Procure uma mercadoria.</li><br><li>Selecione e confime para efetivar a troca.</li></ul></html>");
		lblInstrucao2.setForeground(new Color(255, 0, 0));
		lblInstrucao2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInstrucao2.setBounds(560, 0, 320, 79);
		panelBtn2.add(lblInstrucao2);

		panel_merc_troc = new JPanel();
		panel_merc_troc.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panel_merc_troc.setBounds(10, 437, 880, 208);
		contentPanel.add(panel_merc_troc);
		panel_merc_troc.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 880, 208);
		panel_merc_troc.add(scrollPane);

		tableMerc = new JTable();
		scrollPane.setViewportView(tableMerc);

		lblTroca = new JLabel("troca");
		lblTroca.setForeground(new Color(255, 255, 255));
		lblTroca.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTroca.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroca.setBounds(340, 0, 220, 38);
		contentPanel.add(lblTroca);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panel.setBounds(10, 656, 880, 40);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblMsg = new JLabel("");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMsg.setBounds(10, 0, 334, 40);
		panel.add(lblMsg);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(0, 255, 0));
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(748, 8, 122, 23);
		panel.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarTroca();
			}
		});

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Cod. barras");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Preço Un");
		tableModel.addColumn("Quant");
		tableModel.addColumn("Preço Total");

		tableMercTroca = new JTable();
		tableMercTroca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneTroca.setViewportView(tableMercTroca);
		tableMercTroca.setModel(tableModel);
		tableMercTroca.getColumnModel().getColumn(0).setPreferredWidth(90);
		tableMercTroca.getColumnModel().getColumn(1).setPreferredWidth(195);
		tableMercTroca.getColumnModel().getColumn(2).setPreferredWidth(730);
		tableMercTroca.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableMercTroca.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableMercTroca.getColumnModel().getColumn(5).setPreferredWidth(120);

		try {
			txtMerc.addItem("");
			mercadorias = new ControlerMercadoria().listarMercadorias();
			for (Mercadoria merc : mercadorias) {
				txtMerc.addItem(merc.getMercadoria());
				txtMerc.setSelectedIndex(-1);
			}
			AutoCompletion.enable(txtMerc);
			tableMerc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			lblQuant = new JLabel("Quant.");
			lblQuant.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblQuant.setBounds(398, 33, 56, 17);
			panelBtn2.add(lblQuant);

			txtQuant = new JTextField();
			txtQuant.setBackground(new Color(255, 255, 0));
			txtQuant.setBounds(464, 29, 86, 20);
			txtQuant.setBorder(null);
			panelBtn2.add(txtQuant);
			txtQuant.setColumns(10);
			txtQuant.setText(String.valueOf(1));

			separator = new JSeparator();
			separator.setBounds(117, 49, 232, 2);
			panelBtn2.add(separator);

			separator_1 = new JSeparator();
			separator_1.setBounds(464, 49, 86, 2);
			panelBtn2.add(separator_1);

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Erro interno, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro 404", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita de arquivo, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		}

		tableModelMercTroca = new DefaultTableModel();
		tableModelMercTroca.addColumn("Código");
		tableModelMercTroca.addColumn("Cod. barras");
		tableModelMercTroca.addColumn("Descrição");
		tableModelMercTroca.addColumn("Preço Un");
		tableModelMercTroca.addColumn("Quant");
		tableModelMercTroca.addColumn("Preço Total");

		tableMerc.setModel(tableModelMercTroca);
		tableMerc.getColumnModel().getColumn(0).setPreferredWidth(58);
		tableMerc.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableMerc.getColumnModel().getColumn(2).setPreferredWidth(790);
		tableMerc.getColumnModel().getColumn(3).setPreferredWidth(100);

	}

	public void buscarVenda() {
		try {
			if (tableModel.getRowCount() != 0) {
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					tableModel.removeRow(0);
				}
				tableMercTroca.revalidate();
			}
			codVenda = Integer.parseInt(txtCodVenda.getText());
			Venda venda = new ControlerVenda().imprimirVenda(codVenda);
			for (int i = 0; i < venda.getMercadorias().size(); i++) {
				tableModel.addRow(new Object[] { venda.getMercadorias().get(i).getId(),
						venda.getMercadorias().get(i).getCodBarras(),
						venda.getMercadorias().get(i).getMercadoria().trim(),
						String.format("R$ %.2f", venda.getMercadorias().get(i).getPrecoVenda()),
						venda.getMercadorias().get(i).getEstoque(),
						String.format("R$ %.2f", venda.getMercadorias().get(i).getPrecoVenda()
								* venda.getMercadorias().get(i).getEstoque()) });
			}

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro interno, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na escrita de arquivo, ver o LOG para mais detalhes", "Erro 404",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Nenhuma Mercadoria", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (VendaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Venda", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Codigo da venda não pode ser nulo", "Venda",
					JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		}
	}

	private void confirmarTroca() {
		System.out.println(tableMerc.getSelectedRow());
	}

	private void lancarTrocaMerc() {
		long codBarras = 0;
		for (int i = 0; i < mercadorias.size(); i++) {
			if(txtMerc.getSelectedItem().toString().equals(mercadorias.get(i).getMercadoria())) {
				codBarras = mercadorias.get(i).getCodBarras();
			}
		}
		
		try {
			Mercadoria merc = new ControlerMercadoria().consultaMercadoria(codBarras);
			tableModelMercTroca.addRow(new Object[] {merc.getId(), merc.getCodBarras(), merc.getMercadoria(), merc.getPrecoVenda(), txtQuant.getText(), merc.getPrecoVenda() * Float.parseFloat(txtQuant.getText())});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (MercadoriaNaoEncontradaException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESC");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		JRootPane enter = getRootPane();
		enter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		enter.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (txtCodVenda.hasFocus()) {
					btnBuscar.doClick();
				} else if (txtQuant.hasFocus()) {
					if (txtMerc.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione uma mercadoria para substituir a da venda",
								"AVISO", JOptionPane.INFORMATION_MESSAGE);
					}else {
						lancarTrocaMerc();
					}
				}
			}
		});
	}
}