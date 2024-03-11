package co.com.quipux.simit2.backOffice.wsat.cargasTransito
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y
import groovy.json.JsonSlurper
import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.Utilsbase64
import util.WebServicesUtil
import util.utilsQX
import utils.simit.BuildComparendo
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.constantesSimit.documentosExtenciones
import utils.simit.constantesSimit.letraConstantes
import utils.simit.utilsSimit

class CargaComparendo {


	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject = findTestObject('Object Repository/BackOffice/WebService/CargasTransito/Request_CargarArchivos',[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject



	//-------------------------------------- Dado

	@Dado('subo (.*) de comparendos al sistema con el usuario (.*) y clave (.*)')
	def subo_de_comparendos_al_sistema_con_el_usuario_y_clave(String cantidad, String usuario, String clave) {
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		TemporaryVariables.usuario = usuario
		TemporaryVariables.clave = clave
		TemporaryVariables.cantidadCarga = Integer.parseInt(cantidad)
		def listaComparendos = BuildComparendo.BuildComparendoSimpleMaxivo(TemporaryVariables.usuario, TemporaryVariables.cantidadCarga )
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 1, BuildComparendo.idSubSede)
		String estandarTransferenciaAlcoholemia = utilsSimit.getEstandarTransferencia(1, 2, BuildComparendo.idSubSede)
		ArrayList<DocumentoCargaArchivosDTO> listaEvidencias = new ArrayList<DocumentoCargaArchivosDTO>()


		for (comparendo in listaComparendos) {
			if(comparendo.COMINFRACCION.equals("F")) {
				archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferenciaAlcoholemia, comparendo) + "\n"
			}else {
				archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, comparendo) + "\n"
			}

			valorTotal += Long.parseLong(comparendo.COMVALINFRA)
			listaEvidencias.add(new DocumentoCargaArchivosDTO(comparendo))
		}



		archivo += "${TemporaryVariables.cantidadCarga},${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		DocumentoCargaArchivosEvidencias.addLast(listaEvidencias)

		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",TemporaryVariables.usuario))
		cookies.add(new Cookie("password",TemporaryVariables.clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))

		requestObject.setHttpBody("{\"codigoEntidad\":\"${listaComparendos.get(0).COMDIVIPOMUNI}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 1,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${listaComparendos.get(0).COMDIVIPOMUNI}comp.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","M${listaComparendos.get(0).COMDIVIPOMUNI}comp.txt")
		//responseObject = WS.sendRequest(requestObject)
	}


	@Dado('que cargo un comparendo con mi usuario (.*) y clave (.*)')
	public static def que_cargo_un_comparendo_con_usuario_y_clave(String usuario,String clave) {
		TemporaryVariables.usuario = usuario
		TemporaryVariables.clave = clave
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def comparendo = BuildComparendo.BuildComparendoSimple(usuario)
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 1, BuildComparendo.idSubSede)

		if(comparendo.COMINFRACCION.equals("F")) {
			String estandarTransferenciaAlcoholemia = utilsSimit.getEstandarTransferencia(1, 2, BuildComparendo.idSubSede)
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferenciaAlcoholemia, comparendo) + "\n"
		}else {
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, comparendo) + "\n"
		}

		DocumentoCargaArchivosEvidencias.addLast(new DocumentoCargaArchivosDTO(comparendo))
		valorTotal += Long.parseLong(comparendo.COMVALINFRA)

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"



		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",TemporaryVariables.usuario))
		cookies.add(new Cookie("password",TemporaryVariables.clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))


		requestObject.setHttpBody("{\"codigoEntidad\":\"${comparendo.COMDIVIPOMUNI}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 1,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${comparendo.COMDIVIPOMUNI}comp.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","S${comparendo.COMDIVIPOMUNI}comp.txt.txt")
		responseObject = WS.sendRequest(requestObject)
	}

	@Dado('que cargo un comparendo con tutor con mi usuario (.*) y clave (.*)')
	public static def que_cargo_un_comparendo_con_tutor_con_mi_usuario_y_clave(String usuario,String clave) {
		TemporaryVariables.usuario = usuario
		TemporaryVariables.clave = clave
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def comparendo = BuildComparendo.BuildComparendoTutor(usuario)
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 1, BuildComparendo.subSede)

		if(comparendo.COMINFRACCION.equals("F")) {
			String estandarTransferenciaAlcoholemia = utilsSimit.getEstandarTransferencia(1, 2, BuildComparendo.idSubSede)
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferenciaAlcoholemia, comparendo) + "\n"
		}else {
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, comparendo) + "\n"
		}

		valorTotal += Long.parseLong(comparendo.COMVALINFRA)
		DocumentoCargaArchivosEvidencias.addLast(new DocumentoCargaArchivosDTO(comparendo))

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",TemporaryVariables.usuario))
		cookies.add(new Cookie("password",TemporaryVariables.clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))

		requestObject.setHttpBody("{\"codigoEntidad\":\"${comparendo.COMDIVIPOMUNI}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 1,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${comparendo.COMDIVIPOMUNI}comp.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","TS${comparendo.COMDIVIPOMUNI}comp.txt")
		responseObject = WS.sendRequest(requestObject)
	}



	//--------------------------------------- Cuando
	@Cuando('el sistema procesa el archivo')
	static def el_sistema_procesa_el_archivo() {
		WebServicesUtil.verifyCodeResponst(responseObject)
		WebUI.comment(responseObject.getResponseBodyContent().toString())

		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(responseObject.getResponseBodyContent())
		result instanceof Map


		if(result.radicado.equals(0) || result.radicado.equals('') ) {
			WebServicesUtil.verifyCodeResponst(responseObject)
			KeywordUtil.markErrorAndStop("Fallo al momento de generar el radicado")
		}
		TemporaryVariables.radicadoCargaArchivos = result.radicado.toString()

		DocumentoCargaArchivosEvidencias.setComRadicado(TemporaryVariables.radicadoCargaArchivos)

		utilsSimit.TiempoRadicado(TemporaryVariables.radicadoCargaArchivos,1)
	}


	//------------------------------------- Entonces
	@Entonces('resivo una respuesta de la carga')
	static def resivo_una_respuesta_de_la_carga() {

		if(utilsSimit.consultaErrorRadicado(TemporaryVariables.radicadoCargaArchivos,1)) {
			DocumentoCargaArchivosEvidencias.guardarEvidencia()
			KeywordUtil.markErrorAndStop('hay un error en los comparendos cargado validar el documento de evidencias')
		}

	}



	//------------------------------------- Y

	@Y('termino la prueba de carga y liquidacion Masiva')
	def termino_la_prueba_de_carga_y_liquidacion_Masiva() {

		WebUI.closeBrowser()
		//utilidades.moveFile(DocumentoCargaArchivo.PATH + DocumentoCargaArchivo.NOMBRE_DOCUMENTO + documentosExtenciones.XLSX, DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS + TemporaryVariables.FECHA_EJECUCION_PRUEBA + '/')

		DocumentoCargaArchivosEvidencias.guardarEvidencia()

	}


	@Y('realizo la liquidacion de los comparendos cargados por el sistema')
	def  realizo_la_liquidacion_de_los_comparendos_cargados_por_el_sistema() {

		def primero = true

		ArrayList<DocumentoCargaArchivosDTO> Documento = DocumentoCargaArchivosEvidencias.getListComparendos(TemporaryVariables.radicadoCargaArchivos)
		ArrayList<DocumentoCargaArchivosDTO> DocumentoEvidencias = new ArrayList<DocumentoCargaArchivosDTO>()

		for (var in Documento) {
			println var.COMConsecutivo
			if(primero) {
				WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), var.COMDocumentoInfractor)
				WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
				primero = false
			}else {
				if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',[('id'): "0"]), GlobalVariable.delay, FailureHandling.OPTIONAL)) {
					WebUI.click(
							findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',
							[('id'): "0"]))
					WebUI.click(
							findTestObject('Object Repository/homePublico/button_continuar'))
				}

				WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), var.COMDocumentoInfractor)
				WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_busquedaDocPlac'))
			}


			if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',[('id'): "0"]), GlobalVariable.delay, FailureHandling.OPTIONAL)) {
				WebUI.click(
						findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',
						[('id'): "0"]))
				WebUI.click(
						findTestObject('Object Repository/homePublico/button_continuar'))
			}


			if(!WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
				var.COMReferenciaPago = documentosExtenciones.PDF
				var.COMLiquidableWeb = letraConstantes.N
				DocumentoEvidencias.add(new DocumentoCargaArchivosDTO(var))
				continue
			}


			WebUI.scrollToElement(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay)


			if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/checkbox_multaXComparendo', [('comparendo'):var.COMNumeroComparendo]), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {

				utilsSimit.clickUsingJS(findTestObject('Object Repository/homePublico/estadoCuenta/checkbox_multaXComparendo', [('comparendo'): var.COMNumeroComparendo]))

				WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_imprimirCuponDePago'))
				WebUI.delay(GlobalVariable.shortDelay)


				WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/modalCupon/button_descargarCupon'))
				WebUI.delay(GlobalVariable.shortDelay)


				var.COMReferenciaPago = documentosExtenciones.PDF
				var.COMLiquidableWeb = letraConstantes.S
			}else {
				var.COMReferenciaPago = documentosExtenciones.PDF
				var.COMLiquidableWeb = letraConstantes.N
			}

			DocumentoEvidencias.add(new DocumentoCargaArchivosDTO(var))
		}
		DocumentoCargaArchivosEvidencias.setCOMReferenciaPagoLote(DocumentoEvidencias)

	}
}