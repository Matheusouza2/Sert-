package com.sert.main;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

public class ExemploPrintable {

	public ExemploPrintable() {

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
			g[0].drawString("M&K Papelaria", 50, 30);
			g[0].setFont(new Font("Arial", Font.PLAIN, 8));
			g[0].drawString("Cupom não fiscal", 47, 45);
			g[0].drawLine(0, 50, 180, 50);
			g[0].setFont(new Font("Courier New", Font.BOLD, 8));
			g[0].drawString("Descrição", 10, 58);
			g[0].drawString("Quant.", 75, 58);
			g[0].drawString("Valor", 130, 58);
			g[0].drawLine(0, 60, 180, 60);
			int loop = 70;
			for(int i = 0; i < 200; i++) {
				g[0].setFont(new Font("Arial", Font.PLAIN, 7));
				g[0].drawString(""+i, 10, loop);
				g[0].drawString("CLIPS ACC 3/0", 15, loop);
				g[0].drawString("1", 75, loop);
				g[0].drawString("2,50", 130, loop);
				loop += 10;
			}
			
			
			
			// libera os recursos gráficos
			g[0].dispose();
			// encerra a impressão
			pj.end();
		}
		// desfaz o frame temporário
		f.dispose();
	}

	public static void main(String[] args) {

		new ExemploPrintable();

	}
}