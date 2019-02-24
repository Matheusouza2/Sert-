package com.sert.controler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JDateField {
	
	private Date data = new Date();
	private static Date dataStatic = new Date();
	private static SimpleDateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");;
	private static SimpleDateFormat formatadorDateHoraStatic = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");;
	private SimpleDateFormat formatadorDateHora;
	
	public JDateField() {
		formatadorDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	public static String getDate(){
		return formatadorDate.format(dataStatic);
	}
	 
	public String getDateHora(){
		return formatadorDateHora.format(data);
	}
	
	public static String getDateHoraStatic(){
		return formatadorDateHoraStatic.format(dataStatic);
	}
}