/* groovylint-disable ClassJavadoc, CompileStatic */
package co.com.quipux.simit2.homePublico.wat

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Entonces
import cucumber.api.java.es.Y
import internal.GlobalVariable
import util.Dates
import util.utilsQX
import commons.utilidades

public class EstadoCuenta {

	//Variables

	//-------------------Dado

	@Dado('que consulto con un documento que tiene mas de una persona asociada')
	def que_consulto_con_un_documento_que_tiene_mas_de_una_persona_asociada(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), "326698")
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
		WebUI.delay(GlobalVariable.delay)
	}

	@Dado('que realizo una consulta con el minimo de caracteres requeridos para realizar una consulta')
	def que_realizo_una_consulta_con_el_minimo_de_caracteres_requeridos_para_realizar_una_consulta(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), utilsQX.getLetra(2))
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
	}

	@Dado('que realiazo una consulta con el maximo de caracteres permitidos en una consulta')
	def que_realiazo_una_consulta_con_el_maximo_de_caracteres_permitidos_en_una_consulta(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), utilsQX.getLetra(22))
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
	}

	@Dado('que realizo una consulta con caracteres especiales no permitidos por la aplicacion')
	def que_realizo_una_consultacon_caracteres_especiales_no_permitidos_por_la_aplicacion(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), utilidades.randomStringEspecialCharter(11))
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
	}

	//--------------------- Cuando
	@Cuando('el sistema recupera la informacion asociada')
	def el_sistema_recupera_la_informacion_asociada(){

		if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/radioButton_selecionarPersona', [('id'): "0"]), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
			WebUI.click(findTestObject('Object Repository/homePublico/radioButton_selecionarPersona', [('id'): "0"]),FailureHandling.STOP_ON_FAILURE)
			WebUI.click(findTestObject('Object Repository/homePublico/button_continuar'),FailureHandling.STOP_ON_FAILURE)
			WebUI.delay(GlobalVariable.shortDelay)
		}

		if(!WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/div_table_comparendosMutas'), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
			KeywordUtil.markErrorAndStop('no hay informacion asociada a al dato entregado')

		}

		//WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/div_cerrarCivi'), FailureHandling.OPTIONAL)

		//WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/div_resumenEstadoCuenta'), GlobalVariable.shortDelay, FailureHandling.STOP_ON_FAILURE)

	}

	//---------------------Entonces
	@Entonces('descargo el estado de cuenta de mi consulta asociada')
	def descargo_el_estado_de_cuenta_de_mi_consulta_asociada(){
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/a_enviarCorreoEstadoCuenta'))
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/a_DescargarEstadoCuenta'),FailureHandling.OPTIONAL)
		WebUI.delay(GlobalVariable.delay)
		WebUI.closeBrowser()

	}

	@Entonces('envio a mi correo el estado de cuenta')
	def envio_a_mi_correo_el_estado_de_cuenta(){
		WebUI.click(
				findTestObject('Object Repository/homePublico/estadoCuenta/a_enviarCorreoEstadoCuenta'))

		WebUI.setText(
				findTestObject('Object Repository/homePublico/estadoCuenta/modalEstadoCuenta/input_email'),
				GlobalVariable.emailUser)
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/modalEstadoCuenta/button_enviar'))
		WebUI.delay(GlobalVariable.delay)
		utilsQX.verifyAlerts()
	}

	@Entonces('seleciono un multa para ver el detalle de la misma')
	def seleciono_un_multa_para_ver_el_detalle_de_la_misma(){
		WebUI.click(
				findTestObject('Object Repository/homePublico/estadoCuenta/a_verDetalle',[('id'):0]))

	}


	@Entonces('el sistema me presenata una alerta de fallo en al consultar')
	def el_sistema_me_presenata_una_alerta_de_fallo_en_al_consultar(){
		utilsQX.verifyAlerts()
	}
	//---------------------Y

	@Y('reviso la llegada del estado de cuenta')
	def reviso_la_llegada_del_estado_de_cuenta(){
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(
				findTestObject("Object Repository/generalObjects/Gmail/span_correo",
				[("hora"): Dates.getTimeStamp("HH"), ("correo"): "Estado de cuenta SIMIT"]),
				FailureHandling.OPTIONAL)
		WebUI.closeBrowser()
	}

	@Y('reviso la informacion de la misma')
	def reviso_la_informacion_de_la_misma(){
		WebUI.closeBrowser()
	}

	@Y('Seleciono una persona para realizar la consulta')
	def Seleciono_una_persona_para_realizar_la_consulta(){
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',[('id'): "0"]), GlobalVariable.shortDelay, FailureHandling.OPTIONAL)) {
			WebUI.click(
					findTestObject('Object Repository/homePublico/radioButton_selecionarPersona',
					[('id'): "0"]))
			WebUI.click(
					findTestObject('Object Repository/homePublico/button_continuar'))
		}

	}



}