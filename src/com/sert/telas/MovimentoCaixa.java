package com.sert.telas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

import com.sert.controler.ControlerCaixa;
import com.sert.controler.UsuLogado;
import com.sert.entidades.Caixa;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.1.0
 * 
 */
public class MovimentoCaixa extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup bg = new ButtonGroup();
	private JTextField txtHistorico;
	private JTextField txtValor;
	private JSeparator separatorHistorico;
	private JLabel lblValor;
	private JSeparator separatorValor;
	private JButton btnConfirmar;
	private JLabel lblMovimentarCaixa;
	private JPanel panel;
	private JRadioButton rdbtnSuprimento;
	private JRadioButton rdbtnSangria;
	private JLabel lblHistorico;
	private JButton btnX;

	public MovimentoCaixa() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 295);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMovimentarCaixa = new JLabel("movimentar caixa");
		lblMovimentarCaixa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovimentarCaixa.setForeground(new Color(255, 255, 255));
		lblMovimentarCaixa.setBounds(119, 4, 178, 19);
		contentPane.add(lblMovimentarCaixa);

		btnX = new JButton("X");
		btnX.setBounds(378, 0, 39, 23);
		contentPane.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		panel.setBounds(10, 34, 397, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		rdbtnSuprimento = new JRadioButton("Suprimento");
		rdbtnSuprimento.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSuprimento.setBounds(60, 49, 109, 23);
		panel.add(rdbtnSuprimento);

		rdbtnSangria = new JRadioButton("Sangria");
		rdbtnSangria.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSangria.setBounds(183, 49, 109, 23);
		panel.add(rdbtnSangria);

		bg.add(rdbtnSangria);
		bg.add(rdbtnSuprimento);

		lblHistorico = new JLabel("Historico:");
		lblHistorico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorico.setBounds(10, 112, 68, 14);
		panel.add(lblHistorico);

		txtHistorico = new JTextField();
		txtHistorico.setBounds(74, 109, 282, 20);
		txtHistorico.setBorder(null);
		txtHistorico.setBackground(new Color(240, 240, 240));
		panel.add(txtHistorico);
		txtHistorico.setColumns(10);

		separatorHistorico = new JSeparator();
		separatorHistorico.setBackground(new Color(0, 0, 128));
		separatorHistorico.setBounds(20, 129, 337, 14);
		panel.add(separatorHistorico);

		lblValor = new JLabel("Valor (R$):");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(108, 172, 86, 14);
		panel.add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(189, 169, 86, 20);
		txtValor.setBorder(null);
		txtValor.setBackground(new Color(240, 240, 240));
		panel.add(txtValor);
		txtValor.setColumns(10);

		separatorValor = new JSeparator();
		separatorValor.setBackground(new Color(0, 0, 128));
		separatorValor.setBounds(124, 190, 147, 14);
		panel.add(separatorValor);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(144, 216, 109, 23);
		panel.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
				Caixa caixa = new Caixa();
				caixa.setHistorico(txtHistorico.getText());
				caixa.setDinheiro(1);
				caixa.setIdUsuario(UsuLogado.getId());
				caixa.setValorDinheiro(Float.parseFloat(txtValor.getText()));
				if (rdbtnSangria.isSelected()) {
					caixa.setRetirada(true);
				} else {
					caixa.setRetirada(false);
				}
				new ControlerCaixa().lancamentoCaixa(caixa);
				}catch (SQLException e) {

				}catch (ClassNotFoundException e) {

				}catch (IOException e) {
				}
			}
		});
	}
}