/**
 * 
 */
package com.fi.crm.campaigns.web.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 5/2015
 */
public class ViewCampaignForm extends BaseFormGrid{

	/** Serial UID */
	private static final long serialVersionUID = -2029803880737200808L;

	/* Attributes */
	private VerticalLayout mainLayout;
	private Label name;
	private TextArea description;
	private Label contactType;
	private Label status;
	private Label campaignDate;
	private Label eventDate;
	private CheckBox automaticCalling;
	private TextArea message;
	private Label store;
	private Label efectiveCount;
	private Label totalCount;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewCampaignForm.class);

	/** Default constructor */
	public ViewCampaignForm(){
		super();
		setImmediate(true);
		this.mainLayout = new VerticalLayout();
		addComponent(this.mainLayout);
		addStyleName("campaigns_pop-up");
		this.init();
	}

	/** */
	private void init(){

		try{

			this.name = new Label();
			this.description = new TextArea();
			this.description.setColumns(40);
			this.description.setRows(2);
			this.contactType = new Label();
			this.status = new Label();
			this.campaignDate = new Label();
			this.eventDate = new Label();
			this.automaticCalling = new CheckBox();
			this.automaticCalling.setReadOnly(true);
			this.message = new TextArea();
			this.message.setColumns(40);
			this.message.setRows(2);
			this.store = new Label();
			this.efectiveCount = new Label();
			this.totalCount = new Label();

			Label nameLabel = new Label(Messages.getString("CampaignForm.name"));
			nameLabel.addStyleName("tinytitlefont");
			Label descLabel = new Label(Messages.getString("CampaignForm.description"));
			descLabel.addStyleName("tinytitlefont");
			Label contactTypeLabel = new Label(Messages.getString("CampaignForm.contactType"));
			contactTypeLabel.addStyleName("tinytitlefont");
			Label statusLabel = new Label(Messages.getString("CampaignForm.status"));
			statusLabel.addStyleName("tinytitlefont");
			Label messageLabel = new Label(Messages.getString("CampaignForm.message"));
			messageLabel.addStyleName("tinytitlefont");
			Label storeLabel = new Label(Messages.getString("CampaignForm.store"));
			storeLabel.addStyleName("tinytitlefont");
			Label automaticCallingLabel = new Label(Messages.getString("CampaignForm.automaticCalling"));
			automaticCallingLabel.addStyleName("tinytitlefont");
			Label advanceLabel = new Label(Messages.getString("CampaignForm.advance"));
			advanceLabel.addStyleName("tinytitlefont");
			Label efectivityLabel = new Label(Messages.getString("CampaignForm.efectivity"));
			efectivityLabel.addStyleName("tinytitlefont");

			/*  */
			HorizontalLayout nameLayout = new HorizontalLayout();
			nameLayout.setSpacing(true);
			nameLayout.addComponent(nameLabel);
			nameLayout.addComponent(this.name);

			HorizontalLayout descLayout = new HorizontalLayout();
			descLayout.setSpacing(true);
			descLayout.addComponent(descLabel);
			descLayout.addComponent(this.description);

			HorizontalLayout typeLayout = new HorizontalLayout();
			typeLayout.setSpacing(true);
			typeLayout.addComponent(contactTypeLabel);
			typeLayout.addComponent(this.contactType);
			HorizontalLayout statusLayout = new HorizontalLayout();
			statusLayout.setSpacing(true);
			statusLayout.addComponent(statusLabel);
			statusLayout.addComponent(this.status);

			HorizontalLayout contactLayout = new HorizontalLayout();
			contactLayout.setSpacing(true);
			contactLayout.addComponent(typeLayout);
			contactLayout.addComponent(statusLayout);

			Panel campaignDatePanel = new Panel();
			campaignDatePanel.setWidth("-1px");
			campaignDatePanel.setCaption(Messages.getString("CampaignForm.validity"));
			HorizontalLayout campaignDateLayout = new HorizontalLayout();
			campaignDateLayout.setWidth("100%");
			campaignDateLayout.setSpacing(true);
			campaignDateLayout.addComponent(this.campaignDate);
			campaignDatePanel.setContent(campaignDateLayout);

			Panel eventDatePanel = new Panel();
			eventDatePanel.setWidth("-1px");
			eventDatePanel.setCaption(Messages.getString("CampaignForm.event"));
			HorizontalLayout eventDateLayout = new HorizontalLayout();
			eventDateLayout.setWidth("100%");
			eventDateLayout.setSpacing(true);
			eventDateLayout.addComponent(this.eventDate);
			eventDatePanel.setContent(eventDateLayout);

			HorizontalLayout messageLayout = new HorizontalLayout();
			messageLayout.setSpacing(true);
			messageLayout.addComponent(messageLabel);
			messageLayout.addComponent(this.message);

			HorizontalLayout storeLayout = new HorizontalLayout();
			storeLayout.setSpacing(true);
			storeLayout.addComponent(storeLabel);
			storeLayout.addComponent(this.store);

			HorizontalLayout autoCallLayout = new HorizontalLayout();
			autoCallLayout.setSpacing(true);
			autoCallLayout.addComponent(this.automaticCalling);
			autoCallLayout.addComponent(automaticCallingLabel);

			HorizontalLayout advanceLayout = new HorizontalLayout();
			advanceLayout.setSpacing(true);
			advanceLayout.addComponent(advanceLabel);
			advanceLayout.addComponent(this.efectiveCount);

			HorizontalLayout totalLayout = new HorizontalLayout();
			totalLayout.setSpacing(true);
			totalLayout.addComponent(efectivityLabel);
			totalLayout.addComponent(this.totalCount);

			this.mainLayout.setMargin(true);
			this.mainLayout.setSpacing(true);
			this.mainLayout.addComponent(nameLayout);
			this.mainLayout.addComponent(descLayout);
			this.mainLayout.addComponent(contactLayout);
			this.mainLayout.addComponent(campaignDatePanel);
			this.mainLayout.addComponent(eventDatePanel);
			this.mainLayout.addComponent(messageLayout);
			this.mainLayout.addComponent(storeLayout);
			this.mainLayout.addComponent(autoCallLayout);
			this.mainLayout.addComponent(advanceLayout);
			this.mainLayout.addComponent(totalLayout);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * @param campaignDTO
	 */
	public void loadFields(CampaignDTO campaignDTO){

		try{

			this.name.setValue(campaignDTO.getName());
			this.description.setValue(campaignDTO.getDescription());
			this.description.setReadOnly(true);
			this.contactType .setValue(Messages.getString(campaignDTO.getContactType().getViewKey()));
			this.status.setValue(Messages.getString(campaignDTO.getStatus().getViewKey()));

			String campaignDate = CommonUtil.formatDate(campaignDTO.getStartDate(), "dd/MM/yyyy") 
					+ " - " + CommonUtil.formatDate(campaignDTO.getEndDate(), "dd/MM/yyyy");

			this.campaignDate.setValue(campaignDate);

			String eventDate = CommonUtil.formatDate(campaignDTO.getEventStartDate(), "dd/MM/yyyy") 
					+ " - " + CommonUtil.formatDate(campaignDTO.getEventEndDate(), "dd/MM/yyyy");

			this.eventDate.setValue(eventDate);
			this.message.setValue(campaignDTO.getMessage());
			this.message.setReadOnly(true);
			this.store.setValue(campaignDTO.getStore().getName());
			this.efectiveCount.setValue(campaignDTO.getEfectiveCount() != null ? campaignDTO.getEfectiveCount().toString() : "0");
			this.totalCount.setValue(campaignDTO.getTotalCount() != null ? campaignDTO.getTotalCount().toString() : "0");

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}
}