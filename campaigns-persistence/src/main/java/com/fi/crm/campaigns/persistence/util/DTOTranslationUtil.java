/**
 * 
 */
package com.fi.crm.campaigns.persistence.util;

import java.util.List;

import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;

/**
 * @author david.camacho@fitideas.co
 * class to make conversions between DTO's and model objects
 */
public abstract class DTOTranslationUtil <DTOObject, ModelObject> {

	/**
	 * translate a unique model instance to a DTO Instance
	 * @param dtoObject the loaded from data base instance from the model package
	 * @return the equivalent DTO translated
	 */
	public abstract ModelObject translateObject (DTOObject dtoObject) throws TechnicalException;
	
	/**
	 * translate a list of model instances to a list DTO Instances
	 * @param dtoObjectList the loaded from data base instance from the model package
	 * @return the equivalent DTO translated list
	 */
	public abstract List<ModelObject> translateList (List<DTOObject> dtoObjectList) throws TechnicalException;
	
	/**
	 * makes the reverse adminEntityComponent.OPERATION to trasnlateObject translating the DTO instance to model Instance  
	 * @param modelObject the business loaded DTO instance
	 * @return the translated model instance equivalent
	 */
	public abstract DTOObject reverseTranslateObject (ModelObject modelObject) throws TechnicalException;
	
	/**
	 * makes the reverse adminEntityComponent.OPERATION to trasnlateObjectList translating the DTO instances list to model Instances list
	 * @param modelObjectList the business loaded DTO instances list
	 * @return the translated model instances list equivalent
	 */
	public abstract List<DTOObject> reverseTranslateObjectList (List<ModelObject> modelObjectList) throws TechnicalException;

}
