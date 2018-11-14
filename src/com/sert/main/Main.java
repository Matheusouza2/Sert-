package com.sert.main;

import javax.swing.UIManager;

import com.sert.telas.Entrada;
import com.sert.telas.Inicio;
/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * @author Matheus Souza
 * @version 1.0.0
 * 
 * */
public class Main {

	public static void main(String[] args) {
		try {
			
			new Inicio().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}