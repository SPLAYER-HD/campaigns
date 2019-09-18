package com.fi.crm.campaigns.business.util;

import java.util.Comparator;

import com.fi.crm.campaigns.common.dto.ProvinceDTO;

public class ProvinceComparator implements Comparator<ProvinceDTO> {

	@Override
	public int compare(ProvinceDTO arg0, ProvinceDTO arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
