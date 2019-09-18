package com.fi.crm.campaigns.web.components;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.fi.crm.campaigns.common.enums.InterfaceActionModeEnum;
import com.fi.crm.campaigns.web.forms.BaseFormGrid;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SpringContextHelper;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Class to represent an administration form
 * 
 * @author am
 * 
 * @param <T>
 */
public abstract class AdminListView<T> extends BaseFormGrid {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseFormGrid form;
	protected List<T> entityList;
	protected HorizontalLayout searchLayout = new HorizontalLayout();
	protected VerticalLayout formLayout = new VerticalLayout();
	protected HorizontalLayout actionLayout = new HorizontalLayout();
	protected BaseAdminEntityComponent adminEntityComponent;
	protected SpringContextHelper helper;
	@SuppressWarnings("rawtypes")
	protected AdminStructureBaseView baseView;
	protected Subject currentUser;

	protected TextField valueSearchText = new TextField();
	protected Button searchButton = new Button();
	protected Button saveButton = new Button();

	private Boolean searchEnabled;
	private Boolean deleteEnabled;
	private Boolean editEnabled;
	private Boolean newEnabled;

	protected InterfaceActionModeEnum actionMode;

	public AdminListView() {
		currentUser = SecurityUtils.getSubject();
		setImmediate(true);
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponent(buildSearchLayout());
		verticalLayout.addComponent(buildFormLayout());
		verticalLayout.addComponent(buildActionLayout());
		addComponent(verticalLayout, true, true);

		valueSearchText.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -6549081726526133772L;

			public void valueChange(ValueChangeEvent event) {
				// Assuming that the value type is a String
				searchEntity();
			}
		});
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

	public abstract List<T> getFilledList();

	public abstract void searchEntity();

	public abstract void setEntityList(List<T> entityList);

	public abstract void fillTable();

	public void init() {

	}

	public List<T> getEntityList() {
		return this.entityList;
	}

	public BaseFormGrid getForm() {
		return form;
	}

	public void setForm(BaseFormGrid form) {
		this.form = form;
	}

	/**
	 * @return the deleteEnabled
	 */
	public Boolean getDeleteEnabled() {
		return deleteEnabled;
	}

	/**
	 * @param deleteEnabled
	 *            the deleteEnabled to set
	 */
	public void setDeleteEnabled(Boolean deleteEnabled) {
		this.deleteEnabled = deleteEnabled;
	}

	/**
	 * @return the editEnabled
	 */
	public Boolean getEditEnabled() {
		return editEnabled;
	}

	/**
	 * @param editEnabled
	 *            the editEnabled to set
	 */
	public void setEditEnabled(Boolean editEnabled) {
		this.editEnabled = editEnabled;
	}

	/**
	 * @return the searchEnabled
	 */
	public Boolean getSearchEnabled() {
		return searchEnabled;
	}

	/**
	 * @param searchEnabled
	 *            the searchEnabled to set
	 */
	public void setSearchEnabled(Boolean searchEnabled) {
		this.searchEnabled = searchEnabled;
		if (!searchEnabled) {
			this.removeComponent(searchLayout);
		}
	}

	/**
	 * @return the newEnabled
	 */
	public Boolean getNewEnabled() {
		return newEnabled;
	}

	/**
	 * @param newEnabled
	 *            the newEnabled to set
	 */
	public void setNewEnabled(Boolean newEnabled) {
		this.newEnabled = newEnabled;
		if (!newEnabled) {
			actionLayout.removeComponent(saveButton);
		}
	}

	@SuppressWarnings({ "serial" })
	private HorizontalLayout buildSearchLayout() {
		searchLayout.addComponent(valueSearchText);
		searchLayout.addComponent(searchButton);
		searchLayout.setComponentAlignment(searchButton, Alignment.BOTTOM_CENTER);
		searchButton.setCaption(Messages.getString("General.search"));
		searchButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (CommonUtil.validate(searchLayout)) {
					searchEntity();
				} else {
					CommonUtil.showNotification(Messages.getString("General.ValidationErrorCaption"),
							Messages.getString("General.ValidationErrorMessage"), Notification.Type.WARNING_MESSAGE);
				}
			}
		});
		searchLayout.setMargin(true);
		searchLayout.setSpacing(true);
		return searchLayout;
	}

	private VerticalLayout buildFormLayout() {
		formLayout.setWidth("800px");
		formLayout.setHeight("-1");
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		return formLayout;
	}

	@SuppressWarnings("serial")
	private HorizontalLayout buildActionLayout() {
		saveButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				saveButtonActionComplete();
			}
		});

		saveButton.setCaption(Messages.getString("General.save"));

		actionLayout.addComponent(saveButton);
		actionLayout.setMargin(true);
		actionLayout.setSpacing(true);
		return actionLayout;

	}

	public abstract void saveButtonActionComplete();

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
	 * @param formLayout
	 *            the formLayout to set
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
	 * @param valueSearchText
	 *            the valueSearchText to set
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
	 * @param searchButton
	 *            the searchButton to set
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
	 * @param searchLayout
	 *            the searchLayout to set
	 */
	public void setSearchLayout(HorizontalLayout searchLayout) {
		this.searchLayout = searchLayout;
	}

}
