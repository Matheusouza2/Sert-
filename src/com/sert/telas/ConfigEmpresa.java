package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class ConfigEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	public ConfigEmpresa() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblHost = new JLabel("Host: ");
		lblHost.setBounds(10, 11, 41, 14);
		contentPanel.add(lblHost);
		
		textField = new JTextField();
		textField.setBounds(61, 8, 181, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(252, 11, 46, 14);
		contentPanel.add(lblPorta);
		
		textField_1 = new JTextField();
		textField_1.setBounds(308, 8, 56, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnTestarConexo = new JButton("Testar Conexão");
		btnTestarConexo.setBackground(Color.YELLOW);
		btnTestarConexo.setBounds(157, 150, 114, 23);
		contentPanel.add(btnTestarConexo);
		
		JButton btnLiberarLicensa = new JButton("Liberar licensa");
		btnLiberarLicensa.setBackground(Color.GREEN);
		btnLiberarLicensa.setBounds(157, 228, 114, 23);
		contentPanel.add(btnLiberarLicensa);
	}
}
