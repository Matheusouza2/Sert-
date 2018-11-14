package com.sert.controler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JDateField {
	
	private Date data = new Date();
	private SimpleDateFormat formatadorDate = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatadorDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private String date;
	private String dateHora;
	
	public JDateField() {
		this.date = formatadorDate.format(data);
		this.dateHora = formatadorDateHora.format(data);
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getDateHora(){
		return dateHora;
	}
}