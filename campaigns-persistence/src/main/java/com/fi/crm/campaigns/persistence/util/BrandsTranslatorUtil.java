package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.MarcasDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.Marcas;

public class BrandsTranslatorUtil extends
		DTOTranslationUtil<MarcasDTO, Marcas> {

	@Override
	public Marcas translateObject(MarcasDTO marcasDTO)
			throws TechnicalException {
		Marcas marcas = new Marcas();
		marcas.setDescripcion(marcasDTO.getDescripcion());
		marcas.setTstamp(marcasDTO.getTstamp());
		marcas.setIndDemanda(marcasDTO.getIndDemanda());
		marcas.setNoCia(marcasDTO.getNoCia());
		marcas.setCodigo(marcasDTO.getCodigo());
		return marcas;
	}

	@Override
	public List<Marcas> translateList(List<MarcasDTO> dtoObjectList)
			throws TechnicalException {
		List<Marcas> marcasList = new ArrayList<Marcas>();
		for (MarcasDTO marcasDTO : dtoObjectList){
			marcasList.add(translateObject(marcasDTO));
		}
		return marcasList;
	}

	@Override
	public MarcasDTO reverseTranslateObject(Marcas marcas)
			throws TechnicalException {
		MarcasDTO marcasDTO = new MarcasDTO();
		marcasDTO.setDescripcion(marcas.getDescripcion());
		marcasDTO.setTstamp(marcas.getTstamp());
		marcasDTO.setIndDemanda(marcas.getIndDemanda());
		marcasDTO.setNoCia(marcas.getNoCia());
		marcasDTO.setCodigo(marcas.getCodigo());

		return marcasDTO;
	}

	@Override
	public List<MarcasDTO> reverseTranslateObjectList(
			List<Marcas> modelObjectList) throws TechnicalException {
		List<MarcasDTO> marcasList = new ArrayList<MarcasDTO>();
		for (Marcas marcas : modelObjectList) {
			marcasList.add(reverseTranslateObject(marcas));
		}
		return marcasList;
	}

}
