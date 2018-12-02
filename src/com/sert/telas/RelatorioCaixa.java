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

public class RelatorioCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnEditar;
	private ControlerVenda controlerVenda;
	private JPanel panel;
	private JLabel lblTotal;
	private JLabel label;
	private DefaultTableModel modelo;
	private JScrollPane spListaMerc;
	private JTable tabMerc;
	private JLabel lblRelatorioCaixa;

	public RelatorioCaixa() {
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

		btnEditar = new JButton();
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(ListarMercadorias.class.getResource("/com/sert/img/btnEditar.png")));
		btnEditar.setBounds(10, 11, 89, 91);
		btnEditar.setBackground(new Color(139, 0, 0));
		btnEditar.setBorderPainted(false);
		panelBtn.add(btnEditar);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setBounds(520, 11, 170, 28);
		panelBtn.add(lblPeriodo);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabMerc.getSelectedRow() >= 0) {
		
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma mercadoria a ser editada", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

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

		lblRelatorioCaixa = new JLabel("relatorio de caixa");
		lblRelatorioCaixa.setForeground(new Color(255, 255, 255));
		lblRelatorioCaixa.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblRelatorioCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatorioCaixa.setBounds(280, 0, 273, 35);
		contentPanel.add(lblRelatorioCaixa);
		
		panel = new JPanel();
		panel.setBounds(10, 565, 814, 14);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(0, 0, 482, 14);
		panel.add(lblTotal);
		
		label = new JLabel("");
		label.setBounds(758, 0, 46, 14);
		panel.add(label);

		modelo.addColumn("Numero da Venda");
		modelo.addColumn("Data");
		modelo.addColumn("Vendedor");
		modelo.addColumn("Dinheiro");
		modelo.addColumn("Cartão");
		tabMerc.getColumnModel().getColumn(0).setPreferredWidth(55);
		tabMerc.getColumnModel().getColumn(1).setPreferredWidth(130);
		tabMerc.getColumnModel().getColumn(2).setPreferredWidth(790);
		tabMerc.getColumnModel().getColumn(3).setPreferredWidth(100);
		float total = 0;
		try {
			controlerVenda = new ControlerVenda();
			List<Venda> preencheTable = controlerVenda.listarVendas();
			for (int i = 0; i < preencheTable.size(); i++) {
				tabMerc.isCellEditable(i, 1);
				modelo.addRow(new Object[] { preencheTable.get(i).getId(), preencheTable.get(i).getDataVenda(),
						preencheTable.get(i).getVendedor(), preencheTable.get(i).getValTotal()});
				total += preencheTable.get(i).getValTotal();
			}
			label.setText(String.valueOf(total));

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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}