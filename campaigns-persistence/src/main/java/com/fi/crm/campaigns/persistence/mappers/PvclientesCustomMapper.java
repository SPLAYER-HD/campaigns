package com.fi.crm.campaigns.persistence.mappers;

import org.apache.ibatis.annotations.Param;

public interface PvclientesCustomMapper {
	public String getClientCode(@Param("companyId") String companyId,
			@Param("storeId") String storeId,
			@Param("clientCodeIndicator") String clientCodeIndicator);
}