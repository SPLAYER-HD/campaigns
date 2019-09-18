package com.fi.crm.campaigns.persistence.util;

import java.util.ArrayList;
import java.util.List;

import com.fi.crm.campaigns.common.dto.ArticleDTO;
import com.fi.crm.campaigns.infrastructure.exception.TechnicalException;
import com.fi.crm.campaigns.persistence.model.Arinda;

public class ArticleTranslatorUtil extends
		DTOTranslationUtil<ArticleDTO, Arinda> {

	@Override
	public Arinda translateObject(ArticleDTO articleDTO)
			throws TechnicalException {
		Arinda modelObject = new Arinda(); 
		modelObject.setNoCia(articleDTO.getNoCia());
		modelObject.setNoArti(articleDTO.getNoArti());
		modelObject.setClase(articleDTO.getClase());
		modelObject.setCategoria(articleDTO.getCategoria());
		modelObject.setDescripcion(articleDTO.getDescripcion());
		modelObject.setUnidad(articleDTO.getUnidad());
		modelObject.setPeso(articleDTO.getPeso());
		modelObject.setTiempoRep(articleDTO.getTiempoRep());
		modelObject.setCodigo(articleDTO.getCodigo());
		modelObject.setTransito(articleDTO.getTransito());
		modelObject.setUpc(articleDTO.getUpc());
		modelObject.setMarca(articleDTO.getMarca());
		modelObject.setGrupo(articleDTO.getGrupo());
		modelObject.setMaximo(articleDTO.getMaximo());
		modelObject.setMinimo(articleDTO.getMinimo());
		modelObject.setReorden(articleDTO.getReorden());
		modelObject.setImpVen(articleDTO.getImpVen());
		modelObject.setFabricacion(articleDTO.getFabricacion());
		modelObject.setPromoapp(articleDTO.getPromoapp());
		modelObject.setDiscExc(articleDTO.getDiscExc());
		modelObject.setConttype(articleDTO.getConttype());
		modelObject.setIndLote(articleDTO.getIndLote());
		modelObject.setIndActivo(articleDTO.getIndActivo());
		modelObject.setGraciaVencer(articleDTO.getGraciaVencer());
		modelObject.setCostoNuevo(articleDTO.getCostoNuevo());
		modelObject.setCostoEstandar(articleDTO.getCostoEstandar());
		modelObject.setAplicaImpuesto(articleDTO.getAplicaImpuesto());
		modelObject.setNombreLargo(articleDTO.getNombreLargo());
		modelObject.setIndImprimeGarantia(articleDTO.getIndImprimeGarantia());
		modelObject.setIndRequiereSerie(articleDTO.getIndRequiereSerie());
		modelObject.setIndEsParaOferta(articleDTO.getIndEsParaOferta());
		modelObject.setGarantia(articleDTO.getGarantia());
		modelObject.setIndImporta(articleDTO.getIndImporta());
		modelObject.setCubicaje(articleDTO.getCubicaje());
		modelObject.setBaseTarima(articleDTO.getBaseTarima());
		modelObject.setAltoTarima(articleDTO.getAltoTarima());
		modelObject.setTipoAlmacen(articleDTO.getTipoAlmacen());
		modelObject.setCodigoArancel(articleDTO.getCodigoArancel());
		modelObject.setDescripcionIngles(articleDTO.getDescripcionIngles());
		modelObject.setTraduccionDescri(articleDTO.getTraduccionDescri());
		modelObject.setTipoProducto(articleDTO.getTipoProducto());
		modelObject.setIndPermisoSalud(articleDTO.getIndPermisoSalud());
		modelObject.setTipoLiquida(articleDTO.getTipoLiquida());
		modelObject.setDiasUtil(articleDTO.getDiasUtil());
		modelObject.setDescripcionAlterna(articleDTO.getDescripcionAlterna());
		modelObject.setTstamp(articleDTO.getTstamp());
		modelObject.setIndEtiqueta(articleDTO.getIndEtiqueta());
		modelObject.setEtiqueta(articleDTO.getEtiqueta());
		modelObject.setAceptaDevolucion(articleDTO.getAceptaDevolucion());
		modelObject.setTipoFactCombo(articleDTO.getTipoFactCombo());
		modelObject.setGrReabastecimiento(articleDTO.getGrReabastecimiento());
		modelObject.setClasificaAbc(articleDTO.getClasificaAbc());
		modelObject.setCostoMercado(articleDTO.getCostoMercado());
		modelObject.setCostoDol(articleDTO.getCostoDol());
		modelObject.setCostoNacDol(articleDTO.getCostoNacDol());
		modelObject.setSaldoUltComp(articleDTO.getSaldoUltComp());
		modelObject.setPedTransito(articleDTO.getPedTransito());
		modelObject.setMargenUtilidad(articleDTO.getMargenUtilidad());
		modelObject.setEsCombo(articleDTO.getEsCombo());
		modelObject.setIndPrecioFijoDol(articleDTO.getIndPrecioFijoDol());
		modelObject.setIndPrecioFijo(articleDTO.getIndPrecioFijo());
		modelObject.setIndLoteWms(articleDTO.getIndLoteWms());
		modelObject.setFactor(articleDTO.getFactor());
		modelObject.setIndUbicacion(articleDTO.getIndUbicacion());
		modelObject.setIndCombo(articleDTO.getIndCombo());
		modelObject.setCodDistribuidor(articleDTO.getCodDistribuidor());
		modelObject.setNumeroAutomatico(articleDTO.getNumeroAutomatico());
		modelObject.setIndConteoWms(articleDTO.getIndConteoWms());
		modelObject.setIndConsignacion(articleDTO.getIndConsignacion());
		modelObject.setCodSubgrupo(articleDTO.getCodSubgrupo());
		modelObject.setNoGerente(articleDTO.getNoGerente());
		modelObject.setCodCaracteristica(articleDTO.getCodCaracteristica());
		modelObject.setCodClase(articleDTO.getCodClase());
		modelObject.setRegistroSanitario(articleDTO.getRegistroSanitario());
		modelObject.setNombre(articleDTO.getNombre());
		modelObject.setUnidadesEmpaque(articleDTO.getUnidadesEmpaque());
		modelObject.setKiloVolumen(articleDTO.getKiloVolumen());
		modelObject.setVolumen(articleDTO.getVolumen());
		modelObject.setIndSeriales(articleDTO.getIndSeriales());
		modelObject.setIndGarantias(articleDTO.getIndGarantias());
		modelObject.setPrecioFobDol(articleDTO.getPrecioFobDol());
		modelObject.setPrecioFob(articleDTO.getPrecioFob());
		modelObject.setNoProve(articleDTO.getNoProve());
		modelObject.setObservacion(articleDTO.getObservacion());
		modelObject.setPromocion(articleDTO.getPromocion());
		modelObject.setComprador(articleDTO.getComprador());
		modelObject.setCompradorAlterno(articleDTO.getCompradorAlterno());
		modelObject.setCodSubmarca(articleDTO.getCodSubmarca());
		modelObject.setAbcCia(articleDTO.getAbcCia());
		modelObject.setAbcSublinea(articleDTO.getAbcSublinea());
		modelObject.setAbcMarca(articleDTO.getAbcMarca());
		modelObject.setPagCatalogo(articleDTO.getPagCatalogo());
		modelObject.setMonedaPreciobase(articleDTO.getMonedaPreciobase());
		modelObject.setMonedaPrecionuevo(articleDTO.getMonedaPrecionuevo());
		modelObject.setCodTipo(articleDTO.getCodTipo());
		modelObject.setCcodGrupo(articleDTO.getCcodGrupo());
		modelObject.setCodGrupo(articleDTO.getCodGrupo());
		modelObject.setFechaRegistro(articleDTO.getFechaRegistro());
		modelObject.setFechaCreacion(articleDTO.getFechaCreacion());
		modelObject.setFechaIngreso(articleDTO.getFechaIngreso());
		modelObject.setFechaUltCom(articleDTO.getFechaUltCom());
		modelObject.setPreciobase(articleDTO.getPreciobase());
		modelObject.setPrecioCd(articleDTO.getPrecioCd());
		modelObject.setStatusCd(articleDTO.getStatusCd());
		modelObject.setIndTop30(articleDTO.getIndTop30());
		modelObject.setValorSerialDistribucion(articleDTO.getValorSerialDistribucion());
		modelObject.setPrecionuevo(articleDTO.getPrecionuevo());
		modelObject.setPrecioLandedDol(articleDTO.getPrecioLandedDol());
		modelObject.setPrecioLanded(articleDTO.getPrecioLanded());
		modelObject.setEnvio(articleDTO.getEnvio());
		modelObject.setAjustaInvPlv(articleDTO.getAjustaInvPlv());
		modelObject.setFechaLanzam(articleDTO.getFechaLanzam());
		modelObject.setPedidoOptimo(articleDTO.getPedidoOptimo());
		modelObject.setDescColeccion(articleDTO.getDescColeccion());
		modelObject.setIndProxd(articleDTO.getIndProxd());
		modelObject.setUnPaquete(articleDTO.getUnPaquete());
		modelObject.setDescripcionTemporada(articleDTO.getDescripcionTemporada());
		modelObject.setTextura(articleDTO.getTextura());
		modelObject.setTexturaFormula(articleDTO.getTexturaFormula());
		modelObject.setMaterial(articleDTO.getMaterial());
		modelObject.setColores(articleDTO.getColores());
		modelObject.setTallas(articleDTO.getTallas());
		modelObject.setIndEvacuacion(articleDTO.getIndEvacuacion());
		modelObject.setCodRet(articleDTO.getCodRet());
		modelObject.setIndRet(articleDTO.getIndRet());
		modelObject.setIndUnoXUnoRecibo(articleDTO.getIndUnoXUnoRecibo());
		modelObject.setCodVenta(articleDTO.getCodVenta());
		modelObject.setArtEspecial(articleDTO.getArtEspecial());
		return modelObject;
	}

	@Override
	public List<Arinda> translateList(List<ArticleDTO> dtoObjectList)
			throws TechnicalException {

		List<Arinda> arindaList = new ArrayList<Arinda>();
		for (ArticleDTO articleDTO : dtoObjectList){
			arindaList.add(translateObject(articleDTO));
		}
		return arindaList;
	}

	@Override
	public ArticleDTO reverseTranslateObject(Arinda modelObject)
			throws TechnicalException {
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setNoCia(modelObject.getNoCia());
		articleDTO.setNoArti(modelObject.getNoArti());
		articleDTO.setClase(modelObject.getClase());
		articleDTO.setCategoria(modelObject.getCategoria());
		articleDTO.setDescripcion(modelObject.getDescripcion());
		articleDTO.setUnidad(modelObject.getUnidad());
		articleDTO.setPeso(modelObject.getPeso());
		articleDTO.setTiempoRep(modelObject.getTiempoRep());
		articleDTO.setCodigo(modelObject.getCodigo());
		articleDTO.setTransito(modelObject.getTransito());
		articleDTO.setUpc(modelObject.getUpc());
		articleDTO.setMarca(modelObject.getMarca());
		articleDTO.setGrupo(modelObject.getGrupo());
		articleDTO.setMaximo(modelObject.getMaximo());
		articleDTO.setMinimo(modelObject.getMinimo());
		articleDTO.setReorden(modelObject.getReorden());
		articleDTO.setImpVen(modelObject.getImpVen());
		articleDTO.setFabricacion(modelObject.getFabricacion());
		articleDTO.setPromoapp(modelObject.getPromoapp());
		articleDTO.setDiscExc(modelObject.getDiscExc());
		articleDTO.setConttype(modelObject.getConttype());
		articleDTO.setIndLote(modelObject.getIndLote());
		articleDTO.setIndActivo(modelObject.getIndActivo());
		articleDTO.setGraciaVencer(modelObject.getGraciaVencer());
		articleDTO.setCostoNuevo(modelObject.getCostoNuevo());
		articleDTO.setCostoEstandar(modelObject.getCostoEstandar());
		articleDTO.setAplicaImpuesto(modelObject.getAplicaImpuesto());
		articleDTO.setNombreLargo(modelObject.getNombreLargo());
		articleDTO.setIndImprimeGarantia(modelObject.getIndImprimeGarantia());
		articleDTO.setIndRequiereSerie(modelObject.getIndRequiereSerie());
		articleDTO.setIndEsParaOferta(modelObject.getIndEsParaOferta());
		articleDTO.setGarantia(modelObject.getGarantia());
		articleDTO.setIndImporta(modelObject.getIndImporta());
		articleDTO.setCubicaje(modelObject.getCubicaje());
		articleDTO.setBaseTarima(modelObject.getBaseTarima());
		articleDTO.setAltoTarima(modelObject.getAltoTarima());
		articleDTO.setTipoAlmacen(modelObject.getTipoAlmacen());
		articleDTO.setCodigoArancel(modelObject.getCodigoArancel());
		articleDTO.setDescripcionIngles(modelObject.getDescripcionIngles());
		articleDTO.setTraduccionDescri(modelObject.getTraduccionDescri());
		articleDTO.setTipoProducto(modelObject.getTipoProducto());
		articleDTO.setIndPermisoSalud(modelObject.getIndPermisoSalud());
		articleDTO.setTipoLiquida(modelObject.getTipoLiquida());
		articleDTO.setDiasUtil(modelObject.getDiasUtil());
		articleDTO.setDescripcionAlterna(modelObject.getDescripcionAlterna());
		articleDTO.setTstamp(modelObject.getTstamp());
		articleDTO.setIndEtiqueta(modelObject.getIndEtiqueta());
		articleDTO.setEtiqueta(modelObject.getEtiqueta());
		articleDTO.setAceptaDevolucion(modelObject.getAceptaDevolucion());
		articleDTO.setTipoFactCombo(modelObject.getTipoFactCombo());
		articleDTO.setGrReabastecimiento(modelObject.getGrReabastecimiento());
		articleDTO.setClasificaAbc(modelObject.getClasificaAbc());
		articleDTO.setCostoMercado(modelObject.getCostoMercado());
		articleDTO.setCostoDol(modelObject.getCostoDol());
		articleDTO.setCostoNacDol(modelObject.getCostoNacDol());
		articleDTO.setSaldoUltComp(modelObject.getSaldoUltComp());
		articleDTO.setPedTransito(modelObject.getPedTransito());
		articleDTO.setMargenUtilidad(modelObject.getMargenUtilidad());
		articleDTO.setEsCombo(modelObject.getEsCombo());
		articleDTO.setIndPrecioFijoDol(modelObject.getIndPrecioFijoDol());
		articleDTO.setIndPrecioFijo(modelObject.getIndPrecioFijo());
		articleDTO.setIndLoteWms(modelObject.getIndLoteWms());
		articleDTO.setFactor(modelObject.getFactor());
		articleDTO.setIndUbicacion(modelObject.getIndUbicacion());
		articleDTO.setIndCombo(modelObject.getIndCombo());
		articleDTO.setCodDistribuidor(modelObject.getCodDistribuidor());
		articleDTO.setNumeroAutomatico(modelObject.getNumeroAutomatico());
		articleDTO.setIndConteoWms(modelObject.getIndConteoWms());
		articleDTO.setIndConsignacion(modelObject.getIndConsignacion());
		articleDTO.setCodSubgrupo(modelObject.getCodSubgrupo());
		articleDTO.setNoGerente(modelObject.getNoGerente());
		articleDTO.setCodCaracteristica(modelObject.getCodCaracteristica());
		articleDTO.setCodClase(modelObject.getCodClase());
		articleDTO.setRegistroSanitario(modelObject.getRegistroSanitario());
		articleDTO.setNombre(modelObject.getNombre());
		articleDTO.setUnidadesEmpaque(modelObject.getUnidadesEmpaque());
		articleDTO.setKiloVolumen(modelObject.getKiloVolumen());
		articleDTO.setVolumen(modelObject.getVolumen());
		articleDTO.setIndSeriales(modelObject.getIndSeriales());
		articleDTO.setIndGarantias(modelObject.getIndGarantias());
		articleDTO.setPrecioFobDol(modelObject.getPrecioFobDol());
		articleDTO.setPrecioFob(modelObject.getPrecioFob());
		articleDTO.setNoProve(modelObject.getNoProve());
		articleDTO.setObservacion(modelObject.getObservacion());
		articleDTO.setPromocion(modelObject.getPromocion());
		articleDTO.setComprador(modelObject.getComprador());
		articleDTO.setCompradorAlterno(modelObject.getCompradorAlterno());
		articleDTO.setCodSubmarca(modelObject.getCodSubmarca());
		articleDTO.setAbcCia(modelObject.getAbcCia());
		articleDTO.setAbcSublinea(modelObject.getAbcSublinea());
		articleDTO.setAbcMarca(modelObject.getAbcMarca());
		articleDTO.setPagCatalogo(modelObject.getPagCatalogo());
		articleDTO.setMonedaPreciobase(modelObject.getMonedaPreciobase());
		articleDTO.setMonedaPrecionuevo(modelObject.getMonedaPrecionuevo());
		articleDTO.setCodTipo(modelObject.getCodTipo());
		articleDTO.setCcodGrupo(modelObject.getCcodGrupo());
		articleDTO.setCodGrupo(modelObject.getCodGrupo());
		articleDTO.setFechaRegistro(modelObject.getFechaRegistro());
		articleDTO.setFechaCreacion(modelObject.getFechaCreacion());
		articleDTO.setFechaIngreso(modelObject.getFechaIngreso());
		articleDTO.setFechaUltCom(modelObject.getFechaUltCom());
		articleDTO.setPreciobase(modelObject.getPreciobase());
		articleDTO.setPrecioCd(modelObject.getPrecioCd());
		articleDTO.setStatusCd(modelObject.getStatusCd());
		articleDTO.setIndTop30(modelObject.getIndTop30());
		articleDTO.setValorSerialDistribucion(modelObject.getValorSerialDistribucion());
		articleDTO.setPrecionuevo(modelObject.getPrecionuevo());
		articleDTO.setPrecioLandedDol(modelObject.getPrecioLandedDol());
		articleDTO.setPrecioLanded(modelObject.getPrecioLanded());
		articleDTO.setEnvio(modelObject.getEnvio());
		articleDTO.setAjustaInvPlv(modelObject.getAjustaInvPlv());
		articleDTO.setFechaLanzam(modelObject.getFechaLanzam());
		articleDTO.setPedidoOptimo(modelObject.getPedidoOptimo());
		articleDTO.setDescColeccion(modelObject.getDescColeccion());
		articleDTO.setIndProxd(modelObject.getIndProxd());
		articleDTO.setUnPaquete(modelObject.getUnPaquete());
		articleDTO.setDescripcionTemporada(modelObject.getDescripcionTemporada());
		articleDTO.setTextura(modelObject.getTextura());
		articleDTO.setTexturaFormula(modelObject.getTexturaFormula());
		articleDTO.setMaterial(modelObject.getMaterial());
		articleDTO.setColores(modelObject.getColores());
		articleDTO.setTallas(modelObject.getTallas());
		articleDTO.setIndEvacuacion(modelObject.getIndEvacuacion());
		articleDTO.setCodRet(modelObject.getCodRet());
		articleDTO.setIndRet(modelObject.getIndRet());
		articleDTO.setIndUnoXUnoRecibo(modelObject.getIndUnoXUnoRecibo());
		articleDTO.setCodVenta(modelObject.getCodVenta());
		articleDTO.setArtEspecial(modelObject.getArtEspecial());
		return articleDTO;
	}

	@Override
	public List<ArticleDTO> reverseTranslateObjectList(
			List<Arinda> modelObjectList) throws TechnicalException {
		List<ArticleDTO> articleList = new ArrayList<ArticleDTO>();
		for (Arinda arinda : modelObjectList) {
			articleList.add(reverseTranslateObject(arinda));
		}
		return articleList;
	}

}
