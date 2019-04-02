package com.sert.main;

import com.sert.controler.JDateField;
import com.sert.telas.Entrada;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.0
 * 
 */
public class Main {

	public static void main(String[] args) {
		try {
			new Entrada().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}