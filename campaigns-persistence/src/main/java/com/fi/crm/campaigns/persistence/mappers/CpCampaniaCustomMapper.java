package com.fi.crm.campaigns.persistence.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.persistence.model.CpCampania;
import com.fi.crm.campaigns.persistence.model.CustomCpCampania;

public interface CpCampaniaCustomMapper {
    /**
     * This method get campaigns y user
     *
     */
    List<CpCampania> selectByStateAndTypeAndUser(@Param("campaignState") String campaignState, 
    		@Param("campaignContactType") String campaignContactType, @Param("user") String user);
    
    List<CustomCpCampania> selectByStateAndDate(@Param("tracingContactedState") String tracingContactedState, 
    		@Param("tracingSeenState") String tracingSeenState, @Param("tracingSentState") String tracingSentState, 
    		@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
    		@Param("campaignState") CampaignStatusEnum campaignState);

}