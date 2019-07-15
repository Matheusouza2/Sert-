package com.sert.impressao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

import com.sert.controler.ControlerEmpresa;
import com.sert.entidades.DuplicataCliente;
import com.sert.entidades.Empresa;

public class PrintableComprovanteDuplicata {

	private INPUT_STREAM docFlavor;
	private SimpleDoc documentoTexto;
	private PrintService impressora;
	private File arquivo;
	private String caminho;

	public PrintableComprovanteDuplicata(DuplicataCliente duplicata) {
		// ESCREVER TXT
		try {
			caminho = "duplicatacomp.txt";
			arquivo = new File(caminho);

			// se existir
			FileWriter arquivoTxt = new FileWriter(arquivo, true);
			PrintWriter linhasTxt = new PrintWriter(arquivoTxt);

			// Pegar informações da Empresa
			Empresa empresa = new ControlerEmpresa().listEmpresa().get(0);

			// ACREDITO QUE SO PODE TER 42 CARACTERES
			linhasTxt.println("          " + empresa.getNomeFant() + "          ");
			linhasTxt.println("          " + empresa.getRua() + "," + empresa.getNumero() + "          ");
			linhasTxt.println("          " + empresa.getCidade() + " - " + empresa.getUf() + "          ");
			linhasTxt.println("          " + empresa.getContato() + "           ");
			linhasTxt.println("================================================");
			linhasTxt.println("*********** COMPROVANTE DE PAGAMENTO ***********");
			linhasTxt.println("================================================");
			linhasTxt.println("	  Comprovo que o cliente " + duplicata.getCliente().getNome());
			linhasTxt.println("	  pagou o valor de " + String.format("R$ %.2f", duplicata.getValorBaixa()));
			linhasTxt.println("	  referente ao debito da venda " + duplicata.getVenda().getId());
			linhasTxt.println("	  no dia " + duplicata.getDataBaixa());
			linhasTxt.println("===============================================");
			linhasTxt.println();
			linhasTxt.println("SertSoft sistema de gestao comercial para MEI  ");
			linhasTxt.println("(87) 98875-4442				          ");

			int i = 0;
			while (i < 10) {
				i++;
				linhasTxt.println();
			}
			linhasTxt.print("" + (char) 27 + "@" + (char) 29 + "V" + (char) 1);
			arquivoTxt.close();
			// emiteComanda();

		} catch (IOException error) {
			System.out.println("nao encontrei arquivo");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// imprime arquivo
		try {
			InputStream print = new FileInputStream(caminho);

			docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			documentoTexto = new SimpleDoc(print, docFlavor, null);

			impressora = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();

			printerAttributes.add(MediaSizeName.ISO_A4);

			// cria a tarefa de impressao

			DocPrintJob printJob = impressora.createPrintJob();

			// tenta imprimir

			printJob.print(documentoTexto, (PrintRequestAttributeSet) printerAttributes);

			print.close();

			arquivo.delete();

			arquivo = new File(caminho);
			arquivo.createNewFile();

		} catch (IOException | PrintException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro encontrado ao imprimir cupom.");
		}
	}
}