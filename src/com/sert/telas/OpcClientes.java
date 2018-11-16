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
public class OpcClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCadastrarNovoCliente ;
	private JButton btnNovoFornecedor;
	private JButton btnListaDeClientes;
	private JButton btnX;
	
	public OpcClientes() {
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
						
		btnCadastrarNovoCliente = new JButton("novo cliente");
		btnCadastrarNovoCliente.setBackground(new Color(255, 255, 0));
		btnCadastrarNovoCliente.setForeground(new Color(0, 0, 0));
		btnCadastrarNovoCliente.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastrarNovoCliente.setBounds(10, 60, 248, 21);
		getContentPane().add(btnCadastrarNovoCliente);
		btnCadastrarNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadCliente().setVisible(true);				
			}
		});
		
		btnNovoFornecedor = new JButton("novo  fornecedor");
		btnNovoFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadFornecedor().setVisible(true);
			}
		});
		btnNovoFornecedor.setBackground(new Color(255, 255, 0));
		btnNovoFornecedor.setForeground(new Color(0, 0, 0));
		btnNovoFornecedor.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnNovoFornecedor.setBounds(10, 124, 248, 21);
		getContentPane().add(btnNovoFornecedor);
		
		btnListaDeClientes = new JButton("listar clientes");
		btnListaDeClientes.setBackground(new Color(255, 255, 0));
		btnListaDeClientes.setForeground(new Color(0, 0, 0));
		btnListaDeClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnListaDeClientes.setBounds(10, 92, 248, 21);
		getContentPane().add(btnListaDeClientes);
		
		JButton btnListaDeFornecedores = new JButton("listar fornecedores");
		btnListaDeFornecedores.setBackground(new Color(255, 255, 0));
		btnListaDeFornecedores.setForeground(new Color(0, 0, 0));
		btnListaDeFornecedores.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnListaDeFornecedores.setBounds(10, 156, 248, 21);
		getContentPane().add(btnListaDeFornecedores);
		
		JButton btnDebitoClientes = new JButton("contas a receber");
		btnDebitoClientes.setBackground(new Color(255, 255, 0));
		btnDebitoClientes.setForeground(new Color(0, 0, 0));
		btnDebitoClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnDebitoClientes.setBounds(10, 188, 248, 21);
		getContentPane().add(btnDebitoClientes);
		
		JButton btnContasAPagar = new JButton("contas a pagar");
		btnContasAPagar.setBackground(new Color(255, 255, 0));
		btnContasAPagar.setForeground(new Color(0, 0, 0));
		btnContasAPagar.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnContasAPagar.setBounds(10, 220, 248, 21);
		getContentPane().add(btnContasAPagar);
		
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