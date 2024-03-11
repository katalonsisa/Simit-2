package co.com.quipux.simit2.homePublico.wat
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
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.TemporaryVariables
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.constantesSimit.letraConstantes
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
import cucumber.api.java.es.Y



class botonPagos {


	@Y('seleciono la opcion de pagar')
	def seleciono_la_opcion_de_pagar(){
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_pagar'))
		WebUI.delay(GlobalVariable.delayWFEP)
	}


	@Y('realizo la pasarela de pagos')
	def realizo_la_pasarela_de_pagos(){

		String Codigo = ""

		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_pagar'))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.setText(findTestObject('Object Repository/PlacetoPay/input_email'), GlobalVariable.emailUser)
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuar'))
		WebUI.delay(GlobalVariable.shortDelay)

		Codigo = utilsSimit.obtenerCodigo()
		WebUI.setText(findTestObject('Object Repository/PlacetoPay/input_CodigoWallet'), Codigo.toString())
		WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuarWallet'))

		WebUI.click(findTestObject('Object Repository/PlacetoPay/div_tarjeta_pago'))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.setText(findTestObject('Object Repository/PlacetoPay/input_codigo_seguridad'), "123")
		WebUI.selectOptionByIndex(findTestObject('Object Repository/PlacetoPay/select_cuotas'), '2')
		WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuar'))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/PlacetoPay/button_regresar_al_comercio'))
	}

	@Y('regreso al inicio')
	def regreso_al_inicio(){
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/homePublico/respuesta_pagos/button_Inicio'))
	}


	@Y('rechazo el pago por la pasarela')
	def rechazo_el_pago_por_la_pasarela() {
		WebUI.click(findTestObject('Object Repository/PlacetoPay/div_CancelarPago'))
	}




	//-------------------- pago masivo

	@Y('realizo el pago de los comparendos cargados al sistema')
	def realizo_el_pago_de_los_comparendos_cargados_al_sistema() {

		ArrayList<DocumentoCargaArchivosDTO> Documento = DocumentoCargaArchivosEvidencias.getListComparendos(TemporaryVariables.radicadoCargaArchivos)
		ArrayList<DocumentoCargaArchivosDTO> DocumentoEvidencias = new ArrayList<DocumentoCargaArchivosDTO>()

		for (var in Documento) {

			println var.COMConsecutivo

			WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), var.COMDocumentoInfractor)
			WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
			WebUI.delay(GlobalVariable.delay)



			if(!WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
				var.COMReferenciaPago = "Sin comparendos"
				var.COMLiquidableWeb = letraConstantes.N
				DocumentoEvidencias.add(new DocumentoCargaArchivosDTO(var))
				continue
			}
			WebUI.scrollToElement(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay)


			if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/checkbox_multaXComparendo', [('comparendo'):var.COMNumeroComparendo]), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {


				utilsSimit.clickUsingJS(findTestObject('Object Repository/homePublico/estadoCuenta/checkbox_multaXComparendo', [('comparendo'):var.COMNumeroComparendo]))


				String Codigo = ""

				WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_pagar'))
				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.setText(findTestObject("Object Repository/PlacetoPay/input_email.com"), GlobalVariable.emailUser)
				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuar'))
				WebUI.delay(GlobalVariable.delay)

				Codigo = utilsSimit.obtenerCodigo()
				WebUI.setText(findTestObject('Object Repository/PlacetoPay/input_CodigoWallet'), Codigo.toString())
				var.COMReferenciaPago = WebUI.getText(findTestObject('Object Repository/PlacetoPay/p_refenciaPago'))
				var.COMLiquidableWeb = letraConstantes.S
				DocumentoEvidencias.add(new DocumentoCargaArchivosDTO(var))


				WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuarWallet'))

				WebUI.click(findTestObject('Object Repository/PlacetoPay/div_tarjeta_pago'))
				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.setText(findTestObject('Object Repository/PlacetoPay/input_codigo_seguridad'), "123")
				WebUI.selectOptionByIndex(findTestObject('Object Repository/PlacetoPay/select_cuotas'), '2')
				WebUI.click(findTestObject('Object Repository/PlacetoPay/button_continuar'))
				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.click(findTestObject('Object Repository/PlacetoPay/button_regresar_al_comercio'))

				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.click(findTestObject('Object Repository/homePublico/respuesta_pagos/button_Inicio'))
				WebUI.delay(GlobalVariable.shortDelay)
				WebUI.click(findTestObject('Object Repository/homePublico/respuesta_pagos/modal encuesta/button_cerrar_Encuesta'))
				WebUI.delay(GlobalVariable.shortDelay)
			}else {
				var.COMReferenciaPago = '------'
				var.COMLiquidableWeb = letraConstantes.N
				DocumentoEvidencias.add(new DocumentoCargaArchivosDTO(var))
				WebUI.navigateToUrl(GlobalVariable.urlHomePublico)
			}

		}
		DocumentoCargaArchivosEvidencias.setCOMReferenciaPagoLote(DocumentoEvidencias)

	}

}