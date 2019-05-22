package com.sert.valida;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class LiberacaoCnpj {

	private static boolean cnpjLiberado = false;

	protected LiberacaoCnpj() throws IOException {
		URL url = new URL("https://sertsoft.000webhostapp.com/license.php?cnpj=31199696000150");

		Scanner scanner = new Scanner(url.openStream());
		String liberacao = scanner.next();
		scanner.close();

		if (liberacao.equals("free")) {
			cnpjLiberado = true;
		}
	}
	
	public static boolean isCnpjLiberado() throws IOException {
		new LiberacaoCnpj();
		return cnpjLiberado;
	}
}