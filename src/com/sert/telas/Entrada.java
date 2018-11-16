package com.sert.telas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class Entrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBanner;
	private JPanel panelLogin;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblVersao;
	
	private JTextField txtUser;
	private JPasswordField pwdUsu;
	
	private JButton btnEntrar;
	private JButton btnX;
	
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblLogo;
	private JLabel lblLogo2;
		

	public Entrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelBanner = new JPanel();
		panelBanner.setBackground(new Color(0, 0, 128));
		panelBanner.setBounds(0, 0, 210, 400);
		
		contentPane.add(panelBanner);
		panelBanner.setLayout(null);
		
		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/Logo.png")));
		lblLogo.setBounds(10, 11, 190, 168);
		panelBanner.add(lblLogo);
		
		lblLogo2 = new JLabel("");
		lblLogo2.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/Logo2.png")));
		lblLogo2.setBounds(10, 279, 190, 75);
		panelBanner.add(lblLogo2);
		
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 0));
		panelLogin.setBounds(210, 0, 340, 400);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(107, 160, 146, 28);
		panelLogin.add(txtUser);
		txtUser.setColumns(10);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(157, 135, 46, 14);
		panelLogin.add(lblUsuario);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSenha.setBounds(157, 225, 46, 14);
		panelLogin.add(lblSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBounds(136, 325, 89, 23);
		panelLogin.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entrada();				
			}
		});
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(107, 188, 146, 2);
		panelLogin.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(new Color(0, 0, 205));
		separator_1.setBounds(107, 278, 146, 2);
		panelLogin.add(separator_1);
		
		pwdUsu = new JPasswordField();
		pwdUsu.setBounds(107, 250, 146, 28);
		panelLogin.add(pwdUsu);
		
		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(284, 11, 46, 23);
		btnX.setBackground(Color.RED);
		panelLogin.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		lblVersao = new JLabel("Versão 1.0.0");
		lblVersao.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVersao.setBounds(268, 375, 62, 14);
		panelLogin.add(lblVersao);
	}
	
	private void entrada(){
		String login = txtUser.getText();
		String senha = pwdUsu.getPassword().toString();
		
		if(senha.equals("s3rtc0nfig")) {
			new ConfigEmpresa().setVisible(true);
		}else {
			
		}
	}
}