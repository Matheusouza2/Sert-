package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerUsuario;
import com.sert.controler.JDateField;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class CadCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JLabel lblCodigoCliente;
	private JLabel lblCpf;
	private JLabel lblRg;
	private JLabel lblNome;
	private JLabel lblEndereco;
	private JLabel lblCep;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JLabel lblContato;
	private JLabel lblComplemento;
	private JLabel lblObservacoes;
	private JLabel lblDataDeInclusao;
	private JLabel lblUsuario;
	private JLabel lblCadastroDeClientes;

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
	private JTextArea textArea;

	private JButton btnX;
	private JButton btnSalvar;

	public CadCliente() {
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

		String dataPrincipal = new JDateField().getDate();

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		JButton button_1 = new JButton("");
		button_1.setBounds(109, 11, 89, 91);
		panelBtn.add(button_1);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 814, 421);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		lblCodigoCliente = new JLabel("Codigo Cliente:");
		lblCodigoCliente.setBounds(10, 14, 97, 14);
		panelForm.add(lblCodigoCliente);

		txtCodCliente = new JTextField();
		txtCodCliente.setEnabled(false);
		txtCodCliente.setBounds(105, 11, 58, 20);
		panelForm.add(txtCodCliente);
		txtCodCliente.setColumns(10);

		try {
			int id;
			id = new ControlerUsuario().confereId();
			txtCodCliente.setText(String.valueOf(++id));
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

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(185, 17, 46, 14);
		panelForm.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(222, 14, 115, 20);
		panelForm.add(txtCpf);
		txtCpf.setColumns(10);

		lblRg = new JLabel("RG:");
		lblRg.setBounds(389, 17, 38, 14);
		panelForm.add(lblRg);

		txtRg = new JTextField();
		txtRg.setBounds(416, 14, 104, 20);
		panelForm.add(txtRg);
		txtRg.setColumns(10);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 62, 38, 14);
		panelForm.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(58, 59, 458, 20);
		panelForm.add(txtNome);
		txtNome.setColumns(10);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(549, 62, 30, 14);
		panelForm.add(lblCep);

		txtCep = new JTextField();
		txtCep.setBounds(589, 59, 115, 20);
		panelForm.add(txtCep);
		txtCep.setColumns(10);

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(10, 115, 58, 14);
		panelForm.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(78, 112, 434, 20);
		panelForm.add(txtEndereco);
		txtEndereco.setColumns(10);

		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(549, 115, 58, 14);
		panelForm.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(617, 112, 66, 20);
		panelForm.add(txtNumero);
		txtNumero.setColumns(10);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 157, 38, 14);
		panelForm.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(58, 154, 223, 20);
		panelForm.add(txtBairro);
		txtBairro.setColumns(10);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(304, 157, 46, 14);
		panelForm.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(360, 154, 115, 20);
		panelForm.add(txtCidade);
		txtCidade.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(520, 157, 46, 14);
		panelForm.add(lblEstado);

		txtEstado = new JTextField();
		txtEstado.setBounds(576, 154, 58, 20);
		panelForm.add(txtEstado);
		txtEstado.setColumns(10);

		lblComplemento = new JLabel("Complemento:");
		lblComplemento.setBounds(10, 212, 85, 14);
		panelForm.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(105, 209, 115, 20);
		panelForm.add(txtComplemento);
		txtComplemento.setColumns(10);

		lblContato = new JLabel("Contato:");
		lblContato.setBounds(266, 212, 61, 14);
		panelForm.add(lblContato);

		txtContato = new JTextField();
		txtContato.setBounds(337, 209, 115, 20);
		panelForm.add(txtContato);
		txtContato.setColumns(10);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(322, 297, 58, 14);
		panelForm.add(lblUsuario);

		lblDataDeInclusao = new JLabel("Data de inclusão: " + dataPrincipal);
		lblDataDeInclusao.setBounds(589, 297, 215, 14);
		panelForm.add(lblDataDeInclusao);

		lblObservacoes = new JLabel("Observa\u00E7\u00F5es:");
		lblObservacoes.setBounds(10, 297, 95, 14);
		panelForm.add(lblObservacoes);

		textArea = new JTextArea();
		textArea.setBounds(10, 322, 794, 88);
		panelForm.add(textArea);

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

		lblCadastroDeClientes = new JLabel("cadastro de clientes");
		lblCadastroDeClientes.setForeground(new Color(255, 255, 255));
		lblCadastroDeClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeClientes.setBounds(33, -4, 768, 30);
		contentPane.add(lblCadastroDeClientes);

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	}
}
