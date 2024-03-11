/* groovylint-disable PackageName, PropertyName */
package model.cargaArchivos.evidencias

import model.cargaArchivos.csv.ComparendoDTO
import util.XlsColumn

public class DocumentoCargaArchivosDTO {

	@XlsColumn(columnNumber = 0, headerText = 'Número consecutivo')
	String COMConsecutivo

	@XlsColumn(columnNumber = 1, headerText = 'Número Comparendo')
	String COMNumeroComparendo

	@XlsColumn(columnNumber = 2, headerText = 'Número Divipo')
	String COMDivipo

	@XlsColumn(columnNumber = 3, headerText = 'Placa vehiculo')
	String COMPlaca

	@XlsColumn(columnNumber = 4, headerText = 'Número documento infractor')
	String COMDocumentoInfractor

	@XlsColumn(columnNumber = 5, headerText = 'Tipo documento infractor')
	String COMTipoDocumento

	@XlsColumn(columnNumber = 6, headerText = 'Tipo carga')
	String COMTipoCarga

	@XlsColumn(columnNumber = 7, headerText = 'Fecha comparendo')
	String COMFechaComparendo

	@XlsColumn(columnNumber = 8, headerText = 'Nombre infractor')
	String COMNombreInfractor

	@XlsColumn(columnNumber = 9, headerText = 'Apellido infractor')
	String COMApellidoInfractor

	@XlsColumn(columnNumber = 10, headerText = 'Tipo infracion')
	String COMTipoInfracion

	@XlsColumn(columnNumber = 11, headerText = 'Comparendo Valor')
	String COMValor

	@XlsColumn(columnNumber = 12, headerText = 'Radicado')
	String COMRadicado

	@XlsColumn(columnNumber = 13, headerText = 'Estado radicado')
	String COMEstadoRadicado

	@XlsColumn(columnNumber = 14, headerText = 'ID CARGA INFORMACION')
	String COMIDCargaInformacion

	@XlsColumn(columnNumber = 15, headerText = 'Detalle Error')
	String COMDetalleError

	@XlsColumn(columnNumber = 16, headerText = 'Referencia pago')
	String COMReferenciaPago

	@XlsColumn(columnNumber = 17, headerText = 'Liquidable por web')
	String COMLiquidableWeb

	//--------------------- Resoluciones

	@XlsColumn(columnNumber = 18, headerText = 'Numero de la resolucion')
	String RESNumeroResolucion

	@XlsColumn(columnNumber = 19, headerText = 'Radicado Resolucion')
	String RESRadicadoResolucion

	@XlsColumn(columnNumber = 20, headerText = 'ID CARGA INFORMACION resolucion')
	String RESIDCargaInformacionResolucion

	@XlsColumn(columnNumber = 21, headerText = 'Estado de la resolucion')
	String RESEstadoResolucion

	@XlsColumn(columnNumber = 22, headerText = 'Detalle error resolucion')
	String RESDetalleErrorResolucion

	@XlsColumn(columnNumber = 23, headerText = 'Referencia pago')
	String RESReferenciaPago

	@XlsColumn(columnNumber = 24, headerText = 'Liquidable por web')
	String RESLiquidableWeb

	//------------------ Recaudo


	@XlsColumn(columnNumber = 25, headerText = 'Numero Cuenta')
	String RECNumeroCuenta

	@XlsColumn(columnNumber = 26, headerText = 'Radicado Recaudo Local')
	String RECRadicadoRecaudoLocal

	@XlsColumn(columnNumber = 27, headerText = 'ID Carga informacion Reacuado local')
	String RECIDCargaInformacionRacudoLocal

	@XlsColumn(columnNumber = 28, headerText = 'Estado del recaudo')
	String RECEstadoRecaudoLocal

	@XlsColumn(columnNumber = 29, headerText = 'Detalle error recaudo local')
	String RECDetalleErroRecaudoLocal




	public DocumentoCargaArchivosDTO(DocumentoCargaArchivosDTO temp){
		this.COMConsecutivo = temp.COMConsecutivo
		this.COMDivipo = temp.COMDivipo
		this.COMNumeroComparendo = temp.COMNumeroComparendo
		this.COMPlaca = temp.COMPlaca
		this.COMFechaComparendo = temp.COMFechaComparendo
		this.COMNombreInfractor = temp.COMNombreInfractor
		this.COMApellidoInfractor = temp.COMApellidoInfractor
		this.COMTipoInfracion = temp.COMTipoInfracion
		this.COMDocumentoInfractor = temp.COMDocumentoInfractor
		this.COMTipoDocumento = temp.COMTipoDocumento
		this.COMTipoCarga = temp.COMTipoCarga
		this.COMRadicado = temp.COMRadicado
		this.COMValor = temp.COMValor
		this.COMEstadoRadicado = temp.COMEstadoRadicado
		this.COMIDCargaInformacion = temp.COMIDCargaInformacion
		this.COMDetalleError = temp.COMDetalleError
		this.COMLiquidableWeb = temp.COMLiquidableWeb
		this.COMReferenciaPago = temp.COMReferenciaPago
		this.RESNumeroResolucion = temp.RESNumeroResolucion
		this.RESRadicadoResolucion = temp.RESRadicadoResolucion
		this.RESEstadoResolucion = temp.RESEstadoResolucion
		this.RESDetalleErrorResolucion = temp.RESDetalleErrorResolucion
		this.RESIDCargaInformacionResolucion = temp.RESIDCargaInformacionResolucion
		this.RECNumeroCuenta = temp.RECNumeroCuenta
		this.RECRadicadoRecaudoLocal = temp.RECRadicadoRecaudoLocal
		this.RECEstadoRecaudoLocal = temp.RECEstadoRecaudoLocal
		this.RECDetalleErroRecaudoLocal = temp.RECDetalleErroRecaudoLocal
	}


	public DocumentoCargaArchivosDTO() {
		this.COMConsecutivo = ''
		this.COMDivipo = ''
		this.COMNumeroComparendo = ''
		this.COMPlaca = ''
		this.COMFechaComparendo = ''
		this.COMNombreInfractor = ''
		this.COMApellidoInfractor = ''
		this.COMTipoInfracion = ''
		this.COMDocumentoInfractor = ''
		this.COMTipoDocumento = ''
		this.COMTipoCarga = ''
		this.COMRadicado = ''
		this.COMValor = ''
		this.COMEstadoRadicado = ''
		this.COMIDCargaInformacion = ''
		this.COMDetalleError = ''
		this.COMLiquidableWeb = ''
		this.COMReferenciaPago = ''
		this.RESNumeroResolucion = ''
		this.RESRadicadoResolucion = ''
		this.RESEstadoResolucion = ''
		this.RESDetalleErrorResolucion = ''
		this.RESIDCargaInformacionResolucion = ''
		this.RECNumeroCuenta = ''
		this.RECRadicadoRecaudoLocal = ''
		this.RECEstadoRecaudoLocal = ''
		this.RECDetalleErroRecaudoLocal = ''
	}


	public DocumentoCargaArchivosDTO(ComparendoDTO Comparendo) {
		this.COMConsecutivo = Comparendo.COMCONSECUTIVO
		this.COMDivipo = Comparendo.COMDIVIPOMUNI
		this.COMNumeroComparendo = Comparendo.COMNUMERO
		this.COMPlaca = Comparendo.COMPLACA
		this.COMFechaComparendo = Comparendo.COMFECHA
		this.COMNombreInfractor = Comparendo.COMNOMBRE
		this.COMApellidoInfractor = Comparendo.COMAPELLIDO
		this.COMTipoInfracion = Comparendo.COMTIPOINFRAC
		this.COMDocumentoInfractor = Comparendo.COMINFRACTOR
		this.COMTipoDocumento = Comparendo.COMTIPODOC
		this.COMTipoCarga = '1'
		this.COMRadicado = ''
		this.COMValor = Comparendo.COMVALOR
		this.COMEstadoRadicado = ''
		this.COMIDCargaInformacion = ''
		this.COMDetalleError = ''
		this.COMLiquidableWeb = ''
		this.COMReferenciaPago = ''
		this.RESNumeroResolucion = ''
		this.RESRadicadoResolucion = ''
		this.RESEstadoResolucion = ''
		this.RESDetalleErrorResolucion = ''
		this.RESIDCargaInformacionResolucion = ''
	}


}
