package com.sert.tables;

import java.util.Comparator;

import com.sert.entidades.Venda;

public class VendaComparator implements Comparator<Venda> {
	   
		public int compare(Venda vendaA, Venda vendaB) {
	        // rating is between 1 and 5, lower is more important
	        int issueAValue = vendaA.getId();
	        int issueBValue = vendaB.getId();

	        return issueAValue - issueBValue;
	    }
	}