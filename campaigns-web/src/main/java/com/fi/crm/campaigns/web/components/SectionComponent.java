package com.fi.crm.campaigns.web.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class SectionComponent extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FontAwesome icon;
	private String name;
	private Label iconLbl;
	private Label nameLbl;

	private HorizontalLayout mainLayout;

	public SectionComponent(FontAwesome icon, String name) {
		this.icon = icon;
		this.name = name;

		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	private void buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		// mainLayout.setHeight("120px");
		mainLayout.setMargin(new MarginInfo(false, false, false, true));
		mainLayout.setStyleName("titleSec");

		HorizontalLayout insideLayout = new HorizontalLayout();
		insideLayout.setImmediate(false);

		iconLbl = new Label(icon.getHtml(), ContentMode.HTML);
		iconLbl.setStyleName("titleSech1");
		this.nameLbl = new Label(name);
		nameLbl.setStyleName("titleSech1");

		insideLayout.addComponent(iconLbl);
		insideLayout.addComponent(nameLbl);
		mainLayout.addComponent(insideLayout);
		// top-level component properties
		setWidth("100.0%");
	}

	public void setContent(FontAwesome icon, String name){
		iconLbl.setValue(icon.getHtml());
		this.nameLbl.setValue(name);
	}

}
