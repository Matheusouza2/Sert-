package com.sert.telas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.sert.controler.ControlerVenda;
import com.sert.controler.Log;
import com.sert.dao.ConexaoDao;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.CodBarrasJaCadastradoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		
		new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				progressBar.setString("Testando conexão do banco de dados");
				//new ConexaoDao().testeConexao();
				progressBar.setString("Carregando modulos do sistema");
				ListarMercadorias.setPreencheTable(new ControlerMercadoria().listarMercadorias());
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