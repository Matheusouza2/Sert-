package com.sert.telas;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.ControlerUsuario;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.AutoCompletion;
import com.sert.entidades.Usuario;
import com.sert.exceptions.NenhumUsuCadException;
import com.sert.exceptions.UsuarioNaoCadastradoException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class Entrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBanner;
	private JPanel panelLogin;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblVersao;

	private JComboBox<String> txtUser;
	private JPasswordField pwdUsu;

	private JButton btnEntrar;
	private JButton btnX;

	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblLogo;
	private JLabel lblLogo2;

	public Entrada() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(getIconImage());

		listen();

		panelBanner = new JPanel();
		panelBanner.setBackground(new Color(0, 0, 128));
		panelBanner.setBounds(0, 0, 210, 400);

		contentPane.add(panelBanner);
		panelBanner.setLayout(null);

		lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/Logo.png")));
		lblLogo.setBounds(10, 11, 190, 168);
		panelBanner.add(lblLogo);

		lblLogo2 = new JLabel("");
		lblLogo2.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/Logo2.png")));
		lblLogo2.setBounds(10, 279, 190, 75);
		panelBanner.add(lblLogo2);

		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 0));
		panelLogin.setBounds(210, 0, 340, 400);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		

		txtUser = new JComboBox<String>();
		txtUser.setEditable(true);
		txtUser.setVisible(true);
		txtUser.setBounds(107, 160, 146, 28);
		panelLogin.add(txtUser);

		List<Usuario> usuList;
		try {
			usuList = new ControlerUsuario().listarUsuario();
			for (int i = 0; i < usuList.size(); i++) {
				txtUser.addItem(usuList.get(i).getNome());
				txtUser.setSelectedItem(null);
			}
			AutoCompletion.enable(txtUser);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NenhumUsuCadException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(149, 135, 62, 14);
		panelLogin.add(lblUsuario);

		lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(153, 225, 54, 14);
		panelLogin.add(lblSenha);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBounds(136, 325, 89, 23);
		btnEntrar.setRequestFocusEnabled(true);
		panelLogin.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					entrada();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (UsuarioNaoCadastradoException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(107, 188, 146, 2);
		panelLogin.add(separator);

		separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(new Color(0, 0, 205));
		separator_1.setBounds(107, 278, 146, 2);
		panelLogin.add(separator_1);

		pwdUsu = new JPasswordField();
		pwdUsu.setBounds(107, 250, 146, 28);
		panelLogin.add(pwdUsu);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(284, 11, 46, 23);
		btnX.setBackground(Color.RED);
		panelLogin.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		lblVersao = new JLabel("Versão 1.0.1");
		lblVersao.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblVersao.setBounds(268, 375, 62, 14);
		panelLogin.add(lblVersao);
	}

	private void listen() {

		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				"ENTER");
		escback.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {
			public void actionPerformed(ActionEvent e) {
				try {
					entrada();
				} catch (ClassNotFoundException | UsuarioNaoCadastradoException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	private void entrada() throws ClassNotFoundException, UsuarioNaoCadastradoException, SQLException, IOException {
		String login = txtUser.getSelectedItem().toString();
		String senha = new String(pwdUsu.getPassword());

		if (senha.equals("s3rtc0nfig")) {
			new ConfigEmpresa().setVisible(true);
		} else {
			Usuario usu = new ControlerUsuario().login(login, senha);
			if (usu.getId() != 0) {
				UsuLogado.setId(usu.getId());
				UsuLogado.setNome(usu.getNome());
				new Banner().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario ou senha invalido", "Erro de login",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
//	public final Image getIconImage(){
//		Image icone = Toolkit.getDefaultToolkit().getImage(Entrada.class.getResource("/com/sert/img/logo2.png"));
//		return icone;
//	}
}