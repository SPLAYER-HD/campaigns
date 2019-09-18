/**
 * 
 */
package com.fi.crm.campaigns.web.views.secure;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.author.AuthorServiceInterface;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.enums.PermissionEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.web.components.AdminEntityComponent;
import com.fi.crm.campaigns.web.forms.AuthorsForm;
import com.fi.crm.campaigns.web.util.CommonUtil;
import com.fi.crm.campaigns.web.util.Messages;
import com.fi.crm.campaigns.web.util.SessionUtil;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

/**
 * @company FitIdeas
 * @author Ing. Jose Augusto Cupasachoa
 * @date Jan, 29/2015
 */
public class AuthorAdminView extends AdminBaseView{

	/** Serial UID */
	private static final long serialVersionUID = 1915047676438371858L;

	/* Attributes */
	private AdminEntityComponent adminEntityComponent;
	private AuthorServiceInterface authorService;
	private AuthorDTO authorDTO;

	/* Constants */
	private static final String AUTHOR_NAME = "NAME";
	private static final String AUTHOR_LAST_NAME_1 = "LAST_NAME_1";
	private static final String AUTHOR_LAST_NAME_2 = "LAST_NAME_2";
	private static final String AUTHOR_EMAIL = "EMAIL";
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorAdminView.class);

	/** Default constructor */
	public AuthorAdminView(){
		super();
		this.init();
	}

	/**
	 * 
	 */
	private void init(){

		try{

			section.setContent(FontAwesome.USERS, Messages.getString("bread.authors"));
			final AuthorsForm authorsForm = new AuthorsForm();
			final FieldGroup fieldGroup = new FieldGroup();
			this.authorService = (AuthorServiceInterface)springContextHelper.getBean("authorService");

			this.adminEntityComponent = new AdminEntityComponent() {

				/** Serial UID */
				private static final long serialVersionUID = 7687702857171789626L;

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.BaseAdminEntityComponent#formActionCancel(com.vaadin.ui.Window)
				 */
				@Override
				public void formActionCancel(Window w) {
					w.close();
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#editItemActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void editItemActionComplete(Window w) {

					try{

						fieldGroup.commit();
						authorService.updateAuthor(authorDTO, SessionUtil.getUserInfo());
						CommonUtil.showNotification(Messages.getString("General.edit"), Type.HUMANIZED_MESSAGE);
						fillTable();
						w.close();

					}catch (Exception e){
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#editItemAction(java.lang.Object)
				 */
				@Override
				public void editItemAction(Object data) {

					try{

						authorDTO = (AuthorDTO)data;
						BeanItem<AuthorDTO> beanItem = new BeanItem<AuthorDTO>(authorDTO);
						fieldGroup.setItemDataSource(beanItem);
						fieldGroup.bindMemberFields(authorsForm);
						adminEntityComponent.setForm(authorsForm);
						adminEntityComponent.openAdminForm(Messages.getString("AuthorsForm.edit"), CMD_EDIT);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#deleteItemActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void deleteItemActionComplete(Window w) {

					try{

						fieldGroup.commit();
						authorService.deleteAuthor(authorDTO, SessionUtil.getUserInfo());
						CommonUtil.showNotification(Messages.getString("General.delete.success"), Type.HUMANIZED_MESSAGE);
						fillTable();
						w.close();

					}catch (BusinessException be){

						if (be.getCode() != null && be.getCode().equals(ErrorCodesEnum.DELETE_NOT_POSSIBLE_BY_REFERENCE))
							CommonUtil.showNotification(Messages.getString("AuthorsForm.err.delete.reference"), Type.ERROR_MESSAGE);
						else
							CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);

						LOGGER.error(be.getMessage(), be);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				@Override
				public void deleteItemAction(Object data) {

					try{

						authorDTO = (AuthorDTO)data;
						BeanItem<AuthorDTO> beanItem = new BeanItem<AuthorDTO>(authorDTO);
						fieldGroup.setItemDataSource(beanItem);
						fieldGroup.bindMemberFields(authorsForm);
						adminEntityComponent.setForm(authorsForm);
						adminEntityComponent.openAdminForm(Messages.getString("AuthorsForm.delete"), CMD_DELETE);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString("General.error"), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#addButtonActionComplete(com.vaadin.ui.Window)
				 */
				@Override
				public void addButtonActionComplete(Window w) {

					try{

						fieldGroup.commit();
						authorService.createAuthor(authorDTO, SessionUtil.getUserInfo());
						CommonUtil.showNotification(Messages.getString("General.save.success"), Type.HUMANIZED_MESSAGE);
						fillTable();
						w.close();

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString(""), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}

				/*
				 * (non-Javadoc)
				 * @see com.fi.crm.campaigns.web.components.AdminEntityComponent#addButtonAction()
				 */
				@Override
				public void addButtonAction() {

					try{

						authorDTO = new AuthorDTO();
						BeanItem<AuthorDTO> beanItem = new BeanItem<AuthorDTO>(authorDTO);
						fieldGroup.setItemDataSource(beanItem);
						fieldGroup.bindMemberFields(authorsForm);
						adminEntityComponent.setForm(authorsForm);
						adminEntityComponent.openAdminForm(Messages.getString("AuthorsForm.add"), CMD_ADD);

					}catch (Exception e){
						CommonUtil.showNotification(Messages.getString(""), Type.ERROR_MESSAGE);
						LOGGER.error(e.getMessage(), e);
					}
				}
			};

			if (subject.isPermitted(PermissionEnum.AUTHORS_ADMIN_INSERT.toString()))
				adminEntityComponent.setNewEnabled(true);
			else
				adminEntityComponent.setNewEnabled(false);

			if (subject.isPermitted(PermissionEnum.AUTHORS_ADMIN_UPDATE.toString()))
				adminEntityComponent.setEditEnabled(true);
			else
				adminEntityComponent.setEditEnabled(false);

			if (subject.isPermitted(PermissionEnum.AUTHORS_ADMIN_DELETE.toString()))
				adminEntityComponent.setDeleteEnabled(true);
			else
				adminEntityComponent.setDeleteEnabled(false);

			this.defineTable();
			this.fillTable();
			addComponent(adminEntityComponent);

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#fillTable()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void fillTable() {

		try{

			List<AuthorDTO> authors = authorService.getAllAuthors(SessionUtil.getUserInfo());

			if (authors != null && !authors.isEmpty()){

				adminEntityComponent.getEntityTable().removeAllItems();

				for (AuthorDTO author: authors){

					adminEntityComponent.getEntityTable().addItem(author);
					adminEntityComponent.buildRowActionLayout(author);
					Item item = adminEntityComponent.getEntityTable().getItem(author);

					item.getItemProperty(AUTHOR_NAME).setValue(author.getFirstName());
					item.getItemProperty(AUTHOR_LAST_NAME_1).setValue(author.getLastName1());
					item.getItemProperty(AUTHOR_LAST_NAME_2).setValue(author.getLastName2());
					item.getItemProperty(AUTHOR_EMAIL).setValue(author.getEmail());
					item.getItemProperty(adminEntityComponent.OPERATION).setValue(((AdminEntityComponent)adminEntityComponent).getRowActionLayout());

				}
			}

			/* Show exactly the currently contained rows (items) */
			adminEntityComponent.getEntityTable().setPageLength(adminEntityComponent.getEntityTable().size());

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.fi.crm.campaigns.web.views.secure.AdminBaseView#defineTable()
	 */
	@Override
	public void defineTable(){

		try{

			LOGGER.debug("Start method: AuthorAdminView.defineTable!!");

			adminEntityComponent.getEntityTable().addContainerProperty(AUTHOR_NAME, String.class, "", Messages.getString("AuthorsForm.firstName"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(AUTHOR_LAST_NAME_1, String.class, "", Messages.getString("AuthorsForm.lastName1"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(AUTHOR_LAST_NAME_2, String.class, "", Messages.getString("AuthorsForm.lastName2"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(AUTHOR_EMAIL, String.class, "", Messages.getString("AuthorsForm.email"), null, null);
			adminEntityComponent.getEntityTable().addContainerProperty(adminEntityComponent.OPERATION, HorizontalLayout.class, null, Messages.getString("General.actions"), null, null);

			/* Show exactly the currently contained rows (items) */
			adminEntityComponent.getEntityTable().setPageLength(adminEntityComponent.getEntityTable().size());

			LOGGER.debug("Stop method: AuthorAdminView.defineTable!!");

		}catch (Exception e){
			LOGGER.error(e.getMessage(), e);
		}
	}
}