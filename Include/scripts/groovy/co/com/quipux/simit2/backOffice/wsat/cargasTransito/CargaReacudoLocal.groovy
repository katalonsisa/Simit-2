package co.com.quipux.simit2.backOffice.wsat.cargasTransito

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.rmi.CORBA.Util

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.es.Dado
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y
import groovy.json.JsonSlurper
import groovy.json.internal.Dates
import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.Utilsbase64
import util.WebServicesUtil
import util.utilsQX
import utils.simit.BuildComparendo
import utils.simit.BuildRecaudoLocal
import utils.simit.BuildResoluciones
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

public class CargaReacudoLocal {


	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject

	//-------------------------------- dado



	@Dado('que realizo una carga de un recaudo local con mi (.*) y (.*)')
	def que_realizo_una_carga_de_un_recaudo_local_con_mi(String usuario, String clave){
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def fecha = util.Dates.getTimeStamp("dd/MM/YYYY")
		String numeroCuenta = utilsQX.getNumero(Random.newInstance().nextInt(20))
		def recaudoLocal = BuildRecaudoLocal.buildRecaudoLocalSimple(usuario, true,"")
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(3, 1,BuildRecaudoLocal.idSubSede)

		def evidencias =  DocumentoCargaArchivosEvidencias.getComparendoNumComp(recaudoLocal.getRECNUMEROREFERENCIA().toString())

		archivo += "${numeroCuenta},${fecha},${fecha},${recaudoLocal.RECSUBSEDESECRET},1 \n"

		archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, recaudoLocal) + "\n"
		valorTotal += Long.parseLong(recaudoLocal.RECTOTAL.toString())
		evidencias.setRECNumeroCuenta(numeroCuenta)


		DocumentoCargaArchivosEvidencias.setRECNumeroCuenta(evidencias)

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		requestObject = findTestObject('Object Repository/BackOffice/WebService/CargasTransito/Request_CargarArchivos',[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])

		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",usuario))
		cookies.add(new Cookie("password",clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))


		requestObject.setHttpBody("{\"codigoEntidad\":\"${recaudoLocal.RECSUBSEDESECRET}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 3,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${recaudoLocal.RECSUBSEDESECRET}rec.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","Carga-Recaudo-Local-usuario-${usuario}.txt")
		responseObject = WS.sendRequest(requestObject)
	}

	//--------------------------------- cuando
	@Cuando("el sistema resive el y procesa el archivo con exito del recaudo")
	def el_sistema_resive_el_y_procesa_el_archivo_con_exito_del_recaudo () {
		WebServicesUtil.verifyCodeResponst(responseObject)
		WebUI.comment(responseObject.getResponseBodyContent().toString())

		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(responseObject.getResponseBodyContent())
		result instanceof Map


		if(result.radicado.equals(0) || result.radicado.equals('') ) {
			WebServicesUtil.verifyCodeResponst(responseObject)
			KeywordUtil.markErrorAndStop("Fallo ")
		}
		TemporaryVariables.radicadoCargaArchivosRecaudoLocal = result.radicado.toString()

		DocumentoCargaArchivosEvidencias.setRECRadicadoRecaudoLocal(TemporaryVariables.radicadoCargaArchivosRecaudoLocal)

		utilsSimit.TiempoRadicado(TemporaryVariables.radicadoCargaArchivosRecaudoLocal,3)
	}


	@Cuando("realizo la carga del recaudo local de los comparendos cargados previamente")
	def  realizo_la_carga_del_recaudo_local_de_los_comparendos_cargados_previamente () {
		def archivo =  ""
		def lineaControl = ""
		Long valorTotal = 0
		def fecha = util.Dates.getTimeStamp("dd/MM/YYYY")
		String numeroCuenta = utilsQX.getNumero(Random.newInstance().nextInt(20) + 1)
		def recaudoLocalLista = BuildRecaudoLocal.buildRecaudoLocaldatoReferencia(TemporaryVariables.cantidadCarga, TemporaryVariables.usuario, true, TemporaryVariables.radicadoCargaArchivos)
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(3, 1,BuildRecaudoLocal.idSubSede)
		ArrayList<DocumentoCargaArchivosDTO> listaEvidencias = new ArrayList<DocumentoCargaArchivosDTO>()


		archivo += "${numeroCuenta},${fecha},${fecha},${recaudoLocalLista.get(0).RECSUBSEDESECRET},1 \n"

		for (recaudo in recaudoLocalLista) {

			def evidencias =  DocumentoCargaArchivosEvidencias.getComparendoNumComp(recaudo.RECNUMEROREFERENCIA)

			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, recaudo) + "\n"
			valorTotal += Long.parseLong(recaudo.RECTOTAL.toString())
			evidencias.setRECNumeroCuenta(numeroCuenta)
			listaEvidencias.add(new DocumentoCargaArchivosDTO(evidencias))

		}


		DocumentoCargaArchivosEvidencias.setRECNumeroCuentalista(listaEvidencias)

		archivo += "${TemporaryVariables.cantidadCarga},${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"


		requestObject = findTestObject('Object Repository/BackOffice/WebService/CargasTransito/Request_CargarArchivos',[('url'):GlobalVariable.dominio,('contexto'):GlobalVariable.contexto])

		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",TemporaryVariables.usuario))
		cookies.add(new Cookie("password",TemporaryVariables.clave))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))


		requestObject.setHttpBody("{\"codigoEntidad\":\"${recaudoLocalLista.get(0).RECSUBSEDESECRET}\",\"tipoMedioCarga\": 1,\"archivo\":[{\"tipoArchivo\": 3,\"archivo\":\"${Utilsbase64.encodeText(archivo)}\",\"nombreArchivo\": \"${recaudoLocalLista.get(0).RECSUBSEDESECRET}rec.txt\"}]}")
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","${recaudoLocalLista.get(0).RECSUBSEDESECRET}rec.txt")
		responseObject = WS.sendRequest(requestObject)
	}


	//------------------------------------- entonces
	@Entonces('resivo una respuesta exitosa de la carga del recaudo')
	def  resivo_una_respuesta_exitosa_de_la_carga_del_recaudo() {

		if(utilsSimit.consultaErrorRadicado(TemporaryVariables.radicadoCargaArchivosRecaudoLocal,3)) {
			KeywordUtil.markErrorAndStop('hay un error el recaudo local cargado validar el documento de evidencias')
		}

		DocumentoCargaArchivosEvidencias.guardarEvidencia()
	}


	//-------------------------------- Y


}
