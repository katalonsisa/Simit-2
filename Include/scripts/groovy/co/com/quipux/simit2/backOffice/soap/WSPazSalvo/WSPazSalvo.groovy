package co.com.quipux.simit2.backOffice.soap.WSPazSalvo

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.charset.StandardCharsets
import java.security.SecureRandom

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
import util.WebServicesUtil
import util.utilsQX

public class WSPazSalvo {


	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject


	//----------------------- Dado
	@Dado("que realizo la consulta de paz y salvo")
	def que_realizo_la_consulta_de_paz_y_salvo() {

		def idTipoDocumentoSelect = Math.abs( new SecureRandom().nextInt() % 9 - 1) + 1

		def archivo = new PazSalvoXML(idFuncionario:GlobalVariable.userDefaul,
		clave:GlobalVariable.passwordDefault,
		idSecretaria:GlobalVariable.divipoDefault,
		idTipoDocumento:idTipoDocumentoSelect,
		idContraventor:utilsQX.getNumero(idTipoDocumentoSelect)).toString()
		requestObject = findTestObject('Object Repository/PlacetoPay/HomePublico/SOAP/WsPazYSalvo/getPazSalvo')

		requestObject.setSoapBody(archivo)

		responseObject = WS.sendRequest(requestObject)
	}



	// @Entonces
	@Entonces("el servicio me responde con los datos de paz y salvo")
	def el_servicio_me_responde_con_los_datos_de_paz_y_salvo() {
		WebServicesUtil.verifyCodeResponst(responseObject)
		println new String (responseObject.getResponseBodyContent().toString().bytes, StandardCharsets.UTF_8).replace('&lt;', '<').replace('&gt;', '>')
	}

	//class

	public class PazSalvoXML {
		String idFuncionario = ""
		String clave = ""
		String idSecretaria = ""
		String idTipoDocumento = ""
		String idContraventor = ""

		String toString() {'<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://webServices.qxMultas.quipux.com.co">' +
			'<soapenv:Header/>/n<soapenv:Body>/n<web:getPazSalvo>'+
			"<idFuncionario>${idFuncionario}</idFuncionario>/n"+
			"<clave>${clave}</clave>/n"+
			"<idSecretaria>${idSecretaria}</idSecretaria>/n"+
			"<idTipoDocumento>${idTipoDocumento}</idTipoDocumento>/n"+
			"<idContraventor>${idContraventor}</idContraventor>/n"+
			'</web:getPazSalvo></soapenv:Body></soapenv:Envelope>'};
	}
}
