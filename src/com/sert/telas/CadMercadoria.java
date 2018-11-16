package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.JDateField;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.MercadoriaNaoEncontradaException;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class CadMercadoria extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JLabel lblCodigoMercadoria;
	private JLabel lblDataDeInclusao;
	private JLabel lblCodDeBarras;
	private JLabel lblDescricao;
	private JLabel lblPrecoVenda;
	private JLabel lblUsuario;
	private JLabel lblData;
	private JLabel lblUnidade;
	private JLabel lblPrecoCompra;

	private JTextField txtCodMercadoria;
	private JTextField txtCodBarras;
	private JTextField txtDescricaoMerc;
	private JNumberFormatField txtPrecoVenda;
	private JComboBox<String> cbUnd;
	private JNumberFormatField txtPrecoCompra;

	private JButton btnSalvar;
	private JButton btnX;

	private int codUsu;
	private JLabel lblCadastroDeMercadoria;

	private JLabel lblLucro;

	private int idGerador;

	public CadMercadoria() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		String dataPrincipal = new JDateField().getDate();

		btnSalvar = new JButton();
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBorderPainted(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtCodBarras.getText().equals("") || txtDescricaoMerc.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Os campos 'Cod. de Barras' e 'Descrição' não podem estar em branco", "Erro",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Mercadoria mercadoria = new Mercadoria(0, Long.parseLong(txtCodBarras.getText()),
								txtDescricaoMerc.getText(), Float.parseFloat(txtPrecoVenda.getText().replace(",", ".")),
								lblData.getText(), codUsu, cbUnd.getSelectedItem().toString(),
								Float.parseFloat(txtPrecoCompra.getText().replace(",", ".")), 0, dataPrincipal, 0);
						new ControlerMercadoria().cadastrarMercadoria(mercadoria);
						JOptionPane.showMessageDialog(null, "Mercadoria cadastrada com sucesso", "Sucesso!",
								JOptionPane.INFORMATION_MESSAGE);
						limpaCampos();
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
				} catch (CodBarrasJaCadastradoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				} catch (MercadoriaNaoEncontradaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(CadMercadoria.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		JButton button_1 = new JButton("");
		button_1.setBounds(109, 11, 89, 91);
		panelBtn.add(button_1);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 814, 421);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		lblCodigoMercadoria = new JLabel("Código Mercadoria:");
		lblCodigoMercadoria.setBounds(10, 14, 114, 14);
		panelForm.add(lblCodigoMercadoria);

		try {
			idGerador = new ControlerMercadoria().confereId();
			txtCodMercadoria = new JTextField();
			txtCodMercadoria.setEnabled(false);
			txtCodMercadoria.setBounds(134, 11, 58, 20);
			txtCodMercadoria.setText(String.valueOf(idGerador));
			panelForm.add(txtCodMercadoria);
			txtCodMercadoria.setColumns(10);
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

		lblCodDeBarras = new JLabel("Cod. de barras");
		lblCodDeBarras.setBounds(263, 14, 88, 14);
		panelForm.add(lblCodDeBarras);

		txtCodBarras = new JNumberField();
		txtCodBarras.setBounds(361, 11, 156, 20);
		panelForm.add(txtCodBarras);
		txtCodBarras.setColumns(10);

		lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 64, 63, 14);
		panelForm.add(lblDescricao);

		txtDescricaoMerc = new JTextField();
		txtDescricaoMerc.setBounds(83, 61, 434, 20);
		panelForm.add(txtDescricaoMerc);
		txtDescricaoMerc.setColumns(10);

		lblPrecoVenda = new JLabel("Preço venda (R$):");
		lblPrecoVenda.setBounds(399, 154, 100, 14);
		panelForm.add(lblPrecoVenda);

		txtPrecoVenda = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoVenda.setLimit(6);
		txtPrecoVenda.setBounds(509, 151, 86, 20);
		panelForm.add(txtPrecoVenda);
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				float precoCompra = Float.parseFloat(txtPrecoCompra.getText().replace(",", "."));
				float precoVenda = Float.parseFloat(txtPrecoVenda.getText().replace(",", "."));
				float lucro = ((precoVenda / precoCompra) - 1) * 100;
				if (lucro <= 0) {
					lblLucro.setText("Lucro (%): " + 0);
				} else {
					lblLucro.setText(String.format("Lucro (%%): %.2f", lucro));
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});

		lblDataDeInclusao = new JLabel("Data de inclusão:");
		lblDataDeInclusao.setBounds(10, 294, 100, 14);
		panelForm.add(lblDataDeInclusao);

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(564, 294, 205, 14);
		panelForm.add(lblUsuario);

		lblData = new JLabel(dataPrincipal);
		lblData.setBounds(118, 294, 108, 14);
		panelForm.add(lblData);

		lblUnidade = new JLabel("Unidade:");
		lblUnidade.setBounds(593, 64, 58, 14);
		panelForm.add(lblUnidade);

		cbUnd = new JComboBox<String>();
		cbUnd.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "CJ", "CM", "CX", "EMBAL", "FARDO", "FOLHA", "FRASCO", "UNID", "RESMA" }));
		cbUnd.setBounds(661, 61, 88, 20);
		panelForm.add(cbUnd);

		lblPrecoCompra = new JLabel("Preço Compra (R$):");
		lblPrecoCompra.setBounds(10, 151, 114, 14);
		panelForm.add(lblPrecoCompra);

		lblLucro = new JLabel("Lucro (%): ");
		lblLucro.setBounds(399, 202, 162, 14);
		panelForm.add(lblLucro);

		txtPrecoCompra = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoCompra.setColumns(10);
		txtPrecoCompra.setBounds(134, 148, 86, 20);
		panelForm.add(txtPrecoCompra);
		txtPrecoCompra.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				float precoCompra = Float.parseFloat(txtPrecoCompra.getText().replace(",", "."));
				float precoVenda = Float.parseFloat(txtPrecoVenda.getText().replace(",", "."));
				float lucro = ((precoVenda / precoCompra) - 1) * 100;
				if (lucro <= 0) {
					lblLucro.setText("Lucro (%): " + 0);
				} else {
					lblLucro.setText(String.format("Lucro (%%): %.2f", lucro));
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);

		lblCadastroDeMercadoria = new JLabel("cadastro de mercadoria");
		lblCadastroDeMercadoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeMercadoria.setForeground(Color.WHITE);
		lblCadastroDeMercadoria.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeMercadoria.setBounds(248, 0, 337, 35);
		contentPanel.add(lblCadastroDeMercadoria);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	public void limpaCampos() {
		try {
			idGerador = new ControlerMercadoria().confereId();
			txtCodMercadoria.setText(String.valueOf(idGerador));
			txtCodBarras.setText(null);
			txtDescricaoMerc.setText(null);
			txtPrecoVenda.setText(null);
			txtPrecoCompra.setText(null);
			cbUnd.setSelectedIndex(-1);
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
}