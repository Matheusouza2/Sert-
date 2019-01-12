package com.sert.telas;

import java.awt.BorderLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

import com.sert.controler.JDateField;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BackupSistema extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextComponent textArea;
	private JProgressBar progressBar;

	public BackupSistema() {
		setBounds(100, 100, 651, 451);
		setModal(true);
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.YELLOW);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setForeground(Color.BLUE);
		panel.setBorder(new TitledBorder(null, "Backup", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 631, 378);
		contentPanel.add(panel);
		panel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 611, 356);
		panel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		progressBar = new JProgressBar();
		progressBar.setBounds(10, 400, 631, 30);
		contentPanel.add(progressBar);

		progressBar.setStringPainted(true);
		progressBar.setString("Aguarde...");
		progressBar.setIndeterminate(true);
		new SwingWorker<Object, Object>() {
			@Override
			protected Object doInBackground() throws Exception {
				final List<String> comandos = new ArrayList<String>();
				String data = JDateField.getDate().replace("/", "_");
				String dir = "C:/Sert+/Backup/" + data + ".backup";
				comandos.add("C:/Program Files/PostgreSQL/10/bin/pg_dump.exe");

				comandos.add("-h");
				comandos.add("localhost");
				comandos.add("-p");
				comandos.add("5432");
				comandos.add("-U");
				comandos.add("postgres");
				comandos.add("-F");
				comandos.add("c");
				comandos.add("-b");
				comandos.add("-v");
				comandos.add("-f");
				comandos.add(dir);

				comandos.add("sertbd");
				ProcessBuilder pb = new ProcessBuilder(comandos);

				pb.environment().put("PGPASSWORD", "s3rt+");

				try {
					final Process process = pb.start();

					final BufferedReader r = new BufferedReader(new InputStreamReader(process.getErrorStream()));
					String line = r.readLine();
					while (line != null) {
						((JTextArea) textArea).append(line + "\n");
						textArea.setCaretPosition(textArea.getDocument().getLength());
						Thread.sleep(50);
						line = r.readLine();
					}
					r.close();

					process.waitFor();
					process.destroy();
					JOptionPane.showMessageDialog(null, "backup realizado com sucesso na pasta " + dir);

				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				return null;
			}

			@Override
			protected void done() {
				dispose();
				super.done();
			}
		}.execute();
		;
	}
}