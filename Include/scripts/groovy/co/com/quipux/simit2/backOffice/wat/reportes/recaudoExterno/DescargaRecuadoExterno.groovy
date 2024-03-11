package co.com.quipux.simit2.backOffice.wat.reportes.recaudoExterno
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

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

import cucumber.api.java.es.Dado
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Entonces



class DescargaRecuadoExterno {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Dado("que se ingresa las fechas desde y hasta que se solicita el reporte de recuado externo.")
	def que_se_ingresa_las_fechas_desde_y_hasta_que_se_solicita_el_reporte_de_recuado_externo() {
		WebUI.setText(findTestObject('Object Repository/BackOffice/Portal/Reportes/Recaudo Externo/Descarga Recuado Externo/Inpunt_fechaInicial'), "11/04/2022" )
		WebUI.setText(findTestObject('Object Repository/BackOffice/Portal/Reportes/Recaudo Externo/Descarga Recuado Externo/Inpunt_fechaHasta'), "16/04/2022" )
	}

	@Cuando("realizo la consulta")
	def realizo_la_consulta() {
		WebUI.doubleClick(findTestObject('Object Repository/BackOffice/Portal/Reportes/Recaudo Externo/Descarga Recuado Externo/Button_descarga'))
	}

	@Entonces("se verifica la respuesta")
	def se_verifica_la_respuesta() {
		WebUI.waitForPageLoad(60, FailureHandling.STOP_ON_FAILURE)
	}
}