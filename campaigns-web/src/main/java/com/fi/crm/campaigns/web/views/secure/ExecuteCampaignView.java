/**
 * 
 */
package com.fi.crm.campaigns.web.views.secure;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.client.ClientServiceInterface;
import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.services.tracing.TracingServiceInterface;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientCampaignDTO;
import com.fi.crm.campaigns.common.dto.ClientDTO;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.TracingDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.web.extensions.CallExtension;
import com.fi.crm.campaigns.web.forms.ClientForm;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 2/2015
 */
public class ExecuteCampaignView extends AdminBaseView{

	/** Serial UID */
	private static final long serialVersionUID = -8833474287972353839L;

	/* Attributes */
	private TracingServiceInterface tracingService;
	private ClientServiceInterface clientService;
	private ConstantServiceInterface constantService;
	private TracingDTO tracingDTO;
	private CampaignDTO campaign;
	private ClientCampaignDTO clientDTO;

	/* Visual components */
	private Panel titlePanel;
	private Panel clientPanel;
	private VerticalLayout mainLayout;
	private FieldGroup fieldGroup;
	private ClientForm clientForm;
	private HorizontalLayout buttonsLayout;
	private Label iconLabel;
	private Label titleLabel;
	private Label dateLabel;
	private HorizontalLayout leftLayout;
	private HorizontalLayout rightLayout;
	private HorizontalLayout panelLayout;
	private HorizontalLayout centerLayout;
	private Label descLabel;
	private Label clientLabel;
	private Label clientNameLabel;
	private Button callButton;
	private Button finishCallButton;
	private Button nextButton;
	private HorizontalLayout bottomLeftLayout;
	private HorizontalLayout bottomRightLayout;
	private HorizontalLayout bottomLayout;
	private HorizontalLayout leftButtonLayout;
	private Button endCallButton;
	private Button endContinueButton;
	private HorizontalLayout rightButtonLayout;
	private CallExtension callExtension;
	private String defaultIndicative;
	private Panel descPanel;
	private Button showMessageButton;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteCampaignView.class);

	/** Default constructor */
	public ExecuteCampaignView(CampaignDTO campaign){

		super();

		try{

			this.callExtension = SessionUtil.getCallExtension();
			this.tracingService = (TracingServiceInterface)springContextHelper.getBean("tracingService");
			this.clientService = (ClientServiceInterface)springContextHelper.getBean("clientService");
			this.constantService = (ConstantServiceInterface)springContextHelper.getBean("constantService");
			this.campaign = campaign;
			this.fieldGroup = new FieldGroup();

			/* Load local indicative */
			HashMap<String, ConstantDTO> constants = this.constantService.getAllConstants();
			this.defaultIndicative = constants.get(ConstantsIdentifierEnum.PHONE_INDICATIVE.getValue()).getValue();

			this.init();

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/** Post method to load local variables into the form */
	private void init(){

		try{

			/* Load components on page */
			section.setContent(FontAwesome.CHECK, Messages.getString("bread.myCampaigns"));

			this.dateLabel = new Label();
			this.dateLabel.addStyleName("titlefont");
			this.dateLabel.setWidth("-1px");

			this.iconLabel = new Label(FontAwesome.PHONE.getHtml(), ContentMode.HTML);
			this.iconLabel.addStyleName("callicon");
			this.iconLabel.setWidth("-1px");

			this.titleLabel = new Label();
			this.titleLabel.addStyleName("titlefont");
			this.titleLabel.setWidth("-1px");

			this.leftLayout = new HorizontalLayout();
			this.leftLayout.addComponent(this.iconLabel);
			this.leftLayout.addComponent(this.titleLabel);
			this.leftLayout.setComponentAlignment(this.iconLabel, Alignment.MIDDLE_LEFT);
			this.leftLayout.setComponentAlignment(this.titleLabel, Alignment.MIDDLE_LEFT);

			this.rightLayout = new HorizontalLayout();
			this.rightLayout.addComponent(this.dateLabel);
			this.rightLayout.setComponentAlignment(this.dateLabel, Alignment.MIDDLE_RIGHT);

			this.panelLayout = new HorizontalLayout();
			this.panelLayout.setWidth("100%");
			this.panelLayout.setMargin(new MarginInfo(false, true, false, true));
			this.panelLayout.addComponent(this.leftLayout);
			this.panelLayout.addComponent(this.rightLayout);
			this.panelLayout.setComponentAlignment(this.leftLayout, Alignment.MIDDLE_LEFT);
			this.panelLayout.setComponentAlignment(this.rightLayout, Alignment.MIDDLE_RIGHT);

			this.titlePanel = new Panel();
			this.titlePanel.setStyleName("iel-form");
			this.titlePanel.setWidth("90%");
			this.titlePanel.setContent(this.panelLayout);

			this.descLabel = new Label("",ContentMode.HTML);
			this.descLabel.addStyleName("bodyfont");

			this.centerLayout = new HorizontalLayout();
			this.centerLayout.setWidth("90%");
			this.centerLayout.setMargin(new MarginInfo(false, true, false, true));
			this.centerLayout.addComponent(this.descLabel);
			this.centerLayout.setComponentAlignment(this.descLabel, Alignment.TOP_CENTER);

			
			this.descPanel = new Panel();
			this.descPanel.setStyleName("iel-form");
			this.descPanel.setWidth("90%");
			this.descPanel.setContent(this.centerLayout);
			this.descPanel.setVisible(false);
			this.showMessageButton = new Button ("Ver Mensaje");
			this.showMessageButton.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = -1749248287390847761L;

				@Override
				public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
					descPanel.setVisible(!descPanel.isVisible());
				}
			});
			
			/* Bottom panel */
			this.clientLabel = new Label(Messages.getString("General.cliente"));
			this.clientLabel.addStyleName("titlefont");
			this.clientLabel.setWidth("-1px");

			this.clientNameLabel = new Label();
			this.clientNameLabel.addStyleName("titlefont");
			this.clientNameLabel.setWidth("-1px");

			this.callButton = new Button();
			this.callButton.setCaption(Messages.getString("General.call"));
			this.callButton.setIcon(FontAwesome.PHONE);
			this.callButton.setEnabled(false);
			this.callButton.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = -7929821496347675712L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event) {
					callExtension.initCall();
					callExtension.makeCall(getNumberToCall());
					setClientData();
				}
			});

			this.finishCallButton = new Button();
			this.finishCallButton.setCaption(Messages.getString("General.hangUp"));
			this.finishCallButton.setIcon(FontAwesome.PHONE);
			this.finishCallButton.setEnabled(false);
			this.finishCallButton.setVisible(false);
			this.finishCallButton.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = -7929821496347675712L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event) {
					callExtension.hangUp();
					finishCallButton.setVisible(false);
				}
			});

			this.nextButton = new Button();
			this.nextButton.setCaption(Messages.getString("General.next"));
			this.nextButton.setIcon(FontAwesome.ARROW_RIGHT);
			this.nextButton.setEnabled(false);
			this.nextButton.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = 5765358972232487062L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event){
					setCampaignData();
				}
			});

			this.bottomLeftLayout = new HorizontalLayout();
			this.bottomLeftLayout.addComponent(this.clientLabel);
			this.bottomLeftLayout.addComponent(this.clientNameLabel);
			this.bottomLeftLayout.setComponentAlignment(this.clientLabel, Alignment.MIDDLE_LEFT);
			this.bottomLeftLayout.setComponentAlignment(this.clientNameLabel, Alignment.MIDDLE_LEFT);

			this.bottomRightLayout = new HorizontalLayout();
			this.bottomRightLayout.addComponent(this.callButton);
			this.bottomRightLayout.addComponent(this.finishCallButton);
			this.bottomRightLayout.addComponent(this.nextButton);
			this.bottomRightLayout.setComponentAlignment(this.callButton, Alignment.MIDDLE_RIGHT);
			this.bottomRightLayout.setComponentAlignment(this.nextButton, Alignment.MIDDLE_RIGHT);

			this.bottomLayout = new HorizontalLayout();
			this.bottomLayout.setWidth("90%");
			this.bottomLayout.setMargin(new MarginInfo(false, true, false, true));
			this.bottomLayout.addComponent(this.bottomLeftLayout);
			this.bottomLayout.addComponent(this.bottomRightLayout);
			this.bottomLayout.setComponentAlignment(this.bottomLeftLayout, Alignment.MIDDLE_LEFT);
			this.bottomLayout.setComponentAlignment(this.bottomRightLayout, Alignment.MIDDLE_RIGHT);

			/* Client panel */
			this.clientForm = new ClientForm();
			this.clientPanel = new Panel();
			this.clientPanel.setContent(this.clientForm);
			this.clientPanel.setVisible(false);
			this.clientPanel.setWidth("90%");

			/* Buttons layout */
			this.leftButtonLayout = new HorizontalLayout();
			this.leftButtonLayout.setSpacing(true);

			this.endCallButton = new Button();
			this.endCallButton.setCaption(Messages.getString("General.call.end"));
			this.endCallButton.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = 4983064771494159924L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event){

					if (CommonUtil.validate(clientForm)){

						try{

							fieldGroup.commit();
							tracingDTO.setTracingStatus((TracingStatusEnum)clientForm.getStatus().getValue());//null pointer exception 24 febrero 2015 en pc marcela
							tracingDTO.setTracingSubStatus((TracingStatusDTO)clientForm.getSubStatus().getValue());
							tracingDTO.setObservations(clientForm.getComments().getValue());
							tracingDTO.setCalling(false);
							tracingService.updateTracing(tracingDTO, SessionUtil.getUserInfo());

							/* Update Client */
							clientService.createOrUpdateClient(clientDTO);
							setCampaignData();

						}catch (Exception e){
							LOGGER.error(e.getMessage(), e);
						}

					}else
						CommonUtil.showNotification(Messages.getString("General.validate.form"), Type.ERROR_MESSAGE, Position.BOTTOM_CENTER);
				}
			});

			this.leftButtonLayout.addComponent(this.endCallButton);
			this.leftButtonLayout.setComponentAlignment(this.endCallButton, Alignment.MIDDLE_RIGHT);

			this.rightButtonLayout = new HorizontalLayout();
			this.rightButtonLayout.setSpacing(true);

			this.endContinueButton = new Button();
			this.endContinueButton.setCaption(Messages.getString("General.call.continue"));
			this.endContinueButton.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = 5681551642799145073L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event){

					if (CommonUtil.validate(clientForm)){

						try{

							fieldGroup.commit();
							tracingDTO.setTracingStatus((TracingStatusEnum)clientForm.getStatus().getValue());
							tracingDTO.setTracingSubStatus((TracingStatusDTO)clientForm.getSubStatus().getValue());
							tracingDTO.setObservations(clientForm.getComments().getValue());
							tracingDTO.setCalling(false);
							tracingService.updateTracing(tracingDTO, SessionUtil.getUserInfo());
							clientService.createOrUpdateClient(clientDTO);
							setCampaignData();
							setClientData();

							/* Make the call for the next client */
							callExtension.initCall();
							callExtension.makeCall(getNumberToCall());

						}catch (Exception e){
							LOGGER.error(e.getMessage(), e);
						}

					}else
						CommonUtil.showNotification(Messages.getString("General.validate.form"), Type.ERROR_MESSAGE, Position.BOTTOM_CENTER);
				}
			});

			this.leftButtonLayout.addComponent(this.endContinueButton);
			this.leftButtonLayout.setComponentAlignment(this.endContinueButton, Alignment.MIDDLE_LEFT);

			this.buttonsLayout = new HorizontalLayout();
			this.buttonsLayout.setSpacing(true);
			this.buttonsLayout.setVisible(false);
			this.buttonsLayout.addComponent(this.leftButtonLayout);
			this.buttonsLayout.addComponent(this.rightButtonLayout);

			/* Main layout */
			this.mainLayout = new VerticalLayout();
			this.mainLayout.setSpacing(true);
			this.mainLayout.addComponent(this.titlePanel);
			this.mainLayout.setComponentAlignment(this.titlePanel, Alignment.TOP_CENTER);
			this.mainLayout.addComponent(this.showMessageButton);
			this.mainLayout.setComponentAlignment(this.showMessageButton, Alignment.TOP_CENTER);
			this.mainLayout.addComponent(this.descPanel);
			this.mainLayout.setComponentAlignment(this.descPanel, Alignment.TOP_CENTER);
			this.mainLayout.addComponent(this.bottomLayout);
			this.mainLayout.setComponentAlignment(this.bottomLayout, Alignment.BOTTOM_CENTER);
			this.mainLayout.addComponent(this.clientPanel);
			this.mainLayout.setComponentAlignment(this.clientPanel, Alignment.BOTTOM_CENTER);
			this.mainLayout.addComponent(this.buttonsLayout);
			this.mainLayout.setComponentAlignment(this.buttonsLayout, Alignment.BOTTOM_CENTER);
			addComponent(this.mainLayout);

			String campaignDate = CommonUtil.formatDate(this.campaign.getStartDate(), "dd/MM/yyyy") 
					+ " - " + CommonUtil.formatDate(this.campaign.getEndDate(), "dd/MM/yyyy");
			this.dateLabel.setValue(campaignDate);
			this.titleLabel.setValue(this.campaign.getName());
			this.descLabel.setValue(this.campaign.getMessage());
			setCampaignData();

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/** load campaign data */
	private void setCampaignData(){

		try{

			this.tracingDTO = this.tracingService.getClientTracingByCampaign(this.campaign, SessionUtil.getUserInfo());
			this.clientPanel.setVisible(false);
			this.buttonsLayout.setVisible(false);
			this.finishCallButton.setEnabled(false);
			this.finishCallButton.setVisible(false);

			if (this.tracingDTO != null){

				this.clientDTO = this.tracingDTO.getClient();

				String clientName = this.clientDTO.getFirstName() 
						+ (this.clientDTO.getLastName1() != null && !this.clientDTO.getLastName1().trim().equals("") ? " " + this.clientDTO.getLastName1().trim() : "")
						+ (this.clientDTO.getLastName2() != null && !this.clientDTO.getLastName2().trim().equals("") ? " " + this.clientDTO.getLastName2().trim() : "");

				this.clientNameLabel.setValue(clientName);
				this.callButton.setEnabled(true);
				this.nextButton.setEnabled(true);
				this.callButton.setVisible(true);
				this.nextButton.setVisible(true);
			}else{
				CommonUtil.showNotification(Messages.getString("Validator.campaigns.client.noClients"), Type.ERROR_MESSAGE);
			}

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/** Load campaign data */
	private void setClientData(){

		try{

			BeanItem<ClientDTO> beanItem = new BeanItem<ClientDTO>(clientDTO);
			this.fieldGroup.setItemDataSource(beanItem);
			this.clientForm.clearForm();
			this.fieldGroup.bindMemberFields(this.clientForm);
			this.clientPanel.setVisible(true);
			this.buttonsLayout.setVisible(true);
			this.callButton.setEnabled(false);
			this.finishCallButton.setEnabled(true);
			this.nextButton.setEnabled(false);
			this.callButton.setVisible(false);
			this.finishCallButton.setVisible(true);
			this.nextButton.setVisible(false);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#fillTable()
	 */
	@Override
	public void fillTable(){}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#defineTable()
	 */
	@Override
	public void defineTable(){}
	
	private String getNumberToCall () {
		String indicative = clientDTO.getIndicative();
		//Prefer calling to the cellphone
		String numberToCall = defaultIndicative + (clientDTO.getCellPhone() == null ? (indicative + clientDTO.getTelephone()) : clientDTO.getCellPhone()) ;
		
		LOGGER.debug("indicative >>> "+ defaultIndicative);
		LOGGER.debug("Number To Call >>> "+ numberToCall);

		return numberToCall;
	}
}