package com.sert.telas;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.sert.controler.JDateField;
import com.sert.controler.UsuLogado;
import com.sert.opcoes.OpcClientes;
import com.sert.opcoes.OpcDashBoard;
import com.sert.opcoes.OpcFerramentas;
import com.sert.opcoes.OpcFiscal;
import com.sert.opcoes.OpcProdutos;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

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
	private JButton btnFuncionario;
	private JButton btnOrcamento;

	public Inicio() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setTitle("Bem-vindo ao Sert+");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(getIconImage());

		listen();

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
		btnProdutos.setBounds(205, 11, 89, 91);
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
		btnFiscal.setBounds(403, 11, 89, 91);
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
		btnVendas.setBounds(502, 11, 89, 91);
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
		btnDashboard.setBounds(601, 11, 89, 91);
		panelButtons.add(btnDashboard);

		btnSair = new JButton();
		btnSair.setBackground(new Color(255, 0, 0));
		btnSair.setBorderPainted(false);
		btnSair.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/sairBtn.png")));
		btnSair.setBounds(799, 11, 89, 91);
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
		btnFerramentas.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnFerramentas.png")));
		btnFerramentas.setBorderPainted(false);
		btnFerramentas.setBackground(new Color(175, 238, 238));
		btnFerramentas.setBounds(700, 11, 89, 91);
		panelButtons.add(btnFerramentas);
		btnFerramentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OpcFerramentas().setVisible(true);
			}
		});

		btnFuncionario = new JButton();
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarUsu().setVisible(true);
			}
		});
		btnFuncionario.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnFuncionarios.png")));
		btnFuncionario.setBorderPainted(false);
		btnFuncionario.setBackground(new Color(255, 245, 238));
		btnFuncionario.setBounds(106, 11, 89, 91);
		panelButtons.add(btnFuncionario);

		btnOrcamento = new JButton();
		btnOrcamento.setBorderPainted(false);
		btnOrcamento.setBackground(new Color(160, 82, 45));
		btnOrcamento.setBounds(304, 11, 89, 91);
		panelButtons.add(btnOrcamento);
		btnOrcamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaOrcamento().setVisible(true);
			}
		});

		panelUsados = new JPanel();
		panelUsados.setBackground(new Color(41, 171, 226));
		panelUsados.setBounds(10, 135, 1344, 622);
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
		btnTrocaUsu.setBounds(1081, 530, 231, 81);
		panelUsados.add(btnTrocaUsu);
		btnTrocaUsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Entrada().setVisible(true);
				dispose();
			}
		});

		lblBemVindoa = new JLabel("Bem vindo (a): " + UsuLogado.getNome());
		lblBemVindoa.setForeground(Color.WHITE);
		lblBemVindoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBemVindoa.setBounds(629, 552, 442, 14);
		panelUsados.add(lblBemVindoa);

		lblBanner = new JLabel("");
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setIcon(new ImageIcon("C:\\Sert+\\img\\BannerInicio.png"));
		lblBanner.setBounds(0, 0, 1344, 590);
		panelUsados.add(lblBanner);

		lblLegenda = new JLabel("F2 - Pesquisa preço");
		lblLegenda.setBounds(10, 11, 258, 14);
		panelUsados.add(lblLegenda);
	}

	private void listen() {
		JRootPane enterMerc = getRootPane();
		enterMerc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2");
		enterMerc.getRootPane().getActionMap().put("F2", new AbstractAction("F2") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				new PesqMercVenda().setVisible(true);
			}
		});
	}

	public Image getIconImage() {
		URL url = this.getClass().getResource("/com/sert/img/Logo2.png");
		Image icone = Toolkit.getDefaultToolkit().getImage(url);

		return icone;
	}
}