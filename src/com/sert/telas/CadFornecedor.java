package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.sert.controler.JDateField;

import java.awt.Font;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class CadFornecedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodCliente;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtRg;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtContato;
	private JTextField txtUsuInclude;

	public CadFornecedor() {
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panel.setBounds(10, 34, 814, 113);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBounds(10, 11, 89, 91);
		panel.add(btnSalvar);
		
		JButton button_1 = new JButton("");
		button_1.setBounds(109, 11, 89, 91);
		panel.add(button_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panel_1.setBounds(10, 158, 814, 421);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtCodCliente = new JTextField();
		txtCodCliente.setEnabled(false);
		txtCodCliente.setBounds(135, 11, 58, 20);
		panel_1.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		JLabel lblCodigoCliente = new JLabel("Codigo Fornecedor:");
		lblCodigoCliente.setBounds(10, 14, 115, 14);
		panel_1.add(lblCodigoCliente);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 62, 38, 14);
		panel_1.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(58, 59, 458, 20);
		panel_1.add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JTextField();
		txtCpf.setBounds(296, 11, 115, 20);
		panel_1.add(txtCpf);
		txtCpf.setColumns(10);
		
		JLabel lblCpf = new JLabel("CNPJ:");
		lblCpf.setBounds(240, 14, 46, 14);
		panel_1.add(lblCpf);
		
		JLabel lblRg = new JLabel("IE:");
		lblRg.setBounds(495, 14, 38, 14);
		panel_1.add(lblRg);
		
		txtRg = new JTextField();
		txtRg.setBounds(543, 11, 104, 20);
		panel_1.add(txtRg);
		txtRg.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(549, 62, 30, 14);
		panel_1.add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setBounds(589, 59, 115, 20);
		panel_1.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(10, 115, 58, 14);
		panel_1.add(lblEndereo);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(78, 112, 434, 20);
		panel_1.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 157, 38, 14);
		panel_1.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(58, 154, 223, 20);
		panel_1.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(304, 157, 46, 14);
		panel_1.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(360, 154, 115, 20);
		panel_1.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(520, 157, 46, 14);
		panel_1.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(576, 154, 58, 20);
		panel_1.add(txtEstado);
		txtEstado.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(549, 115, 58, 14);
		panel_1.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(617, 112, 66, 20);
		panel_1.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(10, 212, 85, 14);
		panel_1.add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setBounds(105, 209, 115, 20);
		panel_1.add(txtComplemento);
		txtComplemento.setColumns(10);
		
		JLabel lblContato = new JLabel("Contato:");
		lblContato.setBounds(266, 212, 61, 14);
		panel_1.add(lblContato);
		
		txtContato = new JTextField();
		txtContato.setBounds(337, 209, 115, 20);
		panel_1.add(txtContato);
		txtContato.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(322, 297, 58, 14);
		panel_1.add(lblUsuario);
		
		JLabel lblObservaes = new JLabel("Observações:");
		lblObservaes.setBounds(10, 297, 95, 14);
		panel_1.add(lblObservaes);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 322, 794, 88);
		panel_1.add(textArea);
		
		txtUsuInclude = new JTextField();
		txtUsuInclude.setEditable(false);
		txtUsuInclude.setBounds(389, 294, 129, 20);
		panel_1.add(txtUsuInclude);
		txtUsuInclude.setColumns(10);
		
		JLabel lblDataDeIncluso = new JLabel("Data de inclusão: "+ JDateField.getDate());
		lblDataDeIncluso.setBounds(589, 297, 215, 14);
		panel_1.add(lblDataDeIncluso);
		
		JButton btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		
		JLabel lblCadastroDeFornecedores = new JLabel("cadastro de fornecedores");
		lblCadastroDeFornecedores.setForeground(new Color(255, 255, 255));
		lblCadastroDeFornecedores.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeFornecedores.setBounds(246, 0, 342, 36);
		contentPane.add(lblCadastroDeFornecedores);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
