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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.sert.controler.ControlerCliente;
import com.sert.controler.Log;
import com.sert.controler.PermissoesStatic;
import com.sert.entidades.Cliente;

public class ListarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	private List<Cliente> clientes;
	private JButton btnX;
	private JPanel panelBtn;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTable tabCliente;
	private int opcao;
	private JButton btnSelecionar;
	private Cliente cliente;

	public ListarCliente(int opcao) {
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

		this.opcao = opcao;

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
		btnEditar.setIcon(new ImageIcon(ListarCliente.class.getResource("/com/sert/img/btnEditarCliente.png")));
		btnEditar.setBounds(10, 11, 89, 91);
		btnEditar.setBackground(Color.ORANGE);
		btnEditar.setBorderPainted(false);
		panelBtn.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tabCliente.getSelectedRow() >= 0) {
					int idEditar = Integer
							.parseInt(String.valueOf(tabCliente.getValueAt(tabCliente.getSelectedRow(), 0)));
					if (idEditar == 0) {
						JOptionPane.showMessageDialog(null, "O cliente Consumidor não pode ser editado", "Advertência",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					new CadCliente(1, idEditar).setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente a ser editado", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnExcluir = new JButton();
		btnExcluir.setIcon(new ImageIcon(ListarUsu.class.getResource("/com/sert/img/btnExcluir.png")));
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBackground(Color.RED);
		btnExcluir.setBounds(109, 11, 89, 91);
		panelBtn.add(btnExcluir);
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabCliente.getSelectedRow() >= 0) {
					int idCliente = Integer
							.parseInt(String.valueOf(tabCliente.getValueAt(tabCliente.getSelectedRow(), 0)));
					if (idCliente == 0) {
						JOptionPane.showMessageDialog(null, "O cliente Consumidor não pode ser excluido", "Advertência",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					try {
						new ControlerCliente().excluirCliente(idCliente);
						repagina();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes",
								"Sistema", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
								"Banco de dados", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes",
								"Arquivo", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um cliente a ser excluido", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSelecionar = new JButton();
		btnSelecionar.setIcon(new ImageIcon(ListarCliente.class.getResource("/com/sert/img/btnSelecionarCliente.png")));
		btnSelecionar.setBorderPainted(false);
		btnSelecionar.setBackground(Color.GREEN);
		btnSelecionar.setBounds(10, 11, 89, 91);
		panelBtn.add(btnSelecionar);
		
		selecionarCliente();
		
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
			clientes = new ControlerCliente().listCliente();
			for (Cliente cliente : clientes) {
				modelo.addRow(new Object[] { cliente.getId(), cliente.getNome() });
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		}
		tabCliente = new JTable();
		tabCliente.getTableHeader().setReorderingAllowed(false);
		tabCliente.setModel(modelo);
		scrollPane.setViewportView(tabCliente);
		
		getPermissoes();
	}

	private void repagina() {
		try {
			while (tabCliente.getRowCount() > 0) {
				modelo.removeRow(0);
			}
			tabCliente.setModel(modelo);
			clientes.clear();
			clientes = new ControlerCliente().listCliente();
			for (Cliente cliente : clientes) {
				modelo.addRow(new Object[] { cliente.getId(), cliente.getNome() });
			}
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
					"Banco de dados", JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
					JOptionPane.ERROR_MESSAGE);
			Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
		}

		tabCliente.revalidate();

	}

	public Cliente selecionarCliente() {
		if (opcao == 1) {
			btnEditar.setVisible(false);
			btnExcluir.setVisible(false);

			btnSelecionar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(tabCliente.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, "Selecione um cliente para continuar");
						return;
					}
					int id = Integer.parseInt(String.valueOf(tabCliente.getValueAt(tabCliente.getSelectedRow(), 0)));
					
					try {
						cliente = new ControlerCliente().consultaClienteAlter(id);
						dispose();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Classe não encontrada, veja o log para mais detalhes", "Sistema",
								JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Erro de banco de dados, veja o log para mais detalhes",
								"Banco de dados", JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro de escrita de arquivo, veja o log para mais detalhes", "Arquivo",
								JOptionPane.ERROR_MESSAGE);
						Log.gravaLog("| LISTAR CLIENTE |" + e1.getMessage());
					}
				}
			});
		} else {
			btnSelecionar.setVisible(false);
		}	
		return cliente;
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
		if(!PermissoesStatic.permissoesFunc.isAltCliente()) {
			btnEditar.setEnabled(false);
		}
		if(!PermissoesStatic.permissoesFunc.isExclCliente()) {
			btnExcluir.setEnabled(false);
		}
	}
}