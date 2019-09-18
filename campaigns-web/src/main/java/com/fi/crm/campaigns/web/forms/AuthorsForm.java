/**
 * 
 */
package com.fi.crm.campaigns.web.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.web.util.CatalogUtil;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 29/2015 
 */
public class AuthorsForm extends BaseFormGrid{

	/** Serial UID */
	private static final long serialVersionUID = 8896205720323392477L;

	/* Attributes */
	private VerticalLayout globalLayout;
	private TextField firstName;
	private TextField lastName1;
	private TextField lastName2;
	private TextField email;
	private CheckBox status;
	private ComboBox pdusuario;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorsForm.class);

	/** Default constructor */
	public AuthorsForm(){
		super();
		setImmediate(true);
		this.globalLayout = new VerticalLayout();
		addComponent(this.globalLayout);
		this.init();
		addStyleName("campaigns_pop-up");
	}

	/** Init method */
	private void init(){

		try{

			this.firstName = new TextField();
			this.lastName1 = new TextField();
			this.lastName2 = new TextField();
			this.email = new TextField();
			this.email.addValidator(new EmailValidator(Messages.getString("Validator.email.format")));
			this.email.setWidth("350px");

			CommonUtil.setAttributes(this.firstName, Messages.getString("AuthorsForm.firstName"), 50, true, false);
			CommonUtil.setAttributes(this.lastName1, Messages.getString("AuthorsForm.lastName1"), 50, true, false);
			CommonUtil.setAttributes(this.lastName2, Messages.getString("AuthorsForm.lastName2"), 50, false, false);
			CommonUtil.setAttributes(this.email, Messages.getString("AuthorsForm.email"), 50, true, false);

			status = new CheckBox();
			pdusuario = new ComboBox();
			this.pdusuario.setWidth("350px");
			CommonUtil.setAttributes(this.status, Messages.getString("AuthorsForm.status"),false, false);
			CommonUtil.setAttributes(this.pdusuario, Messages.getString("AuthorsForm.pdusuario"), true, true);
			
			CatalogUtil.loadSelectPdUsuario(this.pdusuario);
			
			this.globalLayout.addComponent(this.firstName);
			this.globalLayout.addComponent(this.lastName1);
			this.globalLayout.addComponent(this.lastName2);
			this.globalLayout.addComponent(this.email);
			this.globalLayout.addComponent(this.status);
			this.globalLayout.addComponent(this.pdusuario);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}
}