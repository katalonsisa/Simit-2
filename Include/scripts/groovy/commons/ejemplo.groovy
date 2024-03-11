package commons
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.concurrent.ConcurrentHashMap.KeySetView

import org.apache.commons.lang.RandomStringUtils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import cucumber.api.java.en.Given
import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import model.cargaArchivos.xml.Comparendo
import model.cargaArchivos.xml.cargaArchivoComparendosDTO
import utils.simit.BuildComparendo
import utils.simit.BuildJsonRecibirCurso
import utils.simit.BuildResoluciones
import utils.simit.BuildWSBogota
import utils.simit.constantesSimit.DocumentoCargaArchivo
import utils.simit.DocumentoCargaArchivosEvidencias
import utils.simit.constantesSimit.documentosExtenciones
import utils.simit.constantesSimit.letraConstantes
import utils.simit.constantesSimit.modulos
import utils.simit.utilsSimit
import util.Cookie
import util.Dates
import util.GenerateDocumento
import util.GenerateXml
import util.Placas
import util.PropertiesManager
import util.RandomNames
import util.TemporaryVariables
import util.Utilsbase64
import util.WebServicesUtil
import util.utilsQX
import util.DirrecionesAleatorias
import commons.utilidades
import model.cargaArchivos.csv.ComparendoDTO


public class ejemplo {

	//----------------- variables privadas

	// objecto para crear la peticion rest
	private static RequestObject requestObject
	//objecto para recivir la respuesta de la peticion
	private static ResponseObject responseObject



	@Given('run example')
	def run_example() {
	
		println(utilsQX.getLetra(10))
	}
}
