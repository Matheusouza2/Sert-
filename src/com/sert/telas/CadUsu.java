package com.sert.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.sert.controler.ConsultaCep;
import com.sert.controler.ControlerUsuario;
import com.sert.controler.Log;
import com.sert.controler.Seguranca;
import com.sert.controler.ValidaCNP;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.entidades.Usuario;
import com.sert.exceptions.UsuarioJaCadastradoException;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;

public class CadUsu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JButton btnSalvar;
	private JTextField txtCpf;
	private JButton btnX;
	private JLabel lblTitlePage;
	private JPanel panelForm;
	private JPanel panelPermissoes;
	private JLabel lblCodigoCliente;
	private JTextField txtCodFunc;
	private JLabel lblCpf;
	private JLabel lblRg;
	private JTextField txtRg;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblCep;
	private JTextField txtCep;
	private JLabel lblEndereco;
	private JTextField txtEndereco;
	private JLabel lblNumero;
	private JTextField txtNumero;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblEstado;
	private JComboBox<String> txtEstado;
	private JLabel lblObservaes;
	private JScrollPane scrollPane;
	private JTextArea txtAreaObs;
	private JPasswordField pwdSenhaUsu;
	private int id;
	private JLabel lblSenha;
	private JTabbedPane tabbedPane;

	// Opção 0 vai ativar o cadastro enquanto a opção 1 vai ativar a alteração do
	// usuario

	public CadUsu(int opcao, int idUsu) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 618);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		try {
			id = new ControlerUsuario().confereId();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		}

		lblTitlePage = new JLabel("cadastro de funcionarios");
		lblTitlePage.setForeground(new Color(255, 255, 255));
		lblTitlePage.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitlePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitlePage.setBounds(33, -4, 768, 30);
		contentPane.add(lblTitlePage);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 158, 814, 449);
		contentPane.add(tabbedPane);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setLayout(null);

		panelPermissoes = new JPanel();
		panelPermissoes.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelPermissoes.setLayout(null);

		tabbedPane.addTab("Cadastro", null, panelForm, null);
		tabbedPane.addTab("Permissões", null, panelPermissoes, null);
		tabbedPane.setEnabledAt(0, true);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Funcionarios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(183, 147, 126, 126);
		panelPermissoes.add(panel);
		panel.setLayout(null);

		JCheckBox chckbxCadastrar = new JCheckBox("Cadastrar");
		chckbxCadastrar.setBounds(6, 45, 97, 23);
		panel.add(chckbxCadastrar);

		JCheckBox chckbxEditar = new JCheckBox("Editar");
		chckbxEditar.setBounds(6, 71, 97, 23);
		panel.add(chckbxEditar);

		JCheckBox chckbxExcluir = new JCheckBox("Excluir");
		chckbxExcluir.setBounds(6, 97, 97, 23);
		panel.add(chckbxExcluir);

		JCheckBox chckbxListar = new JCheckBox("Listar");
		chckbxListar.setBounds(6, 19, 97, 23);
		panel.add(chckbxListar);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(338, 147, 126, 126);
		panelPermissoes.add(panel_1);

		JCheckBox checkBox = new JCheckBox("Cadastrar");
		checkBox.setBounds(6, 19, 97, 23);
		panel_1.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("Editar");
		checkBox_1.setBounds(6, 71, 97, 23);
		panel_1.add(checkBox_1);

		JCheckBox checkBox_2 = new JCheckBox("Excluir");
		checkBox_2.setBounds(6, 97, 97, 23);
		panel_1.add(checkBox_2);

		JCheckBox checkBox_3 = new JCheckBox("Listar");
		checkBox_3.setBounds(6, 45, 97, 23);
		panel_1.add(checkBox_3);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Clientes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(28, 147, 126, 126);
		panelPermissoes.add(panel_2);

		JCheckBox checkBox_4 = new JCheckBox("Cadastrar");
		checkBox_4.setBounds(6, 19, 97, 23);
		panel_2.add(checkBox_4);

		JCheckBox checkBox_5 = new JCheckBox("Editar");
		checkBox_5.setBounds(6, 71, 97, 23);
		panel_2.add(checkBox_5);

		JCheckBox checkBox_6 = new JCheckBox("Excluir");
		checkBox_6.setBounds(6, 97, 97, 23);
		panel_2.add(checkBox_6);

		JCheckBox checkBox_7 = new JCheckBox("Listar");
		checkBox_7.setBounds(6, 45, 97, 23);
		panel_2.add(checkBox_7);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Fiscal",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(493, 147, 126, 126);
		panelPermissoes.add(panel_3);

		JCheckBox checkBox_8 = new JCheckBox("Cadastrar");
		checkBox_8.setBounds(6, 19, 97, 23);
		panel_3.add(checkBox_8);

		JCheckBox checkBox_9 = new JCheckBox("Editar");
		checkBox_9.setBounds(6, 71, 97, 23);
		panel_3.add(checkBox_9);

		JCheckBox checkBox_10 = new JCheckBox("Excluir");
		checkBox_10.setBounds(6, 97, 97, 23);
		panel_3.add(checkBox_10);

		JCheckBox checkBox_11 = new JCheckBox("Listar");
		checkBox_11.setBounds(6, 45, 97, 23);
		panel_3.add(checkBox_11);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Or\u00E7amento",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(648, 147, 126, 126);
		panelPermissoes.add(panel_4);

		JCheckBox checkBox_12 = new JCheckBox("Cadastrar");
		checkBox_12.setBounds(6, 19, 97, 23);
		panel_4.add(checkBox_12);

		JCheckBox checkBox_13 = new JCheckBox("Editar");
		checkBox_13.setBounds(6, 71, 97, 23);
		panel_4.add(checkBox_13);

		JCheckBox checkBox_14 = new JCheckBox("Excluir");
		checkBox_14.setBounds(6, 97, 97, 23);
		panel_4.add(checkBox_14);

		JCheckBox checkBox_15 = new JCheckBox("Listar");
		checkBox_15.setBounds(6, 45, 97, 23);
		panel_4.add(checkBox_15);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Vendas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(28, 284, 126, 126);
		panelPermissoes.add(panel_5);

		JCheckBox checkBox_16 = new JCheckBox("Cadastrar");
		checkBox_16.setBounds(6, 19, 97, 23);
		panel_5.add(checkBox_16);

		JCheckBox checkBox_17 = new JCheckBox("Editar");
		checkBox_17.setBounds(6, 71, 97, 23);
		panel_5.add(checkBox_17);

		JCheckBox checkBox_18 = new JCheckBox("Excluir");
		checkBox_18.setBounds(6, 97, 97, 23);
		panel_5.add(checkBox_18);

		JCheckBox checkBox_19 = new JCheckBox("Listar");
		checkBox_19.setBounds(6, 45, 97, 23);
		panel_5.add(checkBox_19);

		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Dashboard",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(183, 284, 126, 126);
		panelPermissoes.add(panel_6);

		JCheckBox checkBox_20 = new JCheckBox("Cadastrar");
		checkBox_20.setBounds(6, 19, 97, 23);
		panel_6.add(checkBox_20);

		JCheckBox checkBox_21 = new JCheckBox("Editar");
		checkBox_21.setBounds(6, 71, 97, 23);
		panel_6.add(checkBox_21);

		JCheckBox checkBox_22 = new JCheckBox("Excluir");
		checkBox_22.setBounds(6, 97, 97, 23);
		panel_6.add(checkBox_22);

		JCheckBox checkBox_23 = new JCheckBox("Listar");
		checkBox_23.setBounds(6, 45, 97, 23);
		panel_6.add(checkBox_23);

		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Ferramentas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBounds(338, 284, 126, 126);
		panelPermissoes.add(panel_7);

		JCheckBox checkBox_24 = new JCheckBox("Cadastrar");
		checkBox_24.setBounds(6, 19, 97, 23);
		panel_7.add(checkBox_24);

		JCheckBox checkBox_25 = new JCheckBox("Editar");
		checkBox_25.setBounds(6, 71, 97, 23);
		panel_7.add(checkBox_25);

		JCheckBox checkBox_26 = new JCheckBox("Excluir");
		checkBox_26.setBounds(6, 97, 97, 23);
		panel_7.add(checkBox_26);

		JCheckBox checkBox_27 = new JCheckBox("Listar");
		checkBox_27.setBounds(6, 45, 97, 23);
		panel_7.add(checkBox_27);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 1, true), "Permiss\u00F5es rapidas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_8.setBounds(28, 11, 281, 125);
		panelPermissoes.add(panel_8);
		panel_8.setLayout(null);
		
		JCheckBox chckbxPdv = new JCheckBox("PDV");
		chckbxPdv.setBounds(6, 17, 54, 23);
		panel_8.add(chckbxPdv);
		
		JCheckBox chckbxEntradaDeMercadorias = new JCheckBox("Entrada de mercadorias");
		chckbxEntradaDeMercadorias.setBounds(6, 43, 151, 23);
		panel_8.add(chckbxEntradaDeMercadorias);

		lblCodigoCliente = new JLabel("Codigo Funcionario:");
		lblCodigoCliente.setBounds(10, 14, 111, 14);
		panelForm.add(lblCodigoCliente);

		txtCodFunc = new JTextField();
		txtCodFunc.setEnabled(false);
		txtCodFunc.setBounds(131, 11, 58, 20);
		panelForm.add(txtCodFunc);
		txtCodFunc.setColumns(10);
		txtCodFunc.setText(String.valueOf(id));

		lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(369, 17, 46, 14);
		panelForm.add(lblCpf);

		txtCpf = new JDocumentFormatedField().getCpf();
		txtCpf.setBounds(406, 14, 115, 20);
		txtCpf.setColumns(10);
		panelForm.add(txtCpf);
		txtCpf.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				String cpf = txtCpf.getText().replace(".", "").replace("-", "").replace(" ", "");
				if (!cpf.isEmpty()) {
					if (cpf.length() == 11) {
						if (!ValidaCNP.isValidCPF(cpf)) {
							JOptionPane.showMessageDialog(null, "O CPF digitado é invalido");
							cpf = null;
							txtCpf.setText(null);
						}
					}else {
						cpf = null;
						txtCpf.setText(null);
					}
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		lblRg = new JLabel("RG:");
		lblRg.setBounds(573, 17, 38, 14);
		panelForm.add(lblRg);

		txtRg = new JTextField();
		txtRg.setBounds(600, 14, 104, 20);
		panelForm.add(txtRg);
		txtRg.setColumns(10);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 62, 38, 14);
		panelForm.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(58, 59, 458, 20);
		panelForm.add(txtNome);
		txtNome.setColumns(10);

		lblCep = new JLabel("CEP:");
		lblCep.setBounds(10, 93, 30, 14);
		panelForm.add(lblCep);

		txtCep = new JDocumentFormatedField().getCep();
		txtCep.setBounds(50, 90, 115, 20);
		panelForm.add(txtCep);
		txtCep.setColumns(10);
		txtCep.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!txtCep.getText().replace("     -   ", "").isEmpty()) {
					ConsultaCep consulta = new ConsultaCep(txtCep.getText().replace("-", ""));
					txtBairro.setText(consulta.getBairro());
					txtCidade.setText(consulta.getCidade());
					txtEndereco.setText(consulta.getLogradouro());
					txtEstado.setSelectedItem(consulta.getEstado());
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(10, 124, 58, 14);
		panelForm.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(78, 121, 434, 20);
		panelForm.add(txtEndereco);
		txtEndereco.setColumns(10);

		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(548, 124, 58, 14);
		panelForm.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(616, 121, 66, 20);
		panelForm.add(txtNumero);
		txtNumero.setColumns(10);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 163, 38, 14);
		panelForm.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(58, 160, 223, 20);
		panelForm.add(txtBairro);
		txtBairro.setColumns(10);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(304, 163, 46, 14);
		panelForm.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(360, 160, 115, 20);
		panelForm.add(txtCidade);
		txtCidade.setColumns(10);

		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(520, 163, 46, 14);
		panelForm.add(lblEstado);

		txtEstado = new JComboBox<String>();
		txtEstado.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		txtEstado.setBounds(573, 160, 58, 20);
		panelForm.add(txtEstado);

		lblObservaes = new JLabel("Observações:");
		lblObservaes.setBounds(10, 293, 96, 14);
		panelForm.add(lblObservaes);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 789, 99);
		panelForm.add(scrollPane);

		txtAreaObs = new JTextArea();
		txtAreaObs.setLineWrap(true);
		scrollPane.setViewportView(txtAreaObs);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(548, 62, 46, 14);
		panelForm.add(lblSenha);

		pwdSenhaUsu = new JPasswordField();
		pwdSenhaUsu.setEchoChar('*');
		pwdSenhaUsu.setBounds(600, 59, 129, 20);
		panelForm.add(pwdSenhaUsu);

		if (opcao == 1) {
			try {
				Usuario usuario = new ControlerUsuario().consultaUsuEdit(idUsu);

				lblTitlePage.setText("alterar funcionario");
				txtCodFunc.setText(String.valueOf(usuario.getId()));
				txtRg.setText(String.valueOf(usuario.getRg()));
				txtCpf.setText(String.valueOf(usuario.getCpf()));
				txtNome.setText(usuario.getNome());
				txtCep.setText(String.valueOf(usuario.getCep()));
				txtEndereco.setText(usuario.getRua());
				txtNumero.setText(String.valueOf(usuario.getNumero()));
				txtBairro.setText(usuario.getBairro());
				txtCidade.setText(usuario.getCidade());
				txtEstado.setSelectedItem(usuario.getUf());
				txtAreaObs.setText(usuario.getObs());

			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Classe não encontrada, ver o log para mais detalhes",
						"Class not found", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog(e.getMessage());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
						"Banco de dados", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog(e.getMessage());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo, veja o log para mais detalhes",
						"Arquivo", JOptionPane.INFORMATION_MESSAGE);
				Log.gravaLog(e.getMessage());
			}
		}

		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario usu = new Usuario();
				usu.setId(Integer.parseInt(txtCodFunc.getText()));
				if (!txtCpf.getText().replace(".", "").replace("-", "").replace(" ", "").isEmpty()) {
					usu.setCpf(Long.parseLong(txtCpf.getText().replace(".", "").replace("-", "").replace(" ", "")));
				}
				if (!txtRg.getText().isEmpty()) {
					usu.setRg(Integer.parseInt(txtRg.getText()));
				}
				usu.setNome(txtNome.getText());
				usu.setSenha(Seguranca.criptografar(new String(pwdSenhaUsu.getPassword())));
				if (!txtCep.getText().replace("-", "").replace(" ", "").isEmpty()) {
					usu.setCep(Integer.parseInt(txtCep.getText().replace("-", "").replace(" ", "")));
				}
				usu.setRua(txtEndereco.getText());
				if (!txtNumero.getText().isEmpty()) {
					usu.setNumero(Integer.parseInt(txtNumero.getText()));
				}
				usu.setBairro(txtBairro.getText());
				usu.setCidade(txtCidade.getText());
				usu.setUf(txtEstado.getSelectedItem().toString());
				usu.setObs(txtAreaObs.getText());
				try {
					if (opcao == 0) {
						new ControlerUsuario().cadastrarUsuario(usu);
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
						dispose();
					} else {
						new ControlerUsuario().atualizarUsuario(usu);
						JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");
						ListarUsu.repagina();
						dispose();
					}

				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Classe não encontrada, ver o log para mais detalhes",
							"Class not found", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog(e.getMessage());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
							"Banco de dados", JOptionPane.ERROR_MESSAGE);
					Log.gravaLog(e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo, veja o log para mais detalhes",
							"Arquivo", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog(e.getMessage());
				} catch (UsuarioJaCadastradoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}