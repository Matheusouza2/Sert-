package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class Aguarde extends JDialog {

	public Aguarde() {
		setBackground(new Color(0, 0, 128));
		getContentPane().setLayout(null);
		setModal(true);		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 293, 78);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAitForIt = new JLabel("Wait for it");
		lblAitForIt.setBounds(69, 39, 169, 14);
		panel.add(lblAitForIt);
	}
}