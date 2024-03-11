package co.com.quipux.simit2.backOffice.wat.cargaArchivos
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
import util.GenerateDocumento
import util.TemporaryVariables
import util.utilsQX
import utils.simit.BuildComparendo
import utils.simit.constantesSimit.DocumentoCargaArchivo
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

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces



class CargaComparendos {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Dado("selecciona un archivo de comparendos")
	def selecciona_un_archivo_de_comparendos() {
		WebUI.selectOptionByValue(findTestObject('Object Repository/BackOffice/Portal/Cargas/CargaArchivos/Select_secretaria'), "673", true)
		def archivo =  ""
		def comparendo = BuildComparendo.BuildComparendoSimple("MonitoreoSIMIT")
		String estandarTransferencia = utilsSimit.getEstandarTransferencia(1, 1, BuildComparendo.idSubSede)
		if(comparendo.COMINFRACCION.equals("F")) {
			String estandarTransferenciaAlcoholemia = utilsSimit.getEstandarTransferencia(1, 2, BuildComparendo.idSubSede)
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferenciaAlcoholemia, comparendo) + "\n"
		}else {
			archivo += utilsSimit.aplicarEstandarTranferencia(estandarTransferencia, comparendo) + "\n"
		}

		def valorTotal = Long.parseLong(comparendo.COMVALINFRA)

		archivo += "1,${valorTotal},${utilsQX.getNumero(10)},${utilsQX.getNumero(10)}"
		GenerateDocumento.newfile(archivo,DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/","${comparendo.COMDIVIPOMUNI}comp.txt")

		
		WebUI.uploadFile(findTestObject('Object Repository/BackOffice/Portal/Cargas/CargaArchivos/Input_comparendos'),  new File(DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS  + TemporaryVariables.FECHA_EJECUCION_PRUEBA + "/"+"${comparendo.COMDIVIPOMUNI}comp.txt").getAbsolutePath())
	}

	@Cuando("damos clic en el boton de registrar")
	def damos_clic_en_el_boton_de_registrar() {
		WebUI.click(findTestObject('Object Repository/BackOffice/Portal/Cargas/CargaArchivos/Button_registrar'))
	}
}