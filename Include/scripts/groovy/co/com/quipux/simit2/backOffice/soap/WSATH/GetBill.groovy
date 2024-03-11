package co.com.quipux.simit2.backOffice.soap.WSATH

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import internal.GlobalVariable
import util.GenerateDocumento
import util.TemporaryVariables
import utils.simit.BuildWSATH
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

public class GetBill {

	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject = findTestObject('Object Repository/BackOffice/SOAP/WSATH/SOAP_GetBill')
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject



	//----------------------- @Dado
	@Dado("que relaizo la consulta al servicio soap de ATH")
	def  que_relaizo_la_consulta_al_servicio_soap_de_ATH() {

		def comparendosLiquidados = DocumentoCargaArchivosEvidencias.getListComparendosLiquidacion()

		def refLiquidacion =  utilsSimit.ConsultaReferenciaLiquidacion(comparendosLiquidados.get(0))
		def archivo = BuildWSATH.buildGetbill("refLiquidacion")

		requestObject.setSoapBody(archivo.toString())

		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  +
				TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","GetBill-${new Date().format("dd-MM-YYYY-HH-mm-ss")}.txt")
		this.responseObject = WS.sendRequest(this.requestObject)
	}


	//-------------------- @Entonces
	@Entonces("el obtengo la respuesta de la factura desde ATH")
	def el_obtengo_la_respuesta_de_la_factura_desde_ATH() {
		def xmlsluper = new XmlSlurper().parseText(new String (responseObject.getResponseBodyContent().toString().bytes, StandardCharsets.UTF_8).replace('&lt;', '<').replace('&gt;', '>'))

		assert !xmlsluper.return.message.toString().isAllWhitespace() : 'Fallo al momento de recuperar el consecutivo de carga'
		assert !xmlsluper.return.requestId.toString().isAllWhitespace() : 'Fallo al momento de recuperar el detalle de la carga'

	}
}
