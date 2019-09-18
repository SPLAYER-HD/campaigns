/**
 * 
 */
package com.fi.crm.campaigns.web.forms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.web.util.CatalogUtil;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 28/2015
 */
public abstract class CampaignForm extends BaseFormGrid{

	/** Serial UID */
	private static final long serialVersionUID = 5943171229108527176L;

	/* Attributes */
	private VerticalLayout globalLayout;
	private TextField name;
	private TextArea description;
	private ComboBox author;
	private TextField nameDomain;
	private TextField domain;
	private Label defaultMail;
	private DateField startDate;
	private DateField endDate;
	private DateField eventStartDate;
	private DateField eventEndDate;
	private OptionGroup contactType;
	private CheckBox automaticCalling;
	protected TextArea message;
	private RichTextArea htmlMessage;
	private TextField mailSubject;
	protected ComboBox businessUnit;
	private ComboBox store;
	private HorizontalLayout sendMailLayout;
	private HorizontalLayout sendSMSLayout;
	private HorizontalLayout mailFromLayout;
	protected TextField testMail;
	protected TextField testSMS;
	private Button sendMail;
	private Button sendSMS;
	private Label toolLabel;
	
	private ComboBox brand;
	private ComboBox article;
	
	private Label smsLength;
	
	private int maxSmsLength=159;
	private Window toolLabelWindow;

	/* Constants */
	private static final Logger LOGGER = LoggerFactory.getLogger(CampaignForm.class);

	/** Default constructor */
	public CampaignForm(String defaultMailDomain){

		super();
		setImmediate(true);
		this.globalLayout = new VerticalLayout();
		this.globalLayout.setSpacing(true);
		this.globalLayout.setMargin(true);
		this.globalLayout.setSizeFull();
		setWidth("70%");
		addStyleName("campaigns_pop-up");
		addComponent(this.globalLayout);
		this.init(defaultMailDomain);
	}

	/** Init method */
	@SuppressWarnings("deprecation")
	private void init(String defaultMailDomain){

		try{

			this.name = new TextField();
			this.name.setWidth("350px");
			this.description = new TextArea();
			this.description.setColumns(30);
			this.description.setRows(15);
			this.author = new ComboBox();
			this.author.setWidth("350px");
			this.domain = new TextField();
			this.domain.setWidth("350px");
			this.domain.addValidator(new EmailValidator(Messages.getString("Validator.email.format")));
			this.nameDomain = new TextField();
			this.nameDomain.setWidth("350px");
			this.defaultMail = new Label();
			this.defaultMail.setValue(defaultMailDomain);
			this.startDate = new DateField();
			this.startDate.setDateFormat("dd/MM/yyyy HH:mm");
			this.startDate.setResolution(Resolution.MINUTE);
			this.endDate = new DateField();
			this.endDate.setDateFormat("dd/MM/yyyy HH:mm");
			this.endDate.setResolution(Resolution.MINUTE);
			this.eventStartDate = new DateField();
			this.eventStartDate.setDateFormat("dd/MM/yyyy HH:mm");
			this.eventStartDate.setResolution(Resolution.MINUTE);
			this.eventEndDate = new DateField();
			this.eventEndDate.setDateFormat("dd/MM/yyyy HH:mm");
			this.eventEndDate.setResolution(Resolution.MINUTE);
			this.contactType = new OptionGroup();
			this.automaticCalling = new CheckBox();
			this.message = new TextArea();
			this.message.setTextChangeEventMode(TextChangeEventMode.EAGER);
			this.message.setWidth("800px");
			this.message.setHeight("100px");
			this.mailSubject = new TextField();
			this.businessUnit = new ComboBox();
			this.store = new ComboBox();
			this.store.setWidth("350px");
			this.brand = new ComboBox();
			this.brand.setWidth("350px");
			this.article = new ComboBox();
			this.article.setWidth("350px");
			this.htmlMessage = new RichTextArea();
			this.htmlMessage.setWidth("800px");
			this.htmlMessage.setHeight("600px");
			this.testMail = new TextField();
			this.testMail.setWidth("350px");
			this.testMail.addValidator(new EmailValidator(Messages.getString("Validator.email.format")));
			this.sendMail = new Button();
			this.sendMail.setCaption(Messages.getString("General.send.mail"));
			
			this.testSMS = new TextField();
			this.testSMS.setWidth("350px");
			this.sendSMS = new Button();
			this.sendSMS.setCaption(Messages.getString("General.send.sms"));
			
			this.toolLabel = new Label();
			this.toolLabel.setContentMode(ContentMode.HTML);
			this.toolLabel.setValue(Messages.getString("General.html.caption"));
			
			this.mailSubject.setVisible(false);
			this.htmlMessage.setVisible(false);
			
			this.smsLength = new Label();
			this.smsLength.setWidth("200px");
			this.smsLength.setVisible(false);
			
			/***/
			CommonUtil.setAttributes(this.name, Messages.getString("CampaignForm.name"), 80, true, false);
			CommonUtil.setAttributes(this.description, Messages.getString("CampaignForm.description"), 500, true, false);
			CommonUtil.setAttributes(this.author, Messages.getString("CampaignForm.author"), true, true);
			CommonUtil.setAttributes(this.domain, Messages.getString("CampaignForm.domain"), 50, true, false);
			CommonUtil.setAttributes(this.nameDomain, Messages.getString("CampaignForm.nameDomain"), 50, true, false);
			CommonUtil.setAttributes(this.startDate, Messages.getString("CampaignForm.startDate"), true, true);
			CommonUtil.setAttributes(this.endDate, Messages.getString("CampaignForm.endDate"), true, true);
			CommonUtil.setAttributes(this.eventStartDate, Messages.getString("CampaignForm.eventStartDate"), true, true);
			CommonUtil.setAttributes(this.eventEndDate, Messages.getString("CampaignForm.eventEndDate"), true, true);
			CommonUtil.setAttributes(this.contactType, Messages.getString("CampaignForm.contactType"), true, true);
			CommonUtil.setAttributes(this.automaticCalling, Messages.getString("CampaignForm.automaticCalling"), false, false);
			CommonUtil.setAttributes(this.message, Messages.getString("CampaignForm.message"), 50000, true, true);
			CommonUtil.setAttributes(this.htmlMessage, Messages.getString("CampaignForm.message"), true, true);
			CommonUtil.setAttributes(this.mailSubject, Messages.getString("CampaignForm.mailSubject"), 100, true, false);
			CommonUtil.setAttributes(this.businessUnit, Messages.getString("CampaignForm.businessUnit"), false, false);
			CommonUtil.setAttributes(this.store, Messages.getString("CampaignForm.store"), false, false);
			CommonUtil.setAttributes(this.brand, Messages.getString("CampaignForm.brands"), false, false);
			CommonUtil.setAttributes(this.article, Messages.getString("CampaignForm.articles"), false, false);
			CommonUtil.setAttributes(this.testMail, Messages.getString("CampaignForm.testMail"), 50, false, false);
			CommonUtil.setAttributes(this.testSMS, Messages.getString("CampaignForm.testSMS"), 10, false, false);
			CommonUtil.setAttributes(this.defaultMail, Messages.getString("CampaignForm.defaultMail"));

			/** Customs validators */
			CommonUtil.setDateRangeValidator(this.startDate, this.endDate);
			CommonUtil.setDateRangeValidator(this.eventStartDate, this.eventEndDate);

			/***/
			this.contactType.addValueChangeListener(new ValueChangeListener() {

				/** Serial UID */
				private static final long serialVersionUID = 1691849130927390918L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {
					smsLength.setVisible(false);
					testMail.setRequired(false);
					testSMS.setRequired(false);
					message.setWidth("800px");
					if (contactType.getValue() != null){
						CampaignContactTypeEnum campaignContactTypeEnum = (CampaignContactTypeEnum)contactType.getValue();

						switch (campaignContactTypeEnum) {
						case EMAIL:
							htmlMessage.setVisible(true);
							htmlMessage.setRequired(true);
							mailSubject.setVisible(true);
							mailSubject.setRequired(true);
							sendMailLayout.setVisible(true);
							testMail.setVisible(true);
							sendSMSLayout.setVisible(false);
							defaultMail.setVisible(true);
							domain.setRequired(true);
							domain.setVisible(true);
							message.setHeight("600px");
							break;
						case SMS:
							htmlMessage.setVisible(false);
							htmlMessage.setRequired(false);
							mailSubject.setVisible(false);
							mailSubject.setRequired(false);
							sendMailLayout.setVisible(false);
							sendSMSLayout.setVisible(true);
							testSMS.setVisible(true);
							defaultMail.setVisible(false);
							domain.setRequired(false);
							domain.setVisible(false);
							nameDomain.setRequired(false);
							nameDomain.setVisible(false);
							message.setMaxLength(maxSmsLength);
							smsLength.setVisible(true);							
							message.setHeight("100px");
							break;
						case PHONE:
							htmlMessage.setVisible(false);
							htmlMessage.setRequired(false);
							mailSubject.setVisible(false);
							mailSubject.setRequired(false);
							sendMailLayout.setVisible(false);
							sendSMSLayout.setVisible(false);
							defaultMail.setVisible(false);
							domain.setRequired(false);
							domain.setVisible(false);
							nameDomain.setRequired(false);
							nameDomain.setVisible(false);
							message.setHeight("300px");
							break;
						default:
							break;
						}
					}
				}
			});

			/***/
			this.message.addValueChangeListener(new ValueChangeListener() {

				/** Serial UID */
				private static final long serialVersionUID = -1447550064379954954L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {
					if (contactType != null && contactType.getValue() != null) {
						CampaignContactTypeEnum campaignContactTypeEnum = (CampaignContactTypeEnum)contactType.getValue();
						if (campaignContactTypeEnum.equals(CampaignContactTypeEnum.EMAIL)) {
							if (message.getValue() != null)
								htmlMessage.setValue(message.getValue());
						}else {
							if (campaignContactTypeEnum.equals(CampaignContactTypeEnum.SMS)) {
								if (message.getValue() != null){
									//amount of characters used
									String mess = "Usados "+String.valueOf(message.getValue().length())+ " de "+String.valueOf(maxSmsLength);
									smsLength.setValue(mess);
								}
							}
						}
					}
				}
			});
			
			this.message.addTextChangeListener(new TextChangeListener() {
				private static final long serialVersionUID = -8978525765720194786L;

				@Override
				public void textChange(TextChangeEvent event) {
					if (contactType != null && contactType.getValue() != null) {
						CampaignContactTypeEnum campaignContactTypeEnum = (CampaignContactTypeEnum)contactType.getValue();
						if (campaignContactTypeEnum.equals(CampaignContactTypeEnum.SMS)) {
							if (message.getValue() != null){
								//amount of characters used
								String mess = "Usados "+String.valueOf(message.getValue().length())+ " de "+String.valueOf(maxSmsLength);
								smsLength.setValue(mess);
							}
						}
					}
				}
			});
			
			/***/
			this.htmlMessage.addValueChangeListener(new ValueChangeListener(){

				/** Serial UID */
				private static final long serialVersionUID = -3403935231022720906L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {
					if (htmlMessage.getValue() != null)
						message.setValue(htmlMessage.getValue());
				}
			});

			/***/
			this.author.addValueChangeListener(new ValueChangeListener() {

				/** Serial UID */
				private static final long serialVersionUID = -1447550064379954954L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
				 */
				@Override
				public void valueChange(ValueChangeEvent event) {

					AuthorDTO author = (AuthorDTO)event.getProperty().getValue();

					if (author != null)
						domain.setValue(author.getEmail());
				}
			});

			/***/
			this.sendMail.addClickListener(new ClickListener() {

				/** Serial UID */
				private static final long serialVersionUID = 8225356899800096141L;

				/*
				 * (non-Javadoc)
				 * @see com.vaadin.ui.Button.ClickListener#buttonClick(com.vaadin.ui.Button.ClickEvent)
				 */
				@Override
				public void buttonClick(ClickEvent event) {
					SendTestMail();
				}
			});

			this.sendSMS.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 7328657059558450933L;

				@Override
				public void buttonClick(ClickEvent event) {
					sendTestSMS();
				}
			});
			
			this.store.addFocusListener(new FocusListener() {
				
				private static final long serialVersionUID = 8225356899800096141L;

				@Override
				public void focus(FocusEvent event) {
					String businessUnitId = null;
					if (businessUnit.getValue() != null){
						businessUnitId = ((BusinessUnitDTO)businessUnit.getValue()).getCode();
					}
					loadStoresByBusinessUnit(businessUnitId);
				}
			});
			
			this.businessUnit.addFocusListener(new FocusListener() {
				private static final long serialVersionUID = -7072322960819039134L;

				@Override
				public void focus(FocusEvent event) {
					CatalogUtil.loadSelectBusinessUnit(businessUnit);
				}
			});
			
			this.brand.addFocusListener(new FocusListener() {
				
				private static final long serialVersionUID = -3216703156714561201L;

				@Override
				public void focus(FocusEvent event) {
					CatalogUtil.loadSelectBrands(brand);
				}
			});
			this.article.addFocusListener(new FocusListener() {
				private static final long serialVersionUID = 8225356899800096141L;

				@Override
				public void focus(FocusEvent event) {
					String brandId = null;
					if (brand.getValue() != null){
						brandId = ((MarcasDTO)brand.getValue()).getCodigo();
					}

					loadArticlesByBrand(brandId, brandId != null);
				}
			});

			this.author.addFocusListener(new FieldEvents.FocusListener() {
				private static final long serialVersionUID = 8225356899800096141L;

				@Override
				public void focus(FocusEvent event) {
					String status = Boolean.TRUE.toString();
					loadAuthorsByStatus(status);
				}
			});

			this.globalLayout.setStyleName("iel-form");
			this.globalLayout.addComponent(this.name);
			this.globalLayout.addComponent(this.description);
			CatalogUtil.loadSelectContactType(this.contactType);
			this.globalLayout.addComponent(this.contactType);

			Panel campaignDatePanel = new Panel();
			HorizontalLayout campaignDateLayout = new HorizontalLayout();
			campaignDateLayout.setSpacing(true);
			campaignDateLayout.setWidth("100%");
			campaignDateLayout.addComponent(this.startDate);
			campaignDateLayout.addComponent(this.endDate);
			campaignDatePanel.setContent(campaignDateLayout);
			campaignDatePanel.setWidth("600px");
			this.globalLayout.addComponent(campaignDatePanel);

			Panel eventDatePanel = new Panel();
			HorizontalLayout eventDateLayout = new HorizontalLayout();
			eventDateLayout.setSpacing(true);
			eventDateLayout.setWidth("100%");
			eventDateLayout.addComponent(this.eventStartDate);
			eventDateLayout.addComponent(this.eventEndDate);
			eventDatePanel.setContent(eventDateLayout);
			eventDatePanel.setWidth("600px");
			this.globalLayout.addComponent(eventDatePanel);

			this.mailFromLayout = new HorizontalLayout();
			this.mailFromLayout.setSpacing(true);
			this.mailFromLayout.addComponent(this.author);
			this.mailFromLayout.addComponent(this.defaultMail);

			this.globalLayout.addComponent(this.mailFromLayout);
			
			this.mailFromLayout = new HorizontalLayout();
			this.mailFromLayout.setSpacing(true);
			this.mailFromLayout.addComponent(this.nameDomain);
			this.mailFromLayout.addComponent(this.domain);
			this.globalLayout.addComponent(this.mailFromLayout);

			this.globalLayout.addComponent(this.mailSubject);

			VerticalLayout messageLayout = new VerticalLayout();
			messageLayout.setSizeFull();
			messageLayout.addComponent(this.htmlMessage);
			messageLayout.addComponent(this.message);
			messageLayout.addListener(new LayoutClickListener() {
				
				private static final long serialVersionUID = 7623474738857517067L;

				public void layoutClick(LayoutClickEvent event) {
					if (event.getButton().equals(MouseButton.RIGHT)){
						UI.getCurrent().addWindow(toolLabelWindow);						
					}
				}
			});

			this.globalLayout.addComponent(messageLayout);
			
			this.smsLength.setWidth("100%");
			globalLayout.addComponent(this.smsLength);
			
			toolLabelWindow = new Window("Ayudas para Construir Mensaje");
			toolLabelWindow.setModal(true);
			VerticalLayout toolLabelWindowLayout = new VerticalLayout();
			toolLabelWindowLayout.setMargin(true);
			toolLabelWindowLayout.setSpacing(true);
			toolLabelWindowLayout.addComponent(this.toolLabel);
			toolLabelWindow.setContent(toolLabelWindowLayout);

			this.sendMailLayout = new HorizontalLayout();
			FormLayout formLayout = new FormLayout();
			formLayout.addComponent(this.testMail);
			this.sendMailLayout.addComponent(formLayout);
			this.sendMailLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
			this.sendMailLayout.addComponent(this.sendMail);
			this.sendMailLayout.setComponentAlignment(this.sendMail, Alignment.MIDDLE_CENTER);
			this.sendMailLayout.setVisible(false);
			this.globalLayout.addComponent(this.sendMailLayout);

			this.sendSMSLayout = new HorizontalLayout();
			FormLayout formLayoutSMS = new FormLayout();
			formLayoutSMS.addComponent(this.testSMS);
			this.sendSMSLayout.addComponent(formLayoutSMS);
			this.sendSMSLayout.setComponentAlignment(formLayoutSMS, Alignment.MIDDLE_CENTER);
			this.sendSMSLayout.addComponent(this.sendSMS);
			this.sendSMSLayout.setComponentAlignment(this.sendSMS, Alignment.MIDDLE_CENTER);
			this.sendSMSLayout.setVisible(false);
			this.globalLayout.addComponent(this.sendSMSLayout);
			
			Panel businessUnitStorePanel = new Panel();
			HorizontalLayout businessUnitStorePanelLayout = new HorizontalLayout();
			businessUnitStorePanelLayout.setSpacing(true);
			businessUnitStorePanelLayout.setWidth("100%");
			businessUnitStorePanelLayout.addComponent(this.businessUnit);
			businessUnitStorePanelLayout.addComponent(this.store);
			businessUnitStorePanel.setContent(businessUnitStorePanelLayout);
			businessUnitStorePanel.setWidth("800px");
			this.globalLayout.addComponent(businessUnitStorePanel);
			
    		Panel brandsArticlesPanel = new Panel();
			HorizontalLayout brandsArticlesPanelLayout = new HorizontalLayout();
			brandsArticlesPanelLayout.setSpacing(true);
			brandsArticlesPanelLayout.setWidth("100%");
			brandsArticlesPanelLayout.addComponent(this.brand);
			brandsArticlesPanelLayout.addComponent(this.article);
			brandsArticlesPanel.setContent(brandsArticlesPanelLayout);
			brandsArticlesPanel.setWidth("800px");
			this.globalLayout.addComponent(brandsArticlesPanel);
		
			this.globalLayout.addComponent(this.automaticCalling);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/** Abstract method to improve the test mail sender */
	public abstract void SendTestMail();
	public abstract void sendTestSMS();
	
	public void loadStoresByBusinessUnit (String businessUnit) {
		CatalogUtil.loadSelectStore(this.store, businessUnit);		
	}

	public void loadArticlesByBrand (String brandId, boolean loadWhenNullBrandId) {
		CatalogUtil.loadSelectArticleByBrand(this.article, brandId, loadWhenNullBrandId);		
	}

	public void loadAuthorsByStatus (String status) {
		CatalogUtil.loadSelectAuthorsByStatus(this.author, status);		
	}
	
	public void loadBusinessUnitById (String codigo, String noCia){
		CatalogUtil.loadSelectBusinessUnitById(this.businessUnit, codigo, noCia);
	}
	
	public void loadAuthorsById (Integer authorId){
		CatalogUtil.loadSelectAuthorsById(this.author, authorId);
	}
	
	public void loadBrandById (String noCia, String codigo){
		CatalogUtil.loadSelectBrandById(brand, noCia, codigo);
	}
	
	public void loadStoreById (String noCia, String businessUnitId, String codigo){
		CatalogUtil.loadSelectStoreById(store, noCia, businessUnitId, codigo);
	}
	
	public void loadArticleById (String noCia, String brandId, String articleId) {
		CatalogUtil.loadSelectArticleById(article, noCia, brandId, articleId);
	}

	public TextField getNameDomain() {
		return nameDomain;
	}

}