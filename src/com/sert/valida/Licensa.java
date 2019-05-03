package com.sert.valida;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import com.sert.controler.ControlerEmpresa;
import com.sert.entidades.Empresa;

public class Licensa {

	public static void main(String[] args) {
		try {
			String mac = NetworkInfo.getMacAddress();
			String cnpj = "";
			ControlerEmpresa empresa = new ControlerEmpresa();
			List<Empresa> empresas = empresa.listEmpresa();
			for (int i = 0; i < empresas.size(); i++) {
				cnpj = String.valueOf(empresas.get(i).getCnpj());
			}
			System.out.println(mac+"\n"+cnpj);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}