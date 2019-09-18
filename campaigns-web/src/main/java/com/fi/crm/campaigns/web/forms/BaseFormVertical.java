package com.fi.crm.campaigns.web.forms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class BaseFormVertical extends VerticalLayout implements BaseForm{
	private List<HorizontalLayout> layouts;
	protected int numComponentsPerLayout = 3;
	private List<RepaintListener> listeners = new ArrayList<RepaintListener>();
	private List<NewElementsInFormListener> newElementsListeners = new ArrayList<NewElementsInFormListener>();
	
	public BaseFormVertical() {
		super();
		this.setSpacing(true);
		this.setSizeUndefined(); // This isn't enough
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void clearForm() {
		Iterator<Component> it = this.iterator();
		while (it.hasNext()) {
			Component c = it.next();
			if(c instanceof AbstractField) {
				((AbstractField) c).setValue(null);	
			}
		}
		
		if ( layouts == null ) {
			return;
		}
		
		for ( HorizontalLayout hl : layouts ) {
			Iterator<Component> itHl = hl.iterator();
			while (itHl.hasNext()) {
				Component c = itHl.next();
				
				if(c instanceof AbstractField) {
					((AbstractField) c).setValue(null);	
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setRequired(boolean required){
		Iterator<Component> it = this.iterator();
		while (it.hasNext()) {
			final Component c = it.next();
			if(c instanceof AbstractField) {
				((AbstractField) c).setRequired(required);
				((AbstractField) c).setImmediate(true);
				((AbstractField) c).addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						System.out.println("field c ==" + ((AbstractField) c).getCaption());
						if(c instanceof AbstractField) {
							System.out.println(" if field ==" + ((AbstractField) c).getCaption());
							((AbstractField) c).setRequired(true);
						}				
					}
				});
			}
		}
		
		if ( layouts == null ) {
			return;
		}
		
		for ( HorizontalLayout hl : layouts ) {
			Iterator<Component> itHl = hl.iterator();
			while (itHl.hasNext()) {
				final Component c = itHl.next();
				
				if(c instanceof AbstractField) {
					((AbstractField) c).setRequired(required);
					((AbstractField) c).setImmediate(true);
					((AbstractField) c).addValueChangeListener(new ValueChangeListener() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(ValueChangeEvent event) {
							if(c instanceof AbstractField) {
								System.out.println("field ==" + ((AbstractField) c).getCaption());
								((AbstractField) c).setRequired(true);
							}				
						}
					});
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void setListener(boolean required){
		
		Iterator<Component> it = this.iterator();
		while (it.hasNext()) {
			final Component c = it.next();
			if(c instanceof AbstractField) {
				System.out.println("field ==" + ((AbstractField) c).getCaption());
				((AbstractField) c).setRequired(required);
				((AbstractField) c).addValueChangeListener(new ValueChangeListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						if(c instanceof AbstractField) {
							System.out.println("field ==" + ((AbstractField) c).getCaption());
							((AbstractField) c).setRequired(true);
						}				
					}
				});
			}
		}
		
		if ( layouts == null ) {
			return;
		}
		
		for ( HorizontalLayout hl : layouts ) {
			Iterator<Component> itHl = hl.iterator();
			while (itHl.hasNext()) {
				final Component c = itHl.next();
				
				if(c instanceof AbstractField) {
					System.out.println("field ==" + ((AbstractField) c).getCaption());
					((AbstractField) c).setRequired(required);
					((AbstractField) c).addValueChangeListener(new ValueChangeListener() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(ValueChangeEvent event) {
							if(c instanceof AbstractField) {
								System.out.println("field ==" + ((AbstractField) c).getCaption());
								((AbstractField) c).setRequired(true);
							}				
						}
					});
				}
			}
		}
	}
	

	protected void notifyRepaintToListeners() {
		for ( RepaintListener listener : listeners ) {
			listener.requestRepaint();
		}
	}

	public void addRepaintListener(RepaintListener listener) {
		if ( listener != null) {
			listeners.add(listener);
		}
	}
	
	public interface RepaintListener {
		public void requestRepaint();
	}
	
	public void addNewElementsInFormListener(NewElementsInFormListener listener) {
		if ( listener != null) {
			newElementsListeners.add(listener);
		}
	}
	
	public void notifyNewElementsToListeners() {
		for ( NewElementsInFormListener listener : newElementsListeners ) {
			listener.newElementsInForm();
		}
	}
	
	public interface NewElementsInFormListener {

		public void newElementsInForm();
	}

}
