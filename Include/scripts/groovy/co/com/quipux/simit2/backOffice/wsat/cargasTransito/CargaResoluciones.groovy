package co.com.quipux.simit2.backOffice.wsat.cargasTransito

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y
import groovy.json.JsonSlurper
import internal.GlobalVariable
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.Utilsbase64
import util.WebServicesUtil
import util.utilsQX
import utils.simit.BuildResoluciones
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

public class CargaResoluciones {


	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject



	//-------------------------------------- Dado



	@Dado('que realizo una carga de resoluciones de tipo 1 con mi usuario (.*) y clave (.*)')
	def que_realizo_una_carga_de_resoluciones_de_tipo_1_con_mi_usuario_y_clave(String usuario, String clave) {
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def resolucion = BuildResoluciones.resolucionSimpleTipo1(usuario)
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 2, resolucion.RESIDCIUDAD)

		def evidencias =  DocumentoCargaArchivosEvidencias.getComparendoNumComp(resolucion.RESCOMP.toString())

		archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, resolucion) + "\n"
		valorTotal += Long.parseLong(resolucion.RESVALPAG.toString())
		evidencias.setRESNumeroResolucion(resolucion.RESNUMERO.toString())


		DocumentoCargaArchivosEvidencias.setRESNumeroResolucion(evidencias)

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		requestObject = findTestObject('Object Repository/BackOffice/WebService/CargasTransito/Request_CargarArchivos',[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])

		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",usuario))
		cookies.add(new Cookie("password",clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))


		requestObject.setHttpBody("{\"codigoEntidad\":\"${resolucion.RESORGANISMO}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 2,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${resolucion.RESORGANISMO}resol.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","Carga-resolucion-usuario-${usuario}.txt")
		responseObject = WS.sendRequest(requestObject)
	}


	@Dado("que realizo la carga de una resolucion de tipo 16 con usuario (.*) y clave (.*)")
	def que_realizo_la_carga_de_una_resolucion_de_tipo_16_con_usuario_y_clave(String usuario, String clave) {
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def resolucion = BuildResoluciones.resolucionSimpleTipo1(usuario)
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 2, resolucion.RESIDCIUDAD)

		def evidencias =  DocumentoCargaArchivosEvidencias.getComparendoNumComp(resolucion.RESCOMP.toString())

		archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, resolucion) + "\n"
		valorTotal += Long.parseLong(resolucion.RESVALPAG.toString())
		evidencias.setRESNumeroResolucion(resolucion.RESNUMERO.toString())


		DocumentoCargaArchivosEvidencias.setRESNumeroResolucion(evidencias)

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		requestObject = findTestObject('Object Repository/BackOffice/WebService/CargasTransito/Request_CargarArchivos',[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])

		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",usuario))
		cookies.add(new Cookie("password",clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))


		requestObject.setHttpBody("{\"codigoEntidad\":\"${resolucion.RESORGANISMO}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 2,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${resolucion.RESORGANISMO}resol.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","Carga-resolucion-usuario-${usuario}.txt")
		responseObject = WS.sendRequest(requestObject)
	}




	//-------------------------------- Cuando


	@Cuando('el sistema resive y procesa el archivo con exito')
	def el_sistema_resive_y_procesa_el_archivo_con_exito() {
		WebServicesUtil.verifyCodeResponst(responseObject)
		WebUI.comment(responseObject.getResponseBodyContent().toString())

		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(responseObject.getResponseBodyContent())
		result instanceof Map


		if(result.radicado.equals(0) || result.radicado.equals('') ) {
			WebServicesUtil.verifyCodeResponst(responseObject)
			KeywordUtil.markErrorAndStop("Fallo ")
		}
		TemporaryVariables.radicadoCargaArchivosResolucion = result.radicado.toString()

		DocumentoCargaArchivosEvidencias.setRESRadicado(TemporaryVariables.radicadoCargaArchivosResolucion)

		utilsSimit.TiempoRadicado(TemporaryVariables.radicadoCargaArchivosResolucion,2)
	}


	//------------------------ Entonces
	@Entonces('resivo una respuesta exitosa de la carga de la resolucion')
	def resivo_una_respuesta_exitosa_de_la_carga_de_la_resolucion() {

		if(utilsSimit.consultaErrorRadicado(TemporaryVariables.radicadoCargaArchivos,2)) {
			KeywordUtil.markErrorAndStop('hay un error en las resoluciones cargado validar el documento de evidencias')
		}

	}


	//------------------------------- Y
	@Y("realizo la carga maxiva de las resoluciones con los comparendos previamente cargados")
	def realizo_la_carga_maxiva_de_las_resoluciones_con_los_comparendos_previamente_cargados() {

	}
}
