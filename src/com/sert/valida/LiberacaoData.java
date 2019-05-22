package com.sert.valida;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LiberacaoData {
	private static boolean dataOk = false;

	protected LiberacaoData() throws IOException {

		URL url = new URL("https://sertsoft.000webhostapp.com/date.php");

		Scanner in = new Scanner(url.openStream());

		String dataServ = in.next();
		in.close();

		Date data = new Date();
		SimpleDateFormat formatadorDate = new SimpleDateFormat("yyyy-MM-dd");
		String dataLoc = formatadorDate.format(data);

		if (dataLoc.equals(dataServ)) {
			dataOk = true;
		}
	}

	public static boolean isDataOk() throws IOException {
		new LiberacaoData();
		return dataOk;
	}
}