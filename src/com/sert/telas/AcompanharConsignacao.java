package com.sert.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerConsignacao;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Orcamento;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class AcompanharConsignacao extends JDialog implements ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnX;
	private JPanel panelForm;
	private JComponent panelLegenda;
	private JLabel lblAbertoSignificaQue;
	private JLabel lblDevolvidoQuando;
	private JLabel lblFaturadoSignificaQue;
	private JPanel panelBtn;
	private JButton btnLancarVenda;
	private JButton btnDevolverMerc;
	private JButton btnDevolucaoTotal;
	private static JTable tableConsignacao;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;
	private JLabel lblCodConsignao;
	private JTextField txtCodConsignacao;
	private JTextField txtVendedor;
	private JTextField txtNomeCliente;
	private JLabel lblVendedor;
	private JSeparator separatorVendedor;
	private JLabel lblCliente;
	private JLabel lblNome;
	private JSeparator separatorCliente;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JSeparator separatorCpf;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblUf;
	private JTextField txtUf;
	private JLabel lblContato;
	private JSeparator separatorEndereco;
	private JSeparator separatorNumero;
	private JSeparator separatorBairro;
	private JSeparator separatorCidade;
	private JSeparator separatorUf;
	private JTextField txtContato;
	private JSeparator separatorContato;
	private JLabel lblMercadorias;
	private JLabel lblStatus;
	private JTextField txtStatus;
	private JSeparator separatorStatus;
	private JScrollPane scrollPane_1;
	private static JTable tableMerc;
	private DefaultTableModel modelMerc;
	private JSeparator separatorCodCons;
	private static ControlerConsignacao controlerConsignacao;
	private JLabel lblPendenteDeFaturamento;
	private JLabel lblIdCliente;

	public AcompanharConsignacao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 760);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new LineBorder(Color.YELLOW, 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitulo = new JLabel("acompanhamento de consignacao");
		lblTitulo.setBounds(406, 4, 488, 35);
		contentPane.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Gtek Technology", Font.PLAIN, 17));

		btnX = new JButton("X");
		btnX.setBounds(1249, 4, 46, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(this);

		panelBtn = new JPanel();
		panelBtn.setLayout(null);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBackground(Color.YELLOW);
		panelBtn.setBounds(10, 50, 1280, 113);
		contentPane.add(panelBtn);

		btnLancarVenda = new JButton();
		btnLancarVenda.setIcon(
				new ImageIcon(AcompanharConsignacao.class.getResource("/com/sert/img/btnLancarVendaConsignacao.png")));
		btnLancarVenda.setBackground(Color.GREEN);
		btnLancarVenda.setBounds(10, 11, 89, 91);
		panelBtn.add(btnLancarVenda);
		btnLancarVenda.addActionListener(this);

		btnDevolverMerc = new JButton();
		btnDevolverMerc.setIcon(
				new ImageIcon(AcompanharConsignacao.class.getResource("/com/sert/img/btnDevolverMercConsignacao.png")));
		btnDevolverMerc.setBackground(new Color(0, 128, 0));
		btnDevolverMerc.setBounds(109, 11, 89, 91);
		panelBtn.add(btnDevolverMerc);
		btnDevolverMerc.addActionListener(this);

		btnDevolucaoTotal = new JButton();
		btnDevolucaoTotal.setIcon(
				new ImageIcon(AcompanharConsignacao.class.getResource("/com/sert/img/btnDevolucaoConsignacao.png")));
		btnDevolucaoTotal.setBackground(new Color(173, 216, 230));
		btnDevolucaoTotal.setBounds(208, 11, 89, 91);
		panelBtn.add(btnDevolucaoTotal);
		btnDevolucaoTotal.addActionListener(this);

		panelForm = new JPanel();
		panelForm.setBounds(10, 217, 1280, 532);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		panelLegenda = new JPanel();
		panelLegenda.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 0), 2, true), "Legenda",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLegenda.setBounds(10, 396, 627, 125);
		panelForm.add(panelLegenda);
		panelLegenda.setLayout(null);

		lblAbertoSignificaQue = new JLabel(
				"Aberto: Significa que a consignação está com o cliente e não retornou a loja ainda.");
		lblAbertoSignificaQue.setForeground(Color.RED);
		lblAbertoSignificaQue.setBounds(10, 13, 505, 14);
		panelLegenda.add(lblAbertoSignificaQue);

		lblDevolvidoQuando = new JLabel("Devolvido: É quando o cliente devolveu todas as mercadorias da consignação.");
		lblDevolvidoQuando.setForeground(new Color(255, 127, 80));
		lblDevolvidoQuando.setBounds(10, 40, 505, 14);
		panelLegenda.add(lblDevolvidoQuando);

		lblFaturadoSignificaQue = new JLabel(
				"Faturado: Significa que o cliente ficou com todas ou parte das mercadorias da consignação.");
		lblFaturadoSignificaQue.setForeground(Color.GREEN);
		lblFaturadoSignificaQue.setBounds(10, 67, 544, 14);
		panelLegenda.add(lblFaturadoSignificaQue);

		lblPendenteDeFaturamento = new JLabel(
				"<html><body>Pendente de faturamento: É quando o cliente devolveu alguma mercadoria<br> e a consignação não foi fechada.</body></html>");
		lblPendenteDeFaturamento.setForeground(Color.DARK_GRAY);
		lblPendenteDeFaturamento.setBounds(10, 80, 571, 34);
		panelLegenda.add(lblPendenteDeFaturamento);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 627, 309);
		panelForm.add(scrollPane);

		tableConsignacao = new JTable();
		modelo = new DefaultTableModel();
		modelo.addColumn("Cod. Cons.");
		modelo.addColumn("Cliente");
		modelo.addColumn("Data");
		modelo.addColumn("Status");
		tableConsignacao.setModel(modelo);
		tableConsignacao.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableConsignacao.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableConsignacao.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableConsignacao.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(tableConsignacao);
		tableConsignacao.getSelectionModel().addListSelectionListener(this);

		lblCodConsignao = new JLabel("Cod. Consignação:");
		lblCodConsignao.setBounds(647, 14, 119, 14);
		panelForm.add(lblCodConsignao);

		txtCodConsignacao = new JTextField();
		txtCodConsignacao.setEditable(false);
		txtCodConsignacao.setBorder(null);
		txtCodConsignacao.setBounds(758, 11, 86, 20);
		panelForm.add(txtCodConsignacao);
		txtCodConsignacao.setColumns(10);

		separatorCodCons = new JSeparator();
		separatorCodCons.setBackground(new Color(0, 0, 128));
		separatorCodCons.setBounds(647, 32, 197, 2);
		panelForm.add(separatorCodCons);

		lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(993, 14, 78, 14);
		panelForm.add(lblVendedor);

		txtVendedor = new JTextField();
		txtVendedor.setEditable(false);
		txtVendedor.setBorder(null);
		txtVendedor.setBounds(1053, 11, 217, 20);
		panelForm.add(txtVendedor);
		txtVendedor.setColumns(10);

		separatorVendedor = new JSeparator();
		separatorVendedor.setBackground(new Color(0, 0, 128));
		separatorVendedor.setBounds(993, 32, 277, 2);
		panelForm.add(separatorVendedor);

		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(647, 42, 57, 14);
		panelForm.add(lblStatus);

		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBorder(null);
		txtStatus.setBounds(693, 39, 151, 20);
		panelForm.add(txtStatus);

		separatorStatus = new JSeparator();
		separatorStatus.setBackground(new Color(0, 0, 128));
		separatorStatus.setBounds(647, 60, 197, 2);
		panelForm.add(separatorStatus);

		lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCliente.setBounds(647, 71, 57, 20);
		panelForm.add(lblCliente);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(647, 103, 46, 14);
		panelForm.add(lblNome);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setBounds(693, 100, 289, 20);
		txtNomeCliente.setBackground(new Color(240, 240, 240));
		txtNomeCliente.setBorder(null);
		panelForm.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		separatorCliente = new JSeparator();
		separatorCliente.setBackground(new Color(0, 0, 128));
		separatorCliente.setBounds(647, 120, 335, 2);
		panelForm.add(separatorCliente);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(993, 103, 28, 14);
		panelForm.add(lblCpf);

		txtCpf = new JDocumentFormatedField().getCpf();
		txtCpf.setBounds(1031, 100, 145, 20);
		panelForm.add(txtCpf);
		txtCpf.setBorder(null);
		txtCpf.setEditable(false);
		txtCpf.setBackground(new Color(240, 240, 240));
		txtCpf.setColumns(10);

		separatorCpf = new JSeparator();
		separatorCpf.setBackground(new Color(0, 0, 128));
		separatorCpf.setBounds(993, 120, 185, 2);
		panelForm.add(separatorCpf);

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(647, 136, 60, 14);
		panelForm.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setEditable(false);
		txtEndereco.setColumns(10);
		txtEndereco.setBorder(null);
		txtEndereco.setBounds(710, 133, 292, 20);
		panelForm.add(txtEndereco);

		separatorEndereco = new JSeparator();
		separatorEndereco.setBackground(new Color(0, 0, 128));
		separatorEndereco.setBounds(646, 153, 356, 2);
		panelForm.add(separatorEndereco);

		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(1062, 136, 55, 14);
		panelForm.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setColumns(10);
		txtNumero.setBorder(null);
		txtNumero.setBounds(1118, 133, 60, 20);
		panelForm.add(txtNumero);

		separatorNumero = new JSeparator();
		separatorNumero.setBackground(new Color(0, 0, 128));
		separatorNumero.setBounds(1059, 153, 119, 2);
		panelForm.add(separatorNumero);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(647, 175, 46, 14);
		panelForm.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setBorder(null);
		txtBairro.setColumns(10);
		txtBairro.setBounds(693, 172, 233, 20);
		panelForm.add(txtBairro);

		separatorBairro = new JSeparator();
		separatorBairro.setBackground(new Color(0, 0, 128));
		separatorBairro.setBounds(647, 192, 277, 9);
		panelForm.add(separatorBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(960, 175, 60, 14);
		panelForm.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setBorder(null);
		txtCidade.setColumns(10);
		txtCidade.setBounds(1007, 172, 126, 20);
		panelForm.add(txtCidade);

		separatorCidade = new JSeparator();
		separatorCidade.setBackground(new Color(0, 0, 128));
		separatorCidade.setBounds(960, 192, 174, 2);
		panelForm.add(separatorCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(1178, 175, 35, 14);
		panelForm.add(lblUf);

		txtUf = new JTextField();
		txtUf.setEditable(false);
		txtUf.setColumns(10);
		txtUf.setBorder(null);
		txtUf.setBounds(1206, 172, 60, 20);
		panelForm.add(txtUf);

		separatorUf = new JSeparator();
		separatorUf.setBackground(new Color(0, 0, 128));
		separatorUf.setBounds(1177, 192, 89, 2);
		panelForm.add(separatorUf);

		lblContato = new JLabel("Contato:");
		lblContato.setBounds(647, 210, 60, 14);
		panelForm.add(lblContato);

		txtContato = new JTextField();
		txtContato.setEditable(false);
		txtContato.setBounds(703, 207, 109, 20);
		txtContato.setBorder(null);
		panelForm.add(txtContato);
		txtContato.setColumns(10);

		separatorContato = new JSeparator();
		separatorContato.setBackground(new Color(0, 0, 128));
		separatorContato.setBounds(647, 227, 165, 2);
		panelForm.add(separatorContato);

		lblMercadorias = new JLabel("Mercadorias");
		lblMercadorias.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMercadorias.setBounds(647, 257, 86, 20);
		panelForm.add(lblMercadorias);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(647, 288, 623, 233);
		panelForm.add(scrollPane_1);

		modelMerc = new DefaultTableModel();
		modelMerc.addColumn("Cod barras");
		modelMerc.addColumn("Descrição");
		modelMerc.addColumn("Qnt");
		modelMerc.addColumn("Preço");
		modelMerc.addColumn("Status Merc");
		modelMerc.addColumn("");
		tableMerc = new JTable();
		tableMerc.setModel(modelMerc);
		tableMerc.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableMerc.getColumnModel().getColumn(1).setPreferredWidth(250);
		tableMerc.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableMerc.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableMerc.getColumnModel().getColumn(5).setMaxWidth(0);
		tableMerc.getColumnModel().getColumn(5).setMinWidth(0);
		tableMerc.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
		tableMerc.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
		tableMerc.getSelectionModel().addListSelectionListener(this);
		scrollPane_1.setViewportView(tableMerc);

		lblIdCliente = new JLabel();
		lblIdCliente.setBounds(1224, 103, 46, 14);

		listen();

		preencheTable();

	}

	private void preencheTable() {
		try {
			controlerConsignacao = new ControlerConsignacao();
			List<Orcamento> listOrc = controlerConsignacao.listarConsignacao();

			for (Orcamento orc : listOrc) {
				modelo.addRow(new Object[] { orc.getId(), orc.getCliente().getNome(), orc.getData(), orc.getStatus() });
			}
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
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

	public static void faturarConsignacao() {
		try {
			Orcamento consignacao = new Orcamento();
			consignacao.setStatus("Faturado");
			consignacao.setId((int) tableConsignacao.getValueAt(tableConsignacao.getSelectedRow(), 0));
			Mercadoria merc;
			List<Mercadoria> mercadorias = new ArrayList<Mercadoria>();
			for (int i = 0; i < tableMerc.getRowCount(); i++) {
				merc = new Mercadoria();
				merc.setCodBarras((long) tableMerc.getValueAt(i, 0));
				merc.setEstoque((float) tableMerc.getValueAt(i, 2));
				merc.setPrecoVenda((float) tableMerc.getValueAt(i, 3));
				merc.setId((int) tableMerc.getValueAt(i, 5));
				merc.setStatus("Faturado");
				mercadorias.add(merc);
			}
			consignacao.setMercadorias(mercadorias);
			controlerConsignacao.faturarConsignacao(consignacao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnX) {
			dispose();
		} else if (e.getSource() == btnLancarVenda) {
			PontoDeVenda pdv = new PontoDeVenda();
			pdv.addConsig(txtNomeCliente.getText(), lblIdCliente.getText(), txtCpf.getText());
			for (int i = 0; i < tableMerc.getRowCount(); i++) {
				if (tableMerc.getValueAt(i, 4).equals("Aberto")) {
					pdv.addMercConsig((long) tableMerc.getValueAt(i, 0), (float) tableMerc.getValueAt(i, 2),
							(float) tableMerc.getValueAt(i, 3));
				}
			}
			pdv.setVisible(true);

		} else if (e.getSource() == btnDevolverMerc) {
			try {
				if (tableConsignacao.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma consignação");
					return;
				}
				if (tableMerc.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma mercadoria, ou faça a decolução total");
					return;
				}
				Orcamento consignacao = new Orcamento();
				consignacao.setStatus("Pendente de Faturamento");
				consignacao.setId((int) tableConsignacao.getValueAt(tableConsignacao.getSelectedRow(), 0));
				Mercadoria merc = new Mercadoria();
				merc.setCodBarras((long) tableMerc.getValueAt(tableMerc.getSelectedRow(), 0));
				merc.setEstoque((float) tableMerc.getValueAt(tableMerc.getSelectedRow(), 2));
				merc.setPrecoVenda((float) tableMerc.getValueAt(tableMerc.getSelectedRow(), 3));
				merc.setId((int) tableMerc.getValueAt(tableMerc.getSelectedRow(), 5));
				merc.setStatus("Devolvido");
				List<Mercadoria> mercadorias = new ArrayList<Mercadoria>();
				mercadorias.add(merc);
				consignacao.setMercadorias(mercadorias);

				controlerConsignacao.devolucaoConsig(consignacao);
				atualizartableMerc();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == btnDevolucaoTotal) {
			try {
				if (tableConsignacao.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma consignação");
					return;
				}
				Orcamento consignacao = new Orcamento();
				consignacao.setStatus("Devolvido");
				consignacao.setId((int) tableConsignacao.getValueAt(tableConsignacao.getSelectedRow(), 0));
				Mercadoria merc;
				List<Mercadoria> mercadorias = new ArrayList<Mercadoria>();
				for (int i = 0; i < tableMerc.getRowCount(); i++) {
					merc = new Mercadoria();
					merc.setCodBarras((long) tableMerc.getValueAt(i, 0));
					merc.setEstoque((float) tableMerc.getValueAt(i, 2));
					merc.setPrecoVenda((float) tableMerc.getValueAt(i, 3));
					merc.setId((int) tableMerc.getValueAt(i, 5));
					merc.setStatus("Devolvido");
					mercadorias.add(merc);
				}
				consignacao.setMercadorias(mercadorias);

				controlerConsignacao.devolucaoConsig(consignacao);

				atualizartableConsig();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == tableConsignacao.getSelectionModel()) {

			if (e.getValueIsAdjusting()) {
				int linha = tableConsignacao.getSelectedRow();
				int idCons = Integer.parseInt(tableConsignacao.getValueAt(linha, 0).toString());
				String status = tableConsignacao.getValueAt(linha, 3).toString();

				if (status.equals("Faturado") || status.equals("Devolvido")) {
					btnDevolucaoTotal.setEnabled(false);
					btnDevolverMerc.setEnabled(false);
					btnLancarVenda.setEnabled(false);
				} else {
					btnDevolucaoTotal.setEnabled(true);
					btnDevolverMerc.setEnabled(true);
					btnLancarVenda.setEnabled(true);
				}

				try {
					Orcamento orcamento = new ControlerConsignacao().consultarConsignacao(idCons);
					txtCodConsignacao.setText(String.valueOf(orcamento.getId()));
					txtVendedor.setText(orcamento.getUsuario().getNome());
					txtStatus.setText(orcamento.getStatus());
					txtNomeCliente.setText(orcamento.getCliente().getNome());
					txtCpf.setText(String.valueOf(orcamento.getCliente().getCpf()));
					txtEndereco.setText(orcamento.getCliente().getRua());
					txtBairro.setText(orcamento.getCliente().getBairro());
					txtCidade.setText(orcamento.getCliente().getCidade());
					txtUf.setText(orcamento.getCliente().getUf());
					txtContato.setText(String.valueOf(orcamento.getCliente().getContato()));
					txtNumero.setText(String.valueOf(orcamento.getCliente().getNumero()));
					lblIdCliente.setText(String.valueOf(orcamento.getCliente().getId()));

					if (tableMerc.getRowCount() != -1) {
						modelMerc.setRowCount(0);
					}

					for (Mercadoria merc : orcamento.getMercadorias()) {
						modelMerc.addRow(new Object[] { merc.getCodBarras(), merc.getMercadoria(), merc.getEstoque(),
								merc.getPrecoVenda(), merc.getStatus(), merc.getId() });
					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} // FIm do if da tabela consignação
		if (e.getSource() == tableMerc.getSelectionModel()) {
			if (e.getValueIsAdjusting()) {
				String status = tableMerc.getValueAt(tableMerc.getSelectedRow(), 4).toString();
				if (status.equals("Devolvido") || status.equals("Faturado")) {
					btnDevolucaoTotal.setEnabled(false);
					btnDevolverMerc.setEnabled(false);
					btnLancarVenda.setEnabled(false);
				} else {
					btnDevolucaoTotal.setEnabled(true);
					btnDevolverMerc.setEnabled(true);
					btnLancarVenda.setEnabled(true);
				}
			}
		}
	}

	public void atualizartableMerc() {
		int idCons = Integer.parseInt(tableConsignacao.getValueAt(tableConsignacao.getSelectedRow(), 0).toString());
		try {
			Orcamento orcamento = new ControlerConsignacao().consultarConsignacao(idCons);
			if (tableMerc.getRowCount() != -1) {
				modelMerc.setRowCount(0);
			}

			for (Mercadoria merc : orcamento.getMercadorias()) {
				modelMerc.addRow(new Object[] { merc.getCodBarras(), merc.getMercadoria(), merc.getEstoque(),
						merc.getPrecoVenda(), merc.getStatus(), merc.getId() });
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void atualizartableConsig() {
		for (int i = 0; i < tableConsignacao.getRowCount(); i++) {
			modelo.removeRow(0);
		}
		tableConsignacao.setModel(modelo);

		preencheTable();
	}
}