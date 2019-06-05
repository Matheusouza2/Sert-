package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.MercadoriaNaoEncontradaException;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class CadMercadoria extends JDialog implements ActionListener, FocusListener {

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
	private JLabel lblUnidade;
	private JLabel lblPrecoCompra;
	private JLabel lblUltimoUsuEdit;
	private JLabel lblDataDeEdicao;

	private JTextField txtCodMercadoria;
	private JTextField txtCodBarras;
	private JTextField txtDescricaoMerc;
	private JNumberFormatField txtPrecoVenda;
	private JComboBox<String> cbUnd;
	private JNumberFormatField txtPrecoCompra;

	private JButton btnSalvar;
	private JButton btnX;

	private JLabel lblCadastroDeMercadoria;

	private JLabel lblLucro;
	private String dataPrincipal;
	private int idGerador;
	private ControlerMercadoria controlerMercadoria;
	private JLabel lblEstoque;
	private Mercadoria mercadoria;
	private JSeparator separatorCodBarras;
	private JSeparator separatorDescricao;
	private static String dataSalvAlt;
	private JSeparator separatorPrecComp;
	private JSeparator separatorPrecVend;
	private JSeparator separatorIdMerc;

	public CadMercadoria(int operacao, long codBarras) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

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

		lblCadastroDeMercadoria = new JLabel("cadastro de mercadoria");
		lblCadastroDeMercadoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeMercadoria.setForeground(Color.WHITE);
		lblCadastroDeMercadoria.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeMercadoria.setBounds(248, 0, 337, 35);
		contentPanel.add(lblCadastroDeMercadoria);

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		dataPrincipal = JDateField.getDate();

		btnSalvar = new JButton();
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBorderPainted(false);
		btnSalvar.addActionListener(this);
		btnSalvar.setIcon(new ImageIcon(CadMercadoria.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 814, 421);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		lblCodigoMercadoria = new JLabel("Código Mercadoria:");
		lblCodigoMercadoria.setForeground(new Color(128, 128, 128));
		lblCodigoMercadoria.setBounds(10, 14, 114, 14);
		panelForm.add(lblCodigoMercadoria);

		try {
			idGerador = new ControlerMercadoria().confereId();
			txtCodMercadoria = new JTextField();
			txtCodMercadoria.setForeground(new Color(128, 128, 128));
			txtCodMercadoria.setEnabled(false);
			txtCodMercadoria.setBorder(null);
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

		separatorIdMerc = new JSeparator();
		separatorIdMerc.setBackground(new Color(0, 0, 128));
		separatorIdMerc.setBounds(10, 31, 182, 2);
		panelForm.add(separatorIdMerc);

		lblCodDeBarras = new JLabel("Cod. de barras:");
		lblCodDeBarras.setForeground(new Color(128, 128, 128));
		lblCodDeBarras.setBounds(263, 14, 88, 14);
		panelForm.add(lblCodDeBarras);

		txtCodBarras = new JNumberField();
		txtCodBarras.setForeground(new Color(128, 128, 128));
		txtCodBarras.setBounds(353, 11, 164, 20);
		txtCodBarras.setBorder(null);
		txtCodBarras.setBackground(new Color(240, 240, 240));
		txtCodBarras.setFocusable(true);
		panelForm.add(txtCodBarras);
		txtCodBarras.setColumns(10);
		if (operacao == 1) {
			lblEstoque = new JLabel("Estoque: ");
			lblEstoque.setBounds(661, 14, 143, 14);
			panelForm.add(lblEstoque);
		}

		separatorCodBarras = new JSeparator();
		separatorCodBarras.setBackground(new Color(0, 0, 128));
		separatorCodBarras.setBounds(263, 31, 254, 2);
		panelForm.add(separatorCodBarras);

		lblDescricao = new JLabel("Descrição:");
		lblDescricao.setForeground(new Color(128, 128, 128));
		lblDescricao.setBounds(10, 64, 63, 14);
		panelForm.add(lblDescricao);

		txtDescricaoMerc = new JTextField();
		txtDescricaoMerc.setForeground(new Color(128, 128, 128));
		txtDescricaoMerc.setBounds(72, 61, 445, 20);
		txtDescricaoMerc.setBorder(null);
		txtDescricaoMerc.setBackground(new Color(240, 240, 240));
		panelForm.add(txtDescricaoMerc);
		txtDescricaoMerc.setColumns(10);

		separatorDescricao = new JSeparator();
		separatorDescricao.setBackground(new Color(0, 0, 128));
		separatorDescricao.setBounds(10, 81, 507, 2);
		panelForm.add(separatorDescricao);

		lblUnidade = new JLabel("Unidade:");
		lblUnidade.setForeground(new Color(128, 128, 128));
		lblUnidade.setBounds(593, 64, 58, 14);
		panelForm.add(lblUnidade);

		cbUnd = new JComboBox<String>();
		cbUnd.setBackground(new Color(240, 240, 240));
		cbUnd.setForeground(new Color(128, 128, 128));
		cbUnd.setModel(new DefaultComboBoxModel<String>(new String[] { "", "CJ", "CX", "FD", "UN", "RS", "PC" }));
		cbUnd.setBounds(661, 61, 88, 20);
		panelForm.add(cbUnd);

		lblPrecoCompra = new JLabel("Preço Compra (R$):");
		lblPrecoCompra.setForeground(new Color(128, 128, 128));
		lblPrecoCompra.setBounds(10, 151, 114, 14);
		panelForm.add(lblPrecoCompra);

		txtPrecoCompra = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoCompra.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecoCompra.setForeground(new Color(128, 128, 128));
		txtPrecoCompra.setBorder(null);
		txtPrecoCompra.setBackground(new Color(240, 240, 240));
		txtPrecoCompra.setColumns(10);
		txtPrecoCompra.setBounds(122, 148, 98, 20);
		panelForm.add(txtPrecoCompra);
		txtPrecoCompra.addFocusListener(this);

		separatorPrecComp = new JSeparator();
		separatorPrecComp.setBackground(new Color(0, 0, 128));
		separatorPrecComp.setBounds(10, 168, 210, 2);
		panelForm.add(separatorPrecComp);

		lblPrecoVenda = new JLabel("Preço venda (R$):");
		lblPrecoVenda.setForeground(new Color(128, 128, 128));
		lblPrecoVenda.setBounds(399, 151, 100, 14);
		panelForm.add(lblPrecoVenda);

		txtPrecoVenda = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoVenda.setHorizontalAlignment(SwingConstants.LEFT);
		txtPrecoVenda.setForeground(new Color(128, 128, 128));
		txtPrecoVenda.setBorder(null);
		txtPrecoVenda.setBackground(new Color(240, 240, 240));
		txtPrecoVenda.setLimit(6);
		txtPrecoVenda.setBounds(499, 148, 96, 20);
		panelForm.add(txtPrecoVenda);
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.addFocusListener(this);

		separatorPrecVend = new JSeparator();
		separatorPrecVend.setBackground(new Color(0, 0, 128));
		separatorPrecVend.setBounds(399, 168, 196, 2);
		panelForm.add(separatorPrecVend);

		lblLucro = new JLabel("Lucro (%): ");
		lblLucro.setForeground(new Color(128, 128, 128));
		lblLucro.setBounds(399, 202, 162, 14);
		panelForm.add(lblLucro);

		lblDataDeInclusao = new JLabel("Data de cadastro: " + dataPrincipal);
		lblDataDeInclusao.setForeground(new Color(128, 128, 128));
		lblDataDeInclusao.setBounds(10, 294, 210, 14);
		panelForm.add(lblDataDeInclusao);

		lblUsuario = new JLabel("Usuário: " + UsuLogado.getNome());
		lblUsuario.setForeground(new Color(128, 128, 128));
		lblUsuario.setBounds(564, 294, 205, 14);
		panelForm.add(lblUsuario);

		if (operacao == 1) {
			lblCadastroDeMercadoria.setText("alterar mercadoria");
			lblDataDeEdicao = new JLabel();
			lblDataDeEdicao.setBounds(10, 396, 210, 14);
			panelForm.add(lblDataDeEdicao);

			lblUltimoUsuEdit = new JLabel();
			lblUltimoUsuEdit.setBounds(555, 396, 249, 14);
			panelForm.add(lblUltimoUsuEdit);
			// Trecho de consulta do cadastro da mercadoria para edição da mesma
			try {
				controlerMercadoria = new ControlerMercadoria();
				mercadoria = controlerMercadoria.consultaMercadoria(codBarras);
				txtCodMercadoria.setText(String.valueOf(mercadoria.getId()));
				txtCodBarras.setText(String.valueOf(mercadoria.getCodBarras()));
				txtDescricaoMerc.setText(mercadoria.getMercadoria().trim());
				txtPrecoVenda.setText(String.format("%.2f", mercadoria.getPrecoVenda()));
				cbUnd.setSelectedItem(mercadoria.getUnd().trim());
				lblDataDeInclusao.setText("data de cadastro: " + mercadoria.getDataCadastro());
				txtPrecoCompra.setText(String.format("%.2f", mercadoria.getPrecoCompra()));
				lblEstoque.setText("Estoque: " + String.format("%.2f", mercadoria.getEstoque()));
				lblDataDeEdicao.setText("Data de edição: " + mercadoria.getDataAlt());
				lblUltimoUsuEdit.setText("Último usuário a editar: " + mercadoria.getUsuAlt());
				dataSalvAlt = mercadoria.getDataCadastro();
				float precoCompra = mercadoria.getPrecoCompra();
				float precoVenda = mercadoria.getPrecoVenda();
				float lucro = ((precoVenda / precoCompra) - 1) * 100;
				if (lucro <= 0) {
					lblLucro.setText("Lucro (%): " + 0);
				} else {
					lblLucro.setText(String.format("Lucro (%%): %.2f", lucro));
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
			} catch (MercadoriaNaoEncontradaException e1) {

			}
			// Fim do trecho de consulta do cadastro da mercadoria para edição
			// da
			// mesma
		}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource() == txtPrecoCompra) {
			float precoCompra = Float.parseFloat(txtPrecoCompra.getText().replace(",", "."));
			float precoVenda = Float.parseFloat(txtPrecoVenda.getText().replace(",", "."));
			float lucro = ((precoVenda / precoCompra) - 1) * 100;
			if (lucro <= 0) {
				lblLucro.setText("Lucro (%): " + 0);
			} else {
				lblLucro.setText(String.format("Lucro (%%): %.2f", lucro));
			}
		} else if (arg0.getSource() == txtPrecoVenda) {
			float precoCompra = Float.parseFloat(txtPrecoCompra.getText().replace(",", "."));
			float precoVenda = Float.parseFloat(txtPrecoVenda.getText().replace(",", "."));
			float lucro = (((precoVenda / precoCompra) - 1) * 100);

			lblLucro.setText(String.format("Lucro (%%): %.2f", lucro));
		}

	}
}