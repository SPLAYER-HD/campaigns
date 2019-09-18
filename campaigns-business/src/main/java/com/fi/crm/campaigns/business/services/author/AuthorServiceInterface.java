package com.fi.crm.campaigns.business.services.author;

import java.util.List;

import com.fi.crm.campaigns.common.dto.AuthorDTO;
import com.fi.crm.campaigns.common.dto.UserInfoDTO;
import com.fi.crm.campaigns.infrastructure.exception.BusinessException;



public interface AuthorServiceInterface {
	
	public List<AuthorDTO> getAllAuthors(UserInfoDTO userInfo) throws BusinessException;
	public List<AuthorDTO> getAllAuthorsByStatus(UserInfoDTO userInfo, String status) throws BusinessException;
	public AuthorDTO getAuthorById(UserInfoDTO userInfo, Integer authorId ) throws BusinessException;
	public AuthorDTO createAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException;
	public AuthorDTO updateAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException;
	public void deleteAuthor(AuthorDTO author, UserInfoDTO userInfo) throws BusinessException;

}
