package com.sert.opcoes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sert.controler.JDateField;
import com.sert.controler.PermissoesStatic;
import com.sert.editableFields.JDocumentFormatedField;

import java.awt.Font;
import javax.swing.JComboBox;

public class OpcDuplicata extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnX;
	private JLabel lblBackground;
	private JLabel lblCliente;
	private JComboBox<String> comboBox;
	private JLabel lblDataInicial;
	private JTextField txtDtInicial;
	private JLabel lblDataFinal;
	private JTextField txtDtFinal;
	private JButton btnConfirmar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OpcDuplicata dialog = new OpcDuplicata();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OpcDuplicata() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setOpaque(false);
		contentPane.setLayout(null);

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/btnX.png")));
		btnX.setOpaque(false);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setBounds(239, 2, 30, 30);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.YELLOW);
		lblCliente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblCliente.setBounds(10, 67, 56, 14);
		contentPane.add(lblCliente);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(64, 65, 194, 20);
		contentPane.add(comboBox);

		lblDataInicial = new JLabel("Data inicial");
		lblDataInicial.setForeground(new Color(255, 255, 0));
		lblDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataInicial.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblDataInicial.setBounds(10, 106, 248, 14);
		contentPane.add(lblDataInicial);

		txtDtInicial = new JTextField();
		txtDtInicial.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtDtInicial.setBounds(26, 131, 215, 30);
		txtDtInicial.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(txtDtInicial);
		txtDtInicial.setColumns(10);
		txtDtInicial.setText(JDateField.getDate());

		lblDataFinal = new JLabel("Data final");
		lblDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataFinal.setForeground(Color.YELLOW);
		lblDataFinal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblDataFinal.setBounds(10, 172, 248, 14);
		contentPane.add(lblDataFinal);

		txtDtFinal = new JTextField();
		txtDtFinal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		txtDtFinal.setColumns(10);
		txtDtFinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtDtFinal.setBounds(26, 197, 215, 30);
		contentPane.add(txtDtFinal);
		txtDtFinal.setText(JDateField.getDate());
		
		btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(OpcDuplicata.class.getResource("/com/sert/img/btnConfirmar.png")));
		btnConfirmar.setOpaque(false);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setBounds(93, 266, 89, 23);
		contentPane.add(btnConfirmar);

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/backOpc.png")));
		lblBackground.setBounds(0, 0, 268, 300);
		contentPane.add(lblBackground);
		getPermissoes();
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isCadCliente()) {

		}
		if (!PermissoesStatic.permissoesFunc.isListCliente()) {

		}
	}
}
