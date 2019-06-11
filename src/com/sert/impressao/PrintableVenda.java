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
import com.sert.entidades.Empresa;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;

public class PrintableVenda {

	private INPUT_STREAM docFlavor;
	private SimpleDoc documentoTexto;
	private PrintService impressora;
	private File arquivo;
	private String caminho;

	public PrintableVenda(Venda venda) {

		// ESCREVER TXT
		try {
			caminho = "venda.txt";
			arquivo = new File(caminho);

			// se existir
			FileWriter arquivoTxt = new FileWriter(arquivo, true);
			PrintWriter linhasTxt = new PrintWriter(arquivoTxt);

			// Pegar informações da venda
			Empresa empresa = new ControlerEmpresa().listEmpresa().get(0);

			// ACREDITO QUE SO PODE TER 42 CARACTERES
			linhasTxt.println("          " + empresa.getNomeFant() + "          ");
			linhasTxt.println("          " + empresa.getRua() + "," + empresa.getNumero() + "          ");
			linhasTxt.println("          " + empresa.getCidade() + " - " + empresa.getUf() + "          ");
			linhasTxt.println("          " + empresa.getContato() + "           ");
			linhasTxt.println("================================================");
			linhasTxt.println("************ NAO E DOCUMENTO FISCAL ************");
			linhasTxt.println("================================================");
			linhasTxt.println("PRODUTO      QTDE      VALOR UN.      VALOR");
			// dados da venda

			for (Mercadoria mercadoria : venda.getMercadorias()) {
				linhasTxt.print(String.format("%10s", mercadoria.getMercadoria()));
				linhasTxt.println();
				linhasTxt.print(String.format("%15s     ", mercadoria.getEstoque()));
				linhasTxt.print(String.format("%10s    ", mercadoria.getPrecoVenda()));
				linhasTxt.print(String.format("%9s    ", mercadoria.getEstoque() * mercadoria.getPrecoVenda()));
				linhasTxt.println();
			}
			linhasTxt.println();
			linhasTxt.println("------------------------------------------------");
			linhasTxt.println("Total                             R$ " + venda.getValTotal());
			linhasTxt.println("------------------------------------------------");
			linhasTxt.println("Cliente: " + venda.getCliente());
			linhasTxt.println("Vendedor: " + venda.getVendedor());
			linhasTxt.println("Data: " + venda.getDataVenda());
			linhasTxt.println("===============================================");
			linhasTxt.println("           AGRADECEMOS SUA PRESENCA            ");
			linhasTxt.println("                 VOLTE SEMPRE                  ");
			linhasTxt.println();
			linhasTxt.println("SertSoft (87) 98875-4442				          ");

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