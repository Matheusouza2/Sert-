package com.sert.telas;

import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.sert.controler.JDateField;
import com.sert.controler.PermissoesStatic;
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
 * @version 1.1.0
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

	private JLabel lblLogo;
	private JLabel lblBemVindoa;
	private JLabel lblBanner;
	private JLabel lblDataHora;
	private JLabel lblLegenda;
	private JButton btnFuncionario;
	private JButton btnOrcamento;
	private JLabel lblBackground;

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
		panelButtons.setOpaque(false);
		panelButtons.setBounds(10, 11, 1344, 113);
		contentPane.add(panelButtons);
		panelButtons.setLayout(null);

		btnClientes = new JButton();
		btnClientes.setOpaque(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnCliente.png")));
		btnClientes.setBounds(7, 11, 89, 91);
		panelButtons.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OpcClientes().setVisible(true);
			}
		});

		btnFuncionario = new JButton();
		btnFuncionario.setBounds(106, 11, 89, 91);
		btnFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ListarUsu().setVisible(true);
			}
		});
		btnFuncionario.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnFuncionarios.png")));
		btnFuncionario.setOpaque(false);
		btnFuncionario.setContentAreaFilled(false);
		btnFuncionario.setBorderPainted(false);
		panelButtons.add(btnFuncionario);

		btnProdutos = new JButton();
		btnProdutos.setOpaque(false);
		btnProdutos.setContentAreaFilled(false);
		btnProdutos.setBorderPainted(false);
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OpcProdutos().setVisible(true);
			}
		});
		btnProdutos.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/produtosBtn.png")));
		btnProdutos.setBounds(205, 11, 89, 91);
		panelButtons.add(btnProdutos);

		btnOrcamento = new JButton();
		btnOrcamento.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnOrcamento.png")));
		btnOrcamento.setOpaque(false);
		btnOrcamento.setContentAreaFilled(false);
		btnOrcamento.setBorderPainted(false);
		btnOrcamento.setBounds(304, 11, 89, 91);
		panelButtons.add(btnOrcamento);
		btnOrcamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaOrcamento().setVisible(true);
			}
		});

		btnFiscal = new JButton();
		btnFiscal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OpcFiscal().setVisible(true);
			}
		});
		btnFiscal.setOpaque(false);
		btnFiscal.setContentAreaFilled(false);
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

		btnVendas.setOpaque(false);
		btnVendas.setContentAreaFilled(false);
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
		btnDashboard.setOpaque(false);
		btnDashboard.setContentAreaFilled(false);
		btnDashboard.setBorderPainted(false);
		btnDashboard.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/dashboardBtn.png")));
		btnDashboard.setBounds(601, 11, 89, 91);
		panelButtons.add(btnDashboard);

		btnFerramentas = new JButton();
		btnFerramentas.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnFerramentas.png")));
		btnFerramentas.setBorderPainted(false);
		btnFerramentas.setOpaque(false);
		btnFerramentas.setContentAreaFilled(false);
		btnFerramentas.setBounds(700, 11, 89, 91);
		panelButtons.add(btnFerramentas);
		btnFerramentas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new OpcFerramentas().setVisible(true);
			}
		});

		btnSair = new JButton();
		btnSair.setOpaque(false);
		btnSair.setContentAreaFilled(false);
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
		lblDataHora.setForeground(Color.BLACK);
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

		lblBackground = new JLabel("");
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/backBtnPanel.png")));
		lblBackground.setBounds(0, 0, 1344, 113);
		panelButtons.add(lblBackground);

		panelUsados = new JPanel();
		panelUsados.setBounds(10, 135, 1344, 622);
		contentPane.add(panelUsados);
		panelUsados.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/Logo.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 554, 163, 30);
		panelUsados.add(lblLogo);

		btnTrocaUsu = new JButton();
		btnTrocaUsu.setOpaque(false);
		btnTrocaUsu.setContentAreaFilled(false);
		btnTrocaUsu.setBorderPainted(false);
		btnTrocaUsu.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/btnTrocaUsuario.png")));
		btnTrocaUsu.setBounds(1081, 530, 123, 87);
		panelUsados.add(btnTrocaUsu);
		btnTrocaUsu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Entrada().setVisible(true);
				dispose();
			}
		});

		lblBemVindoa = new JLabel("Bem vindo (a): " + UsuLogado.getNome());
		lblBemVindoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindoa.setForeground(Color.WHITE);
		lblBemVindoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBemVindoa.setBounds(451, 102, 442, 20);
		panelUsados.add(lblBemVindoa);

		lblLegenda = new JLabel("F1 - Empresa | F2 - Pesquisar Preço");
		lblLegenda.setBounds(10, 11, 564, 14);
		panelUsados.add(lblLegenda);

		lblBanner = new JLabel("");
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setIcon(new ImageIcon("C:\\Sert+\\img\\BannerInicio.png"));
		lblBanner.setBounds(0, 0, 1344, 590);
		panelUsados.add(lblBanner);

		JLabel lblContato = new JLabel("Contato: (87) 9 8875-4442");
		lblContato.setForeground(new Color(0, 0, 128));
		lblContato.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblContato.setHorizontalAlignment(SwingConstants.CENTER);
		lblContato.setBounds(20, 584, 163, 27);
		panelUsados.add(lblContato);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Inicio.class.getResource("/com/sert/img/backEntrada.png")));
		lblNewLabel.setBounds(0, 0, 1344, 622);
		panelUsados.add(lblNewLabel);

		permissoes();
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

	private void permissoes() {
		if (!PermissoesStatic.permissoesFunc.isLancarVendas()) {
			btnVendas.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isListFunc()) {
			btnFuncionario.setEnabled(false);
		}
	}
}