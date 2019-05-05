package com.sert.tables;

import com.sert.entidades.DuplicataCliente;

public class ContasAReceberComparator {
	public int compare(DuplicataCliente duplicataA, DuplicataCliente duplicataB) {
        int issueAValue = duplicataA.getId();
        int issueBValue = duplicataB.getId();

        return issueAValue - issueBValue;
    }
}