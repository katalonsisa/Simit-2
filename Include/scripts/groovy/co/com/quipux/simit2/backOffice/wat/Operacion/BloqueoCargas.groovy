package co.com.quipux.simit2.backOffice.wat.Operacion

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
import cucumber.api.java.es.Y
import internal.GlobalVariable

public class BloqueoCargas {
	//----------------- variables privadas




	//----------------- @Dado
	@Dado("que selecciono el tipo de carga (.*)")
	def que_selecciono_el_tipo_de_carga(String tipoCarga){
		def tipo = tipoCarga.equalsIgnoreCase("Resoluciones") ? "2" : "1"
		WebUI.selectOptionByValue(findTestObject('Object Repository/BackOffice/Portal/Operacion/Bloqueo cargas/Select_TipoDocumento'), tipo,false)
	}


	//----------------- @Cuando
	@Cuando("realizo la busqueda del tipo de carga")
	def realizo_la_busqueda_del_tipo_de_carga(){
		WebUI.click(findTestObject('Object Repository/BackOffice/Portal/Operacion/Bloqueo cargas/button_buscar'), FailureHandling.STOP_ON_FAILURE)
	}


	//----------------- @Entonces
	@Entonces("busco y desactivo el tipo de carga (.*)")
	def  busco_y_desactivo_el_tipo_de_carga(String tipoArchivo){
		WebUI.doubleClick(findTestObject('Object Repository/BackOffice/Portal/Operacion/Bloqueo cargas/Switch_Activo',[('tipo'): tipoArchivo]))
	}


	//--------------------- @Y
	@Y("que selecciono la siguiente secretaria (.*)")
	def que_selecciono_la_siguiente_secretaria(String secretaria) {
		WebUI.selectOptionByValue(findTestObject('Object Repository/BackOffice/Portal/Operacion/Bloqueo cargas/Select_Secretaria'), secretaria, true)
	}
}
