package com.sert.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerVenda;
import com.sert.entidades.Venda;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.NenhumaVendaRalizadaException;
import com.sert.impressao.PrintableVenda;
import com.sert.tables.TableModelVenda;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.JSeparator;
import javax.swing.JTextField;

public class RelatorioVendas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tabMerc;
	private DefaultTableModel modelo;
	private JScrollPane spListaMerc;
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnDetalhar;
	private JLabel lblRelatorioVendas;
	private ControlerVenda controlerVenda;
	private JLabel label;
	private JButton btnCancelar;
	private JTextField textField;
	private JSeparator separator;
	private JLabel lblProcurar;
	private BasicEventList<Venda> vendas;
	private FilterList<Venda> textFilteredIssues;
	private JButton btnReimprimir;

	public RelatorioVendas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPanel.add(btnX);
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
		panelBtn.setBounds(10, 34, 814, 113);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		btnDetalhar = new JButton();
		btnDetalhar.setIcon(new ImageIcon(RelatorioVendas.class.getResource("/com/sert/img/btnDetalhar.png")));
		btnDetalhar.setBounds(109, 11, 89, 91);
		btnDetalhar.setBackground(new Color(0, 255, 0));
		btnDetalhar.setBorderPainted(false);
		panelBtn.add(btnDetalhar);
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabMerc.getSelectedRow() >= 0) {
					try {
						int idDetalhar = Integer.parseInt(String.valueOf(tabMerc.getValueAt(tabMerc.getSelectedRow(), 0)));
						Venda vendaDetalhe = new ControlerVenda().imprimirVenda(idDetalhar);
						new DetalheVenda(vendaDetalhe).setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NenhumaMercadoriaCadastradaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma venda para detalhar", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		btnCancelar = new JButton();
		btnCancelar.setIcon(new ImageIcon(RelatorioVendas.class.getResource("/com/sert/img/btnCancelar.png")));
		btnCancelar.setEnabled(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(208, 11, 89, 91);
		panelBtn.add(btnCancelar);
		
		separator = new JSeparator();
		separator.setToolTipText("");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLUE);
		separator.setBounds(474, 0, 2, 116);
		panelBtn.add(separator);
		
		lblProcurar = new JLabel("procurar");
		lblProcurar.setFont(new Font("Gtek Technology", Font.BOLD, 11));
		lblProcurar.setBounds(499, 49, 98, 14);
		panelBtn.add(lblProcurar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(607, 46, 197, 20);
		panelBtn.add(textField);
		

		spListaMerc = new JScrollPane();
		spListaMerc.setBounds(10, 158, 814, 396);
		spListaMerc.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(spListaMerc);
		tabMerc = new JTable();
		tabMerc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabMerc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spListaMerc.setViewportView(tabMerc);
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabMerc.setModel(modelo);

		lblRelatorioVendas = new JLabel("relatorio de vendas");
		lblRelatorioVendas.setForeground(new Color(255, 255, 255));
		lblRelatorioVendas.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblRelatorioVendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorioVendas.setBounds(280, 0, 273, 35);
		contentPanel.add(lblRelatorioVendas);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 565, 814, 14);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(0, 0, 482, 14);
		panel.add(lblTotal);
		
		label = new JLabel("");
		label.setBounds(734, 0, 80, 14);
		panel.add(label);

		vendas = new BasicEventList<Venda>();
		MatcherEditor<Venda> textMatcherEditor = new TextComponentMatcherEditor<Venda>(textField,
				new Venda());
		
		btnReimprimir = new JButton();
		btnReimprimir.setIcon(new ImageIcon(RelatorioVendas.class.getResource("/com/sert/img/btnImprimir.png")));
		btnReimprimir.setBorderPainted(false);
		btnReimprimir.setBackground(new Color(0, 128, 128));
		btnReimprimir.setBounds(10, 11, 89, 91);
		panelBtn.add(btnReimprimir);
		btnReimprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tabMerc.getSelectedRow() >= 0) {
					try {
						Venda venda = new ControlerVenda().imprimirVenda(Integer.parseInt(String.valueOf(tabMerc.getValueAt(tabMerc.getSelectedRow(), 0))));
						new PrintableVenda(venda);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NenhumaMercadoriaCadastradaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null, "Selecione uma venda para ser impressa", "AVISO", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});

		textFilteredIssues = new FilterList<Venda>(vendas, textMatcherEditor);
		AdvancedTableModel<Venda> mercTableModel = GlazedListsSwing
				.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelVenda());
		tabMerc.setModel(mercTableModel);
		
		tabMerc.getColumnModel().getColumn(0).setPreferredWidth(110);
		tabMerc.getColumnModel().getColumn(1).setPreferredWidth(270);
		tabMerc.getColumnModel().getColumn(2).setPreferredWidth(500);
		tabMerc.getColumnModel().getColumn(3).setPreferredWidth(650);
		tabMerc.getColumnModel().getColumn(4).setPreferredWidth(120);
				
		float total = 0;
		
		try {
			controlerVenda = new ControlerVenda();
			List<Venda> preencheTable = controlerVenda.listarVendas();
			int i =0;
			for (Venda venda : preencheTable) {
				vendas.add(venda);
				total += preencheTable.get(i).getValTotal() + preencheTable.get(i).getAcrescimo()
						- preencheTable.get(i).getDesconto() - preencheTable.get(i).getValCartao();
				i++;
			}
			label.setText(String.valueOf("R$ " + String.format("%.2f",total)).replace(".", ","));

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro LOG",
					JOptionPane.ERROR_MESSAGE);
		} catch (NenhumaMercadoriaCadastradaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (NenhumaVendaRalizadaException e1) {			
			e1.printStackTrace();
		}
	}
}
