package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import java.awt.Color;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerDuplicata;
import com.sert.controler.JDateField;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Cliente;
import com.sert.entidades.DuplicataCliente;
import com.sert.entidades.Usuario;
import com.sert.entidades.Venda;
import com.sert.impressao.PrintableComprovanteDuplicata;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class BaixarParcela extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JLabel lblTitle;
	private JPanel panelBtn;
	private JButton btnConfirmarBaixa;
	private JPanel panel;
	private JLabel lblId;
	private JTextField txtId;
	private JLabel lblCliente;
	private JTextField txtCliente;
	private JLabel lblCpf;
	private JTextField txtCpf;
	private JLabel lblParcela;
	private JLabel lblVendedor;
	private JTextField txtVendedor;
	private JLabel lblVenda;
	private JLabel lblDataDaVenda;
	private JTextField txtIdVenda;
	private JTextField txtDataVenda;
	private JLabel lblVencimento;
	private JLabel lblValorDaParcela;
	private JTextField txtVencimento;
	private JTextField txtValorParcela;
	private JLabel lblValorABaixar;
	private JTextField txtValorBaixa;
	private int idParcela;
	private float valorReal;
	private ControlerDuplicata controler;
	private JCheckBox chckbxAlterarValorDa;
	private JLabel lblUsuarioBaixa;
	private JTextField txtUsuario;
	private JLabel lblDataDaBaixa;
	private JTextField txtDataBaixa;
	private DuplicataCliente duplicata;
	private JLabel lblValorBaixado;
	private JTextField txtValorBaixado;
	private JLabel lblAviso;

	/**
	 * Create the dialog.
	 */
	// Se a tela de origem for 0 ele vem do cadastro do cliente, então a tabela de
	// debito especifico deve atualizar
	// Caso a tela seja 1 então a tabela da tela ContasAReceber será atualizada
	// Caso vizualização for true habilitará alguns campos extras e desabilitará
	// todos os textFields e buttons
	public BaixarParcela(int idParcela, int telaOrigem, boolean vizualizacao) {
		setBounds(100, 100, 675, 541);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		this.idParcela = idParcela;
		listen();

		lblTitle = new JLabel();
		lblTitle.setText("confirmar baixa");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(93, 0, 489, 33);
		contentPanel.add(lblTitle);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(629, 0, 46, 23);
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
		panelBtn.setBounds(12, 34, 651, 113);
		contentPanel.add(panelBtn);

		btnConfirmarBaixa = new JButton();
		btnConfirmarBaixa.setIcon(new ImageIcon(BaixarParcela.class.getResource("/com/sert/img/btnBaixar.png")));
		btnConfirmarBaixa.setBackground(Color.GREEN);
		btnConfirmarBaixa.setBounds(10, 11, 89, 91);
		panelBtn.add(btnConfirmarBaixa);
		btnConfirmarBaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				baixarConta();
				if (telaOrigem == 0) {
					CadCliente.atualizarLista("");
				} else {
					ContasAReceber.atualizarLista();
				}
				dispose();
			}
		});

		panel = new JPanel();
		panel.setBounds(12, 155, 651, 375);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblId = new JLabel("id:");
		lblId.setBounds(10, 11, 26, 14);
		panel.add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(46, 8, 57, 20);
		panel.add(txtId);
		txtId.setColumns(10);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 54, 46, 14);
		panel.add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(66, 51, 311, 20);
		panel.add(txtCliente);
		txtCliente.setColumns(10);

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(398, 51, 36, 14);
		panel.add(lblCpf);

		txtCpf = new JDocumentFormatedField().getCpf();
		txtCpf.setEditable(false);
		txtCpf.setBounds(444, 48, 107, 20);
		panel.add(txtCpf);
		txtCpf.setColumns(10);

		lblParcela = new JLabel("Parcela: ");
		lblParcela.setBounds(509, 11, 85, 14);
		panel.add(lblParcela);

		lblVendedor = new JLabel("Vendedor: ");
		lblVendedor.setBounds(10, 110, 65, 14);
		panel.add(lblVendedor);

		txtVendedor = new JTextField();
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(75, 107, 152, 20);
		panel.add(txtVendedor);
		txtVendedor.setColumns(10);

		lblVenda = new JLabel("Venda: ");
		lblVenda.setBounds(10, 153, 46, 14);
		panel.add(lblVenda);

		lblDataDaVenda = new JLabel("Data da venda:");
		lblDataDaVenda.setBounds(142, 153, 85, 14);
		panel.add(lblDataDaVenda);

		txtIdVenda = new JTextField();
		txtIdVenda.setEditable(false);
		txtIdVenda.setBounds(58, 150, 57, 20);
		panel.add(txtIdVenda);
		txtIdVenda.setColumns(10);

		txtDataVenda = new JTextField();
		txtDataVenda.setEditable(false);
		txtDataVenda.setBounds(237, 150, 140, 20);
		panel.add(txtDataVenda);
		txtDataVenda.setColumns(10);

		lblVencimento = new JLabel("Vencimento:");
		lblVencimento.setBounds(10, 208, 78, 14);
		panel.add(lblVencimento);

		lblValorDaParcela = new JLabel("Valor da parcela:");
		lblValorDaParcela.setBounds(237, 208, 107, 14);
		panel.add(lblValorDaParcela);

		txtVencimento = new JTextField();
		txtVencimento.setEditable(false);
		txtVencimento.setBounds(90, 205, 107, 20);
		panel.add(txtVencimento);
		txtVencimento.setColumns(10);

		txtValorParcela = new JNumberFormatField(new DecimalFormat("0.00"));
		txtValorParcela.setEditable(false);
		txtValorParcela.setBounds(348, 205, 86, 20);
		panel.add(txtValorParcela);
		txtValorParcela.setColumns(10);

		lblValorABaixar = new JLabel("Valor a baixar: ");
		lblValorABaixar.setBounds(10, 272, 85, 14);
		panel.add(lblValorABaixar);

		txtValorBaixa = new JNumberFormatField(new DecimalFormat("0.00"));
		txtValorBaixa.setBounds(111, 269, 86, 20);
		panel.add(txtValorBaixa);
		txtValorBaixa.setColumns(10);

		chckbxAlterarValorDa = new JCheckBox("Alterar valor da parcela");
		chckbxAlterarValorDa.setBounds(440, 204, 170, 23);
		panel.add(chckbxAlterarValorDa);

		chckbxAlterarValorDa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxAlterarValorDa.isSelected()) {
					txtValorParcela.setEditable(true);
				} else {
					txtValorParcela.setEditable(false);
				}
			}
		});

		preencheCampos();
		if (vizualizacao) {
			vizualizarDuplicata();
		}
	}

	private void preencheCampos() {
		try {
			controler = new ControlerDuplicata();
			duplicata = controler.consultaDuplicata(idParcela);
			txtId.setText(String.valueOf(duplicata.getId()));
			lblParcela.setText("Parcela: " + duplicata.getNumParcela());
			txtCliente.setText(duplicata.getCliente().getNome());
			txtCpf.setText(String.valueOf(duplicata.getCliente().getCpf()));
			txtVendedor.setText(duplicata.getVenda().getVendedor());
			txtIdVenda.setText(String.valueOf(duplicata.getVenda().getId()));
			txtVencimento.setText(duplicata.getDataVencimento());
			txtDataVenda.setText(duplicata.getVenda().getDataVenda());
			txtValorParcela.setText(String.format("%.2f", duplicata.getValor()));
			txtValorBaixa.setText(String.format("%.2f", duplicata.getValor()));
			valorReal = duplicata.getValor();

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		}

	}

	private void baixarConta() {
		DuplicataCliente duplicataBaixa = new DuplicataCliente();
		Usuario usu = new Usuario();
		Cliente cliente = new Cliente();
		Venda venda = new Venda();
		venda.setId(Integer.parseInt(txtIdVenda.getText()));
		cliente.setNome(txtCliente.getText());
		usu.setId(UsuLogado.getId());
		duplicataBaixa.setId(Integer.parseInt(txtId.getText()));
		duplicataBaixa.setDataBaixa(JDateField.getDateHoraStatic());
		duplicataBaixa.setVenda(venda);
		duplicataBaixa.setUsuBaixa(usu);
		duplicataBaixa.setCliente(cliente);
		duplicataBaixa.setValor(Float.parseFloat(txtValorParcela.getText().replace(",", ".")));
		duplicataBaixa.setSituacao("Baixado");

		float valorBaixa = Float.parseFloat(txtValorBaixa.getText().replace(",", "."));

		duplicataBaixa.setValorBaixa(valorBaixa);

		try {
			controler = new ControlerDuplicata();
			if (valorBaixa == valorReal) {
				controler.baixarDuplicata(duplicataBaixa);
				JOptionPane.showMessageDialog(null, "Baixa realizada com sucesso!");
				new PrintableComprovanteDuplicata(duplicataBaixa);
			} else if (valorBaixa < valorReal) {
				int opcao = JOptionPane.showConfirmDialog(null,
						"O valor pago é menor que a parcela!\n Deseja criar uma pendência do valor restante para 30 dias ?",
						"Valor menor que o devido", JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					DuplicataCliente pendencia = controler.consultaDuplicata(duplicataBaixa.getId());
					pendencia.setValor(valorReal - valorBaixa);
					pendencia.setNumParcela(1);
					controler.lancarDuplicata(pendencia);
					controler.baixarDuplicata(duplicataBaixa);
					JOptionPane.showMessageDialog(null, "Baixa realizada com sucesso!");
				} else {
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null, "O valor pago não pode ser maior que o da parcela");
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| BAIXAR PARCELA |" + e1.getMessage());
		}
	}

	public void vizualizarDuplicata() {
		btnConfirmarBaixa.setEnabled(false);
		txtValorBaixa.setVisible(false);
		chckbxAlterarValorDa.setVisible(false);
		lblValorABaixar.setVisible(false);
		lblTitle.setText("vizulaizar parcela");

		lblUsuarioBaixa = new JLabel("Usuario a baixar:");
		lblUsuarioBaixa.setBounds(10, 272, 105, 14);
		panel.add(lblUsuarioBaixa);

		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setBounds(111, 269, 146, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblDataDaBaixa = new JLabel("Data da baixa:");
		lblDataDaBaixa.setBounds(298, 272, 93, 14);
		panel.add(lblDataDaBaixa);

		txtDataBaixa = new JTextField();
		txtDataBaixa.setEditable(false);
		txtDataBaixa.setBounds(387, 269, 140, 20);
		panel.add(txtDataBaixa);
		txtDataBaixa.setColumns(10);

		lblValorBaixado = new JLabel("Valor baixado:");
		lblValorBaixado.setBounds(10, 334, 93, 14);
		panel.add(lblValorBaixado);

		txtValorBaixado = new JTextField();
		txtValorBaixado.setEditable(false);
		txtValorBaixado.setBounds(97, 331, 100, 20);
		panel.add(txtValorBaixado);
		txtValorBaixado.setColumns(10);

		lblAviso = new JLabel("");
		lblAviso.setForeground(new Color(255, 0, 0));
		lblAviso.setBounds(260, 325, 367, 33);
		panel.add(lblAviso);

		if (duplicata.getUsuBaixa() != null && duplicata.getDataBaixa() != null) {
			txtUsuario.setText(duplicata.getUsuBaixa().getNome());
			txtDataBaixa.setText(duplicata.getDataBaixa());
			txtValorBaixado.setText(String.format("%.2f", duplicata.getValorBaixa()));
		}

		if (duplicata.getValorBaixa() < duplicata.getValor()) {
			if (duplicata.getValorBaixa() != 0) {
				lblAviso.setText(
						"<html>O valor baixado e o valor da parcela não são iguais, isso significa que uma pendência foi gerada dessa duplicata</html>");
			}
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