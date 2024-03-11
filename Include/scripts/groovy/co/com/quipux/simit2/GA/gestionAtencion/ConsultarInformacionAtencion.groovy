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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.ast.Y
import cucumber.api.java.en_lol.WEN
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import util.utilsQX
import internal.GlobalVariable

public class ConsultarInformacionAtencion {

	@Cuando ('se despliega listado de subsede')
	def se_despliega_listado_de_subsede(){
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/ConsultarInformacionAtencion/Bt_ListaSubsede'))
	}
	
	@Y ('seleccionar subsede')
	def seleccionar_subsede(){
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/ConsultarInformacionAtencion/subsedeLista'))
	}

	@Entonces ('dar clic en el boton buscar')
	def dar_clic_en_el_boton_buscar() {
		WebUI.click(findTestObject('Object Repository/GA/Gestion de la atencion/ConsultarInformacionAtencion/BtnBuscar'))
	}
}
