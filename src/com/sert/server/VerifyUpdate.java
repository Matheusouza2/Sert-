package com.sert.server;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class VerifyUpdate {
	
	private static boolean up = false;
	private static int versaoAt = 0;
	
	public VerifyUpdate() throws IOException {
		URL url = new URL("https://sertsoft.000webhostapp.com/php/license.php?currentVersion=" + versaoAt);

		Scanner scanner = new Scanner(url.openStream());
		String atualiza = scanner.next();
		scanner.close();

		if (!atualiza.equals("")) {
			int resposta = JOptionPane.showConfirmDialog(null,
					"Existem atualizações disponiveis para seu sistema, deseja baixar ?", "Atualização disponivel",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				up = true;
			} else {
				up = false;
			}
		} else {
			up = false;
		}
	}

	public static boolean up(int versao){
		versaoAt = versao;
		try {
			new VerifyUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return up;
	}
}