package com.fi.crm.campaigns.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.persistence.model.CustomCpSeguimientoMensaje;

public interface CustomCpSeguimientoMensajeMapper {
    
	/**
     * This method get tracing with client by campaign
     *
     */
    List<CustomCpSeguimientoMensaje> getTracingMessageByCampaignId(@Param("campaignId") Integer campaignId);
    
  

}