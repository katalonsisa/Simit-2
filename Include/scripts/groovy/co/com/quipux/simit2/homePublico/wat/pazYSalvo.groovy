/* groovylint-disable-next-line PackageName */
package co.com.quipux.simit2.homePublico.wat

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import commons.utilidades
import cucumber.api.java.es.Y
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import internal.GlobalVariable
import util.utilsQX

public class pazYSalvo {


	@Dado('que seleciono la opcion de solicitar paz y salvo')
	def que_seleciono_la_opcion_de_solicitar_paz_y_salvo(){
		WebUI.click(findTestObject('Object Repository/homePublico/a_solicitarPazYSalvo'))
	}

	@Cuando('el sistema al realizar la consulta de paz y salvo')
	def el_sistema_al_realizar_la_consulta_de_paz_y_salvo() {
		WebUI.delay(GlobalVariable.shortDelay)
	}

	@Entonces('me muestra la informacion de que no tengo multas asociadas')
	def me_muestra_la_informacion_de_que_no_tengo_multas_asociadas() {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay)
	}

	@Y('Descargo el comprobante de paz y salvo')
	def Descargo_el_comprobante_de_paz_y_salvo() {
		if (WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/a_guardarPazYSalvo'),GlobalVariable.shortDelay,FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/a_guardarPazYSalvo'))
			WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/model_pazYSalvo/button_descargarPazYSalvo'),FailureHandling.OPTIONAL)
		}else {
			WebUI.doubleClick(findTestObject('Object Repository/homePublico/paz y salvo/a_descargar'),FailureHandling.OPTIONAL)
		}
	}

	@Y('Envio al correo el comprobante de paz y salvo')
	def Envio_al_correo_el_comprobante_de_paz_y_salvo() {
		if (WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/a_guardarPazYSalvo'), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/a_guardarPazYSalvo'))
			WebUI.setText(findTestObject('Object Repository/homePublico/estadoCuenta/model_pazYSalvo/input_emailPazYSalvo'), GlobalVariable.emailUser)
			WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/model_pazYSalvo/button_enviaCorreoPazYSalvo'))
		}else {
			WebUI.setText(findTestObject('Object Repository/homePublico/paz y salvo/input_correo'), GlobalVariable.emailUser)
			WebUI.click(findTestObject('Object Repository/homePublico/paz y salvo/button_enviar'))
		}
	}

	@Y('reviso que no me muestre las opciones de generar paz y salvo')
	def reviso_que_no_me_muestre_las_opciones_de_generar_paz_y_salvo() {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/homePublico/paz y salvo/a_descargar'), GlobalVariable.delayWFEP)
	}

	@Y('realizo la consulta con un documento sin obligaciones pendientes')
	def realizo_la_consulta_con_un_documento_sin_obligaciones_pendientes() {
	}

	@Y('realizo la solicitud con un documento sin obligaciones pendientes')
	def realizo_la_solicitud_con_un_documento_sin_obligaciones_pendientes() {
		String documento = utilsQX.getLetra(10)
		WebUI.setText(findTestObject('Object Repository/homePublico/modal_solicitarPazYSalvo/input_documento'), documento)
		//WebUI.selectOptionByIndex(findTestObject('Object Repository/homePublico/modal_solicitarPazYSalvo/select_tipoDocumento') , '2')
		WebUI.setText(findTestObject('Object Repository/homePublico/modal_solicitarPazYSalvo/input_correoElectronico'),GlobalVariable.emailUser)
		WebUI.click(findTestObject('Object Repository/homePublico/modal_solicitarPazYSalvo/button_continuar'))
	}
}
