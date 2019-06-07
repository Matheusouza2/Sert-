package com.sert.telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerUsuario;
import com.sert.controler.Log;
import com.sert.controler.PermissoesStatic;
import com.sert.entidades.Usuario;
import com.sert.exceptions.NenhumUsuCadException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListarUsu extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnEditar;
	private static JTable tabUsu;
	private JButton btnNovoUsuario;
	private JScrollPane scrollPane;
	private static DefaultTableModel modelo;
	private static List<Usuario> usuarios;
	private JButton btnExcluir;

	public ListarUsu() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 834, 590);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setModal(true);
		contentPanel.setBackground(new Color(0, 0, 128));
		contentPanel.setBorder(new LineBorder(new Color(255, 255, 0), 2, true));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		listen();

		btnX = new JButton("X");
		btnX.setBounds(788, 0, 46, 23);
		contentPanel.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setBackground(Color.RED);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		panelBtn = new JPanel();
		panelBtn.setBackground(new Color(255, 255, 0));
		panelBtn.setBounds(10, 34, 814, 113);
		panelBtn.setBorder(new LineBorder(new Color(41, 171, 226), 2, true));
		contentPanel.add(panelBtn);
		panelBtn.setLayout(null);

		btnEditar = new JButton();
		btnEditar.setIcon(new ImageIcon(ListarUsu.class.getResource("/com/sert/img/btnEditarUsuario.png")));
		btnEditar.setBounds(109, 11, 89, 91);
		btnEditar.setBackground(Color.ORANGE);
		btnEditar.setBorderPainted(false);
		panelBtn.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabUsu.getSelectedRow() >= 0) {
					int idEditar = Integer.parseInt(String.valueOf(tabUsu.getValueAt(tabUsu.getSelectedRow(), 0)));
					if (idEditar == 0) {
						JOptionPane.showMessageDialog(null, "O usuário SertSoft não pode ser editado", "Advertência",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					new CadUsu(1, idEditar).setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Selecione um usuário a ser editado", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnNovoUsuario = new JButton();
		btnNovoUsuario.setIcon(new ImageIcon(ListarUsu.class.getResource("/com/sert/img/btnNovoUsu.png")));
		btnNovoUsuario.setBorderPainted(false);
		btnNovoUsuario.setBackground(Color.GREEN);
		btnNovoUsuario.setBounds(10, 11, 89, 91);
		panelBtn.add(btnNovoUsuario);
		btnNovoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CadUsu(0, 0).setVisible(true);
			}
		});

		btnExcluir = new JButton();
		btnExcluir.setIcon(new ImageIcon(ListarUsu.class.getResource("/com/sert/img/btnExcluir.png")));
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(208, 11, 89, 91);
		panelBtn.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabUsu.getSelectedRow() >= 0) {
					int idUsu = Integer.parseInt(String.valueOf(tabUsu.getValueAt(tabUsu.getSelectedRow(), 0)));
					if (idUsu == 0) {
						JOptionPane.showMessageDialog(null, "O usuário SertSoft não pode ser excluido", "Advertência",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						new ControlerUsuario().excluirUsuario(idUsu);
						repagina();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes",
								"Sistema", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR USU |" + e1.getMessage());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
								"Banco de dados", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR USU |" + e1.getMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes",
								"Arquivo", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR USU |" + e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um usuário a ser excluido", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 156, 814, 423);
		contentPanel.add(scrollPane);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("ID");
		modelo.addColumn("Nome");

		try {
			usuarios = new ControlerUsuario().listarUsuario();
			for (Usuario usu : usuarios) {
				modelo.addRow(new Object[] { usu.getId(), usu.getNome() });
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (NenhumUsuCadException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		}
		tabUsu = new JTable();
		tabUsu.getTableHeader().setReorderingAllowed(false);
		tabUsu.setModel(modelo);
		scrollPane.setViewportView(tabUsu);

		getPermissoes();
	}

	public static void repagina() {
		try {
			while (tabUsu.getRowCount() > 0) {
				modelo.removeRow(0);
			}
			tabUsu.setModel(modelo);
			usuarios.clear();
			usuarios = new ControlerUsuario().listarUsuario();
			for (Usuario usu : usuarios) {
				modelo.addRow(new Object[] { usu.getId(), usu.getNome() });
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (NenhumUsuCadException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR USU |" + e1.getMessage());
		}

		tabUsu.revalidate();

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

		JRootPane delete = getRootPane();
		delete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				"DELETE");
		delete.getRootPane().getActionMap().put("DELETE", new AbstractAction("DELETE") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				btnExcluir.doClick();
			}
		});
	}

	private void getPermissoes() {
		if (!PermissoesStatic.permissoesFunc.isCadFunc()) {
			btnNovoUsuario.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isAltFunc()) {
			btnEditar.setEnabled(false);
		}
		if (!PermissoesStatic.permissoesFunc.isExclFunc()) {
			btnExcluir.setEnabled(false);
		}
	}
}