package com.sert.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerCliente;
import com.sert.controler.ControlerConsignacao;
import com.sert.controler.ControlerOrcamento;
import com.sert.controler.ControlerUsuario;
import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.AutoCompletion;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Cliente;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Orcamento;
import com.sert.entidades.Usuario;
import com.sert.exceptions.MercadoriaSemEstoqueException;
import com.sert.exceptions.MercadoriaSemPrecoException;
import com.sert.exceptions.NenhumUsuCadException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;

public class TelaOrcamento extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JScrollPane scrollPane;

	private JLabel lblUsuario;
	private JLabel lblDataDeIncluso;

	private JButton btnSalvar;
	private JButton btnX;

	private JTable table;

	private DefaultTableModel modelo;
	private JComboBox<String> cbMercDesc;
	private List<Mercadoria> mercList;
	private JComboBox<String> cbMercRef;
	private JLabel lblCadastroDeMercadoria;
	private JComboBox<Integer> cbId;
	private JLabel lblCodDeBarras;
	private JLabel lblMercadoria;
	private JLabel lblQuant;
	private JTextField txtQuant;
	private JLabel lblCod;
	private JTextField txtCodCliente;
	private JComboBox<String> cbCliente;
	private JPanel panelId;
	private JLabel lblCodCliente;
	private JLabel lblCliente;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JTextField txtCodVendedor;
	private JButton btnImprimir;
	private JButton gerarConsignacao;
	private JButton button_1;
	private JButton btnAdd;
	private JComboBox<String> cbVendedor;
	private JSeparator separator;
	private JLabel lblCodVendedor;
	private JLabel lblVendedor;
	private JLabel lblN;
	private JLabel lblEndereo;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;
	private JTextField txtCpf;
	private JLabel lblContato;
	private JTextField txtContato;
	private JCheckBox chckbxSalvo;
	private int id = 0;
	private float valTotal;
	private JLabel lblValorTotalR;

	public TelaOrcamento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 760);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new LineBorder(Color.YELLOW, 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listen();

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			boolean[] canEdit = new boolean[] { false, false, false, true, false, false };

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		};

		lblCadastroDeMercadoria = new JLabel("orcamento");
		lblCadastroDeMercadoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeMercadoria.setForeground(Color.WHITE);
		lblCadastroDeMercadoria.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeMercadoria.setBounds(481, 0, 337, 35);
		contentPane.add(lblCadastroDeMercadoria);

		btnX = new JButton("X");
		btnX.setBounds(1254, 0, 46, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 1280, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvarOrcamento();
			}
		});

		btnImprimir = new JButton();
		btnImprimir.setIcon(new ImageIcon(TelaOrcamento.class.getResource("/com/sert/img/btnImprimir.png")));
		btnImprimir.setBackground(new Color(0, 128, 0));
		btnImprimir.setBounds(109, 11, 89, 91);
		panelBtn.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxSalvo.isSelected()) {

				} else {
					JOptionPane.showMessageDialog(null, "Salve o orçamento antes de imprimir!");
				}
			}
		});

		gerarConsignacao = new JButton();
		gerarConsignacao.setIcon(new ImageIcon(TelaOrcamento.class.getResource("/com/sert/img/btnConsignacao.png")));
		gerarConsignacao.setBackground(new Color(173, 216, 230));
		gerarConsignacao.setBounds(208, 11, 89, 91);
		panelBtn.add(gerarConsignacao);
		gerarConsignacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja gerar uma consignação deste orçamento?",
						"Consignação", JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					Orcamento consignacao = new Orcamento();
					List<Mercadoria> listMerc = new ArrayList<>();
					Mercadoria merc = new Mercadoria();
					Cliente cliente = new Cliente();
					Usuario usu = new Usuario();
					float valTotal = 0;
					usu.setId(Integer.parseInt(txtCodVendedor.getText()));
					cliente.setId(Integer.parseInt(txtCodCliente.getText()));
					for (int i = 0; i < table.getRowCount(); i++) {
						merc.setId((int) table.getValueAt(i, 0));
						merc.setEstoque(Float
								.parseFloat(table.getValueAt(i, 4).toString().replace("R$", "").replace(",", ".")));
						merc.setPrecoVenda(Float.parseFloat(table.getValueAt(i, 3).toString().replace("R$", "").replace(",", ".")));
						listMerc.add(merc);
						valTotal += Float
								.parseFloat(table.getValueAt(i, 5).toString().replace("R$", "").replace(",", "."));
					}
					try {
						consignacao.setId(new ControlerConsignacao().retornarId());
						consignacao.setUsuario(usu);
						consignacao.setCliente(cliente);
						consignacao.setMercadorias(listMerc);
						consignacao.setValTotal(valTotal);
						new ControlerConsignacao().lancarConsignacao(consignacao);
						
						JOptionPane.showMessageDialog(null, "Consignação gerada com sucesso!");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		button_1 = new JButton();
		button_1.setBackground(Color.GREEN);
		button_1.setBounds(307, 11, 89, 91);
		panelBtn.add(button_1);

		try {
			id = new ControlerOrcamento().confereId();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		lblN = new JLabel("Nº " + id);
		lblN.setBounds(1148, 11, 122, 26);
		panelBtn.add(lblN);
		lblN.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 332, 1280, 417);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 1250, 320);
		panelForm.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);

		lblDataDeIncluso = new JLabel("Data de inclusão: " + JDateField.getDate());
		lblDataDeIncluso.setBounds(10, 392, 289, 14);
		panelForm.add(lblDataDeIncluso);

		lblUsuario = new JLabel("Usuario: " + UsuLogado.getNome());
		lblUsuario.setBounds(891, 392, 357, 14);

		cbId = new JComboBox<Integer>();
		cbId.setEditable(true);
		cbId.setVisible(true);
		cbId.setBounds(38, 11, 58, 20);
		panelForm.add(cbId);

		cbMercDesc = new JComboBox<String>();
		cbMercDesc.setEditable(true);
		cbMercDesc.setVisible(true);
		cbMercDesc.setBounds(391, 11, 272, 20);
		panelForm.add(cbMercDesc);

		cbMercRef = new JComboBox<String>();
		cbMercRef.setEditable(true);
		cbMercRef.setVisible(true);
		cbMercRef.setBounds(197, 11, 102, 20);
		panelForm.add(cbMercRef);

		mercList = ControlerVenda.mercadorias;
		for (int i = 0; i < mercList.size(); i++) {

			cbId.addItem(mercList.get(i).getId());
			cbId.setSelectedItem(null);

			cbMercDesc.addItem(mercList.get(i).getMercadoria());
			cbMercDesc.setSelectedItem(null);

			cbMercRef.addItem(String.valueOf(mercList.get(i).getCodBarras()));
			cbMercRef.setSelectedItem(null);
		}
		AutoCompletion.enable(cbId);
		AutoCompletion.enable(cbMercDesc);
		AutoCompletion.enable(cbMercRef);

		escutaComboMerc();

		panelForm.add(lblUsuario);
		modelo.addColumn("Cod");
		modelo.addColumn("Cod. barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço Venda");
		modelo.addColumn("Quant.");
		modelo.addColumn("Preço Total");
		table.setModel(modelo);

		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JNumberField()));
		table.getColumnModel().getColumn(5)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
		table.getTableHeader().setReorderingAllowed(false);
		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if (table.getSelectedRow() >= 0) {
					int linha = table.getSelectedRow();
					double preco = Double
							.parseDouble(modelo.getValueAt(linha, 3).toString().replace(",", ".").replace("R$", ""));
					float qtd = Float.parseFloat(modelo.getValueAt(linha, 4).toString().replace(",", "."));
					double total = qtd * preco;
					String convert = String.format("R$ %.2f", total);
					modelo.setValueAt(convert, linha, 5);
				}
			}
		});

		lblCod = new JLabel("Cod:");
		lblCod.setBounds(10, 14, 38, 14);
		panelForm.add(lblCod);

		lblCodDeBarras = new JLabel("Cod. de Barras:");
		lblCodDeBarras.setBounds(106, 14, 89, 14);
		panelForm.add(lblCodDeBarras);

		lblMercadoria = new JLabel("Mercadoria:");
		lblMercadoria.setBounds(320, 14, 73, 14);
		panelForm.add(lblMercadoria);

		lblQuant = new JLabel("Quant.");
		lblQuant.setBounds(715, 12, 46, 14);
		panelForm.add(lblQuant);

		txtQuant = new JNumberField();
		txtQuant.setBounds(771, 9, 51, 20);
		panelForm.add(txtQuant);
		txtQuant.setColumns(10);

		btnAdd = new JButton("Adicionar");
		btnAdd.setBounds(832, 8, 89, 23);
		panelForm.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long codBarras = Long.parseLong(cbMercRef.getSelectedItem().toString());
				float quant = Float.parseFloat(txtQuant.getText().replace(",", "."));
				Mercadoria mercadoria;
				try {
					mercadoria = new ControlerVenda().consultaMercVenda(codBarras, quant);

					modelo.addRow(new Object[] { mercadoria.getId(), mercadoria.getCodBarras(),
							mercadoria.getMercadoria(), String.format("R$ %.2f", mercadoria.getPrecoVenda()), quant,
							String.format("R$ %.2f", mercadoria.getPrecoVenda() * quant) });

					table.setModel(modelo);
					table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);

					cbId.setSelectedItem("");
					cbMercDesc.setSelectedItem("");
					cbMercRef.setSelectedItem("");
					txtQuant.setText("");
					valTotal += Float.parseFloat(
							table.getValueAt(table.getSelectedRow(), 5).toString().replace("R$", "").replace(",", "."));
					lblValorTotalR.setText("Valor total: R$ " + String.format("%.2f", valTotal));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Valor invalido", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "AVISO",
							JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (MercadoriaSemEstoqueException e1) {
					JOptionPane.showMessageDialog(null, "A quantidade a ser vendida está maior do que o estoque",
							"AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro de banco de dados, contate o suporte para auxilio",
							"AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo, contate o suporte para auxilio",
							"AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (NenhumaMercadoriaCadastradaException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				} catch (MercadoriaSemPrecoException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
					Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
				}
			}
		});

		chckbxSalvo = new JCheckBox("Salvo");
		chckbxSalvo.setEnabled(false);
		chckbxSalvo.setBounds(1163, 10, 97, 23);
		panelForm.add(chckbxSalvo);

		lblValorTotalR = new JLabel();
		lblValorTotalR.setBounds(1091, 373, 169, 14);
		panelForm.add(lblValorTotalR);

		panelId = new JPanel();
		panelId.setBorder(new LineBorder(new Color(30, 144, 255), 2, true));
		panelId.setBounds(10, 158, 1280, 161);
		contentPane.add(panelId);
		panelId.setLayout(null);

		lblCodCliente = new JLabel("Cod. cliente:");
		lblCodCliente.setBounds(10, 11, 76, 14);
		panelId.add(lblCodCliente);

		txtCodCliente = new JTextField();
		txtCodCliente.setEditable(false);
		txtCodCliente.setBounds(84, 8, 55, 20);
		panelId.add(txtCodCliente);
		txtCodCliente.setColumns(10);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(176, 11, 55, 14);
		panelId.add(lblCliente);

		cbCliente = new JComboBox<String>();
		cbCliente.setBounds(241, 8, 311, 20);
		panelId.add(cbCliente);

		try {
			List<Cliente> clientes = new ControlerCliente().listCliente();
			for (Cliente cliente : clientes) {
				cbCliente.addItem(cliente.getNome());
			}
			cbCliente.setSelectedItem(null);
			AutoCompletion.enable(cbCliente);
			cbCliente.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int i = cbCliente.getSelectedIndex();
					txtCodCliente.setText(String.valueOf(clientes.get(i).getId()));
					txtBairro.setText(clientes.get(i).getBairro());
					txtCidade.setText(clientes.get(i).getCidade());
					txtEndereco.setText(clientes.get(i).getRua());
					txtNumero.setText(String.valueOf(clientes.get(i).getNumero()));
					txtUf.setText(clientes.get(i).getUf());
					txtContato.setText(String.valueOf(clientes.get(i).getContato()));
				}
			});

			lblEndereo = new JLabel("Endereço:");
			lblEndereo.setBounds(10, 69, 60, 14);
			panelId.add(lblEndereo);

			txtEndereco = new JTextField();
			txtEndereco.setEditable(false);
			txtEndereco.setBounds(84, 66, 299, 20);
			panelId.add(txtEndereco);
			txtEndereco.setColumns(10);

			lblNumero = new JLabel("Numero:");
			lblNumero.setBounds(405, 69, 55, 14);
			panelId.add(lblNumero);

			txtNumero = new JTextField();
			txtNumero.setEditable(false);
			txtNumero.setBounds(466, 66, 68, 20);
			panelId.add(txtNumero);
			txtNumero.setColumns(10);

			lblBairro = new JLabel("Bairro:");
			lblBairro.setBounds(10, 94, 46, 14);
			panelId.add(lblBairro);

			txtBairro = new JTextField();
			txtBairro.setEditable(false);
			txtBairro.setBounds(66, 91, 223, 20);
			panelId.add(txtBairro);
			txtBairro.setColumns(10);

			lblCidade = new JLabel("Cidade:");
			lblCidade.setBounds(323, 94, 60, 14);
			panelId.add(lblCidade);

			txtCidade = new JTextField();
			txtCidade.setEditable(false);
			txtCidade.setBounds(379, 91, 117, 20);
			panelId.add(txtCidade);
			txtCidade.setColumns(10);

			lblUf = new JLabel("UF:");
			lblUf.setBounds(541, 94, 35, 14);
			panelId.add(lblUf);

			txtUf = new JTextField();
			txtUf.setEditable(false);
			txtUf.setBounds(574, 91, 55, 20);
			panelId.add(txtUf);
			txtUf.setColumns(10);

			separator = new JSeparator();
			separator.setBackground(new Color(0, 0, 128));
			separator.setOrientation(SwingConstants.VERTICAL);
			separator.setBounds(691, 8, 2, 142);
			panelId.add(separator);

			lblCodVendedor = new JLabel("Cod. vendedor: ");
			lblCodVendedor.setBounds(703, 69, 91, 14);
			panelId.add(lblCodVendedor);

			txtCodVendedor = new JTextField();
			txtCodVendedor.setEditable(false);
			txtCodVendedor.setBounds(793, 66, 55, 20);
			panelId.add(txtCodVendedor);
			txtCodVendedor.setColumns(10);

			lblVendedor = new JLabel("Vendedor:");
			lblVendedor.setBounds(889, 69, 60, 14);
			panelId.add(lblVendedor);

			cbVendedor = new JComboBox<String>();
			cbVendedor.setBounds(959, 66, 289, 20);
			panelId.add(cbVendedor);

			List<Usuario> usuarios = new ControlerUsuario().listarUsuario();
			for (Usuario usuario : usuarios) {
				cbVendedor.addItem(usuario.getNome());
			}
			cbVendedor.setSelectedItem(null);
			AutoCompletion.enable(cbVendedor);

			JLabel lblCpf = new JLabel("CPF:");
			lblCpf.setBounds(10, 39, 35, 14);
			panelId.add(lblCpf);

			txtCpf = new JDocumentFormatedField().getCpf();
			txtCpf.setEditable(false);
			txtCpf.setBounds(55, 36, 129, 20);
			panelId.add(txtCpf);

			lblContato = new JLabel("Contato:");
			lblContato.setBounds(10, 125, 60, 14);
			panelId.add(lblContato);

			txtContato = new JDocumentFormatedField().getTel();
			txtContato.setEditable(false);
			txtContato.setBounds(84, 122, 117, 20);
			panelId.add(txtContato);
			txtContato.setColumns(10);
			txtCpf.setColumns(10);

			cbVendedor.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					int id = usuarios.get(cbVendedor.getSelectedIndex()).getId();
					txtCodVendedor.setText(String.valueOf(id));
				}
			});

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes");
			Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes");
			Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro na abertura ou escrita do arquivo, veja o log para mais detalhes");
			Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
		} catch (NenhumUsuCadException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			Log.gravaLog("| ORCAMENTO | " + e1.getMessage());
		}

	}

	public void escutaComboMerc() {
		cbId.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbId.isPopupVisible()) {
					if (cbId.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbId.getSelectedItem().equals(merc.getId())) {
								cbMercRef.setSelectedItem(merc.getCodBarras());
								cbMercDesc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbMercRef.setSelectedItem("");
						cbMercDesc.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});

		cbMercRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercRef.isPopupVisible()) {

					if (cbMercRef.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbMercRef.getSelectedItem().equals(String.valueOf(merc.getCodBarras()))) {
								cbId.setSelectedItem(merc.getId());
								cbMercDesc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbId.setSelectedItem("");
						cbMercDesc.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		cbMercDesc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercDesc.isPopupVisible()) {

					if (cbMercDesc.getSelectedItem() != "") {
						for (Mercadoria merc : mercList) {
							if (cbMercDesc.getSelectedItem().equals(merc.getMercadoria())) {
								cbId.setSelectedItem(merc.getId());
								cbMercRef.setSelectedItem(merc.getCodBarras());
							}
						}
					} else {
						cbId.setSelectedItem("");
						cbMercRef.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	private void salvarOrcamento() {
		int codCliente = (txtCodCliente.getText().equals("")) ? 0 : Integer.parseInt(txtCodCliente.getText());
		int codVendedor = (txtCodVendedor.getText().equals("")) ? 0 : Integer.parseInt(txtCodVendedor.getText());

		if (codCliente > 0 && codVendedor > 0 && table.getRowCount() > 0) {

			try {
				Orcamento orcamento = new Orcamento();
				Cliente cliente = new Cliente();
				Usuario usuario = new Usuario();
				Mercadoria mercadoria;
				List<Mercadoria> mercadorias = new ArrayList<Mercadoria>();
				cliente.setId(Integer.parseInt(txtCodCliente.getText()));
				usuario.setId(Integer.parseInt(txtCodVendedor.getText()));
				for (int i = 0; i < table.getRowCount(); i++) {
					mercadoria = new Mercadoria();
					mercadoria.setId(Integer.parseInt(table.getValueAt(i, 0).toString()));
					mercadoria.setPrecoVenda(
							Float.parseFloat(table.getValueAt(i, 3).toString().replace(",", ".").replace("R$", "")));
					mercadoria.setEstoque(Float.parseFloat(table.getValueAt(i, 4).toString()));
					valTotal += Float.parseFloat(table.getValueAt(i, 5).toString());

					mercadorias.add(mercadoria);
				}
				orcamento.setId(id);
				orcamento.setCliente(cliente);
				orcamento.setUsuario(usuario);
				orcamento.setMercadorias(mercadorias);
				orcamento.setValTotal(valTotal);
				new ControlerOrcamento().salvarOrcamento(orcamento);
				JOptionPane.showMessageDialog(null, "Orçamento salvo com sucesso!");
				chckbxSalvo.setSelected(true);
				cbCliente.setEnabled(false);
				cbVendedor.setEnabled(false);
				cbId.setEnabled(false);
				cbMercDesc.setEnabled(false);
				cbMercRef.setEnabled(false);
				btnSalvar.setEnabled(false);
				btnAdd.setEnabled(false);
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes");
				Log.gravaLog("| TelaOrcamento |" + e1.getMessage());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes");
				Log.gravaLog("| TelaOrcamento |" + e1.getMessage());
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo, veja o log para mais detalhes");
				Log.gravaLog("| TelaOrcamento |" + e1.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Verifique se o cliente e o vendedor estão selecionados, e se existe alguma mercadoria lançada no orçamento. \nOBS: O cliente CONSUMIDOR e o vendedor SERTSOFT não podem ser utilizados!",
					"INFORMAÇÃO", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE");
		escback.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JRootPane enter = getRootPane();
		enter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");
		enter.getRootPane().getActionMap().put("ENTER", new AbstractAction("ENTER") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				btnAdd.doClick();
			}
		});

		JRootPane delete = getRootPane();
		delete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"DELETE");
		delete.getRootPane().getActionMap().put("DELETE", new AbstractAction("DELETE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					valTotal -= Float.parseFloat(
							table.getValueAt(table.getSelectedRow(), 5).toString().replace("R$", "").replace(",", "."));
					lblValorTotalR.setText("Valor total: R$ " + String.format("%.2f", valTotal));
					modelo.removeRow(table.getSelectedRow());
					table.setModel(modelo);
					table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
				} else {
					JOptionPane.showMessageDialog(null, "Não há nenhuma mercadoria selecionada", "AVISO",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}
}