package com.fi.crm.campaigns.web.components;

import com.fi.crm.campaigns.common.enums.InterfaceActionModeEnum;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SpringContextHelper;
import com.fi.crm.campaigns.web.views.SecureBaseView;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public abstract class AdminStructureBaseView<T> extends SecureBaseView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected Accordion accordion = new Accordion();
	protected HeaderComponent headerComponent = new HeaderComponent();
	protected AdminstrativeComponent adminComponent;
	protected SpringContextHelper helper;
	protected Button newSearch;
	protected HorizontalLayout actionLayout;
	@SuppressWarnings("rawtypes")
	protected AdminEntityTabView mainAdminEntityTabView;
	
	public void initView(String imageName) {
		adminComponent = new AdminstrativeComponent(imageName);
		actionLayout = new HorizontalLayout();
		actionLayout.setSpacing(true);
		newSearch = new Button(Messages.getString("General.new.search"));
		newSearch.setVisible(false);
		newSearch.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				tabIntialize();	
				mainAdminEntityTabView.changeInterfaceActionMode(InterfaceActionModeEnum.SEARCH_MODE);
			}
		});
		actionLayout.addComponent(newSearch);
		addComponent(headerComponent);
		addComponent(adminComponent);
		addComponent(accordion);
		addComponent(actionLayout);
		setMargin(true);
		setSpacing(true);
		/*
    	 * servicios
    	 */
    	helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());		
	}
	
	public abstract void searchEntityAction(T t);
	public abstract void addTab();
	public abstract void tabIntialize();
	public abstract void removeTab(Component c);
	
	
	/**
	 * @return the headerComponent
	 */
	public HeaderComponent getHeaderComponent() {
		return headerComponent;
	}

	/**
	 * @param headerComponent the headerComponent to set
	 */
	public void setHeaderComponent(HeaderComponent headerComponent) {
		this.headerComponent = headerComponent;
	}
	
	public void addActionLayout(Component component){
		actionLayout.addComponent(component);
	}
	
	public void removeActionLayout(Component component){
		actionLayout.removeComponent(component);
	}
	
	public void setVisibleNewSearch(boolean visible){
		newSearch.setVisible(visible);
	}
}
