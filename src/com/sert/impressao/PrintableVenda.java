package com.sert.impressao;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

import com.sert.controler.JDateField;
import com.sert.controler.UsuLogado;
import com.sert.entidades.Venda;

public class PrintableVenda {

	public PrintableVenda(Venda venda) {

		Frame f = new Frame();
		f.pack();
		Graphics g[] = new Graphics[50];
		// pega o Toolkit do Frame
		Toolkit tk = f.getToolkit();
		// Pega os serviços de impressão existentes no computador,
		// para que seja escolhida uma impressora.
		// Também pode ser uma impressora de rede
		PrintJob pj = tk.getPrintJob(f, "Venda", null);
		
		if (pj != null) {
			g[0] = pj.getGraphics();
			g[0].setFont(new Font("Arial", Font.PLAIN, 10));
			g[0].drawString("M&K Papelaria", 50, 8);
			g[0].setFont(new Font("Arial", Font.PLAIN, 7));
			g[0].drawString("CNPJ: 31.199.696/0001-50", 47, 17);
			g[0].drawString("Rua José de Siqueira Campos, Nº 135", 30, 25);
			g[0].setFont(new Font("Arial", Font.BOLD, 8));
			g[0].drawString("Cupom não fiscal", 52, 34);
			g[0].drawLine(0, 37, 180, 37);
			g[0].setFont(new Font("Courier New", Font.BOLD, 8));
			g[0].drawString("Descrição", 10, 43);
			g[0].drawString("Quant.", 75, 43);
			g[0].drawString("Valor", 130, 43);
			g[0].drawLine(0, 46, 180, 46);
			g[0].setFont(new Font("Arial", Font.PLAIN, 7));
			g[0].drawString(venda.getMercadorias().get(0).getMercadoria(), 10, 54);
			g[0].drawString(String.valueOf(venda.getMercadorias().get(0).getEstoque()), 75, 60);
			g[0].drawString(String.valueOf(venda.getMercadorias().get(0).getPrecoVenda()*venda.getMercadorias().get(0).getEstoque()), 130, 60);
			int loop = 60;
			float total = 0;
			for (int i = 1; i < venda.getMercadorias().size(); i++) {
				g[0].drawString(venda.getMercadorias().get(i).getMercadoria(), 10, loop+15);
				g[0].drawString(String.valueOf(venda.getMercadorias().get(i).getEstoque()), 75, loop+26);
				g[0].drawString(String.valueOf(venda.getMercadorias().get(i).getPrecoVenda()*venda.getMercadorias().get(i).getEstoque()), 130, loop+26);
				loop += 20;
				total += venda.getMercadorias().get(i).getPrecoVenda()*venda.getMercadorias().get(i).getEstoque();
			}
			g[0].drawString("Total "+total, 100, loop+20); 
			g[0].drawLine(0, loop+30, 180, loop+30);
			g[0].drawString("Vendedor "+UsuLogado.getNome(), 10, loop+40);
			g[0].drawString("Data: "+JDateField.getDateHoraStatic(), 10, loop+50);
			
			
			// libera os recursos gráficos
			g[0].dispose();
			// encerra a impressão
			pj.end();
		}
		// desfaz o frame temporário
		f.dispose();
	}
}