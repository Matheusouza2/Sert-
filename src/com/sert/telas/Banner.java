package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.ControlerDuplicata;
import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.PermissoesStatic;
import com.sert.controler.PropriedadesControler;
import com.sert.controler.UsuLogado;
import com.sert.dao.ConexaoDao;
import com.sert.entidades.DuplicataCliente;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.server.LiberacaoData;
import com.sert.server.VerifyUpdate;

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
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JProgressBar;

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
	private JLabel lblProgressBar;

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

		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Banner.class.getResource("/com/sert/img/SertSoftBanner.png")));
		label.setBounds(0, 0, 681, 267);
		contentPane.add(label);

		lblProgressBar = new JLabel("Aguarde...");
		lblProgressBar.setForeground(new Color(0, 0, 255));
		lblProgressBar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		lblProgressBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgressBar.setBounds(10, 278, 650, 41);
		contentPane.add(lblProgressBar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(512, 273, 40, 40);
		contentPane.add(lblNewLabel);

		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 128));
		progressBar.setIndeterminate(true);
		progressBar.setBorder(null);

		progressBar.setBackground(new Color(240, 240, 240));
		progressBar.setBounds(0, 323, 670, 7);
		contentPane.add(progressBar);
		
		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				lblProgressBar.setText("Testando conexão do banco de dados...");
				ConexaoDao.testarCon();
				lblProgressBar.setText("Carregando Permissões...");
				PermissoesStatic.preenchePermissoes(UsuLogado.getId());
				lblProgressBar.setText("Sincronizando com o servidor...");
				if (!LiberacaoData.isDataOk()) {
					JOptionPane.showMessageDialog(null, "Verifique a data e a hora do seu computador");
					dispose();
				}
				lblProgressBar.setText("Carregando mercadorias...");
				preencherListas();
				lblProgressBar.setText("Carregando clientes...");
				verContasAReceber();
				lblProgressBar.setText("Verificando atualizações...");
				if(VerifyUpdate.up(new PropriedadesControler().getVersion())) {
					String[] comandoComParametros = new String[] { "cmd.exe", "/C", "start", "cmd.exe",  
					        "/C",  
					        "java",  
					        "-jar",  
					        "C:/Program Files/Sert+/update/SertUpdate.jar"
					    };  
					    Runtime.getRuntime().exec(comandoComParametros);  
					System.exit(0);
				}
				return null;
			}

			protected void done() {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				dispose();
				new Inicio().setVisible(true);
			}
		}.execute();
	}

	private void preencherListas()
			throws ClassNotFoundException, NenhumaMercadoriaCadastradaException, SQLException, IOException {
		lblProgressBar.setText("Atualizando Mercadorias...");
		new ControlerVenda().atualizarCadastros();
	}

	private void verContasAReceber() throws ClassNotFoundException, SQLException, IOException, ParseException {
		ControlerDuplicata dup = new ControlerDuplicata();
		List<DuplicataCliente> listDup = dup.listDuplicata();
		lblProgressBar.setText("Verificando situações...");
		for (DuplicataCliente duplicata : listDup) {
			DateFormat df = new SimpleDateFormat("ddMMyyyy");
			// Converte para Date
			Date diaDup = df.parse(duplicata.getDataVencimento().replace("/", "").replace(" ", "").replace(":", ""));
			Date hoje = df.parse(JDateField.getDate().replace("/", "").replace(" ", "").replace(":", ""));
			if (diaDup.before(hoje) && duplicata.getSituacao().equals("A vencer")) {
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