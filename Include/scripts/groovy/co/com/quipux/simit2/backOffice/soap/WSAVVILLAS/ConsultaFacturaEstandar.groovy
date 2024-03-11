package co.com.quipux.simit2.backOffice.soap.WSAVVILLAS

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import util.GenerateDocumento
import util.TemporaryVariables
import utils.simit.BuildWSAVVILLAS
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.constantesSimit.commonPath
import utils.simit.utilsSimit

public class ConsultaFacturaEstandar {


	// objecto para crear la peticion rest
	private static RequestObject requestObject = findTestObject('Object Repository/BackOffice/SOAP/WSAVVilllas/SOAP_ConsultarFacturaEstandar')
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject

	//------------------------- @Dado
	@Dado("que realizo el consumo para consultar la factura")
	def que_realizo_el_consumo_del_servicio_para_pagar_la_Factura() {
		def comparendosLiquidados = DocumentoCargaArchivosEvidencias.getListComparendosLiquidacion()

		def refLiquidacion =  utilsSimit.ConsultaReferenciaLiquidacion(comparendosLiquidados.get(0))
		def archivo = BuildWSAVVILLAS.buildXMLConsultaFactura(refLiquidacion)

		requestObject.setSoapBody(archivo)

		GenerateDocumento.newfile(archivo,commonPath.PATH_EVIDENCIAS+ '/WSAVVILLAS/'  +
				TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","consulta-factura-AVVILLAS-soap-${new Date().format("dd-MM-YYYY-HH-mm-ss")}.txt")
		this.responseObject = WS.sendRequest(this.requestObject)
	}



	@Entonces('el sistema me retorna los datos relacionados con la factura')
	def El_sistema_me_entrega_un_respuesta_Exitosa_del_consumo_del_servicio_para_ATH(){
		println(responseObject.getResponseBodyContent())
		def xmlsluper = new XmlSlurper().parseText(new String (responseObject.getResponseBodyContent().toString().bytes, StandardCharsets.UTF_8).replace('&lt;', '<').replace('&gt;', '>'))

		assert !xmlsluper.return.mensajeRespuesta.toString().isAllWhitespace()
		assert xmlsluper.return.mensajeRespuesta.text() == "Transacción exitosa"

	}



}
