package co.com.quipux.simit2.homePublico.wsat.cursosViales

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
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.jna.platform.win32.COM.util.annotation.ComObject

import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import internal.GlobalVariable
import util.Cookie
import util.GenerateDocumento
import util.TemporaryVariables
import util.WebServicesUtil
import utils.simit.BuildJsonRecibirCurso
import utils.simit.constantesSimit.CursosViales
import groovy.json.JsonSlurper

public class recibirCurso {

	private static RequestObject requestObject  = findTestObject('Object Repository/HomePublico/WebService/CursoVialService/Request_resibirCurso');
	private static ResponseObject responseObject ;


	@Dado('que envio el json informando que una persona finalizo un curso de forma exitoza')
	def que_envio_el_json_informando_que_una_persona_finalizo_un_curso_de_forma_exitoza(){


		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",GlobalVariable.userDefaul))
		cookies.add(new Cookie("password",GlobalVariable.emailUser))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))

		def json = BuildJsonRecibirCurso.buildJsonCompleto()

		requestObject.setHttpBody(json)
		GenerateDocumento.newfile(json, CursosViales.PATH_CURSOS_VIALES_EVICENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","CursoVialExitoso.json")
		responseObject = WS.sendRequest(requestObject)
	}


	@Dado('que envio un json sin los siguientes campos (.*)')
	def que_envio_un_json_sin_los_siguientes_campos(String campo){
		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",GlobalVariable.userDefaul))
		cookies.add(new Cookie("password",GlobalVariable.emailUser))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))
		def json = BuildJsonRecibirCurso.buildSinCampos(campo)
		requestObject.setHttpBody(json)
		GenerateDocumento.newfile(json, CursosViales.PATH_CURSOS_VIALES_EVICENCIAS + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","CursoVialCamposFaltantes.json")
		responseObject = WS.sendRequest(requestObject)
	}


	@Dado('que envio el valores maximos en el (.*) permitidos en el json')
	def que_envio_el_valores_maximos_en_el_permitidos_en_el_json(String campo){
		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",GlobalVariable.userDefaul))
		cookies.add(new Cookie("password",GlobalVariable.emailUser))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))
		def json = BuildJsonRecibirCurso.buildCamposMaximos(campo)
		requestObject.setHttpBody(json)
		GenerateDocumento.newfile(json,CursosViales.PATH_CURSOS_VIALES_EVICENCIAS + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","CursoVialCamposMaximos.json")
		responseObject = WS.sendRequest(requestObject)
	}

	@Dado('que envio el valor menor al minimo permitido en el campo (.*) en el json')
	def que_envio_el_valor_menor_al_minimo_permitido_en_el_campo_en_el_json(String campo){
		ArrayList<Cookie> cookies = new ArrayList<Cookie>()
		cookies.add(new Cookie("usuario",GlobalVariable.userDefaul))
		cookies.add(new Cookie("password",GlobalVariable.emailUser))
		requestObject.setHttpHeaderProperties(WebServicesUtil.HTTPHeaderCookies(cookies))
		def json = BuildJsonRecibirCurso.buildCamposMinimos(campo)

		requestObject.setHttpBody(json)
		GenerateDocumento.newfile(json, CursosViales.PATH_CURSOS_VIALES_EVICENCIAS + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","CursoVialCamposMinimos.json")
		responseObject = WS.sendRequest(requestObject)
	}


	@Entonces('verifico que se halla reportado de manera exitoza')
	def verifico_que_se_halla_reportado_de_manera_exitoza(){
		WebServicesUtil.verifyCodeResponst(responseObject)
		WebUI.comment(responseObject.getResponseBodyContent().toString())
	}


	@Entonces('verifico que muestre fallo al envio')
	def verifico_que_muestre_fallo_al_envio(){
		WebUI.comment(responseObject.getResponseBodyContent().toString())

		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(responseObject.getResponseBodyContent())
		result instanceof Map
		if(result.codigo.equals(null) || result.codigo.equals("")){
			WebServicesUtil.verifyCodeResponst(responseObject)
			KeywordUtil.markErrorAndStop("Fallo ")
		}
		if(result.codigo.toString().equals("200")){
			KeywordUtil.markErrorAndStop("El envio fue exitoso campo sin validacion")
		}
	}
}
