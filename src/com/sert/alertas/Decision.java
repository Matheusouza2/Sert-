package com.sert.alertas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Decision extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static JPanel contentPanel = new JPanel();
	private JButton btnX;
	private static JButton btnSim;
	private static JButton btnNo;
	private static JLabel lblMenssagem;
	private static JLabel lblImage;
	public static int ALERT;
	public static int CANCEL;
	public static int INFO;
	public static int YES = 1;
	public static int NO = 0;
	public static int retorno = 0;
	public Decision() {
		setFocusableWindowState(true);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 450, 172);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(Color.YELLOW, 2, true));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnX = new JButton("X");
		btnX.setBackground(Color.RED);
		btnX.setForeground(Color.WHITE);
		btnX.setBounds(404, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnSim = new JButton("Sim");
		btnSim.setBounds(59, 138, 89, 23);
		btnSim.setBackground(Color.GREEN);
		btnSim.setForeground(Color.WHITE);

		btnNo = new JButton("Não");
		btnNo.setBounds(284, 138, 89, 23);
		btnNo.setBackground(Color.RED);
		btnNo.setForeground(Color.WHITE);

		lblMenssagem = new JLabel("");
		lblMenssagem.setForeground(new Color(255, 255, 255));
		lblMenssagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenssagem.setFont(new Font("Arial", Font.BOLD, 14));
		lblMenssagem.setBounds(91, 58, 349, 56);
		contentPanel.add(lblMenssagem);

		lblImage = new JLabel();
		lblImage.setBounds(10, 54, 71, 63);
		
		
		contentPanel.add(btnSim);
		contentPanel.add(btnNo);
	}

	public int AlertDecision(String mensagem, int opcoesAlert) {
		Decision alerta = new Decision();
		if (opcoesAlert == ALERT) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/alert.png")));
			contentPanel.add(lblImage);
		} else if (opcoesAlert == CANCEL) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/cancel.png")));
			contentPanel.add(lblImage);
		} else if (opcoesAlert == INFO) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/info.png")));
			contentPanel.add(lblImage);
		}

		lblMenssagem.setText(mensagem);
		
		btnSim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retorno = YES;
				alerta.dispose();
			}
		});

		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retorno = NO;
				alerta.dispose();
			}
		});
		alerta.setVisible(true);
		return retorno;
		
	}

}
