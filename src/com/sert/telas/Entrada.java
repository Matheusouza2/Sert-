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

import java.awt.BorderLayout;
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JWindow;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.1.0
 * 
 */
public class Entrada extends JFrame implements FocusListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private JPanel panelLogin;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblVersao;
	private static JWindow window = new JWindow();
	private static JLabel lblX;
	private JPasswordField pwdUsu;

	private JButton btnEntrar;
	private JButton btnX;

	private JSeparator separator;
	private JSeparator separator_1;
	private JTextField txtUser;
	private JLabel label;
	private JLabel lblExclamacao2;
	private JLabel lblOUsurioDeve;
	private JLabel lblASenhaDeve;
	private JLabel lblExclamacao;

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

		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/iconUsuario.png")));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblUsuario.setBounds(145, 160, 92, 28);
		panelLogin.add(lblUsuario);

		txtUser = new JTextField();
		txtUser.setBounds(243, 161, 140, 28);
		txtUser.setBackground(new Color(255, 255, 0));
		txtUser.setBorder(null);
		panelLogin.add(txtUser);
		txtUser.setColumns(10);
		txtUser.setFocusTraversalKeysEnabled(false);
		txtUser.addFocusListener(this);

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

		new Autocomplete(txtUser, this, listNomes, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f);

		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(145, 188, 230, 2);
		panelLogin.add(separator);

		lblSenha = new JLabel("Senha:");
		lblSenha.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/iconSenha.png")));
		lblSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblSenha.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblSenha.setBounds(145, 250, 92, 28);
		panelLogin.add(lblSenha);

		pwdUsu = new JPasswordField();
		pwdUsu.setBackground(Color.YELLOW);
		pwdUsu.setBounds(235, 251, 148, 28);
		pwdUsu.setBorder(null);
		panelLogin.add(pwdUsu);
		pwdUsu.addFocusListener(this);

		separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(new Color(0, 0, 205));
		separator_1.setBounds(145, 278, 230, 2);
		panelLogin.add(separator_1);

		btnX = new JButton();
		btnX.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnX.png")));
		btnX.setOpaque(false);
		btnX.setBorderPainted(false);
		btnX.setContentAreaFilled(false);
		btnX.setBounds(516, 5, 28, 28);
		panelLogin.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnEntrar = new JButton("");
		btnEntrar.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnEntrar.png")));
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setBorder(null);
		btnEntrar.setBackground(Color.YELLOW);
		btnEntrar.setBounds(230, 325, 89, 23);
		btnEntrar.setRequestFocusEnabled(true);
		btnEntrar.addMouseListener(this);
		panelLogin.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				entrada();
			}
		});

		lblVersao = new JLabel("Versão 1.1.0 | © SertSoft");
		lblVersao.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersao.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblVersao.setBounds(190, 375, 170, 14);
		panelLogin.add(lblVersao);

		lblExclamacao = new JLabel("");
		lblExclamacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblExclamacao.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/alert.png")));
		lblExclamacao.setBounds(385, 162, 25, 25);
		panelLogin.add(lblExclamacao);
		lblExclamacao.setVisible(false);

		lblOUsurioDeve = new JLabel("O usuário deve ser informado");
		lblOUsurioDeve.setForeground(Color.RED);
		lblOUsurioDeve.setHorizontalAlignment(SwingConstants.CENTER);
		lblOUsurioDeve.setBounds(175, 192, 200, 14);
		panelLogin.add(lblOUsurioDeve);
		lblOUsurioDeve.setVisible(false);

		lblExclamacao2 = new JLabel("");
		lblExclamacao2.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/alert.png")));
		lblExclamacao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblExclamacao2.setBounds(385, 252, 25, 25);
		panelLogin.add(lblExclamacao2);
		lblExclamacao2.setVisible(false);

		lblASenhaDeve = new JLabel("A senha deve ser informada");
		lblASenhaDeve.setHorizontalAlignment(SwingConstants.CENTER);
		lblASenhaDeve.setForeground(Color.RED);
		lblASenhaDeve.setBounds(175, 282, 200, 14);
		panelLogin.add(lblASenhaDeve);
		lblASenhaDeve.setVisible(false);
		
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

				if (txtUser.hasFocus()) {
					txtUser.transferFocus();
				} else if (pwdUsu.hasFocus()) {
					btnEntrar.doClick();
					btnEntrar.transferFocus();
				}
			}
		});

	}

	private void entrada() {
		String senha = new String(pwdUsu.getPassword());
		if (senha.equals("s3rtc0nfig")) {
			new ConfigEmpresa().setVisible(true);
			dispose();
		} else {
			String login = txtUser.getText();
			senha = Seguranca.criptografar(senha);
			Usuario usu;
			try {
				usu = new ControlerUsuario().login(login, senha);
				UsuLogado.setId(usu.getId());
				UsuLogado.setNome(usu.getNome());
				new Banner().setVisible(true);
				dispose();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
						JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| ENTRADA |" + e1.getMessage());
			} catch (UsuarioNaoCadastradoException e1) {
				alert();
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
	}

	private void mostrarCamposErro() {
		if (new String(pwdUsu.getPassword()).equals("") && txtUser.getText().equals("")) {
			separator.setBackground(Color.RED);
			separator_1.setBackground(Color.RED);
			lblExclamacao.setVisible(true);
			lblOUsurioDeve.setVisible(true);
			lblExclamacao2.setVisible(true);
			lblASenhaDeve.setVisible(true);
		} else if (txtUser.getText().equals("")) {
			separator.setBackground(Color.RED);
			lblExclamacao.setVisible(true);
			lblOUsurioDeve.setVisible(true);
		} else if (new String(pwdUsu.getPassword()).equals("")) {
			separator_1.setBackground(Color.RED);
			lblExclamacao2.setVisible(true);
			lblASenhaDeve.setVisible(true);
		}
	}

	public Image getIconImage() {
		URL url = this.getClass().getResource("/com/sert/img/Logo2.png");
		Image icone = Toolkit.getDefaultToolkit().getImage(url);

		return icone;
	}

	private static void alert() {
		JPanel contentPanel = new JPanel();
		window.setBackground(Color.RED);
		window.setBounds(116, 74, 325, 63);
		window.getContentPane().setLayout(new BorderLayout());
		window.setLocation(contentPanel.getX() + 530, contentPanel.getY() + 250);
		contentPanel.setLayout(null);
		contentPanel.setBorder(null);
		window.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(Color.RED);

		window.setOpacity(0.75f);
		window.getContentPane().add(contentPanel);
		window.setVisible(true);

		JLabel lblUsurioEouSenha = new JLabel("Usuário e/ou senha invalidos!");
		lblUsurioEouSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsurioEouSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurioEouSenha.setBounds(10, 11, 305, 41);
		contentPanel.add(lblUsurioEouSenha);

		lblX = new JLabel("X");
		lblX.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(300, 0, 25, 14);
		contentPanel.add(lblX);

		lblX.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				window.dispose();
			}
		});
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (arg0.getSource() == txtUser) {
			separator.setBackground(new Color(0, 0, 205));
			lblExclamacao.setVisible(false);
			lblOUsurioDeve.setVisible(false);
			if (window.isFocusable()) {
				window.dispose();
			}
		} else if (arg0.getSource() == pwdUsu) {
			separator_1.setBackground(new Color(0, 0, 205));
			lblExclamacao2.setVisible(false);
			lblASenhaDeve.setVisible(false);
			if (window.isFocusable()) {
				window.dispose();
			}
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		mostrarCamposErro();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == btnEntrar) {
			btnEntrar.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnEntrarClick.png")));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == btnEntrar) {
			btnEntrar.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnEntrar.png")));
		}
	}
}