package com.sert.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.ParseException;

import com.sert.valida.LiberacaoCnpj;
import com.sert.valida.LiberacaoData;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.0.5
 * 
 */
public class Main {
	public static void main(String[] args) throws ParseException {

		try {

			System.out.println(LiberacaoData.isDataOk());

			System.out.println(LiberacaoCnpj.isCnpjLiberado());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

//		try {
//			new Entrada().setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}