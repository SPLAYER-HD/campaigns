package com.fi.crm.campaigns.web.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.addon.tableexport.DefaultTableHolder;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Slider;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

public class CommonUtil {

	private CommonUtil(){
		//Clase utilitaria se esconde el constructor
	}	

	/**
	 * static logger for class CommonUtil
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * Method that returns user terminal address
	 * 
	 * @return
	 */
	public static String getTerminal() {
		return UI.getCurrent().getPage().getWebBrowser().getAddress();
	}

	/**
	 * Method that returns default locale based on user web browser
	 * configuration or default application locale
	 * 
	 * @return
	 */
	public static Locale getDefaultLocale() {
		Locale locale = UI.getCurrent().getPage().getWebBrowser().getLocale();
		if (locale == null) {
			locale = Locale.getDefault();
		}

		if (locale == null) {
			locale = Locale.US;
		}

		return locale;
	}

	/**
	 * Method that returns the fields validation result
	 * 
	 * @param layout
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean validate(HasComponents layout) {
		boolean isValid = true;
		Iterator<Component> i = layout.iterator();
		while (i.hasNext() && isValid) {
			if(!isValid){
				return isValid;
			}
			Component c = i.next();
			LOGGER.debug("getCaption ==== "+(c.getCaption()));
			LOGGER.debug("c ==== "+c);
			if(c instanceof HasComponents){
				isValid = validate((HasComponents)c);
			}else{

				if (c instanceof AbstractField) {
					try {
						LOGGER.debug("instanceof AbstractField ==== "+(c.getCaption()));
						((AbstractField) c).validate();
					} catch (Exception e) {
						LOGGER.debug("Component null ==== "+((AbstractField) c).getCaption());
						LOGGER.debug("Error" ,e);
						isValid = false;
						((AbstractField)c).setValidationVisible(true);
						return false;
					}
				}
			}
		}
		LOGGER.debug("result ==== "+ isValid);
		return isValid;
	}


	/**
	 * Method that get value of field in a safe null mode
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getSafeValue(AbstractField af) {
		if (af.getValue() == null) {
			return null;
		}

		if (af.getValue() instanceof String) {
			return af.getValue().toString();
		}
		return null;
	}

	/**
	 * Method that get value of date field in a safe null mode
	 */
	public static Date getSafeDateValue(DateField dateField) {
		if (dateField.getValue() == null) {
			return null;
		}
		if (dateField.getValue() instanceof Date) {
			return dateField.getValue();
		}
		return null;
	}

	/**
	 * Method that allow to get the value of field as float
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Long getLongValue(AbstractField af) {
		if (af.getValue() == null) {
			return null;
		}

		if (af.getValue() instanceof String) {
			try {
				return Long.parseLong(af.getValue().toString());
			} catch (NumberFormatException e) {
				StringToLongConverter stic = new StringToLongConverter();
				return stic.convertToModel(af.getValue().toString(), Long.class, UI.getCurrent().getLocale());
			}
		}
		return null;
	}

	/**
	 * Method that allow to get the value of field as double
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static double getDoubleValue(AbstractField af) {
		if (af.getValue() == null) {
			return 0L;
		}

		if (af.getValue() instanceof String) {
			try {
				return Double.parseDouble(af.getValue().toString());
			} catch (NumberFormatException e) {
				StringToDoubleConverter stdc = new StringToDoubleConverter();
				return stdc.convertToModel(af.getValue().toString(), Double.class, UI.getCurrent().getLocale());
			}
		}
		return 0L;
	}

	/**
	 * Method that allow to get the value formated of double
	 * 
	 * @param af
	 * @return
	 */
	public static String getFormatDoubleValue(BigDecimal value) {
		if (value == null) {
			return "";
		}	
		try {
			return NumberFormat.getCurrencyInstance().format(value);
		} catch (NumberFormatException e) {
			return "";
		}

	}

	/**
	 * Method that allow to get the value of field as double
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Double getDoubleObjectValue(AbstractField af) {
		if (af.getValue() == null || af.getValue().equals("")) {
			return null;
		}

		if (af.getValue() instanceof String) {
			try {
				return Double.parseDouble(af.getValue().toString());
			} catch (NumberFormatException e) {
				StringToDoubleConverter stdc = new StringToDoubleConverter();
				return stdc.convertToModel(af.getValue().toString(), Double.class, UI.getCurrent().getLocale());
			}
		}
		return null;
	}

	/**
	 * Method that allow to get the value of field as double
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static BigDecimal getBigDecimalValue(AbstractField af) {
		if (af.getValue() == null || af.getValue().equals("")) {
			return null;
		}

		if (af.getValue() instanceof String) {
			try {
				return BigDecimal.valueOf(Double.parseDouble(af.getValue().toString()));
			} catch (NumberFormatException e) {
				StringToDoubleConverter stdc = new StringToDoubleConverter();
				return new BigDecimal(stdc.convertToModel(af.getValue().toString(), Double.class, UI.getCurrent().getLocale()));
			}
		}
		return null;
	}

	/**
	 * Method that allow to get the value of field as integer
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int getIntValue(AbstractField af) {
		if (af.getValue() == null ) {
			return 0;
		}

		if (af.getValue() instanceof String) {
			try {
				return Integer.parseInt(af.getValue().toString());
			} catch (NumberFormatException e) {
				StringToIntegerConverter stic = new StringToIntegerConverter();
				return stic.convertToModel(af.getValue().toString(), Integer.class, UI.getCurrent().getLocale());
			}
		}
		return 0;
	}

	/**
	 * Method that allow to get the value of field as integer
	 * 
	 * @param af
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Integer getIntegerValue(AbstractField af) {
		if (af.getValue() == null || af.getValue().equals("")) {
			return null;
		}

		if (af.getValue() instanceof String) {
			try {
				return Integer.parseInt(af.getValue().toString());
			} catch (NumberFormatException e) {
				StringToIntegerConverter stic = new StringToIntegerConverter();
				return stic.convertToModel(af.getValue().toString(), Integer.class, UI.getCurrent().getLocale());
			}
		}
		return null;
	}

	/**
	 * Method that set all attributes associated to text fields
	 * 
	 * @param field
	 * @param name
	 * @param length
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(AbstractTextField field, String name,
			Integer length, Boolean required, Boolean immediate) {
		field.setCaption(name);
		field.setRequired(required);
		field.setRequiredError(String.format(Messages.getString(
				"Validator.required.error"), field.getCaption()));
		field.setMaxLength(length);
		field.setValidationVisible(false);
		field.setImmediate(immediate);
		field.setNullRepresentation("");
	}

	/**
	 * Method that set all attributes associated to select fields
	 * 
	 * @param field
	 * @param name
	 * @param length
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(AbstractSelect field, String name,
			boolean required, boolean immediate) {
		field.setCaption(name);
		field.setRequired(required);
		field.setRequiredError(String.format(Messages.getString(
				"Validator.requiredSelect.error"), field.getCaption()));
		field.setValidationVisible(false);
		field.setImmediate(immediate);
	}

	/**
	 * Method that set all attributes associated to select fields
	 * 
	 * @param field
	 * @param name
	 * @param length
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(RichTextArea field, String name,
			boolean required, boolean immediate) {
		field.setCaption(name);
		field.setRequired(required);
		field.setRequiredError(String.format(Messages.getString("Validator.requiredSelect.error"), field.getCaption()));
		field.setValidationVisible(false);
		field.setImmediate(immediate);
	}

	/**
	 * Method that set all attributes associated to select fields
	 * 
	 * @param field
	 * @param name
	 * @param length
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(AbstractField<Double> field, String name,
			boolean required, boolean immediate) {
		field.setCaption(name);
		field.setRequired(required);
		field.setRequiredError(String.format(Messages.getString("Validator.requiredSelect.error"), field.getCaption()));
		field.setValidationVisible(false);
		field.setImmediate(immediate);
	}

	/**
	 * 
	 * @param field
	 * @param name
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(CheckBox field, String name, boolean required, boolean immediate) {
		field.setRequired(required);
		field.setCaption(name);
		field.setRequiredError(String.format(Messages.getString("Validator.required.error"), field.getCaption()));
		field.setValidationVisible(false);
		field.setImmediate(immediate);
	}

	/**
	 * Method that sets all attributes associated to date fields
	 * 
	 * @param field
	 * @param name
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(DateField field, String name,
			boolean required, boolean immediate) {
		field.setRequired(required);
		field.setCaption(name);
		field.setRequiredError(String.format(Messages.getString(
				"Validator.required.error"),
				field.getCaption()));
		field.setValidationVisible(false);
		field.setImmediate(immediate);
		field.setLocale(UI.getCurrent().getLocale());
	}

	/**
	 * Method that sets all attributes associated to date fields
	 * 
	 * @param field
	 * @param name
	 * @param required
	 * @param immediate
	 */
	public static void setAttributes(AbstractComponent field, String name) {
		field.setCaption(name);
		field.setLocale(UI.getCurrent().getLocale());
	}

	/**
	 * 
	 * @param field
	 * @param minValue
	 * @param maxValue
	 */
	public static void addNumericValidator(TextField field, Double minValue, Double maxValue) {
		field.setLocale(UI.getCurrent().getLocale());
		field.setConverter(new StringToDoubleConverter());
		String message = String.format(Messages.getString("Validator.number.error"));
		NumberFormat nf = NumberFormat.getNumberInstance(UI.getCurrent().getLocale());
		if ( minValue != null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.range"), nf.format(minValue), nf.format(maxValue));
		} else if ( minValue != null && maxValue == null ) {
			message = String.format(Messages.getString("Validator.number.lowerLimit"), nf.format(minValue));
		} else if ( minValue == null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.higherLimit"), nf.format(maxValue));
		}

		Validator numbersValidator = new DoubleRangeValidator(
				message, minValue, maxValue);
		field.addValidator(numbersValidator);
	}

	/**
	 * 
	 * @param field
	 * @param minValue
	 * @param maxValue
	 */
	public static void addIntegerValidator(TextField field, Integer minValue, Integer maxValue) {
		field.setConverter(new StringToIntegerConverter());
		String message = String.format(Messages.getString("Validator.number.error"));
		NumberFormat nf = NumberFormat.getNumberInstance(UI.getCurrent().getLocale());
		if ( minValue != null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.range"), nf.format(minValue), nf.format(maxValue));
		} else if ( minValue != null && maxValue == null ) {
			message = String.format(Messages.getString("Validator.number.lowerLimit"), nf.format(minValue));
		} else if ( minValue == null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.higherLimit"), nf.format(maxValue));
		}
		field.setConversionError(Messages.getString("General.StringToIntegerConverter"));
		Validator numbersValidator = new IntegerRangeValidator(
				message, minValue, maxValue);
		field.addValidator(numbersValidator);
	}

	/**
	 * 
	 * @param field
	 * @param minValue
	 * @param maxValue
	 */
	public static void addLongValidator(TextField field) {
		field.setConverter(new StringToLongConverter());
		field.setConversionError(Messages.getString("General.StringToIntegerConverter"));
	}

	/**
	 * 
	 * @param field
	 * @param minValue
	 * @param maxValue
	 * @param resolution
	 */
	public static void addDateValidator(DateField field, Date minValue, Date maxValue, Resolution resolution) {
		String message = String.format(Messages.getString("Validator.date.error"));
		DateFormat nf = DateFormat.getDateInstance();
		if ( minValue != null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.date.range"), nf.format(minValue), nf.format(maxValue));
		} else if ( minValue != null && maxValue == null ) {
			message = String.format(Messages.getString("Validator.date.lowerLimit"), nf.format(minValue));
		} else if ( minValue == null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.date.higherLimit"), nf.format(maxValue));
		}

		Validator dateValidator = new DateRangeValidator(
				message, minValue, maxValue, resolution);
		field.addValidator(dateValidator);
	}

	/**
	 * 
	 * @param field
	 * @param minLength
	 * @param maxLength
	 */
	public static void addLengthValidator(AbstractTextField field, Integer minLength, Integer maxLength) {

		String message = String.format(Messages.getString("Validator.length.error"), minLength, maxLength);
		if ( minLength != null && maxLength != null ) {
			message = String.format(Messages.getString("Validator.length.error"), minLength, maxLength);
		} else if ( minLength != null && maxLength == null ) {
			message = String.format(Messages.getString("Validator.length.lowerLimit"), minLength);
		} else if ( minLength == null && maxLength != null ) {
			message = String.format(Messages.getString("Validator.length.higherLimit"), maxLength);
		}

		Validator lengthValidator = new StringLengthValidator(
				message, minLength, maxLength, true);
		field.addValidator(lengthValidator);
	}
	/**
	 * 
	 * @param field
	 */
	public static void addEmailValidator(AbstractTextField field) {
		EmailValidator emailValidator = new EmailValidator(Messages.getString("General.invalid.mail"));
		field.addValidator(emailValidator);
	}

	/**
	 * 
	 * @param select
	 * @param options
	 * @param value 
	 */
	@SuppressWarnings("unchecked")
	public static void addOptions(AbstractSelect select, Object[] options, Object[] value) {
		if(options == null) {
			return;
		}

		select.addContainerProperty("id", String.class, null);
		select.addContainerProperty("text", String.class, null);

		for ( int i = 0; i < options.length; i++ ) {
			String val = (String)options[i];
			Item item = select.addItem(val);
			item.getItemProperty("id").setValue(val);
			item.getItemProperty("text").setValue(val);
		}
		select.setItemCaptionMode(
				ItemCaptionMode.PROPERTY);
		select.setItemCaptionPropertyId("text");

		if ( value != null && value.length == 1 ) {
			select.setValue((String)value[0]);
		} else {
			select.setValue(value);        	
		}
	}

	/**
	 * 
	 * @param select
	 * @param options
	 * @param value 
	 */
	@SuppressWarnings("unchecked")
	public static void addBooleanOptions(AbstractSelect select, String[] options, String[] value) {
		if(options == null) {
			return;
		}

		select.addContainerProperty("id", String.class, null);
		select.addContainerProperty("text", String.class, null);

		for ( int i = 0; i < options.length; i++ ) {
			String val = options[i];
			Item item = select.addItem(val);
			item.getItemProperty("id").setValue(val);
			item.getItemProperty("text").setValue(Messages.getString( "General." + val ));
		}
		select.setItemCaptionMode(
				ItemCaptionMode.PROPERTY);
		select.setItemCaptionPropertyId("text");

		if ( value != null && value.length == 1 ) {
			select.setValue(value[0]);
		} else {
			select.setValue(value);        	
		}
	}

	/**
	 * 
	 * @param field
	 * @param expression
	 */
	public static void addRegularExpressionValidator(TextField field,
			String expression) {
		if ( expression != null && !expression.equals("") ) {
			Validator regexpValidator = new RegexpValidator(expression, 
					Messages.getString("Validator.regexp.error"));
			field.addValidator(regexpValidator);
		}
	}

	public static void collapseTree(Tree regionTree, Collection<?> rootItemIds) {
		if ( rootItemIds == null ) {
			return;
		}

		for ( Object rootItemId : rootItemIds ) {
			regionTree.collapseItemsRecursively(rootItemId);
		}
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void clearForm(AbstractLayout layout) {
		Iterator<Component> it = layout.iterator();
		while (it.hasNext()) {
			Component c = it.next();
			if(c instanceof AbstractField) {
				if(c instanceof Slider){
					((AbstractField) c).setValue(new Double(0.0));
				} else{
					((AbstractField) c).setValue(null);	
				}
			} else if(c instanceof AbstractLayout){
				clearForm((AbstractLayout)c);
			} else if (c instanceof Panel){ 
				clearForm((Panel) c);
			}
		}
	}

	/**
	 * Method that clean components in panel
	 * 
	 * @param layout
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean clearForm(Panel layout) {
		boolean result = true;
		Iterator<Component> i = layout.iterator();
		LOGGER.debug("Component Panel Iterator==== "+(i.toString()));
		while (i.hasNext()) {
			Component c = i.next();
			LOGGER.debug("Component Panel ==== "+(c.getCaption()));
			if (c instanceof AbstractField) {
				((AbstractField) c).setValue(null);	
			} else if (c instanceof AbstractComponentContainer) {
				clearForm((AbstractLayout)c);
			} else if (c instanceof Panel) {
				clearForm((Panel) c);
			}
		}
		return result;
	}

	public static void addDoubleValidator(TextField field, Double minValue, Double maxValue) {
		field.setConverter(new StringToDoubleConverter());
		String message = String.format(Messages.getString("Validator.number.error"));
		NumberFormat nf = NumberFormat.getNumberInstance(UI.getCurrent().getLocale());
		if ( minValue != null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.range"), nf.format(minValue), nf.format(maxValue));
		} else if ( minValue != null && maxValue == null ) {
			message = String.format(Messages.getString("Validator.number.lowerLimit"), nf.format(minValue));
		} else if ( minValue == null && maxValue != null ) {
			message = String.format(Messages.getString("Validator.number.higherLimit"), nf.format(maxValue));
		}

		Validator numbersValidator = new DoubleRangeValidator(
				message, minValue, maxValue);
		field.addValidator(numbersValidator);
	}

	public static void setAttributes(Button button, String description, String iconURL, String syleName, Object data, boolean inmediate){
		button.setDescription(description);
		if(iconURL != null){
			button.setIcon(new ThemeResource("images/"+iconURL));
		}
		button.setStyleName(syleName);
		button.setData(data);
		button.setImmediate(inmediate);	
		button.setWidth("-1px");
		button.setHeight("-1px");
	}

	public static double roundDouble(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	@SuppressWarnings("unchecked")
	public static IndexedContainer copyContainer(Container source) {
		IndexedContainer cont = new IndexedContainer();

		for (Object prop : source.getContainerPropertyIds()){
			cont.addContainerProperty(prop, source.getType(prop), null);
		}
		for (Object id : source.getItemIds()) {
			Item sourceItem = source.getItem(id);
			Item destItem = cont.addItem(id);
			for (Object prop : source.getContainerPropertyIds()) {
				Object value = sourceItem.getItemProperty(prop).getValue();
				destItem.getItemProperty(prop).setValue(value);
			}
		}

		return cont;
	}
	public static void showNotification(String msg, Type type){
		try{
			Notification notif = new Notification(msg, type);
			notif.show(UI.getCurrent().getPage());
			notif.setDelayMsec(9000);
		}catch (Exception e){
			LOGGER.error("CommonUtil: " + e.getMessage(), e);
		}
	}
	public static void showNotification(String msg, Type type, Position position){
		try{
			Notification notif = new Notification(msg, type);
			notif.setPosition(position);
			notif.show(UI.getCurrent().getPage());
			notif.setDelayMsec(9000);
		}catch (Exception e){
			LOGGER.error("CommonUtil: " + e.getMessage(), e);
		}
	}
	public static void showNotification(String caption, String msg, Type type){
		Notification notif = new Notification(caption, msg, type);
		notif.show(UI.getCurrent().getPage());
		notif.setDelayMsec(2000);
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public static void setDateRangeValidator(final DateField startDate, final DateField endDate){

		final com.fi.crm.campaigns.web.validators.DateRangeValidator campaignDateRangeValidator = 
				new com.fi.crm.campaigns.web.validators.DateRangeValidator(null, null, Messages.getString("Validator.date.validRange"));
		startDate.addValidator(campaignDateRangeValidator);
		endDate.addValidator(campaignDateRangeValidator);

		/***/
		ValueChangeListener campaignDateValueChangeListener = new ValueChangeListener() {

			/** Serial UID */
			private static final long serialVersionUID = -6037697487606199646L;

			/*
			 * (non-Javadoc)
			 * @see com.vaadin.data.Property.ValueChangeListener#valueChange(com.vaadin.data.Property.ValueChangeEvent)
			 */
			@Override
			public void valueChange(ValueChangeEvent event) {

				if (startDate.getValue() != null && endDate.getValue() != null){
					campaignDateRangeValidator.setDateValue(startDate.getValue());
					campaignDateRangeValidator.setLimitDate(endDate.getValue());
				}
			}
		};

		startDate.addValueChangeListener(campaignDateValueChangeListener);
		endDate.addValueChangeListener(campaignDateValueChangeListener);

	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @param mainTable
	 * @param propertiesToRemove
	 * @param ui
	 * @param reportTitle
	 * @return
	 */
	public static Button getDownloadButton(final Table mainTable, final List<Object> propertiesToRemove, final UI ui, final String reportTitle){

		Button excelExportButton = new Button();
		//setAttributes(excelExportButton, Messages.getString("AdminListView.excel.export"),"excel-icon.png", "link-transparent", null, false);

		excelExportButton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = -73954695086117200L;
			private ExcelExportSXSSF excelExport;

			public void buttonClick(final ClickEvent event) {

				IndexedContainer indexedContainer = copyContainer(mainTable.getContainerDataSource());
				if(propertiesToRemove != null){
					for (Object object : propertiesToRemove) {
						indexedContainer.removeContainerProperty(object);
					} 
				}

				DefaultTableHolder holder = new DefaultTableHolder(mainTable){
					private static final long serialVersionUID = 1L;

					@Override
					public UI getUI() {
						return ui;
					}
				};
				excelExport = new ExcelExportSXSSF(holder);
				excelExport.setDisplayTotals(false);
				excelExport.setReportTitle(reportTitle);
				excelExport.export();
			}
		});

		return excelExportButton;
	}
}