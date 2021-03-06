package com.sert.opcoes;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.PermissoesStatic;
import com.sert.telas.AjusteEstoque;
import com.sert.telas.DataPicker;
import com.sert.telas.ListarMercadorias;
import com.sert.telas.PesqMercVenda;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpcPontoDeVenda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JPanel panel;
	private JButton btnAtualizarCadastros;
	private JButton btnRelatorioDeCaixa;
	private JButton btnListarMercadorias;
	private JLabel lblTitulo;
	private JPanel panelAguarde;
	private JLabel label;
	private JLabel lblAguarde;

	public OpcPontoDeVenda() {
		setBounds(100, 100, 325, 300);
		setUndecorated(true);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(OpcPontoDeVenda.class.getResource("/com/sert/img/btnX.png")));
		btnX.setBackground(new Color(0, 0, 128));
		btnX.setBorder(null);
		btnX.setFocusPainted(false);
		btnX.setForeground(new Color(255, 255, 255));
		btnX.setBounds(280, 5, 39, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelAguarde = new JPanel();
		panelAguarde.setBounds(51, 56, 223, 187);
		// contentPanel.add(panelAguarde);
		panelAguarde.setVisible(false);
		panelAguarde.setLayout(null);

		label = new JLabel("");
		label.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/load.gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 223, 187);
		panelAguarde.add(label);

		lblAguarde = new JLabel("Aguarde...");
		lblAguarde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(0, 162, 223, 25);
		panelAguarde.add(lblAguarde);

		panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 39, 305, 250);
		contentPanel.add(panel);
		panel.setLayout(null);

		btnAtualizarCadastros = new JButton("");
		btnAtualizarCadastros
				.setIcon(new ImageIcon(OpcPontoDeVenda.class.getResource("/com/sert/img/btnAtualizarCad.png")));
		btnAtualizarCadastros.setBounds(52, 60, 200, 36);
		btnAtualizarCadastros.setBorderPainted(false);
		btnAtualizarCadastros.setOpaque(false);
		btnAtualizarCadastros.setContentAreaFilled(false);
		panel.add(btnAtualizarCadastros);
		btnAtualizarCadastros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				panelAguarde.setVisible(true);
				new SwingWorker<Object, Object>() {
					@Override
					protected Object doInBackground() throws Exception {
						new ControlerVenda().atualizarCadastros();
						ControlerVenda.mercadorias = new ControlerMercadoria().listarMercadorias();
						ListarMercadorias.setPreencheTable(ControlerVenda.mercadorias);
						PesqMercVenda.setPreencheTable(ControlerVenda.mercadorias);
						return null;
					}

					protected void done() {
						contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						panelAguarde.setVisible(false);
						JOptionPane.showMessageDialog(null, "Ajuste realizado com sucesso", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					};

				}.execute();

			}
		});

		btnRelatorioDeCaixa = new JButton("");
		btnRelatorioDeCaixa
				.setIcon(new ImageIcon(OpcPontoDeVenda.class.getResource("/com/sert/img/btnRelatorioCaixa.png")));
		btnRelatorioDeCaixa.setBounds(52, 107, 200, 36);
		btnRelatorioDeCaixa.setBorderPainted(false);
		btnRelatorioDeCaixa.setOpaque(false);
		btnRelatorioDeCaixa.setContentAreaFilled(false);
		btnRelatorioDeCaixa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DataPicker(DataPicker.CAIXA).setVisible(true);
			}
		});
		panel.add(btnRelatorioDeCaixa);

		btnListarMercadorias = new JButton("");
		btnListarMercadorias
				.setIcon(new ImageIcon(OpcPontoDeVenda.class.getResource("/com/sert/img/btnListarMercadorias.png")));
		btnListarMercadorias.setBounds(52, 154, 200, 36);
		btnListarMercadorias.setBorderPainted(false);
		btnListarMercadorias.setOpaque(false);
		btnListarMercadorias.setContentAreaFilled(false);
		panel.add(btnListarMercadorias);
		btnListarMercadorias.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListarMercadorias().setVisible(true);
			}
		});

		lblTitulo = new JLabel("Funções caixa");
		lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBackground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(46, 1, 233, 30);
		contentPanel.add(lblTitulo);

		permissoes();
	}

	private void permissoes() {
		if (!PermissoesStatic.permissoesFunc.isDashCaixa()) {
			btnRelatorioDeCaixa.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isListProd()) {
			btnListarMercadorias.setEnabled(false);
		}
	}
}
