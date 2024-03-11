package co.com.quipux.simit2.backOffice.soap.WSATH

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.charset.StandardCharsets

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
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.WebServicesUtil
import util.utilsQX
import utils.simit.BuildWSATH
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

public class PagoLiquidacion {

	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject = findTestObject('Object Repository/BackOffice/SOAP/WSATH/SOAP_sendPmtNotification')
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject


	//-------------------- @Dado
	@Dado('que realizo el consumo del pago de liquidacion expuesto para ATH')
	def que_realizo_el_consumo_del_pago_de_liquidacion_expuesto_para_ATH() {

		def comparendosLiquidados = DocumentoCargaArchivosEvidencias.getListComparendosLiquidacion()

		def refLiquidacion =  utilsSimit.ConsultaReferenciaLiquidacion(comparendosLiquidados.get(0))
		def archivo = BuildWSATH.BuildXMLPagoLiquidacion(refLiquidacion,comparendosLiquidados.get(0).COMValor)

		requestObject.setSoapBody(archivo)

		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  +
				TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","Pago-Liquidacion-ATH-soap-${new Date().format("dd-MM-YYYY-HH-mm-ss")}.txt")
		this.responseObject = WS.sendRequest(this.requestObject)
	}


	//-------------------- @Entonces
	@Entonces('El sistema me entrega un respuesta Exitosa del consumo del servicio para ATH')
	def El_sistema_me_entrega_un_respuesta_Exitosa_del_consumo_del_servicio_para_ATH(){
		println(responseObject.getResponseBodyContent())
		def xmlsluper = new XmlSlurper().parseText(new String (responseObject.getResponseBodyContent().toString().bytes, StandardCharsets.UTF_8).replace('&lt;', '<').replace('&gt;', '>'))

		assert !xmlsluper.return.message.toString().isAllWhitespace()
		assert xmlsluper.return.message.text() == "Transacción exitosa"

	}
}
