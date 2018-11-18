package com.sert.telas;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Aguarde extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Aguarde() {
		getContentPane().setBackground(Color.BLUE);
		setBounds(100, 100, 250, 100);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setModal(true);
		
		
		JLabel lblAguarde = new JLabel("aguarde");
		lblAguarde.setForeground(Color.WHITE);
		lblAguarde.setFont(new Font("Gtek Technology", Font.BOLD, 16));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(10, 11, 230, 78);
		getContentPane().add(lblAguarde);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public void finalizar(){
		dispose();
	}
}