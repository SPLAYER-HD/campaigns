package com.fi.crm.campaigns.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.persistence.model.CustomCpSeguimiento;

public interface CustomCpSeguimientoMapper {
    
	/**
     * This method get tracing with client by campaign
     *
     */
    List<CustomCpSeguimiento> getTracingByCampaignId(@Param("campaignId") Integer campaignId);
    
  

}