package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sert.controler.PropriedadesControler;

public class ConexaoDao {
	private static Connection con = null;
	private static ConexaoDao self = null;

	protected Connection getConector() throws SQLException, ClassNotFoundException, IOException {
		PropriedadesControler controler = new PropriedadesControler();
		if (con == null) {
			Class.forName("org.postgresql.Driver");
			//" + controler.getHost() + "
			con = DriverManager.getConnection("jdbc:postgresql://"+ controler.getHost() +":" + controler.getPort()
					+ "/sertbd?autoReconnect=true&useSSL=false", controler.getLogin(), controler.getPassword());
		}
		return con;
	}

	public static synchronized ConexaoDao getInstacia() {
		if (self == null) {
			self = new ConexaoDao();
			return self;
		} else {
			return self;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (self != null) {
			desconectar();
		}
	}

	private void desconectar() throws SQLException {
		con.close();
	}

	public static boolean testarCon() throws ClassNotFoundException, SQLException, IOException {
		if (getInstacia().getConector() != null) {
			return true;
		}
		return false;
	}
}