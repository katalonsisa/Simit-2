package commons

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.awt.Robot
import java.awt.event.KeyEvent

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
import com.kms.katalon.logging.LogUtil

import co.com.quipux.simit2.backOffice.wsat.cargasTransito.CargaComparendo
import co.com.quipux.simit2.backOffice.wsat.cargasTransito.runnerCargaComparendosMasivosCompleto
import cucumber.api.java.es.Cuando
import cucumber.api.java.es.Dado
import cucumber.api.java.es.Y

import internal.GlobalVariable
import util.GenerateDocumento
import util.TemporaryVariables
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.constantesSimit.documentosExtenciones
import utils.simit.constantesSimit.jmeterIntegracion
import utils.simit.utilsSimit

public class commonsSteps {


	//--------------------------------Dado-----------------------
	@Dado('que realizo una consulta de estado de cuenta con el siguiente dato (.*)')
	def	que_realizo_una_consulta_de_estado_de_cuenta_con_el_siguiente_dato(String dato){
		WebUI.waitForPageLoad(GlobalVariable.delay)
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), dato)
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
		WebUI.delay(GlobalVariable.delay)
	}

	@Dado('que genero datos para jmeter de liquidaciones')
	def que_genero_datos_para_jmeter_de_liquidaciones() {
		def archivo = ''

		for(NumeroLiquidacion in utilsSimit.ConsultaReferenciaLiquidacion(DocumentoCargaArchivosEvidencias.getListComparendosLiquidacion())) {
			String Numero = NumeroLiquidacion.toString().replace("[", "").replace("]", "")
			println(DocumentoCargaArchivosEvidencias.getComparendoLiquidado(Numero).getCOMValor())
			archivo += Numero + ',' + DocumentoCargaArchivosEvidencias.getComparendoLiquidado(Numero).getCOMValor().toString() +"\n"
		}
		GenerateDocumento.newfile(archivo, jmeterIntegracion.PATH_DATOS_LIQUIDACION, "Liquidaciones" + documentosExtenciones.CSV)

	}

	@Dado('que realizo una consulta de estado de cuenta')
	def	que_realizo_una_consulta_de_estado_de_cuenta(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), utilsSimit.documentoPersonaObligacion())
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
		WebUI.delay(40)

	}

	@Dado('que realizo una consulta de estado de cuenta con curso')
	def	que_realizo_una_consulta_de_estado_de_cuenta_con_curso(){
		WebUI.setText(findTestObject('Object Repository/homePublico/Input_busqueda'), utilsSimit.documentoPersonaCursoVial())
		WebUI.click(findTestObject('Object Repository/homePublico/button_busqueda'))
		WebUI.delay(GlobalVariable.delay)
	}

	@Dado('que ingreso al portal de backOffice con usuario (.*) y clave (.*)')
	def que_ingreso_al_portal_de_backoffice(String usuario, String clave) {
		WebUI.openBrowser(GlobalVariable.urlBackOffice)
		WebUI.maximizeWindow()
		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_user'), usuario)
		TemporaryVariables.usuario = usuario
		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_password'), clave)
		WebUI.click(findTestObject('Object Repository/BackOffice/Login/button_ingresar'))
	}
	
	@Dado('que ingreso al portal de backOffice')
	def que_ingreso_al_portal_de_backoffice() {
		WebUI.openBrowser(GlobalVariable.urlBackOffice)
		WebUI.maximizeWindow()
		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_user'), GlobalVariable.UserDefault)
		TemporaryVariables.usuario = GlobalVariable.UserDefault
		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_password'), GlobalVariable.PasswordDefault)
		WebUI.click(findTestObject('Object Repository/BackOffice/Login/button_ingresar'))
	}

	@Dado('que ingreso al portal de GA')
	def que_ingreso_al_portal_de_GA() {
		
		WebUI.openBrowser(GlobalVariable.urlBackOffice)
		WebUI.maximizeWindow()
//		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_user'), GlobalVariable.userDefaul)
//		WebUI.setText(findTestObject('Object Repository/BackOffice/Login/input_password'),  GlobalVariable.passwordDefault)
//		WebUI.click(findTestObject('Object Repository/BackOffice/Login/button_ingresar'))
//		WebUI.delay( GlobalVariable.delay)
		WebUI.navigateToUrl(GlobalVariable.urlGA)
		//WebUI.maximizeWindow()
		WebUI.setText(findTestObject('Object Repository/GA/Login/input_username'), GlobalVariable.UserGADefaul)
		WebUI.setEncryptedText(findTestObject('Object Repository/GA/Login/input_password'), GlobalVariable.PasswordGADefaul)
		WebUI.click(findTestObject('Object Repository/GA/Login/Button_Ingresar'))
	}



	@Dado('que tengo comparendos por pagar')
	def que_tengo_comparendos_por_pagar() {
		if(DocumentoCargaArchivosEvidencias.getList().get(0).COMConsecutivo.equals('') || DocumentoCargaArchivosEvidencias.getListComparendosSinLiquidar().isEmpty()) {
			Object[] obj = ['admin', 'admin']
			CargaComparendo.invokeMethod('que_cargo_un_comparendo_con_usuario_y_clave', obj)
			CargaComparendo.invokeMethod('el_sistema_procesa_el_archivo', null)
			CargaComparendo.invokeMethod('resivo_una_respuesta_de_la_carga', null)
		}else {
			if(true) {

			}
		}
	}



	@Dado('que se realizo la solicitud de liquidar un comparendo por medio de la plataforma de simit')
	def que_se_realizo_la_solicitud_de_liquidar_un_comparendo_por_medio_de_la_plataforma_de_simit() {
		String[] logTags = ["@pruebaLiquidacionMasiva"] as String[]
		CucumberKW.runFeatureFileWithTags("Include/features/FuncionalidadesCompletas/cargasComparendos/cargaComparendosMasivoCompleto.feature",logTags, FailureHandling.STOP_ON_FAILURE)
	}


	//--------------------------Cuando---------------------
	@Cuando("Abro el navegador y me dirigo al home pulbico de simit")
	def Abro_el_navegador_y_me_dirigo_al_home_pulbico_de_simit(){
		WebUI.openBrowser(GlobalVariable.urlHomePublico)
		WebUI.maximizeWindow()
		WebUI.delay(GlobalVariable.delay)
	}


	//-------------------------Entonces------------------------


	//--------------------------Y-------------------------------
	@Y('salto el tutorial')
	def salto_el_tutorial(){
		WebUI.delay(GlobalVariable.delay)
		/*
		 * WebUI.delay(GlobalVariable.delay)
		 * WebUI.click(findTestObject('Object Repository/homePublico/tutorial/a_saltarTutorial'))
		 * WebUI.acceptAlert(FailureHandling.OPTIONAL)
		 * WebUI.click(findTestObject('Object Repository/homePublico/span_cerrarModal'), FailureHandling.OPTIONAL)
		 */
	}




	@Y('entro al correo')
	def entro_al_correo(){
		WebUI.openBrowser("gmail.com")
		WebUI.maximizeWindow()
		WebUI.setText(findTestObject('Object Repository/generalObjects/Gmail/loginGmail/input_correo'), GlobalVariable.emailUser)
		WebUI.click(findTestObject('Object Repository/generalObjects/Gmail/loginGmail/button_siguienteCorreo'))
		WebUI.delay(GlobalVariable.delay)
		WebUI.setEncryptedText(findTestObject('Object Repository/generalObjects/Gmail/loginGmail/input_password'), GlobalVariable.emailPassword)
		WebUI.click(findTestObject('Object Repository/generalObjects/Gmail/loginGmail/button_siguiente'))
	}


	@Y("selecciono la opcion (.*) del modulo (.*)")
	def selecciono_la_opcion (String opcion , String modulo ){
		WebUI.delay(GlobalVariable.shortDelay)
		Robot rob = new Robot()
		WebUI.mouseOver(findTestObject('Object Repository/generalObjects/menu/div_menuOpciones'))
		WebUI.delay(GlobalVariable.shortDelay)
		rob.keyPress(KeyEvent.VK_LEFT)
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': modulo.trim() ]))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': opcion.trim()]))
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': modulo.trim() ]),FailureHandling.OPTIONAL)
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/GeneralObjects/menu/bt_ConsultarAtenciones', ['opcion': modulo.trim() ]))
		WebUI.mouseOver(findTestObject('Object Repository/generalObjects/menu/div_desOver'),FailureHandling.OPTIONAL)
	}


	@Y("selecciono el modulo de (.*) y el submenu de (.*) y la opcion (.*)")
	def selecciono_la_opcion_del_sudmodulo_del_modulo(String modulo ,String submenu , String opcion){
		WebUI.delay(GlobalVariable.shortDelay)
		Robot rob = new Robot()
		WebUI.mouseOver(findTestObject('Object Repository/generalObjects/menu/div_menuOpciones'))
		WebUI.delay(GlobalVariable.shortDelay)
		rob.keyPress(KeyEvent.VK_LEFT)
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': modulo.trim() ]))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': submenu.trim()]))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': opcion.trim()]))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': submenu.trim()]))
		WebUI.click(findTestObject('Object Repository/generalObjects/menu/a_opcionModulo', ['opcion': modulo.trim() ]))
		WebUI.delay(GlobalVariable.shortDelay)
		WebUI.mouseOver(findTestObject('Object Repository/generalObjects/menu/div_desOver'))
	}





}
