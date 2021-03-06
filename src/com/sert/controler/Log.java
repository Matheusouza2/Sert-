package com.sert.controler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class Log {
	
	public static void gravaLog(String avsLog) {

		Calendar calendar = new GregorianCalendar();
		
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		int mes = calendar.get(GregorianCalendar.MONTH) + 1;
		int ano = calendar.get(GregorianCalendar.YEAR);
		int hora = calendar.get(GregorianCalendar.HOUR_OF_DAY);
		int minuto = calendar.get(GregorianCalendar.MINUTE);
		int sec = calendar.get(GregorianCalendar.SECOND);
		
		
		String caminho = "C:/Program Files/Sert+/Logs/" + dia + ".txt";

		File file = new File(caminho);

		FileWriter arq;

		try {
			arq = new FileWriter(file.getAbsoluteFile(), true);

			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.println(dia + "/" + mes + "/" + ano + " " + hora + ":" + minuto + ":" + sec + " --> " + avsLog);

			arq.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na abertura ou escrita do arquivo de Log \n" + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}