package com.sert.telas;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PontoDeVendaFuncoes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PontoDeVendaFuncoes dialog = new PontoDeVendaFuncoes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PontoDeVendaFuncoes() {
		setBounds(100, 100, 325, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnX = new JButton("X");
		btnX.setBackground(new Color(255, 0, 0));
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBounds(280, 5, 39, 23);
		contentPanel.add(btnX);
		
		panel = new JPanel();
		panel.setBounds(10, 39, 305, 250);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnAtualizarCadastros = new JButton("Atualizar Cadastros");
		btnAtualizarCadastros.setBounds(10, 11, 134, 23);
		panel.add(btnAtualizarCadastros);
		
		JButton btnRelatorioDeCaixa = new JButton("Relatorio de Caixa");
		btnRelatorioDeCaixa.setBounds(10, 45, 134, 23);
		panel.add(btnRelatorioDeCaixa);
		
		JButton btnListarMercadorias = new JButton("Listar Mercadorias");
		btnListarMercadorias.setBounds(10, 79, 134, 23);
		panel.add(btnListarMercadorias);
		
		JLabel lblNewLabel = new JLabel("funcoes caixa");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 1, 233, 30);
		contentPanel.add(lblNewLabel);
	}
}
