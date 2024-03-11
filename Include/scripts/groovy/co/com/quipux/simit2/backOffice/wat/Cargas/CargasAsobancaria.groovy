package co.com.quipux.simit2.backOffice.wat.Cargas
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
import util.utilsQX

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.Keys

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.ast.Cuando
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces



class CargasAsobancaria {

	@Dado ("Subo el archivo de asobancaria")
	def Subo_el_archivo_de_asobancaria () {
		WebUI.uploadFile(findTestObject('Object Repository/BackOffice/Portal/Cargas/CargasAsobancaria/input_seleccionarArchivo'),  new File('Include/assets/documentos/20220301-1.txt').getAbsolutePath())
	}
	@Cuando ("hago el envío del archivo")
	def hago_el_envío_del_archivo () {
		WebUI.click(findTestObject("Object Repository/BackOffice/Portal/Cargas/CargasAsobancaria/button_procesar"))
	}
	@Entonces ("me muestra una ventana con el mensaje de que el archivo ha sido cargado")
	def me_muestra_una_ventana_con_el_mensaje_de_que_el_archivo_ha_sido_cargado () {
		WebUI.click(findTestObject("Object Repository/BackOffice/Portal/Cargas/CargasAsobancaria/button_salir"))
		WebUI.closeBrowser()
		
		WebUI.sendKeys(findTestObject("Object Repository/BackOffice/Portal/Cargas/CargasAsobancaria/button_salir"), Keys.ENTER.toString())
	}
}