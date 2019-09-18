package com.fi.crm.campaigns.web.components;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author JoseAugusto
 */
public class CampaignComponent extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private Panel mainPanel;

	@AutoGenerated
	private VerticalLayout verticalLayoutPanel;

	@AutoGenerated
	private Label eventDateLabel;

	@AutoGenerated
	private Label campaignDateLabel;

	@AutoGenerated
	private Label phoneIconLabel;

	@AutoGenerated
	private Label campaignNameLabel;

	@AutoGenerated
	private Button showCampaignButton;

	/** Serial UID */
	private static final long serialVersionUID = -8063156324438600678L;

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */
	public CampaignComponent() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		loadMainLayout();
	}

	private void loadMainLayout() {
		showCampaignButton.setIcon(FontAwesome.ARROW_RIGHT);
		showCampaignButton.setCaption("");
		phoneIconLabel.addStyleName("callicon");
		phoneIconLabel.setContentMode(ContentMode.HTML);
		phoneIconLabel.setValue(FontAwesome.PHONE.getHtml());
		phoneIconLabel.setCaption("");
		mainPanel.setStyleName("iel-form");
	}

	/**
	 * @return the eventDateLabel
	 */
	public Label getEventDateLabel() {
		return eventDateLabel;
	}

	/**
	 * @param eventDateLabel the eventDateLabel to set
	 */
	public void setEventDateLabel(Label eventDateLabel) {
		this.eventDateLabel = eventDateLabel;
	}

	/**
	 * @return the campaignDateLabel
	 */
	public Label getCampaignDateLabel() {
		return campaignDateLabel;
	}

	/**
	 * @param campaignDateLabel the campaignDateLabel to set
	 */
	public void setCampaignDateLabel(Label campaignDateLabel) {
		this.campaignDateLabel = campaignDateLabel;
	}

	/**
	 * @return the campaignNameLabel
	 */
	public Label getCampaignNameLabel() {
		return campaignNameLabel;
	}

	/**
	 * @param campaignNameLabel the campaignNameLabel to set
	 */
	public void setCampaignNameLabel(Label campaignNameLabel) {
		this.campaignNameLabel = campaignNameLabel;
	}

	/**
	 * @return the showCampaignButton
	 */
	public Button getShowCampaignButton() {
		return showCampaignButton;
	}

	/**
	 * @param showCampaignButton the showCampaignButton to set
	 */
	public void setShowCampaignButton(Button showCampaignButton) {
		this.showCampaignButton = showCampaignButton;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);

		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");

		// mainPanel
		mainPanel = buildMainPanel();
		mainLayout.addComponent(mainPanel);

		return mainLayout;
	}

	@AutoGenerated
	private Panel buildMainPanel() {
		// common part: create layout
		mainPanel = new Panel();
		mainPanel.setImmediate(false);
		mainPanel.setWidth("300px");
		mainPanel.setHeight("-1px");

		// verticalLayoutPanel
		verticalLayoutPanel = buildVerticalLayoutPanel();
		mainPanel.setContent(verticalLayoutPanel);

		return mainPanel;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayoutPanel() {
		// common part: create layout
		verticalLayoutPanel = new VerticalLayout();
		verticalLayoutPanel.setImmediate(false);
		verticalLayoutPanel.setWidth("100.0%");
		verticalLayoutPanel.setHeight("-1px");
		verticalLayoutPanel.setMargin(false);
		verticalLayoutPanel.setSpacing(true);

		// showCampaignButton
		showCampaignButton = new Button();
		showCampaignButton.setCaption("Button");
		showCampaignButton.setImmediate(true);
		showCampaignButton.setWidth("-1px");
		showCampaignButton.setHeight("-1px");
		verticalLayoutPanel.addComponent(showCampaignButton);
		verticalLayoutPanel.setComponentAlignment(showCampaignButton,
				new Alignment(6));

		// campaignNameLabel
		campaignNameLabel = new Label();
		campaignNameLabel.setImmediate(false);
		campaignNameLabel.setWidth("-1px");
		campaignNameLabel.setHeight("-1px");
		campaignNameLabel.setValue("Label");
		verticalLayoutPanel.addComponent(campaignNameLabel);
		verticalLayoutPanel.setComponentAlignment(campaignNameLabel,
				new Alignment(48));

		// phoneIconLabel
		phoneIconLabel = new Label();
		phoneIconLabel.setImmediate(false);
		phoneIconLabel.setWidth("-1px");
		phoneIconLabel.setHeight("-1px");
		verticalLayoutPanel.addComponent(phoneIconLabel);
		verticalLayoutPanel.setComponentAlignment(phoneIconLabel,
				new Alignment(48));

		// campaignDateLabel
		campaignDateLabel = new Label();
		campaignDateLabel.setImmediate(false);
		campaignDateLabel.setWidth("-1px");
		campaignDateLabel.setHeight("-1px");
		campaignDateLabel.setValue("Label");
		verticalLayoutPanel.addComponent(campaignDateLabel);
		verticalLayoutPanel.setComponentAlignment(campaignDateLabel,
				new Alignment(48));

		// eventDateLabel
		eventDateLabel = new Label();
		eventDateLabel.setImmediate(false);
		eventDateLabel.setWidth("-1px");
		eventDateLabel.setHeight("-1px");
		eventDateLabel.setValue("Label");
		verticalLayoutPanel.addComponent(eventDateLabel);
		verticalLayoutPanel.setComponentAlignment(eventDateLabel,
				new Alignment(48));

		return verticalLayoutPanel;
	}
}