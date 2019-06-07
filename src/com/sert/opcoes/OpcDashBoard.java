package com.sert.opcoes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import com.sert.controler.PermissoesStatic;
import com.sert.relatorios.RelatorioEstoque;
import com.sert.relatorios.RelatorioVendas;
import com.sert.telas.DataPicker;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class OpcDashBoard extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JButton btnEstoque;
	private JButton btnRelatorioCompras;
	private JButton btnRelatorioVendas;
	private JButton btnX;
	private JButton btnRelaCaixa;
	private JButton btnAuditoria;
	private JButton btnDebitoClientes;
	private JButton btnLivroCaixa;

	public OpcDashBoard() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listen();

		btnX = new JButton("X");
		btnX.setBackground(Color.RED);
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(212, 11, 46, 23);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnEstoque = new JButton("estoque");
		btnEstoque.setBackground(new Color(255, 255, 0));
		btnEstoque.setForeground(new Color(0, 0, 0));
		btnEstoque.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnEstoque.setBounds(10, 60, 248, 21);
		getContentPane().add(btnEstoque);
		btnEstoque.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RelatorioEstoque().setVisible(true);
			}
		});

		btnRelatorioVendas = new JButton("relatorio de vendas");
		btnRelatorioVendas.setBackground(new Color(255, 255, 0));
		btnRelatorioVendas.setForeground(new Color(0, 0, 0));
		btnRelatorioVendas.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnRelatorioVendas.setBounds(10, 92, 248, 21);
		getContentPane().add(btnRelatorioVendas);
		btnRelatorioVendas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RelatorioVendas().setVisible(true);
			}

		});

		btnRelaCaixa = new JButton("relatorio de caixa");
		btnRelaCaixa.setForeground(Color.BLACK);
		btnRelaCaixa.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnRelaCaixa.setBackground(Color.YELLOW);
		btnRelaCaixa.setBounds(10, 124, 248, 21);
		contentPane.add(btnRelaCaixa);
		btnRelaCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DataPicker(DataPicker.VENDAS).setVisible(true);
			}
		});

		btnRelatorioCompras = new JButton("relatorio de compras");
		btnRelatorioCompras.setBackground(new Color(255, 255, 0));
		btnRelatorioCompras.setForeground(new Color(0, 0, 0));
		btnRelatorioCompras.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		btnRelatorioCompras.setBounds(10, 156, 248, 21);
		getContentPane().add(btnRelatorioCompras);
		btnRelatorioCompras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DataPicker(DataPicker.COMPRAS).setVisible(true);
			}
		});

		/**
		 * btnLivroCaixa = new JButton("livro caixa"); btnLivroCaixa.setBackground(new
		 * Color(255, 255, 0)); btnLivroCaixa.setForeground(new Color(0, 0, 0));
		 * btnLivroCaixa.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		 * btnLivroCaixa.setBounds(10, 188, 248, 21);
		 * getContentPane().add(btnLivroCaixa); btnLivroCaixa.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * 
		 *           } });
		 * 
		 *           btnAuditoria = new JButton("auditoria");
		 *           btnAuditoria.setBackground(new Color(255, 255, 0));
		 *           btnAuditoria.setForeground(new Color(0, 0, 0));
		 *           btnAuditoria.setFont(new Font("Gtek Technology", Font.PLAIN, 14));
		 *           btnAuditoria.setBounds(10, 220, 248, 21);
		 *           getContentPane().add(btnAuditoria);
		 * 
		 *           btnDebitoClientes = new JButton("contas a receber");
		 *           btnDebitoClientes.setBackground(new Color(255, 255, 0));
		 *           btnDebitoClientes.setForeground(new Color(0, 0, 0));
		 *           btnDebitoClientes.setFont(new Font("Gtek Technology", Font.PLAIN,
		 *           14)); btnDebitoClientes.setBounds(10, 252, 248, 21);
		 *           getContentPane().add(btnDebitoClientes);
		 */
		getPermissoes();
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

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isDashEstoque()) {
			btnEstoque.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isDashVenda()) {
			btnRelatorioVendas.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isDashCaixa()) {
			btnRelaCaixa.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isDashCompra()) {
			btnRelatorioCompras.setEnabled(false);
		}
	}
}