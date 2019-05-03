package com.sert.editableFields;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class JDocumentFormatedField {

	public JTextField getCep(){
		MaskFormatter format_textField3;
		JTextField tfcep = null;
		try {
			format_textField3 = new MaskFormatter("#####-###");
			tfcep = new JFormattedTextField(format_textField3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tfcep;
	}
	
	public JTextField getTel(){
		MaskFormatter format_textField3;
		JTextField tfcep = null;
		try {
			format_textField3 = new MaskFormatter("(##)#####-####");
			tfcep = new JFormattedTextField(format_textField3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tfcep;
	}
	
	public JTextField getCpf(){
		JTextField tfcpf = null;
		MaskFormatter format_textField3;
		try {
			format_textField3 = new MaskFormatter("###.###.###-##");
			tfcpf = new JFormattedTextField(format_textField3);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return tfcpf;
	}
	
	public JTextField getCnpj(){
		JTextField tfcnpj = null;
		MaskFormatter format_textField3;
		try {
			format_textField3 = new MaskFormatter("##.###.###/####-##");
			tfcnpj = new JFormattedTextField(format_textField3);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return tfcnpj;
	}
	
	public JTextField getData(){
		JTextField tfData = null;
		MaskFormatter format_textField3;
		try {
			format_textField3 = new MaskFormatter("##/##/####");
			tfData = new JFormattedTextField(format_textField3);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return tfData;
	}
}