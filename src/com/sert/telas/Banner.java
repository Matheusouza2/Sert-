package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.dao.ConexaoDao;

import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class Banner extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Banner() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBounds(100, 100, 670, 330);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setForeground(new Color(0, 0, 255));
		progressBar.setBounds(10, 290, 650, 29);
		contentPane.add(progressBar);
		progressBar.setStringPainted(true);

		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Banner.class.getResource("/com/sert/img/SertSoftBanner.png")));
		label.setBounds(0, 0, 681, 297);
		contentPane.add(label);

		progressBar.setIndeterminate(true);

		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				progressBar.setString("Testando conexão do banco de dados");
				ConexaoDao.testarCon();
				progressBar.setString("Carregando modulos internos do sistema");
				ListarMercadorias.setPreencheTable(new ControlerMercadoria().listarMercadorias());
				PesqMercVenda.setPreencheTable(new ControlerMercadoria().listarMercadorias());
				new ControlerVenda().atualizarCadastros();

				return null;
			}

			protected void done() {
				new Inicio().setVisible(true);
				dispose();
			}
		}.execute();
	}
}