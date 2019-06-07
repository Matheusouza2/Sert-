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
import com.sert.entidades.PermissoesFunc;
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
	private JCheckBox chckbxEstoque;
	private JCheckBox chckbxCaixa;
	private JCheckBox chckbxCompra;
	private JCheckBox chckbxVenda;
	private PermissoesFunc permissoes = new PermissoesFunc();
	private JCheckBox chckbxCadClient;
	private JPanel panelCliente;
	private JCheckBox chckbxEditClient;
	private JCheckBox chckbxExcClient;
	private JCheckBox chckbxListarClient;
	private JPanel panelNota;
	private JCheckBox chckbxCadNota;
	private JCheckBox chckbxMovEstoque;
	private JCheckBox chckbxListarProd;
	private JCheckBox chckbxEditarFunc;
	private JCheckBox chckbxExcluirFunc;
	private JCheckBox chckbxListarFunc;
	private JCheckBox chckbxEditProd;
	private JCheckBox chckbxCadProd;
	private JCheckBox chckbxExcProd;
	private JCheckBox chckbxCadastrarFunc;
	private JCheckBox chckbxLancarVendas;
	private JCheckBox chckbxLancarConsignao;
	private JCheckBox chckbxListarNota;
	private JPanel panelOrcamento;
	private JCheckBox chckbxCadOrcamento;
	private JCheckBox chckbxVerConsignao;
	private JPanel panelPreConfg;
	private JCheckBox chckbxAdmin;

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

		panelCliente = new JPanel();
		panelCliente.setLayout(null);
		panelCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Clientes",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCliente.setBounds(28, 11, 126, 126);
		panelPermissoes.add(panelCliente);

		chckbxCadClient = new JCheckBox("Cadastrar");
		chckbxCadClient.setBounds(6, 19, 97, 23);
		panelCliente.add(chckbxCadClient);

		chckbxEditClient = new JCheckBox("Editar");
		chckbxEditClient.setBounds(6, 71, 97, 23);
		panelCliente.add(chckbxEditClient);

		chckbxExcClient = new JCheckBox("Excluir");
		chckbxExcClient.setBounds(6, 97, 97, 23);
		panelCliente.add(chckbxExcClient);

		chckbxListarClient = new JCheckBox("Listar");
		chckbxListarClient.setBounds(6, 45, 97, 23);
		panelCliente.add(chckbxListarClient);

		JPanel panelFuncionario = new JPanel();
		panelFuncionario.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Funcionarios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelFuncionario.setBounds(183, 11, 126, 126);
		panelPermissoes.add(panelFuncionario);
		panelFuncionario.setLayout(null);

		chckbxCadastrarFunc = new JCheckBox("Cadastrar");
		chckbxCadastrarFunc.setBounds(6, 45, 97, 23);
		panelFuncionario.add(chckbxCadastrarFunc);

		chckbxEditarFunc = new JCheckBox("Editar");
		chckbxEditarFunc.setBounds(6, 71, 97, 23);
		panelFuncionario.add(chckbxEditarFunc);

		chckbxExcluirFunc = new JCheckBox("Excluir");
		chckbxExcluirFunc.setBounds(6, 97, 97, 23);
		panelFuncionario.add(chckbxExcluirFunc);

		chckbxListarFunc = new JCheckBox("Listar");
		chckbxListarFunc.setBounds(6, 19, 97, 23);
		panelFuncionario.add(chckbxListarFunc);

		JPanel panelProduto = new JPanel();
		panelProduto.setLayout(null);
		panelProduto.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Produto",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProduto.setBounds(338, 11, 126, 126);
		panelPermissoes.add(panelProduto);

		chckbxCadProd = new JCheckBox("Cadastrar");
		chckbxCadProd.setBounds(6, 19, 97, 23);
		panelProduto.add(chckbxCadProd);

		chckbxEditProd = new JCheckBox("Editar");
		chckbxEditProd.setBounds(6, 71, 97, 23);
		panelProduto.add(chckbxEditProd);

		chckbxExcProd = new JCheckBox("Excluir");
		chckbxExcProd.setBounds(6, 97, 97, 23);
		panelProduto.add(chckbxExcProd);

		chckbxListarProd = new JCheckBox("Listar");
		chckbxListarProd.setBounds(6, 45, 97, 23);
		panelProduto.add(chckbxListarProd);

		panelNota = new JPanel();
		panelNota.setLayout(null);
		panelNota.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Nota",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNota.setBounds(493, 11, 126, 98);
		panelPermissoes.add(panelNota);

		chckbxCadNota = new JCheckBox("Cadastrar");
		chckbxCadNota.setBounds(6, 19, 97, 23);
		panelNota.add(chckbxCadNota);

		chckbxMovEstoque = new JCheckBox("Mov Estoque");
		chckbxMovEstoque.setBounds(6, 71, 97, 23);
		panelNota.add(chckbxMovEstoque);

		chckbxListarNota = new JCheckBox("Listar");
		chckbxListarNota.setBounds(6, 45, 97, 23);
		panelNota.add(chckbxListarNota);

		panelOrcamento = new JPanel();
		panelOrcamento.setLayout(null);
		panelOrcamento.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Or\u00E7amento",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelOrcamento.setBounds(629, 11, 170, 103);
		panelPermissoes.add(panelOrcamento);

		chckbxCadOrcamento = new JCheckBox("Cadastrar");
		chckbxCadOrcamento.setBounds(6, 19, 97, 23);
		panelOrcamento.add(chckbxCadOrcamento);

		chckbxVerConsignao = new JCheckBox("Ver consignação");
		chckbxVerConsignao.setBounds(6, 71, 139, 23);
		panelOrcamento.add(chckbxVerConsignao);

		chckbxLancarConsignao = new JCheckBox("Lançar consignação");
		chckbxLancarConsignao.setBounds(6, 45, 158, 23);
		panelOrcamento.add(chckbxLancarConsignao);

		JPanel panelVenda = new JPanel();
		panelVenda.setLayout(null);
		panelVenda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Vendas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelVenda.setBounds(28, 148, 126, 54);
		panelPermissoes.add(panelVenda);

		chckbxLancarVendas = new JCheckBox("Lançar Vendas");
		chckbxLancarVendas.setBounds(6, 19, 114, 23);
		panelVenda.add(chckbxLancarVendas);

		JPanel panelDashBoard = new JPanel();
		panelDashBoard.setLayout(null);
		panelDashBoard.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true), "Dashboard",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDashBoard.setBounds(183, 148, 126, 126);
		panelPermissoes.add(panelDashBoard);

		chckbxEstoque = new JCheckBox("Estoque");
		chckbxEstoque.setBounds(6, 19, 97, 23);
		panelDashBoard.add(chckbxEstoque);

		chckbxCaixa = new JCheckBox("Caixa");
		chckbxCaixa.setBounds(6, 71, 97, 23);
		panelDashBoard.add(chckbxCaixa);

		chckbxCompra = new JCheckBox("Compra");
		chckbxCompra.setBounds(6, 97, 97, 23);
		panelDashBoard.add(chckbxCompra);

		chckbxVenda = new JCheckBox("Venda");
		chckbxVenda.setBounds(6, 45, 97, 23);
		panelDashBoard.add(chckbxVenda);

		panelPreConfg = new JPanel();
		panelPreConfg.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 128), 2, true),
				"Pr\u00E9 configura\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPreConfg.setBounds(673, 290, 126, 120);
		panelPermissoes.add(panelPreConfg);
		panelPreConfg.setLayout(null);

		chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxAdmin.isSelected()) {
					selectAll();
				}
			}
		});
		chckbxAdmin.setBounds(6, 22, 97, 23);
		panelPreConfg.add(chckbxAdmin);

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
					} else {
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

				getPermissoes(idUsu);

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
				setPermissoes();
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
						new ControlerUsuario().cadastrarUsuario(usu, permissoes);
						JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
						dispose();
					} else {
						new ControlerUsuario().atualizarUsuario(usu, permissoes);
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

	private void setPermissoes() {
		permissoes.setIdFunc(Integer.parseInt(txtCodFunc.getText()));
		if (chckbxCadClient.isSelected()) {
			permissoes.setCadCliente(true);
		}
		if (chckbxEditClient.isSelected()) {
			permissoes.setAltCliente(true);
		}
		if (chckbxListarClient.isSelected()) {
			permissoes.setListCliente(true);
		}
		if (chckbxExcClient.isSelected()) {
			permissoes.setExclCliente(true);
		}
		if (chckbxListarFunc.isSelected()) {
			permissoes.setListFunc(true);
		}
		if (chckbxCadastrarFunc.isSelected()) {
			permissoes.setCadFunc(true);
		}
		if (chckbxEditarFunc.isSelected()) {
			permissoes.setAltFunc(true);
		}
		if (chckbxExcluirFunc.isSelected()) {
			permissoes.setExclFunc(true);
		}
		if (chckbxCadProd.isSelected()) {
			permissoes.setCadProd(true);
		}
		if (chckbxListarProd.isSelected()) {
			permissoes.setListProd(true);
		}
		if (chckbxEditProd.isSelected()) {
			permissoes.setAltProd(true);
		}
		if (chckbxExcProd.isSelected()) {
			permissoes.setExclProduto(true);
		}
		if (chckbxCadNota.isSelected()) {
			permissoes.setCadNota(true);
		}
		if (chckbxListarNota.isSelected()) {
			permissoes.setListNota(true);
		}
		if (chckbxMovEstoque.isSelected()) {
			permissoes.setMovEstoque(true);
		}
		if (chckbxCadOrcamento.isSelected()) {
			permissoes.setCadOrcamento(true);
		}
		if (chckbxLancarConsignao.isSelected()) {
			permissoes.setLancConsignacao(true);
		}
		if (chckbxVerConsignao.isSelected()) {
			permissoes.setVerConsig(true);
		}
		if (chckbxEstoque.isSelected()) {
			permissoes.setDashEstoque(true);
		}
		if (chckbxVenda.isSelected()) {
			permissoes.setDashVenda(true);
		}
		if (chckbxCaixa.isSelected()) {
			permissoes.setDashCaixa(true);
		}
		if (chckbxCompra.isSelected()) {
			permissoes.setDashCompra(true);
		}
		if (chckbxLancarVendas.isSelected()) {
			permissoes.setLancarVendas(true);
		}
	}

	private void getPermissoes(int idUsu) {

		PermissoesFunc permissoesGet;
		try {
			permissoesGet = new ControlerUsuario().consultaPermicoes(idUsu);
			if (permissoesGet.isCadCliente()) {
				chckbxCadClient.setSelected(true);
			}
			if (permissoesGet.isAltCliente()) {
				chckbxEditClient.setSelected(true);
			}
			if (permissoesGet.isListCliente()) {
				chckbxListarClient.setSelected(true);
			}
			if (permissoesGet.isExclCliente()) {
				chckbxExcClient.setSelected(true);
			}
			if (permissoesGet.isListFunc()) {
				chckbxListarFunc.setSelected(true);
			}
			if (permissoesGet.isCadFunc()) {
				chckbxCadastrarFunc.setSelected(true);
			}
			if (permissoesGet.isAltFunc()) {
				chckbxEditarFunc.setSelected(true);
			}
			if (permissoesGet.isExclFunc()) {
				chckbxExcluirFunc.setSelected(true);
			}
			if (permissoesGet.isCadProd()) {
				chckbxCadProd.setSelected(true);
			}
			if (permissoesGet.isListProd()) {
				chckbxListarProd.setSelected(true);
			}
			if (permissoesGet.isAltProd()) {
				chckbxEditProd.setSelected(true);
			}
			if (permissoesGet.isExclProduto()) {
				chckbxExcProd.setSelected(true);
			}
			if (permissoesGet.isCadNota()) {
				chckbxCadNota.setSelected(true);
			}
			if (permissoesGet.isListNota()) {
				chckbxListarNota.setSelected(true);
			}
			if (permissoesGet.isMovEstoque()) {
				chckbxMovEstoque.setSelected(true);
			}
			if (permissoesGet.isCadOrcamento()) {
				chckbxCadOrcamento.setSelected(true);
			}
			if (permissoesGet.isLancConsignacao()) {
				chckbxLancarConsignao.setSelected(true);
			}
			if (permissoesGet.isVerConsig()) {
				chckbxVerConsignao.setSelected(true);
			}
			if (permissoesGet.isDashEstoque()) {
				chckbxEstoque.setSelected(true);
			}
			if (permissoesGet.isDashVenda()) {
				chckbxVenda.setSelected(true);
			}
			if (permissoesGet.isDashCaixa()) {
				chckbxCaixa.setSelected(true);
			}
			if (permissoesGet.isDashCompra()) {
				chckbxCompra.setSelected(true);
			}
			if (permissoesGet.isLancarVendas()) {
				chckbxLancarVendas.setSelected(true);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectAll() {
		chckbxCadClient.setSelected(true);
		chckbxEditClient.setSelected(true);
		chckbxListarClient.setSelected(true);
		chckbxExcClient.setSelected(true);
		chckbxListarFunc.setSelected(true);
		chckbxCadastrarFunc.setSelected(true);
		chckbxEditarFunc.setSelected(true);
		chckbxExcluirFunc.setSelected(true);
		chckbxCadProd.setSelected(true);
		chckbxListarProd.setSelected(true);
		chckbxEditProd.setSelected(true);
		chckbxExcProd.setSelected(true);
		chckbxCadNota.setSelected(true);
		chckbxListarNota.setSelected(true);
		chckbxMovEstoque.setSelected(true);
		chckbxCadOrcamento.setSelected(true);
		chckbxLancarConsignao.setSelected(true);
		chckbxVerConsignao.setSelected(true);
		chckbxEstoque.setSelected(true);
		chckbxVenda.setSelected(true);
		chckbxCaixa.setSelected(true);
		chckbxCompra.setSelected(true);
		chckbxLancarVendas.setSelected(true);

	}
}