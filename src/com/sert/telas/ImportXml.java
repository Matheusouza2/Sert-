package com.sert.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

import com.sert.controler.ControlerEmpresa;
import com.sert.controler.ControlerFornecedor;
import com.sert.controler.ControlerMercadoria;
import com.sert.controler.ControlerNfe;
import com.sert.controler.ControlerVenda;
import com.sert.controler.DeserializableNfe;
import com.sert.controler.JDateField;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.editableFields.JNumberField;
import com.sert.entidades.Empresa;
import com.sert.entidades.Fornecedor;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.MercadoriaNFe;
import com.sert.entidades.NFeEntrada;
import com.sert.exceptions.FornecedorJaCadastradoException;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.NotaJaCadastradaException;

import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.JSeparator;
import javax.swing.JRootPane;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.1.0
 * 
 */
public class ImportXml extends JDialog implements CellEditorListener {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JPanel panelBtn;
	private JPanel panelForm;
	private File caminho;
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

	private List<Mercadoria> mercList;
	private JLabel lblAtenoAoCadastrar;
	private List<Mercadoria> mercadoriaGravar = new ArrayList<>();
	private Mercadoria mercadoriaImport;
	private ControlerMercadoria controlerMercadoria;

	private JButton btnFsist;

	private ControlerNfe controlerNfe;

	private TableCellEditor cellDescMerc;
	private TableCellEditor cellRefMerc;

	private JPanel panelAguarde;

	private JLabel label;

	private JLabel lblAguarde;

	private int opcaoEntrada = 0;

	public ImportXml() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		setBounds(100, 100, screenSize.width - 74, 760);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		listen();

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(ImportXml.class.getResource("/com/sert/img/btnX.png")));
		btnX.setBounds(1262, 2, 28, 28);
		contentPanel.add(btnX);
		btnX.setBorderPainted(false);
		btnX.setContentAreaFilled(false);
		btnX.setOpaque(false);
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
		panelBtn.setBounds(10, 34, getWidth() - 20, 113);
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		panelAguarde = new JPanel();
		panelAguarde.setBounds(450, 300, 306, 187);
		panelAguarde.setVisible(false);
		contentPanel.add(panelAguarde);
		panelAguarde.setLayout(null);

		label = new JLabel("");
		label.setIcon(new ImageIcon(AjusteEstoque.class.getResource("/com/sert/img/load.gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(41, 0, 223, 187);
		panelAguarde.add(label);

		lblAguarde = new JLabel("Aguarde...");
		lblAguarde.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 22));
		lblAguarde.setHorizontalAlignment(SwingConstants.CENTER);
		lblAguarde.setBounds(0, 153, 306, 34);
		panelAguarde.add(lblAguarde);

		btnSalvar = new JButton();
		btnSalvar.setIcon(new ImageIcon(CadNotas.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBackground(Color.GREEN);
		btnSalvar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				panelAguarde.setVisible(true);
				
				recuperaNota();
				confirmarCadastro();
				
				new SwingWorker<Object, Object>() {
					@Override
					public Object doInBackground() throws Exception {
						
						return null;
					}

					@Override
					protected void done() {
						panelAguarde.setVisible(false);
						contentPanel.setCursor(Cursor.getDefaultCursor());
						while (table.getRowCount() > 0) {
							modelo.removeRow(0);
						}
						table.setModel(modelo);

						txtCaminhoXML.setText(null);
						txtCnpj.setText(null);
						txtChave.setText(null);
						txtCidade.setText(null);
						txtFornecedor.setText(null);
						txtIe.setText(null);
						txtValorNota.setText(null);
						txtUf.setText(null);
						txtRua.setText(null);
						txtNumero.setText(null);
						txtNumeroNota.setText(null);
						table.setModel(modelo);
						table.revalidate();
					}
				}.execute();
			}

		});

		txtCaminhoXML = new JTextField();
		txtCaminhoXML.setBounds(getWidth() - 590, 46, 431, 20);
		panelBtn.add(txtCaminhoXML);
		txtCaminhoXML.setColumns(10);
		txtCaminhoXML.setEditable(false);

		btnBuscarXml = new JButton("Buscar XML");
		btnBuscarXml.setBounds(getWidth() - 150, 45, 111, 23);
		panelBtn.add(btnBuscarXml);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(0, 0, 255));
		separator.setToolTipText("");
		separator.setForeground(Color.WHITE);
		separator.setBounds(529, 0, 2, 113);
		panelBtn.add(separator);

		lblCaminhoDoXml = new JLabel("Caminho do XML:");
		lblCaminhoDoXml.setBounds(getWidth() - 710, 48, 128, 17);
		panelBtn.add(lblCaminhoDoXml);

		btnFsist = new JButton();
		btnFsist.setIcon(new ImageIcon(ImportXml.class.getResource("/com/sert/img/btnFsist.png")));
		btnFsist.setBorderPainted(false);
		btnFsist.setBackground(Color.WHITE);
		btnFsist.setBounds(109, 11, 89, 91);
		panelBtn.add(btnFsist);
		btnFsist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						URI uri = new URI("https://www.fsist.com.br/");
						desktop.browse(uri);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		panelForm = new JPanel();
		panelForm.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelForm.setBounds(10, 158, getWidth() - 20, 591);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, panelForm.getWidth() - 20, 460);
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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

		lblAtenoAoCadastrar = new JLabel(
				"<html>Atenção: 'C' indicará que a mercadoria já está cadastrada no sistema e 'N' indicará que a mercadoria não foi encontrada no sistema.</html>");
		lblAtenoAoCadastrar.setForeground(Color.RED);
		lblAtenoAoCadastrar.setBounds(926, 62, 344, 57);
		panelForm.add(lblAtenoAoCadastrar);

		JButton btnAtalho = new JButton("");
		btnAtalho.setMnemonic(KeyEvent.VK_C);
		btnAtalho.setBounds(171, 9, 0, 0);
		btnAtalho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcaoEntrada = (opcaoEntrada == 0) ? 1 : 0;
				JOptionPane.showMessageDialog(null, "Configuração aplicada com sucesso !");
			}
		});
		contentPanel.add(btnAtalho);

		try {

			mercList = new ControlerMercadoria().listarMercadorias();
			String nomeMerc[] = new String[mercList.size() + 1];
			String refMerc[] = new String[mercList.size() + 1];
			refMerc[0] = " ";
			nomeMerc[0] = " ";
			int i = 1;
			for (Mercadoria merc : mercList) {
				refMerc[i] = String.valueOf(merc.getCodBarras());
				nomeMerc[i] = merc.getMercadoria();
				i += 1;

			}

			cellDescMerc = createEditor(nomeMerc);
			cellRefMerc = createEditor(refMerc);
		} catch (ClassNotFoundException | NenhumaMercadoriaCadastradaException | SQLException | IOException e1) {
			e1.printStackTrace();
		}

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
		modelo.addColumn("Preço Venda");
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.getColumnModel().getColumn(2).setPreferredWidth(82);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		table.getColumnModel().getColumn(5).setPreferredWidth(82);
		table.getColumnModel().getColumn(6).setPreferredWidth(1);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(8).setPreferredWidth(350);
		table.getColumnModel().getColumn(9).setPreferredWidth(55);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setCellEditor(cellRefMerc);
		table.getColumnModel().getColumn(8).setCellEditor(cellDescMerc);
		table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new JNumberField()));

		cellRefMerc.addCellEditorListener(this);
		cellDescMerc.addCellEditorListener(this);

		LookAndFeel lfAnterior = UIManager.getLookAndFeel();
		btnBuscarXml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				panelAguarde.setVisible(true);
				new SwingWorker<Object, Object>() {
					@Override
					public Object doInBackground() throws Exception {
						buscarXml(lfAnterior);
						return null;
					}

					@Override
					protected void done() {
						panelAguarde.setVisible(false);
						contentPanel.setCursor(Cursor.getDefaultCursor());
					}
				}.execute();
			}
		});
	}

	public void buscarXml(LookAndFeel lfAnterior) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			escolherXml = new JFileChooser();
			escolherXml.setFileFilter(new FileFilter() {

				public boolean accept(File f) {
					return (f.getName().endsWith(".xml")) || f.isDirectory();
				}

				public String getDescription() {
					return "*.ret";
				}
			});
			escolherXml.showOpenDialog(rootPane);
			UIManager.setLookAndFeel(lfAnterior);
			caminho = escolherXml.getSelectedFile();
			if (caminho != null) {
				txtCaminhoXML.setText(String.valueOf(caminho));

				nfeXml = new DeserializableNfe().lerXml(String.valueOf(caminho));

				controlerNfe = new ControlerNfe();
				controlerNfe.consultarChaveNfe(nfeXml.getChave());

				Empresa empresa = new ControlerEmpresa().consultarEmpresa(nfeXml.getCnpjDest());

				if (empresa == null) {
					int resposta = JOptionPane.showConfirmDialog(null,
							"O destinatario desta nota possui um CNPJ diferente do seu, deseja continuar mesmo assim ?",
							"CNPJ divergente", JOptionPane.YES_NO_OPTION);
					if (resposta == JOptionPane.YES_OPTION) {
						importaNota();
					} else {
						return;
					}
				} else {
					importaNota();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado", "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		} catch (UnsupportedLookAndFeelException e1) {
			JOptionPane.showMessageDialog(null, "Tipo de busca não suportado pela versã do Windows", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Erro interno do sistema, contate o suporte", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (InstantiationException e1) {
			JOptionPane.showMessageDialog(null, "Erro interno do sistema, contate o suporte", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (IllegalAccessException e1) {
			JOptionPane.showMessageDialog(null, "Erro interno do sistema, contate o suporte", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro no banco de dados, contate o suporte", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Mercadorias não encontradas",
					JOptionPane.WARNING_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (NotaJaCadastradaException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Ver cadastro de notas", JOptionPane.WARNING_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		}
	}

	public void importaNota()
			throws ClassNotFoundException, SQLException, IOException, NenhumaMercadoriaCadastradaException {

		modelo.setNumRows(0);
		mercList = new ControlerMercadoria().listarMercadorias();
		String flag = "N";
		String codBarrasFlag = "";
		String mercadoriaFlag = "";
		float descUn;

		for (int i = 0; i < nfeXml.getMercadorias().size(); i++) {
			for (int j = 0; j < mercList.size(); j++) {
				if (mercList.get(j).getCodBarras() == nfeXml.getMercadorias().get(i).getCodBarras()) {
					flag = "C";
					codBarrasFlag = String.valueOf(mercList.get(j).getCodBarras());
					mercadoriaFlag = mercList.get(j).getMercadoria();
					break;
				}
			}
			descUn = nfeXml.getMercadorias().get(i).getValDesc() / nfeXml.getMercadorias().get(i).getQuantCompra();
			modelo.addRow(new Object[] { nfeXml.getMercadorias().get(i).getCodProd(),
					nfeXml.getMercadorias().get(i).getMercadoria(),
					String.format("R$ %.4f", nfeXml.getMercadorias().get(i).getPrecoUn() - descUn),
					String.format("%.2f", nfeXml.getMercadorias().get(i).getQuantCompra()),
					String.format("R$ %.4f", nfeXml.getMercadorias().get(i).getValDesc()),
					String.format("R$ %.2f",
							(nfeXml.getMercadorias().get(i).getPrecoTotal()
									- nfeXml.getMercadorias().get(i).getValDesc())),
					flag, codBarrasFlag, mercadoriaFlag, 1, 0 });
			flag = "N";
			codBarrasFlag = "";
			mercadoriaFlag = "";
		}
		txtFornecedor.setText(nfeXml.getFornecedor().getNomeFant());
		txtCnpj.setText(nfeXml.getFornecedor().getCnpjForn());
		txtIe.setText(nfeXml.getFornecedor().getIeForn());
		txtRua.setText(nfeXml.getFornecedor().getLograForn());
		txtNumero.setText(String.valueOf(nfeXml.getFornecedor().getNumrEndForn()));
		txtCidade.setText(nfeXml.getFornecedor().getCidadeForn());
		txtUf.setText(nfeXml.getFornecedor().getUfForn());
		txtNumeroNota.setText(String.valueOf(nfeXml.getNumNota()));
		txtChave.setText(nfeXml.getChave());
		txtValorNota.setText(String.format("R$ %.2f", nfeXml.getValNota()));
	}

	// Recupera os itens da nota para cadastro das mercadorias e seus respectivos
	// estoques
	private void recuperaNota() {
		if (table.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Selecione um xml para começar o lançamento da nota", "AVISO",
					JOptionPane.WARNING_MESSAGE);
		} else {

			for (int i = 0; i < table.getRowCount(); i++) {
				mercadoriaImport = new Mercadoria();
				String id = table.getValueAt(i, 0).toString(); 
				if(id.length() > 5) {
					do{
						id = id.substring (0, id.length() - 1);
					}while (id.length() > 5);
					
				}
				
				mercadoriaImport.setId(Integer.parseInt(id));
				mercadoriaImport.setMercadoria(table.getValueAt(i, 1).toString());
				if (table.getValueAt(i, 6).equals("N")) {
					mercadoriaImport.setCodBarras(Long.parseLong("98500000" + mercadoriaImport.getId()));
				} else {
					mercadoriaImport.setCodBarras(Long.parseLong(table.getValueAt(i, 7).toString()));
				}
				mercadoriaImport.setDataCadastro(JDateField.getDate());
				mercadoriaImport.setUnd(nfeXml.getMercadorias().get(i).getUnd());
				mercadoriaImport.setPrecoCompra(
						Float.parseFloat(table.getValueAt(i, 2).toString().replace("R$", "").replace(",", ".")));
				mercadoriaImport.setUsuCad(UsuLogado.getId());
				mercadoriaImport.setEstoque(Float.parseFloat(table.getValueAt(i, 3).toString().replace(",", "."))
						* Float.parseFloat(table.getValueAt(i, 9).toString().replace(",", ".")));
				mercadoriaImport.setCadastrada(table.getValueAt(i, 6).toString());
				mercadoriaImport.setPrecoVenda(Float.parseFloat(table.getValueAt(i, 10).toString().replace(",", ".")));
				mercadoriaGravar.add(mercadoriaImport);
			}
		}

	}

	// Cadastra os itens da nota, atualiza o estoque, cadastra fornecedor, e grava a
	// nota
	private void confirmarCadastro() {
		try {
			controlerMercadoria = new ControlerMercadoria();
			controlerNfe = new ControlerNfe();

			// 1 - Cadastra Fornecedor
			lblAguarde.setText("Cadastrando Fornecedor..");
			ControlerFornecedor controlerFornecedor = new ControlerFornecedor();
			Fornecedor fornecedor = controlerFornecedor.pesqFornecedor(nfeXml.getFornecedor().getCnpjForn());
			if (fornecedor == null) {
				controlerFornecedor.cadadastrar(nfeXml.getFornecedor());
				fornecedor = controlerFornecedor.pesqFornecedor(nfeXml.getFornecedor().getCnpjForn());
			}

			// 2 - Cadastra as mercadorias e seus estoques
			lblAguarde.setText("Cadastrando Mercadorias...");
			float auxEstoque = 0;
			for (int i = 0; i < mercadoriaGravar.size(); i++) {
				auxEstoque = mercadoriaGravar.get(i).getEstoque();
				if (opcaoEntrada == 1) {
					mercadoriaGravar.get(i).setEstoque(0);
					mercadoriaGravar.get(i).setPrecoVenda(0);
				}

				Mercadoria mercadoria;
				if (mercadoriaGravar.get(i).getCadastrada().equals("N")) {
					mercadoria = new Mercadoria(mercadoriaGravar.get(i).getId(), mercadoriaGravar.get(i).getCodBarras(),
							mercadoriaGravar.get(i).getMercadoria(), mercadoriaGravar.get(i).getPrecoVenda(),
							mercadoriaGravar.get(i).getDataCadastro(), mercadoriaGravar.get(i).getUsuCad(),
							mercadoriaGravar.get(i).getUnd(), mercadoriaGravar.get(i).getPrecoCompra() / auxEstoque,
							UsuLogado.getNome(), "", mercadoriaGravar.get(i).getEstoque());
					controlerMercadoria.cadastrarMercadoriaNf(mercadoria);

				} else {
					mercadoria = new Mercadoria();
					mercadoria.setEstoque(mercadoriaGravar.get(i).getEstoque());
					mercadoria.setCodBarras(mercadoriaGravar.get(i).getCodBarras());
					mercadoria.setPrecoVenda(mercadoriaGravar.get(i).getPrecoVenda());
					controlerMercadoria.entradaMercadoria(mercadoria, mercadoriaGravar.get(i).getId());
				}
			}

			// 3 - Cadastra a nota
			lblAguarde.setText("Cadastrando Nota...");
			MercadoriaNFe mercadoriaNota;
			List<MercadoriaNFe> mercadoriasNota = new ArrayList<MercadoriaNFe>();
			for (Mercadoria mercadoria : mercadoriaGravar) {
				int idMerc = controlerMercadoria.consultaMercadoria(mercadoria.getCodBarras()).getId();
				mercadoriaNota = new MercadoriaNFe();
				mercadoriaNota.setCodBarras(mercadoria.getCodBarras());
				mercadoriaNota.setCodProd(idMerc);
				mercadoriaNota.setPrecoUn(mercadoria.getPrecoCompra());
				mercadoriaNota.setQuantCompra(mercadoria.getEstoque());
				mercadoriasNota.add(mercadoriaNota);
			}
			int id = controlerNfe.recuperaId();
			NFeEntrada entrada = new NFeEntrada(nfeXml.getCnpjDest(), fornecedor, id, nfeXml.getChave(),
					nfeXml.getNumNota(), mercadoriasNota, nfeXml.getValNota(), JDateField.getDateHoraStatic());

			lblAguarde.setText("Atualizando estoque...");
			new ControlerVenda().atualizarCadastros();

			controlerNfe.cadastrarNfe(entrada);

			JOptionPane.showMessageDialog(null, "Nota fiscal cadastrada com sucesso !", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);

			File xml = new File(caminho.toString());
			xml.delete();

		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Erro interno do sistema, contate o suporte", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml " + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, ver o LOG para mais detalhes", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml LINE 699---> " + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de arquivo, ver o LOG para mais detalhes", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml LINE 702---> " + e1.getMessage());
		} catch (MercadoriaNaoEncontradaException e) {
			JOptionPane.showMessageDialog(null, "Erro no cadastro de mercadoria, ver o LOG para mais detalhes", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml LINE 705---> " + e.getMessage());
		} catch (FornecedorJaCadastradoException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar o Fornecedor, ver o LOG para mais detalhes", "Erro",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("ImportXml LINE 708---> " + e.getMessage());
		} catch (NenhumaMercadoriaCadastradaException e) {
			e.printStackTrace();
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

	private TableCellEditor createEditor(String dados[]) {
		JComboBox<String> combo = new JComboBox<String>(dados);

		AutoCompleteDecorator.decorate(combo);

		return new ComboBoxCellEditor(combo);
	}

	@Override
	public void editingCanceled(ChangeEvent arg0) {
	}

	@Override
	public void editingStopped(ChangeEvent arg0) {
		for (Mercadoria merc : mercList) {

			if (table.getValueAt(table.getSelectedRow(), 8).equals(merc.getMercadoria())) {
				modelo.setValueAt("C", table.getSelectedRow(), 6);
				modelo.setValueAt(merc.getCodBarras(), table.getSelectedRow(), 7);
				table.setModel(modelo);
				break;
			} else if (table.getValueAt(table.getSelectedRow(), 7).equals(String.valueOf(merc.getCodBarras()))) {
				modelo.setValueAt("C", table.getSelectedRow(), 6);
				modelo.setValueAt(merc.getMercadoria(), table.getSelectedRow(), 8);
				table.setModel(modelo);
				break;
			} else if (table.getValueAt(table.getSelectedRow(), 8).equals(" ")
					|| table.getValueAt(table.getSelectedRow(), 7).equals(" ")) {
				modelo.setValueAt("N", table.getSelectedRow(), 6);
				modelo.setValueAt("", table.getSelectedRow(), 7);
				modelo.setValueAt("", table.getSelectedRow(), 8);
				table.setModel(modelo);
				break;
			}
		}
	}
}