package com.fi.crm.campaigns.web.components;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fi.crm.campaigns.common.enums.InterfaceActionModeEnum;
import com.fi.crm.campaigns.web.forms.BaseFormGrid;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SpringContextHelper;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

/**
 * Class to represent an administration form
 * @author am
 *
 * @param <T>
 */
public abstract class AdminEntityTabView<T> extends BaseFormGrid implements Tab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseFormGrid form;
	private Window window; 
	protected T entity;
	protected HorizontalLayout searchLayout = new HorizontalLayout();
	protected VerticalLayout formLayout = new VerticalLayout();
	protected HorizontalLayout actionLayout = new HorizontalLayout();
	protected SpringContextHelper helper;
	@SuppressWarnings("rawtypes")
	protected AdminStructureBaseView baseView;
	protected Subject currentUser;
	
	protected TextField valueSearchText = new TextField();
	protected Button searchButton = new Button();
	protected Button saveButton = new Button();
	protected Button deleteButton = new Button();
	protected Button editButton = new Button();
	protected Button cancelButton = new Button();
		
	private Boolean searchEnabled;
	private Boolean deleteEnabled;
	private Boolean editEnabled;
	private Boolean newEnabled;
	private boolean closable;
	
	protected InterfaceActionModeEnum actionMode;
	
	public static final String CMD_DELETE = "DELETE";
	public static final String CMD_NEW = "NEW";
	
	public AdminEntityTabView() {
		currentUser = SecurityUtils.getSubject();
		setImmediate(true);
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponent(buildSearchLayout());
		verticalLayout.addComponent(buildFormLayout());
		verticalLayout.addComponent(buildActionLayout());
		addComponent(verticalLayout);
		
		valueSearchText.addFocusListener(new FocusListener() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void focus(FocusEvent event) {
	            searchButton.setClickShortcut(KeyCode.ENTER);
	        }
	    });
		
		valueSearchText.addBlurListener(new BlurListener() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void blur(BlurEvent event) {
	        	searchButton.removeClickShortcut();
	        }
	    });
		/*
		 * Spring
		 */
		helper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());
	}
	
	public abstract T getFilledEntity();
	public abstract void searchEntity();
	public abstract void changeInterfaceActionMode(InterfaceActionModeEnum actionMode);
	public abstract void setEntity( T entity );
	
	public void init() {
		
	}

	public T getEntity() {
		return this.entity;
	}
	
	public BaseFormGrid getForm() {
		return form;
	}

	public void setForm(BaseFormGrid form) {
		this.form = form;
	}

	public void openAdminForm(String name, String cmd) {
		openAdminForm(name, cmd, true, false, false);
	}
	
	@SuppressWarnings("serial")
	public void openAdminForm(String name, String cmd, boolean modal, boolean resizable, boolean fullScreen) {
		window = new Window(name);
		
		window.setModal(modal);
		window.setResizable(resizable);
		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);
		vl.setMargin(true);
		vl.setStyleName("adminEntityForm");
		vl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		if ( fullScreen ) {
			window.setSizeFull();
			vl.setWidth(100, Unit.PERCENTAGE);
		} else {
			vl.setSizeUndefined();
			window.setSizeUndefined();
		}
		window.setContent(vl);
		
		String actionOk;
		String actionCancel;
		HorizontalLayout hl = new HorizontalLayout();
		
		Button okButton = new Button();
		Button cancelButton = new Button();
		if(cmd.equals(CMD_DELETE)){
			vl.addComponent(new Label(String.format(Messages.getString("General.delete"))));
			actionOk = Messages.getString("General.yes");
			actionCancel = Messages.getString("General.no");
			
			okButton.addClickListener(new Button.ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {		
					deleteButtonActionComplete(window);
				} 
			});

			cancelButton.addClickListener(new Button.ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {
					cancelButtonActionComplete(window);
				} 
			});		
		} else if(cmd.equals(CMD_NEW)) {
			vl.addComponent(new Label(String.format(Messages.getString("General.record.not.found"+" "+ valueSearchText.getValue()), form.toString())));
			vl.addComponent(new Label(String.format(Messages.getString("General.want.create"))));
			actionOk = Messages.getString("General.yes");
			actionCancel = Messages.getString("General.no");
			
			okButton.addClickListener(new Button.ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {
					newButtonAction(window);
				} 
			});

			cancelButton.addClickListener(new Button.ClickListener(){

				@Override
				public void buttonClick(ClickEvent event) {
					cancelButtonActionComplete(window);
				} 
			});		
		} else {
			return;
		}
		okButton.setCaption(actionOk);
		cancelButton.setCaption(actionCancel);
		
		hl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		hl.setSpacing(true);
		hl.addComponent(okButton);
		hl.addComponent(cancelButton);
		vl.addComponent(hl);
		window.center();
		
		window.addCloseListener(new Window.CloseListener() {
			
			@Override
			public void windowClose(CloseEvent e) {
				cancelButtonActionComplete(window);
			}
		});
		UI.getCurrent().addWindow(window);
	}

	/**
	 * @return the deleteEnabled
	 */
	public Boolean getDeleteEnabled() {
		return deleteEnabled;
	}

	/**
	 * @param deleteEnabled the deleteEnabled to set
	 */
	public void setDeleteEnabled(Boolean deleteEnabled) {
		this.deleteEnabled = deleteEnabled;
		if( !deleteEnabled ) {
			actionLayout.removeComponent( deleteButton );
		}
	}

	/**
	 * @return the editEnabled
	 */
	public Boolean getEditEnabled() {
		return editEnabled;
	}

	/**
	 * @param editEnabled the editEnabled to set
	 */
	public void setEditEnabled(Boolean editEnabled) {
		this.editEnabled = editEnabled;
		if( !editEnabled ) {
			actionLayout.removeComponent( editButton );
		}
	}

	/**
	 * @return the searchEnabled
	 */
	public Boolean getSearchEnabled() {
		return searchEnabled;
	}

	/**
	 * @param searchEnabled the searchEnabled to set
	 */
	public void setSearchEnabled(Boolean searchEnabled) {
		this.searchEnabled = searchEnabled;
		if( !searchEnabled ) {
			searchLayout.removeComponent(searchButton);
		}
	}

	/**
	 * @return the newEnabled
	 */
	public Boolean getNewEnabled() {
		return newEnabled;
	}

	/**
	 * @param newEnabled the newEnabled to set
	 */
	public void setNewEnabled(Boolean newEnabled) {
		this.newEnabled = newEnabled;
		if( !newEnabled ) {
			actionLayout.removeComponent( saveButton );
		}
	}

	@SuppressWarnings({ "serial" })
	private HorizontalLayout buildSearchLayout(){
		searchLayout.addComponent(valueSearchText);
		searchLayout.addComponent(searchButton);
		searchLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_CENTER);
		searchButton.setCaption(Messages.getString("General.search"));
		searchButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if(CommonUtil.validate(searchLayout)){
					searchEntity();
				} else {
					CommonUtil.showNotification(Messages.getString("General.ValidationErrorCaption"),Messages.getString("General.ValidationErrorMessage"), Notification.Type.WARNING_MESSAGE);
				}
			}
		});
		searchLayout.setMargin(true);
		searchLayout.setSpacing(true);
		return searchLayout;
	}
	
	private VerticalLayout buildFormLayout(){
		formLayout.setWidth("900px");
		formLayout.setHeight("-1");
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		return formLayout;
	}
	
	@SuppressWarnings("serial")
	private HorizontalLayout buildActionLayout(){
		saveButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(CommonUtil.validate(searchLayout) && CommonUtil.validate(formLayout)){
					newButtonActionComplete();
				} else {
					CommonUtil.showNotification(Messages.getString("General.ValidationErrorCaption"),Messages.getString("General.ValidationErrorMessage"), Notification.Type.WARNING_MESSAGE);
				}	
			}
		});
		editButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				if(CommonUtil.validate(searchLayout) && CommonUtil.validate(formLayout)){
					editButtonActionComplete();
				} else {
					CommonUtil.showNotification(Messages.getString("General.ValidationErrorCaption"),Messages.getString("General.ValidationErrorMessage"), Notification.Type.WARNING_MESSAGE);
				}				
			}
		});
		deleteButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				deleteButtonAction();				
			}
		});
		cancelButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				actionMode = InterfaceActionModeEnum.SEARCH_MODE;
				cancelButtonActionComplete();				
			}
		});
		
		saveButton.setCaption(Messages.getString("General.save"));
		editButton.setCaption(Messages.getString("General.save"));
		deleteButton.setCaption(Messages.getString("General.delete.button"));
		cancelButton.setCaption(Messages.getString("General.cancel"));
		actionLayout.addComponent(saveButton);
		actionLayout.addComponent(editButton);
		actionLayout.addComponent(deleteButton);
		actionLayout.addComponent(cancelButton);
		actionLayout.setMargin(true);
		actionLayout.setSpacing(true);
		return actionLayout;
		
	}

	public abstract void newButtonAction(Window w);
	public abstract void newButtonActionComplete();
	public abstract void editButtonActionComplete();	
	public abstract void deleteButtonAction();
	public abstract void deleteButtonActionComplete(Window w);
	public abstract void cancelButtonActionComplete(Window w);
	public abstract void cancelButtonActionComplete();
	
	/**
	 * 
	 */
	public void clearForm() {
		CommonUtil.clearForm(formLayout);
	}

	/**
	 * @return the formLayout
	 */
	public VerticalLayout getFormLayout() {
		return formLayout;
	}

	/**
	 * @param formLayout the formLayout to set
	 */
	public void setFormLayout(VerticalLayout formLayout) {
		this.formLayout = formLayout;
	}

	/**
	 * @return the valueSearchText
	 */
	public TextField getValueSearchText() {
		return valueSearchText;
	}

	/**
	 * @param valueSearchText the valueSearchText to set
	 */
	public void setValueSearchText(TextField valueSearchText) {
		this.valueSearchText = valueSearchText;
	}

	/**
	 * @return the searchButton
	 */
	public Button getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	/**
	 * @return the searchLayout
	 */
	public HorizontalLayout getSearchLayout() {
		return searchLayout;
	}

	/**
	 * @param searchLayout the searchLayout to set
	 */
	public void setSearchLayout(HorizontalLayout searchLayout) {
		this.searchLayout = searchLayout;
	}

	@Override
	public boolean isClosable() {
		return this.closable;
	}

	@Override
	public void setClosable(boolean closable) {
		this.closable = closable;
		
	}

	@Override
	public Component getComponent() {
		return this;
	}
}
