package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.alertas.Info;
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.Log;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.VendaNaoEncontradaException;
import com.sert.tables.TableModelMerc;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TrocaEstorno extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodVenda;
	private JTextField txtMerc;
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
	private AdvancedTableModel<Mercadoria> mercTableModel;
	private FilterList<Mercadoria> textFilteredIssues;
	private BasicEventList<Mercadoria> mercadorias;

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
		txtCodVenda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCodVenda.setBounds(156, 29, 106, 20);
		panelBtn1.add(txtCodVenda);
		txtCodVenda.isFocusable();
		txtCodVenda.setColumns(10);

		btnBuscar = new JButton("Buscar");
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

		txtMerc = new JTextField();
		txtMerc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMerc.setBounds(117, 29, 232, 20);
		panelBtn2.add(txtMerc);
		txtMerc.setColumns(10);

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

		mercadorias = new BasicEventList<Mercadoria>();

		try {
			for (Mercadoria merc : new ControlerMercadoria().listarMercadorias()) {
				mercadorias.add(merc);
			}
			tableMerc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			MatcherEditor<Mercadoria> textMatcherEditor = new TextComponentMatcherEditor<Mercadoria>(txtMerc,
					new Mercadoria());

			textFilteredIssues = new FilterList<Mercadoria>(mercadorias, textMatcherEditor);
			mercTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(textFilteredIssues,
					new TableModelMerc());
			tableMerc.setModel(mercTableModel);

		} catch (ClassNotFoundException e1) {
			Info.AlertInfo(e1.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e1) {
			Info.AlertInfo(e1.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (SQLException e1) {
			Info.AlertInfo(e1.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		} catch (IOException e1) {
			Info.AlertInfo(e1.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e1.getMessage());
		}

		tableMerc.getColumnModel().getColumn(0).setPreferredWidth(58);
		tableMerc.getColumnModel().getColumn(1).setPreferredWidth(140);
		tableMerc.getColumnModel().getColumn(2).setPreferredWidth(790);
		tableMerc.getColumnModel().getColumn(3).setPreferredWidth(100);

	}

	public void buscarVenda() {
		codVenda = Integer.parseInt(txtCodVenda.getText());
		if (codVenda == 0) {

		}
		try {
			Venda venda = new ControlerVenda().imprimirVenda(codVenda);
			for (int i = 0; i < venda.getMercadorias().size(); i++) {
				tableModel.addRow(new Object[] { venda.getMercadorias().get(i).getId(),
						venda.getMercadorias().get(i).getCodBarras(),
						venda.getMercadorias().get(i).getMercadoria().trim(),
						 String.format("R$ %.2f", venda.getMercadorias().get(i).getPrecoVenda()), venda.getMercadorias().get(i).getEstoque(),
						String.format("R$ %.2f", venda.getMercadorias().get(i).getPrecoVenda() * venda.getMercadorias().get(i).getEstoque()) });
			}

		} catch (ClassNotFoundException e) {
			Info.AlertInfo(e.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (SQLException e) {
			Info.AlertInfo(e.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (IOException e) {
			Info.AlertInfo(e.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e) {
			Info.AlertInfo(e.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		} catch (VendaNaoEncontradaException e) {
			Info.AlertInfo(e.getMessage(), Info.INFO);
			Log.gravaLog("|TrocaEstorno|" + e.getMessage());
		}
	}

	private void confirmarTroca() {

	}
}