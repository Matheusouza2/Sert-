package com.sert.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.print.DocFlavor;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JOptionPane;

import com.sert.controler.ControlerEmpresa;
import com.sert.controler.ControlerVenda;
import com.sert.entidades.Empresa;
import com.sert.entidades.Mercadoria;
import com.sert.entidades.Venda;
import com.sert.exceptions.NenhumaMercadoriaCadastradaException;
import com.sert.exceptions.VendaNaoEncontradaException;

public class Teste {

	private static INPUT_STREAM docFlavor;
	private static SimpleDoc documentoTexto;
	private static PrintService impressora;
	private static PrintService servico;
	private static File arquivo;
	private static String caminho;

	public static void main(String[] args) {

		// ESCREVER TXT
		try {
			caminho = "venda.txt";
			arquivo = new File(caminho);

			// se existir
			FileWriter arquivoTxt = new FileWriter(arquivo, true);
			PrintWriter linhasTxt = new PrintWriter(arquivoTxt);

			// Pegar informações da venda
			Venda venda = new ControlerVenda().imprimirVenda(37);
			Empresa empresa = new ControlerEmpresa().listEmpresa().get(0);

			// ACREDITO QUE SO PODE TER 42 CARACTERES
			linhasTxt.println("          " + empresa.getNomeFant() + "          ");
			linhasTxt.println("          " + empresa.getRua() + ","+empresa.getNumero()+"          ");
			linhasTxt.println("          " + empresa.getCidade() + " - "+empresa.getUf()+"          ");
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

			linhasTxt.println("===========================================");
			linhasTxt.println("   INFORMACOES PARA FECHAMENTO DE CONTA    ");
			linhasTxt.println("===========================================");
			linhasTxt.println(" GARCOM  CONTA.DIV.  VAL.PESS. COVER  DESC.");
			linhasTxt.print(
					String.format("%7s  %9s  %9s  %5s   %s", "garcom", "dividirconta", "vlpessoa", "cover", "desconto"
					// garcom.getText(),
					// dividirConta.getText(),
					// valorPorPessoa.getText(),
					// cover.getText(),
					// descontoConta.getText()
					));
			linhasTxt.println();
			linhasTxt.println("===========================================");

			linhasTxt.println("SubTotal                            " + "10,00");
			linhasTxt.println("                   ------------------------");

			linhasTxt.println("Total                                " + venda.getValTotal());
			linhasTxt.println("===============================================");
			linhasTxt.println("           AGRADECEMOS SUA PRESENÇA            ");
			linhasTxt.println("                 VOLTE SEMPRE                  ");
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
		} catch (VendaNaoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NenhumaMercadoriaCadastradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// imprime arquivo
		try {
			InputStream print = new FileInputStream(caminho);

			docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			documentoTexto = new SimpleDoc(print, docFlavor, null);

			PrintService[] servicosImpressao = PrintServiceLookup.lookupPrintServices(docFlavor, null);
			impressora = PrintServiceLookup.lookupDefaultPrintService();

			PrintRequestAttributeSet printerAttributes = new HashPrintRequestAttributeSet();
			// modelo do papel ****** opcional
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
			JOptionPane.showMessageDialog(null, "Erro encontrado ao imprimir comanda.");
		}
	}
}