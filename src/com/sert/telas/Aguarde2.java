package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Aguarde2 extends JDialog {

	private static final long serialVersionUID = 1L;

	public Aguarde2() {
		setBounds(100, 100, 342, 101);
		setUndecorated(true);
		setModal(true);
		getContentPane().setLayout(null);
		
		JLabel lblAguarde = new JLabel("AGUARDE...");
		lblAguarde.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(10, 11, 322, 79);
		getContentPane().add(lblAguarde);
	}
}