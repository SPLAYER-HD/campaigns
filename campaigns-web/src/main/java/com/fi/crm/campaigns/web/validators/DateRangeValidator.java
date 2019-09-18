package com.fi.crm.campaigns.web.validators;

import java.util.Date;

import com.vaadin.data.Validator;

public class DateRangeValidator implements Validator {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date limitDate;
	private Date dateValue;
	private String errorMessage;




	public DateRangeValidator(Date limitDate, Date dateValue, String errorMessage) {
		super();
		this.limitDate = limitDate;
		this.dateValue = dateValue;
		this.errorMessage = errorMessage;
	}




	@Override
	public void validate(Object value) throws InvalidValueException {

		if (dateValue == null || limitDate == null)
			return;

		if (!(value instanceof Date)){
			throw new InvalidValueException("");
		}else if(limitDate != null && !limitDate.after(dateValue)){
			throw new InvalidValueException(errorMessage);
		}

	}

	/**
	 * @return the limitDate
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * @param limitDate the limitDate to set
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	/**
	 * @return the dateValue
	 */
	public Date getDateValue() {
		return dateValue;
	}

	/**
	 * @param dateValue the dateValue to set
	 */
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

}
