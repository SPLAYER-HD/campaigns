package com.fi.crm.campaigns.web.util;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fi.crm.campaigns.business.services.author.AuthorServiceInterface;
import com.fi.crm.campaigns.business.services.parametrization.ParametrizationServiceInterface;
import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CityDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.PdUsuarioDTO;
import com.fi.crm.campaigns.common.dto.ProvinceDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.enums.CampaignContactTypeEnum;
import com.fi.crm.campaigns.common.enums.CampaignStatusEnum;
import com.fi.crm.campaigns.common.enums.GenreEnum;
import com.fi.crm.campaigns.common.enums.PermissionEnum;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.web.util.Messages;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

public class CatalogUtil {

	/* Services */
	private static ParametrizationServiceInterface parametrizationService;
	private static AuthorServiceInterface authorService;
	private static SpringContextHelper springContextHelper;

	/**
	 * static logger for class CatalogUtil
	 */
	private static final Logger logger = LoggerFactory.getLogger(CatalogUtil.class);

	/**
	 * Method that allows add options to a select component from a separated by
	 * character string
	 * 
	 * @param select
	 * @param str
	 * @param separator
	 */
	@SuppressWarnings("unchecked")
	public static void addOptions(AbstractSelect select, String str, String separator) {
		if (str == null) {
			return;
		}

		StringTokenizer st = new StringTokenizer(str, separator);

		select.addContainerProperty("id", String.class, null);
		select.addContainerProperty("text", String.class, null);
		List<String> selected = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String val = st.nextToken();
			Item item = select.addItem(val);
			item.getItemProperty("id").setValue(val);
			item.getItemProperty("text").setValue(val);
			selected.add(val);
		}
		select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		select.setItemCaptionPropertyId("text");
		select.setValue(selected);
	}

	/**
	 * Method that allows add options to a currency select
	 * 
	 * @param select
	 * @param currencies
	 */
	@SuppressWarnings("unchecked")
	public static void addCurrencyOptions(AbstractSelect select, List<Currency> currencies) {
		if (currencies == null) {
			return;
		}
		select.addContainerProperty("text", String.class, null);
		for (Currency currency : currencies) {
			select.addItem(currency);
			Item item = select.getItem(currency);
			item.getItemProperty("text").setValue(currency.getDisplayName());
		}
		select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		select.setItemCaptionPropertyId("text");
	}

	@SuppressWarnings("unchecked")
	public static void addPermissionOptions(AbstractSelect select) {
		select.addContainerProperty("text", String.class, null);
		for (PermissionEnum permission : PermissionEnum.values()) {
			select.addItem(permission);
			Item item = select.getItem(permission);
			item.getItemProperty("text").setValue(Messages.getString(permission.getViewKey()));
		}
		select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		select.setItemCaptionPropertyId("text");

	}


	/**
	 * @param documentTypeCombo
	 */
	@SuppressWarnings("unchecked")
	public static void addCampaignStatus(AbstractSelect select) {

		try {

			CampaignStatusEnum[] values = CampaignStatusEnum.values();
			select.setNullSelectionAllowed(false);
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);

			for (int i = 0; i < values.length; i++) {
				Item item = select.addItem(values[i]);
				String value = values[i].getValue();
				item.getItemProperty("id").setValue(value);
				item.getItemProperty("text").setValue(Messages.getString(values[i].getViewKey()));
			}
			
			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");
			select.setValue(CampaignStatusEnum.ALL);

		} catch (Exception e) {
			logger.error("Error in addCampaignStatus method", e);
		}
	}

	/**
	 * Load Business Units 
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectBusinessUnit(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<BusinessUnitDTO> unitList = parametrizationService.getBusinessUnits(SessionUtil.getUserInfo());
			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);
			Item itemAll = select.addItem(new BusinessUnitDTO());
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));

			for (BusinessUnitDTO businessUnitDTO: unitList){
				Item item = select.addItem(businessUnitDTO);
				item.getItemProperty("text").setValue(businessUnitDTO.getName());
			}
			
			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectBusinessUnitById(AbstractSelect select, String codigo, String noCia){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);

			Item itemAll = select.addItem(new BusinessUnitDTO());
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));

			if (codigo != null && codigo.length() > 0){
				BusinessUnitDTO businessUnitDTO = parametrizationService.getBusinessUnitById(codigo, noCia, SessionUtil.getUserInfo());
				Item item = select.addItem(businessUnitDTO);
				item.getItemProperty("text").setValue(businessUnitDTO.getName());
			}
			
			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public static void loadSelectBrands(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<MarcasDTO> unitList = parametrizationService.getBrands(SessionUtil.getUserInfo());
			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);

			MarcasDTO all = new MarcasDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
		
			for (MarcasDTO marcasDTO: unitList){

				Item item = select.addItem(marcasDTO);
				item.getItemProperty("text").setValue(marcasDTO.getDescripcion());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectBrandById(AbstractSelect select, String noCia, String codigo){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");
			
			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);

			MarcasDTO all = new MarcasDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
		
			if (codigo != null){
				MarcasDTO marcasDTO = parametrizationService.getBrandById(noCia, codigo, SessionUtil.getUserInfo());
				Item item = select.addItem(marcasDTO);
				item.getItemProperty("text").setValue(marcasDTO.getDescripcion());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadSelectArticleByBrand(AbstractSelect select, String brandId, boolean loadWhenNullBrandId){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);
			
			ArticleDTO all = new ArticleDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			if (loadWhenNullBrandId){
				List<ArticleDTO> unitList = parametrizationService.getArticlesByBrand(SessionUtil.getUserInfo(),brandId);
				for (ArticleDTO articleDTO: unitList){

					Item item = select.addItem(articleDTO);
					item.getItemProperty("text").setValue(articleDTO.getNoArti() + "--"+ articleDTO.getDescripcion());
				}
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectArticleById(AbstractSelect select, String noCia, String brandId, String articleId){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			select.removeAllItems();
			select.addContainerProperty("id", String.class, null);
			select.addContainerProperty("text", String.class, null);
			
			ArticleDTO all = new ArticleDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			if (noCia != null && brandId != null && articleId != null){
				ArticleDTO articleDTO = parametrizationService.getArticlesById(SessionUtil.getUserInfo(),noCia, brandId, articleId);
				if (articleDTO != null){
					Item item = select.addItem(articleDTO);
					item.getItemProperty("text").setValue(articleDTO.getNoArti() + "--"+ articleDTO.getDescripcion());
				}
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	
	/**
	 * Load Authors
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectAuthors(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (authorService == null)
				authorService = (AuthorServiceInterface)springContextHelper.getBean("authorService");

			List<AuthorDTO> authorsList = authorService.getAllAuthors(SessionUtil.getUserInfo());

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			select.setNullSelectionAllowed(false);
			
			for (AuthorDTO authorDTO: authorsList){
				Item item = select.addItem(authorDTO);
				item.getItemProperty("text").setValue(authorDTO.getFullName());
			}
			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectAuthorsByStatus(AbstractSelect select, String status){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (authorService == null)
				authorService = (AuthorServiceInterface)springContextHelper.getBean("authorService");

			List<AuthorDTO> authorsList = authorService.getAllAuthorsByStatus(SessionUtil.getUserInfo(), status);

			
			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			select.setNullSelectionAllowed(false);
			
			for (AuthorDTO authorDTO: authorsList){
				Item item = select.addItem(authorDTO);
				item.getItemProperty("text").setValue(authorDTO.getFullName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectAuthorsById(AbstractSelect select, Integer authorId){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (authorService == null)
				authorService = (AuthorServiceInterface)springContextHelper.getBean("authorService");

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			select.setNullSelectionAllowed(false);
			
			if (authorId != null ){
				AuthorDTO authorDTO = authorService.getAuthorById(SessionUtil.getUserInfo(), authorId);
				Item item = select.addItem(authorDTO);
				item.getItemProperty("text").setValue(authorDTO.getFullName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Load stores
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectStore(AbstractSelect select, String businessUnitId){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<StoreDTO> storesList = parametrizationService.getStoresByBusinessUnit(SessionUtil.getUserInfo(),businessUnitId);

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);

			StoreDTO all = new StoreDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));

			for (StoreDTO storeDTO: storesList){

				Item item = select.addItem(storeDTO);
				item.getItemProperty("text").setValue(storeDTO.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void loadSelectStoreById(AbstractSelect select, String noCia, String businessUnitId, String codigo){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);

			StoreDTO all = new StoreDTO();
			Item itemAll = select.addItem(all);
			itemAll.getItemProperty("text").setValue(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));
			select.setNullSelectionItemId(Messages.getString(CampaignStatusEnum.ALL.getViewKey()));

			if (codigo != null && businessUnitId != null){
				StoreDTO storeDTO = parametrizationService.getStoresById(SessionUtil.getUserInfo(), noCia, businessUnitId, codigo);
				Item item = select.addItem(storeDTO);
				item.getItemProperty("text").setValue(storeDTO.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	
	/**
	 * Load contact typo for campaigns
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectContactType(AbstractSelect select){

		try{

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (CampaignContactTypeEnum contactType: CampaignContactTypeEnum.values()){
				Item item = select.addItem(contactType);
				item.getItemProperty("text").setValue(Messages.getString(contactType.getViewKey()));
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Load trace status into select
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectTraceStatus(AbstractSelect select){

		try{

			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (TracingStatusEnum status: TracingStatusEnum.values()){
				Item item = select.addItem(status);
				item.getItemProperty("text").setValue(Messages.getString(status.getViewKey()));
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Load trace status into select
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectTraceSubStatus(AbstractSelect select, TracingStatusEnum tracingStatusEnum){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<TracingStatusDTO> list = parametrizationService.getTracingStatusByType(tracingStatusEnum, SessionUtil.getUserInfo());

			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (TracingStatusDTO tracingStatusDTO: list){
				Item item = select.addItem(tracingStatusDTO);
				item.getItemProperty("text").setValue(tracingStatusDTO.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Load document type into select
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectDocumentType(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<DocumentTypeDTO> documentTipes = parametrizationService.getDocumentTypes();

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (DocumentTypeDTO documentType: documentTipes){
				Item item = select.addItem(documentType);
				item.getItemProperty("text").setValue(documentType.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Load genre into select
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectGenre(AbstractSelect select){

		try{

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (GenreEnum genreEnum: GenreEnum.values()){
				Item item = select.addItem(genreEnum);
				item.getItemProperty("text").setValue(Messages.getString(genreEnum.getDescription()));
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectProvince(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<ProvinceDTO> provinces = parametrizationService.getProvinces();

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);

			for (ProvinceDTO province: provinces){
				Item item = select.addItem(province);
				item.getItemProperty("text").setValue(province.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param select
	 */
	@SuppressWarnings("unchecked")
	public static void loadSelectCities(AbstractSelect select, String countryId, String provinceId){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<CityDTO> cities = parametrizationService.getCities(provinceId);

			select.removeAllItems();
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);
			
			for (CityDTO city: cities){
				Item item = select.addItem(city);
				item.getItemProperty("text").setValue(city.getName());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadSelectPdUsuario(AbstractSelect select){

		try{

			if (springContextHelper == null)
				springContextHelper = new SpringContextHelper(VaadinServlet.getCurrent().getServletContext());

			if (parametrizationService == null)
				parametrizationService = (ParametrizationServiceInterface)springContextHelper.getBean("parametrizationService");

			List<PdUsuarioDTO> pdUsuarios = parametrizationService.getAllPdUsuario(SessionUtil.getUserInfo());

			select.removeAllItems();
			
			select.addContainerProperty("text", String.class, null);
			select.addContainerProperty("id", String.class, null);

			for (PdUsuarioDTO pdUsuario: pdUsuarios){
				Item item = select.addItem(pdUsuario);
				item.getItemProperty("text").setValue(pdUsuario.getDescripcion());
			}

			select.setItemCaptionMode(ItemCaptionMode.PROPERTY);
			select.setItemCaptionPropertyId("text");

		}catch (Exception e){
			logger.error(e.getMessage(), e);
		}
	}
}