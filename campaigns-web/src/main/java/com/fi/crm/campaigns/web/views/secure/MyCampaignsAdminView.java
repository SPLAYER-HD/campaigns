/**
 * 
 */
package com.fi.crm.campaigns.web.views.secure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.campaign.CampaignsServiceInterface;
import com.fi.crm.campaigns.common.dto.CampaignDTO;
import com.fi.crm.campaigns.web.components.CampaignComponent;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 2/2015
 */
public class MyCampaignsAdminView extends AdminBaseView{

	/** Serial UID */
	private static final long serialVersionUID = 1031020927352770922L;

	/* Attributes */
	private CampaignsServiceInterface campaignService;
	private GridLayout grid;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCampaignsAdminView.class);

	/** Default constructor */
	public MyCampaignsAdminView(){
		super();
		this.init();
	}

	/** Init method */
	private void init(){

		try{

			section.setContent(FontAwesome.CHECK, Messages.getString("bread.myCampaigns"));
			this.grid = new GridLayout();
			this.grid.setColumns(4);
			this.campaignService = (CampaignsServiceInterface)springContextHelper.getBean("campaignsService");
			List<CampaignDTO> campaignList = this.campaignService.getCampaignsByUser(SessionUtil.getUserInfo());

			System.out.println ("Campaigns LIST :" + campaignList.size());
			
			for (final CampaignDTO campaign: campaignList){

				CampaignComponent campaignComponent = new CampaignComponent();
				campaignComponent.getCampaignNameLabel().setValue(campaign.getName());

				String campaignDate = CommonUtil.formatDate(campaign.getStartDate(), "dd/MM/yyyy") 
						+ " - " + CommonUtil.formatDate(campaign.getEndDate(), "dd/MM/yyyy");

				campaignComponent.getCampaignDateLabel().setValue(campaignDate);

				String eventDate = CommonUtil.formatDate(campaign.getEventStartDate(), "dd/MM/yyyy") 
						+ " - " + CommonUtil.formatDate(campaign.getEventEndDate(), "dd/MM/yyyy");

				campaignComponent.getEventDateLabel().setValue(eventDate);

				campaignComponent.getShowCampaignButton().addClickListener(new ClickListener() {

					/** Serial UID */
					private static final long serialVersionUID = 4060285830655211318L;

					/*
					 * (non-Javadoc)
					 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
					 */
					@Override
					public void buttonClick(ClickEvent event) {
						try{
							getUI().setContent(new ExecuteCampaignView(campaign));
						}catch (Exception e){
							LOGGER.error(e.getMessage(), e);
						}
					}
				});

				this.grid.addComponent(campaignComponent);

			}

			this.grid.setMargin(true);
			this.grid.setSpacing(true);
			addComponent(this.grid);

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
}