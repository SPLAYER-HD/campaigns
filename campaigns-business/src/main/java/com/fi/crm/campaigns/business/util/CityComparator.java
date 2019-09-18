package com.fi.crm.campaigns.business.util;

import java.util.Comparator;

import com.fi.crm.campaigns.common.dto.CityDTO;

public class CityComparator implements Comparator<CityDTO> {

	@Override
	public int compare(CityDTO arg0, CityDTO arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
