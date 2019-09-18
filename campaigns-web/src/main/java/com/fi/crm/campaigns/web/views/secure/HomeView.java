package com.fi.crm.campaigns.web.views.secure;

import com.fi.crm.campaigns.web.components.HeaderComponent;
import com.fi.crm.campaigns.web.components.SectionComponent;
import com.fi.crm.campaigns.web.extensions.CallExtension;
import com.fi.crm.campaigns.web.views.SecureBaseView;
import com.vaadin.server.FontAwesome;

public class HomeView extends SecureBaseView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private static Logger logger = LoggerFactory.getLogger(HomeView.class);
	
	CallExtension callExt = null;

	public HomeView(){
		super();
		HeaderComponent header = new HeaderComponent();
		addComponent(header);
		SectionComponent section = new SectionComponent(FontAwesome.HOME, "Inicio");
		addComponent(section);
	}
}
