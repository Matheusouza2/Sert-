package com.sert.main;

import com.sert.telas.Entrada;

/**
 * Desenvolvido e mantido por SertSoft -- Uma empresa do gupo M&K
 * 
 * @author Matheus Souza
 * @version 1.1.0 OC HONDA: 2432057. Funcionamento da numeração de versionamento
 *          do sistema: 1- O primeiro numero significa que uma mudança de grande
 *          significancia foi adicionada ao sistema como um novo modulo, novas
 *          funcionalidades de peso. 2- O segundo numero significa mudanças nas
 *          telas onde o usuário sentirá uma real diferença como mudança total
 *          ou pelo menos mudança de 50% da tela. 3- O terceiro numero indicará
 *          mudanças de pequeno porte, para controle apenas interno,
 *          significando mudanças de rotinas melhoras de desenpenho, otimização
 *          de codigo, pequenas mudanças na tela R- O R significa o release e
 *          não será divulgado ao Usuário, apenas para uso interno e controle de
 *          qualquer outra modificação que não se enquadre em alguma das 3
 *          opções anteriores.
 * 
 */
public class Main {
	public static void main(String[] args) {
		try {
			new Entrada().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}