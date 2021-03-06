package com.fi.crm.campaigns.web.components;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AdminstrativeComponent extends CustomComponent {


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private HorizontalLayout headerLayout;
	@AutoGenerated
	private VerticalLayout titleLayout;
	@AutoGenerated
	private HorizontalLayout sectionDescriptionLayout;
	@AutoGenerated
	private Label sectionDescriptionLabel;
	@AutoGenerated
	private HorizontalLayout sectionTitleLayout;
	@AutoGenerated
	private Label sectionTitleLabel;
	private static final long serialVersionUID = 1L;

	private String imageName;
	private Image image;
	
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public AdminstrativeComponent(String imageName) {
		this.imageName = imageName;
		buildMainLayout();
		setCompositionRoot(mainLayout);

	}

	public String getSectionDescription() {
		return this.sectionDescriptionLabel.getValue();
	}

	public void setSectionDescription(String sectionDescription) {
		this.sectionDescriptionLabel.setValue(sectionDescription);
	}

	public String getSectionTitle() {
		return this.sectionTitleLabel.getValue();
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitleLabel.setValue(sectionTitle);
	}

	/**
	 * @return the sectionTitleLayout
	 */
	public HorizontalLayout getSectionTitleLayout() {
		return sectionTitleLayout;
	}

	/**
	 * @param sectionTitleLayout the sectionTitleLayout to set
	 */
	public void setSectionTitleLayout(HorizontalLayout sectionTitleLayout) {
		this.sectionTitleLayout = sectionTitleLayout;
	}

	/**
	 * @return the sectionTitleLabel
	 */
	public Label getSectionTitleLabel() {
		return sectionTitleLabel;
	}

	/**
	 * @param sectionTitleLabel the sectionTitleLabel to set
	 */
	public void setSectionTitleLabel(Label sectionTitleLabel) {
		this.sectionTitleLabel = sectionTitleLabel;
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
		
		// headerLayout
		headerLayout = buildHeaderLayout();
		mainLayout.addComponent(headerLayout);
		
		return mainLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildHeaderLayout() {
		// common part: create layout
		headerLayout = new HorizontalLayout();
		headerLayout.setImmediate(false);
		headerLayout.setWidth("-1px");
		headerLayout.setHeight("-1px");
		headerLayout.setMargin(false);
		headerLayout.setSpacing(false);
		
		//logo
		image = new Image();
		image.setWidth("-1px");
		image.setHeight("-1px");
		image.setSource(new ThemeResource("images/"+imageName));
		headerLayout.addComponent(image);
		headerLayout.setComponentAlignment(image, Alignment.BOTTOM_RIGHT);
		// titleLayout
		titleLayout = buildTitleLayout();
		headerLayout.addComponent(titleLayout);
		headerLayout.setComponentAlignment(titleLayout, Alignment.BOTTOM_LEFT);
		
		
		return headerLayout;
	}

	@AutoGenerated
	private VerticalLayout buildTitleLayout() {
		// common part: create layout
		titleLayout = new VerticalLayout();
		titleLayout.setImmediate(false);
		titleLayout.setWidth("-1px");
		titleLayout.setHeight("-1px");
		titleLayout.setMargin(false);
		
		// sectionTitleLayout
		sectionTitleLayout = buildSectionTitleLayout();
		titleLayout.addComponent(sectionTitleLayout);
		titleLayout.setComponentAlignment(sectionTitleLayout, Alignment.BOTTOM_LEFT);
		
		// sectionDescriptionLayout
		sectionDescriptionLayout = buildSectionDescriptionLayout();
		titleLayout.addComponent(sectionDescriptionLayout);
		titleLayout.setComponentAlignment(sectionDescriptionLayout, Alignment.BOTTOM_LEFT);
		
		return titleLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildSectionTitleLayout() {
		// common part: create layout
		sectionTitleLayout = new HorizontalLayout();
		sectionTitleLayout.setImmediate(false);
		sectionTitleLayout.setWidth("-1px");
		sectionTitleLayout.setHeight("-1px");
		sectionTitleLayout.setMargin(false);
		sectionTitleLayout.setSpacing(false);
		
		// sectionTitleLabel
		sectionTitleLabel = new Label();
		sectionTitleLabel.setStyleName("sectionTitle");
		sectionTitleLabel.setImmediate(false);
		sectionTitleLabel.setWidth("-1px");
		sectionTitleLabel.setHeight("-1px");
		sectionTitleLabel.setValue("Label");
		sectionTitleLayout.addComponent(sectionTitleLabel);
		sectionTitleLayout.setComponentAlignment(sectionTitleLabel, Alignment.BOTTOM_LEFT);
		
		return sectionTitleLayout;
	}

	@AutoGenerated
	private HorizontalLayout buildSectionDescriptionLayout() {
		// common part: create layout
		sectionDescriptionLayout = new HorizontalLayout();
		sectionDescriptionLayout.setImmediate(false);
		sectionDescriptionLayout.setWidth("-1px");
		sectionDescriptionLayout.setHeight("-1px");
		sectionDescriptionLayout.setMargin(false);
		
		// sectionDescriptionLabel
		sectionDescriptionLabel = new Label();
		sectionDescriptionLabel.setStyleName("sectionDescription");
		sectionDescriptionLabel.setImmediate(false);
		sectionDescriptionLabel.setWidth("-1px");
		sectionDescriptionLabel.setHeight("-1px");
		sectionDescriptionLabel.setValue("Label");
		sectionDescriptionLayout.addComponent(sectionDescriptionLabel);
		
		return sectionDescriptionLayout;
	}	
}