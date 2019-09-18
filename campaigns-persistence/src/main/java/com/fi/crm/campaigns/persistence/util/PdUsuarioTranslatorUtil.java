package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.PdUsuarioDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.PdUsuario;

public class PdUsuarioTranslatorUtil extends
		DTOTranslationUtil<PdUsuarioDTO, PdUsuario> {

	@Override
	public PdUsuario translateObject(PdUsuarioDTO dtoObject)
			throws TechnicalException {
		PdUsuario pdUsuario = new PdUsuario();
		pdUsuario.setCodUsuario(dtoObject.getCodUsuario());
		pdUsuario.setContrasena(dtoObject.getContrasena());
		pdUsuario.setRol(dtoObject.getRol());
		pdUsuario.setDescripcion(dtoObject.getDescripcion());
		pdUsuario.setNoGerente(dtoObject.getNoGerente());
		pdUsuario.setActivo(dtoObject.getActivo());
		pdUsuario.setCdPerfilPerUsu(dtoObject.getCdPerfilPerUsu());
		pdUsuario.setTxCorreoUsu(dtoObject.getTxCorreoUsu());
		pdUsuario.setNoCia(dtoObject.getNoCia());
		pdUsuario.setId(dtoObject.getId());
		return pdUsuario;
	}

	@Override
	public List<PdUsuario> translateList(List<PdUsuarioDTO> dtoObjectList)
			throws TechnicalException {
		List<PdUsuario> pdUsuarioList = new ArrayList<PdUsuario>();
		for (PdUsuarioDTO pdUsuarioDTO : dtoObjectList) {
			pdUsuarioList.add(translateObject(pdUsuarioDTO));
		}
		return pdUsuarioList;
	}

	@Override
	public PdUsuarioDTO reverseTranslateObject(PdUsuario pdUsuario)
			throws TechnicalException {
		PdUsuarioDTO dtoObject = new PdUsuarioDTO();
		dtoObject.setCodUsuario(pdUsuario.getCodUsuario());
		dtoObject.setContrasena(pdUsuario.getContrasena());
		dtoObject.setRol(pdUsuario.getRol());
		dtoObject.setDescripcion(pdUsuario.getDescripcion());
		dtoObject.setNoGerente(pdUsuario.getNoGerente());
		dtoObject.setActivo(pdUsuario.getActivo());
		dtoObject.setCdPerfilPerUsu(pdUsuario.getCdPerfilPerUsu());
		dtoObject.setTxCorreoUsu(pdUsuario.getTxCorreoUsu());
		dtoObject.setNoCia(pdUsuario.getNoCia());
		dtoObject.setId(pdUsuario.getId());

		return dtoObject;
	}

	@Override
	public List<PdUsuarioDTO> reverseTranslateObjectList(
			List<PdUsuario> modelObjectList) throws TechnicalException {
		List<PdUsuarioDTO> pdUsuarioList = new ArrayList<PdUsuarioDTO>();
		for (PdUsuario pdUsuario : modelObjectList){
			pdUsuarioList.add(reverseTranslateObject(pdUsuario));
		}
		return pdUsuarioList;
	}

}
