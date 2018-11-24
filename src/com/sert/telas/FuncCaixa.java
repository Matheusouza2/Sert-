package com.sert.telas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.border.TitledBorder;

import com.sert.controler.JDateField;
import com.sert.controler.UsuLogado;
import com.sert.editableFields.JNumberFormatField;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.text.NumberFormat;
import javax.swing.JTextField;

public class FuncCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JNumberFormatField txtDinheiroSis;
	private JPanel panel;
	private JLabel lblData;
	private JPanel panelValor;
	private JButton btnAbrirCaixa;
	private JLabel lblUsuario;
	private JLabel lblDinheiro;
	private JLabel lblCarto;
	private JNumberFormatField txtCartaoSis;
	private JLabel lblNewLabel;
	private JLabel lblAberturaDoCaixa;
	private JLabel lblCaixaSistema;
	private JLabel lblCaixaFsico;
	private JLabel lblSaldoCaixa;
	private JNumberFormatField txtSaldoCaixa;
	private JNumberFormatField txtIncosistencia;
	private JNumberFormatField txtAbertCaixa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FuncCaixa dialog = new FuncCaixa(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FuncCaixa(int opcao) {
		setBounds(100, 100, 497, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setModal(true);

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(10, 35, 461, 304);
		contentPanel.add(panel);
		panel.setLayout(null);

		lblData = new JLabel("Data: " + JDateField.getDateHoraStatic());
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblData.setForeground(new Color(255, 255, 0));
		lblData.setBounds(10, 11, 222, 20);
		panel.add(lblData);

		lblUsuario = new JLabel("Usuário:" + UsuLogado.getNome());
		lblUsuario.setForeground(new Color(255, 255, 0));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(229, 14, 222, 14);
		panel.add(lblUsuario);

		if (opcao == 0) {
			abrirCaixa();
		} else {
			fecharCaixa();
		}
	}

	private void abrirCaixa() {
		panelValor = new JPanel();
		panelValor.setBackground(new Color(0, 0, 128));
		panelValor.setForeground(new Color(255, 255, 0));
		panelValor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Valor de abertura",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelValor.setBounds(10, 89, 394, 106);
		panel.add(panelValor);
		panelValor.setLayout(null);

		txtDinheiroSis = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDinheiroSis.setBounds(154, 43, 86, 20);
		panelValor.add(txtDinheiroSis);
		txtDinheiroSis.setColumns(10);

		btnAbrirCaixa = new JButton("Abrir caixa");
		btnAbrirCaixa.setForeground(new Color(255, 255, 255));
		btnAbrirCaixa.setBackground(new Color(0, 255, 0));
		btnAbrirCaixa.setBounds(149, 206, 102, 23);
		btnAbrirCaixa.setBorderPainted(false);
		panel.add(btnAbrirCaixa);
	}

	private void fecharCaixa() {
		lblAberturaDoCaixa = new JLabel("Abertura do caixa: ");
		lblAberturaDoCaixa.setForeground(new Color(255, 255, 0));
		lblAberturaDoCaixa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAberturaDoCaixa.setBounds(10, 61, 137, 14);
		panel.add(lblAberturaDoCaixa);

		txtAbertCaixa = new JNumberFormatField(new DecimalFormat("0.00"));
		txtAbertCaixa.setEditable(false);
		txtAbertCaixa.setColumns(10);
		txtAbertCaixa.setBounds(157, 58, 86, 20);
		panel.add(txtAbertCaixa);

		panelValor = new JPanel();
		panelValor.setBackground(new Color(0, 0, 128));
		panelValor.setForeground(new Color(255, 255, 0));
		panelValor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Valor de fechamento",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 0)));
		panelValor.setBounds(10, 86, 441, 173);
		panel.add(panelValor);
		panelValor.setLayout(null);

		lblDinheiro = new JLabel("Dinheiro");
		lblDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblDinheiro.setForeground(Color.YELLOW);
		lblDinheiro.setBounds(36, 62, 46, 14);
		panelValor.add(lblDinheiro);

		txtDinheiroSis = new JNumberFormatField(new DecimalFormat("0.00"));
		txtDinheiroSis.setEditable(false);
		txtDinheiroSis.setBounds(16, 76, 86, 20);
		panelValor.add(txtDinheiroSis);
		txtDinheiroSis.setColumns(10);

		lblCarto = new JLabel("Cartão");
		lblCarto.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarto.setForeground(Color.YELLOW);
		lblCarto.setBounds(36, 117, 46, 14);
		panelValor.add(lblCarto);

		txtCartaoSis = new JNumberFormatField(new DecimalFormat("0.00"));
		txtCartaoSis.setEditable(false);
		txtCartaoSis.setColumns(10);
		txtCartaoSis.setBounds(16, 132, 86, 20);
		panelValor.add(txtCartaoSis);

		lblCaixaSistema = new JLabel("Caixa sistema");
		lblCaixaSistema.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCaixaSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaixaSistema.setForeground(new Color(255, 255, 0));
		lblCaixaSistema.setBounds(10, 28, 98, 14);
		panelValor.add(lblCaixaSistema);

		lblCaixaFsico = new JLabel("Caixa físico");
		lblCaixaFsico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaixaFsico.setForeground(Color.YELLOW);
		lblCaixaFsico.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCaixaFsico.setBounds(171, 28, 98, 14);
		panelValor.add(lblCaixaFsico);

		lblSaldoCaixa = new JLabel("Saldo caixa");
		lblSaldoCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldoCaixa.setForeground(Color.YELLOW);
		lblSaldoCaixa.setBounds(177, 79, 86, 14);
		panelValor.add(lblSaldoCaixa);

		txtSaldoCaixa = new JNumberFormatField(new DecimalFormat("0.00"));
		txtSaldoCaixa.setColumns(10);
		txtSaldoCaixa.setBounds(177, 97, 86, 20);
		panelValor.add(txtSaldoCaixa);

		lblNewLabel = new JLabel("Inconsistência");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(327, 29, 104, 14);
		panelValor.add(lblNewLabel);

		txtIncosistencia = new JNumberFormatField(new DecimalFormat("0.00"));
		txtIncosistencia.setColumns(10);
		txtIncosistencia.setBounds(336, 97, 86, 20);
		panelValor.add(txtIncosistencia);

		btnAbrirCaixa = new JButton("Fechar caixa");
		btnAbrirCaixa.setForeground(new Color(255, 255, 255));
		btnAbrirCaixa.setBackground(new Color(255, 0, 0));
		btnAbrirCaixa.setBounds(179, 270, 102, 23);
		btnAbrirCaixa.setBorderPainted(false);
		panel.add(btnAbrirCaixa);
		}
}