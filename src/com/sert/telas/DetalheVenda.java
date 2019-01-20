package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.sert.entidades.Venda;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DetalheVenda extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblVendaDetalhada;
	private JButton btnX;
	private JPanel panelForm;
	private JLabel lblNumero;
	private JLabel lblVendedor;
	private JLabel lblCliente;
	private JPanel panelCabecalho;
	private JTextField txtNumVenda;
	private JTextField txtCliente;
	private JTextField txtVendedor;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JLabel lblAcrescimo;
	private JLabel lblDesconto;
	private JLabel lblTotal;
	private JLabel lblDinheiro;
	private JLabel lblCarto;
	private JTextField txtAcrescimo;
	private JTextField txtDesconto;
	private JTextField txtDinheiro;
	private JTextField txtCartao;
	private JPanel panelPagamentos;
	private JLabel lblTotal_1;
	private float total;
	private float totalGlobal;
	private JLabel lblTroco;
	private JTextField txtTroco;

	public DetalheVenda(Venda venda) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 642);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		panel.setBounds(0, 0, 834, 642);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblVendaDetalhada = new JLabel("venda detalhada");
		lblVendaDetalhada.setForeground(new Color(255, 255, 255));
		lblVendaDetalhada.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblVendaDetalhada.setBounds(301, 11, 231, 23);
		panel.add(lblVendaDetalhada);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(778, 11, 46, 23);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnX);

		panelCabecalho = new JPanel();
		panelCabecalho.setLayout(null);
		panelCabecalho.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelCabecalho.setBackground(Color.YELLOW);
		panelCabecalho.setBounds(10, 40, 814, 113);
		panel.add(panelCabecalho);

		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(10, 11, 63, 14);
		panelCabecalho.add(lblNumero);
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(10, 71, 46, 14);
		panelCabecalho.add(lblCliente);

		lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(485, 12, 63, 14);
		panelCabecalho.add(lblVendedor);

		txtNumVenda = new JTextField(String.valueOf(venda.getId()));
		txtNumVenda.setEditable(false);
		txtNumVenda.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNumVenda.setBounds(83, 9, 86, 20);
		panelCabecalho.add(txtNumVenda);
		txtNumVenda.setColumns(10);

		txtCliente = new JTextField(venda.getCliente().trim());
		txtCliente.setEditable(false);
		txtCliente.setBounds(66, 68, 326, 20);
		panelCabecalho.add(txtCliente);
		txtCliente.setColumns(10);

		txtVendedor = new JTextField(venda.getVendedor().trim());
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(558, 9, 234, 20);
		panelCabecalho.add(txtVendedor);
		txtVendedor.setColumns(10);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 164, 814, 384);
		panel.add(panelForm);
		panelForm.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 794, 330);
		panelForm.add(scrollPane);

		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("Código");
		tableModel.addColumn("Cod. barras");
		tableModel.addColumn("Descrição");
		tableModel.addColumn("Preço Un");
		tableModel.addColumn("Quant");
		tableModel.addColumn("Preço Total");

		table = new JTable();
		table.setModel(tableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(195);
		table.getColumnModel().getColumn(2).setPreferredWidth(730);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		scrollPane.setViewportView(table);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(704, 352, 100, 14);
		panelForm.add(lblTotal);
		
		panelPagamentos = new JPanel();
		panelPagamentos.setBounds(10, 559, 814, 72);
		panel.add(panelPagamentos);
		panelPagamentos.setLayout(null);
		
		lblAcrescimo = new JLabel("Acrescimo:");
		lblAcrescimo.setBounds(10, 14, 74, 14);
		panelPagamentos.add(lblAcrescimo);
		
		lblDesconto = new JLabel("Desconto:");
		lblDesconto.setBounds(10, 41, 62, 14);
		panelPagamentos.add(lblDesconto);
		
		lblDinheiro = new JLabel("Dinheiro:");
		lblDinheiro.setBounds(232, 14, 54, 14);
		panelPagamentos.add(lblDinheiro);
		
		lblCarto = new JLabel("Cartão:");
		lblCarto.setBounds(232, 41, 54, 14);
		panelPagamentos.add(lblCarto);
		
		txtAcrescimo = new JTextField("R$ "+String.format("%.2f", venda.getAcrescimo()));
		txtAcrescimo.setEditable(false);
		txtAcrescimo.setBounds(82, 11, 86, 20);
		panelPagamentos.add(txtAcrescimo);
		txtAcrescimo.setColumns(10);
		
		txtDesconto = new JTextField("R$ "+String.format("%.2f", venda.getDesconto()));
		txtDesconto.setEditable(false);
		txtDesconto.setBounds(82, 38, 86, 20);
		panelPagamentos.add(txtDesconto);
		txtDesconto.setColumns(10);
		
		txtDinheiro = new JTextField("R$ "+String.format("%.2f", venda.getValDInheiro()));
		txtDinheiro.setEditable(false);
		txtDinheiro.setBounds(296, 11, 86, 20);
		panelPagamentos.add(txtDinheiro);
		txtDinheiro.setColumns(10);
		
		txtCartao = new JTextField("R$ "+String.format("%.2f", venda.getValCartao()));
		txtCartao.setEditable(false);
		txtCartao.setBounds(296, 38, 86, 20);
		panelPagamentos.add(txtCartao);
		txtCartao.setColumns(10);
		
		lblTotal_1 = new JLabel();
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal_1.setBounds(604, 23, 172, 26);
		panelPagamentos.add(lblTotal_1);
		
		for (int i = 0; i < venda.getMercadorias().size(); i++) {
			tableModel.addRow(new Object[] { venda.getMercadorias().get(i).getId(),
					venda.getMercadorias().get(i).getCodBarras(), venda.getMercadorias().get(i).getMercadoria().trim(),
					"R$ " + String.format("%.2f", venda.getMercadorias().get(i).getPrecoVenda()),
					venda.getMercadorias().get(i).getEstoque(),
					"R$ " + String.format("%.2f", venda.getMercadorias().get(i).getPrecoVenda()
							* venda.getMercadorias().get(i).getEstoque()) });
			total += venda.getMercadorias().get(i).getPrecoVenda()
					* venda.getMercadorias().get(i).getEstoque();
		}
		totalGlobal = total + (venda.getAcrescimo() - venda.getDesconto());
		lblTotal.setText("Total: R$ "+String.format("%.2f", total));
		lblTotal_1.setText("Total: R$ "+String.format("%.2f", totalGlobal));
	
		
		lblTroco = new JLabel("Troco:");
		lblTroco.setBounds(408, 29, 46, 14);
		panelPagamentos.add(lblTroco);
		
		txtTroco = new JTextField("R$ "+String.format("%.2f", (venda.getValDInheiro() - totalGlobal) - venda.getValCartao()));
		txtTroco.setBounds(464, 28, 86, 20);
		txtTroco.setEditable(false);
		panelPagamentos.add(txtTroco);
		txtTroco.setColumns(10);
		
	}
}