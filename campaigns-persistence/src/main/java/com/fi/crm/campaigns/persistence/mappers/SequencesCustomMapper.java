package com.fi.crm.campaigns.persistence.mappers;


public interface SequencesCustomMapper {
    
	/**
     * This method return sequence to camppaigns.
     *
     */
    public Integer getCampaignSequence();
    
    /**
     * This method return sequence to Authors.
     *
     */
    public Integer getAuthorSequence();

    /**
     * This method return sequence to Clients.
     *
     */
    public Integer getClientSequence();
    
    /**
     * This method return sequence to Audit detail.
     *
     */
    public Integer getAuditDetailSequence();
    
    /**
     * This method return sequence to Tracing Status.
     *
     */
    public Integer getTracingStatusSequence();
    
    /**
     * This method return sequence to Audit Info.
     *
     */
    public Integer getAuditInfoSequence();
    
    /**
     * This method return sequence to profile.
     *
     */
    public Integer getProfileSequence();
    
    /**
     * This method return sequence to Tracing.
     *
     */
    public Integer getTracingSequence();
    
    /**
     * This method return sequence to Tracing message.
     *
     */
    public Integer getTracingMessageSequence();
    
    /**
     * This method return sequence to User Profile.
     *
     */
    public Integer getUserProfileSequence();
           
    
}