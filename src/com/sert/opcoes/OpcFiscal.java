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

import com.sert.telas.AjusteEstoque;
import com.sert.telas.CadNotas;
import com.sert.telas.ListarNFeEntrada;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class OpcFiscal extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JButton btnCadastroNfe;
	private JButton btnVizuNota;
	private JButton btnMovEstoque;
	private JButton btnX;

	public OpcFiscal() {
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

		btnCadastroNfe = new JButton("entrada de nota");
		btnCadastroNfe.setBackground(new Color(255, 255, 0));
		btnCadastroNfe.setForeground(new Color(0, 0, 0));
		btnCadastroNfe.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnCadastroNfe.setBounds(10, 107, 248, 21);
		getContentPane().add(btnCadastroNfe);
		btnCadastroNfe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadNotas(0).setVisible(true);
			}
		});

		btnVizuNota = new JButton("vizualizar notas");
		btnVizuNota.setBackground(new Color(255, 255, 0));
		btnVizuNota.setForeground(new Color(0, 0, 0));
		btnVizuNota.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnVizuNota.setBounds(10, 171, 248, 21);
		getContentPane().add(btnVizuNota);
		btnVizuNota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarNFeEntrada().setVisible(true);
			}
		});

		btnMovEstoque = new JButton("mov estoque");
		btnMovEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AjusteEstoque().setVisible(true);
			}
		});
		btnMovEstoque.setBackground(new Color(255, 255, 0));
		btnMovEstoque.setForeground(new Color(0, 0, 0));
		btnMovEstoque.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnMovEstoque.setBounds(10, 139, 248, 21);
		getContentPane().add(btnMovEstoque);

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