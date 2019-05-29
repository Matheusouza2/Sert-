package com.sert.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerCliente;
import com.sert.controler.Log;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.AutoCompletion;
import com.sert.editableFields.JDocumentFormatedField;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Cliente;
import com.sert.entidades.Mercadoria;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LancarDuplicata extends JDialog implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static List<Cliente> listClientes;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblTitle;
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnSalvarDup;
	private JTextField txtIdDuplicata;
	private JTextField txtVendedor;
	private JTextField txtValor;
	private JTextField txtParcelas;
	private JTextField txtPrimeiroVencimento;
	private JLabel lblVencimento;
	private JSeparator separator;
	private JLabel lblParcelas;
	private JLabel lblPrimeiroVencimento;
	private JLabel lblVendedor;
	private JComboBox<String> cbNomeCliente;
	private JLabel lblCodCliente;
	private JComboBox<String> cbIdCliente;
	private JLabel lblNome;
	private JButton btnCadastrarCliente;
	private JPanel panel;
	private JLabel lblId;
	private JButton btnPrlanamento;
	private JScrollPane scrollPane;
	private JSeparator separator_1;
	private JTable table;
	private JSeparator separator_2;
	private JSeparator separator_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LancarDuplicata dialog = new LancarDuplicata();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LancarDuplicata() {
		setBounds(100, 100, 718, 541);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		setLocationRelativeTo(null);
		setUndecorated(true);
		setModal(true);
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		listen();

		lblTitle = new JLabel();
		lblTitle.setText("lancar conta");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Gtek Technology", Font.PLAIN, 17));
		lblTitle.setBounds(93, 0, 489, 33);
		contentPanel.add(lblTitle);

		btnX = new JButton("X");
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.setBounds(662, 5, 46, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelBtn = new JPanel();
		panelBtn.setLayout(null);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		panelBtn.setBackground(Color.YELLOW);
		panelBtn.setBounds(12, 34, 696, 113);
		contentPanel.add(panelBtn);

		btnSalvarDup = new JButton();
		btnSalvarDup.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/BtnSalvar.png")));
		btnSalvarDup.setBackground(Color.GREEN);
		btnSalvarDup.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSalvarDup);
		btnSalvarDup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnCadastrarCliente = new JButton();
		btnCadastrarCliente.setBackground(new Color(0, 128, 128));
		btnCadastrarCliente.setBounds(109, 11, 89, 91);
		panelBtn.add(btnCadastrarCliente);
		btnCadastrarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(12, 158, 696, 372);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblId = new JLabel("Id:");
		lblId.setBounds(10, 11, 32, 14);
		panel.add(lblId);

		txtIdDuplicata = new JTextField();
		txtIdDuplicata.setEditable(false);
		txtIdDuplicata.setBounds(38, 8, 56, 20);
		panel.add(txtIdDuplicata);
		txtIdDuplicata.setColumns(10);

		lblCodCliente = new JLabel("Cod. Cliente:");
		lblCodCliente.setBounds(10, 52, 76, 14);
		panel.add(lblCodCliente);

		cbIdCliente = new JComboBox<String>();
		cbIdCliente.setBounds(82, 49, 56, 20);
		panel.add(cbIdCliente);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(148, 52, 46, 14);
		panel.add(lblNome);

		cbNomeCliente = new JComboBox<String>();
		cbNomeCliente.setBounds(204, 49, 343, 20);
		panel.add(cbNomeCliente);

		cbIdCliente.addItem("");
		cbNomeCliente.addItem("");
		for (Cliente cliente : carregarClientes()) {
			cbIdCliente.addItem(String.valueOf(cliente.getId()));
			cbNomeCliente.addItem(cliente.getNome());
		}
		AutoCompletion.enable(cbIdCliente);
		AutoCompletion.enable(cbNomeCliente);

		escutaComboMerc();

		lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(427, 11, 61, 14);
		panel.add(lblVendedor);

		txtVendedor = new JTextField(UsuLogado.getNome());
		txtVendedor.setEditable(false);
		txtVendedor.setBounds(498, 8, 143, 20);
		txtVendedor.setText(UsuLogado.getNome());
		panel.add(txtVendedor);
		txtVendedor.setColumns(10);

		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setBounds(10, 83, 631, 2);
		panel.add(separator);

		lblVencimento = new JLabel("Valor:");
		lblVencimento.setBounds(16, 98, 46, 14);
		panel.add(lblVencimento);

		txtValor = new JNumberFormatField(new DecimalFormat("0.00"));
		txtValor.setBorder(null);
		txtValor.setBackground(Color.LIGHT_GRAY);
		txtValor.setBounds(55, 95, 99, 20);
		panel.add(txtValor);
		txtValor.setColumns(10);

		lblParcelas = new JLabel("Parcelas:");
		lblParcelas.setBounds(178, 98, 56, 14);
		panel.add(lblParcelas);

		txtParcelas = new JNumberField();
		txtParcelas.setBorder(null);
		txtParcelas.setBackground(Color.LIGHT_GRAY);
		txtParcelas.setBounds(237, 95, 61, 20);
		panel.add(txtParcelas);
		txtParcelas.setColumns(10);

		lblPrimeiroVencimento = new JLabel("Primeiro vencimento:");
		lblPrimeiroVencimento.setBounds(320, 98, 136, 14);
		panel.add(lblPrimeiroVencimento);

		txtPrimeiroVencimento = new JDocumentFormatedField().getData();
		txtPrimeiroVencimento.setBorder(null);
		txtPrimeiroVencimento.setBackground(Color.LIGHT_GRAY);
		txtPrimeiroVencimento.setBounds(448, 95, 99, 20);
		panel.add(txtPrimeiroVencimento);
		txtPrimeiroVencimento.setColumns(10);

		btnPrlanamento = new JButton("");
		btnPrlanamento.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/btnPrevia.png")));
		btnPrlanamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPrlanamento.setBackground(Color.LIGHT_GRAY);
		btnPrlanamento.setBorder(null);
		btnPrlanamento.setBounds(559, 94, 76, 23);
		panel.add(btnPrlanamento);
		btnPrlanamento.addActionListener(this);
		btnPrlanamento.addMouseListener(this);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 676, 233);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 128));
		separator_1.setBounds(55, 115, 99, 2);
		panel.add(separator_1);

		separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 128));
		separator_2.setBounds(237, 116, 61, 2);
		panel.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 128));
		separator_3.setBounds(448, 115, 99, 2);
		panel.add(separator_3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public static List<Cliente> carregarClientes() {
		try {
			listClientes = new ControlerCliente().listCliente();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LANÇAR DUPLICATA |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LANÇAR DUPLICATA |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LANÇAR DUPLICATA |" + e1.getMessage());
		}

		return listClientes;

	}

	public void escutaComboMerc() {
		cbIdCliente.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbIdCliente.isPopupVisible()) {

					if (cbIdCliente.getSelectedItem() != null) {
						for (Cliente cliente : listClientes) {
							if (cbIdCliente.getSelectedItem().equals(String.valueOf(cliente.getId()))) {
								cbNomeCliente.setSelectedItem(cliente.getNome());
							}
						}
					} else {
						cbIdCliente.setSelectedItem("");
						cbNomeCliente.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		cbNomeCliente.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbNomeCliente.isPopupVisible()) {

					if (cbNomeCliente.getSelectedItem() != "") {
						for (Cliente cliente : listClientes) {
							if (cbNomeCliente.getSelectedItem().equals(cliente.getNome())) {
								cbIdCliente.setSelectedItem(cliente.getId());
							}
						}
					} else {
						cbNomeCliente.setSelectedItem("");
						cbIdCliente.setSelectedItem("");
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		btnPrlanamento.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/btnPrevia.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		btnPrlanamento.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/btnPreviaPressed.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		btnPrlanamento.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/btnPrevia.png")));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		btnPrlanamento.setIcon(new ImageIcon(LancarDuplicata.class.getResource("/com/sert/img/btnPreviaEntered.png")));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
}