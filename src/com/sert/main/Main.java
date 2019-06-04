package com.sert.main;

import com.sert.telas.CadCliente;
import com.sert.telas.CadMercadoria;
import com.sert.telas.Entrada;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.5
 * OC HONDA: 2432057
 * 
 */
public class Main {
	public static void main(String[] args) {
		
		try {
			new CadCliente(0, 0).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}