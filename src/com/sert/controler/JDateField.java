package com.sert.controler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JDateField {
	
	private static Date data = new Date();
	private static SimpleDateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat formatadorDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static String getDate(){
		return formatadorDate.format(data);
	}
	
	public static String getDateHora(){
		return formatadorDateHora.format(data);
	}
}