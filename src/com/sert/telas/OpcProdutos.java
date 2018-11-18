package com.sert.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class OpcProdutos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCadastrarNovoCliente ;
	private JButton btnNovoFornecedor;
	private JButton btnListaDeClientes;
	private JButton btnX;
	
	public OpcProdutos() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
						
		btnCadastrarNovoCliente = new JButton("nova mercadoria");
		btnCadastrarNovoCliente.setBackground(new Color(255, 255, 0));
		btnCadastrarNovoCliente.setForeground(new Color(0, 0, 0));
		btnCadastrarNovoCliente.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastrarNovoCliente.setBounds(10, 107, 248, 21);
		getContentPane().add(btnCadastrarNovoCliente);
		btnCadastrarNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadMercadoria(0,0).setVisible(true);				
			}
		});
		
		btnNovoFornecedor = new JButton("extrato mercadoria");
		btnNovoFornecedor.setBackground(new Color(255, 255, 0));
		btnNovoFornecedor.setForeground(new Color(0, 0, 0));
		btnNovoFornecedor.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnNovoFornecedor.setBounds(10, 171, 248, 21);
		getContentPane().add(btnNovoFornecedor);
		
		btnListaDeClientes = new JButton("listar mercadorias");
		btnListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarMercadorias().setVisible(true);
			}
		});
		btnListaDeClientes.setBackground(new Color(255, 255, 0));
		btnListaDeClientes.setForeground(new Color(0, 0, 0));
		btnListaDeClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnListaDeClientes.setBounds(10, 139, 248, 21);
		getContentPane().add(btnListaDeClientes);
		
		btnX = new JButton("X");
		btnX.setBackground(Color.RED);
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(212, 11, 46, 23);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}