package com.sert.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaLivroCaixa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtData;
	private JTextField txtHistorico;
	private JTextField txtValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaLivroCaixa dialog = new TelaLivroCaixa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaLivroCaixa() {
		setBounds(100, 100, 980, 580);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		panel.setBounds(10, 72, 944, 389);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 924, 342);
		panel.add(scrollPane);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 11, 46, 14);
		panel.add(lblData);
		
		JLabel lblHistorico = new JLabel("Historico:");
		lblHistorico.setBounds(188, 11, 46, 14);
		panel.add(lblHistorico);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(610, 11, 46, 14);
		panel.add(lblValor);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(66, 8, 112, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		
		txtHistorico = new JTextField();
		txtHistorico.setBounds(244, 8, 345, 20);
		panel.add(txtHistorico);
		txtHistorico.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setBounds(651, 8, 100, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(771, 11, 46, 14);
		panel.add(lblTipo);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(827, 8, 28, 20);
		panel.add(cbTipo);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(865, 7, 64, 23);
		panel.add(btnAdd);
		
		JLabel lblLivroCaixa = new JLabel("livro caixa");
		lblLivroCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivroCaixa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLivroCaixa.setForeground(new Color(255, 255, 255));
		lblLivroCaixa.setBounds(204, 11, 555, 51);
		contentPanel.add(lblLivroCaixa);
	}
}
