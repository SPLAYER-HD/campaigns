package com.fi.crm.campaigns.web.views.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.server.FontAwesome;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 28/2015
 */
public class ConfigCampaignView extends BaseView {

	/** Serial UID */
	private static final long serialVersionUID = 8023536940842852705L;

	/* Constants */
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigCampaignView.class);

	/** Default constructor */
	public ConfigCampaignView() {
		super();
	}

	/**
	 * 
	 */
	public void loadUI(){

		try{

			LOGGER.debug("Start view: ConfigCampaignView.loadUI!!");

			section.setContent(FontAwesome.COMMENTS, Messages.getString("ConfigCampaignView.config.campaigns"));

		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}

		//		SecurityUtils.getSubject().isPermitted(PermissionEnum.CAMPAIGN_DELETE.toString());
		//		section.setContent(FontAwesome.HOME, Messages.getString("ConfigCampaignView.config.campaigns"));
		//		
		//		searchLayout = new HorizontalLayout();
		//		searchLayout.setSpacing(true);
		//		
		//		campaignStatusFilter = new ComboBox();
		//		dateRangeComponent = new DateRangeComponent();
		//		search = new Button(Messages.getString("ConfigCampaignView.see"));	
		//		
		//		CommonUtil.setAttributes(campaignStatusFilter, Messages.getString("ConfigCampaignView.campaignStatusFilter"), false, false);
		//		
		//		CatalogUtil.addCampaignStatus(campaignStatusFilter);
		//
		//		searchLayout.addComponent(campaignStatusFilter);
		//		searchLayout.addComponent(dateRangeComponent);
		//		searchLayout.addComponent(search);
		//		contentLayout.addComponent(searchLayout);

	}
}
