package com.sert.relatorios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerCaixa;
import com.sert.entidades.Caixa;
import com.sert.tables.TableRenderer;
import com.sert.telas.ListarMercadorias;

public class RelatorioCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnEditar;
	private ControlerCaixa controlerCaixa;
	private JPanel panel;
	private JLabel lblQuantVenda;
	private JLabel lblDinheiro;
	private DefaultTableModel modelo;
	private JScrollPane spListaMerc;
	private JTable tabMerc;
	private JLabel lblRelatorioCaixa;
	private float totalDinheiro;
	private float totalCartao;
	private float totalAmbos;
	private float totalCompra;
	private float lucroDinheiro;
	private float lucroPorcento;
	private JLabel lblTotalVenda;
	private JLabel lblCartao;
	private JLabel lblTotalDinheiro;
	private JLabel lblTotalCarto;
	private JLabel lblTotalCompra;
	private JLabel lblLucroBruto;
	private JLabel lblCompra;
	private JLabel lblLucro;
	private JLabel lblPeriodo;
	private JLabel lblValSangria;
	private JLabel lblSangria;
	private JLabel lblTotalDeCaixa;
	private JLabel lblTotalCaixa;

	public RelatorioCaixa(String dtInicial, String dtFinal) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 708);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		listen();

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

		lblPeriodo = new JLabel("Periodo: " + dtInicial + " à " + dtFinal);
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodo.setBounds(494, 11, 310, 28);
		panelBtn.add(lblPeriodo);

		spListaMerc = new JScrollPane();
		spListaMerc.setBounds(10, 158, 814, 396);
		spListaMerc.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(spListaMerc);
		tabMerc = new JTable();
		tabMerc.getTableHeader().setReorderingAllowed(false);
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
		panel.setBounds(10, 565, 814, 132);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblQuantVenda = new JLabel("Quantidade de Movimentações:");
		lblQuantVenda.setBounds(0, 0, 192, 14);
		panel.add(lblQuantVenda);

		lblDinheiro = new JLabel("");
		lblDinheiro.setBounds(142, 25, 155, 14);
		panel.add(lblDinheiro);

		lblTotalVenda = new JLabel("");
		lblTotalVenda.setBounds(202, 0, 58, 14);
		panel.add(lblTotalVenda);

		lblCartao = new JLabel("");
		lblCartao.setBounds(142, 50, 155, 14);
		panel.add(lblCartao);

		lblTotalDinheiro = new JLabel("Total dinheiro:");
		lblTotalDinheiro.setBounds(0, 25, 135, 14);
		panel.add(lblTotalDinheiro);

		lblTotalCarto = new JLabel("Total Cartão:");
		lblTotalCarto.setBounds(0, 50, 135, 14);
		panel.add(lblTotalCarto);

		lblTotalCompra = new JLabel("Total compra:");
		lblTotalCompra.setBounds(0, 75, 135, 14);
		panel.add(lblTotalCompra);

		lblLucroBruto = new JLabel("Lucro Bruto:");
		lblLucroBruto.setBounds(0, 109, 135, 14);
		panel.add(lblLucroBruto);

		lblCompra = new JLabel();
		lblCompra.setBounds(142, 75, 155, 14);
		panel.add(lblCompra);

		lblLucro = new JLabel();
		lblLucro.setBounds(142, 100, 172, 32);
		panel.add(lblLucro);

		lblSangria = new JLabel("Sangria:");
		lblSangria.setBounds(288, 25, 90, 14);
		panel.add(lblSangria);

		lblValSangria = new JLabel("");
		lblValSangria.setBounds(388, 25, 90, 14);
		panel.add(lblValSangria);

		lblTotalDeCaixa = new JLabel("Total de caixa:");
		lblTotalDeCaixa.setBounds(288, 62, 90, 14);
		panel.add(lblTotalDeCaixa);

		lblTotalCaixa = new JLabel("");
		lblTotalCaixa.setBounds(388, 62, 105, 14);
		panel.add(lblTotalCaixa);

		modelo.addColumn("Data");
		modelo.addColumn("Historico");
		modelo.addColumn("Operação");
		modelo.addColumn("Dinheiro");
		modelo.addColumn("Cartão");
		modelo.addColumn("Total");
		tabMerc.getColumnModel().getColumn(0).setPreferredWidth(230);
		tabMerc.getColumnModel().getColumn(1).setPreferredWidth(400);
		tabMerc.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabMerc.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabMerc.getColumnModel().getColumn(4).setPreferredWidth(120);
		tabMerc.getColumnModel().getColumn(5).setPreferredWidth(120);
		tabMerc.setDefaultRenderer(Object.class, new TableRenderer());

		try {
			controlerCaixa = new ControlerCaixa();
			List<Caixa> preencheTable = controlerCaixa.historicoCaixa(dtInicial, dtFinal);
			String operacao = "+";
			float retiradas = 0;
			for (int i = 0; i < preencheTable.size(); i++) {
				if (preencheTable.get(i).isRetirada()) {
					operacao = "-";
					retiradas += preencheTable.get(i).getValorDinheiro();
				} else {
					operacao = "+";
					totalAmbos += preencheTable.get(i).getValorDinheiro() + preencheTable.get(i).getValorCartao();
					totalDinheiro += preencheTable.get(i).getValorDinheiro();
					totalCartao += preencheTable.get(i).getValorCartao();
					totalCompra += preencheTable.get(i).getValorCompra();
				}
				modelo.addRow(new Object[] { preencheTable.get(i).getDataOperacao(),
						preencheTable.get(i).getHistorico(), operacao,
						"R$ " + String.format("%.2f", preencheTable.get(i).getValorDinheiro()),
						"R$ " + String.format("%.2f", preencheTable.get(i).getValorCartao()),
						"R$ " + String.format("%.2f",
								preencheTable.get(i).getValorDinheiro() + preencheTable.get(i).getValorCartao()) });
			}

			lblDinheiro.setText("R$ " + String.format("%.2f", totalDinheiro));
			lblTotalVenda.setText(String.valueOf(tabMerc.getRowCount()));
			lblCartao.setText("R$ " + String.format("%.2f", totalCartao));
			lblCompra.setText("R$ " + String.format("%.2f", totalCompra));
			lucroDinheiro = totalAmbos - totalCompra - retiradas;
			lucroPorcento = ((totalAmbos / totalCompra) - 1) * 100;
			lblLucro.setText("<html>R$ " + String.format("%.2f", lucroDinheiro) + "<br>"
					+ String.format("%.2f", lucroPorcento) + " %</html>");
			lblValSangria.setText("R$ " + String.format("%.2f", retiradas));
			lblTotalCaixa.setText("R$ " + String.format("%.2f", (totalDinheiro + totalCartao) - retiradas));

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro LOG",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listen() {

		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESC");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}
}