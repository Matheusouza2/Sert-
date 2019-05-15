package com.sert.telas;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerNfe;
import com.sert.controler.Log;
import com.sert.entidades.NFeEntrada;
import com.sert.tables.TableModelNFe;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.matchers.MatcherEditor;
import ca.odell.glazedlists.swing.AdvancedTableModel;
import ca.odell.glazedlists.swing.GlazedListsSwing;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListarNFeEntrada extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisa;
	private JButton btnX;
	private JLabel lblTitle;
	private JPanel panel;
	private JButton btnDetalhar;
	private JSeparator separator;
	private JLabel lblPesquisar;
	private JSeparator separator_1;
	private JTable table;
	private JScrollPane scrollPane;
	private BasicEventList<NFeEntrada> nfeList;
	private FilterList<NFeEntrada> textFilteredIssues;
	private AdvancedTableModel<NFeEntrada> nfeEntradaTableModel;

	public ListarNFeEntrada() {
		setBounds(100, 100, 1020, 700);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setUndecorated(true);
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

		lblTitle = new JLabel("notas de entrada");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(347, 11, 325, 35);
		contentPanel.add(lblTitle);

		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBorder(new LineBorder(new Color(255, 255, 0), 1, true));
		panel.setBounds(10, 45, 1000, 110);
		contentPanel.add(panel);
		panel.setLayout(null);

		btnDetalhar = new JButton();
		btnDetalhar.setIcon(new ImageIcon(ListarNFeEntrada.class.getResource("/com/sert/img/btnDetalhar2.png")));
		btnDetalhar.setBorderPainted(false);
		btnDetalhar.setBackground(Color.YELLOW);
		btnDetalhar.setBounds(10, 9, 89, 91);
		panel.add(btnDetalhar);
		btnDetalhar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(255, 255, 0));
		separator.setBounds(673, 4, 9, 101);
		panel.add(separator);

		lblPesquisar = new JLabel("pesquisar");
		lblPesquisar.setForeground(new Color(0, 0, 128));
		lblPesquisar.setFont(new Font("Gtek Technology", Font.BOLD, 15));
		lblPesquisar.setBounds(692, 44, 136, 22);
		panel.add(lblPesquisar);

		txtPesquisa = new JTextField();
		txtPesquisa.setForeground(new Color(0, 0, 128));
		txtPesquisa.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtPesquisa.setBackground(Color.YELLOW);
		txtPesquisa.setBounds(838, 45, 152, 20);
		txtPesquisa.setBorder(null);
		panel.add(txtPesquisa);
		txtPesquisa.setColumns(10);

		separator_1 = new JSeparator();
		separator_1.setBounds(838, 65, 152, 2);
		panel.add(separator_1);

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(Color.YELLOW, 2, true));
		scrollPane.setBounds(10, 165, 1000, 524);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		setModal(true);

		nfeList = new BasicEventList<NFeEntrada>();

		List<NFeEntrada> nfeEntrada = null;
		try {
			nfeEntrada = new ControlerNfe().listarNfe();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Erro interno do sistema, ver o LOG para mais detalhes", "Erro",JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|ListarNFe|" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,"Erro de banco de dados, ver o LOG para mais detalhes", "Erro",JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|ListarNFe|" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,"Erro na escrita de arquivo, ver o LOG para mais detalhes", "Erro",JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("|ListarNFe|" + e1.getMessage());
		}

		for (NFeEntrada nFeEntrada2 : nfeEntrada) {
			nfeList.add(nFeEntrada2);
		}

		MatcherEditor<NFeEntrada> textMatcherEditor = new TextComponentMatcherEditor<NFeEntrada>(txtPesquisa,
				new NFeEntrada());
		textFilteredIssues = new FilterList<NFeEntrada>(nfeList, textMatcherEditor);
		nfeEntradaTableModel = GlazedListsSwing.eventTableModelWithThreadProxyList(textFilteredIssues,
				new TableModelNFe());
		table.setModel(nfeEntradaTableModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
		table.getColumnModel().getColumn(4).setPreferredWidth(400);
	}
}