package com.sert.opcoes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.sert.telas.CadCliente;
import com.sert.telas.ContasAReceber;
import com.sert.telas.ListarCliente;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.5
 * 
 * */
public class OpcClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCadastrarNovoCliente ;
	private JButton btnListaDeClientes;
	private JButton btnX;
	private JButton btnDebitoClientes;
	
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
		contentPane.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
						
		btnCadastrarNovoCliente = new JButton("novo cliente");
		btnCadastrarNovoCliente.setBackground(new Color(255, 255, 0));
		btnCadastrarNovoCliente.setForeground(new Color(0, 0, 0));
		btnCadastrarNovoCliente.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastrarNovoCliente.setBounds(10, 107, 248, 21);
		getContentPane().add(btnCadastrarNovoCliente);
		btnCadastrarNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadCliente(0, 0).setVisible(true);				
			}
		});
				
		btnListaDeClientes = new JButton("listar clientes");
		btnListaDeClientes.setBackground(new Color(255, 255, 0));
		btnListaDeClientes.setForeground(new Color(0, 0, 0));
		btnListaDeClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnListaDeClientes.setBounds(10, 139, 248, 21);
		getContentPane().add(btnListaDeClientes);
		btnListaDeClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarCliente(0).setVisible(true);
			}
		});
						
		btnDebitoClientes = new JButton("contas a receber");
		btnDebitoClientes.setBackground(new Color(255, 255, 0));
		btnDebitoClientes.setForeground(new Color(0, 0, 0));
		btnDebitoClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnDebitoClientes.setBounds(10, 171, 248, 21);
		getContentPane().add(btnDebitoClientes);
		btnDebitoClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ContasAReceber().setVisible(true);				
			}
		});
		
//		JButton btnContasAPagar = new JButton("contas a pagar");
//		btnContasAPagar.setBackground(new Color(255, 255, 0));
//		btnContasAPagar.setForeground(new Color(0, 0, 0));
//		btnContasAPagar.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
//		btnContasAPagar.setBounds(10, 220, 248, 21);
//		getContentPane().add(btnContasAPagar);
		
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