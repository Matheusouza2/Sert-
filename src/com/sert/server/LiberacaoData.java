package com.sert.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LiberacaoData {
	private static boolean dataOk = false;

	protected LiberacaoData() {

		URL url;
		try {
			url = new URL("https://sertsoft.000webhostapp.com/license.php?date");
			Scanner in = new Scanner(url.openStream());

			String dataServ = in.next();
			in.close();

			Date data = new Date();
			SimpleDateFormat formatadorDate = new SimpleDateFormat("yyyy-MM-dd");
			String dataLoc = formatadorDate.format(data);

			if (dataLoc.equals(dataServ)) {
				dataOk = true;
			}
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null,
					"Não conseguimos sincronizar com nossos servidores, entre em contato com o suporte!");
			dataOk = true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Não conseguimos sincronizar com nossos servidores, entre em contato com o suporte!");
			dataOk = true;
		}
	}

	public static boolean isDataOk() throws IOException {
		new LiberacaoData();
		return dataOk;
	}
}