package com.sert.opcoes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sert.controler.PermissoesStatic;
import com.sert.telas.CadCliente;
import com.sert.telas.ContasAReceber;
import com.sert.telas.ListarCliente;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.5
 * 
 */
public class OpcClientes extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JButton btnCadastrarNovoCliente;
	private JButton btnListaDeClientes;
	private JButton btnX;
	private JButton btnDebitoClientes;
	private JLabel lblBackground;

	public OpcClientes() {
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

		btnCadastrarNovoCliente = new JButton();
		btnCadastrarNovoCliente
				.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/btnNovoCliente.png")));
		btnCadastrarNovoCliente.setBounds(34, 84, 200, 36);
		btnCadastrarNovoCliente.setOpaque(false);
		btnCadastrarNovoCliente.setBorderPainted(false);
		btnCadastrarNovoCliente.setContentAreaFilled(false);
		getContentPane().add(btnCadastrarNovoCliente);
		btnCadastrarNovoCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadCliente(0, 0).setVisible(true);
			}
		});

		btnListaDeClientes = new JButton();
		btnListaDeClientes.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/btnListarClientes.png")));
		btnListaDeClientes.setOpaque(false);
		btnListaDeClientes.setBorderPainted(false);
		btnListaDeClientes.setContentAreaFilled(false);
		btnListaDeClientes.setBounds(34, 131, 200, 36);
		getContentPane().add(btnListaDeClientes);
		btnListaDeClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarCliente(0).setVisible(true);
			}
		});

		btnDebitoClientes = new JButton("");
		btnDebitoClientes.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/btnContasAReceber.png")));
		btnDebitoClientes.setBackground(new Color(255, 255, 0));
		btnDebitoClientes.setOpaque(false);
		btnDebitoClientes.setBorderPainted(false);
		btnDebitoClientes.setContentAreaFilled(false);
		btnDebitoClientes.setForeground(new Color(0, 0, 0));
		btnDebitoClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnDebitoClientes.setBounds(34, 178, 200, 36);
		getContentPane().add(btnDebitoClientes);
		btnDebitoClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ContasAReceber().setVisible(true);
			}
		});

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/backOpc.png")));
		lblBackground.setBounds(0, 0, 268, 300);
		contentPane.add(lblBackground);
		getPermissoes();
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isCadCliente()) {
			btnCadastrarNovoCliente.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isListCliente()) {
			btnListaDeClientes.setEnabled(false);
		}
	}
}