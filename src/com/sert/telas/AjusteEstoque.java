package com.sert.telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerMercadoria;
import com.sert.editableFields.AutoCompleteDecoratorCombo;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class AjusteEstoque extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelBtn;
	private JPanel panelForm;

	private JScrollPane scrollPane;

	private JLabel lblUsuario;
	private JLabel lblTipo;
	private JLabel lblDataDeIncluso;
	private JLabel lblAddRow;
	private JLabel lblRemoveRow;

	private JRadioButton rdbtnEntrada;
	private JRadioButton rdbtnSaida;

	private JButton btnSalvar;
	private JButton btnListar;
	private JButton btnX;
	private JButton btnAddRow;
	private JButton btnRemoveRow;

	private JTable table;
	private ButtonGroup bg = new ButtonGroup();

	private DefaultTableModel modelo;
	private JComboBox cbMercDesc;
	private List<Mercadoria> mercList;
	private JComboBox cbMercRef;

	public AjusteEstoque() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 620);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		modelo = new DefaultTableModel();

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBounds(10, 11, 814, 113);
		contentPane.add(panelBtn);
		panelBtn.setLayout(null);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBackground(new Color(0, 255, 0));
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);

		btnListar = new JButton("");
		btnListar.setBounds(109, 11, 89, 91);
		panelBtn.add(btnListar);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(768, 0, 46, 23);
		panelBtn.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 135, 814, 474);
		contentPane.add(panelForm);
		panelForm.setLayout(null);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 14, 58, 14);
		panelForm.add(lblTipo);

		lblDataDeIncluso = new JLabel("Data de inclusão:");
		lblDataDeIncluso.setBounds(10, 449, 215, 14);
		panelForm.add(lblDataDeIncluso);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(402, 449, 174, 14);
		panelForm.add(lblUsuario);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 794, 407);
		panelForm.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		rdbtnEntrada = new JRadioButton("Entrada");
		rdbtnEntrada.setBounds(74, 10, 109, 23);
		panelForm.add(rdbtnEntrada);

		rdbtnSaida = new JRadioButton("Saída");
		rdbtnSaida.setBounds(185, 10, 109, 23);
		panelForm.add(rdbtnSaida);

		bg.add(rdbtnEntrada);
		bg.add(rdbtnSaida);

		table = new JTable();
		scrollPane.setViewportView(table);
		modelo.addColumn("Cod. barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço Compra");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Preço Total");
		modelo.addRow(new Object[] { "", "", "", "" });
		table.setModel(modelo);

		lblAddRow = new JLabel("Adicionar Mercadoria:");
		lblAddRow.setBounds(405, 16, 135, 14);
		panelForm.add(lblAddRow);
		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (table.getSelectedRow() >= 0 && modelo.getValueAt(table.getSelectedRow(), 2) != ""
						&& modelo.getValueAt(table.getSelectedRow(), 3) != "") {
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
		btnAddRow.setBounds(543, 10, 38, 27);
		btnAddRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				modelo.addRow(new Object[] { "", "", "", "" });
				table.setModel(modelo);
				table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
			}
		});
		panelForm.add(btnAddRow);

		lblRemoveRow = new JLabel("Remover mercadoria:");
		lblRemoveRow.setBounds(632, 16, 124, 14);
		panelForm.add(lblRemoveRow);

		btnRemoveRow = new JButton("");
		btnRemoveRow.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/btnRemoveRow.png")));
		btnRemoveRow.setBackground(new Color(255, 0, 0));
		btnRemoveRow.setBounds(766, 10, 38, 27);
		btnRemoveRow.setBorderPainted(false);
		btnRemoveRow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					modelo.removeRow(table.getSelectedRow());
					table.setModel(modelo);
					table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
				} else {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada", "AVISO",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelForm.add(btnRemoveRow);

		cbMercDesc = new JComboBox();
		AutoCompleteDecoratorCombo.decorate(cbMercDesc);
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbMercDesc.getModel();
		model.removeAllElements();
		cbMercDesc.addItem("");
		try {
			mercList = new ControlerMercadoria().listarMercadorias();
			for (int i = 0; i < mercList.size(); i++) {
				model.addElement(mercList.get(i).getMercadoria());
			}
			cbMercDesc.setModel(model);
		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
			e1.printStackTrace();
		}

		cbMercRef = new JComboBox();
		AutoCompleteDecoratorCombo.decorate(cbMercRef);
		DefaultComboBoxModel model1 = (DefaultComboBoxModel) cbMercRef.getModel();
		model1.removeAllElements();
		cbMercRef.addItem("");
		try {
			mercList = new ControlerMercadoria().listarMercadorias();
			for (int i = 0; i < mercList.size(); i++) {
				model1.addElement(mercList.get(i).getCodBarras());
			}
			cbMercRef.setModel(model1);
		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
			e1.printStackTrace();
		}

		escutaComboMerc();

		table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbMercRef));
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(cbMercDesc));
		table.getColumnModel().getColumn(2)
				.setCellEditor(new DefaultCellEditor(new JNumberFormatField(new DecimalFormat("0.00"))));
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

					if (cbMercDesc.getSelectedItem() != "") {
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