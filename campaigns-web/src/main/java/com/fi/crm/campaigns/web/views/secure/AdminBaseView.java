/**
 * 
 */
package com.fi.crm.campaigns.web.views.secure;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 29/2015
 */
public abstract class AdminBaseView extends BaseView{

	/** Serial UID */
	private static final long serialVersionUID = 7180380510974418077L;

	/**
	 * 
	 */
	public abstract void fillTable();

	/**
	 * 
	 */
	public abstract void defineTable();
}