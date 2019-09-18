package com.fi.crm.campaigns.business.services.parametrization;

import java.util.List;

import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.common.dto.BusinessUnitDTO;
import com.fi.crm.campaigns.common.dto.CityDTO;
import com.fi.crm.campaigns.common.dto.DocumentTypeDTO;
import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.common.dto.PdUsuarioDTO;
import com.fi.crm.campaigns.common.dto.ProvinceDTO;
import com.fi.crm.campaigns.common.dto.StoreDTO;
import com.fi.crm.campaigns.common.dto.TracingStatusDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.TracingStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;

public interface ParametrizationServiceInterface {
	
	public List<StoreDTO> getStores(UserInfoDTO userInfo) throws BusinessException;
	public List<StoreDTO> getStoresByBusinessUnit(UserInfoDTO userInfo, String businessUnitId) throws BusinessException;
	public StoreDTO getStoresById(UserInfoDTO userInfo,String noCia, String businessUnitId, String codigo) throws BusinessException;
	public StoreDTO getStoreById(String noCia, String centro, UserInfoDTO userInfo) throws BusinessException;
	public List<BusinessUnitDTO> getBusinessUnits(UserInfoDTO userInfo) throws BusinessException;
	public BusinessUnitDTO getBusinessUnitById(String codigo, String noCia, UserInfoDTO userInfo) throws BusinessException;
	public List<TracingStatusDTO> getTracingStatusByType(TracingStatusEnum tracingStatus,  UserInfoDTO userInfo) throws BusinessException;
	public List<DocumentTypeDTO> getDocumentTypes()	throws BusinessException;
	public List<ProvinceDTO> getProvinces() throws BusinessException;
	public List<CityDTO> getCities(String provinceId) throws BusinessException;
	public TracingStatusDTO getTracingStatusById(TracingStatusDTO tracingStatus, UserInfoDTO userInfo) throws BusinessException;
	public List<MarcasDTO> getBrands (UserInfoDTO userInfo) throws BusinessException;
	public MarcasDTO getBrandById (String noCia, String codigo, UserInfoDTO userInfo) throws BusinessException;
	public List<ArticleDTO> getArticlesByBrand (UserInfoDTO userInfo, String brandId) throws BusinessException;
	public ArticleDTO getArticlesById(UserInfoDTO userInfo,String noCia, String brandId, String articleId) throws BusinessException;
	public List<PdUsuarioDTO> getAllPdUsuario (UserInfoDTO userInfo) throws BusinessException;
	
}
