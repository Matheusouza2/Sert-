package com.sert.opcoes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.sert.telas.CadMercadoria;
import com.sert.telas.ListarMercadorias;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class OpcProdutos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnCadastrarNovaMercadoria ;
	private JButton btnNovoExtratoMerc;
	private JButton btnListaDeMerc;
	private JButton btnX;
	
	public OpcProdutos() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
						
		btnCadastrarNovaMercadoria = new JButton("nova mercadoria");
		btnCadastrarNovaMercadoria.setBackground(new Color(255, 255, 0));
		btnCadastrarNovaMercadoria.setForeground(new Color(0, 0, 0));
		btnCadastrarNovaMercadoria.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastrarNovaMercadoria.setBounds(10, 107, 248, 21);
		getContentPane().add(btnCadastrarNovaMercadoria);
		btnCadastrarNovaMercadoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadMercadoria(0,0).setVisible(true);				
			}
		});
		
		btnNovoExtratoMerc = new JButton("extrato mercadoria");
		btnNovoExtratoMerc.setBackground(new Color(255, 255, 0));
		btnNovoExtratoMerc.setForeground(new Color(0, 0, 0));
		btnNovoExtratoMerc.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnNovoExtratoMerc.setBounds(10, 171, 248, 21);
		getContentPane().add(btnNovoExtratoMerc);
		
		btnListaDeMerc = new JButton("listar mercadorias");
		btnListaDeMerc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarMercadorias().setVisible(true);
			}
		});
		btnListaDeMerc.setBackground(new Color(255, 255, 0));
		btnListaDeMerc.setForeground(new Color(0, 0, 0));
		btnListaDeMerc.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnListaDeMerc.setBounds(10, 139, 248, 21);
		getContentPane().add(btnListaDeMerc);
		
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