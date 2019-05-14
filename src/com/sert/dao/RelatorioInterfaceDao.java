package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class RelatorioInterfaceDao {
	Connection con;
	
	public RelatorioInterfaceDao() throws ClassNotFoundException, SQLException, IOException {
		con = (Connection) ConexaoDao.getInstacia().getConector();
	}
	
	public void relEstoque() {
		try{
		       String sql = "SELECT * from clientes";
		       PreparedStatement stmt = con.prepareStatement(sql);
		       ResultSet rs = stmt.executeQuery();
		       HashMap parametros = new HashMap();
		       JasperPrint impressao = JasperFillManager.fillReport("./src/relatorios/rel_entrega_devolucao.jasper", parametros, new JRResultSetDataSource(rs));
		       JasperViewer view = new JasperViewer(impressao, false);
		       view.setTitle("Relatorio de Entrega e Devolução");
		       view.setZoomRatio(0.75F);
		       view.setExtendedState(MAXIMIZED_BOTH);
		       view.setVisible(true);
		    }catch(Exception e){
		       e.printStackTrace();
		       JOptionPane.showMessageDialog(null, e.getMessage());
		    }
	}
	
	public void orcamento() {
		try{
		       String sqlConsulta = "SELECT * from clientes";
		       PreparedStatement stmt= (PreparedStatement) con_relat.conexao.prepareStatement(sqlConsulta);
		       ResultSet rs = stmt.executeQuery();
		       HashMap parametros = new HashMap();
		       JasperPrint impressao = JasperFillManager.fillReport("./src/relatorios/rel_entrega_devolucao.jasper", parametros, new JRResultSetDataSource(rs));
		       JasperViewer view = new JasperViewer(impressao, false);
		       view.setTitle("Relatorio de Entrega e Devolução");
		       view.setZoomRatio(0.75F);
		       view.setExtendedState(MAXIMIZED_BOTH);
		       view.setVisible(true);
		    }catch(Exception e){
		       e.printStackTrace();
		       JOptionPane.showMessageDialog(null, e.getMessage());
		    }
	}
}