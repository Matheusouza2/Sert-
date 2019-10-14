package com.sert.opcoes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.sert.controler.PermissoesStatic;
import com.sert.telas.CadMercadoria;
import com.sert.telas.ListarMercadorias;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	private JButton btnExtratoMerc;
	private JButton btnListaDeMerc;
	private JButton btnX;
	private JLabel lblBack;
	
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
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnX = new JButton();
		btnX.setIcon(new ImageIcon(OpcProdutos.class.getResource("/com/sert/img/btnX.png")));
		btnX.setBorderPainted(false);
		btnX.setOpaque(false);
		btnX.setContentAreaFilled(false);
		btnX.setBounds(239, 2, 30, 30);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
						
		btnCadastrarNovaMercadoria = new JButton();
		btnCadastrarNovaMercadoria.setIcon(new ImageIcon(OpcProdutos.class.getResource("/com/sert/img/btnNovoProduto.png")));
		btnCadastrarNovaMercadoria.setBorderPainted(false);
		btnCadastrarNovaMercadoria.setOpaque(false);
		btnCadastrarNovaMercadoria.setContentAreaFilled(false);
		btnCadastrarNovaMercadoria.setBounds(34, 96, 200, 36);
		getContentPane().add(btnCadastrarNovaMercadoria);
		btnCadastrarNovaMercadoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadMercadoria(0,0).setVisible(true);				
			}
		});
		
		btnListaDeMerc = new JButton();
		btnListaDeMerc.setBorderPainted(false);
		btnListaDeMerc.setOpaque(false);
		btnListaDeMerc.setContentAreaFilled(false);
		btnListaDeMerc.setIcon(new ImageIcon(OpcProdutos.class.getResource("/com/sert/img/btnListarMercadorias.png")));
		btnListaDeMerc.setBounds(34, 143, 200, 36);
		getContentPane().add(btnListaDeMerc);
		btnListaDeMerc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarMercadorias().setVisible(true);
			}
		});
		
		btnExtratoMerc = new JButton();
		btnExtratoMerc.setIcon(new ImageIcon(OpcProdutos.class.getResource("/com/sert/img/btnExtratoProduto.png")));
		btnExtratoMerc.setBorderPainted(false);
		btnExtratoMerc.setOpaque(false);
		btnExtratoMerc.setContentAreaFilled(false);
		btnExtratoMerc.setBounds(34, 190, 200, 36);
		getContentPane().add(btnExtratoMerc);
		btnExtratoMerc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OpcExtratoMerc().setVisible(true);				
			}
		});
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(OpcProdutos.class.getResource("/com/sert/img/backOpc.png")));
		lblBack.setBounds(0, 0, 269, 300);
		contentPane.add(lblBack);
		
		getPermissoes();
	}
	
	private void getPermissoes() {
		if(!PermissoesStatic.permissoesFunc.isCadProd()) {
			btnCadastrarNovaMercadoria.setEnabled(false);
		}
		if(!PermissoesStatic.permissoesFunc.isListProd()) {
			btnListaDeMerc.setEnabled(false);
		}
	}
}