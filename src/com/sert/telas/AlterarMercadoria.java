package com.sert.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sert.controler.ControlerMercadoria;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;

import javax.swing.JComboBox;

import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.MercadoriaNaoEncontradaException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class AlterarMercadoria extends JDialog {
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private final JPanel panelBtn;
	private final JPanel panelForm;

	private JButton btnX;
	private JButton btnSalvarEdicao;

	private JLabel lblCodMercadoria;
	private JLabel lblCodBarras;
	private JLabel lblDescricao;
	private JLabel lblUltimoUsuEdit;
	private JLabel lblUsurioDeCadastro;
	private JLabel lblData;
	private JLabel lblUnd;
	private JLabel lblPrecoVenda;
	private JLabel lblDataDeEdicao;
	private JLabel lblLucro;
	private JLabel lblAlterarMercadorias;
	private JLabel lblPrecoCompra;
	private JLabel lblDataDeCadastro;
	private JLabel lblDataCad;
	private JLabel lblEstoque;

	private JTextField txtCodMercadoria;
	private JTextField txtDescricao;
	private JTextField txtPrecoCompra;
	private JNumberFormatField txtPrecoVenda;
	private JNumberField txtCodbarras;
	private JComboBox<String> cbUnd;

	private int codUsu;

	private Date data = new Date();
	private SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

	private ControlerMercadoria controlerMercadoria;
	private Mercadoria mercadoria;

	public AlterarMercadoria(long codBarras) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
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

		lblAlterarMercadorias = new JLabel("alterar mercadorias");
		lblAlterarMercadorias.setBounds(280, 0, 273, 35);
		contentPanel.add(lblAlterarMercadorias);
		lblAlterarMercadorias.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarMercadorias.setForeground(Color.WHITE);
		lblAlterarMercadorias.setFont(new Font("Gtek Technology", Font.PLAIN, 17));

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBounds(10, 34, 814, 113);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		String dataPrincipal = formatador.format(data);

		btnSalvarEdicao = new JButton("");
		btnSalvarEdicao.setIcon(new ImageIcon(AlterarMercadoria.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvarEdicao.setBackground(new Color(0, 255, 0));
		btnSalvarEdicao.setBorder(null);
		btnSalvarEdicao.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvarEdicao);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 814, 421);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		lblCodMercadoria = new JLabel("Código Mercadoria:");
		lblCodMercadoria.setBounds(10, 14, 114, 14);
		panelForm.add(lblCodMercadoria);

		txtCodMercadoria = new JTextField();
		txtCodMercadoria.setText("0");
		txtCodMercadoria.setEnabled(false);
		txtCodMercadoria.setColumns(10);
		txtCodMercadoria.setBounds(134, 11, 58, 20);
		panelForm.add(txtCodMercadoria);

		lblCodBarras = new JLabel("Cod. de barras");
		lblCodBarras.setBounds(263, 14, 88, 14);
		panelForm.add(lblCodBarras);

		txtCodbarras = new JNumberField();
		txtCodbarras.setColumns(10);
		txtCodbarras.setBounds(361, 11, 156, 20);
		panelForm.add(txtCodbarras);

		lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 64, 63, 14);
		panelForm.add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(83, 61, 434, 20);
		panelForm.add(txtDescricao);

		lblUnd = new JLabel("Unidade:");
		lblUnd.setBounds(593, 64, 58, 14);
		panelForm.add(lblUnd);

		cbUnd = new JComboBox<String>();
		cbUnd.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "CJ", "CM", "CX", "EMBAL", "FARDO", "FOLHA", "FRASCO", "UNID", "RESMA" }));
		cbUnd.setBounds(661, 61, 88, 20);
		panelForm.add(cbUnd);

		lblPrecoVenda = new JLabel("Preço venda (R$):");
		lblPrecoVenda.setBounds(361, 120, 100, 14);
		panelForm.add(lblPrecoVenda);

		txtPrecoVenda = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoVenda.setLimit(6);
		txtPrecoVenda.setColumns(10);
		txtPrecoVenda.setBounds(471, 117, 86, 20);
		panelForm.add(txtPrecoVenda);

		lblPrecoCompra = new JLabel("Preço Compra (R$):");
		lblPrecoCompra.setBounds(10, 120, 114, 14);
		panelForm.add(lblPrecoCompra);

		txtPrecoCompra = new JNumberFormatField(new DecimalFormat("0.00"));
		txtPrecoCompra.setBounds(134, 117, 86, 20);
		panelForm.add(txtPrecoCompra);
		txtPrecoCompra.setColumns(10);

		lblLucro = new JLabel();
		lblLucro.setBounds(361, 158, 144, 14);
		panelForm.add(lblLucro);

		lblDataDeEdicao = new JLabel("Data de edição:");
		lblDataDeEdicao.setBounds(10, 396, 100, 14);
		panelForm.add(lblDataDeEdicao);

		lblData = new JLabel(dataPrincipal);
		lblData.setBounds(118, 396, 108, 14);
		panelForm.add(lblData);

		lblUsurioDeCadastro = new JLabel("Usuário de cadastro:");
		lblUsurioDeCadastro.setBounds(555, 363, 249, 14);
		panelForm.add(lblUsurioDeCadastro);

		lblUltimoUsuEdit = new JLabel("Último usuário a editar:");
		lblUltimoUsuEdit.setBounds(555, 396, 249, 14);
		panelForm.add(lblUltimoUsuEdit);

		lblDataDeCadastro = new JLabel("Data de cadastro:");
		lblDataDeCadastro.setBounds(10, 371, 100, 14);
		panelForm.add(lblDataDeCadastro);

		lblDataCad = new JLabel();
		lblDataCad.setBounds(118, 371, 102, 14);
		panelForm.add(lblDataCad);

		lblEstoque = new JLabel("Estoque: ");
		lblEstoque.setBounds(661, 120, 143, 14);
		panelForm.add(lblEstoque);

		// Trecho de consulta do cadastro da mercadoria para edição da mesma
		try {
			controlerMercadoria = new ControlerMercadoria();
			mercadoria = controlerMercadoria.consultaMercadoria(codBarras);
			txtCodMercadoria.setText(String.valueOf(mercadoria.getId()));
			txtCodbarras.setText(String.valueOf(mercadoria.getCodBarras()));
			txtDescricao.setText(mercadoria.getMercadoria());
			txtPrecoVenda.setText(String.format("%.2f", mercadoria.getPrecoVenda()));
			cbUnd.setSelectedItem(mercadoria.getUnd());
			lblDataCad.setText(mercadoria.getDataCadastro());
			txtPrecoCompra.setText(String.format("%.2f", mercadoria.getPrecoCompra()));
			lblEstoque.setText(String.format("%.2f", mercadoria.getEstoque()));

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
		}
		catch (MercadoriaNaoEncontradaException e1) {
			
		}
		// Fim do trecho de consulta do cadastro da mercadoria para edição da
		// mesma

//		btnSalvarEdicao.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					if (txtCodbarras.getText().equals("") && txtDescricao.getText().equals("")) {
//						JOptionPane.showMessageDialog(null,
//								"Os campos 'Cod. de Barras' e 'Descrição' não podem estar em branco", "Erro",
//								JOptionPane.ERROR_MESSAGE);
//					} else {
//						Mercadoria mercadoria = new Mercadoria(Integer.parseInt(txtCodMercadoria.getText()),
//								Long.parseLong(txtCodbarras.getText()), txtDescricao.getText(),
//								Float.parseFloat(txtPrecoVenda.getText().replace(",", ".")), lblData.getText(), codUsu,
//								cbUnd.getSelectedItem().toString(),
//								Float.parseFloat(txtPrecoCompra.getText().replace(",", ".")), 0, dataPrincipal, 0);
//						controlerMercadoria.alterarMercadoria(mercadoria);
//						JOptionPane.showMessageDialog(null, "Mercadoria alterada com sucesso", "Sucesso!",
//								JOptionPane.INFORMATION_MESSAGE);
//						dispose();
//					}
//				} catch (SQLException e1) {
//					JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
//							JOptionPane.ERROR_MESSAGE);
//				} catch (CodBarrasJaCadastradoException e) {
//					JOptionPane.showMessageDialog(null, "Aviso: " + e.getMessage(), "Aviso interno",
//							JOptionPane.WARNING_MESSAGE);
//				}
//			}
//		});
	}
}