package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.border.LineBorder;

import com.sert.controler.ControlerEmpresa;
import com.sert.controler.PropriedadesControler;
import com.sert.dao.ConexaoDao;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.entidades.Empresa;

public class ConfigEmpresa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtHost;
	private JTextField txtPort;
	private JButton btnX;
	private JLabel lblHost;
	private JLabel lblPorta;
	private JButton btnTestarConexo;
	private JLabel lblCnpj;
	private JTextField txtEmpresa;
	private JTextField txtCnpj;
	private JLabel lblEmpresa;
	private JLabel lblUsuario;
	private JTextField txtusuario;
	private JPasswordField txtSenha;
	private JLabel lblSenha;

	public ConfigEmpresa() {
		setUndecorated(true);
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(Color.YELLOW, 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnX = new JButton("X");
		btnX.setBounds(394, 7, 46, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblHost = new JLabel("Host: ");
		lblHost.setForeground(new Color(255, 255, 255));
		lblHost.setBounds(40, 59, 41, 14);
		contentPanel.add(lblHost);

		txtHost = new JTextField();
		txtHost.setBounds(91, 56, 181, 20);
		contentPanel.add(txtHost);
		txtHost.setColumns(10);

		lblPorta = new JLabel("Porta:");
		lblPorta.setForeground(new Color(255, 255, 255));
		lblPorta.setBounds(282, 56, 46, 14);
		contentPanel.add(lblPorta);

		txtPort = new JTextField();
		txtPort.setBounds(338, 53, 56, 20);
		contentPanel.add(txtPort);
		txtPort.setColumns(10);

		btnTestarConexo = new JButton("testar conexao");
		btnTestarConexo.setFont(new Font("Gtek Technology", Font.PLAIN, 11));
		btnTestarConexo.setBackground(Color.YELLOW);
		btnTestarConexo.setBorder(new LineBorder(Color.GREEN, 2, true));
		btnTestarConexo.setBounds(140, 259, 169, 30);
		contentPanel.add(btnTestarConexo);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setForeground(new Color(255, 255, 255));
		lblEmpresa.setBounds(26, 181, 56, 14);
		contentPanel.add(lblEmpresa);

		lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setForeground(new Color(255, 255, 255));
		lblCnpj.setBounds(26, 218, 46, 14);
		contentPanel.add(lblCnpj);

		txtEmpresa = new JTextField();
		txtEmpresa.setEditable(false);
		txtEmpresa.setBounds(82, 178, 358, 20);
		contentPanel.add(txtEmpresa);
		txtEmpresa.setColumns(10);

		txtCnpj = new JDocumentFormatedField().getCnpj();
		txtCnpj.setEditable(false);
		txtCnpj.setBounds(82, 215, 161, 20);
		contentPanel.add(txtCnpj);
		txtCnpj.setColumns(10);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(54, 100, 56, 14);
		contentPanel.add(lblUsuario);

		txtusuario = new JTextField();
		txtusuario.setBounds(120, 97, 86, 20);
		contentPanel.add(txtusuario);
		txtusuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(308, 97, 86, 20);
		contentPanel.add(txtSenha);

		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setBounds(255, 100, 46, 14);
		contentPanel.add(lblSenha);

		try {
			PropriedadesControler controler = new PropriedadesControler();
			txtHost.setText(controler.getHost());
			txtPort.setText(controler.getPort());
			txtusuario.setText(controler.getLogin());
			txtSenha.setText(controler.getPassword());

			ControlerEmpresa empresa = new ControlerEmpresa();
			List<Empresa> empresas = empresa.listEmpresa();
			for (int i = 0; i < empresas.size(); i++) {
				txtEmpresa.setText(empresas.get(i).getNome().trim());
				txtCnpj.setText(String.valueOf(empresas.get(i).getCnpj()));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		btnTestarConexo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PropriedadesControler controler = new PropriedadesControler();
					controler.setHost(txtHost.getText());
					controler.setPort(txtPort.getText());
					controler.setLogin(txtusuario.getText());
					controler.setPassword(new String(txtSenha.getPassword()));
					controler.setProp();
					if (ConexaoDao.testarCon()) {
						JOptionPane.showMessageDialog(null, "Conexão realizada com sucesso !");
						new Entrada().setVisible(true);
						dispose();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO DE BANCO DE DADOS",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}
