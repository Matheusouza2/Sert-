package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.sert.controler.ConsultaCep;
import com.sert.controler.ControlerCliente;
import com.sert.controler.ControlerDuplicata;
import com.sert.controler.JDateField;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.controler.ValidaCNP;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.entidades.Cliente;
import com.sert.entidades.DuplicataCliente;
import com.sert.exceptions.ClienteJaCadastradoException;
import com.sert.tables.TableModelContasAReceber;
import com.sert.tables.TableRenderer;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.5
 * 
 */
public class CadCliente extends JDialog implements ActionListener, FocusListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JLabel lblCodigoCliente;
	private JLabel lblCpf;
	private JLabel lblRg;
	private JLabel lblNome;
	private JLabel lblEndereco;
	private JLabel lblCep;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JLabel lblContato;
	private JLabel lblObservacoes;
	private JLabel lblDataDeInclusao;
	private JLabel lblUsuario;
	private JLabel lblCadastroDeClientes;

	private JTextField txtCodCliente;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtRg;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JComboBox<String> txtEstado;
	private JTextField txtNumero;
	private JTextField txtContato;
	private JTextArea txtAreaObs;

	private JButton btnX;
	private JButton btnSalvar;
	private JRadioButton rdbtnCpf;
	private JRadioButton rdbtnCnpj;

	private int id;
	private static int idEdit;

	private ButtonGroup bg = new ButtonGroup();
	private JPanel panelDebito;
	private JTextField txtFiltrar;
	private static JTable tableDebitos;
	private JTabbedPane tabbedPane;
	private JLabel lblFiltrar;
	private JScrollPane scrollPane;
	private int opcao;
	private JButton btnGerarCpf;
	private static BasicEventList<DuplicataCliente> duplicatas;
	private FilterList<DuplicataCliente> textFilteredIssues;
	private JSeparator separator;
	private JButton btnBaixarDuplicata;
	private JButton btnVerDuplicata;
	private JComboBox<String> cbFiltro;
	private JSeparator separator_1;
	private JSeparator separatorId;
	private JSeparator separatorEndereco;
	private JSeparator separatorNome;
	private JSeparator separatorNumero;
	private JSeparator separatorCpf;
	private JSeparator separatorBairro;
	private JSeparator separatorCidade;
	private JSeparator separatorContato;
	private JButton btnSomatorio;
	private JLabel lblBack;
	private JLabel lblBackBtn;
	private JLabel lblBackForm;

	// Quando a opçao for 0 será chamado o cadastro, quando a opção for 1 será
	// chamado o editar
	public CadCliente(int opcao, int idEdit) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 618);
		setUndecorated(true);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0f));
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listen();

		this.opcao = opcao;
		CadCliente.idEdit = idEdit;

		btnX = new JButton();
		btnX.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/btnX.png")));
		btnX.setBounds(790, 2, 30, 30);
		btnX.setContentAreaFilled(false);
		btnX.setOpaque(false);
		btnX.setBorderPainted(false);
		contentPane.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblCadastroDeClientes = new JLabel();
		lblCadastroDeClientes.setForeground(new Color(255, 255, 255));
		lblCadastroDeClientes.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeClientes.setBounds(33, 0, 768, 33);
		contentPane.add(lblCadastroDeClientes);
		if (opcao == 0) {
			lblCadastroDeClientes.setText("cadastrar cliente");
		} else {
			lblCadastroDeClientes.setText("editar cliente");
		}

		panelBtn = new JPanel();
		panelBtn.setOpaque(false);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 814, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setBorderPainted(false);
		btnSalvar.setOpaque(false);
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		btnGerarCpf = new JButton();
		btnGerarCpf.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/btnGerarCpf.png")));
		btnGerarCpf.setBorderPainted(false);
		btnGerarCpf.setOpaque(false);
		btnGerarCpf.setContentAreaFilled(false);
		btnGerarCpf.setBounds(109, 11, 89, 91);
		btnGerarCpf
				.setToolTipText("Quando não tiver o CFP real do cliente clique aqui que o sistema gerará um para você");
		panelBtn.add(btnGerarCpf);

		lblBackBtn = new JLabel("");
		lblBackBtn.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/backBtnPanelInterno.png")));
		lblBackBtn.setBounds(0, 0, 814, 113);
		panelBtn.add(lblBackBtn);
		btnGerarCpf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnCpf.setSelected(true);
				txtCpf.setText(ValidaCNP.geraCPF());
			}
		});

		try {
			id = new ControlerCliente().confereId();
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

		txtCpf = new JDocumentFormatedField().getCpf();
		txtCpf.setBounds(406, 14, 115, 20);
		txtCpf.setBorder(null);
		txtCpf.setOpaque(false);
		txtCpf.setColumns(10);
		txtCpf.addFocusListener(this);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 158, 814, 449);
		contentPane.add(tabbedPane);

		panelForm = new JPanel();
		panelForm.setBackground(new Color(230, 230, 250));
		panelForm.setLayout(null);

		tabbedPane.addTab("Cadastro", null, panelForm, null);
		if (opcao == 1) {
			chamaDebitos();
		}

		lblCodigoCliente = new JLabel("Codigo Cliente:");
		lblCodigoCliente.setForeground(new Color(0, 0, 128));
		lblCodigoCliente.setBounds(10, 14, 97, 14);
		panelForm.add(lblCodigoCliente);

		txtCodCliente = new JTextField();
		txtCodCliente.setEnabled(false);
		txtCodCliente.setBorder(null);
		txtCodCliente.setOpaque(false);
		txtCodCliente.setBounds(105, 11, 58, 20);
		panelForm.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		txtCodCliente.setText(String.valueOf(id));

		separatorId = new JSeparator();
		separatorId.setBackground(new Color(0, 0, 128));
		separatorId.setBounds(10, 32, 153, 2);
		panelForm.add(separatorId);

		rdbtnCpf = new JRadioButton("CPF");
		rdbtnCpf.setOpaque(false);
		rdbtnCpf.setBounds(209, 11, 58, 23);
		rdbtnCpf.setSelected(true);
		panelForm.add(rdbtnCpf);
		bg.add(rdbtnCpf);

		rdbtnCpf.addActionListener(this);

		rdbtnCnpj = new JRadioButton("CNPJ");
		rdbtnCnpj.setOpaque(false);
		rdbtnCnpj.setBounds(269, 11, 58, 23);
		panelForm.add(rdbtnCnpj);

		bg.add(rdbtnCnpj);

		rdbtnCnpj.addActionListener(this);

		lblCpf = new JLabel("CPF:");
		lblCpf.setOpaque(false);
		lblCpf.setBorder(null);
		lblCpf.setForeground(new Color(0, 0, 128));
		lblCpf.setBounds(369, 17, 46, 14);
		panelForm.add(lblCpf);
		panelForm.add(txtCpf);

		separatorCpf = new JSeparator();
		separatorCpf.setBackground(new Color(0, 0, 128));
		separatorCpf.setBounds(369, 35, 139, 2);
		panelForm.add(separatorCpf);

		lblRg = new JLabel("RG:");
		lblRg.setForeground(new Color(0, 0, 128));
		lblRg.setBounds(573, 17, 38, 14);
		panelForm.add(lblRg);

		txtRg = new JTextField();
		txtRg.setBounds(600, 14, 104, 20);
		txtRg.setBorder(null);
		txtRg.setOpaque(false);
		panelForm.add(txtRg);
		txtRg.setColumns(10);

		JSeparator separatorRg = new JSeparator();
		separatorRg.setBackground(new Color(0, 0, 128));
		separatorRg.setBounds(573, 35, 139, 2);
		panelForm.add(separatorRg);

		lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(0, 0, 128));
		lblNome.setBounds(10, 62, 38, 14);
		panelForm.add(lblNome);

		txtNome = new JTextField();
		txtNome.setOpaque(false);
		txtNome.setBorder(null);
		txtNome.setBounds(58, 59, 458, 20);
		panelForm.add(txtNome);
		txtNome.setColumns(10);

		separatorNome = new JSeparator();
		separatorNome.setBackground(new Color(0, 0, 128));
		separatorNome.setBounds(10, 80, 506, 2);
		panelForm.add(separatorNome);

		lblCep = new JLabel("CEP:");
		lblCep.setForeground(new Color(0, 0, 128));
		lblCep.setBounds(549, 62, 30, 14);
		panelForm.add(lblCep);

		txtCep = new JDocumentFormatedField().getCep();
		txtCep.setBounds(589, 59, 115, 20);
		panelForm.add(txtCep);
		txtCep.setBorder(null);
		txtCep.setOpaque(false);
		txtCep.setColumns(10);
		txtCep.addFocusListener(this);

		JSeparator separatorCep = new JSeparator();
		separatorCep.setBackground(new Color(0, 0, 128));
		separatorCep.setBounds(549, 80, 129, 2);
		panelForm.add(separatorCep);

		lblEndereco = new JLabel("Endereço:");
		lblEndereco.setForeground(new Color(0, 0, 128));
		lblEndereco.setBounds(10, 115, 58, 14);
		panelForm.add(lblEndereco);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(78, 112, 434, 20);
		txtEndereco.setBorder(null);
		txtEndereco.setOpaque(false);
		panelForm.add(txtEndereco);
		txtEndereco.setColumns(10);

		separatorEndereco = new JSeparator();
		separatorEndereco.setBackground(new Color(0, 0, 128));
		separatorEndereco.setBounds(10, 132, 502, 2);
		panelForm.add(separatorEndereco);

		lblNumero = new JLabel("Numero:");
		lblNumero.setForeground(new Color(0, 0, 128));
		lblNumero.setBounds(549, 115, 58, 14);
		panelForm.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setBounds(600, 112, 83, 20);
		panelForm.add(txtNumero);
		txtNumero.setBorder(null);
		txtNumero.setOpaque(false);
		txtNumero.setColumns(10);

		separatorNumero = new JSeparator();
		separatorNumero.setBackground(new Color(0, 0, 128));
		separatorNumero.setBounds(549, 132, 139, 2);
		panelForm.add(separatorNumero);

		lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(new Color(0, 0, 128));
		lblBairro.setBounds(10, 157, 38, 14);
		panelForm.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setBounds(58, 154, 223, 20);
		txtBairro.setBorder(null);
		txtBairro.setOpaque(false);
		panelForm.add(txtBairro);
		txtBairro.setColumns(10);

		separatorBairro = new JSeparator();
		separatorBairro.setBackground(new Color(0, 0, 128));
		separatorBairro.setBounds(10, 175, 272, 2);
		panelForm.add(separatorBairro);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(new Color(0, 0, 128));
		lblCidade.setBounds(304, 157, 46, 14);
		panelForm.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(360, 154, 115, 20);
		txtCidade.setBorder(null);
		txtCidade.setOpaque(false);
		panelForm.add(txtCidade);
		txtCidade.setColumns(10);

		separatorCidade = new JSeparator();
		separatorCidade.setBackground(new Color(0, 0, 128));
		separatorCidade.setBounds(304, 175, 171, 2);
		panelForm.add(separatorCidade);

		lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(0, 0, 128));
		lblEstado.setBounds(520, 157, 46, 14);
		panelForm.add(lblEstado);

		txtEstado = new JComboBox<String>();
		txtEstado.setBounds(576, 154, 58, 20);
		txtEstado.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		panelForm.add(txtEstado);

		lblContato = new JLabel("Contato:");
		lblContato.setForeground(new Color(0, 0, 128));
		lblContato.setBounds(10, 198, 61, 14);
		panelForm.add(lblContato);

		txtContato = new JDocumentFormatedField().getTel();
		txtContato.setBounds(58, 195, 115, 20);
		txtContato.setBorder(null);
		txtContato.setOpaque(false);
		panelForm.add(txtContato);
		txtContato.setColumns(10);

		separatorContato = new JSeparator();
		separatorContato.setBackground(new Color(0, 0, 128));
		separatorContato.setBounds(10, 215, 163, 2);
		panelForm.add(separatorContato);

		lblUsuario = new JLabel("Usuario:" + UsuLogado.getNome());
		lblUsuario.setForeground(new Color(0, 0, 128));
		lblUsuario.setBounds(322, 297, 198, 14);
		panelForm.add(lblUsuario);

		lblDataDeInclusao = new JLabel("Data de inclusão: " + JDateField.getDate());
		lblDataDeInclusao.setForeground(new Color(0, 0, 128));
		lblDataDeInclusao.setBounds(589, 297, 215, 14);
		panelForm.add(lblDataDeInclusao);

		lblObservacoes = new JLabel("Observações:");
		lblObservacoes.setForeground(new Color(0, 0, 128));
		lblObservacoes.setBounds(10, 297, 95, 14);
		panelForm.add(lblObservacoes);

		txtAreaObs = new JTextArea();
		txtAreaObs.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		txtAreaObs.setOpaque(false);
		txtAreaObs.setBounds(10, 322, 794, 88);
		panelForm.add(txtAreaObs);

//		lblBackForm = new JLabel("");
//		lblBackForm.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/backForm.png")));
//		lblBackForm.setBounds(0, 0, 809, 421);
//		panelForm.add(lblBackForm);

		lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/backPanel.png")));
		lblBack.setBounds(0, 0, 834, 618);
		contentPane.add(lblBack);

		txtNome.addFocusListener(this);

		btnSalvar.addActionListener(this);

		if (opcao == 1) {
			atualizarCliente();
		}
	}

	private void cadastrarCliente() {
		Cliente cliente = new Cliente();

		cliente.setId(Integer.parseInt(txtCodCliente.getText()));
		if (!txtCpf.getText().replace(".", "").replace("-", "").replace(" ", "").replace("/", "").isEmpty()) {
			cliente.setCpf(Long
					.parseLong(txtCpf.getText().replace(".", "").replace("-", "").replace(" ", "").replace("/", "")));
		}
		if (!txtRg.getText().isEmpty()) {
			cliente.setRg(Integer.parseInt(txtRg.getText()));
		}
		cliente.setNome(txtNome.getText());
		if (!txtCep.getText().replace("-", "").replace(" ", "").isEmpty()) {
			cliente.setCep(Integer.parseInt(txtCep.getText().replace("-", "").replace(" ", "")));
		}
		cliente.setRua(txtEndereco.getText());
		if (!txtNumero.getText().isEmpty()) {
			cliente.setNumero(Integer.parseInt(txtNumero.getText()));
		}
		cliente.setBairro(txtBairro.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setUf(txtEstado.getSelectedItem().toString());
		cliente.setObs(txtAreaObs.getText());
		if (!txtContato.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "").isEmpty()) {
			cliente.setContato(Long.parseLong(
					txtContato.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", "")));
		}

		try {
			if (opcao == 0) {
				new ControlerCliente().cadastrarCliente(cliente);
				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
				limparTela();
			} else if (opcao == 1) {
				new ControlerCliente().atualizarCliente(cliente);
				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!");
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
			JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog(e.getMessage());
		} catch (ClienteJaCadastradoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Arquivo", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog(e.getMessage());
		}
	}

	private void limparTela() {
		try {
			id = new ControlerCliente().confereId();
			txtCodCliente.setText(String.valueOf(id));
			txtNome.setText(null);
			txtAreaObs.setText(null);
			txtBairro.setText(null);
			txtCep.setText(null);
			txtCidade.setText(null);
			txtContato.setText(null);
			txtCpf.setText(null);
			txtEndereco.setText(null);
			txtEstado.setSelectedIndex(0);
			txtNumero.setText(null);
			txtRg.setText(null);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CAD CLIENTE |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CAD CLIENTE |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CAD CLIENTE |" + e1.getMessage());
		}
	}

	private void atualizarCliente() {
		try {
			Cliente cliente = new ControlerCliente().consultaClienteAlter(idEdit);
			txtCodCliente.setText(String.valueOf(cliente.getId()));
			txtNome.setText(cliente.getNome());
			txtCpf.setText(String.valueOf(cliente.getCpf()));
			txtBairro.setText(cliente.getBairro());
			txtCep.setText(String.valueOf(cliente.getCep()));
			txtAreaObs.append(cliente.getObs());
			txtCidade.setText(cliente.getCidade());
			txtContato.setText(String.valueOf(cliente.getContato()));
			txtEndereco.setText(cliente.getRua());
			txtEstado.setSelectedItem(cliente.getUf());
			txtNumero.setText(String.valueOf(cliente.getNumero()));

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| ALT CLIENTE |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| ALT CLIENTE |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| ALT CLIENTE |" + e1.getMessage());
		}
	}

	private void chamaDebitos() {

		btnBaixarDuplicata = new JButton();
		btnBaixarDuplicata.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/btnBaixarConta.png")));
		btnBaixarDuplicata.setToolTipText("Selecione uma duplicata para dar baixa");
		btnBaixarDuplicata.setBorderPainted(false);
		btnBaixarDuplicata.setOpaque(false);
		btnBaixarDuplicata.setContentAreaFilled(false);
		btnBaixarDuplicata.setBounds(208, 11, 89, 91);
		panelBtn.add(btnBaixarDuplicata);
		btnBaixarDuplicata.addActionListener(this);

		btnVerDuplicata = new JButton();
		btnVerDuplicata.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/btnVerDuplicata.png")));
		btnVerDuplicata.setOpaque(false);
		btnVerDuplicata.setBorderPainted(false);
		btnVerDuplicata.setContentAreaFilled(false);
		btnVerDuplicata.setBounds(307, 11, 89, 91);
		panelBtn.add(btnVerDuplicata);

		btnSomatorio = new JButton();
		btnSomatorio.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/btnSomatorio.png")));
		btnSomatorio.setOpaque(false);
		btnSomatorio.setBorderPainted(false);
		btnSomatorio.setContentAreaFilled(false);
		btnSomatorio.setBounds(406, 11, 89, 91);
		panelBtn.add(btnSomatorio);
		btnVerDuplicata.addActionListener(this);

		panelDebito = new JPanel();
		panelDebito.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelDebito.setLayout(null);
		tabbedPane.addTab("Débitos", null, panelDebito, null);
		lblFiltrar = new JLabel("filtrar");
		lblFiltrar.setForeground(new Color(0, 0, 128));
		lblFiltrar.setFont(new Font("Gtek Technology", Font.BOLD, 11));
		lblFiltrar.setBounds(10, 11, 77, 14);
		panelDebito.add(lblFiltrar);

		txtFiltrar = new JTextField();
		txtFiltrar.setBackground(new Color(240, 240, 240));
		txtFiltrar.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFiltrar.setBorder(null);
		txtFiltrar.setBounds(97, 7, 126, 20);
		panelDebito.add(txtFiltrar);
		txtFiltrar.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 789, 370);
		panelDebito.add(scrollPane);

		tableDebitos = new JTable();
		tableDebitos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableDebitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDebitos.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tableDebitos);

		duplicatas = new BasicEventList<DuplicataCliente>();

		try {
			for (DuplicataCliente duplicata : new ControlerDuplicata().listDuplicata()) {
				if (duplicata.getCliente().getId() == idEdit) {
					duplicatas.add(duplicata);
				}
			}

			MatcherEditor<DuplicataCliente> textMatcherEditor = new TextComponentMatcherEditor<DuplicataCliente>(
					txtFiltrar, new DuplicataCliente());
			textFilteredIssues = new FilterList<DuplicataCliente>(duplicatas, textMatcherEditor);
			AdvancedTableModel<DuplicataCliente> mercTableModel = GlazedListsSwing
					.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelContasAReceber());
			tableDebitos.setModel(mercTableModel);

			tableDebitos.getColumnModel().getColumn(2).setPreferredWidth(400);
			tableDebitos.getColumnModel().getColumn(6).setMaxWidth(0);
			tableDebitos.getColumnModel().getColumn(6).setMinWidth(0);
			tableDebitos.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
			tableDebitos.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
			tableDebitos.setDefaultRenderer(Object.class, new TableRenderer());

			separator = new JSeparator();
			separator.setBackground(new Color(0, 0, 128));
			separator.setBounds(96, 27, 127, 2);
			panelDebito.add(separator);

			cbFiltro = new JComboBox<String>();
			cbFiltro.setBounds(257, 7, 102, 20);
			panelDebito.add(cbFiltro);
			cbFiltro.setModel(new DefaultComboBoxModel<String>(new String[] { "", "A vencer", "Baixado", "Atrasada" }));
			cbFiltro.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					atualizarLista(String.valueOf(cbFiltro.getSelectedItem()));
				}
			});

			separator_1 = new JSeparator();
			separator_1.setOrientation(SwingConstants.VERTICAL);
			separator_1.setBackground(Color.YELLOW);
			separator_1.setBounds(233, 1, 1, 41);
			panelDebito.add(separator_1);

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		}

		lblBackForm = new JLabel("");
		lblBackForm.setIcon(new ImageIcon(CadCliente.class.getResource("/com/sert/img/backForm.png")));
		lblBackForm.setBounds(0, 0, 809, 421);
		panelDebito.add(lblBackForm);
	}

	public static void atualizarLista(String filtro) {
		try {
			duplicatas.clear();
			for (DuplicataCliente duplicata : new ControlerDuplicata().listDuplicata()) {
				if (duplicata.getCliente().getId() == idEdit) {
					if (duplicata.getSituacao().equals(filtro)) {
						duplicatas.add(duplicata);
					} else if (filtro.equals("")) {
						duplicatas.add(duplicata);
					}
				}
			}

			tableDebitos.revalidate();

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| CONTAS A RECEBER |" + e1.getMessage());
		}
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE");
	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		if (arg0.getSource() == txtCep) {
			if (!txtCep.getText().replace("     -   ", "").isEmpty()) {
				ConsultaCep consulta = new ConsultaCep(txtCep.getText().replace("-", ""));
				txtBairro.setText(consulta.getBairro());
				txtCidade.setText(consulta.getCidade());
				txtEndereco.setText(consulta.getLogradouro());
				txtEstado.setSelectedItem(consulta.getEstado());
			}
		} else if (arg0.getSource() == txtCpf) {
			if (rdbtnCnpj.isSelected()) { // Validação do CNPJ
				String cnpj = txtCpf.getText().replace(".", "").replace("-", "").replace("/", "").replace(" ", "");
				if (!cnpj.isEmpty()) {
					if (cnpj.length() == 14) {
						if (!ValidaCNP.isValidCNPJ(cnpj)) {
							JOptionPane.showMessageDialog(null, "O CNPJ digitado é invalido");
							cnpj = null;
							txtCpf.setText(null);
						}
					} else {
						cnpj = null;
						txtCpf.setText(null);
					}
				}
			} // Fim da validação do CNPJ
			else if (rdbtnCpf.isSelected()) { // Validação do CPF
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
			} // Fim da validação do CPF
		} else if (arg0.getSource() == txtNome) {
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "O cliente deve ter um nome", "Advertência",
						JOptionPane.WARNING_MESSAGE);
				txtNome.requestFocus();
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerDuplicata) {
			if (tableDebitos.getSelectedRow() >= 0) {
				int idParcela = (int) tableDebitos.getValueAt(tableDebitos.getSelectedRow(), 6);
				new BaixarParcela(idParcela, 1, true).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma duplicata para ser vizualizada");
			}
		} else if (e.getSource() == btnBaixarDuplicata) {
			if (tableDebitos.getSelectedRow() >= 0) {
				if (tableDebitos.getValueAt(tableDebitos.getSelectedRow(), 1).equals("Baixado")) {
					JOptionPane.showMessageDialog(null, "Parcela já baixada");
					return;
				}
				int idParcela = duplicatas.get(tableDebitos.getSelectedRow()).getId();
				new BaixarParcela(idParcela, 0, false).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma duplicata para baixar");
			}
		} else if (e.getSource() == rdbtnCpf) {
			panelForm.remove(txtCpf);
			panelForm.revalidate();
			lblCpf.setText("CPF: ");
			txtCpf = new JDocumentFormatedField().getCpf();
			txtCpf.setBounds(406, 14, 115, 20);
			txtCpf.setBorder(null);
			txtCpf.setColumns(10);
			txtCpf.addFocusListener(this);
			panelForm.add(txtCpf);
			lblRg.setText("RG: ");
			txtCpf.addFocusListener(this);
		} else if (e.getSource() == rdbtnCnpj) {
			panelForm.remove(txtCpf);
			panelForm.revalidate();
			lblCpf.setText("CNPJ: ");
			txtCpf = new JDocumentFormatedField().getCnpj();
			txtCpf.setBounds(406, 14, 115, 20);
			txtCpf.setBorder(null);
			txtCpf.setColumns(10);
			txtCpf.addFocusListener(this);
			panelForm.add(txtCpf);
			lblRg.setText("IE: ");
			txtCpf.addFocusListener(this);
		} else if (e.getSource() == btnSalvar) {
			cadastrarCliente();
		}
	}
}