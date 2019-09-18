/**
 * 
 */
package com.fi.crm.campaigns.web.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.common.dto.ProvinceDTO;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.web.util.CatalogUtil;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Feb, 4/2015
 */
public class ClientForm extends BaseFormGrid{

	/** Serial UID */
	private static final long serialVersionUID = 3769305418521576644L;

	/* Attributes */
	private VerticalLayout mainLayout;
	private ComboBox status;
	private ComboBox subStatus;
	private TextArea comments;
	private ComboBox documentType;
	private TextField documentNumber;
	private TextField firstName;
	private TextField lastName1;
	private TextField lastName2;
	private TextField indicative;
	private TextField telephone;
	private TextField cellPhone;
	private OptionGroup genre;
	private TextField email;
	private TextField address;
	private ComboBox city;
	private ComboBox province;
	private DateField birthday;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientForm.class);

	/** Default constructor */
	public ClientForm(){
		super();
		setImmediate(true);
		this.mainLayout = new VerticalLayout();
		addComponent(this.mainLayout);
		addStyleName("campaigns_pop-up");
		this.init();
	}

	/** Post method to load local variables into the form */
	private void init(){

		try{

			this.status = new ComboBox();
			this.subStatus = new ComboBox();
			this.comments = new TextArea();
			this.documentType = new ComboBox();
			this.documentNumber = new TextField();
			this.firstName = new TextField();
			this.lastName1 = new TextField();
			this.lastName2 = new TextField();
			this.indicative = new TextField();
			this.telephone = new TextField();
			this.cellPhone = new TextField();
			this.genre = new OptionGroup();
			this.email = new TextField();
			this.email.addValidator(new EmailValidator(Messages.getString("Validator.email.format")));
			this.address = new TextField();
			this.city = new ComboBox();
			this.province = new ComboBox();
			this.birthday = new DateField();
			this.birthday.setDateFormat("dd/MM/yyyy");

			/* Load attribute behavior */
			CommonUtil.setAttributes(this.status, Messages.getString("ClientForm.status"), true, false);
			CommonUtil.setAttributes(this.subStatus, Messages.getString("ClientForm.subStatus"), true, false);
			CommonUtil.setAttributes(this.comments, Messages.getString("ClientForm.comment"), 50, false, false);
			CommonUtil.setAttributes(this.documentType, Messages.getString("ClientForm.docType"), false, false);
			CommonUtil.setAttributes(this.documentNumber, Messages.getString("ClientForm.docNum"), 50, false, false);
			CommonUtil.setAttributes(this.firstName, Messages.getString("ClientForm.firstName"), 50, false, false);
			CommonUtil.setAttributes(this.lastName1, Messages.getString("ClientForm.lastName1"), 50, false, false);
			CommonUtil.setAttributes(this.lastName2, Messages.getString("ClientForm.lastName2"), 50, false, false);
			CommonUtil.setAttributes(this.indicative, Messages.getString("ClientForm.indicative"), 4, false, false);
			CommonUtil.setAttributes(this.telephone, Messages.getString("ClientForm.telephone"), 50, false, false);
			CommonUtil.setAttributes(this.cellPhone, Messages.getString("ClientForm.cellPhone"), 50, false, false);
			CommonUtil.setAttributes(this.genre, Messages.getString("ClientForm.genre"), true, false);
			CommonUtil.setAttributes(this.email, Messages.getString("ClientForm.email"), 50, false, false);
			CommonUtil.setAttributes(this.address, Messages.getString("ClientForm.address"), 50, false, false);
			CommonUtil.setAttributes(this.city, Messages.getString("ClientForm.city"), false, false);
			CommonUtil.setAttributes(this.province, Messages.getString("ClientForm.province"), false, false);
			CommonUtil.setAttributes(this.birthday, Messages.getString("ClientForm.birthday"), false, false);

			/* Set attributes into the form */
			this.mainLayout.setMargin(true);
			this.mainLayout.setSpacing(true);
			this.mainLayout.setWidth("100%");
			CatalogUtil.loadSelectTraceStatus(this.status);

			this.status.addValueChangeListener(new Property.ValueChangeListener() {

				/** Serial UID */
				private static final long serialVersionUID = 3381918076694623852L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {

					try{

						TracingStatusEnum tracingStatusEnum = (TracingStatusEnum)event.getProperty().getValue();

						if (tracingStatusEnum != null)
							CatalogUtil.loadSelectTraceSubStatus(subStatus, tracingStatusEnum);
						else
							subStatus.removeAllItems();

					}catch (Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}
			});

			this.mainLayout.addComponent(this.status);
			this.mainLayout.addComponent(this.subStatus);
			this.comments.setWidth("350px");
			this.mainLayout.addComponent(this.comments);
			CatalogUtil.loadSelectDocumentType(this.documentType);

			HorizontalLayout docLayout = new HorizontalLayout();
			docLayout.setSpacing(true);
			docLayout.addComponent(this.documentType);
			docLayout.addComponent(this.documentNumber);
			this.mainLayout.addComponent(docLayout);

			HorizontalLayout nameLayout = new HorizontalLayout();
			nameLayout.setSpacing(true);
			nameLayout.addComponent(this.firstName);
			nameLayout.addComponent(this.lastName1);
			nameLayout.addComponent(this.lastName2);
			this.mainLayout.addComponent(nameLayout);

			HorizontalLayout phoneLayout = new HorizontalLayout();
			phoneLayout.setSpacing(true);
			phoneLayout.addComponent(this.indicative);
			phoneLayout.addComponent(this.telephone);
			phoneLayout.addComponent(this.cellPhone);
			this.mainLayout.addComponent(phoneLayout);

			HorizontalLayout locationLayout = new HorizontalLayout();
			locationLayout.setSpacing(true);
			CatalogUtil.loadSelectProvince(this.province);

			this.province.addValueChangeListener(new Property.ValueChangeListener() {

				/** Serial UID */
				private static final long serialVersionUID = 3381918076694623852L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {

					try{

						ProvinceDTO province = (ProvinceDTO)event.getProperty().getValue();

						if (province != null)
							CatalogUtil.loadSelectCities(city, province.getCountryId(), province.getId());
						else
							city.removeAllItems();

					}catch (Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}
			});

			locationLayout.addComponent(this.province);
			locationLayout.addComponent(this.city);
			locationLayout.addComponent(this.address);
			this.mainLayout.addComponent(locationLayout);

			this.genre.addStyleName("horizontal");
			CatalogUtil.loadSelectGenre(this.genre);
			this.mainLayout.addComponent(this.genre);
			this.mainLayout.addComponent(this.birthday);
			this.email.setWidth("350px");
			this.mainLayout.addComponent(this.email);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * @return the comments
	 */
	public TextArea getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(TextArea comments) {
		this.comments = comments;
	}

	/**
	 * @return the status
	 */
	public ComboBox getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ComboBox status) {
		this.status = status;
	}

	/**
	 * @return the subStatus
	 */
	public ComboBox getSubStatus() {
		return subStatus;
	}

	/**
	 * @param subStatus the subStatus to set
	 */
	public void setSubStatus(ComboBox subStatus) {
		this.subStatus = subStatus;
	}
	
	public void clearForm (){
		this.status.setValue("");
		this.subStatus.setValue("");
		this.comments.setValue("");
		this.documentType.setValue("");;
		this.documentNumber.setValue("");
		this.firstName.setValue("");
		this.lastName1.setValue("");
		this.lastName2.setValue("");
		this.indicative.setValue("");
		this.telephone.setValue("");
		this.cellPhone.setValue("");
		this.genre.setValue("");
		this.email.setValue("");
		this.address.setValue("");
		this.city.setValue("");
		this.province.setValue("");
		this.birthday.setValue(null);
	}
}