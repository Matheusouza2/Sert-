package com.sert.relatorios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerMercadoria;
import com.sert.dao.RelatorioInterfaceDao;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.tables.TableModelMerc;
import com.sert.telas.CadMercadoria;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class RelatorioEstoque extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JPanel panelValores;
	private JLabel lblTitle;
	private JButton btnX;
	private JLabel lblTotalDeItens;
	private JLabel lblTotalDeVolumes;
	private BasicEventList<Mercadoria> mercadorias;
	private JTable tabMerc;
	private FilterList<Mercadoria> textFilteredIssues;
	private JTextField txtProcurar;
	private JSeparator separator;
	private JLabel lblProcurar;
	private JButton btnEditar;
	private JSeparator separator_1;
	private JPanel panelBtn;
	private JLabel lblValTotalItens;
	private JLabel lblValTotalVolumes;
	private float valTotalVolume;
	private JLabel lblPrecoVenda;
	private JLabel lblPreoVenda;
	private float totalVenda;

	public RelatorioEstoque() {
		setBounds(100, 100, 1020, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBounds(0, 0, 1020, 700);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(967, 11, 43, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblTitle = new JLabel("relatorio de estoque");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(347, 11, 325, 35);
		contentPanel.add(lblTitle);

		panelBtn = new JPanel();
		panelBtn.setLayout(null);
		panelBtn.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panelBtn.setBackground(Color.YELLOW);
		panelBtn.setBounds(10, 61, 1000, 110);
		contentPanel.add(panelBtn);

		btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(RelatorioEstoque.class.getResource("/com/sert/img/btnEditar.png")));
		btnEditar.setBorderPainted(false);
		btnEditar.setBackground(new Color(139, 0, 0));
		btnEditar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabMerc.getSelectedRow() >= 0) {
					long idEditar = (long) tabMerc.getValueAt(tabMerc.getSelectedRow(), 1);
					new CadMercadoria(1, idEditar).setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma mercadoria a ser editada", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		txtProcurar = new JTextField();
		txtProcurar.setBackground(new Color(255, 255, 0));
		txtProcurar.setColumns(10);
		txtProcurar.setBounds(793, 44, 197, 20);
		txtProcurar.setBorder(null);
		panelBtn.add(txtProcurar);

		lblProcurar = new JLabel("procurar");
		lblProcurar.setForeground(new Color(0, 0, 128));
		lblProcurar.setFont(new Font("Gtek Technology", Font.BOLD, 11));
		lblProcurar.setBounds(685, 47, 98, 14);
		panelBtn.add(lblProcurar);

		separator = new JSeparator();
		separator.setToolTipText("");
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLUE);
		separator.setBounds(640, 0, 2, 110);
		panelBtn.add(separator);

		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 128));
		separator_1.setBounds(793, 67, 197, 7);
		panelBtn.add(separator_1);

		mercadorias = new BasicEventList<Mercadoria>();

		try {
			for (Mercadoria mercadoria : new ControlerMercadoria().listarMercadorias()) {
				mercadorias.add(mercadoria);
				valTotalVolume += mercadoria.getEstoque();
				totalVenda += mercadoria.getPrecoVenda();
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Driver de bando de dados não encontrado", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no metodo SQL: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do Log: " + e1.getMessage(), "Erro SQL",
					JOptionPane.ERROR_MESSAGE);
		} catch (NenhumaMercadoriaCadastradaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.YELLOW, 2, true));
		scrollPane.setBounds(10, 182, 1000, 429);
		contentPanel.add(scrollPane);

		tabMerc = new JTable();
		tabMerc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tabMerc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tabMerc);

		MatcherEditor<Mercadoria> textMatcherEditor = new TextComponentMatcherEditor<Mercadoria>(txtProcurar,
				new Mercadoria());
		
		JButton btnImprimir = new JButton();
		btnImprimir.setBorderPainted(false);
		btnImprimir.setBackground(new Color(0, 128, 0));
		btnImprimir.setBounds(109, 11, 89, 91);
		panelBtn.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					new SwingWorker<Object, Object>(){
						
						@Override
						protected Object doInBackground() throws Exception {
							new RelatorioInterfaceDao().relEstoque();
							return null;
						}
						@Override
						protected void done() {
							contentPanel.setCursor(Cursor.getDefaultCursor());
						};
					}.execute();
			}
		});

		textFilteredIssues = new FilterList<Mercadoria>(mercadorias, textMatcherEditor);
		AdvancedTableModel<Mercadoria> mercTableModel = GlazedListsSwing
				.eventTableModelWithThreadProxyList(textFilteredIssues, new TableModelMerc());
		tabMerc.setModel(mercTableModel);

		panelValores = new JPanel();
		panelValores.setBackground(Color.GRAY);
		panelValores.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panelValores.setBounds(10, 622, 1000, 67);
		contentPanel.add(panelValores);
		panelValores.setLayout(null);

		lblTotalDeItens = new JLabel("Total de Itens:");
		lblTotalDeItens.setForeground(new Color(255, 255, 255));
		lblTotalDeItens.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalDeItens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalDeItens.setBounds(10, 11, 95, 14);
		panelValores.add(lblTotalDeItens);

		lblTotalDeVolumes = new JLabel("Total de Volumes:");
		lblTotalDeVolumes.setForeground(new Color(255, 255, 255));
		lblTotalDeVolumes.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalDeVolumes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalDeVolumes.setBounds(10, 36, 111, 14);
		panelValores.add(lblTotalDeVolumes);
		
		lblValTotalItens = new JLabel(String.valueOf(tabMerc.getRowCount()));
		lblValTotalItens.setForeground(new Color(255, 255, 255));
		lblValTotalItens.setHorizontalAlignment(SwingConstants.LEFT);
		lblValTotalItens.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValTotalItens.setBounds(97, 11, 136, 14);
		panelValores.add(lblValTotalItens);
		
		lblValTotalVolumes = new JLabel(String.format("%.2f", valTotalVolume));
		lblValTotalVolumes.setForeground(new Color(255, 255, 255));
		lblValTotalVolumes.setHorizontalAlignment(SwingConstants.LEFT);
		lblValTotalVolumes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValTotalVolumes.setBounds(115, 36, 136, 14);
		panelValores.add(lblValTotalVolumes);
		
		lblPreoVenda = new JLabel("Preço venda:");
		lblPreoVenda.setForeground(new Color(255, 255, 255));
		lblPreoVenda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPreoVenda.setBounds(766, 12, 95, 14);
		panelValores.add(lblPreoVenda);
										
		lblPrecoVenda = new JLabel(String.format("R$ %.2f",totalVenda));
		lblPrecoVenda.setForeground(new Color(255, 255, 255));
		lblPrecoVenda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrecoVenda.setBounds(846, 11, 95, 14);
		panelValores.add(lblPrecoVenda);

		tabMerc.getColumnModel().getColumn(0).setPreferredWidth(58);
		tabMerc.getColumnModel().getColumn(1).setPreferredWidth(140);
		tabMerc.getColumnModel().getColumn(2).setPreferredWidth(790);
		tabMerc.getColumnModel().getColumn(3).setPreferredWidth(100);

	}
}