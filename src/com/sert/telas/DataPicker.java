package com.sert.telas;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import com.sert.controler.JDateField;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.relatorios.RelatorioCaixa;
import com.sert.relatorios.RelatorioCompras;

import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.ImageIcon;

public class DataPicker extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDtInicial;
	private JTextField txtDtFinal;
	private JLabel lblDataInicial;
	private JLabel lblDataFinal;
	private JButton btnOk;
	private JButton btnX;
	public static int CAIXA = 0;
	public static int COMPRAS = 1;
	private JLabel lblBack;

	public DataPicker(int tela) {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);

		listen();
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		txtDtInicial = new JDocumentFormatedField().getData();
		txtDtInicial.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtDtInicial.setBounds(10, 63, 248, 33);
		txtDtInicial.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(txtDtInicial);
		txtDtInicial.setColumns(10);
		txtDtInicial.setText(JDateField.getDate());

		lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setForeground(new Color(255, 255, 0));
		lblDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataInicial.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDataInicial.setBounds(10, 38, 248, 14);
		contentPanel.add(lblDataInicial);

		lblDataFinal = new JLabel("Data final");
		lblDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataFinal.setForeground(Color.YELLOW);
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDataFinal.setBounds(10, 150, 248, 14);
		contentPanel.add(lblDataFinal);

		txtDtFinal = new JDocumentFormatedField().getData();
		txtDtFinal.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtDtFinal.setColumns(10);
		txtDtFinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtDtFinal.setBounds(10, 175, 248, 33);
		contentPanel.add(txtDtFinal);
		txtDtFinal.setText(JDateField.getDate());

		btnOk = new JButton("OK");
		btnOk.setBounds(91, 266, 89, 23);
		contentPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int data1 = Integer.parseInt(txtDtInicial.getText().replace("/", ""));
				int data2 = Integer.parseInt(txtDtFinal.getText().replace("/", ""));
				if (data1 > data2) {
					JOptionPane.showMessageDialog(null, "A data inicial não pode ser maior que a data final");
				} else {
					if (tela == COMPRAS) {
						new RelatorioCompras(txtDtInicial.getText(), txtDtFinal.getText()).setVisible(true);
					} else if (tela == CAIXA) {
						new RelatorioCaixa(txtDtInicial.getText(), txtDtFinal.getText()).setVisible(true);
					}
				}
			}
		});

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(DataPicker.class.getResource("/com/sert/img/btnX.png")));
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setOpaque(false);
		btnX.setBounds(234, 2, 30, 30);
		contentPanel.add(btnX);
		
		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(DataPicker.class.getResource("/com/sert/img/backOpc.png")));
		lblBack.setBounds(0, 0, 268, 300);
		contentPanel.add(lblBack);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void listen() {

		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESC");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}
}