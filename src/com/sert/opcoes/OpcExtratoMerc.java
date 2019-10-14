package com.sert.opcoes;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.sert.controler.ControlerMercadoria;
import com.sert.controler.PermissoesStatic;
import com.sert.entidades.Mercadoria;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;

public class OpcExtratoMerc extends JDialog {
	private static final long serialVersionUID = 8275974870463493878L;

	private JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JLabel lblMerc;
	private JComboBox<String> cbMerc;
	private JButton btnConfirmar;
	private JLabel lblBackground;

	private JLabel lblCodMerc;

	private JComboBox<Integer> cbCodMerc;

	private JLabel lblCodBarras;

	private JComboBox<String> cbCodBarras;

	private List<Mercadoria> mercs;

	public OpcExtratoMerc() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 300);
		setBackground(new Color(41, 171, 226));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);

		listen();

		btnX = new JButton("");
		btnX.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/btnX.png")));
		btnX.setOpaque(false);
		btnX.setContentAreaFilled(false);
		btnX.setBorderPainted(false);
		btnX.setBounds(239, 2, 30, 30);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		lblCodMerc = new JLabel("Codigo");
		lblCodMerc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMerc.setForeground(Color.YELLOW);
		lblCodMerc.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblCodMerc.setBounds(95, 45, 77, 14);
		contentPanel.add(lblCodMerc);

		cbCodMerc = new JComboBox<Integer>();
		cbCodMerc.setBounds(70, 70, 127, 20);
		contentPanel.add(cbCodMerc);
		AutoCompleteDecorator.decorate(cbCodMerc);

		lblCodBarras = new JLabel("Cod. de Barras");
		lblCodBarras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodBarras.setForeground(Color.YELLOW);
		lblCodBarras.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblCodBarras.setBounds(80, 118, 107, 14);
		contentPanel.add(lblCodBarras);

		cbCodBarras = new JComboBox<String>();
		cbCodBarras.setBounds(55, 143, 157, 20);
		contentPanel.add(cbCodBarras);
		AutoCompleteDecorator.decorate(cbCodBarras);

		lblMerc = new JLabel("Mercadoria");
		lblMerc.setHorizontalAlignment(SwingConstants.CENTER);
		lblMerc.setForeground(Color.YELLOW);
		lblMerc.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		lblMerc.setBounds(95, 182, 77, 14);
		contentPanel.add(lblMerc);

		cbMerc = new JComboBox<String>();
		cbMerc.setBounds(10, 207, 248, 20);
		contentPanel.add(cbMerc);
		AutoCompleteDecorator.decorate(cbMerc);

		btnConfirmar = new JButton("");
		btnConfirmar.setIcon(new ImageIcon(OpcDuplicata.class.getResource("/com/sert/img/btnConfirmar.png")));
		btnConfirmar.setOpaque(false);
		btnConfirmar.setContentAreaFilled(false);
		btnConfirmar.setBorderPainted(false);
		btnConfirmar.setBounds(93, 266, 89, 23);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int codMerc = (int) cbCodMerc.getSelectedItem();
				
				new ControlerMercadoria().extratoMerc(codMerc);
			}
		});

		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(OpcClientes.class.getResource("/com/sert/img/backOpc.png")));
		lblBackground.setBounds(0, 0, 268, 300);
		contentPanel.add(lblBackground);
		getPermissoes();

		try {
			mercs = new ControlerMercadoria().listarMercadorias();
			for (Mercadoria merc : mercs) {
				cbCodMerc.addItem(merc.getId());
				cbCodBarras.addItem(String.valueOf(merc.getCodBarras()));
				cbMerc.addItem(merc.getMercadoria());
			}

			cbCodMerc.setSelectedItem(null);
			cbCodBarras.setSelectedItem(null);
			cbMerc.setSelectedItem(null);

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NenhumaMercadoriaCadastradaException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		escutaComboMerc();
	}

	public void escutaComboMerc() {
		cbCodMerc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbCodMerc.isPopupVisible()) {
					if (cbCodMerc.getSelectedItem() != null) {
						for (Mercadoria merc : mercs) {
							if (cbCodMerc.getSelectedItem().equals(merc.getId())) {
								cbCodBarras.setSelectedItem(merc.getCodBarras());
								cbMerc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbCodBarras.setSelectedItem(null);
						cbMerc.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});

		cbCodBarras.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbCodBarras.isPopupVisible()) {

					if (cbCodBarras.getSelectedItem() != null) {
						for (Mercadoria merc : mercs) {
							if (cbCodBarras.getSelectedItem().equals(String.valueOf(merc.getCodBarras()))) {
								cbCodMerc.setSelectedItem(merc.getId());
								cbMerc.setSelectedItem(merc.getMercadoria());
							}
						}
					} else {
						cbCodMerc.setSelectedItem(null);
						cbMerc.setSelectedItem(null);
						JOptionPane.showMessageDialog(null, "Mercadoria não encontrada", "AVISO",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			}

		});
		cbMerc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (cbMerc.isPopupVisible()) {

					if (cbMerc.getSelectedItem() != "") {
						for (Mercadoria merc : mercs) {
							if (cbMerc.getSelectedItem().equals(merc.getMercadoria())) {
								cbCodMerc.setSelectedItem(merc.getId());
								cbCodBarras.setSelectedItem(merc.getCodBarras());
							}
						}
					} else {
						cbCodMerc.setSelectedItem(null);
						cbCodBarras.setSelectedItem(null);
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
				"ESC");
		escback.getRootPane().getActionMap().put("ESC", new AbstractAction("ESC") {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isCadCliente()) {

		}
		if (!PermissoesStatic.permissoesFunc.isListCliente()) {

		}
	}
}