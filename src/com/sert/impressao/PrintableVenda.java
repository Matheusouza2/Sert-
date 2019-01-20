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
			g[0].setFont(new Font("Arial", Font.BOLD, 14));
			g[0].drawString("M&K Papelaria", 60, 10);
			g[0].setFont(new Font("Arial", Font.PLAIN, 10));
			g[0].drawString("CNPJ: 31.199.696/0001-50", 47, 20);
			g[0].drawString("Rua José de Siqueira Campos, Nº 135", 30, 28);
			g[0].setFont(new Font("Arial", Font.BOLD, 9));
			g[0].drawString("Cupom não fiscal", 60, 38);
			g[0].drawLine(0, 40, 210, 40);
			g[0].setFont(new Font("Courier New", Font.BOLD, 8));
			g[0].drawString("Descrição", 10, 49);
			g[0].drawString("Quant.", 95, 49);
			g[0].drawString("Valor", 150, 49);
			g[0].drawLine(0, 52, 210, 52);
			g[0].setFont(new Font("Arial", Font.PLAIN, 7));
			g[0].drawString(venda.getMercadorias().get(0).getMercadoria(), 10, 60);
			g[0].drawString(String.valueOf(venda.getMercadorias().get(0).getEstoque()), 95, 68);
			g[0].drawString(String.valueOf(venda.getMercadorias().get(0).getPrecoVenda()*venda.getMercadorias().get(0).getEstoque()), 150, 68);
			int loop = 68;
			for (int i = 1; i < venda.getMercadorias().size(); i++) {
				g[0].drawString(venda.getMercadorias().get(i).getMercadoria(), 10, loop+15);
				g[0].drawString(String.valueOf(venda.getMercadorias().get(i).getEstoque()), 95, loop+26);
				g[0].drawString(String.valueOf(venda.getMercadorias().get(i).getPrecoVenda()*venda.getMercadorias().get(i).getEstoque()), 150, loop+26);
				loop += 20;
			}
			g[0].setFont(new Font("Arial", Font.PLAIN, 8));
			g[0].drawString("Total: R$ "+String.format("%.2f",venda.getValTotal()).replace(".", ","), 140, loop+20); 
			g[0].drawLine(0, loop+30, 210, loop+30);
			g[0].drawString("Vendedor: "+UsuLogado.getNome(), 10, loop+40);
			g[0].drawString("Cliente: "+venda.getCliente(), 10, loop+50);
			g[0].drawString("Data: "+JDateField.getDateHoraStatic(), 10, loop+60);
			
			
			// libera os recursos gráficos
			g[0].dispose();
			// encerra a impressão
			pj.end();
		}
		// desfaz o frame temporário
		f.dispose();
	}
}