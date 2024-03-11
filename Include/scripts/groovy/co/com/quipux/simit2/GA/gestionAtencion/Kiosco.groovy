package co.com.quipux.simit2.GA.gestionAtencion

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import internal.GlobalVariable
import util.utilsQX

public class Kiosco {



	//------------------- @Dado
	@Dado('que solicito un turno y ingreso un documento en el kiosco')
	def que_solicito_un_turno_y_ingreso_un_documento_en_el_kiosco() {
		WebUI.switchToWindowIndex(1)
		WebUI.delay(GlobalVariable.delay)
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Button_turno'))
		//String currentPage = WebUI.getUrl()
		//int currentTab = WebUI.getWindowIndex()
		WebUI.selectOptionByValue(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Select_tipoDocumento'), "number:2", true)
		WebUI.setText(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Input_Documento'), utilsQX.GenerateNIT(2))
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Button_continuar'))
	}


	//----------------- @Cuando
	@Cuando('el sistema me pida el tipo de servicio seleccion (.*)')
	def el_sistema_me_pida_el_tipo_de_servicio_selecion_solicitar(String tipo) {
		if(tipo.equals('Solicitar paz y salvo')) {
			WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Div_PazYSalvo'))
		}
		if(tipo.equals('Solicitar liquidación')) {
			WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Div_Liquidacion'))
		}
		if(tipo.equals('Consulta estado de cuenta')) {
			WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Div_EstadoCuenta'))
		}
	}

	@Entonces('solicito el turno')
	def solicito_el_turno() {
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/Kiosco/Button_continuar'))
	}
}
