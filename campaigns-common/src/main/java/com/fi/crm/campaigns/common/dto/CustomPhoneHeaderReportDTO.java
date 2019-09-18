package com.fi.crm.campaigns.common.dto;

import java.util.Date;

import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;

public class CustomPhoneHeaderReportDTO implements BaseDTO {

	private static final long serialVersionUID = -9007057146964910697L;
	private Integer campaignId;
	private String name;
	private String description;
	private AuthorDTO author;
	private Date startDate;
	private Date endDate;
	private Date eventStartDate;
	private Date eventEndDate;
	private CampaignContactTypeEnum contactType;
	private CampaignStatusEnum status;
	private Boolean automaticCalling;
	private String message;
	private String mailSubject;
	private BusinessUnitDTO businessUnit;
	private StoreDTO store;
	private String approver;
	private String userCreator;
	private String domain;
	private MarcasDTO brand;
	private ArticleDTO article;
	private Integer asignadas;
	private Integer realizadas;
	private Float porcentajeRealizadas;
	private Integer efectivas;
	private Float porcentajeEfectivas;
	private Integer noEfectivas;
	private Float porcentajeNoEfectivas;
	
	/** Default constructor */
	public CustomPhoneHeaderReportDTO(){}

	/**
	 * @return the campaignId
	 */
	public Integer getCampaignId() {
		return campaignId;
	}

	/**
	 * @param campaignId the campaignId to set
	 */
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the author
	 */
	public AuthorDTO getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the eventStartDate
	 */
	public Date getEventStartDate() {
		return eventStartDate;
	}

	/**
	 * @param eventStartDate the eventStartDate to set
	 */
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	/**
	 * @return the eventEndDate
	 */
	public Date getEventEndDate() {
		return eventEndDate;
	}

	/**
	 * @param eventEndDate the eventEndDate to set
	 */
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	/**
	 * @return the contactType
	 */
	public CampaignContactTypeEnum getContactType() {
		return contactType;
	}

	/**
	 * @param contactType the contactType to set
	 */
	public void setContactType(CampaignContactTypeEnum contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the status
	 */
	public CampaignStatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(CampaignStatusEnum status) {
		this.status = status;
	}

	/**
	 * @return the automaticCalling
	 */
	public Boolean getAutomaticCalling() {
		return automaticCalling;
	}

	/**
	 * @param automaticCalling the automaticCalling to set
	 */
	public void setAutomaticCalling(Boolean automaticCalling) {
		this.automaticCalling = automaticCalling;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the mailSubject
	 */
	public String getMailSubject() {
		return mailSubject;
	}

	/**
	 * @param mailSubject the mailSubject to set
	 */
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	/**
	 * @return the businessUnit
	 */
	public BusinessUnitDTO getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * @param businessUnit the businessUnit to set
	 */
	public void setBusinessUnit(BusinessUnitDTO businessUnit) {
		this.businessUnit = businessUnit;
	}

	/**
	 * @return the store
	 */
	public StoreDTO getStore() {
		return store;
	}

	/**
	 * @param store the store to set
	 */
	public void setStore(StoreDTO store) {
		this.store = store;
	}

	/**
	 * @return the approver
	 */
	public String getApprover() {
		return approver;
	}

	/**
	 * @param approver the approver to set
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}

	/**
	 * @return the userCreator
	 */
	public String getUserCreator() {
		return userCreator;
	}

	/**
	 * @param userCreator the userCreator to set
	 */
	public void setUserCreator(String userCreator) {
		this.userCreator = userCreator;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomPhoneHeaderReportDTO [campaignId=" + campaignId + ", name=" + name
				+ ", description=" + description + ", author=" + author
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", eventStartDate=" + eventStartDate + ", eventEndDate="
				+ eventEndDate + ", contactType=" + contactType + ", status="
				+ status + ", automaticCalling=" + automaticCalling
				+ ", message=" + message + ", mailSubject=" + mailSubject
				+ ", businessUnit=" + businessUnit + ", store=" + store
				+ ", approver=" + approver + ", userCreator=" + userCreator
				+ ", domain=" + domain + ", marca="+ brand.getCodigo()
				+",article="+article.getNoArti() 
				+",asignadas="+asignadas 
				+",realizadas="+realizadas
				+",porcentajeRealizadas="+porcentajeRealizadas
				+",efectivas="+efectivas
				+",porcentajeEfectivas="+porcentajeEfectivas
				+",noEfectivas="+noEfectivas
				+",porcentajeNoEfectivas="+porcentajeNoEfectivas+"]";
	}

	public MarcasDTO getBrand() {
		return brand;
	}

	public void setBrand(MarcasDTO brand) {
		this.brand = brand;
	}

	public ArticleDTO getArticle() {
		return article;
	}

	public void setArticle(ArticleDTO article) {
		this.article = article;
	}

	public Integer getAsignadas() {
		return asignadas;
	}

	public void setAsignadas(Integer asignadas) {
		this.asignadas = asignadas;
	}

	public Integer getRealizadas() {
		return realizadas;
	}

	public void setRealizadas(Integer realizadas) {
		this.realizadas = realizadas;
	}

	public Float getPorcentajeRealizadas() {
		return porcentajeRealizadas;
	}

	public void setPorcentajeRealizadas(Float porcentajeRealizadas) {
		this.porcentajeRealizadas = porcentajeRealizadas;
	}

	public Integer getEfectivas() {
		return efectivas;
	}

	public void setEfectivas(Integer efectivas) {
		this.efectivas = efectivas;
	}

	public Float getPorcentajeEfectivas() {
		return porcentajeEfectivas;
	}

	public void setPorcentajeEfectivas(Float porcentajeEfectivas) {
		this.porcentajeEfectivas = porcentajeEfectivas;
	}

	public Integer getNoEfectivas() {
		return noEfectivas;
	}

	public void setNoEfectivas(Integer noEfectivas) {
		this.noEfectivas = noEfectivas;
	}

	public Float getPorcentajeNoEfectivas() {
		return porcentajeNoEfectivas;
	}

	public void setPorcentajeNoEfectivas(Float porcentajeNoEfectivas) {
		this.porcentajeNoEfectivas = porcentajeNoEfectivas;
	}
}
