package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class Aguarde extends JProgressBar {

	public Aguarde() {
		setBackground(new Color(0, 0, 128));
		setForeground(new Color(255, 255, 0));
		setIndeterminate(true);
		setFont(new Font("Gtek Technology", Font.PLAIN, 16));
		setBounds(500, 150, 218, 47);
		setStringPainted(true);
		isIndeterminate();
		setString("aguarde");
		setForeground(new Color(255, 255, 0));
		
		//setUndecorated(true);
	
	
	}
}