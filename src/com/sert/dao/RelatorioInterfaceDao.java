package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioInterfaceDao {
	Connection con;
	
	public RelatorioInterfaceDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}
	
	public void relEstoque() {
		try{
		       String sql = "SELECT * FROM cad_mercadorias ORDER BY nome_mercadoria ASC";
		       PreparedStatement stmt = con.prepareStatement(sql);
		       ResultSet rs = stmt.executeQuery();
		       HashMap parametros = new HashMap();
		       parametros.put("nome_fant", "M&K PAPELARIA");
		       JDialog viewer = new JDialog(new javax.swing.JFrame(),"Estoque", true);
		       viewer.setSize(1024,768);
		       viewer.setLocationRelativeTo(null);
		       JasperPrint impressao = JasperFillManager.fillReport("src/relatorios/estoque.jasper", parametros, new JRResultSetDataSource(rs));
		       JasperViewer view = new JasperViewer(impressao, true);
		       viewer.getContentPane().add(view.getContentPane());
		       viewer.setVisible(true);
		    }catch(Exception e){
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
	
	public void venda(int id) {
		try {
			String sql = "SELECT f.nome as func_nome, cl.nome as cliente_nome, v.id, v.data_venda, v.val_total, v.acrescimo, v.desconto, v.val_dinheiro, v.val_cartao, cm.id as id_merc, cm.cod_barras, cm.nome_mercadoria, vm.valor_un, vm.quantidade FROM vendas v INNER JOIN funcionario f ON v.vendedor = f.id INNER JOIN clientes cl ON v.cliente = cl.id INNER JOIN venda_merc vm ON v.id = vm.id INNER JOIN cad_mercadorias cm ON vm.id_merc = cm.id WHERE v.id ="+id;
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			HashMap parametros = new HashMap();
			parametros.put("nome_fant", "M&K PAPELARIA");
			JDialog viewer = new JDialog(new javax.swing.JFrame(), "Estoque", true);
			viewer.setSize(800, 600);
			viewer.setLocationRelativeTo(null);
			JasperPrint impressao = JasperFillManager.fillReport("src/relatorios/venda.jasper", parametros,
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