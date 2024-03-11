package co.com.quipux.simit2.backOffice.soap.cargasArchivos
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.charset.StandardCharsets

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.network.ProxyInformation
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.common.SoapClient
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import model.cargaArchivos.xml.Comparendo
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.Utilsbase64
import util.WebServicesUtil
import util.utilsQX
import utils.simit.BuildComparendo
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y
import groovy.json.JsonSlurper
import org.json.XML



class CargaArchivoComparendo {


	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject


	@Dado("que registro un comparendo individual por el servicio soap con mi (.*) y (.*)")
	def que_registro_un_comparendo_individual_por_el_servicio_soap_con_mi(String usuario,String clave) {
		def archivo =  ""
		def comparendo = BuildComparendo.BuildComparendoSimple(usuario)

		archivo = new Comparendo(comparendo, usuario, clave).toString()


		DocumentoCargaArchivosEvidencias.addLast(new DocumentoCargaArchivosDTO(comparendo))

		requestObject = findTestObject('Object Repository/BackOffice/SOAP/WsCargaArchivos/WSCargasArchivoSimitNuevo/cargaArchivoComparendos/Request 1',[('url'):GlobalVariable.dominio])

		//ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		//cookies.add(new Cookie("Content-Type",'text/xml;charset=utf-8'))
		//requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))
		requestObject.setSoapBody(archivo)

		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","Carga-Comparendo-soap-${usuario}.txt")
		responseObject = WS.sendRequest(requestObject)
	}


	@Cuando("el sitema procesa el comparendo registrado por soap")
	def el_sitema_procesa_el_comparendo_registrado_por_soap() {
		WebServicesUtil.verifyCodeResponst(responseObject)
		def xmlsluper = new XmlSlurper().parseText(new String (responseObject.getResponseBodyContent().toString().bytes, StandardCharsets.UTF_8).replace('&lt;', '<').replace('&gt;', '>'))

		assert !xmlsluper.return.ArchivosTransito.encabezado.consecutivoCarga.toString().isAllWhitespace() : 'Fallo al momento de recuperar el consecutivo de carga'
		assert !xmlsluper.return.ArchivosTransito.detalle.toString().isAllWhitespace() : 'Fallo al momento de recuperar el detalle de la carga'

		TemporaryVariables.radicadoCargaArchivos = xmlsluper.return.ArchivosTransito.encabezado.consecutivoCarga..toString()

		DocumentoCargaArchivosEvidencias.setComRadicado(TemporaryVariables.radicadoCargaArchivos,9)

		utilsSimit.TiempoRadicado(TemporaryVariables.radicadoCargaArchivos,1)

		if(utilsSimit.consultaErrorRadicado(TemporaryVariables.radicadoCargaArchivos,1)) {
			DocumentoCargaArchivosEvidencias.guardarEvidencia()
			KeywordUtil.markErrorAndStop('hay un error en los comparendos cargado validar el documento de evidencias')
		}

	}

}