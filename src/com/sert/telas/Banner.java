package com.sert.telas;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.Log;
import com.sert.dao.ConexaoDao;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

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
	private int opcao = 0;
	private Thread conThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Banner frame = new Banner();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Banner() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBounds(100, 100, 670, 330);
		contentPane = new JPanel();
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
		new Thread() {
			@Override
			public void run() {
				try {
					String[] frases = {"", "Aguarde...Conectando ao banco de dados", "Carregando Modulos do sistema",
							"Verificando conexões externas", "Abrindo Sistema", "Abrindo Sistema"};
					int con;
					while (true) {
						con = conexoes();
						progressBar.setValue(progressBar.getValue() + 25);
						if(!Banner.this.conThread.isAlive()){
							++opcao;
						}
						progressBar.setString(frases[con].toString());
						sleep(1000);
						
						if(progressBar.getValue() == 100){
							dispose();
							new Inicio().setVisible(true);
							break;
						}
					}
				} catch (InterruptedException e) {

					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					progressBar.setString("Driver do banco não encontrado no projeto");
				} catch (SQLException e) {
					progressBar.setString("Erro ao conectar com banco de dados, verifique o log");
					try {
						new Log().gravaLog(e.getMessage());
					} catch (IOException e1) {
						progressBar.setString("Verifique o caminho do log do sistema");
					}
				} catch (IOException e) {
					progressBar.setString("Verifique o caminho do log do sistema");
				} catch (NenhumaMercadoriaCadastradaException e) {
					try {
						new Log().gravaLog(e.getMessage());
					} catch (IOException e1) {
						progressBar.setString("Verifique o caminho do log do sistema");
					}
				} catch (CodBarrasJaCadastradoException e) {
					try {
						new Log().gravaLog(e.getMessage());
					} catch (IOException e1) {
						progressBar.setString("Verifique o caminho do log do sistema");
					}
				}
			}

		}.start();
	}

	public int conexoes()throws ClassNotFoundException, SQLException, IOException, NenhumaMercadoriaCadastradaException, CodBarrasJaCadastradoException {
		switch (opcao) {
		case 0:
			conThread = new Thread() {
				@Override
				public void run() {
					try {
						new ConexaoDao().testeConexao();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
		case 1:
			conThread = new Thread() {
				@Override
				public void run() {
					try {
						new ControlerMercadoria().listarMercadorias();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (NenhumaMercadoriaCadastradaException e) {
						e.printStackTrace();
					}
				}
			};
		case 2:
			++opcao;
			break;
		}
		return opcao;
	}
}