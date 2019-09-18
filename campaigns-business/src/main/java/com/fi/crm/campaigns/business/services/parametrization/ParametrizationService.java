package com.fi.crm.campaigns.business.services.parametrization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fi.crm.campaigns.business.services.constant.ConstantServiceInterface;
import com.fi.crm.campaigns.business.util.CityComparator;
import com.fi.crm.campaigns.business.util.DocumentTypeComparator;
import com.fi.crm.campaigns.business.util.ProvinceComparator;
import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CityDTO;
import com.fi.crm.campaigns.common.dto.ConstantDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.PdUsuarioDTO;
import com.fi.crm.campaigns.common.dto.ProvinceDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.ConstantsIdentifierEnum;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.ArgecanMapper;
import com.fi.crm.campaigns.persistence.mappers.ArgeproMapper;
import com.fi.crm.campaigns.persistence.mappers.ArgetidMapper;
import com.fi.crm.campaigns.persistence.mappers.ArincdMapper;
import com.fi.crm.campaigns.persistence.mappers.ArindaMapper;
import com.fi.crm.campaigns.persistence.mappers.ArunnegMapper;
import com.fi.crm.campaigns.persistence.mappers.CpEstadoSeguimientoMapper;
import com.fi.crm.campaigns.persistence.mappers.MarcasMapper;
import com.fi.crm.campaigns.persistence.mappers.PdUsuarioMapper;
import com.fi.crm.campaigns.persistence.mappers.PvtiendassvyMapper;
import com.fi.crm.campaigns.persistence.model.Argecan;
import com.fi.crm.campaigns.persistence.model.ArgecanExample;
import com.fi.crm.campaigns.persistence.model.ArgecanExample.Criteria;
import com.fi.crm.campaigns.persistence.model.Argepro;
import com.fi.crm.campaigns.persistence.model.ArgeproExample;
import com.fi.crm.campaigns.persistence.model.Argetid;
import com.fi.crm.campaigns.persistence.model.ArgetidExample;
import com.fi.crm.campaigns.persistence.model.ArincdExample;
import com.fi.crm.campaigns.persistence.model.ArincdKey;
import com.fi.crm.campaigns.persistence.model.ArindaExample;
import com.fi.crm.campaigns.persistence.model.ArunnegExample;
import com.fi.crm.campaigns.persistence.model.ArunnegKey;
import com.fi.crm.campaigns.persistence.model.CpEstadoSeguimientoExample;
import com.fi.crm.campaigns.persistence.model.MarcasExample;
import com.fi.crm.campaigns.persistence.model.PdUsuarioExample;
import com.fi.crm.campaigns.persistence.model.Pvtiendassvy;
import com.fi.crm.campaigns.persistence.model.PvtiendassvyExample;
import com.fi.crm.campaigns.persistence.util.ArticleTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.BrandsTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.BusinessUnitTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.PdUsuarioTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.StoreTranslatorUtil;
import com.fi.crm.campaigns.persistence.util.TracingStatusTranslatorUtil;
import com.googlecode.ehcache.annotations.Cacheable;

public class ParametrizationService implements ParametrizationServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ParametrizationService.class);

	// mappers
	@Autowired
	private ArincdMapper arincdMapper;
	@Autowired
	private ArunnegMapper arunnegMapper;
	@Autowired
	private CpEstadoSeguimientoMapper estadoSeguimientoMapper;
	@Autowired
	private ArgetidMapper documentTypesMapper;
	@Autowired
	private ArgecanMapper citiesMapper;
	@Autowired
	private ArgeproMapper provincesMapper;

	@Autowired
	private PvtiendassvyMapper pvtiendassvyMapper;

	@Autowired
	private MarcasMapper marcasMapper;

	@Autowired
	private ArindaMapper arindaMapper;
	
	@Autowired
	private PdUsuarioMapper pdUsuarioMapper;
	
	@Autowired
	private ConstantServiceInterface constantService;
	// Translators
	private static final StoreTranslatorUtil STORE_TRANSLATOR_UTIL = new StoreTranslatorUtil();
	private static final BusinessUnitTranslatorUtil BUSINESS_UNIT_TRANSLATOR_UTIL = new BusinessUnitTranslatorUtil();
	private static final TracingStatusTranslatorUtil TRACING_STATUS_TRANSLATOR_UTIL = new TracingStatusTranslatorUtil();
	private static final BrandsTranslatorUtil BRANDS_TRANSLATOR_UTIL = new BrandsTranslatorUtil();
	private static final ArticleTranslatorUtil ARTICLE_TRANSLATOR_UTIL = new ArticleTranslatorUtil();
	private static final PdUsuarioTranslatorUtil PDUSUARIO_TRANSLATOR_UTIL = new PdUsuarioTranslatorUtil();
	
	@Override
	@Cacheable(cacheName = "getAllStores")
	public List<StoreDTO> getStores(UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getStores method");
		try {
			ArincdExample example = new ArincdExample();
			example.createCriteria().getAllCriteria();
			return STORE_TRANSLATOR_UTIL
					.reverseTranslateObjectList(arincdMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getStores method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getStores method");
		}
	}

	@Override
	@Cacheable(cacheName = "getAllStoresByBusinessUnit")
	public List<StoreDTO> getStoresByBusinessUnit(UserInfoDTO userInfo,
			String businessUnitId) throws BusinessException {
		logger.debug("Staring getAllStoresByBusinessUnit method");
		try {
			PvtiendassvyExample example = new PvtiendassvyExample();
			PvtiendassvyExample.Criteria crit = example.createCriteria();
			HashMap<String, ConstantDTO> allConstants = constantService
					.getAllConstants();
			ConstantDTO company = allConstants.get(
					ConstantsIdentifierEnum.DEFAULT_COMPANY);
			String companyId = ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue();
			if (company != null)
				companyId = company.getValue();
			crit.andNoCiaEqualTo(companyId);
			if (businessUnitId != null && businessUnitId.length() > 0)
				crit.andCodNegocioEqualTo(businessUnitId);

			example.setOrderByClause("nombre asc");
			List<Pvtiendassvy> arstores = pvtiendassvyMapper
					.selectByExample(example);

			// TODO generar un traductor
			List<StoreDTO> stores = new ArrayList<StoreDTO>();
			for (Pvtiendassvy arstore : arstores) {
				StoreDTO store = new StoreDTO();
				store.setCenter(arstore.getCentro());
				store.setName(arstore.getNombre());
				store.setNoCia(arstore.getNoCia());
				stores.add(store);
			}

			return stores;
		} catch (Exception e) {
			logger.error("Error getAllStoresByBusinessUnit method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getAllStoresByBusinessUnit method");
		}
	}

	@Override
	public StoreDTO getStoresById(UserInfoDTO userInfo,
			String noCia, String businessUnitId, String codigo) throws BusinessException {
		logger.debug("Staring getStoresById method");
		try {
			PvtiendassvyExample example = new PvtiendassvyExample();
			PvtiendassvyExample.Criteria crit = example.createCriteria();
			crit.andNoCiaEqualTo(noCia);
			crit.andCodNegocioEqualTo(businessUnitId);
			crit.andCentroEqualTo(codigo);

			List<Pvtiendassvy> arstores = pvtiendassvyMapper
					.selectByExample(example);

			List<StoreDTO> stores = new ArrayList<StoreDTO>();
			for (Pvtiendassvy arstore : arstores) {
				StoreDTO store = new StoreDTO();
				store.setCenter(arstore.getCentro());
				store.setName(arstore.getNombre());
				store.setNoCia(arstore.getNoCia());
				stores.add(store);
			}

			return stores.get(0);
		} catch (Exception e) {
			logger.error("Error getStoresById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getStoresById method");
		}
	}
	
	@Override
	@Cacheable(cacheName = "getStoreById")
	public StoreDTO getStoreById(String noCia, String centro,
			UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getStores method");
		try {
			ArincdKey key = new ArincdKey();
			key.setCentro(centro);
			key.setNoCia(noCia);
			return STORE_TRANSLATOR_UTIL.reverseTranslateObject(arincdMapper
					.selectByPrimaryKey(key));
		} catch (Exception e) {
			logger.error("Error getStores method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getStores method");
		}
	}

	@Override
	@Cacheable(cacheName = "getAllBusinessUnit")
	public List<BusinessUnitDTO> getBusinessUnits(UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getBusinessUnits method");
		try {
			ArunnegExample example = new ArunnegExample();
			ArunnegExample.Criteria c = example.createCriteria();
			HashMap<String, ConstantDTO> allConstants = constantService
					.getAllConstants();
			ConstantDTO company = allConstants.get(
					ConstantsIdentifierEnum.DEFAULT_COMPANY);
			String companyId = ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue();
			if (company != null)
				companyId = company.getValue();			
			
			c.andNoCiaEqualTo(companyId);
			c.andActivoEqualTo("S");
			example.setOrderByClause("NOMBRE asc");
			return BUSINESS_UNIT_TRANSLATOR_UTIL
					.reverseTranslateObjectList(arunnegMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getBusinessUnits method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getBusinessUnits method");
		}
	}

	@Override
	@Cacheable(cacheName = "getTracingStatusByType")
	public List<TracingStatusDTO> getTracingStatusByType(
			TracingStatusEnum tracingStatus, UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getTracingStatusByType method");
		try {
			CpEstadoSeguimientoExample example = new CpEstadoSeguimientoExample();
			example.createCriteria().andTipoEqualTo(tracingStatus.getValue());
			return TRACING_STATUS_TRANSLATOR_UTIL
					.reverseTranslateObjectList(estadoSeguimientoMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getTracingStatusByType method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getTracingStatusByType method");
		}
	}

	@Override
	@Cacheable(cacheName = "getTracingStatusById")
	public TracingStatusDTO getTracingStatusById(
			TracingStatusDTO tracingStatus, UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getTracingStatusById method");
		try {
			return TRACING_STATUS_TRANSLATOR_UTIL
					.reverseTranslateObject(estadoSeguimientoMapper
							.selectByPrimaryKey(tracingStatus.getStatusId()));

		} catch (Exception e) {
			logger.error("Error getTracingStatusById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getTracingStatusById method");
		}
	}

	@Override
	@Cacheable(cacheName = "getProvinces")
	public List<ProvinceDTO> getProvinces() throws BusinessException {
		logger.debug("Starting getProvinces");
		try {
			HashMap<String, ConstantDTO> map = constantService
					.getAllConstants();
			String countryId = map.get(
					ConstantsIdentifierEnum.DEFAULT_COUNTRY.getValue())
					.getValue();
			ArgeproExample example = new ArgeproExample();
			example.createCriteria().andPaisEqualTo(countryId);
			List<Argepro> arprovinces = provincesMapper
					.selectByExample(example);
			List<ProvinceDTO> provinces = new ArrayList<ProvinceDTO>();

			for (Argepro arprovince : arprovinces) {
				ProvinceDTO province = new ProvinceDTO();
				province.setId(arprovince.getProvincia());
				province.setName(arprovince.getDescripcion());
				province.setCountryId(arprovince.getPais());
				provinces.add(province);
			}
			Collections.sort(provinces, new ProvinceComparator());
			return provinces;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("Error getting provinces",
					ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
		} finally {
			logger.debug("Ending getProvinces");
		}
	}

	@Override
	@Cacheable(cacheName = "getCities")
	public List<CityDTO> getCities(String provinceId) throws BusinessException {
		logger.debug("Starting getCities");
		try {
			HashMap<String, ConstantDTO> map = constantService
					.getAllConstants();
			String countryId = map.get(
					ConstantsIdentifierEnum.DEFAULT_COUNTRY.getValue())
					.getValue();
			ArgecanExample example = new ArgecanExample();
			Criteria criteria = example.createCriteria();
			criteria.andPaisEqualTo(countryId);
			criteria.andProvinciaEqualTo(provinceId);
			List<Argecan> arcities = citiesMapper.selectByExample(example);
			List<CityDTO> cities = new ArrayList<CityDTO>();

			for (Argecan arcity : arcities) {
				CityDTO city = new CityDTO();
				city.setId(arcity.getCanton());
				city.setName(arcity.getDescripcion());
				city.setCountryId(arcity.getPais());
				city.setProvinceId(arcity.getProvincia());
				cities.add(city);
			}
			Collections.sort(cities, new CityComparator());
			return cities;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("Error getting cities",
					ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
		} finally {
			logger.debug("Ending getCities");
		}
	}

	@Override
	@Cacheable(cacheName = "getDocumentTypes")
	public List<DocumentTypeDTO> getDocumentTypes() throws BusinessException {
		logger.debug("Starting getDocumentTypes");
		try {
			ArgetidExample example = new ArgetidExample();
			List<Argetid> ardocumenttypes = documentTypesMapper
					.selectByExample(example);
			List<DocumentTypeDTO> documentTypes = new ArrayList<DocumentTypeDTO>();

			for (Argetid ardocumentType : ardocumenttypes) {
				DocumentTypeDTO documentType = new DocumentTypeDTO();
				documentType.setId(ardocumentType.getCodigo());
				documentType.setName(ardocumentType.getEtiqueta());
				documentTypes.add(documentType);
			}
			Collections.sort(documentTypes, new DocumentTypeComparator());
			return documentTypes;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("Error getting document types",
					ErrorCodesEnum.GENERIC_BUSINESS_EXCEPTION);
		} finally {
			logger.debug("Ending getDocumentTypes");
		}
	}

	@Override
	public BusinessUnitDTO getBusinessUnitById(String codigo, String noCia,
			UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getStores method");
		try {
			ArunnegKey key = new ArunnegKey();
			key.setCodigo(codigo);
			key.setNoCia(noCia);
			return BUSINESS_UNIT_TRANSLATOR_UTIL
					.reverseTranslateObject(arunnegMapper
							.selectByPrimaryKey(key));
		} catch (Exception e) {
			logger.error("Error getStores method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getStores method");
		}
	}

	@Override
	@Cacheable(cacheName = "getBrands")
	public List<MarcasDTO> getBrands(UserInfoDTO userInfo)
			throws BusinessException {
		logger.debug("Staring getBrands method");
		try {
			MarcasExample example = new MarcasExample();
			MarcasExample.Criteria c = example.createCriteria();
			HashMap<String, ConstantDTO> allConstants = constantService
					.getAllConstants();
			ConstantDTO company = allConstants.get(
					ConstantsIdentifierEnum.DEFAULT_COMPANY);
			String companyId = ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue();
			if (company != null)
				companyId = company.getValue();
			
			c.andNoCiaEqualTo(companyId);
			example.setOrderByClause("DESCRIPCION asc");
			return BRANDS_TRANSLATOR_UTIL
					.reverseTranslateObjectList(marcasMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getBrands method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getBrands method");
		}
	}

	@Override
	@Cacheable(cacheName = "getArticlesByBrand")
	public List<ArticleDTO> getArticlesByBrand(UserInfoDTO userInfo,
			String brandId) throws BusinessException {
		try {
			logger.debug("Staring getArticlesByBrand method");
			ArindaExample example = new ArindaExample();
			ArindaExample.Criteria c = example.createCriteria();
			HashMap<String, ConstantDTO> allConstants = constantService
					.getAllConstants();
			ConstantDTO company = allConstants.get(
					ConstantsIdentifierEnum.DEFAULT_COMPANY);
			String companyId = ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue();
			if (company != null)
				companyId = company.getValue();			
			c.andNoCiaEqualTo(companyId);
			if (brandId != null && brandId.length() > 0)
				c.andMarcaEqualTo(brandId);
			
			example.setOrderByClause("DESCRIPCION asc");
			return ARTICLE_TRANSLATOR_UTIL
					.reverseTranslateObjectList(arindaMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getArticlesByBrand method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getArticlesByBrand method");
		}
	}

	@Override
	public ArticleDTO getArticlesById(UserInfoDTO userInfo,
			String noCia, String brandId, String articleId) throws BusinessException {
		try {
			logger.debug("Staring getArticlesByBrand method");
			ArindaExample example = new ArindaExample();
			ArindaExample.Criteria c = example.createCriteria();
			c.andNoCiaEqualTo(noCia);
			c.andMarcaEqualTo(brandId);
			c.andNoArtiEqualTo(articleId);
			return ARTICLE_TRANSLATOR_UTIL
					.reverseTranslateObjectList(arindaMapper
							.selectByExample(example)).get(0);

		} catch (Exception e) {
			logger.error("Error getArticlesByBrand method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getArticlesByBrand method");
		}
	}

	
	@Override
	@Cacheable(cacheName = "getAllPdUsuario")
	public List<PdUsuarioDTO> getAllPdUsuario(UserInfoDTO userInfo)
			throws BusinessException {
		try {
			logger.debug("Staring getAllPdUsuario method");
			PdUsuarioExample example = new PdUsuarioExample();
			PdUsuarioExample.Criteria c = example.createCriteria();
			HashMap<String, ConstantDTO> allConstants = constantService
					.getAllConstants();
			ConstantDTO company = allConstants.get(
					ConstantsIdentifierEnum.DEFAULT_COMPANY);
			String companyId = ConstantsIdentifierEnum.DEFAULT_COMPANY.getDefaultValue();
			if (company != null)
				companyId = company.getValue();			
			c.andNoCiaEqualTo(companyId);
			c.andActivoEqualTo("1");
			example.setOrderByClause("DESCRIPCION asc");
			return PDUSUARIO_TRANSLATOR_UTIL
					.reverseTranslateObjectList(pdUsuarioMapper
							.selectByExample(example));

		} catch (Exception e) {
			logger.error("Error getAllPdUsuario method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getArticlesByBrand method");
		}
	}

	@Override
	public MarcasDTO getBrandById(String noCia, String codigo,
			UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getBrandById method");
		try {
			MarcasExample example = new MarcasExample();
			MarcasExample.Criteria c = example.createCriteria();
			c.andNoCiaEqualTo(noCia);
			c.andCodigoEqualTo(codigo);
			return BRANDS_TRANSLATOR_UTIL
					.reverseTranslateObjectList(marcasMapper
							.selectByExample(example)).get(0);

		} catch (Exception e) {
			logger.error("Error getBrandById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			logger.debug("Ending getBrandById method");
		}
	}

}
