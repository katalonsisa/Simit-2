package co.com.quipux.simit2.homePublico.wat

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.xbill.DNS.utils.hexdump

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

import cucumber.api.java.es.Y
import cucumber.api.java.es.Entonces
import internal.GlobalVariable
import util.utilsQX

public class cursosViales {


	@Entonces('consulto mi historial de cursos')
	def consulto_mi_historial_de_cursos(){
		if(WebUI.verifyElementPresent(findTestObject('Object Repository/homePublico/estadoCuenta/a_historialCursos'), GlobalVariable.delayWFEP, FailureHandling.OPTIONAL)){
			WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/a_historialCursos'))
		}else{
			WebUI.click(findTestObject('Object Repository/homePublico/paz y salvo/a_historialCursos'))
		}
		WebUI.delay(GlobalVariable.delay)
	}


	@Entonces('mando al correo del comprovante del curso cursado')
	def mando_al_correo_del_comprovante_del_curso_cursado(){
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/historialCursos/em_enviarCorreoCurso'))
		WebUI.delay(GlobalVariable.delay)
		WebUI.setText(findTestObject('Object Repository/homePublico/estadoCuenta/historialCursos/modalCursos/input_correo'), GlobalVariable.emailUser)
		WebUI.click(findTestObject('Object Repository/homePublico/estadoCuenta/historialCursos/modalCursos/button_enviar'))
		WebUI.delay(GlobalVariable.shortDelay)
		utilsQX.verifyAlerts()
	}

	@Entonces('Seleciono el en la tabla de comparendos el curso que esto cursado')
	def Seleciono_el_en_la_tabla_de_comparendos_el_curso_que_esto_cursado(){
	}



	@Y("descargo el comprovante de del curso")
	def descargo_el_comprovante_de_del_curso(){
	}

	@Y('mando el comprovante del curso que estoy cursando')
	def mando_el_comprovante_del_curso_que_estoy_cursando(){
	}
}
