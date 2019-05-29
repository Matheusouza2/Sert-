-----Inicio da documenta��o de versionamento----
Vers�o 1.0.5
Funcionalidades Basicas do sistema funcinando:
	Cadastro de usuarios
	Cadastro de Mercadorias
	Lan�amento de notas por XML
	Lan�amento de Vendas
	Impress�o de vendas em impressora n�o fiscal
	Relatorio de Estoque
	Relatorio de Vendas
	Relatorio de Caixa
Novidade da vers�o: 
	Implementado o Cadastro de clientes
	Lan�amentos de compras na ficha do cliente 
	Implementado sistema de seguran�a 
	Lan�amentos de mercadorias em Consigna��o
	
	
	
	
	package com.teste.main;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.print.PrintService;
import javax.swing.JOptionPane;

public class Teste {

	public static void main(String[] args) {

//		for (PrintService p : PrinterJob.lookupPrintServices()) {  
//            System.out.println(p.getName());  
//		}
		
		// ESCREVER TXT
		try {
			File arquivo = new File("teste.txt");
			if (arquivo.exists()) {
				// se existir
				FileWriter arquivoTxt = new FileWriter(arquivo, true);
				PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
				// ACREDITO QUE SO PODE TER 42 CARACTERES
				linhasTxt.println("==========================================");
				linhasTxt.println("              Nome da empresa              ");
				linhasTxt.println("===========================================");
				linhasTxt.println("********** NAO E DOCUMENTO FISCAL *********");
				linhasTxt.println("===========================================");
				linhasTxt.println("PRODUTO      QTDE      VALOR UN.      VALOR");
				// dados da tabela
				/*
				 * for(int x = 0; x < tabelaConsumo.getRowCount(); x++){
				 * linhasTxt.print(String.format("%-10.10s",tabelaConsumo.getModel().getValueAt(
				 * x, 1)));
				 * linhasTxt.print(String.format("%7s     ",tabelaConsumo.getModel().getValueAt(
				 * x, 5)));
				 * linhasTxt.print(String.format("%10s    ",tabelaConsumo.getModel().getValueAt(
				 * x, 4)));
				 * linhasTxt.print(String.format("%7s    ",tabelaConsumo.getModel().getValueAt(
				 * x, 6))); linhasTxt.println(); }
				 */
				linhasTxt.println("===========================================");
				linhasTxt.println("   INFORMACOES PARA FECHAMENTO DE CONTA    ");
				linhasTxt.println("===========================================");
				linhasTxt.println(" GARCOM  CONTA.DIV.  VAL.PESS. COVER  DESC.");
				linhasTxt.print(String.format("%7s  %9s  %9s  %5s   %s", "garcom", "dividirconta", "vlpessoa", "cover",
						"desconto"
				// garcom.getText(),
				// dividirConta.getText(),
				// valorPorPessoa.getText(),
				// cover.getText(),
				// descontoConta.getText()
				));
				linhasTxt.println();
				linhasTxt.println("===========================================");
				// linhasTxt.println("SubTotal " + valorBruto.getText());
				linhasTxt.println("SubTotal                            " + "10,00");
				linhasTxt.println("                   ------------------------");
				// linhasTxt.println("Total " + valorBruto.getText());
				linhasTxt.println("Total                                " + "10,00");
				linhasTxt.println("===========================================");
				linhasTxt.println("       MENSAGEM DA EMPRESA VAI AQUI        ");
				linhasTxt.println("   INFORMACOES PARA FECHAMENTO DE CONTA    ");

				int i = 0;
				while (i < 10) {
					i++;
					linhasTxt.println();
				}
				arquivoTxt.close();
				// emiteComanda();

			} else {
				// se naum existir
				arquivo.createNewFile();
				// criaTxt();
			}
		} catch (IOException error) {
			System.out.println("nao encontrei arquivo");
		}

		// imprime arquivo
		try {
			InputStream is = new FileInputStream("teste.txt");
			Scanner sc = new Scanner(is);
			System.out.println("Imprimindo...");
			FileOutputStream fs = new FileOutputStream("XPSPort:");
			PrintStream ps = new PrintStream(fs);

			while (sc.hasNextLine()) {
				String linhas = sc.nextLine();
				ps.println(linhas);
			}
			fs.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro encontrado ao imprimir comanda.");
		}

	}
}