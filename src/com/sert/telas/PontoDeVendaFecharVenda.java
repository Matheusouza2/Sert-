package com.sert.telas;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

import com.sert.controler.ControlerVenda;
import com.sert.controler.Log;
import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;
import com.sert.entidades.Venda;
import com.sert.exceptions.MercadoriaNaoEncontradaException;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.impressao.PrintableVenda;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class PontoDeVendaFecharVenda extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private JNumberFormatField txtDinheiro;
	private JNumberFormatField txtCartao;
	private JPanel panelTroco;

	private JLabel lblTotal;
	private JLabel lblTroco;
	private JPanel panelPrincipal;
	private JLabel lblDinheiro;
	private JLabel lblCartao;
	private JLabel lblEscVoltar;
	private JLabel lblValTotal;
	private JLabel lblValTroco;
	private JLabel lblValorAPagar;
	private JLabel lblValAPagar;
	private JLabel lblDesconto;
	private JLabel label;
	private JLabel lblR;
	private JNumberFormatField txtDescPorc;
	private JNumberFormatField txtDescReal;

	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton rdbtnAcrescimo;
	private JRadioButton rdbtnDesconto;

	private static float auxTotal;

	private float valorAPagar;
	private Venda vendaFinal;

	private float valor;
	private float valorCartao;
	private float valorTotal;
	private float cartaoDinheiro;
	private float descPorc;
	private float descVal;
	private float desconto = 0;
	private float acrescimo = 0;

	private JLabel lblDuplicata;

	private JNumberFormatField txtDuplicata;

	private JTextField txtParcelas;

	private JLabel lblParcelas;

	public PontoDeVendaFecharVenda(Venda venda) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 514);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(0, 0, 611, 514);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setModal(true);

		listen();
		this.vendaFinal = venda;
		valorAPagar = venda.getValTotal();
		auxTotal = valorAPagar;

		panelTroco = new JPanel();
		panelTroco.setBackground(new Color(255, 255, 0));
		panelTroco.setBounds(0, 331, 611, 183);
		contentPanel.add(panelTroco);
		panelTroco.setLayout(null);

		lblTotal = new JLabel("total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(new Color(0, 0, 128));
		lblTotal.setFont(new Font("Gtek Technology", Font.BOLD, 16));
		lblTotal.setBounds(122, 90, 96, 53);
		panelTroco.add(lblTotal);

		lblTroco = new JLabel("troco");
		lblTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblTroco.setForeground(new Color(0, 0, 128));
		lblTroco.setFont(new Font("Gtek Technology", Font.BOLD, 16));
		lblTroco.setBounds(122, 49, 96, 53);
		panelTroco.add(lblTroco);

		lblValTroco = new JLabel();
		lblValTroco.setHorizontalAlignment(SwingConstants.CENTER);
		lblValTroco.setForeground(new Color(0, 0, 128));
		lblValTroco.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValTroco.setBounds(228, 49, 156, 53);
		panelTroco.add(lblValTroco);

		lblValTotal = new JLabel();
		lblValTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblValTotal.setForeground(new Color(0, 0, 128));
		lblValTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValTotal.setBounds(228, 88, 156, 53);
		panelTroco.add(lblValTotal);

		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 0, 128));
		panelPrincipal.setBounds(0, 0, 611, 332);
		contentPanel.add(panelPrincipal);
		panelPrincipal.setLayout(null);

		lblDinheiro = new JLabel("dinheiro");
		lblDinheiro.setFont(new Font("Gtek Technology", Font.PLAIN, 16));
		lblDinheiro.setForeground(new Color(255, 255, 0));
		lblDinheiro.setBounds(173, 185, 109, 32);
		panelPrincipal.add(lblDinheiro);

		lblCartao = new JLabel("cartao");
		lblCartao.setFont(new Font("Gtek Technology", Font.PLAIN, 16));
		lblCartao.setForeground(new Color(255, 255, 0));
		lblCartao.setBounds(173, 235, 109, 32);
		panelPrincipal.add(lblCartao);

		txtDinheiro = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDinheiro.setBounds(292, 190, 86, 20);
		panelPrincipal.add(txtDinheiro);
		txtDinheiro.setColumns(10);
		txtDinheiro.setFocusable(true);

		txtCartao = new JNumberFormatField(new DecimalFormat("0.00"));
		txtCartao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCartao.setBounds(292, 240, 86, 20);
		panelPrincipal.add(txtCartao);
		txtCartao.setColumns(10);

		lblDesconto = new JLabel("");
		lblDesconto.setFont(new Font("Gtek Technology", Font.BOLD, 14));
		lblDesconto.setForeground(new Color(255, 255, 0));
		lblDesconto.setBounds(153, 78, 129, 25);
		panelPrincipal.add(lblDesconto);

		txtDescPorc = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDescPorc.setBounds(292, 63, 86, 20);
		panelPrincipal.add(txtDescPorc);
		txtDescPorc.setEditable(false);
		txtDescPorc.setColumns(10);

		txtDescReal = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDescReal.setBounds(292, 94, 86, 20);
		panelPrincipal.add(txtDescReal);
		txtDescReal.setEditable(false);
		txtDescReal.setColumns(10);

		label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setForeground(new Color(255, 255, 0));
		label.setBounds(388, 66, 46, 14);
		panelPrincipal.add(label);

		lblR = new JLabel("R$");
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblR.setForeground(new Color(255, 255, 0));
		lblR.setBounds(388, 97, 46, 14);
		panelPrincipal.add(lblR);

		rdbtnAcrescimo = new JRadioButton("Acrescimo");
		rdbtnAcrescimo.setForeground(new Color(255, 255, 0));
		rdbtnAcrescimo.setBackground(new Color(0, 0, 128));
		rdbtnAcrescimo.setBounds(221, 19, 109, 23);
		panelPrincipal.add(rdbtnAcrescimo);

		rdbtnDesconto = new JRadioButton("Desconto");
		rdbtnDesconto.setForeground(new Color(255, 255, 0));
		rdbtnDesconto.setBackground(new Color(0, 0, 128));
		rdbtnDesconto.setBounds(345, 19, 109, 23);
		rdbtnDesconto.isSelected();
		panelPrincipal.add(rdbtnDesconto);

		bg.add(rdbtnAcrescimo);
		bg.add(rdbtnDesconto);

		rdbtnAcrescimo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtDescPorc.setEditable(true);
				txtDescReal.setEditable(true);
				lblDesconto.setText("acrescimo");

				descontoVal();
			}
		});

		rdbtnDesconto.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				txtDescPorc.setEditable(true);
				txtDescReal.setEditable(true);
				lblDesconto.setText("desconto");
				descontoVal();
			}
		});

		lblValTotal.setText("R$ " + String.format("%.2f", valorAPagar));

		lblValorAPagar = new JLabel("valor restante");
		lblValorAPagar.setForeground(new Color(0, 0, 128));
		lblValorAPagar.setFont(new Font("Gtek Technology", Font.BOLD, 16));
		lblValorAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorAPagar.setBounds(10, 11, 213, 53);
		panelTroco.add(lblValorAPagar);

		lblValAPagar = new JLabel();
		lblValAPagar.setForeground(new Color(0, 0, 128));
		lblValAPagar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblValAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblValAPagar.setBounds(228, 11, 156, 53);
		panelTroco.add(lblValAPagar);
		lblValAPagar.setText("R$ " + String.format("%.2f", valorAPagar));

		lblEscVoltar = new JLabel("ESC - Voltar  |  F2 - Confirmar pagamento");
		lblEscVoltar.setBounds(145, 158, 321, 14);
		panelTroco.add(lblEscVoltar);
		lblEscVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEscVoltar.setForeground(new Color(0, 0, 128));

		FocusListener focusPag = new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				pagamento();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
			}
		};

		if (venda.getClienteCad() != 0) {
			duplicataActive();
			txtDuplicata.addFocusListener(focusPag);
		}

		txtCartao.addFocusListener(focusPag);
		txtDinheiro.addFocusListener(focusPag);
	}

	private void listen() {
		JRootPane escback = getRootPane();
		escback.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"ESCAPE");
		escback.getRootPane().getActionMap().put("ESCAPE", new AbstractAction("ESCAPE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JRootPane f2closesale = getRootPane();
		f2closesale.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F2");
		f2closesale.getRootPane().getActionMap().put("F2", new AbstractAction("F2") {
			private static final long serialVersionUID = 4394565308739162374L;

			public void actionPerformed(ActionEvent e) {
				finalizarVenda();
			}
		});

	}

	private void pagamento() {
		valor = Float.parseFloat(txtDinheiro.getText().replace(",", "."));
		valorCartao = Float.parseFloat(txtCartao.getText().replace(",", "."));
		float valorDuplicata = 0;
		if (vendaFinal.getClienteCad() > 0)
			valorDuplicata = Float.parseFloat(txtDuplicata.getText().replace(",", "."));
		valorTotal = Float.parseFloat(lblValTotal.getText().replace("R$", "").replace(",", "."));
		cartaoDinheiro = valor + valorCartao + valorDuplicata;

		if (cartaoDinheiro >= valorTotal) {
			float troco = cartaoDinheiro - valorTotal;
			lblValAPagar.setText("R$ 0,00");
			lblValTroco.setText("R$ " + String.format("%.2f", troco));
		} else if (cartaoDinheiro < valorTotal) {
			float restante = valorTotal - cartaoDinheiro;
			lblValAPagar.setText("R$ " + String.format("%.2f", restante));
			lblValTroco.setText("R$ 0,00");
		}
	}

	private void descontoVal() {
		txtDescPorc.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				descPorc = Float.parseFloat(txtDescPorc.getText().replace(",", "."));
				descVal = (descPorc * auxTotal) / 100;
				txtDescReal.setText(String.format("%.2f", descVal));
				if (rdbtnDesconto.isSelected()) {
					lblValAPagar.setText("R$ " + String.format("%.2f", 0.0));
					lblValTroco.setText("R$ " + String.format("%.2f", cartaoDinheiro - (auxTotal - descVal)));
					lblValTotal.setText("R$ " + String.format("%.2f", (auxTotal - descVal)));
					desconto = descVal;
					valorAPagar -= desconto;
				} else if (rdbtnAcrescimo.isSelected()) {
					lblValAPagar.setText("R$ " + String.format("%.2f", (auxTotal + descVal) - cartaoDinheiro));
					lblValTotal.setText("R$ " + String.format("%.2f", (auxTotal + descVal)));
					acrescimo = descVal;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

		txtDescReal.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				descVal = Float.parseFloat(txtDescReal.getText().replace(",", "."));
				descPorc = (descVal * 100) / auxTotal;
				txtDescPorc.setText(String.format("%.2f", descPorc));
				if (rdbtnDesconto.isSelected()) {
					lblValAPagar.setText("R$ " + String.format("%.2f", 0.0));
					lblValTroco.setText("R$ " + String.format("%.2f", cartaoDinheiro - (auxTotal - descVal)));
					lblValTotal.setText("R$ " + String.format("%.2f", (auxTotal - descVal)));
					desconto = descVal;
					valorAPagar -= desconto;
				} else if (rdbtnAcrescimo.isSelected()) {
					lblValAPagar.setText("R$ " + String.format("%.2f", (auxTotal + descVal) - cartaoDinheiro));
					lblValTotal.setText("R$ " + String.format("%.2f", (auxTotal + descVal)));
					acrescimo = descVal;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
			}
		});

	}

	private void finalizarVenda() {
		float valorDuplicata = 0;

		if (vendaFinal.getClienteCad() > 0) {
			valorDuplicata = Float.parseFloat(txtDuplicata.getText().replace(",", "."));
			if (valorDuplicata > 0 && Integer.parseInt(txtParcelas.getText().replace("", "0")) == 0) {
				JOptionPane.showMessageDialog(null, "A quantidade de parcelas deve ser informada!", "Advertência",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

		if ((Float.parseFloat(txtDinheiro.getText().replace(",", "."))
				+ Float.parseFloat(txtCartao.getText().replace(",", "."))) + valorDuplicata >= valorAPagar) {
			if (Float.parseFloat(txtDinheiro.getText().replace(",", ".")) > 0) {
				vendaFinal.setDinheiro(1);
				vendaFinal.setValDInheiro(Float.parseFloat(txtDinheiro.getText().replace(",", ".")));
			}
			if (Float.parseFloat(txtCartao.getText().replace(",", ".")) > 0) {
				valorCartao = Float.parseFloat(txtCartao.getText().replace(",", "."));
				if (valorCartao <= valorAPagar) {
					vendaFinal.setCartao(1);
					vendaFinal.setValCartao(valorCartao);
				} else {
					JOptionPane.showMessageDialog(null, "Pagamento em cartão não permite troco");
					return;
				}
			}
			if (valorDuplicata > 0) {
				if (valorDuplicata <= valorAPagar) {
					vendaFinal.setDuplicata(1);
					vendaFinal.setValDuplicata(Float.parseFloat(txtDuplicata.getText().replace(",", ".")));
					vendaFinal.setParcelasDuplicata(Integer.parseInt(txtParcelas.getText().replace(",", ".")));
				} else {
					JOptionPane.showMessageDialog(null, "O valor da duplicata não pode ser maior que o valor da venda");
					return;
				}
			}
			try {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja imprimir a venda? ", "Impressão",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_NO_OPTION) {
					new PrintableVenda(vendaFinal);
				}
				vendaFinal.setAcrescimo(acrescimo);
				vendaFinal.setDesconto(desconto);

				contentPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

				new ControlerVenda().finalizarVenda(vendaFinal);

				contentPanel.setCursor(Cursor.getDefaultCursor());
				JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!", "VENDA FINALIZADA",
						JOptionPane.WARNING_MESSAGE);
				this.dispose();
				PontoDeVenda.liberarCaixaVenda();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
						JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| FECHAR VENDA |" + e1.getMessage());
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
						"Banco de dados", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| FECHAR VENDA |" + e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes",
						"Arquivo", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| FECHAR VENDA |" + e1.getMessage());
			} catch (NenhumaMercadoriaCadastradaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Arquivo", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| FECHAR VENDA |" + e1.getMessage());
			} catch (MercadoriaNaoEncontradaException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Arquivo", JOptionPane.ERROR_MESSAGE);
				Log.gravaLog("| FECHAR VENDA |" + e1.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Valor pago menor que o total da venda", "VALOR INCORRETO",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void duplicataActive() {
		lblDuplicata = new JLabel("duplicata");
		lblDuplicata.setForeground(Color.YELLOW);
		lblDuplicata.setFont(new Font("Gtek Technology", Font.PLAIN, 16));
		lblDuplicata.setBounds(99, 289, 129, 32);
		panelPrincipal.add(lblDuplicata);

		txtDuplicata = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDuplicata.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDuplicata.setColumns(10);
		txtDuplicata.setBounds(238, 295, 86, 20);
		panelPrincipal.add(txtDuplicata);

		txtParcelas = new JNumberField();
		txtParcelas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtParcelas.setColumns(10);
		txtParcelas.setBounds(473, 295, 86, 20);
		panelPrincipal.add(txtParcelas);

		lblParcelas = new JLabel("parcelas");
		lblParcelas.setForeground(Color.YELLOW);
		lblParcelas.setFont(new Font("Gtek Technology", Font.PLAIN, 16));
		lblParcelas.setBounds(334, 289, 129, 32);
		panelPrincipal.add(lblParcelas);

	}
}