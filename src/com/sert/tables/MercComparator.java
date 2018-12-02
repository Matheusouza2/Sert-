package com.sert.tables;

import java.util.Comparator;

import com.sert.entidades.Mercadoria;

public class MercComparator implements Comparator<Mercadoria> {
   
	public int compare(Mercadoria merc, Mercadoria mercB) {
        // rating is between 1 and 5, lower is more important
        int issueAValue = merc.getId();
        int issueBValue = mercB.getId();

        return issueAValue - issueBValue;
    }
}
