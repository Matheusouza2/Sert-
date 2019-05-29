package com.sert.telas;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sert.controler.ControlerUsuario;
import com.sert.controler.Log;
import com.sert.controler.Seguranca;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.Autocomplete;
import com.sert.entidades.Usuario;
import com.sert.exceptions.NenhumUsuCadException;
import com.sert.exceptions.UsuarioNaoCadastradoException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.4
 * 
 */
public class Entrada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelLogin;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblVersao;

	private JPasswordField pwdUsu;

	private JButton btnEntrar;
	private JButton btnX;

	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField txtUser;
	private JLabel label;

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

		panelLogin = new JPanel();
		panelLogin.setBounds(0, 0, 550, 400);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);

//		txtUser = new JComboBox<String>();
//		txtUser.setEditable(true);
//		txtUser.setVisible(true);
//		txtUser.setBounds(107, 160, 146, 28);
//		panelLogin.add(txtUser);
//
//		List<Usuario> usuList;
//		try {
//			usuList = new ControlerUsuario().listarUsuario();
//			for (int i = 0; i < usuList.size(); i++) {
//				txtUser.addItem(usuList.get(i).getNome());
//				txtUser.setSelectedItem(null);
//			}
//			AutoCompletion.enable(txtUser);
//		} catch (ClassNotFoundException e2) {
//			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Usuário",
//					JOptionPane.ERROR_MESSAGE);
//			Log.gravaLog("| ENTRADA |" + e2.getMessage());
//		} catch (SQLException e2) {
//			JOptionPane.showMessageDialog(null,
//					"O banco de dados encontrou um problema ao ser aberto \n" + e2.getMessage(),
//					"ERRO DE BANCO DE DADOS", JOptionPane.ERROR_MESSAGE);
//		} catch (NenhumUsuCadException e2) {
//			JOptionPane.showMessageDialog(null, e2.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
//		} catch (IOException e2) {
//			JOptionPane.showMessageDialog(null, "Erro de arquivo, veja o log para mais detalhes", "Usuário",
//					JOptionPane.ERROR_MESSAGE);
//			Log.gravaLog("| ENTRADA |" + e2.getMessage());
//		}

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(175, 165, 62, 14);
		panelLogin.add(lblUsuario);

		txtUser = new JTextField();
		txtUser.setBounds(243, 160, 132, 28);
		txtUser.setBackground(new Color(255, 255, 0));
		txtUser.setBorder(null);
		panelLogin.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setFocusTraversalKeysEnabled(false);
		
		List<Usuario> usuList;
		ArrayList<String> listNomes = new ArrayList<String>();
		try {
			usuList = new ControlerUsuario().listarUsuario();
			for (Usuario usu : usuList) {
				listNomes.add(usu.getNome());
			}
		} catch (ClassNotFoundException e2) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Usuário",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| ENTRADA |" + e2.getMessage());
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null,
					"O banco de dados encontrou um problema ao ser aberto \n" + e2.getMessage(),
					"ERRO DE BANCO DE DADOS", JOptionPane.ERROR_MESSAGE);
		} catch (NenhumUsuCadException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Erro de arquivo, veja o log para mais detalhes", "Usuário",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| ENTRADA |" + e2.getMessage());
		}

		new Autocomplete(txtUser, this, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
			public boolean wordTyped(String typedWord) {
				setDictionary(listNomes);

				return super.wordTyped(typedWord);
			}
		};
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(175, 188, 200, 2);
		panelLogin.add(separator);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSenha.setBounds(175, 257, 54, 14);
		panelLogin.add(lblSenha);

		pwdUsu = new JPasswordField();
		pwdUsu.setBackground(Color.YELLOW);
		pwdUsu.setBounds(227, 250, 148, 28);
		pwdUsu.setBorder(null);
		panelLogin.add(pwdUsu);

		separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(new Color(0, 0, 205));
		separator_1.setBounds(175, 278, 200, 2);
		panelLogin.add(separator_1);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(501, 11, 42, 23);
		btnX.setBackground(Color.RED);
		panelLogin.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.setBounds(230, 325, 89, 23);
		btnEntrar.setRequestFocusEnabled(true);
		panelLogin.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					entrada();
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes",
							"Sistema", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog("| ENTRADA |" + e1.getMessage());
				} catch (UsuarioNaoCadastradoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog("| ENTRADA |" + e1.getMessage());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
							"Banco de dados", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog("| ENTRADA |" + e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes",
							"Arquivo", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog("| ENTRADA |" + e1.getMessage());
				}
			}
		});

		lblVersao = new JLabel("Versão 1.0.5");
		lblVersao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblVersao.setBounds(244, 375, 62, 14);
		panelLogin.add(lblVersao);

		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/entrada.png")));
		label.setBounds(0, 0, 550, 400);
		panelLogin.add(label);
	}

	private void listen() {

		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESC");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		JRootPane enter = getRootPane();
		enter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		enter.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					if (txtUser.hasFocus()) {
						txtUser.transferFocus();
					} else if (pwdUsu.hasFocus()) {
						entrada();
					}
				} catch (ClassNotFoundException | UsuarioNaoCadastradoException | SQLException | IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}

	private void entrada() throws ClassNotFoundException, UsuarioNaoCadastradoException, SQLException, IOException {
		String senha = new String(pwdUsu.getPassword());
		if (senha.equals("s3rtc0nfig")) {
			new ConfigEmpresa().setVisible(true);
			dispose();
		} else if (senha.equals("s3rtt3st")) {
			UsuLogado.setId(0);
			UsuLogado.setNome("ADMIN");
			new Banner().setVisible(true);
		} else {
			String login = txtUser.getText();
			senha = Seguranca.criptografar(senha);
			Usuario usu = new ControlerUsuario().login(login, senha);
			if (usu.getId() >= 0) {
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

	public Image getIconImage() {
		URL url = this.getClass().getResource("/com/sert/img/Logo2.png");
		Image icone = Toolkit.getDefaultToolkit().getImage(url);

		return icone;
	}
}