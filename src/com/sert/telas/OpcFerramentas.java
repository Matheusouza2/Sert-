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
public class OpcFerramentas extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCadastrarUsu ;
	private JButton btnMovCaixa;
	private JButton btnContaCedula;
	private JButton btnX;
	
	public OpcFerramentas() {
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
						
		btnCadastrarUsu = new JButton("cadastrar usuario");
		btnCadastrarUsu.setBackground(new Color(255, 255, 0));
		btnCadastrarUsu.setForeground(new Color(0, 0, 0));
		btnCadastrarUsu.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastrarUsu.setBounds(10, 107, 248, 21);
		getContentPane().add(btnCadastrarUsu);
		btnCadastrarUsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		btnMovCaixa = new JButton("movimentar caixa");
		btnMovCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MovimentoCaixa().setVisible(true);
			}
		});
		btnMovCaixa.setBackground(new Color(255, 255, 0));
		btnMovCaixa.setForeground(new Color(0, 0, 0));
		btnMovCaixa.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnMovCaixa.setBounds(10, 171, 248, 21);
		getContentPane().add(btnMovCaixa);
		
		btnContaCedula = new JButton("conta cedula");
		btnContaCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContaCedula().setVisible(true);
			}
		});
		btnContaCedula.setBackground(new Color(255, 255, 0));
		btnContaCedula.setForeground(new Color(0, 0, 0));
		btnContaCedula.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnContaCedula.setBounds(10, 139, 248, 21);
		getContentPane().add(btnContaCedula);
		
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