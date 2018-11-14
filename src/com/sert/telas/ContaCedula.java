package com.sert.telas;

import javax.swing.JPanel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class ContaCedula extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt100;
	private JTextField txt50;
	private JTextField txt20;
	private JTextField txt10;
	private JTextField txt5;
	private JTextField txt2;
	private JLabel lblTotal;
	private int total;
	private int r100;
	private int r50;
	private int r20;
	private int r10;
	private int r5;
	private int r2;

	public ContaCedula() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnX.setBackground(Color.RED);
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(404, 0, 46, 23);
		contentPane.add(btnX);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel.setBounds(10, 34, 430, 255);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txt100 = new JTextField();
		txt100.setBounds(22, 63, 46, 20);
		panel.add(txt100);
		txt100.setColumns(10);
		
		txt50 = new JTextField();
		txt50.setBounds(90, 63, 46, 20);
		panel.add(txt50);
		txt50.setColumns(10);
		
		txt20 = new JTextField();
		txt20.setBounds(158, 63, 46, 20);
		panel.add(txt20);
		txt20.setColumns(10);
		
		txt10 = new JTextField();
		txt10.setBounds(226, 63, 46, 20);
		panel.add(txt10);
		txt10.setColumns(10);
		
		txt5 = new JTextField();
		txt5.setBounds(294, 63, 46, 20);
		panel.add(txt5);
		txt5.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setBounds(362, 63, 46, 20);
		panel.add(txt2);
		txt2.setColumns(10);
		
		JLabel lbl100 = new JLabel("R$ 100");
		lbl100.setBounds(22, 48, 46, 14);
		panel.add(lbl100);
		lbl100.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl50 = new JLabel("R$ 50");
		lbl50.setBounds(90, 48, 46, 14);
		panel.add(lbl50);
		lbl50.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl20 = new JLabel("R$ 20");
		lbl20.setBounds(158, 48, 46, 14);
		panel.add(lbl20);
		lbl20.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl10 = new JLabel("R$ 10");
		lbl10.setBounds(226, 48, 46, 14);
		panel.add(lbl10);
		lbl10.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl5 = new JLabel("R$ 5");
		lbl5.setBounds(294, 48, 46, 14);
		panel.add(lbl5);
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl2 = new JLabel("R$ 2");
		lbl2.setBounds(362, 48, 46, 14);
		panel.add(lbl2);
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(0, 206, 430, 38);
		panel.add(lblTotal);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt2.getText();
				if(!val.equals("")){
					r2 = 2 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r2 = 0;
					atualizaTotal();
				}
				atualizaTotal();
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		txt5.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt5.getText();
				if(!val.equals("")){
					r5 = 5 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r5 = 0;
					atualizaTotal();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		txt10.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt10.getText();
				if(!val.equals("")){
					r10 = 10 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r10 = 0;
					atualizaTotal();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		txt20.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt20.getText();
				if(!val.equals("")){
					r20 = 20 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r20 = 0;
					atualizaTotal();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		txt50.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt50.getText();
				if(!val.equals("")){
					r50 = 50 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r50 = 0;
					atualizaTotal();
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
		txt100.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				String val = txt100.getText();
				if(!val.equals("")){
					r100 = 100 * Integer.parseInt(val);
					atualizaTotal();
				}else{
					r100 = 0;
					atualizaTotal();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {}
		});
	}
	
	public void atualizaTotal (){
		total = 0;
		total = r2 + r5 + r10 + r20 + r50 + r100;
		lblTotal.setText("Total: "+total);
	}
}
