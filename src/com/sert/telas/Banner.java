package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.ControlerDuplicata;
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.PermissoesStatic;
import com.sert.controler.UsuLogado;
import com.sert.dao.ConexaoDao;
import com.sert.entidades.DuplicataCliente;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.1.0
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
		setIconImage(getIconImage());

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
				progressBar.setString("Carregando mercadorias");
				preencherListas();
				progressBar.setString("Carregando clientes e seus debitos");
				verContasAReceber();
				PermissoesStatic.preenchePermissoes(UsuLogado.getId());
				progressBar.setString("Carregando modulos adicionais");
				
				return null;
			}

			protected void done() {
				new Inicio().setVisible(true);
				dispose();
			}
		}.execute();
	}
	
	private void preencherListas() throws ClassNotFoundException, NenhumaMercadoriaCadastradaException, SQLException, IOException {
		new ControlerVenda().atualizarCadastros();
		ControlerVenda.mercadorias = new ControlerMercadoria().listarMercadorias();
		ListarMercadorias.setPreencheTable(ControlerVenda.mercadorias);
		PesqMercVenda.setPreencheTable(ControlerVenda.mercadorias);
	}
	
	private void verContasAReceber() throws ClassNotFoundException, SQLException, IOException, ParseException {
		ControlerDuplicata dup = new ControlerDuplicata();
		List<DuplicataCliente> listDup = dup.listDuplicata();
		
		for(DuplicataCliente duplicata : listDup) {
			DateFormat df = new SimpleDateFormat("ddMMyyyy");
	        //Converte para Date
	        Date diaDup = df.parse(duplicata.getDataVencimento().replace("/", "").replace(" ", "").replace(":", ""));
	        Date hoje = df.parse(JDateField.getDate().replace("/", "").replace(" ", "").replace(":", ""));
			if(diaDup.before(hoje) && duplicata.getSituacao().equals("A vencer")){
				dup.alterarDuplicataStatus(duplicata.getId(), "Atrasada");
			}
		}
	}

	public Image getIconImage() {
		URL url = this.getClass().getResource("/com/sert/img/Logo2.png");
		Image icone = Toolkit.getDefaultToolkit().getImage(url);

		return icone;
	}
}