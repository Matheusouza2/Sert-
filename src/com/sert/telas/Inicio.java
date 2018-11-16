package com.sert.telas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.JDateField;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class Inicio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelButtons;
	private JPanel panelUsados;

	private JButton btnProdutos;
	private JButton btnFiscal;
	private JButton btnClientes;
	private JButton btnVendas;
	private JButton btnDashboard;
	private JButton btnSair;
	private JButton btnTrocaUsu;
	private JButton btnFerramentas;

	private JLabel lblSert;
	private JLabel lblBemVindoa;
	private JLabel lblBanner;
	private JLabel lblDataHora;
	private JLabel lblLegenda;
	
	public Inicio() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Bem-vindo ao Sert+");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelButtons = new JPanel();
		panelButtons.setBackground(new Color(0, 0, 153));
		panelButtons.setBounds(10, 11, 1344, 113);
		contentPane.add(panelButtons);
		panelButtons.setLayout(null);

		btnClientes = new JButton();
		btnClientes.setBackground(new Color(255, 0, 102));
		btnClientes.setBorderPainted(false);
		btnClientes.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/usersBtn.png")));
		btnClientes.setBounds(7, 11, 89, 91);
		panelButtons.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OpcClientes().setVisible(true);
			}
		});

		btnProdutos = new JButton();
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OpcProdutos().setVisible(true);
			}
		});
		btnProdutos.setBackground(new Color(153, 0, 102));
		btnProdutos.setBorderPainted(false);
		btnProdutos.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/produtosBtn.png")));
		btnProdutos.setBounds(109, 11, 89, 91);
		panelButtons.add(btnProdutos);

		btnFiscal = new JButton();
		btnFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OpcFiscal().setVisible(true);
			}
		});
		btnFiscal.setBackground(new Color(255, 204, 0));
		btnFiscal.setBorderPainted(false);
		btnFiscal.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/fiscalBtn.png")));
		btnFiscal.setBounds(208, 11, 89, 91);
		panelButtons.add(btnFiscal);

		btnVendas = new JButton();
		btnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PontoDeVenda().setVisible(true);
			}
		});
		btnVendas.setBackground(new Color(255, 102, 0));
		btnVendas.setBorderPainted(false);
		btnVendas.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/vendasBtn.png")));
		btnVendas.setBounds(307, 11, 89, 91);
		panelButtons.add(btnVendas);

		btnDashboard = new JButton();
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OpcDashBoard().setVisible(true);
			}
		});
		btnDashboard.setBackground(new Color(51, 255, 0));
		btnDashboard.setBorderPainted(false);
		btnDashboard.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/dashboardBtn.png")));
		btnDashboard.setBounds(406, 11, 89, 91);
		panelButtons.add(btnDashboard);

		btnSair = new JButton();
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setBorderPainted(false);
		btnSair.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/sairBtn.png")));
		btnSair.setBounds(604, 11, 89, 91);
		panelButtons.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		lblDataHora = new JLabel();
		lblDataHora.setForeground(new Color(255, 255, 255));
		lblDataHora.setBounds(1153, 11, 181, 14);
		new Thread() {
			public void run() {
				while (true) {
					try {
						lblDataHora.setText(new JDateField().getDateHora());
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					lblDataHora.setText("");
				}
			};

		}.start();
		panelButtons.add(lblDataHora);

		btnFerramentas = new JButton();
		btnFerramentas.setBorderPainted(false);
		btnFerramentas.setBackground(new Color(175, 238, 238));
		btnFerramentas.setBounds(505, 11, 89, 91);
		panelButtons.add(btnFerramentas);
		btnFerramentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OpcFerramentas().setVisible(true);
			}
		});

		panelUsados = new JPanel();
		panelUsados.setBackground(new Color(41, 171, 226));
		panelUsados.setBounds(10, 135, 1344, 590);
		contentPane.add(panelUsados);
		panelUsados.setLayout(null);

		lblSert = new JLabel("sertsoft");
		lblSert.setForeground(new Color(240, 240, 240));
		lblSert.setFont(new Font("Gtek Technology", Font.PLAIN, 20));
		lblSert.setHorizontalAlignment(SwingConstants.CENTER);
		lblSert.setBounds(0, 533, 154, 57);
		panelUsados.add(lblSert);

		btnTrocaUsu = new JButton();
		btnTrocaUsu.setBackground(new Color(41, 171, 226));
		btnTrocaUsu.setBorderPainted(false);
		btnTrocaUsu.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnTrocaUsuario.png")));
		btnTrocaUsu.setBounds(1163, 509, 181, 81);
		panelUsados.add(btnTrocaUsu);
		btnTrocaUsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Entrada().setVisible(true);
				dispose();
			}
		});

		lblBemVindoa = new JLabel("Bem vindo (a):");
		lblBemVindoa.setForeground(Color.WHITE);
		lblBemVindoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBemVindoa.setBounds(1066, 484, 103, 14);
		panelUsados.add(lblBemVindoa);

		lblBanner = new JLabel("");
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/BannerInicio.png")));
		lblBanner.setBounds(0, 0, 1344, 590);
		panelUsados.add(lblBanner);
		
		lblLegenda = new JLabel("F2 - Pesquisa preço");
		lblLegenda.setBounds(10, 11, 258, 14);
		panelUsados.add(lblLegenda);
	}
}