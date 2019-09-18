package com.fi.crm.campaigns.common.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ArticleDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2985101397188350719L;
	private String noCia;
	private String noArti;
	private String clase;
    private String categoria;
    private String descripcion;
    private String unidad;
    private BigDecimal peso;
    private Short tiempoRep;
    private String codigo;
    private BigDecimal transito;
    private String upc;
    private String marca;
    private String grupo;
    private BigDecimal maximo;
    private BigDecimal minimo;
    private BigDecimal reorden;
    private String impVen;
    private String fabricacion;
    private String promoapp;
    private String discExc;
    private String conttype;
    private String indLote;
    private String indActivo;
    private Short graciaVencer;
    private BigDecimal costoNuevo;
    private BigDecimal costoEstandar;
    private String aplicaImpuesto;
    private String nombreLargo;
    private String indImprimeGarantia;
    private String indRequiereSerie;
    private String indEsParaOferta;
    private Short garantia;
    private String indImporta;
    private BigDecimal cubicaje;
    private BigDecimal baseTarima;
    private BigDecimal altoTarima;
    private String tipoAlmacen;
    private String codigoArancel;
    private String descripcionIngles;
    private String traduccionDescri;
    private String tipoProducto;
    private String indPermisoSalud;
    private String tipoLiquida;
    private Long diasUtil;
    private String descripcionAlterna;
    private Date tstamp;
    private String indEtiqueta;
    private String etiqueta;
    private String aceptaDevolucion;
    private String tipoFactCombo;
    private String grReabastecimiento;
    private String clasificaAbc;
    private BigDecimal costoMercado;
    private BigDecimal costoDol;
    private BigDecimal costoNacDol;
    private Integer saldoUltComp;
    private Integer pedTransito;
    private BigDecimal margenUtilidad;
    private String esCombo;
    private String indPrecioFijoDol;
    private String indPrecioFijo;
    private String indLoteWms;
    private BigDecimal factor;
    private String indUbicacion;
    private String indCombo;
    private String codDistribuidor;
    private String numeroAutomatico;
    private String indConteoWms;
    private String indConsignacion;
    private String codSubgrupo;
    private String noGerente;
    private String codCaracteristica;
    private String codClase;
    private String registroSanitario;
    private String nombre;
    private Short unidadesEmpaque;
    private BigDecimal kiloVolumen;
    private BigDecimal volumen;
    private String indSeriales;
    private String indGarantias;
    private BigDecimal precioFobDol;
    private BigDecimal precioFob;
    private String noProve;
    private String observacion;
    private String promocion;
    private String comprador;
    private String compradorAlterno;
    private String codSubmarca;
    private String abcCia;
    private String abcSublinea;
    private String abcMarca;
    private Integer pagCatalogo;
    private String monedaPreciobase;
    private String monedaPrecionuevo;
    private String codTipo;
    private String ccodGrupo;
    private String codGrupo;
    private Date fechaRegistro;
    private Date fechaCreacion;
    private Date fechaIngreso;
    private Date fechaUltCom;
    private BigDecimal preciobase;
    private BigDecimal precioCd;
    private String statusCd;
    private String indTop30;
    private BigDecimal valorSerialDistribucion;
    private BigDecimal precionuevo;
    private BigDecimal precioLandedDol;
    private BigDecimal precioLanded;
    private String envio;
    private String ajustaInvPlv;
    private Date fechaLanzam;
    private String pedidoOptimo;
    private String descColeccion;
    private String indProxd;
    private Short unPaquete;
    private String descripcionTemporada;
    private String textura;
    private String texturaFormula;
    private String material;
    private String colores;
    private String tallas;
    private String indEvacuacion;
    private String codRet;
    private String indRet;
    private String indUnoXUnoRecibo;
    private String codVenta;
    private String artEspecial;
    
    public ArticleDTO () {
    	
    }
    
    public ArticleDTO (String noCia, String noArti){
    	this.noCia = noCia;
    	this.noArti = noArti;
    }
    
	public String getNoCia() {
		return noCia;
	}
	public void setNoCia(String noCia) {
		this.noCia = noCia;
	}
	public String getNoArti() {
		return noArti;
	}
	public void setNoArti(String noArti) {
		this.noArti = noArti;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public Short getTiempoRep() {
		return tiempoRep;
	}
	public void setTiempoRep(Short tiempoRep) {
		this.tiempoRep = tiempoRep;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getTransito() {
		return transito;
	}
	public void setTransito(BigDecimal transito) {
		this.transito = transito;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public BigDecimal getMaximo() {
		return maximo;
	}
	public void setMaximo(BigDecimal maximo) {
		this.maximo = maximo;
	}
	public BigDecimal getMinimo() {
		return minimo;
	}
	public void setMinimo(BigDecimal minimo) {
		this.minimo = minimo;
	}
	public BigDecimal getReorden() {
		return reorden;
	}
	public void setReorden(BigDecimal reorden) {
		this.reorden = reorden;
	}
	public String getImpVen() {
		return impVen;
	}
	public void setImpVen(String impVen) {
		this.impVen = impVen;
	}
	public String getFabricacion() {
		return fabricacion;
	}
	public void setFabricacion(String fabricacion) {
		this.fabricacion = fabricacion;
	}
	public String getPromoapp() {
		return promoapp;
	}
	public void setPromoapp(String promoapp) {
		this.promoapp = promoapp;
	}
	public String getDiscExc() {
		return discExc;
	}
	public void setDiscExc(String discExc) {
		this.discExc = discExc;
	}
	public String getConttype() {
		return conttype;
	}
	public void setConttype(String conttype) {
		this.conttype = conttype;
	}
	public String getIndLote() {
		return indLote;
	}
	public void setIndLote(String indLote) {
		this.indLote = indLote;
	}
	public String getIndActivo() {
		return indActivo;
	}
	public void setIndActivo(String indActivo) {
		this.indActivo = indActivo;
	}
	public Short getGraciaVencer() {
		return graciaVencer;
	}
	public void setGraciaVencer(Short graciaVencer) {
		this.graciaVencer = graciaVencer;
	}
	public BigDecimal getCostoNuevo() {
		return costoNuevo;
	}
	public void setCostoNuevo(BigDecimal costoNuevo) {
		this.costoNuevo = costoNuevo;
	}
	public BigDecimal getCostoEstandar() {
		return costoEstandar;
	}
	public void setCostoEstandar(BigDecimal costoEstandar) {
		this.costoEstandar = costoEstandar;
	}
	public String getAplicaImpuesto() {
		return aplicaImpuesto;
	}
	public void setAplicaImpuesto(String aplicaImpuesto) {
		this.aplicaImpuesto = aplicaImpuesto;
	}
	public String getNombreLargo() {
		return nombreLargo;
	}
	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	public String getIndImprimeGarantia() {
		return indImprimeGarantia;
	}
	public void setIndImprimeGarantia(String indImprimeGarantia) {
		this.indImprimeGarantia = indImprimeGarantia;
	}
	public String getIndRequiereSerie() {
		return indRequiereSerie;
	}
	public void setIndRequiereSerie(String indRequiereSerie) {
		this.indRequiereSerie = indRequiereSerie;
	}
	public String getIndEsParaOferta() {
		return indEsParaOferta;
	}
	public void setIndEsParaOferta(String indEsParaOferta) {
		this.indEsParaOferta = indEsParaOferta;
	}
	public Short getGarantia() {
		return garantia;
	}
	public void setGarantia(Short garantia) {
		this.garantia = garantia;
	}
	public String getIndImporta() {
		return indImporta;
	}
	public void setIndImporta(String indImporta) {
		this.indImporta = indImporta;
	}
	public BigDecimal getCubicaje() {
		return cubicaje;
	}
	public void setCubicaje(BigDecimal cubicaje) {
		this.cubicaje = cubicaje;
	}
	public BigDecimal getBaseTarima() {
		return baseTarima;
	}
	public void setBaseTarima(BigDecimal baseTarima) {
		this.baseTarima = baseTarima;
	}
	public BigDecimal getAltoTarima() {
		return altoTarima;
	}
	public void setAltoTarima(BigDecimal altoTarima) {
		this.altoTarima = altoTarima;
	}
	public String getTipoAlmacen() {
		return tipoAlmacen;
	}
	public void setTipoAlmacen(String tipoAlmacen) {
		this.tipoAlmacen = tipoAlmacen;
	}
	public String getCodigoArancel() {
		return codigoArancel;
	}
	public void setCodigoArancel(String codigoArancel) {
		this.codigoArancel = codigoArancel;
	}
	public String getDescripcionIngles() {
		return descripcionIngles;
	}
	public void setDescripcionIngles(String descripcionIngles) {
		this.descripcionIngles = descripcionIngles;
	}
	public String getTraduccionDescri() {
		return traduccionDescri;
	}
	public void setTraduccionDescri(String traduccionDescri) {
		this.traduccionDescri = traduccionDescri;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public String getIndPermisoSalud() {
		return indPermisoSalud;
	}
	public void setIndPermisoSalud(String indPermisoSalud) {
		this.indPermisoSalud = indPermisoSalud;
	}
	public String getTipoLiquida() {
		return tipoLiquida;
	}
	public void setTipoLiquida(String tipoLiquida) {
		this.tipoLiquida = tipoLiquida;
	}
	public Long getDiasUtil() {
		return diasUtil;
	}
	public void setDiasUtil(Long diasUtil) {
		this.diasUtil = diasUtil;
	}
	public String getDescripcionAlterna() {
		return descripcionAlterna;
	}
	public void setDescripcionAlterna(String descripcionAlterna) {
		this.descripcionAlterna = descripcionAlterna;
	}
	public Date getTstamp() {
		return tstamp;
	}
	public void setTstamp(Date tstamp) {
		this.tstamp = tstamp;
	}
	public String getIndEtiqueta() {
		return indEtiqueta;
	}
	public void setIndEtiqueta(String indEtiqueta) {
		this.indEtiqueta = indEtiqueta;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public String getAceptaDevolucion() {
		return aceptaDevolucion;
	}
	public void setAceptaDevolucion(String aceptaDevolucion) {
		this.aceptaDevolucion = aceptaDevolucion;
	}
	public String getTipoFactCombo() {
		return tipoFactCombo;
	}
	public void setTipoFactCombo(String tipoFactCombo) {
		this.tipoFactCombo = tipoFactCombo;
	}
	public String getGrReabastecimiento() {
		return grReabastecimiento;
	}
	public void setGrReabastecimiento(String grReabastecimiento) {
		this.grReabastecimiento = grReabastecimiento;
	}
	public String getClasificaAbc() {
		return clasificaAbc;
	}
	public void setClasificaAbc(String clasificaAbc) {
		this.clasificaAbc = clasificaAbc;
	}
	public BigDecimal getCostoMercado() {
		return costoMercado;
	}
	public void setCostoMercado(BigDecimal costoMercado) {
		this.costoMercado = costoMercado;
	}
	public BigDecimal getCostoDol() {
		return costoDol;
	}
	public void setCostoDol(BigDecimal costoDol) {
		this.costoDol = costoDol;
	}
	public BigDecimal getCostoNacDol() {
		return costoNacDol;
	}
	public void setCostoNacDol(BigDecimal costoNacDol) {
		this.costoNacDol = costoNacDol;
	}
	public Integer getSaldoUltComp() {
		return saldoUltComp;
	}
	public void setSaldoUltComp(Integer saldoUltComp) {
		this.saldoUltComp = saldoUltComp;
	}
	public Integer getPedTransito() {
		return pedTransito;
	}
	public void setPedTransito(Integer pedTransito) {
		this.pedTransito = pedTransito;
	}
	public BigDecimal getMargenUtilidad() {
		return margenUtilidad;
	}
	public void setMargenUtilidad(BigDecimal margenUtilidad) {
		this.margenUtilidad = margenUtilidad;
	}
	public String getEsCombo() {
		return esCombo;
	}
	public void setEsCombo(String esCombo) {
		this.esCombo = esCombo;
	}
	public String getIndPrecioFijoDol() {
		return indPrecioFijoDol;
	}
	public void setIndPrecioFijoDol(String indPrecioFijoDol) {
		this.indPrecioFijoDol = indPrecioFijoDol;
	}
	public String getIndPrecioFijo() {
		return indPrecioFijo;
	}
	public void setIndPrecioFijo(String indPrecioFijo) {
		this.indPrecioFijo = indPrecioFijo;
	}
	public String getIndLoteWms() {
		return indLoteWms;
	}
	public void setIndLoteWms(String indLoteWms) {
		this.indLoteWms = indLoteWms;
	}
	public BigDecimal getFactor() {
		return factor;
	}
	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}
	public String getIndUbicacion() {
		return indUbicacion;
	}
	public void setIndUbicacion(String indUbicacion) {
		this.indUbicacion = indUbicacion;
	}
	public String getIndCombo() {
		return indCombo;
	}
	public void setIndCombo(String indCombo) {
		this.indCombo = indCombo;
	}
	public String getCodDistribuidor() {
		return codDistribuidor;
	}
	public void setCodDistribuidor(String codDistribuidor) {
		this.codDistribuidor = codDistribuidor;
	}
	public String getNumeroAutomatico() {
		return numeroAutomatico;
	}
	public void setNumeroAutomatico(String numeroAutomatico) {
		this.numeroAutomatico = numeroAutomatico;
	}
	public String getIndConteoWms() {
		return indConteoWms;
	}
	public void setIndConteoWms(String indConteoWms) {
		this.indConteoWms = indConteoWms;
	}
	public String getIndConsignacion() {
		return indConsignacion;
	}
	public void setIndConsignacion(String indConsignacion) {
		this.indConsignacion = indConsignacion;
	}
	public String getCodSubgrupo() {
		return codSubgrupo;
	}
	public void setCodSubgrupo(String codSubgrupo) {
		this.codSubgrupo = codSubgrupo;
	}
	public String getNoGerente() {
		return noGerente;
	}
	public void setNoGerente(String noGerente) {
		this.noGerente = noGerente;
	}
	public String getCodCaracteristica() {
		return codCaracteristica;
	}
	public void setCodCaracteristica(String codCaracteristica) {
		this.codCaracteristica = codCaracteristica;
	}
	public String getCodClase() {
		return codClase;
	}
	public void setCodClase(String codClase) {
		this.codClase = codClase;
	}
	public String getRegistroSanitario() {
		return registroSanitario;
	}
	public void setRegistroSanitario(String registroSanitario) {
		this.registroSanitario = registroSanitario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Short getUnidadesEmpaque() {
		return unidadesEmpaque;
	}
	public void setUnidadesEmpaque(Short unidadesEmpaque) {
		this.unidadesEmpaque = unidadesEmpaque;
	}
	public BigDecimal getKiloVolumen() {
		return kiloVolumen;
	}
	public void setKiloVolumen(BigDecimal kiloVolumen) {
		this.kiloVolumen = kiloVolumen;
	}
	public BigDecimal getVolumen() {
		return volumen;
	}
	public void setVolumen(BigDecimal volumen) {
		this.volumen = volumen;
	}
	public String getIndSeriales() {
		return indSeriales;
	}
	public void setIndSeriales(String indSeriales) {
		this.indSeriales = indSeriales;
	}
	public String getIndGarantias() {
		return indGarantias;
	}
	public void setIndGarantias(String indGarantias) {
		this.indGarantias = indGarantias;
	}
	public BigDecimal getPrecioFobDol() {
		return precioFobDol;
	}
	public void setPrecioFobDol(BigDecimal precioFobDol) {
		this.precioFobDol = precioFobDol;
	}
	public BigDecimal getPrecioFob() {
		return precioFob;
	}
	public void setPrecioFob(BigDecimal precioFob) {
		this.precioFob = precioFob;
	}
	public String getNoProve() {
		return noProve;
	}
	public void setNoProve(String noProve) {
		this.noProve = noProve;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getPromocion() {
		return promocion;
	}
	public void setPromocion(String promocion) {
		this.promocion = promocion;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getCompradorAlterno() {
		return compradorAlterno;
	}
	public void setCompradorAlterno(String compradorAlterno) {
		this.compradorAlterno = compradorAlterno;
	}
	public String getCodSubmarca() {
		return codSubmarca;
	}
	public void setCodSubmarca(String codSubmarca) {
		this.codSubmarca = codSubmarca;
	}
	public String getAbcCia() {
		return abcCia;
	}
	public void setAbcCia(String abcCia) {
		this.abcCia = abcCia;
	}
	public String getAbcSublinea() {
		return abcSublinea;
	}
	public void setAbcSublinea(String abcSublinea) {
		this.abcSublinea = abcSublinea;
	}
	public String getAbcMarca() {
		return abcMarca;
	}
	public void setAbcMarca(String abcMarca) {
		this.abcMarca = abcMarca;
	}
	public Integer getPagCatalogo() {
		return pagCatalogo;
	}
	public void setPagCatalogo(Integer pagCatalogo) {
		this.pagCatalogo = pagCatalogo;
	}
	public String getMonedaPreciobase() {
		return monedaPreciobase;
	}
	public void setMonedaPreciobase(String monedaPreciobase) {
		this.monedaPreciobase = monedaPreciobase;
	}
	public String getMonedaPrecionuevo() {
		return monedaPrecionuevo;
	}
	public void setMonedaPrecionuevo(String monedaPrecionuevo) {
		this.monedaPrecionuevo = monedaPrecionuevo;
	}
	public String getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	public String getCcodGrupo() {
		return ccodGrupo;
	}
	public void setCcodGrupo(String ccodGrupo) {
		this.ccodGrupo = ccodGrupo;
	}
	public String getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaUltCom() {
		return fechaUltCom;
	}
	public void setFechaUltCom(Date fechaUltCom) {
		this.fechaUltCom = fechaUltCom;
	}
	public BigDecimal getPreciobase() {
		return preciobase;
	}
	public void setPreciobase(BigDecimal preciobase) {
		this.preciobase = preciobase;
	}
	public BigDecimal getPrecioCd() {
		return precioCd;
	}
	public void setPrecioCd(BigDecimal precioCd) {
		this.precioCd = precioCd;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getIndTop30() {
		return indTop30;
	}
	public void setIndTop30(String indTop30) {
		this.indTop30 = indTop30;
	}
	public BigDecimal getValorSerialDistribucion() {
		return valorSerialDistribucion;
	}
	public void setValorSerialDistribucion(BigDecimal valorSerialDistribucion) {
		this.valorSerialDistribucion = valorSerialDistribucion;
	}
	public BigDecimal getPrecionuevo() {
		return precionuevo;
	}
	public void setPrecionuevo(BigDecimal precionuevo) {
		this.precionuevo = precionuevo;
	}
	public BigDecimal getPrecioLandedDol() {
		return precioLandedDol;
	}
	public void setPrecioLandedDol(BigDecimal precioLandedDol) {
		this.precioLandedDol = precioLandedDol;
	}
	public BigDecimal getPrecioLanded() {
		return precioLanded;
	}
	public void setPrecioLanded(BigDecimal precioLanded) {
		this.precioLanded = precioLanded;
	}
	public String getEnvio() {
		return envio;
	}
	public void setEnvio(String envio) {
		this.envio = envio;
	}
	public String getAjustaInvPlv() {
		return ajustaInvPlv;
	}
	public void setAjustaInvPlv(String ajustaInvPlv) {
		this.ajustaInvPlv = ajustaInvPlv;
	}
	public Date getFechaLanzam() {
		return fechaLanzam;
	}
	public void setFechaLanzam(Date fechaLanzam) {
		this.fechaLanzam = fechaLanzam;
	}
	public String getPedidoOptimo() {
		return pedidoOptimo;
	}
	public void setPedidoOptimo(String pedidoOptimo) {
		this.pedidoOptimo = pedidoOptimo;
	}
	public String getDescColeccion() {
		return descColeccion;
	}
	public void setDescColeccion(String descColeccion) {
		this.descColeccion = descColeccion;
	}
	public String getIndProxd() {
		return indProxd;
	}
	public void setIndProxd(String indProxd) {
		this.indProxd = indProxd;
	}
	public Short getUnPaquete() {
		return unPaquete;
	}
	public void setUnPaquete(Short unPaquete) {
		this.unPaquete = unPaquete;
	}
	public String getDescripcionTemporada() {
		return descripcionTemporada;
	}
	public void setDescripcionTemporada(String descripcionTemporada) {
		this.descripcionTemporada = descripcionTemporada;
	}
	public String getTextura() {
		return textura;
	}
	public void setTextura(String textura) {
		this.textura = textura;
	}
	public String getTexturaFormula() {
		return texturaFormula;
	}
	public void setTexturaFormula(String texturaFormula) {
		this.texturaFormula = texturaFormula;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getColores() {
		return colores;
	}
	public void setColores(String colores) {
		this.colores = colores;
	}
	public String getTallas() {
		return tallas;
	}
	public void setTallas(String tallas) {
		this.tallas = tallas;
	}
	public String getIndEvacuacion() {
		return indEvacuacion;
	}
	public void setIndEvacuacion(String indEvacuacion) {
		this.indEvacuacion = indEvacuacion;
	}
	public String getCodRet() {
		return codRet;
	}
	public void setCodRet(String codRet) {
		this.codRet = codRet;
	}
	public String getIndRet() {
		return indRet;
	}
	public void setIndRet(String indRet) {
		this.indRet = indRet;
	}
	public String getIndUnoXUnoRecibo() {
		return indUnoXUnoRecibo;
	}
	public void setIndUnoXUnoRecibo(String indUnoXUnoRecibo) {
		this.indUnoXUnoRecibo = indUnoXUnoRecibo;
	}
	public String getCodVenta() {
		return codVenta;
	}
	public void setCodVenta(String codVenta) {
		this.codVenta = codVenta;
	}
	public String getArtEspecial() {
		return artEspecial;
	}
	public void setArtEspecial(String artEspecial) {
		this.artEspecial = artEspecial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((noArti == null) ? 0 : noArti.hashCode());
		result = prime * result + ((noCia == null) ? 0 : noCia.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleDTO other = (ArticleDTO) obj;
		if (noArti == null) {
			if (other.noArti != null)
				return false;
		} else if (!noArti.equals(other.noArti))
			return false;
		if (noCia == null) {
			if (other.noCia != null)
				return false;
		} else if (!noCia.equals(other.noCia))
			return false;
		return true;
	}
}
