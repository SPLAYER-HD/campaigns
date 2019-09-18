package com.fi.crm.campaigns.persistence.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.persistence.model.CpCliente;

public interface CpClienteCustomMapper {
    /**
     * This method get clients by campaigns 
     *
     */
    List<CpCliente> getClientsByCampaign(@Param("campaignId") Integer campaignId, 
    		@Param("statusContacted") String statusContacted, @Param("hoursAfterLastStatus") Integer hoursAfterLastStatus, 
    		@Param("statusNoContacted") String statusNoContacted);
    List<CpCliente> getPendingClientsByContacted(@Param("campaignId") Integer campaignId, @Param("rownum") Integer rownum);
    
}