/* groovylint-disable ClassJavadoc, ClassNameSameAsFilename, CompileStatic */
package co.com.quipux.simit2.homePublico.wat

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import util.Dates
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.utilsSimit

import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.util.KeywordUtil

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y

/* groovylint-disable-next-line ClassName */
class liquidacion {

	//--------------------------variables
	WebDriver driver = DriverFactory.getWebDriver()
	String totalPago = ''
	long valorTotal = 0

	//-------------------------Dado

	@Dado('que tengo obligaciones por pagar ')
	def que_tengo_obligaciones_por_pagar() {
		def listaComparendosPendientesPagos = DocumentoCargaArchivosEvidencias.getListComparendosSinLiquidar()
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'),
				listaComparendosPendientesPagos.get(0).getCOMDocumentoInfractor())
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
		WebUI.delay(GlobalVariable.delay)
	}

	//-------------------------Cuando

	//-------------------------Entonces

	//-------------------------Y
	@Y('seleciono las obligaciones que voy a pagar')
	def seleciono_las_obligaciones_que_voy_a_pagar() {
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.scrollToElement(
				findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'),
				GlobalVariable.shortDelay)
		WebUI.delay(GlobalVariable.shortDelay)

		utilsSimit.clickUsingJS(
				findTestObject('Object Repository/homePublico/estadoCuenta/checkbox_multa', [('id'):'0']))
	}

	@Y('seleciono la opcion de imprimir documento para pago')
	def seleciono_la_opcion_de_imprimir_documento_para_pago() {
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/button_imprimirCuponDePago'))
	}

	@Y('descargo el documento de cupon de pago')
	def descargo_el_documento_de_cupon_de_pago() {
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/modalCupon/button_descargarCupon'))
		WebUI.delay(GlobalVariable.shortDelay)
	}

	@Y('envio al correo el cupon de pago')
	def envio_al_correo_el_cupon_de_pago() {
		WebUI.setText(
				findTestObject('Object Repository/homePublico/estadoCuenta/modalCupon/input_email'),
				GlobalVariable.emailUser)
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/modalCupon/button_enviarLiquidacion'))
		WebUI.delay(GlobalVariable.delay)
	}

	@Y('reviso la llegada del cupon de pago')
	def reviso_la_llegada_del_cupon_de_pago() {
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(
				findTestObject('Object Repository/generalObjects/Gmail/span_correo',
				[("hora"): Dates.getTimeStamp('HH'), ("correo"): 'Liquidación SIMIT']))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.closeBrowser()
	}

}
