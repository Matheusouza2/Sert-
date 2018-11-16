package com.sert.telas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import com.sert.editableFields.JNumberField;
import com.sert.editableFields.JNumberFormatField;

import javax.swing.SwingConstants;

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
	private JNumberFormatField txtDescPorc;
	private JNumberFormatField txtDescReal;
	
	private float auxTotal;
	
	public PontoDeVendaFecharVenda(float valorAPagar) {
		setBounds(0, 0, 611, 514);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBounds(0, 0, 595, 475);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		auxTotal = valorAPagar;
		
		panelTroco = new JPanel();
		panelTroco.setBackground(new Color(255, 255, 0));
		panelTroco.setBounds(0, 331, 594, 143);
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
		panelPrincipal.setBounds(0, 11, 594, 309);
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
		lblCartao.setBounds(173, 249, 109, 32);
		panelPrincipal.add(lblCartao);

		txtDinheiro = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDinheiro.setBounds(292, 190, 86, 20);
		panelPrincipal.add(txtDinheiro);
		txtDinheiro.setColumns(10);

		txtCartao = new JNumberFormatField(new DecimalFormat("0.00"));
		txtCartao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCartao.setBounds(292, 254, 86, 20);
		panelPrincipal.add(txtCartao);
		txtCartao.setColumns(10);

		lblEscVoltar = new JLabel("ESC - Voltar  |  F2 - Confirmar pagamento");
		lblEscVoltar.setForeground(new Color(255, 255, 0));
		lblEscVoltar.setBounds(184, 0, 226, 14);
		panelPrincipal.add(lblEscVoltar);
		
		lblDesconto = new JLabel("desconto");
		lblDesconto.setFont(new Font("Gtek Technology", Font.BOLD, 14));
		lblDesconto.setForeground(new Color(255, 255, 0));
		lblDesconto.setBounds(10, 45, 109, 25);
		panelPrincipal.add(lblDesconto);
		
		txtDescPorc = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDescPorc.setBounds(129, 30, 86, 20);
		panelPrincipal.add(txtDescPorc);
		txtDescPorc.setColumns(10);
		
		txtDescReal = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDescReal.setBounds(129, 61, 86, 20);
		panelPrincipal.add(txtDescReal);
		txtDescReal.setColumns(10);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setForeground(new Color(255, 255, 0));
		label.setBounds(225, 33, 46, 14);
		panelPrincipal.add(label);
		
		JLabel lblR = new JLabel("R$");
		lblR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblR.setForeground(new Color(255, 255, 0));
		lblR.setBounds(225, 64, 46, 14);
		panelPrincipal.add(lblR);
		
		lblValTotal.setText("R$ "+String.format("%.2f", valorAPagar));
		
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
		lblValAPagar.setText("R$ "+String.format("%.2f", valorAPagar));
				
		FocusListener focusPag = new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				pagamento();
			}
			@Override
			public void focusGained(FocusEvent arg0) {}
		};
		
		FocusListener focusDesc = new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				desconto();
			}
			@Override
			public void focusGained(FocusEvent arg0) {}
		};
		
		txtCartao.addFocusListener(focusPag);
		txtDinheiro.addFocusListener(focusPag);
		txtDescPorc.addFocusListener(focusDesc);
		txtDescReal.addFocusListener(focusDesc);
	}

	
	private void pagamento() {
		float valor = Float.parseFloat(txtDinheiro.getText().replace(",", "."));
		if(valor >= auxTotal) {
			System.out.println("Olá");
			float troco = valor - auxTotal;
			lblValAPagar.setText("R$ 0,00");
			lblValTroco.setText("R$ "+String.format("%.2f", troco));
			txtCartao.setEditable(false);
		}else if(valor < auxTotal) {
			
		}
	}
	
	private void desconto() {
		
	}
}
