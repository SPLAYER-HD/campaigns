package com.fi.crm.campaigns.business.services.author;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fi.crm.campaigns.business.services.audit.AuditServiceInterface;
import com.fi.crm.campaigns.business.util.AuditUtil;
import com.fi.crm.campaigns.common.dto.AuditDTO;
import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.common.enums.AuditEventTypeEnum;
import com.fi.crm.campaigns.common.enums.OperationStatusEnum;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;
import com.fi.crm.campaigns.infrastructure.exception.ErrorCodesEnum;
import com.fi.crm.campaigns.persistence.mappers.CpAutorMapper;
import com.fi.crm.campaigns.persistence.mappers.SequencesCustomMapper;
import com.fi.crm.campaigns.persistence.model.CpAutor;
import com.fi.crm.campaigns.persistence.model.CpAutorExample;
import com.fi.crm.campaigns.persistence.util.AuthorTranslatorUtil;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class AuthorService implements AuthorServiceInterface {

	/**
	 * static logger for class SecurityService
	 */
	private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
	
	@Autowired
	private CpAutorMapper autorMapper;
	@Autowired
	private SequencesCustomMapper sequencesCustomMapper;
	@Autowired
	private AuditServiceInterface auditService;
	
	//Translator
	private static final AuthorTranslatorUtil AUTHOR_TRANSLATOR_UTIL = new AuthorTranslatorUtil();
	
	@Override
	@Cacheable(cacheName = "getAllAuthors")
	public List<AuthorDTO> getAllAuthors(UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring getAuthors method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_AUTHORS, userInfo);
		try {
			CpAutorExample example = new CpAutorExample();
			example.createCriteria().getAllCriteria();
			List<CpAutor> autors = autorMapper.selectByExample(example);
			
			audit.setStatus(OperationStatusEnum.OK);
			
			return AUTHOR_TRANSLATOR_UTIL.reverseTranslateObjectList(autors);

		} catch (Exception e) {
			logger.error("Error getAuthors method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getAuthors method");
		}
	}

	@Override
	@TriggersRemove(cacheName = "getAllAuthors", removeAll = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AuthorDTO createAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring createAuthor method");

		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.CREATE_AUTHOR, userInfo);
		try {
			Integer id = sequencesCustomMapper.getAuthorSequence();
			author.setAuthorId(id);
			CpAutor autor = AUTHOR_TRANSLATOR_UTIL.translateObject(author);
			autorMapper.insert(autor);
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(autor.getAutorId());
			return AUTHOR_TRANSLATOR_UTIL.reverseTranslateObject(autor);
		} catch (Exception e) {
			logger.error("Error createAuthor method ", e);
			throw new BusinessException(e, ErrorCodesEnum.INSERTION_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending createAuthor method");
		}
	}

	@Override
	@TriggersRemove(cacheName = "getAllAuthors", removeAll = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AuthorDTO updateAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring updateAuthor method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.UPDATE_AUTHOR, userInfo);
		try {
			
			CpAutor autor = AUTHOR_TRANSLATOR_UTIL.translateObject(author);
			autorMapper.updateByPrimaryKey(autor);
			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(author.getAuthorId());
			return AUTHOR_TRANSLATOR_UTIL.reverseTranslateObject(autor);
			
		} catch (Exception e) {
			logger.error("Error updateAuthor method ", e);
			throw new BusinessException(e, ErrorCodesEnum.UPDATE_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending updateAuthor method");
		}
	}

	@Override
	@TriggersRemove(cacheName = "getAllAuthors", removeAll = true)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException {
		logger.debug("Staring deleteAuthor method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.DELETE_AUTHOR, userInfo);
		try {
			autorMapper.deleteByPrimaryKey(author.getAuthorId());

			audit.setStatus(OperationStatusEnum.OK);
			audit.setEntityId(author.getAuthorId());
		} catch (Exception e) {
			logger.error("Error deleteAuthor method ", e);
			throw new BusinessException(e, ErrorCodesEnum.DELETE_NOT_POSSIBLE_BY_REFERENCE);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending deleteAuthor method");
		}
	}

	@Override
	@Cacheable(cacheName = "getAllAuthors")
	public List<AuthorDTO> getAllAuthorsByStatus(UserInfoDTO userInfo,
			String status) throws BusinessException {
		logger.debug("Staring getAllAuthorsByStatus method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_AUTHORS, userInfo);
		try {
			CpAutorExample example = new CpAutorExample();
			CpAutorExample.Criteria c = example.createCriteria();
			c.andStatusEqualTo(status);
			List<CpAutor> autors = autorMapper.selectByExample(example);
			
			audit.setStatus(OperationStatusEnum.OK);
			
			return AUTHOR_TRANSLATOR_UTIL.reverseTranslateObjectList(autors);

		} catch (Exception e) {
			logger.error("Error getAllAuthorsByStatus method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getAllAuthorsByStatus method");
		}

	}

	@Override
	public AuthorDTO getAuthorById(UserInfoDTO userInfo, Integer authorId)
			throws BusinessException {
		logger.debug("Staring getAuthorById method");
		AuditDTO audit = AuditUtil.buildBasicAudit(AuditEventTypeEnum.GET_AUTHORS, userInfo);
		try {
			CpAutorExample example = new CpAutorExample();
			CpAutorExample.Criteria c = example.createCriteria();
			c.andAutorIdEqualTo(authorId);
			List<CpAutor> autors = autorMapper.selectByExample(example);
			
			audit.setStatus(OperationStatusEnum.OK);
			
			return AUTHOR_TRANSLATOR_UTIL.reverseTranslateObjectList(autors).get(0);

		} catch (Exception e) {
			logger.error("Error getAuthorById method ", e);
			throw new BusinessException(e, ErrorCodesEnum.SELECT_ERROR);
		} finally {
			auditService.registerAudit(audit);
			logger.debug("Ending getAuthorById method");
		}
	}

}
