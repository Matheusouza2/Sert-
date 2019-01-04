package com.sert.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sert.controler.Log;

public class ConexaoDao {
	private static Connection con = null;
	private static ConexaoDao self = null;

	protected Connection getConector() throws SQLException, ClassNotFoundException, IOException {

		if (con == null) {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sertbd?autoReconnect=true&useSSL=false", "postgres", "s3rt+");
			new Log().gravaLog("Conectado ao banco de dados com Sucesso");
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
	
	public boolean testeConexao() throws ClassNotFoundException, SQLException, IOException {
		boolean teste = false;
		if(getInstacia() != null) {
			teste = true;
		}
		return teste;
	}

	@Override
	protected void finalize() throws Throwable {
		if (self != null) {
			desconectar();
		}
	}

	public void desconectar() throws SQLException {
		con.close();
	}
}