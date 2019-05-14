package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Window;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class TelaInfo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblFiltrar;
	private JSeparator separator;

	public static void main(String[] args) {
		try {
			TelaInfo dialog = new TelaInfo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TelaInfo() {
		setBounds(100, 100, 792, 625);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 756, 455);
		contentPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 0));
		panel.setBounds(10, 11, 756, 99);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblFiltrar = new JLabel("filtrar");
		lblFiltrar.setBounds(565, 43, 46, 14);
		panel.add(lblFiltrar);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 0));
		textField.setBounds(621, 40, 86, 20);
		textField.setBorder(null);
		panel.add(textField);
		textField.setColumns(10);

		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setBounds(621, 71, 86, 2);
		panel.add(separator);
	}
}