package com.sert.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Cliente;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaSemEstoqueException;
import com.sert.exceptions.MercadoriaSemPrecoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.opcoes.OpcPontoDeVenda;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.SystemColor;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class PontoDeVenda extends JDialog implements FocusListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelMother;
	private JPanel panelMenu;
	private JPanel panelId;
	private JPanel panelValue;

	private static JNumberField txtCodBarras;
	private static JTextField txtQuant;
	private static JTable prodVenda;

	private JLabel lblCodDeBarras;
	private JLabel lblQuantidade;
	private JLabel lblFSelecionarCliente;
	private JLabel lblFPesquisar;
	private JLabel lblFFechar;
	private JLabel lblFCancelarItem;
	private JLabel lblFCancelarVenda;
	private static JLabel lblTotal;
	private JLabel lblOperador;
	private static JLabel lblStatus;
	private JLabel lblCliente;
	private JLabel lblCpf;

	private JScrollPane spProdutos;
	private static DefaultTableModel modelo;

	static float quantidade;
	static float precoMerc;
	static float precoTotal;
	static float total;

	private static ControlerVenda controlerVenda;

	private static int item;
	private static int idCliente = 0;
	private static String nomeCliente;
	private JSeparator separator_1;
	private Cliente cliente;
	private JLabel lblFLiberar;
	private JLabel lblFFunes;
	private JLabel lblFAtualizar;
	private JSeparator separator;
	private JPanel panelAguarde;
	private JLabel label;
	private JLabel lblAguarde;
	private int linha = 0;
	private float totalConsig = 0;
	private JPanel panelMudarPreco;
	public static boolean origemConsig = false;
	private JNumberFormatField txtNovoValor;
	private JLabel lblR;
	private JLabel lblNomeMerc;
	private JButton btnConfirmar;
	private JButton btnFech;
	private JLabel lblQnt;
	private JTextField txtQnt;
	private JSeparator separator_3;

	public PontoDeVenda() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		setModal(true);
		setUndecorated(true);
		
		idCliente = 0;

		contentPane = new JPanel();
		setBounds(0, 0, screenSize.width - 10, screenSize.height - 40);
		setLocationRelativeTo(null);
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelMudarPreco = new JPanel();
		panelMudarPreco.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		panelMudarPreco.setBounds(356, 164, 348, 218);
		contentPane.add(panelMudarPreco);
		panelMudarPreco.setVisible(false);
		panelMudarPreco.setLayout(null);
		
		panelAguarde = new JPanel();
		panelAguarde.setBounds(566, 270, 223, 187);
		// contentPane.add(panelAguarde);
		panelAguarde.setVisible(false);
		panelAguarde.setLayout(null);

		label = new JLabel("");
		label.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/load.gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 223, 187);
		panelAguarde.add(label);

		lblAguarde = new JLabel("Aguarde...");
		lblAguarde.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(0, 162, 223, 25);
		panelAguarde.add(lblAguarde);

		panelMother = new JPanel();
		panelMother.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		panelMother.setBackground(new Color(0, 0, 128));
		panelMother.setBounds(10, 11, getWidth() - 20, getHeight() - 20);
		contentPane.add(panelMother);
		panelMother.setLayout(null);

		lblCodDeBarras = new JLabel("Cod. de barras:");
		lblCodDeBarras.setForeground(new Color(255, 255, 0));
		lblCodDeBarras.setBounds(58, 24, 109, 14);
		lblCodDeBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelMother.add(lblCodDeBarras);

		txtCodBarras = new JNumberField();
		txtCodBarras.setForeground(new Color(255, 255, 255));
		txtCodBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCodBarras.setBackground(new Color(0, 0, 128));
		txtCodBarras.setBounds(177, 21, 138, 20);
		txtCodBarras.setBorder(null);
		txtCodBarras.requestFocusInWindow();
		panelMother.add(txtCodBarras);
		txtCodBarras.addFocusListener(this);

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(new Color(255, 255, 0));
		lblQuantidade.setBounds(379, 24, 86, 25);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelMother.add(lblQuantidade);

		txtQuant = new JTextField();
		txtQuant.setForeground(new Color(255, 255, 255));
		txtQuant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtQuant.setBackground(new Color(0, 0, 128));
		txtQuant.setBounds(475, 26, 86, 20);
		txtQuant.setEditable(false);
		txtQuant.setBorder(null);
		txtQuant.setText(String.valueOf(1));
		panelMother.add(txtQuant);
		txtQuant.setColumns(10);

		panelMenu = new JPanel();
		panelMenu.setForeground(new Color(255, 255, 0));
		panelMenu.setBounds(10, 582, 1020, 114);
		panelMenu.setBackground(new Color(0, 0, 128));
		panelMenu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Legenda do caixa",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelMother.add(panelMenu);
		panelMenu.setLayout(null);

		lblFSelecionarCliente = new JLabel("F1 - Selecionar Cliente");
		lblFSelecionarCliente.setForeground(new Color(255, 255, 0));
		lblFSelecionarCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFSelecionarCliente.setBounds(148, 18, 144, 14);
		panelMenu.add(lblFSelecionarCliente);

		lblFPesquisar = new JLabel("F2 - Pesquisar mercadoria");
		lblFPesquisar.setForeground(new Color(255, 255, 0));
		lblFPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFPesquisar.setBounds(148, 50, 174, 14);
		panelMenu.add(lblFPesquisar);

		lblFFechar = new JLabel("F4 - Fechar venda");
		lblFFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFFechar.setForeground(new Color(255, 255, 0));
		lblFFechar.setBounds(148, 82, 123, 14);
		panelMenu.add(lblFFechar);

		lblFCancelarItem = new JLabel("F9 - Cancelar Item");
		lblFCancelarItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarItem.setForeground(new Color(255, 255, 0));
		lblFCancelarItem.setBounds(748, 18, 136, 14);
		panelMenu.add(lblFCancelarItem);

		lblFCancelarVenda = new JLabel("F11 - Cancelar Venda");
		lblFCancelarVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarVenda.setForeground(new Color(255, 255, 0));
		lblFCancelarVenda.setBounds(748, 82, 144, 14);
		panelMenu.add(lblFCancelarVenda);

		lblFLiberar = new JLabel("F7 - Liberar Caixa");
		lblFLiberar.setForeground(new Color(255, 255, 0));
		lblFLiberar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFLiberar.setBounds(440, 82, 136, 14);
		panelMenu.add(lblFLiberar);

		lblFFunes = new JLabel("F10 - Funções");
		lblFFunes.setForeground(Color.YELLOW);
		lblFFunes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFFunes.setBounds(748, 50, 123, 14);
		panelMenu.add(lblFFunes);

		lblFAtualizar = new JLabel("F5 - Atualizar cadastros");
		lblFAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFAtualizar.setForeground(Color.YELLOW);
		lblFAtualizar.setBounds(440, 16, 160, 19);
		panelMenu.add(lblFAtualizar);

		JLabel lblFMudar = new JLabel("F6 - Mudar preço");
		lblFMudar.setForeground(Color.YELLOW);
		lblFMudar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFMudar.setBounds(440, 48, 123, 19);
		panelMenu.add(lblFMudar);

		panelValue = new JPanel();
		panelValue.setBounds(panelMother.getX() + 1030, 57, 280, 511);
		panelValue.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panelMother.add(panelValue);
		panelValue.setLayout(null);

		lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setBounds(10, 456, 260, 44);
		panelValue.add(lblTotal);

		lblOperador = new JLabel("Operador: " + UsuLogado.getNome());
		lblOperador.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblOperador.setBounds(10, 11, 260, 20);
		panelValue.add(lblOperador);

		lblStatus = new JLabel("Status: Caixa Livre");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStatus.setBounds(10, 42, 260, 20);
		panelValue.add(lblStatus);

		panelId = new JPanel();
		panelId.setBounds(1040, 579, 280, 117);
		panelId.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panelMother.add(panelId);
		panelId.setLayout(null);

		lblCliente = new JLabel("Cliente: CONSUMIDOR");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 11, 260, 14);
		panelId.add(lblCliente);

		lblCpf = new JLabel("CPF: 0");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(10, 47, 260, 14);
		panelId.add(lblCpf);

		spProdutos = new JScrollPane();
		spProdutos.setBounds(10, 60, 1020, 511);
		panelMother.add(spProdutos);

		prodVenda = new JTable();
		prodVenda.getTableHeader().setReorderingAllowed(false);
		prodVenda.setBorder(new LineBorder(new Color(41, 171, 226)));
		spProdutos.setViewportView(prodVenda);
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		prodVenda.setModel(modelo);

		separator = new JSeparator();
		separator.setBounds(176, 44, 139, 5);
		panelMother.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBounds(475, 47, 86, 2);
		panelMother.add(separator_1);

		modelo.addColumn("Item");
		modelo.addColumn("Cod.");
		modelo.addColumn("Cód. Barras");
		modelo.addColumn("Nome");
		modelo.addColumn("Quant.");
		modelo.addColumn("Valor Unit.");
		modelo.addColumn("Valor Total");
		modelo.addColumn("Valor Compra");
		prodVenda.getColumnModel().getColumn(2).setPreferredWidth(200);
		prodVenda.getColumnModel().getColumn(3).setPreferredWidth(800);
		prodVenda.getColumnModel().getColumn(7).setMinWidth(0);
		prodVenda.getColumnModel().getColumn(7).setMaxWidth(0);

		txtCodBarras.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case (KeyEvent.VK_F1):
					ListarCliente list = new ListarCliente(1);
					list.setVisible(true);
					cliente = list.selecionarCliente();
					idCliente = cliente.getId();
					nomeCliente = cliente.getNome();
					lblCliente.setText("Cliente: " + cliente.getNome());
					lblCpf.setText("CPF: " + cliente.getCpf());
					break;
				case (KeyEvent.VK_F2):
					new PesqMercVenda().setVisible(true);
					break;
				case (KeyEvent.VK_F4):
					fecharVenda();
					break;
				case (KeyEvent.VK_F5):
					atualizarCad();
					break;
				case (KeyEvent.VK_F6):
					if (prodVenda.getSelectedRow() > -1) {
						mudarPrecoItem();
					} else {
						JOptionPane.showMessageDialog(null, "Selecione uma mercadoria antes");
					}
					break;
				case (KeyEvent.VK_F7):
					liberarCaixa();
					break;
				case (KeyEvent.VK_F9):
					cancelarItem();
					break;
				case (KeyEvent.VK_F10):
					new OpcPontoDeVenda().setVisible(true);
					break;
				case (KeyEvent.VK_F11):
					cancelarVenda();
					break;
				case (KeyEvent.VK_MULTIPLY):
					if (txtCodBarras.getText().equals("")) {
						txtQuant.setText("1");
					} else {
						txtQuant.setText(txtCodBarras.getText());
						txtCodBarras.setText("");
					}
					break;
				case (KeyEvent.VK_ENTER):
					adicionarItem();
					break;
				}
			}
		});
	}

	public void cancelarVenda() {
		int resposta = JOptionPane.showConfirmDialog(null, "Deseja cancelar esta venda ?", "CANCELAMENTO",
				JOptionPane.YES_NO_OPTION);

		if (resposta == JOptionPane.YES_OPTION) {
			repor();
			total = 0;
			item = 0;
			dispose();
		}
	}

	public void cancelarItem() {
		if (prodVenda.getSelectedRow() >= 0) {
			for (int i = prodVenda.getSelectedRow(); i < prodVenda.getRowCount(); i++) {
				item = Integer.parseInt(prodVenda.getValueAt(i, 0).toString()) - 1;
				prodVenda.setValueAt(item, i, 0);
			}
			total = total - Float
					.parseFloat(prodVenda.getValueAt(prodVenda.getSelectedRow(), 6).toString().replace(",", "."));
			lblTotal.setText("TOTAL: R$ " + String.format("%.2f", total));
			repor();
			modelo.removeRow(prodVenda.getSelectedRow());
			item = prodVenda.getRowCount();
			prodVenda.setModel(modelo);

			if (prodVenda.getRowCount() <= 0) {
				lblStatus.setText("Status: Caixa Livre");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um item a ser cancelado", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void adicionarItem() {
		try {
			quantidade = Float.parseFloat(txtQuant.getText().replace(",", "."));
			Mercadoria merc = new ControlerVenda().consultaMercVenda(Long.parseLong(txtCodBarras.getText()),
					quantidade);
			if (merc == null) {
				txtCodBarras.setText(null);
				txtQuant.setText("1");
				JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				precoMerc = merc.getPrecoVenda();
				precoTotal = quantidade * precoMerc;

				modelo.addRow(new Object[] { ++item, merc.getId(), merc.getCodBarras(), merc.getMercadoria(),
						quantidade, String.format("%.2f", precoMerc), String.format("%.2f", precoTotal),
						merc.getPrecoCompra() });
				txtCodBarras.setText(null);
				txtQuant.setText("1");
				lblStatus.setText("Status: Venda em andamento");
				total += precoTotal;
				lblTotal.setText("TOTAL: R$ " + String.format("%.2f", total));

			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor invalido", "AVISO", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (MercadoriaSemEstoqueException e) {
			JOptionPane.showMessageDialog(null, "A quantidade a ser vendida está maior do que o estoque", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			txtCodBarras.setText(null);
			txtQuant.setText("1");
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, contate o suporte para auxilio", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo, contate o suporte para auxilio", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e) {
			txtCodBarras.setText(null);
			txtQuant.setText("1");
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		} catch (MercadoriaSemPrecoException e) {
			txtCodBarras.setText(null);
			txtQuant.setText("1");
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
			Log.gravaLog("| PONTO DE VENDA| " + e.getMessage());
		}
	}

	public void liberarCaixa() {
		if (prodVenda.getRowCount() > 0) {
			while (prodVenda.getRowCount() > 0) {
				modelo.removeRow(0);
			}
			repor();
			prodVenda.setModel(modelo);
			lblTotal.setText("TOTAL:");
			lblStatus.setText("Status: Caixa Livre");
			item = 0;
			total = 0;
			JOptionPane.showMessageDialog(null, "Caixa liberado com sucesso", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "O caixa já está livre", "Aviso", JOptionPane.WARNING_MESSAGE);
		}

	}

	public void fecharVenda() {
		if (prodVenda.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Vendas sem mercadoria não podem ser fechadas", "VENDA ZERADA",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			try {
				controlerVenda = new ControlerVenda();
				Mercadoria merc;
				List<Mercadoria> mercFech = new ArrayList<>();

				for (int i = 0; i < prodVenda.getRowCount(); i++) {
					merc = new Mercadoria();
					merc.setId(Integer.parseInt(prodVenda.getValueAt(i, 1).toString()));
					merc.setCodBarras(Long.parseLong(prodVenda.getValueAt(i, 2).toString()));
					merc.setMercadoria(prodVenda.getValueAt(i, 3).toString());
					merc.setEstoque(Float.parseFloat(prodVenda.getValueAt(i, 4).toString()));
					merc.setPrecoVenda(Float.parseFloat(prodVenda.getValueAt(i, 5).toString().replace(",", ".")));
					merc.setPrecoCompra(Float.parseFloat(prodVenda.getValueAt(i, 7).toString().replace(",", "."))
							* merc.getEstoque());
					mercFech.add(merc);
				}
				Venda venda = new Venda(controlerVenda.getIdVenda(), UsuLogado.getId(), UsuLogado.getNome(), idCliente,
						nomeCliente, new JDateField().getTimeStamp(), mercFech,
						Float.parseFloat(String.format("%.2f", total).replace(",", ".")), 0, 0, 0, 0, 0, 0, 0, 0, 0);
				new PontoDeVendaFecharVenda(venda).setVisible(true);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (NenhumaMercadoriaCadastradaException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

	public void repor() {
		List<Mercadoria> tableMercRepor = new ArrayList<>();
		Mercadoria mercadoria;
		if (prodVenda.getSelectedRow() > 0) {
			mercadoria = new Mercadoria();
			mercadoria.setId(Integer.parseInt(prodVenda.getValueAt(prodVenda.getSelectedRow(), 1).toString()));
			mercadoria.setEstoque(Float.parseFloat(prodVenda.getValueAt(prodVenda.getSelectedRow(), 4).toString()));
			tableMercRepor.add(mercadoria);
		} else {
			for (int i = 0; i < prodVenda.getRowCount(); i++) {
				mercadoria = new Mercadoria();
				mercadoria.setId(Integer.parseInt(prodVenda.getValueAt(i, 1).toString()));
				mercadoria.setEstoque(Float.parseFloat(prodVenda.getValueAt(i, 4).toString()));
				tableMercRepor.add(mercadoria);
			}
		}
		try {
			new ControlerVenda().cancelVenda(tableMercRepor);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NenhumaMercadoriaCadastradaException e) {
			e.printStackTrace();
		}
	}

	private void mudarPrecoItem() {
		panelMudarPreco.removeAll();
		panelMudarPreco.setVisible(true);

		txtCodBarras.removeFocusListener(this);

		lblNomeMerc = new JLabel(prodVenda.getValueAt(prodVenda.getSelectedRow(), 3).toString());
		lblNomeMerc.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNomeMerc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeMerc.setBounds(10, 44, 328, 50);
		panelMudarPreco.add(lblNomeMerc);

		lblR = new JLabel("R$");
		lblR.setBounds(13, 108, 32, 14);
		panelMudarPreco.add(lblR);

		txtNovoValor = new JNumberFormatField(new DecimalFormat("0.00"));
		txtNovoValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtNovoValor.setBorder(null);
		txtNovoValor.setBackground(new Color(240, 240, 240));
		txtNovoValor.setBounds(35, 105, 94, 20);
		panelMudarPreco.add(txtNovoValor);
		txtNovoValor.setColumns(10);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 128));
		separator_2.setBounds(10, 125, 119, 2);
		panelMudarPreco.add(separator_2);

		btnFech = new JButton("");
		btnFech.setIcon(new ImageIcon(PontoDeVenda.class.getResource("/com/sert/img/btnX.png")));
		btnFech.setBounds(310, 11, 28, 28);
		panelMudarPreco.add(btnFech);
		btnFech.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtNovoValor.setText("0,00");
				txtQnt.setText("");
				panelMudarPreco.setVisible(false);
				txtCodBarras.addFocusListener(PontoDeVenda.this);
			}
		});

		lblQnt = new JLabel("Quant.");
		lblQnt.setBounds(194, 106, 47, 14);
		panelMudarPreco.add(lblQnt);

		txtQnt = new JNumberField();
		txtQnt.setColumns(10);
		txtQnt.setBorder(null);
		txtQnt.setBackground(SystemColor.menu);
		txtQnt.setBounds(236, 103, 89, 20);
		panelMudarPreco.add(txtQnt);

		separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 128));
		separator_3.setBounds(194, 123, 131, 2);
		panelMudarPreco.add(separator_3);

		btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(PontoDeVenda.class.getResource("/com/sert/img/btnConfirmar.png")));
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setOpaque(false);
		btnConfirmar.setBounds(135, 184, 89, 23);
		panelMudarPreco.add(btnConfirmar);
		btnConfirmar.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				btnConfirmar.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnConfirmar.png")));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnConfirmar.setIcon(new ImageIcon(Entrada.class.getResource("/com/sert/img/btnConfirmarPress.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int linha = prodVenda.getSelectedRow();
				
				double valAnt = Double.parseDouble(prodVenda.getValueAt(linha, 5).toString().replace(",", "."));
				double quantAnt = Double.parseDouble(prodVenda.getValueAt(linha, 4).toString().replace(",", "."));
				double novaQuant = txtQnt.getText().equals("") ? quantAnt : Double.parseDouble(txtQnt.getText().replace(",", "."));
				double novoVal = txtNovoValor.getText().equals("0,00") ? valAnt
						: Double.parseDouble(txtNovoValor.getText().replace(",", "."));
				
				double valTotal = novaQuant * novoVal;
				System.out.println("Valor 0?"+txtNovoValor.getText());
				System.out.println("Quant 0?"+txtQnt.getText());
				
				prodVenda.setValueAt(novaQuant, linha, 4);
				prodVenda.setValueAt(String.format("%.2f", novoVal), linha, 5);
				prodVenda.setValueAt(String.format("%.2f", valTotal), linha, 6);

				panelAguarde.setVisible(true);
				total = 0;
				for (int i = 0; i < prodVenda.getRowCount(); i++) {

					PontoDeVenda.total += Float.parseFloat(prodVenda.getValueAt(i, 6).toString().replace(",", "."));
				}

				panelAguarde.setVisible(false);

				lblTotal.setText("TOTAL: R$ " + String.format("%.2f", total));

				btnFech.doClick();
				novaQuant = 0;
				novoVal = 0;
				valAnt = 0;
				quantAnt = 0;
			}
		});
	}

	public static void liberarCaixaVenda() {
		if (prodVenda.getRowCount() > 0) {
			while (prodVenda.getRowCount() > 0) {
				modelo.removeRow(0);
			}
			prodVenda.setModel(modelo);
			lblTotal.setText("TOTAL:");
			lblStatus.setText("Status: Caixa Livre");
			item = 0;
			total = 0;
		}
	}

	public static void addMercPesq(long codBarras) {
		txtCodBarras.setText(String.valueOf(codBarras));
		adicionarItem();
	}

	public void addMercConsig(long codBarras, float qntd, float precoVenda) {
		origemConsig = true;
		totalConsig += (precoVenda * qntd);

		txtCodBarras.setText(String.valueOf(codBarras));
		txtQuant.setText(String.valueOf(qntd));
		adicionarItem();
		prodVenda.setValueAt(String.format("%.2f", precoVenda), linha, 5);
		prodVenda.setValueAt(String.format("%.2f", precoVenda * qntd), linha, 6);
		linha++;
		lblTotal.setText("TOTAL: R$ 1" + String.format("%.2f", totalConsig));

		total = totalConsig;
	}

	public void addConsig(String nome, String id, String cpf) {
		lblCliente.setText("Cliente: " + nome);
		lblCpf.setText("CPF: " + cpf);
		idCliente = Integer.parseInt(id);
	}

	private void atualizarCad() {
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		panelAguarde.setVisible(true);
		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				new ControlerVenda().atualizarCadastros();
				ControlerVenda.mercadorias = new ControlerMercadoria().listarMercadorias();
				ListarMercadorias.setPreencheTable(ControlerVenda.mercadorias);
				PesqMercVenda.setPreencheTable(ControlerVenda.mercadorias);
				return null;
			}

			protected void done() {
				contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				panelAguarde.setVisible(false);
				JOptionPane.showMessageDialog(null, "Cadastros atualizados com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			};

		}.execute();
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == txtCodBarras) {
			txtCodBarras.requestFocusInWindow();
		}
	}
}