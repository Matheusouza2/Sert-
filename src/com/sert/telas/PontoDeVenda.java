package com.sert.telas;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerVenda;
import com.sert.controler.JDateField;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.MercadoriaSemEstoqueException;
import com.sert.exceptions.MercadoriaSemPrecoException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class PontoDeVenda extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelMother;
	private JPanel panelMenu;
	private JPanel panelId;
	private JPanel panelValue;

	private JNumberField txtCodBarras;
	private JTextField txtQuant;
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

	float quantidade;
	float precoMerc;
	float precoTotal;
	static float total;

	private static ControlerVenda controlerVenda;

	private static int item;
	private static int idCliente = 0;
	private JLabel lblNewLabel;

	public PontoDeVenda() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1350, 730);
		setLocationRelativeTo(null);
		setTitle("Checkout");
		setModal(true);
		setUndecorated(true);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelMother = new JPanel();
		panelMother.setBackground(new Color(0, 0, 128));
		panelMother.setBounds(10, 11, 1330, 707);
		contentPane.add(panelMother);
		panelMother.setLayout(null);

		lblCodDeBarras = new JLabel("Cod. de barras:");
		lblCodDeBarras.setForeground(new Color(255, 255, 0));
		lblCodDeBarras.setBounds(58, 24, 109, 14);
		lblCodDeBarras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelMother.add(lblCodDeBarras);

		txtCodBarras = new JNumberField();
		txtCodBarras.setBounds(177, 21, 138, 20);
		txtCodBarras.requestFocusInWindow();
		panelMother.add(txtCodBarras);
		txtCodBarras.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				txtCodBarras.requestFocusInWindow();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setForeground(new Color(255, 255, 0));
		lblQuantidade.setBounds(379, 24, 86, 25);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelMother.add(lblQuantidade);

		txtQuant = new JTextField();
		txtQuant.setBounds(475, 26, 86, 20);
		txtQuant.setEditable(false);
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
		lblFSelecionarCliente.setBounds(246, 18, 144, 14);
		panelMenu.add(lblFSelecionarCliente);

		lblFPesquisar = new JLabel("F2 - Pesquisar mercadoria");
		lblFPesquisar.setForeground(new Color(255, 255, 0));
		lblFPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFPesquisar.setBounds(246, 50, 174, 14);
		panelMenu.add(lblFPesquisar);

		lblFFechar = new JLabel("F4 - Fechar venda");
		lblFFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFFechar.setForeground(new Color(255, 255, 0));
		lblFFechar.setBounds(246, 82, 123, 14);
		panelMenu.add(lblFFechar);

		lblFCancelarItem = new JLabel("F9 - Cancelar Item");
		lblFCancelarItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarItem.setForeground(new Color(255, 255, 0));
		lblFCancelarItem.setBounds(636, 50, 136, 14);
		panelMenu.add(lblFCancelarItem);

		lblFCancelarVenda = new JLabel("F11 - Cancelar Venda");
		lblFCancelarVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarVenda.setForeground(new Color(255, 255, 0));
		lblFCancelarVenda.setBounds(636, 82, 144, 14);
		panelMenu.add(lblFCancelarVenda);

		JLabel lblFLiberar = new JLabel("F7 - Liberar Caixa");
		lblFLiberar.setForeground(new Color(255, 255, 0));
		lblFLiberar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFLiberar.setBounds(636, 18, 136, 14);
		panelMenu.add(lblFLiberar);

		panelValue = new JPanel();
		panelValue.setBounds(1040, 60, 280, 511);
		panelValue.setBorder(new LineBorder(new Color(41, 171, 226)));
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
		panelId.setBorder(new LineBorder(new Color(41, 171, 226)));
		panelMother.add(panelId);
		panelId.setLayout(null);

		lblCliente = new JLabel("Cliente: Consumidor");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 11, 260, 14);
		panelId.add(lblCliente);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(10, 66, 260, 14);
		panelId.add(lblCpf);

		spProdutos = new JScrollPane();
		spProdutos.setBounds(10, 60, 1020, 511);
		panelMother.add(spProdutos);

		prodVenda = new JTable();
		prodVenda.setBorder(new LineBorder(new Color(41, 171, 226)));
		spProdutos.setViewportView(prodVenda);
		modelo = new DefaultTableModel();
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		prodVenda.setModel(modelo);

		lblNewLabel = new JLabel("F6 - Fechar caixa");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(1200, 0, 130, 14);
		panelMother.add(lblNewLabel);

		modelo.addColumn("Item");
		modelo.addColumn("Cod.");
		modelo.addColumn("Cód. Barras");
		modelo.addColumn("Nome");
		modelo.addColumn("Quant.");
		modelo.addColumn("Valor Unit.");
		modelo.addColumn("Valor Total");
		prodVenda.getColumnModel().getColumn(2).setPreferredWidth(200);
		prodVenda.getColumnModel().getColumn(3).setPreferredWidth(800);

		txtCodBarras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case (KeyEvent.VK_F1):
					// Selecionar o cliente
					break;
				case (KeyEvent.VK_F2):
					// Pesquisar a mercadoria
					break;
				case (KeyEvent.VK_F4):
					fecharVenda();
					break;
				case (KeyEvent.VK_F7):
					liberarCaixa();
					break;
				case (KeyEvent.VK_F9):
					cancelarItem();
					break;
				case (KeyEvent.VK_F11):
					cancelarVenda();
					break;
				case (KeyEvent.VK_MULTIPLY):
					txtQuant.setText(txtCodBarras.getText());
					txtCodBarras.setText("");
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
			total = total - Float.parseFloat(prodVenda.getValueAt(prodVenda.getSelectedRow(), 5).toString());
			lblTotal.setText("TOTAL: " + total);
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

	public void adicionarItem() {
		try {
			quantidade = Float.parseFloat(txtQuant.getText());
			Mercadoria merc = new ControlerVenda().consultaMercVenda(Long.parseLong(txtCodBarras.getText()),
					quantidade);
			if (merc == null) {
				txtCodBarras.setText(null);
				txtQuant.setText("1");
				JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				precoMerc = merc.getPrecoVenda();
				precoTotal = Float.parseFloat(txtQuant.getText()) * precoMerc;

				modelo.addRow(new Object[] { ++item, merc.getId(), merc.getCodBarras(), merc.getMercadoria(),
						quantidade, String.format("%.2f", precoMerc), String.format("%.2f", precoTotal) });
				txtCodBarras.setText(null);
				txtQuant.setText("1");
				lblStatus.setText("Status: Venda em andamento");
				total += precoTotal;
				lblTotal.setText("TOTAL: " + String.format("%.2f", total));

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MercadoriaSemEstoqueException e) {
			JOptionPane.showMessageDialog(null, "A quantidade a ser vendida está maior do que o estoque", "AVISO",
					JOptionPane.INFORMATION_MESSAGE);
			txtCodBarras.setText(null);
			txtQuant.setText("1");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
		} catch (NenhumaMercadoriaCadastradaException e) {
			txtCodBarras.setText(null);
			txtQuant.setText("1");
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
		} catch (MercadoriaSemPrecoException e) {
			txtCodBarras.setText(null);
			txtQuant.setText("1");
			JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
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
					mercFech.add(merc);
				}
				Venda venda = new Venda(controlerVenda.getIdVenda(), UsuLogado.getId(), "", idCliente, "",
						JDateField.getDateHoraStatic(), mercFech,
						Float.parseFloat(String.format("%.2f", total).replace(",", ".")), 0, 0, 0, 0);
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
}