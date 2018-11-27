package com.sert.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.editableFields.AutoCompletion;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class CadNotas extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JPanel panelBtn;
	private JPanel panelForm;

	private JTable table;
	private DefaultTableModel modelo;

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	private JLabel lblCidade;
	private JLabel lblUf;
	private JLabel lblNumeroDaNota;
	private JLabel lblChaveDeAcesso;
	private JLabel lblValorDaNota;
	private JLabel lblCadastroDeNotas;
	private JLabel lblAddRow;
	private JLabel lblRemoveRow;

	private List<Mercadoria> mercList;

	private JComboBox cbFornecedor;
	private JComboBox<String> cbMercDesc;
	private JComboBox<String> cbMercRef;

	private JButton btnRemoveRow;
	private JButton btnAddRow;
	private JButton btnX;
	private JButton btnSalvar;
	private JButton btnImportXML;

	public CadNotas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 760);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		modelo = new DefaultTableModel();

		btnX = new JButton("X");
		btnX.setBounds(1254, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblCadastroDeNotas = new JLabel("cadastro de notas");
		lblCadastroDeNotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeNotas.setForeground(Color.WHITE);
		lblCadastroDeNotas.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblCadastroDeNotas.setBounds(481, 0, 337, 35);
		contentPanel.add(lblCadastroDeNotas);

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 34, 1280, 113);
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		btnImportXML = new JButton();
		btnImportXML.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/btnImportXML.png")));
		btnImportXML.setBorderPainted(false);
		btnImportXML.setBackground(new Color(128, 0, 128));
		btnImportXML.setBounds(109, 11, 89, 91);
		panelBtn.add(btnImportXML);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLUE);
		separator.setBounds(218, 11, 2, 91);
		panelBtn.add(separator);
		btnImportXML.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ImportXml().setVisible(true);
			}
		});

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 1280, 591);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		cbFornecedor = new JComboBox();
//		try {
//			mercList = new ControlerMercadoria().listarMercadorias();
//			for (int i = 0; i < mercList.size(); i++) {
//				cbFornecedor.addItem(mercList.get(i).getMercadoria());
//			}
//			AutoCompletion.enable(cbMercDesc);
//		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
//			e1.printStackTrace();
//		}
		panelForm.add(cbFornecedor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 1260, 460);
		panelForm.add(scrollPane);

		cbMercDesc = new JComboBox<String>();
		cbMercDesc.addItem("");
		try {
			mercList = new ControlerMercadoria().listarMercadorias();
			for (int i = 0; i < mercList.size(); i++) {
				cbMercDesc.addItem(mercList.get(i).getMercadoria());
			}
			AutoCompletion.enable(cbMercDesc);
		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		
		cbMercRef = new JComboBox<String>();
		cbMercRef.addItem("");
		try {
			mercList = new ControlerMercadoria().listarMercadorias();
			for (int i = 0; i < mercList.size(); i++) {
				cbMercRef.addItem(String.valueOf(mercList.get(i).getCodBarras()));
			}
			AutoCompletion.enable(cbMercDesc);
		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
			e1.printStackTrace();
		}

		escutaComboMerc();

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 11, 73, 14);
		panelForm.add(lblFornecedor);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(10, 42, 38, 14);
		panelForm.add(lblCnpj);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(58, 39, 124, 20);
		panelForm.add(textField);
		textField.setColumns(10);

		JLabel lblIe = new JLabel("IE:");
		lblIe.setBounds(214, 42, 29, 14);
		panelForm.add(lblIe);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(253, 39, 86, 20);
		panelForm.add(textField_1);
		textField_1.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.BLUE);
		separator_1.setBounds(517, 8, 2, 101);
		panelForm.add(separator_1);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 73, 38, 14);
		panelForm.add(lblRua);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(58, 70, 281, 20);
		panelForm.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(349, 73, 58, 14);
		panelForm.add(lblNumero);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(417, 70, 58, 20);
		panelForm.add(textField_3);
		textField_3.setColumns(10);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 102, 46, 14);
		panelForm.add(lblCidade);

		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(59, 99, 86, 20);
		panelForm.add(textField_4);
		textField_4.setColumns(10);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(205, 102, 29, 14);
		panelForm.add(lblUf);

		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(244, 99, 58, 20);
		panelForm.add(textField_5);
		textField_5.setColumns(10);

		lblNumeroDaNota = new JLabel("Numero da nota:");
		lblNumeroDaNota.setBounds(546, 11, 95, 14);
		panelForm.add(lblNumeroDaNota);

		textField_6 = new JTextField();
		textField_6.setBounds(650, 8, 73, 20);
		panelForm.add(textField_6);
		textField_6.setColumns(10);

		lblChaveDeAcesso = new JLabel("Chave de acesso:");
		lblChaveDeAcesso.setBounds(755, 11, 109, 14);
		panelForm.add(lblChaveDeAcesso);

		textField_7 = new JTextField();
		textField_7.setBounds(874, 8, 358, 20);
		panelForm.add(textField_7);
		textField_7.setColumns(10);

		lblValorDaNota = new JLabel("Valor da nota:");
		lblValorDaNota.setBounds(546, 42, 80, 14);
		panelForm.add(lblValorDaNota);

		textField_8 = new JTextField();
		textField_8.setBounds(636, 39, 95, 20);
		panelForm.add(textField_8);
		textField_8.setColumns(10);

		table = new JTable();
		scrollPane.setViewportView(table);
		modelo.addColumn("Cod. barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço Unit");
		modelo.addColumn("Quant. Compra");
		modelo.addColumn("Preço Total");
		modelo.addRow(new Object[] { "", "", "", "" });
		table.setModel(modelo);

		lblAddRow = new JLabel("Adicionar Mercadoria:");
		lblAddRow.setBounds(796, 95, 135, 14);
		panelForm.add(lblAddRow);
		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (table.getSelectedRow() >= 0 && modelo.getValueAt(table.getSelectedRow(), 2) != "" && modelo.getValueAt(table.getSelectedRow(), 3) != "") {
					int linha = table.getSelectedRow();
					double preco = Double.parseDouble(modelo.getValueAt(linha, 2).toString().replace(",", "."));
					int qtd = Integer.parseInt(modelo.getValueAt(linha, 3).toString());
					double total = qtd * preco;
					String valor = String.valueOf(total);
					String convert = String.format(valor, "%.2f");
					modelo.setValueAt(convert, linha, 4); 
				}

			}
		});
		btnAddRow = new JButton("");
		btnAddRow.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/btnAddRow.png")));
		btnAddRow.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddRow.setBackground(new Color(0, 255, 0));
		btnAddRow.setBorderPainted(false);
		btnAddRow.setBounds(934, 89, 38, 27);
		btnAddRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelo.addRow(new Object[] { "", "", "", "" });
				table.setModel(modelo);
				table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
			}
		});
		panelForm.add(btnAddRow);

		lblRemoveRow = new JLabel("Remover mercadoria:");
		lblRemoveRow.setBounds(1023, 95, 124, 14);
		panelForm.add(lblRemoveRow);

		btnRemoveRow = new JButton("");
		btnRemoveRow.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/btnRemoveRow.png")));
		btnRemoveRow.setBackground(new Color(255, 0, 0));
		btnRemoveRow.setBounds(1157, 89, 38, 27);
		btnRemoveRow.setBorderPainted(false);
		btnRemoveRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0){
					modelo.removeRow(table.getSelectedRow());
					table.setModel(modelo);
					table.setRowSelectionInterval(table.getRowCount()-1, table.getRowCount()-1);
				}else{
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada", "AVISO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelForm.add(btnRemoveRow);

		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbMercRef));
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMercDesc));
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JNumberField()));
		table.getColumnModel().getColumn(4)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
	}

	public void escutaComboMerc() {
		cbMercRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercRef.isPopupVisible()) {

					if (cbMercRef.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbMercRef.getSelectedItem().equals(merc.getCodBarras())) {
								modelo.setValueAt(merc.getMercadoria(), table.getSelectedRow(), 1);
								table.setModel(modelo);
							}
						}
					} else {
						modelo.setValueAt("", table.getSelectedRow(), 1);
						table.setModel(modelo);
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

					if (cbMercDesc.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbMercDesc.getSelectedItem().equals(merc.getMercadoria())) {
								modelo.setValueAt(merc.getCodBarras(), table.getSelectedRow(), 0);
								table.setModel(modelo);
							}
						}
					} else {
						modelo.setValueAt("", table.getSelectedRow(), 0);
						table.setModel(modelo);
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}
}