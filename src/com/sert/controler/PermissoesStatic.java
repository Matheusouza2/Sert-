package com.sert.controler;

import java.io.IOException;
import java.sql.SQLException;

import com.sert.entidades.PermissoesFunc;

public class PermissoesStatic {

	public static PermissoesFunc permissoesFunc;

	public static void preenchePermissoes(int idUsu) throws ClassNotFoundException, SQLException, IOException {
		permissoesFunc = new ControlerUsuario().consultaPermicoes(idUsu);
	}
}