package com.sert.alertas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Info extends JDialog {

	private static final long serialVersionUID = 1L;

	private final static JPanel contentPanel = new JPanel();
	private static JButton btnX;
	private static JLabel lblMenssagem;
	private static JLabel lblImage;
	private static JButton btnOk;
	public static final int ALERT = 5;
	public static final int CANCEL = 6;
	public static final int INFO = 7;

	public Info() {
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

		btnOk = new JButton("OK");
		btnOk.setBounds(180, 138, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
			
		lblMenssagem = new JLabel("");
		lblMenssagem.setForeground(new Color(255, 255, 255));
		lblMenssagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenssagem.setFont(new Font("Arial", Font.BOLD, 14));
		lblMenssagem.setBounds(91, 58, 349, 56);
		contentPanel.add(lblMenssagem);

		lblImage = new JLabel("");
		lblImage.setBounds(10, 54, 71, 63);
		contentPanel.add(lblImage);
	}

	public static void AlertInfo(String mensagem, int opcoesAlert) {
		Info alerta = new Info();
		
		if (opcoesAlert == ALERT) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/alert.png")));
		} else if (opcoesAlert == CANCEL) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/cancel.png")));
		} else if (opcoesAlert == INFO) {
			lblImage.setIcon(new ImageIcon(Info.class.getResource("/com/sert/img/info.png")));
		}

		lblMenssagem.setText(mensagem);
		contentPanel.add(btnOk);
		
		alerta.setVisible(true);
	}
}