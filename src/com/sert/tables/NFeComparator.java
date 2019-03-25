package com.sert.tables;

import java.util.Comparator;

import com.sert.entidades.NFeEntrada;

public class NFeComparator implements Comparator<NFeEntrada>{

	@Override
	public int compare(NFeEntrada o1, NFeEntrada o2) {
		int issueAValue = o1.getId();
        int issueBValue = o2.getId();
		return issueAValue - issueBValue;
	}

}
