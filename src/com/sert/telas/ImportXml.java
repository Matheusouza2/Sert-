package com.sert.telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerEmpresa;
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.DeserializableNfe;
import com.sert.editableFields.AutoCompleteDecoratorCombo;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Empresa;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.NFeEntrada;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class ImportXml extends JDialog {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JPanel panelBtn;
	private JPanel panelForm;

	private JLabel lblCadastroDeNotas;
	private JLabel lblCaminhoDoXml;
	private JLabel lblNumNota;
	private JLabel lblFornecedor;

	private JButton btnX;
	private JButton btnSalvar;
	private JButton btnBuscarXml;
	private JTable table;

	private JSeparator separator;
	private JScrollPane scrollPane;

	private JFileChooser escolherXml;

	private DefaultTableModel modelo;

	private NFeEntrada nfeXml;

	private JTextField txtCaminhoXML;
	private JTextField txtCnpj;
	private JTextField txtIe;
	private JTextField txtRua;
	private JTextField txtCidade;
	private JTextField txtUf;
	private JTextField txtNumero;
	private JTextField txtNumeroNota;
	private JTextField txtValorNota;
	private JTextField txtChave;
	private JTextField txtFornecedor;

	private JSeparator separator_1;
	private JLabel lblChavAcesso;
	private JLabel lblValNota;
	private JLabel lblNumero;
	private JLabel lblUf;
	private JLabel lblCidade;
	private JLabel lblRua;
	private JLabel lblIe;
	private JLabel lblCnpj;

	private JComboBox cbMercRef;
	private JComboBox cbMercDesc;

	private List<Mercadoria> mercList;
	private JLabel lblAtenoAoCadastrar;

	public ImportXml() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1300, 760);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

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

		lblCadastroDeNotas = new JLabel("importacao de xml");
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

		txtCaminhoXML = new JTextField();
		txtCaminhoXML.setBounds(688, 46, 431, 20);
		panelBtn.add(txtCaminhoXML);
		txtCaminhoXML.setColumns(10);
		txtCaminhoXML.setEditable(false);

		btnBuscarXml = new JButton("Buscar XML");
		btnBuscarXml.setBounds(1129, 45, 111, 23);
		panelBtn.add(btnBuscarXml);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(529, 0, 2, 113);
		panelBtn.add(separator);

		lblCaminhoDoXml = new JLabel("Caminho do XML:");
		lblCaminhoDoXml.setBounds(550, 49, 128, 17);
		panelBtn.add(lblCaminhoDoXml);

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, 1280, 591);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 1260, 460);
		panelForm.add(scrollPane);

		table = new JTable();
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		table.setModel(modelo);

		lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 9, 73, 14);
		panelForm.add(lblFornecedor);

		lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setBounds(10, 34, 38, 14);
		panelForm.add(lblCnpj);

		txtCnpj = new JDocumentFormatedField().getCnpj();
		txtCnpj.setEditable(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(58, 31, 124, 20);
		panelForm.add(txtCnpj);

		lblIe = new JLabel("IE:");
		lblIe.setBounds(214, 34, 29, 14);
		panelForm.add(lblIe);

		txtIe = new JTextField();
		txtIe.setEditable(false);
		txtIe.setColumns(10);
		txtIe.setBounds(253, 31, 86, 20);
		panelForm.add(txtIe);

		lblRua = new JLabel("Rua:");
		lblRua.setBounds(10, 62, 38, 14);
		panelForm.add(lblRua);

		txtRua = new JTextField();
		txtRua.setEditable(false);
		txtRua.setColumns(10);
		txtRua.setBounds(58, 59, 281, 20);
		panelForm.add(txtRua);

		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 90, 46, 14);
		panelForm.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(58, 87, 247, 20);
		panelForm.add(txtCidade);

		lblUf = new JLabel("UF:");
		lblUf.setBounds(388, 90, 29, 14);
		panelForm.add(lblUf);

		txtUf = new JTextField();
		txtUf.setEditable(false);
		txtUf.setColumns(10);
		txtUf.setBounds(427, 87, 58, 20);
		panelForm.add(txtUf);

		lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(349, 62, 58, 14);
		panelForm.add(lblNumero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setColumns(10);
		txtNumero.setBounds(417, 59, 58, 20);
		panelForm.add(txtNumero);

		txtNumeroNota = new JTextField();
		txtNumeroNota.setEditable(false);
		txtNumeroNota.setColumns(10);
		txtNumeroNota.setBounds(650, 11, 73, 20);
		panelForm.add(txtNumeroNota);

		txtValorNota = new JTextField();
		txtValorNota.setEditable(false);
		txtValorNota.setColumns(10);
		txtValorNota.setBounds(636, 42, 95, 20);
		panelForm.add(txtValorNota);

		lblNumNota = new JLabel("Numero da nota:");
		lblNumNota.setBounds(546, 14, 95, 14);
		panelForm.add(lblNumNota);

		lblValNota = new JLabel("Valor da nota:");
		lblValNota.setBounds(546, 45, 80, 14);
		panelForm.add(lblValNota);

		lblChavAcesso = new JLabel("Chave de acesso:");
		lblChavAcesso.setBounds(755, 14, 109, 14);
		panelForm.add(lblChavAcesso);

		txtChave = new JTextField();
		txtChave.setEditable(false);
		txtChave.setColumns(10);
		txtChave.setBounds(874, 11, 358, 20);
		panelForm.add(txtChave);

		txtFornecedor = new JTextField();
		txtFornecedor.setEditable(false);
		txtFornecedor.setBounds(93, 6, 412, 20);
		panelForm.add(txtFornecedor);
		txtFornecedor.setColumns(10);

		separator_1 = new JSeparator();
		separator_1.setToolTipText("");
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.BLUE);
		separator_1.setBounds(529, 6, 2, 113);
		panelForm.add(separator_1);
		
		lblAtenoAoCadastrar = new JLabel("<html>Atenção: 'C' indicara que a mercadoria já está cadastrada no sistema e 'N' indicara que a mercadoria não foi encontrada no sistema.</html>");
		lblAtenoAoCadastrar.setForeground(Color.RED);
		lblAtenoAoCadastrar.setBounds(926, 62, 344, 57);
		panelForm.add(lblAtenoAoCadastrar);
		
		
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

		modelo.addColumn("Código");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço Unit");
		modelo.addColumn("Quant. Nota");
		modelo.addColumn("Val Desc.");
		modelo.addColumn("Preço Total");
		modelo.addColumn("");
		modelo.addColumn("Cod. Barras");
		modelo.addColumn("Descrição");
		modelo.addColumn("Quant.");
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
		table.getColumnModel().getColumn(6).setPreferredWidth(1);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(700);
		table.getColumnModel().getColumn(9).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(cbMercRef));
		table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(cbMercDesc));
		table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new JNumberField()));

		LookAndFeel lfAnterior = UIManager.getLookAndFeel();
		btnBuscarXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					escolherXml = new JFileChooser();
					escolherXml.showOpenDialog(rootPane);
					UIManager.setLookAndFeel(lfAnterior);
					File caminho = escolherXml.getSelectedFile();
					if (caminho != null) {
						txtCaminhoXML.setText(String.valueOf(caminho));
						nfeXml = new DeserializableNfe().lerXml(String.valueOf(caminho));
						Empresa empresa = new ControlerEmpresa().consultarEmpresa(nfeXml.getCnpjDest());
						if (empresa == null) {
							int resposta = JOptionPane.showConfirmDialog(null,
									"O destinatario desta nota possui um CNPJ diferente do seu, deseja continuar mesmo assim ?",
									"CNPJ divergente", JOptionPane.YES_NO_OPTION);
							if (resposta == JOptionPane.YES_OPTION) {
								importaNota();
							}
						} else {
							importaNota();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (UnsupportedLookAndFeelException e1) {
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (InstantiationException e1) {

					e1.printStackTrace();
				} catch (IllegalAccessException e1) {

					e1.printStackTrace();
				} catch (SQLException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				} catch (NenhumaMercadoriaCadastradaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void importaNota() throws ClassNotFoundException, SQLException, IOException, NenhumaMercadoriaCadastradaException {
		modelo.setNumRows(0);
		mercList = new ControlerMercadoria().listarMercadorias();
		String flag = "N";
		long codBarrasFlag = 0;
		String mercadoriaFlag = "";
		
		for (int i = 0; i < nfeXml.getMercadorias().size(); i++) {
			for(int j = 0; j < mercList.size(); j++){
				if(mercList.get(j).getCodBarras() == nfeXml.getMercadorias().get(i).getCodBarras()){
					flag = "C";
					codBarrasFlag = mercList.get(j).getCodBarras();
					mercadoriaFlag = mercList.get(j).getMercadoria();
					break;
				}
			}
			modelo.addRow(new Object[] { nfeXml.getMercadorias().get(i).getCodProd(),
					nfeXml.getMercadorias().get(i).getMercadoria(),
					String.format("%.4f", nfeXml.getMercadorias().get(i).getPrecoUn()),
					String.format("%.2f", nfeXml.getMercadorias().get(i).getQuantCompra()),
					String.format("%.4f", nfeXml.getMercadorias().get(i).getValDesc()),
					String.format("%.2f", (nfeXml.getMercadorias().get(i).getPrecoTotal()
							- nfeXml.getMercadorias().get(i).getValDesc())),
					flag,
					codBarrasFlag,
					mercadoriaFlag,
					1});
			flag = "N";
			codBarrasFlag = 0;
			mercadoriaFlag = "";
		}
		txtFornecedor.setText(nfeXml.getNomeFant());
		txtCnpj.setText(String.valueOf(nfeXml.getCnpjForn()));
		txtIe.setText(String.valueOf(nfeXml.getIeForn()));
		txtRua.setText(nfeXml.getLograForn());
		txtNumero.setText(String.valueOf(nfeXml.getNumrEndForn()));
		txtCidade.setText(nfeXml.getCidadeForn());
		txtUf.setText(nfeXml.getUfForn());
		txtNumeroNota.setText(String.valueOf(nfeXml.getNumNota()));
		txtChave.setText(nfeXml.getChave());
	}

	public void escutaComboMerc() {
		cbMercRef.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMercRef.isPopupVisible()) {

					if (cbMercRef.getSelectedItem() != null) {
						for (Mercadoria merc : mercList) {
							if (cbMercRef.getSelectedItem().equals(merc.getCodBarras())) {
								modelo.setValueAt(merc.getMercadoria(), table.getSelectedRow(), 8);
								table.setModel(modelo);
							}
						}
					} else {
						modelo.setValueAt("", table.getSelectedRow(), 8);
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
								modelo.setValueAt(merc.getCodBarras(), table.getSelectedRow(), 7);
								table.setModel(modelo);
							}
						}
					} else {
						modelo.setValueAt("", table.getSelectedRow(), 7);
						table.setModel(modelo);
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}
	
	public void verificaMercExite(){
		
	}
}