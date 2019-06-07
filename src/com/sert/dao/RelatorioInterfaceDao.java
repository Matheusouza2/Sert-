package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioInterfaceDao {
	Connection con;

	public RelatorioInterfaceDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}

	public void relEstoque() {
		try {
			String sql = "SELECT * FROM cad_mercadorias ORDER BY nome_mercadoria ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			HashMap parametros = new HashMap();
			parametros.put("nome_fant", "M&K PAPELARIA");
			JDialog viewer = new JDialog(new javax.swing.JFrame(), "Estoque", true);
			viewer.setSize(1024, 768);
			viewer.setLocationRelativeTo(null);
			JasperPrint impressao = JasperFillManager.fillReport("src/relatorios/estoque.jasper", parametros,
					new JRResultSetDataSource(rs));
			JasperViewer view = new JasperViewer(impressao, true);
			viewer.getContentPane().add(view.getContentPane());
			viewer.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void orcamento(int id) {
		try {
			String sql = "SELECT * FROM cad_mercadorias ORDER BY nome_mercadoria ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			HashMap parametros = new HashMap();
			parametros.put("nome_fant", "M&K PAPELARIA");
			JDialog viewer = new JDialog(new javax.swing.JFrame(), "Estoque", true);
			viewer.setSize(800, 600);
			viewer.setLocationRelativeTo(null);
			JasperPrint impressao = JasperFillManager.fillReport("src/relatorios/estoque.jasper", parametros,
					new JRResultSetDataSource(rs));
			JasperViewer view = new JasperViewer(impressao, true);
			viewer.getContentPane().add(view.getContentPane());
			viewer.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}