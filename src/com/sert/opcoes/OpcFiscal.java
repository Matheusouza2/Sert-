package com.sert.opcoes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sert.controler.PermissoesStatic;
import com.sert.telas.AjusteEstoque;
import com.sert.telas.CadNotas;
import com.sert.telas.ListarNFeEntrada;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class OpcFiscal extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JButton btnCadastroNfe;
	private JButton btnVizuNota;
	private JButton btnMovEstoque;
	private JButton btnX;

	public OpcFiscal() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);

		contentPane = new JPanel();
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCadastroNfe = new JButton();
		btnCadastroNfe.setIcon(new ImageIcon(OpcFiscal.class.getResource("/com/sert/img/btnEntradaNota.png")));
		btnCadastroNfe.setBorderPainted(false);
		btnCadastroNfe.setOpaque(false);
		btnCadastroNfe.setContentAreaFilled(false);
		btnCadastroNfe.setBounds(34, 91, 200, 36);
		getContentPane().add(btnCadastroNfe);
		btnCadastroNfe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadNotas(0).setVisible(true);
			}
		});

		btnMovEstoque = new JButton();
		btnMovEstoque.setIcon(new ImageIcon(OpcFiscal.class.getResource("/com/sert/img/btnMovEstoque.png")));
		btnMovEstoque.setOpaque(false);
		btnMovEstoque.setBorderPainted(false);
		btnMovEstoque.setContentAreaFilled(false);
		btnMovEstoque.setBounds(34, 185, 200, 36);
		getContentPane().add(btnMovEstoque);
		btnMovEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AjusteEstoque().setVisible(true);
			}
		});

		btnVizuNota = new JButton();
		btnVizuNota.setIcon(new ImageIcon(OpcFiscal.class.getResource("/com/sert/img/btnVizulizarNotas.png")));
		btnVizuNota.setBorderPainted(false);
		btnVizuNota.setContentAreaFilled(false);
		btnVizuNota.setOpaque(false);
		btnVizuNota.setBounds(34, 138, 200, 36);
		getContentPane().add(btnVizuNota);
		btnVizuNota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarNFeEntrada().setVisible(true);
			}
		});

		btnX = new JButton();
		btnX.setIcon(new ImageIcon(OpcFiscal.class.getResource("/com/sert/img/btnX.png")));
		btnX.setOpaque(false);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setBounds(239, 2, 30, 30);
		contentPane.add(btnX);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(OpcFiscal.class.getResource("/com/sert/img/backOpc.png")));
		lblBackground.setBounds(0, 0, 269, 300);
		contentPane.add(lblBackground);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		getPermissoes();
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isCadNota()) {
			btnCadastroNfe.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isListNota()) {
			btnVizuNota.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isMovEstoque()) {
			btnMovEstoque.setEnabled(false);
		}
	}
}