package com.sert.telas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class MovimentoCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup bg = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;

	public MovimentoCaixa() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 295);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panel.setBounds(10, 34, 397, 250);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnSuprimento = new JRadioButton("Suprimento");
		rdbtnSuprimento.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSuprimento.setBounds(60, 49, 109, 23);
		panel.add(rdbtnSuprimento);
		
		JRadioButton rdbtnSangria = new JRadioButton("Sangria");
		rdbtnSangria.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSangria.setBounds(183, 49, 109, 23);
		panel.add(rdbtnSangria);
		
		bg.add(rdbtnSangria);
		bg.add(rdbtnSuprimento);
		
		textField = new JTextField();
		textField.setBounds(57, 109, 282, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 181, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblMotivo = new JLabel("Motivo");
		lblMotivo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotivo.setBounds(164, 93, 68, 14);
		panel.add(lblMotivo);
		
		JLabel lblValor = new JLabel("Valor (R$)");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(155, 166, 86, 14);
		panel.add(lblValor);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(144, 216, 109, 23);
		panel.add(btnConfirmar);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnX.setBounds(371, 0, 46, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		
	}
}
