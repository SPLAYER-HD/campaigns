package com.fi.crm.campaigns.business.util;

import java.util.Comparator;

import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;

public class DocumentTypeComparator implements Comparator<DocumentTypeDTO> {

	@Override
	public int compare(DocumentTypeDTO arg0, DocumentTypeDTO arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
