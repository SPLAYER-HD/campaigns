/**
 * 
 */
package com.fi.crm.campaigns.web.views.secure;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.jms.JmsMessageListener;
import com.fi.crm.campaigns.business.services.campaign.CampaignsServiceInterface;
import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.business.services.report.ReportServiceInterface;
import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;
import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageClicksReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageEffectivenessReportDTO;
import com.fi.crm.campaigns.common.dto.CustomMessageHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportDTO;
import com.fi.crm.campaigns.common.dto.CustomPhoneHeaderReportEffectiveCallsDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.dto.TracingDTO;
import com.fi.crm.campaigns.common.dto.TracingMessageDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.PermissionEnum;
import com.fi.crm.campaigns.common.enums.TracingMessageStatusEnum;
import com.fi.crm.campaigns.common.mail.MailUtil;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.infrastructure.mail.SendEmail;
import com.fi.crm.campaigns.infrastructure.sms.SendSMS;
import com.fi.crm.campaigns.web.components.AdminEntityComponent;
import com.fi.crm.campaigns.web.forms.CampaignForm;
import com.fi.crm.campaigns.web.forms.ViewCampaignForm;
import com.fi.crm.campaigns.web.forms.ViewCampaignMessagesReportForm;
import com.fi.crm.campaigns.web.forms.ViewCampaignPhoneReportForm;
import com.fi.crm.campaigns.web.util.CatalogUtil;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 29/2015
 */
public class CampaignAdminView extends AdminBaseView{

	/** Serial UID */
	private static final long serialVersionUID = 6452355499730644775L;

	/* Variables */
	private CampaignsServiceInterface campaignService;
	private ConstantServiceInterface constantService;
	private TracingServiceInterface tracingService;
	private ReportServiceInterface reportService;
	private ParametrizationServiceInterface parametrizacionService;
	private AdminEntityComponent adminEntityComponent;
	private CampaignDTO campaignDTO;
	private ComboBox status;
	private DateField startDate;
	private DateField endDate;
	private Button search;
	private FieldGroup fieldGroup;
	private String defaultMailDomain;
	private String mailServerIP;
	private String mailServerPort;
	private String templatePath;

	/* Constants */
	private static final String CAMPAIGN_ID = "ID";
	private static final String CAMPAIGN_NAME = "NAME";
	private static final String CAMPAIGN_TYPE = "TYPE";
	private static final String CAMPAIGN_START_DATE = "START_DATE";
	private static final String CAMPAIGN_END_DATE = "END_DATE";
	private static final String CAMPAIGN_ADVANCE = "ADVANCE";
	private static final String CAMPAIGN_STATUS = "STATUS";

	private static final String REPORT_CAMPAIGN_NAME = "Nombre";
	private static final String REPORT_CAMPAIGN_PHONE = "Teléfono";
	private static final String REPORT_CAMPAIGN_EMAIL = "Email";
	private static final String REPORT_CAMPAIGN_STATUS = "Estado";
	private static final String REPORT_CAMPAIGN_SUB_STATUS = "SubEstado";
	private static final String REPORT_CAMPAIGN_DATE = "Fecha";
	private static final String REPORT_CAMPAIGN_OBS = "Observaciones";

	private static final Logger LOGGER = LoggerFactory.getLogger(CampaignAdminView.class);

	/** Default constructor */
	public CampaignAdminView(){

		super();

		try{

			/* Load services insntance */
			this.campaignService = (CampaignsServiceInterface)springContextHelper.getBean("campaignsService");
			this.constantService = (ConstantServiceInterface)springContextHelper.getBean("constantService");
			this.tracingService = (TracingServiceInterface)springContextHelper.getBean("tracingService");
			this.reportService = (ReportServiceInterface)springContextHelper.getBean("reportService");
			this.parametrizacionService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");
			HashMap<String, ConstantDTO> constants = this.constantService.getAllConstants();
			this.defaultMailDomain = constants.get(ConstantsIdentifierEnum.DEFAULT_EMAIL_SENDER.getValue()).getValue();
			this.mailServerIP = constants.get(ConstantsIdentifierEnum.MAIL_SERVER_IP.getValue()).getValue();
			this.mailServerPort = constants.get(ConstantsIdentifierEnum.MAIL_SERVER_PORT.getValue()).getValue();
			this.templatePath = constants.get(ConstantsIdentifierEnum.MAIL_TEMPLATE_PATH.getValue()).getValue();

			//addStyleName("verticalScroll");
			this.status = new ComboBox();
			CatalogUtil.addCampaignStatus(this.status);
			this.startDate = new DateField();
			this.startDate.setDateFormat("dd/MM/yyyy");
			this.endDate = new DateField();
			this.endDate.setDateFormat("dd/MM/yyyy");
			this.search = new Button(Messages.getString("General.search"));

			CommonUtil.setAttributes(this.status, Messages.getString("CampaignForm.status"), false, false);
			CommonUtil.setAttributes(this.startDate, Messages.getString("CampaignForm.startDate"), false, true);
			CommonUtil.setAttributes(this.endDate, Messages.getString("CampaignForm.endDate"), false, true);

			CommonUtil.setDateRangeValidator(this.startDate, this.endDate);

			final HorizontalLayout horizontalLayout = new HorizontalLayout();
			horizontalLayout.addComponent(this.status);
			horizontalLayout.addComponent(this.startDate);
			horizontalLayout.addComponent(this.endDate);
			horizontalLayout.addComponent(this.search);
			horizontalLayout.setComponentAlignment(this.search, Alignment.BOTTOM_CENTER);
			horizontalLayout.setSpacing(true);
			horizontalLayout.setMargin(true);
			addComponent(horizontalLayout);

			this.search.addClickListener(new Button.ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = 8413334615672220891L;

				@Override
				public void buttonClick(ClickEvent event) {

					if (CommonUtil.validate(horizontalLayout))
						fillTable();
					else
						CommonUtil.showNotification(Messages.getString("General.err.search"), Type.ERROR_MESSAGE);
				}
			});

			this.init();

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/** Init method */
	private void init(){

		try{

			this.fieldGroup = new FieldGroup();
			section.setContent(FontAwesome.BRIEFCASE, Messages.getString("bread.campaigns"));
			final CampaignForm campaignForm = new CampaignForm(this.defaultMailDomain){

				/** Serial UID */
				private static final long serialVersionUID = -2245041260350430630L;

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.forms.CampaignForm#SendTestMail()
				 */
				public void SendTestMail(){

					if (CommonUtil.validate(this)){

						try{

							this.testMail.validate();								
							fieldGroup.commit();
							LOGGER.debug("campaignDTO.getBusinessUnit()="+campaignDTO.getBusinessUnit());
							LOGGER.debug("campaignDTO.getBusinessUnit()="+((BusinessUnitDTO)this.businessUnit.getValue()));
							TracingMessageDTO tracingMessageDTO = new TracingMessageDTO();
							tracingMessageDTO.setCampaign(campaignDTO);
							String mailBody = MailUtil.loadMailBody(tracingMessageDTO, campaignDTO.getMessage());
							mailBody = MailUtil.loadTemplate(mailBody, templatePath, JmsMessageListener.MAIL_TEMPLATE_NAME, "");
							String sender = this.getNameDomain().getValue().replaceAll("<", "").replaceAll(">", "").trim();
							String domain = sender+ " <"+campaignDTO.getDomain()+">";
							SendEmail sendEmail = new SendEmail();
							sendEmail.send(domain, this.testMail.getValue(), campaignDTO.getMailSubject(), mailBody, mailServerIP, mailServerPort, null);
							CommonUtil.showNotification(Messages.getString("CampaignForm.test.mail"), Type.HUMANIZED_MESSAGE);

						}catch (InvalidValueException e){
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.validate.form"), Type.ERROR_MESSAGE);
						}catch (Exception e) {
							LOGGER.error(e.getMessage(), e);
						}

					}else
						CommonUtil.showNotification(Messages.getString("CampaignForm.err.validate.form"), Type.ERROR_MESSAGE);
				}

				@Override
				public void sendTestSMS() {
					if (CommonUtil.validate(this)){
						HashMap<String, ConstantDTO> allConstants;
						try {
							allConstants = constantService.getAllConstants();
							String user = allConstants.get(ConstantsIdentifierEnum.SMS_ITCLOUD_USER.getValue()).getValue();
							String password = allConstants.get(ConstantsIdentifierEnum.SMS_ITCLOUD_PASSWORD.getValue()).getValue();
							String phoneInd = allConstants.get(ConstantsIdentifierEnum.SMS_INDICATIVE.getValue()).getValue();
							String smsUrl = allConstants.get(ConstantsIdentifierEnum.SMS_URL.getValue()).getValue();
							String response = SendSMS.sendSMSITCloud(user, password, phoneInd, smsUrl, this.testSMS.getValue(),this.message.getValue());
							if(response.equals(TracingMessageStatusEnum.FAIL_AUTENTICATION.getValue()) || 
									response.equals(TracingMessageStatusEnum.OUTSIDE_AUTHORIZED_HOURS.getValue()) || 
									response.equals(TracingMessageStatusEnum.BALANCE_ERROR.getValue()) || 
									response.equals(TracingMessageStatusEnum.SYSTEM_IN_MAINTENANCE.getValue())){
								CommonUtil.showNotification(Messages.getString("CampaignForm.test.sms.err"), Type.ERROR_MESSAGE);
							}
							if(response.equals(TracingMessageStatusEnum.FAIL_AUTENTICATION.getValue()) ||
								response.equals(TracingMessageStatusEnum.OUTSIDE_AUTHORIZED_HOURS.getValue()) ||
								response.equals(TracingMessageStatusEnum.BALANCE_ERROR.getValue()) ||
								response.equals(TracingMessageStatusEnum.MOBILE_FORMAT_ERROR.getValue()) ||
								response.equals(TracingMessageStatusEnum.MESSAGE_ERROR.getValue()) ||
								response.equals(TracingMessageStatusEnum.SYSTEM_IN_MAINTENANCE.getValue()) ||
								response.equals(TracingMessageStatusEnum.QUANTITY_MAX_MOBILE.getValue()) ||
								response.equals(TracingMessageStatusEnum.OPERATOR_NOT_EXISTS.getValue())){
								CommonUtil.showNotification(Messages.getString("CampaignForm.test.sms"), Type.HUMANIZED_MESSAGE);
							}

						} catch (Exception e) {
							CommonUtil.showNotification(Messages.getString("CampaignForm.test.sms.err"), Type.ERROR_MESSAGE);						
						}
						
					}else {
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.validate.form"), Type.ERROR_MESSAGE);
					}
					
				}
			};

			this.adminEntityComponent = new AdminEntityComponent() {

				/** Serial UID */
				private static final long serialVersionUID = 5518583113350126259L;

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.BaseAdminEntityComponent#formActionCancel(com.vaadin.ui.Window)
				 */
				@Override
				public void formActionCancel(Window w) {
					w.close();
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#editItemActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void editItemActionComplete(Window w) {

					try {

						fieldGroup.commit();

						if (campaignDTO.getStartDate().before(new Date()))
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.startDate"), Type.ERROR_MESSAGE);
						else if (campaignDTO.getEventStartDate().before(new Date()))
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.eventStartDate"), Type.ERROR_MESSAGE);
						else{
							if(campaignDTO.getContactType().equals(CampaignContactTypeEnum.EMAIL)){
								String sender = campaignForm.getNameDomain().getValue().replaceAll("<", "").replaceAll(">", "").trim();
								String domain = sender+ " <"+campaignDTO.getDomain()+">";
								campaignDTO.setDomain(domain);
							}
							campaignService.updateCampaign(campaignDTO, SessionUtil.getUserInfo());
							CommonUtil.showNotification(Messages.getString("General.edit"), Type.HUMANIZED_MESSAGE);
							fillTable();
							w.close();
						}

					} catch (Exception e) {
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#editItemAction(java.lang.Object)
				 */
				@Override
				public void editItemAction(Object data) {

					try{

						campaignDTO = (CampaignDTO)data;
						BeanItem<CampaignDTO> beanItem = new BeanItem<CampaignDTO>(campaignDTO);
						fieldGroup.setItemDataSource(beanItem);
						BusinessUnitDTO businessUnitDTO = campaignDTO.getBusinessUnit();
						if (businessUnitDTO != null)
							campaignForm.loadBusinessUnitById(businessUnitDTO.getCode(), businessUnitDTO.getNoCia());
						
						StoreDTO storeDTO = campaignDTO.getStore();
						if (storeDTO != null && businessUnitDTO != null){
							campaignForm.loadStoreById(storeDTO.getNoCia(), businessUnitDTO.getCode(), storeDTO.getCenter());
						}
						
						AuthorDTO authorDTO = campaignDTO.getAuthor();
						if (authorDTO != null)
							campaignForm.loadAuthorsById(authorDTO.getAuthorId());
						
						MarcasDTO marcasDTO = campaignDTO.getBrand();
						if (marcasDTO != null){
							campaignForm.loadBrandById(marcasDTO.getNoCia(),marcasDTO.getCodigo());
						}
						
						ArticleDTO articleDTO = campaignDTO.getArticle();
						if (articleDTO != null){
							campaignForm.loadArticleById(articleDTO.getNoCia(), articleDTO.getMarca(), articleDTO.getNoArti());
						}
						if(campaignDTO.getContactType().equals(CampaignContactTypeEnum.EMAIL)){
							String [] split = campaignDTO.getDomain().split("<");
							if(split.length>1){
								LOGGER.debug("split###"+split.toString());
								String nameDomain = split[0].trim();
								String domain = split[1].replaceAll(">", "").trim();
								campaignForm.getNameDomain().setValue(nameDomain);
								campaignDTO.setDomain(domain);
							}else{
								campaignForm.getNameDomain().setValue("");
								campaignDTO.setDomain(split[0]);
								
							}
						}
						fieldGroup.bindMemberFields(campaignForm);
						adminEntityComponent.setForm(campaignForm);
						adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.edit"), CMD_EDIT, true, false, true);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#deleteItemActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void deleteItemActionComplete(Window w) {

					try{

						fieldGroup.commit();
						campaignService.deleteCampaign(campaignDTO, SessionUtil.getUserInfo());
						CommonUtil.showNotification(Messages.getString("General.delete.success"), Type.HUMANIZED_MESSAGE);
						fillTable();
						w.close();

					}catch (BusinessException be){

						if (be.getCode() != null && be.getCode().equals(ErrorCodesEnum.DELETE_NOT_POSSIBLE_BY_REFERENCE))
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.delete.reference"), Type.ERROR_MESSAGE);
						else
							CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);

						LOGGER.error(be.getMessage(), be);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#deleteItemAction(java.lang.Object)
				 */
				@Override
				public void deleteItemAction(Object data) {

					try{

						campaignDTO = (CampaignDTO)data;

						if (campaignDTO.getStatus() != null 
								&& !campaignDTO.getStatus().equals(CampaignStatusEnum.CREATED)){

							CommonUtil.showNotification(Messages.getString("CampaignForm.err.delete.status"), Type.ERROR_MESSAGE);
							return;
						}

						BeanItem<CampaignDTO> beanItem = new BeanItem<CampaignDTO>(campaignDTO);
						fieldGroup.setItemDataSource(beanItem);
						fieldGroup.bindMemberFields(campaignForm);
						adminEntityComponent.setForm(campaignForm);
						adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.delete"), CMD_DELETE);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#addButtonActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void addButtonActionComplete(Window w) {

					try{

						fieldGroup.commit();

						if (campaignDTO.getStartDate().before(new Date()))
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.startDate"), Type.ERROR_MESSAGE);
						else if (campaignDTO.getEventStartDate().before(new Date()))
							CommonUtil.showNotification(Messages.getString("CampaignForm.err.eventStartDate"), Type.ERROR_MESSAGE);
						else{
							if(campaignDTO.getContactType().equals(CampaignContactTypeEnum.EMAIL)){
								String sender = campaignForm.getNameDomain().getValue().replaceAll("<", "").replaceAll(">", "").trim();
								String domain = sender+ " <"+campaignDTO.getDomain()+">";
								campaignDTO.setDomain(domain);
							}
							campaignDTO.setStatus(CampaignStatusEnum.CREATED);
							campaignService.createCampaign(campaignDTO, SessionUtil.getUserInfo());
							CommonUtil.showNotification(Messages.getString("General.save.success"), Type.HUMANIZED_MESSAGE);
							fillTable();
							w.close();
						}

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#addButtonAction()
				 */
				@Override
				public void addButtonAction() {

					try{

						campaignDTO = new CampaignDTO();
						BeanItem<CampaignDTO> beanItem = new BeanItem<CampaignDTO>(campaignDTO);
						fieldGroup.setItemDataSource(beanItem);
						fieldGroup.bindMemberFields(campaignForm);
						adminEntityComponent.setForm(campaignForm);
						adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.add"), CMD_ADD, true, false, true);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}
			};

			if (subject.isPermitted(PermissionEnum.CAMPAIGN_ADMIN_INSERT.toString()))
				adminEntityComponent.setNewEnabled(true);
			else
				adminEntityComponent.setNewEnabled(false);

			if (subject.isPermitted(PermissionEnum.CAMPAIGN_ADMIN_UPDATE.toString()))
				adminEntityComponent.setEditEnabled(true);
			else
				adminEntityComponent.setEditEnabled(false);

			if (subject.isPermitted(PermissionEnum.CAMPAIGN_ADMIN_DELETE.toString()))
				adminEntityComponent.setDeleteEnabled(true);
			else
				adminEntityComponent.setDeleteEnabled(false);

			this.defineTable();
			addComponent(adminEntityComponent);
			adminEntityComponent.setWidthAndHeightTable("1300", "400");

		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#fillTable()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void fillTable() {

		try{

			CampaignDTO campaignDTO = new CampaignDTO();
			if (this.status.getValue() != null && !(CampaignStatusEnum.ALL.equals(this.status.getValue())))
				campaignDTO.setStatus((CampaignStatusEnum)this.status.getValue());
			
			campaignDTO.setStartDate(this.startDate.getValue());
			campaignDTO.setEndDate(this.endDate.getValue());

			List<CampaignDTO> campaigns = campaignService.getCampaignsByStateAndDate(campaignDTO, SessionUtil.getUserInfo());
			adminEntityComponent.getEntityTable().removeAllItems();

			if (campaigns != null && !campaigns.isEmpty() && adminEntityComponent != null){


				for (CampaignDTO campaign: campaigns){

					adminEntityComponent.getEntityTable().addItem(campaign);
					Item item = adminEntityComponent.getEntityTable().getItem(campaign);
					adminEntityComponent.buildRowActionLayout(campaign);

					if (subject.isPermitted(PermissionEnum.CAMPAIGN_ADMIN_APROVE.toString())
							&& campaign.getStartDate().after(new Date()) && campaign.getStatus().equals(CampaignStatusEnum.CREATED)){
						adminEntityComponent.getRowActionLayout().addComponent(this.buildAproveButton(campaign));
					}

					adminEntityComponent.getRowActionLayout().addComponent(this.buildViewButton(campaign));
					if (subject.isPermitted(PermissionEnum.CAMPAIGN_REPORT_VIEW.toString()) && 
							(campaign.getContactType().equals(CampaignContactTypeEnum.PHONE) ||campaign.getContactType().equals(CampaignContactTypeEnum.EMAIL) 
									|| campaign.getContactType().equals(CampaignContactTypeEnum.SMS))){
						adminEntityComponent.getRowActionLayout().addComponent(this.buildReportButton(campaign));
					}
					item.getItemProperty(CAMPAIGN_ID).setValue(campaign.getCampaignId().toString());
					item.getItemProperty(CAMPAIGN_NAME).setValue(campaign.getName());
					item.getItemProperty(CAMPAIGN_TYPE).setValue(Messages.getString(campaign.getContactType().getViewKey()));
					item.getItemProperty(CAMPAIGN_START_DATE).setValue(CommonUtil.formatDate(campaign.getStartDate(), "dd/MM/yyyy"));
					item.getItemProperty(CAMPAIGN_END_DATE).setValue(CommonUtil.formatDate(campaign.getEndDate(), "dd/MM/yyyy"));
					item.getItemProperty(CAMPAIGN_ADVANCE).setValue((campaign.getEfectiveCount() == null ? 0 : campaign.getEfectiveCount()) 
							+ "/" + (campaign.getTotalCount() == null ? 0 : campaign.getTotalCount()));
					item.getItemProperty(CAMPAIGN_STATUS).setValue(Messages.getString(campaign.getStatus().getViewKey()));
					item.getItemProperty(adminEntityComponent.OPERATION).setValue(((AdminEntityComponent)adminEntityComponent).getRowActionLayout());

				}
			}

			/* Show exactly the currently contained rows (items) */
			//adminEntityComponent.getEntityTable().setPageLength(adminEntityComponent.getEntityTable().size());

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#defineTable()
	 */
	@Override
	public void defineTable() {

		try{

			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_ID, String.class, "", Messages.getString("CampaignForm.table.id"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_NAME, String.class, "", Messages.getString("CampaignForm.table.name"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_TYPE, String.class, "", Messages.getString("CampaignForm.table.type"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_START_DATE, String.class, "", Messages.getString("CampaignForm.table.startDate"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_END_DATE, String.class, null, Messages.getString("CampaignForm.table.endDate"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_ADVANCE, String.class, null, Messages.getString("CampaignForm.table.advance"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(CAMPAIGN_STATUS, String.class, "", Messages.getString("CampaignForm.table.status"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(adminEntityComponent.OPERATION, HorizontalLayout.class, null, Messages.getString("General.actions"), null, null);

			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_ID,1);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_NAME,2);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_TYPE,2);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_START_DATE,1);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_END_DATE,1);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_ADVANCE,0);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(CAMPAIGN_STATUS,1);
			adminEntityComponent.getEntityTable().setColumnExpandRatio(adminEntityComponent.OPERATION,2);
			/* Show exactly the currently contained rows (items) */
			//adminEntityComponent.getEntityTable().setPageLength(adminEntityComponent.getEntityTable().size());

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * Set aprove button to campaigns list
	 * @param campaignDTO
	 * @return NativeButton
	 */
	public NativeButton buildAproveButton(final CampaignDTO campaignDTO){

		NativeButton aproveButton = new NativeButton();
		aproveButton.setDescription(Messages.getString("General.aprove"));
		aproveButton.setIcon(FontAwesome.CHECK);
		aproveButton.setStyleName("link-transparent");
		aproveButton.setImmediate(false);
		aproveButton.setWidth("-1px");
		aproveButton.setHeight("-1px");
		aproveButton.addClickListener(new Button.ClickListener() {

			/** Serial UID */
			private static final long serialVersionUID = -8587816615732569615L;

			@Override
			public void buttonClick(ClickEvent event){

				ViewCampaignForm viewCampaignForm = new ViewCampaignForm();
				viewCampaignForm.loadFields(campaignDTO);
				adminEntityComponent.setForm(viewCampaignForm);

				Button aproveButton = new Button(Messages.getString("General.aprove"));
				aproveButton.addClickListener(new Button.ClickListener(){

					/** Serial UID */
					private static final long serialVersionUID = -733051339640424212L;

					@Override
					public void buttonClick(ClickEvent event) {

						try{

							campaignDTO.setStatus(CampaignStatusEnum.APPROVED);
							campaignDTO.setApprover(SessionUtil.getUserInfo().getUserCode());
							campaignService.updateCampaign(campaignDTO, SessionUtil.getUserInfo());
							adminEntityComponent.getWindow().close();
							CommonUtil.showNotification(Messages.getString("CampaignForm.aprove"), Type.HUMANIZED_MESSAGE);
							fillTable();

						}catch (Exception e){
							LOGGER.error(e.getMessage(), e);
						}
					}
				});

				Button rejectButton = new Button(Messages.getString("General.reject"));
				rejectButton.addClickListener(new Button.ClickListener(){

					/** Serial UID */
					private static final long serialVersionUID = -733051339640424212L;

					@Override
					public void buttonClick(ClickEvent event) {

						try{

							campaignDTO.setStatus(CampaignStatusEnum.REJECTED);
							campaignDTO.setApprover(SessionUtil.getUserInfo().getUserCode());
							campaignService.updateCampaign(campaignDTO, SessionUtil.getUserInfo());
							adminEntityComponent.getWindow().close();
							fillTable();

						}catch (Exception e){
							LOGGER.error(e.getMessage(), e);
						}
					}
				});

				adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.view"), aproveButton, rejectButton, true, true, false);

			}
		});

		return aproveButton;
	}

	/**
	 * Set aprove button to campaigns list
	 * @param campaignDTO
	 * @return NativeButton
	 */
	public NativeButton buildViewButton(final CampaignDTO campaignDTO){

		NativeButton viewButton = new NativeButton();
		viewButton.setDescription(Messages.getString("General.view"));
		viewButton.setIcon(FontAwesome.SEARCH);
		viewButton.setStyleName("link-transparent");
		viewButton.setImmediate(false);
		viewButton.setWidth("-1px");
		viewButton.setHeight("-1px");
		viewButton.addClickListener(new Button.ClickListener() {

			/** Serial UID */
			private static final long serialVersionUID = -8587816615732569615L;

			@Override
			public void buttonClick(ClickEvent event){

				ViewCampaignForm viewCampaignForm = new ViewCampaignForm();
				viewCampaignForm.loadFields(campaignDTO);
				adminEntityComponent.setForm(viewCampaignForm);

				Button okButton = new Button(Messages.getString("General.ok"));
				okButton.addClickListener(new Button.ClickListener(){

					/** Serial UID */
					private static final long serialVersionUID = -733051339640424212L;

					/*
					 * (non-Javadoc)
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {
						adminEntityComponent.getWindow().close();
					}
				});

				String reportTitle = Messages.getString("General.campaign") + campaignDTO.getName();
				Table reportTable = getTableResults(campaignDTO);
				Button reportButton = new Button();

				if (reportTable != null)
					reportButton = CommonUtil.getDownloadButton(reportTable, null, getUI(), reportTitle);
				else{
					reportButton.addClickListener(new ClickListener() {

						/** Serial UID */
						private static final long serialVersionUID = -1165789436231907994L;

						@Override
						public void buttonClick(ClickEvent event) {
							CommonUtil.showNotification(Messages.getString("General.err.download.report"), Type.ERROR_MESSAGE);
						}
					});
				}

				reportButton.setCaption(Messages.getString("General.report.download"));
				adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.view"), okButton, reportButton, true, true, false);
			}
		});

		return viewButton;
	}


	public NativeButton buildReportButton(final CampaignDTO campaignDTO){

		NativeButton viewButton = new NativeButton();
		viewButton.setDescription(Messages.getString("General.report"));
		viewButton.setIcon(FontAwesome.FILE);
		viewButton.setStyleName("link-transparent");
		viewButton.setImmediate(false);
		viewButton.setWidth("-1px");
		viewButton.setHeight("-1px");
		viewButton.addClickListener(new Button.ClickListener() {

			/** Serial UID */
			private static final long serialVersionUID = -8587816615732569615L;

			@Override
			public void buttonClick(ClickEvent event){

				
				
				try {
					if (campaignDTO.getContactType().equals(CampaignContactTypeEnum.PHONE)){
						ViewCampaignPhoneReportForm viewCampaignPhoneReportForm = new ViewCampaignPhoneReportForm(reportService, parametrizacionService, getUI());
						CustomPhoneHeaderReportDTO customPhoneHeaderReportDTO = reportService.getPhoneHeaderReportByCampaignId(campaignDTO.getCampaignId(), SessionUtil.getUserInfo());
						List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList = reportService.getPhoneHeaderReportEffectiveCallsByCampaignId(campaignDTO.getCampaignId(),SessionUtil.getUserInfo());
						viewCampaignPhoneReportForm.loadFields(customPhoneHeaderReportDTO,customPhoneHeaderReportEffectiveCallsDTOList);
						Integer businessUnitId = null;
						if (campaignDTO.getBusinessUnit() != null && campaignDTO.getBusinessUnit().getCode() != null)
							businessUnitId = Integer.parseInt(campaignDTO.getBusinessUnit().getCode());
						
						List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO = reportService.getEffectivenessByCampaignIdAndBusinessId(campaignDTO.getCampaignId(), businessUnitId, campaignDTO.getStartDate(), campaignDTO.getEndDate());

						viewCampaignPhoneReportForm.buildDownloadButton(customPhoneHeaderReportDTO, customPhoneHeaderReportEffectiveCallsDTOList, customMessageEffectivenessReportDTO);
						adminEntityComponent.setForm(viewCampaignPhoneReportForm);
					}
					
					if (campaignDTO.getContactType().equals(CampaignContactTypeEnum.EMAIL)){
						ViewCampaignMessagesReportForm viewCampaignMessagesReportForm = new ViewCampaignMessagesReportForm(reportService, parametrizacionService, getUI());
						CustomMessageHeaderReportDTO customMessageHeaderReportDTO = reportService.getMessageHeaderReportByCampaignId(campaignDTO.getCampaignId(), CampaignContactTypeEnum.EMAIL.getValue(), SessionUtil.getUserInfo());
						List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList = reportService.getMessageHeaderReportEffectiveByCampaignId(campaignDTO.getCampaignId(),CampaignContactTypeEnum.EMAIL.getValue(),SessionUtil.getUserInfo());
						List<CustomMessageClicksReportDTO> customMessageClicksReportList = reportService.getClicksByCampaignId(campaignDTO.getCampaignId(), SessionUtil.getUserInfo());
						Integer businessUnitId = null;
						if (campaignDTO.getBusinessUnit() != null && campaignDTO.getBusinessUnit().getCode() != null)
							businessUnitId = Integer.parseInt(campaignDTO.getBusinessUnit().getCode());
						
						List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO = reportService.getEffectivenessByCampaignIdAndBusinessId(campaignDTO.getCampaignId(), businessUnitId, campaignDTO.getStartDate(), campaignDTO.getEndDate());
						viewCampaignMessagesReportForm.loadFields(customMessageHeaderReportDTO,customPhoneHeaderReportEffectiveCallsDTOList);
						viewCampaignMessagesReportForm.buildDownloadButton(customMessageHeaderReportDTO,customPhoneHeaderReportEffectiveCallsDTOList, customMessageClicksReportList, customMessageEffectivenessReportDTO);
						adminEntityComponent.setForm(viewCampaignMessagesReportForm);
					}
					
					if (campaignDTO.getContactType().equals(CampaignContactTypeEnum.SMS)){
						ViewCampaignMessagesReportForm viewCampaignMessagesReportForm = new ViewCampaignMessagesReportForm(reportService, parametrizacionService, getUI());
						CustomMessageHeaderReportDTO customMessageHeaderReportDTO = reportService.getMessageHeaderReportByCampaignId(campaignDTO.getCampaignId(), CampaignContactTypeEnum.SMS.getValue(), SessionUtil.getUserInfo());
						List<CustomPhoneHeaderReportEffectiveCallsDTO> customPhoneHeaderReportEffectiveCallsDTOList = reportService.getMessageHeaderReportEffectiveByCampaignId(campaignDTO.getCampaignId(),CampaignContactTypeEnum.SMS.getValue(),SessionUtil.getUserInfo());
						Integer businessUnitId = null;
						if (campaignDTO.getBusinessUnit() != null)
							businessUnitId = Integer.parseInt(campaignDTO.getBusinessUnit().getCode());
						
						List<CustomMessageEffectivenessReportDTO> customMessageEffectivenessReportDTO = reportService.getEffectivenessByCampaignIdAndBusinessId(campaignDTO.getCampaignId(), businessUnitId, campaignDTO.getStartDate(), campaignDTO.getEndDate());
						viewCampaignMessagesReportForm.loadFields(customMessageHeaderReportDTO,customPhoneHeaderReportEffectiveCallsDTOList);
						viewCampaignMessagesReportForm.buildDownloadButton(customMessageHeaderReportDTO,customPhoneHeaderReportEffectiveCallsDTOList, null, customMessageEffectivenessReportDTO);
						adminEntityComponent.setForm(viewCampaignMessagesReportForm);
					}

					Button okButton = new Button(Messages.getString("General.ok"));
					okButton.addClickListener(new Button.ClickListener(){

						/** Serial UID */
						private static final long serialVersionUID = -733051339640424212L;
						@Override
						public void buttonClick(ClickEvent event) {
							adminEntityComponent.getWindow().close();
						}
					});

					adminEntityComponent.openAdminForm(Messages.getString("CampaignForm.view"), okButton, okButton, true, true, true);					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return viewButton;
	}

	
	/**
	 * This method constructs a table with tracing information for a once campaign
	 * @param campaing
	 * @return Table
	 */
	@SuppressWarnings({ "unchecked"})
	private Table getTableResults(CampaignDTO campaing){

		try{

			List<TracingDTO> tracingList = tracingService.getTracingByCampaignId(campaing, SessionUtil.getUserInfo());

			if (tracingList != null && !tracingList.isEmpty()){

				Table reportTable = new Table();
				reportTable.addContainerProperty(REPORT_CAMPAIGN_NAME, String.class, "", Messages.getString("CampaignForm.name"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_PHONE, String.class, "", Messages.getString("ClientForm.telephone"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_EMAIL, String.class, "", Messages.getString("ClientForm.email"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_STATUS, String.class, "", Messages.getString("ClientForm.status"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_SUB_STATUS, String.class, "", Messages.getString("ClientForm.subStatus"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_DATE, String.class, "", Messages.getString("ClientForm.last.status"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_OBS, String.class, "", Messages.getString("ClientForm.comment"), null, null);

				for (TracingDTO tracing: tracingList){

					reportTable.addItem(tracing);
					Item item = reportTable.getItem(tracing);
					item.getItemProperty(REPORT_CAMPAIGN_NAME).setValue(tracing.getClient().getFullName());
					item.getItemProperty(REPORT_CAMPAIGN_PHONE).setValue(tracing.getClient().getCellPhone());
					item.getItemProperty(REPORT_CAMPAIGN_EMAIL).setValue(tracing.getClient().getEmail());
					item.getItemProperty(REPORT_CAMPAIGN_STATUS).setValue(tracing.getTracingStatus() != null ? Messages.getString(tracing.getTracingStatus().getViewKey()) : "");
					item.getItemProperty(REPORT_CAMPAIGN_SUB_STATUS).setValue(tracing.getTracingSubStatus() != null ? tracing.getTracingSubStatus().getName() : "");
					item.getItemProperty(REPORT_CAMPAIGN_DATE).setValue(tracing.getDateLastStatus() != null ? CommonUtil.formatDate(tracing.getDateLastStatus(), "dd/MM/yyyy") : "");
					item.getItemProperty(REPORT_CAMPAIGN_OBS).setValue(tracing.getObservations());

				}

				return reportTable;
			}

			List<TracingMessageDTO> tracingListMessage = tracingService.getTracingMessageByCampaignId(campaing, SessionUtil.getUserInfo());

			if (tracingListMessage != null && !tracingListMessage.isEmpty()){

				Table reportTable = new Table();
				reportTable.addContainerProperty(REPORT_CAMPAIGN_NAME, String.class, "", Messages.getString("CampaignForm.name"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_PHONE, String.class, "", Messages.getString("ClientForm.telephone"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_EMAIL, String.class, "", Messages.getString("ClientForm.email"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_STATUS, String.class, "", Messages.getString("ClientForm.status"), null, null);
				reportTable.addContainerProperty(REPORT_CAMPAIGN_DATE, String.class, "", Messages.getString("ClientForm.last.status"), null, null);

				for (TracingMessageDTO tracing: tracingListMessage){

					reportTable.addItem(tracing);
					Item item = reportTable.getItem(tracing);
					item.getItemProperty(REPORT_CAMPAIGN_NAME).setValue(tracing.getClient().getFullName());
					item.getItemProperty(REPORT_CAMPAIGN_PHONE).setValue(tracing.getClient().getCellPhone());
					item.getItemProperty(REPORT_CAMPAIGN_EMAIL).setValue(tracing.getClient().getEmail());
					item.getItemProperty(REPORT_CAMPAIGN_STATUS).setValue(tracing.getTracingMessageStatus() != null ? Messages.getString(tracing.getTracingMessageStatus().getViewKey()) : "");
					item.getItemProperty(REPORT_CAMPAIGN_DATE).setValue(CommonUtil.formatDate(tracing.getDateLastStatus(), "dd/MM/yyyy"));

				}

				return reportTable;
			}

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}