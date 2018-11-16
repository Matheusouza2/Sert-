package com.sert.telas;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.MercadoriaNaoEncontradaException;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

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
	private JTable prodVenda;

	private JLabel lblCodDeBarras;
	private JLabel lblQuantidade;
	private JLabel lblFSelecionarCliente;
	private JLabel lblFPesquisar;
	private JLabel lblFFechar;
	private JLabel lblFCancelarItem;
	private JLabel lblFCancelarVenda;
	private JLabel lblTotal;
	private JLabel lblOperador;
	private JLabel lblStatus;
	private JLabel lblCliente;
	private JLabel lblCpf;

	private JScrollPane spProdutos;
	private DefaultTableModel modelo;

	private int item;

	public static void main(String[] args) {
		try {
			PontoDeVenda dialog = new PontoDeVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PontoDeVenda() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1350, 730);
		setLocationRelativeTo(null);
		setTitle("Checkout");
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelMother = new JPanel();
		panelMother.setBackground(new Color(255, 255, 0));
		panelMother.setBounds(10, 11, 1330, 707);
		contentPane.add(panelMother);
		panelMother.setLayout(null);

		lblCodDeBarras = new JLabel("Cod. de barras:");
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
			public void focusGained(FocusEvent arg0) {}
		});

		lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(379, 24, 86, 14);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelMother.add(lblQuantidade);

		txtQuant = new JTextField();
		txtQuant.setBounds(475, 21, 86, 20);
		txtQuant.setEditable(false);
		txtQuant.setText(String.valueOf(1));
		panelMother.add(txtQuant);
		txtQuant.setColumns(10);

		panelMenu = new JPanel();
		panelMenu.setBounds(10, 582, 1020, 114);
		panelMenu.setBackground(new Color(0, 0, 128));
		panelMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMother.add(panelMenu);
		panelMenu.setLayout(null);

		lblFSelecionarCliente = new JLabel("F1 - Selecionar Cliente");
		lblFSelecionarCliente.setForeground(Color.WHITE);
		lblFSelecionarCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFSelecionarCliente.setBounds(246, 18, 144, 14);
		panelMenu.add(lblFSelecionarCliente);

		lblFPesquisar = new JLabel("F2 - Pesquisar mercadoria");
		lblFPesquisar.setForeground(Color.WHITE);
		lblFPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFPesquisar.setBounds(246, 50, 174, 14);
		panelMenu.add(lblFPesquisar);

		lblFFechar = new JLabel("F4 - Fechar venda");
		lblFFechar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFFechar.setForeground(Color.WHITE);
		lblFFechar.setBounds(246, 82, 123, 14);
		panelMenu.add(lblFFechar);

		lblFCancelarItem = new JLabel("F9 - Cancelar Item");
		lblFCancelarItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarItem.setForeground(Color.WHITE);
		lblFCancelarItem.setBounds(636, 28, 136, 14);
		panelMenu.add(lblFCancelarItem);

		lblFCancelarVenda = new JLabel("F11 - Cancelar Venda");
		lblFCancelarVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFCancelarVenda.setForeground(Color.WHITE);
		lblFCancelarVenda.setBounds(636, 70, 144, 14);
		panelMenu.add(lblFCancelarVenda);

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

		lblOperador = new JLabel("Operador:");
		lblOperador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOperador.setBounds(10, 11, 260, 20);
		panelValue.add(lblOperador);

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(10, 42, 260, 20);
		panelValue.add(lblStatus);

		panelId = new JPanel();
		panelId.setBounds(1040, 579, 280, 117);
		panelId.setBorder(new LineBorder(new Color(41, 171, 226)));
		panelMother.add(panelId);
		panelId.setLayout(null);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCliente.setBounds(10, 11, 59, 14);
		panelId.add(lblCliente);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpf.setBounds(10, 66, 46, 14);
		panelId.add(lblCpf);

		spProdutos = new JScrollPane();
		spProdutos.setBounds(10, 60, 1020, 511);
		panelMother.add(spProdutos);

		prodVenda = new JTable();
		prodVenda.setBorder(new LineBorder(new Color(41, 171, 226)));
		spProdutos.setViewportView(prodVenda);
		modelo = new DefaultTableModel();
		prodVenda.setModel(modelo);

		modelo.addColumn("Item");
		modelo.addColumn("Cód. Barras");
		modelo.addColumn("Nome");
		modelo.addColumn("Quant.");
		modelo.addColumn("Valor Unit.");
		modelo.addColumn("Valor Total");
		prodVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
		prodVenda.getColumnModel().getColumn(2).setPreferredWidth(800);

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
					new PontoDeVendaFecharVenda((float) 150.10).setVisible(true);
					break;
				case (KeyEvent.VK_F9):
					// Cancelar item
					break;
				case (KeyEvent.VK_F11):
					dispose();
					break;
				case (KeyEvent.VK_MULTIPLY):
					txtQuant.setText(txtCodBarras.getText());
					txtCodBarras.setText("");
					break;
				case (KeyEvent.VK_ENTER):
					try {
						Mercadoria merc = new ControlerMercadoria().consultaMercadoria(Long.parseLong(txtCodBarras.getText()));
						modelo.addRow(new Object[] { ++item, merc.getCodBarras(), merc.getMercadoria(),
								txtQuant.getText(), merc.getPrecoVenda() });
						txtCodBarras.setText(null);
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
								JOptionPane.ERROR_MESSAGE);
						txtCodBarras.setText(null);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
								JOptionPane.ERROR_MESSAGE);
						txtCodBarras.setText(null);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro SQL",
								JOptionPane.ERROR_MESSAGE);
						txtCodBarras.setText(null);
					} catch (MercadoriaNaoEncontradaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "AVISO",
								JOptionPane.WARNING_MESSAGE);
						txtCodBarras.setText(null);
					}
					break;
				}
			}
		});
	}
}