package utils.simit

import com.kms.katalon.core.util.KeywordUtil

import commons.utilidades
import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import sun.security.mscapi.PRNG
import util.GenerateXlsx
import util.TemporaryVariables
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.constantesSimit.documentosExtenciones
import utils.simit.constantesSimit.letraConstantes

public class DocumentoCargaArchivosEvidencias {



	private static ArrayList<DocumentoCargaArchivosDTO> listaEvidencia = new ArrayList<DocumentoCargaArchivosDTO>()

	/**
	 * Metodo para generar el documento base de evidencias de cargaArchivos 
	 * @param Documento
	 * @return null
	 */
	public static  generateDocument(ArrayList<DocumentoCargaArchivosDTO> Documento){
		//GenerateXlsx.generarXlsx(DocumentoCargaArchivo.PATH, DocumentoCargaArchivo.NOMBRE_DOCUMENTO, Documento)
		listaEvidencia = Documento
	}

	/**
	 * Metodo para obtener los datos que hay en el documento de evidencias
	 * @return  ArrayList con la informacion del documento mapeada 
	 */
	public static ArrayList<DocumentoCargaArchivosDTO> getList(){
		//		List<DocumentoCargaArchivosDTO> lista = GenerateXlsx.getObjectFromXlsx(DocumentoCargaArchivo.PATH+DocumentoCargaArchivo.NOMBRE_DOCUMENTO+documentosExtenciones.XLSX, DocumentoCargaArchivosDTO.class)
		//		return lista
		return listaEvidencia
	}


	public static clearDocument(){
		ArrayList<DocumentoCargaArchivosDTO> Documento = new ArrayList<DocumentoCargaArchivosDTO>()
		Documento.add(new DocumentoCargaArchivosDTO())
		generateDocument(Documento)
	}

	public static guardarEvidencia() {
		GenerateXlsx.generarXlsx(DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/",
				DocumentoCargaArchivo.NOMBRE_DOCUMENTO, listaEvidencia)
		//utilidades.moveFile(DocumentoCargaArchivo.PATH+DocumentoCargaArchivo.NOMBRE_DOCUMENTO+documentosExtenciones.XLSX, DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/")
		//clearDocument()
	}


	public static addLast(DocumentoCargaArchivosDTO ultimo) {
		ArrayList<DocumentoCargaArchivosDTO> datos = getList()
		datos.add(ultimo)
		generateDocument(datos)
	}


	public static addLast(ArrayList<DocumentoCargaArchivosDTO> listEvidencias) {
		for (Evidencia in listEvidencias) {
			addLast(Evidencia)
		}
	}



	//------------------------------- metodos set


	public static setComRadicado(String radicado,String tipoCarga = '1') {
		/*ArrayList<DocumentoCargaArchivosDTO> DocumentoEvidencia = getList()
		 DocumentoEvidencia.forEach{println it.COMNumeroComparendo}
		 DocumentoEvidencia.collect{*/
		listaEvidencia.collect{
			if (!it.COMConsecutivo.equals('') && it.COMRadicado.equals('')) {
				it.setCOMRadicado(radicado)
				it.setCOMTipoCarga(tipoCarga)
			}
		}
		//DocumentoEvidencia.forEach{println it.COMNumeroComparendo}

		//generateDocument(DocumentoEvidencia)
	}



	public static void setComEstadoRadicado(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if (it.COMRadicado.equals(DocumentoCargaArchivos.COMRadicado)) {
				it.setCOMEstadoRadicado(DocumentoCargaArchivos.COMEstadoRadicado)
				it.setCOMIDCargaInformacion(DocumentoCargaArchivos.COMIDCargaInformacion)
			}
		}
	}

	public static void setRESEstadoRadicado(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if(it.RESRadicadoResolucion.equals(DocumentoCargaArchivos.RESRadicadoResolucion)) {
				it.setRESEstadoResolucion(DocumentoCargaArchivos.RESEstadoResolucion)
				it.setRESIDCargaInformacionResolucion(DocumentoCargaArchivos.RESIDCargaInformacionResolucion)

			}
		}
	}

	public static void setRECEstadoRadicado(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if(it.RECRadicadoRecaudoLocal.equals(DocumentoCargaArchivos.RECRadicadoRecaudoLocal)) {
				it.setRECEstadoRecaudoLocal(DocumentoCargaArchivos.RECEstadoRecaudoLocal)
				it.setRECIDCargaInformacionRacudoLocal(DocumentoCargaArchivos.RECIDCargaInformacionRacudoLocal)
			}
		}
	}




	public static void setComErrorRadicado(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			it.setCOMDetalleError(DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.COMRadicado == k.getAt('COMRadicado') }.get(0).getAt('COMDetalleError'))
		}
	}





	public static setCOMReferenciaPago(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if(it.COMRadicado.equals(DocumentoCargaArchivos.COMRadicado) &&  it.COMConsecutivo.equals(DocumentoCargaArchivos.COMConsecutivo) ) {
				it.setCOMReferenciaPago(DocumentoCargaArchivos.COMReferenciaPago)
				it.setCOMLiquidableWeb(DocumentoCargaArchivos.COMLiquidableWeb)
			}
		}
	}

	public static setCOMReferenciaPagoLote(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.COMRadicado == k.getAt('COMRadicado') }.get(0)
			it.setCOMReferenciaPago(evidencia.COMReferenciaPago)
			it.setCOMLiquidableWeb(evidencia.COMLiquidableWeb)
		}
	}


	//-------------------------------- set Resoluciones



	public static setRESNumeroResolucion(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {

		listaEvidencia.collect{
			if(it.COMRadicado.equals(DocumentoCargaArchivos.COMRadicado) &&  it.COMConsecutivo.equals(DocumentoCargaArchivos.COMConsecutivo) ) {
				it.setRESNumeroResolucion(DocumentoCargaArchivos.RESNumeroResolucion)
			}
		}
	}


	public static setRESReferenciaPago(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if(it.RESRadicadoResolucion.equals(DocumentoCargaArchivos.RESRadicadoResolucion) && it.COMConsecutivo.equals(DocumentoCargaArchivos.COMConsecutivo) ) {
				it.setCOMReferenciaPago(DocumentoCargaArchivos.RESReferenciaPago)
				it.setCOMLiquidableWeb(DocumentoCargaArchivos.RESLiquidableWeb)
			}
		}
	}


	public static setRESNumeroResolucion(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.COMRadicado == k.getAt('COMRadicado') }.get(0)
			it.setRESNumeroResolucion(evidencia.RESNumeroResolucion)
		}
	}

	public static void setRESErrorRadicado(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.RESRadicadoResolucion == k.getAt('RESRadicadoResolucion') }.get(0)
			it.setRESDetalleErrorResolucion(evidencia.RESDetalleErrorResolucion)
		}
	}


	public static setRESRadicado(String radicado) {
		listaEvidencia.collect{
			if(!it.RESNumeroResolucion.equals('') && it.RESRadicadoResolucion.equals('')) {
				it.RESRadicadoResolucion(radicado)
			}
		}
	}



	//------------------------ set Recaudo


	public static setRECNumeroCuenta(DocumentoCargaArchivosDTO DocumentoCargaArchivos) {
		listaEvidencia.collect{
			if(it.COMRadicado.equals(DocumentoCargaArchivos.COMRadicado) &&  it.COMConsecutivo.equals(DocumentoCargaArchivos.COMConsecutivo) ) {
				it.setRECNumeroCuenta(DocumentoCargaArchivos.RECNumeroCuenta)
			}
		}
	}

	public static setRECNumeroCuentalista(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.COMRadicado == k.getAt('COMRadicado') }.get(0)
			it.setRECNumeroCuenta(evidencia.RECNumeroCuenta)
		}
	}


	public static setRECNumeroCuenta(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.COMRadicado == k.getAt('COMRadicado') }.get(0)
			it.setRESNumeroResolucion(evidencia.RECNumeroCuenta)
		}
	}

	public static setRECRadicadoRecaudoLocal(String radicado) {
		listaEvidencia.collect{
			if(!it.RECNumeroCuenta.equals('') || !it.RECNumeroCuenta.equals(null) && it.RECRadicadoRecaudoLocal.equals('') || it.RECRadicadoRecaudoLocal.equals(null)   ) {
				it.setRECRadicadoRecaudoLocal(radicado.toString())
			}
		}
	}

	public static void setRECErrorRadicadoLocal(ArrayList<DocumentoCargaArchivosDTO> DocumentoCargaArchivos) {
		listaEvidencia.collect{
			DocumentoCargaArchivosDTO evidencia = DocumentoCargaArchivos.findAll{k -> it.COMConsecutivo == k.getAt('COMConsecutivo') && it.RECRadicadoRecaudoLocal == k.getAt('RECRadicadoRecaudoLocal') }.get(0)
			it.setRECDetalleErroRecaudoLocal(evidencia.RECDetalleErroRecaudoLocal)
		}
	}

	//------------------------------- metodos get
	public static ArrayList<DocumentoCargaArchivosDTO> getListComparendos(String radicado){
		return listaEvidencia.findAll { it.getAt('COMRadicado').toString().equals(radicado) }
	}

	public static DocumentoCargaArchivosDTO getComparendoNumComp(String NumComparendo){
		return listaEvidencia.findAll{it.getAt('COMNumeroComparendo').toString().equals(NumComparendo)}
	}


	public static ArrayList<DocumentoCargaArchivosDTO> getListComparendosSinLiquidar(){
		return listaEvidencia.findAll{
			!it.getAt('COMIDCargaInformacion').toString().equals('') &&
					it.getAt('COMReferenciaPago').toString().equals('') && it.getAt('COMLiquidableWeb').toString().equals('')
		}
	}


	public static ArrayList<DocumentoCargaArchivosDTO> getListComparendosLiquidacion(){
		return listaEvidencia.findAll{ it.getAt('COMLiquidableWeb') == letraConstantes.S.toString() }
	}

	public static ArrayList<DocumentoCargaArchivosDTO> getListResolucionesSinLiquidar(){

		return listaEvidencia.findAll{ !it.getAt('COMIDCargaInformacion').equals('') && it.getAt('COMReferenciaPago').equals('') && it.getAt('COMLiquidableWeb').equals('')}

	}


	public static ArrayList<DocumentoCargaArchivosDTO> getListResolucion(String radicado){
		return listaEvidencia.findAll{ it.getAt('RESRadicadoResolucion').toString().equals(radicado)}
	}

	public static ArrayList<DocumentoCargaArchivosDTO> getListRecaudoLocal(String radicado){

		return listaEvidencia.findAll{ it.getAt('RECRadicadoRecaudoLocal').toString().equals(radicado)}
	}


	public static DocumentoCargaArchivosDTO getComparendoLiquidado(String Liquidacion) {
		return listaEvidencia.findAll{ it.getAt('COMReferenciaPago') == Liquidacion }.get(0)
	}

}


