package com.fi.crm.campaigns.web.views.secure;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.web.components.HeaderComponent;
import com.fi.crm.campaigns.web.components.SectionComponent;
import com.fi.crm.campaigns.web.util.SpringContextHelper;
import com.fi.crm.campaigns.web.views.SecureBaseView;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.VerticalLayout;

/**
 * @company
 * @author
 * @date
 */
public class BaseView extends SecureBaseView {

	/** Serial UID */
	private static final long serialVersionUID = 1L;

	/* Variables */
	protected SectionComponent section;
	protected HeaderComponent header;
	protected VerticalLayout contentLayout;
	protected SpringContextHelper springContextHelper;
	protected Subject subject;

	/* Constants */
	private static Logger logger = LoggerFactory.getLogger(BaseView.class);

	/**
	 * 
	 */
	public BaseView() {

		super();
		header = new HeaderComponent();
		addComponent(header);

		section = new SectionComponent(FontAwesome.HOME, "Inicio");
		addComponent(this.section);
		this.springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

		this.subject = SecurityUtils.getSubject();

		contentLayout = new VerticalLayout();
		contentLayout.setMargin(true);
		contentLayout.setSpacing(true);
		contentLayout.setHeight("-1px");
		addComponent(contentLayout);
		logger.error("finished BaseView constructor");
	}
}