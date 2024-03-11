package util

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
/**
 * 
 * @author jhon.agudelo
 * @email jhon.agudelo@quipux.com
 *
 */
public class Direcciones {

	@Keyword
	public static String getDireccionActual () {
		String userDir = System.getProperty("user.dir")
		return userDir;
	}

	@Keyword
	public static String getDireccionDobleSlashInvertido(String direccion){
		return direccion.replaceAll("\\\\", "\\\\\\\\");
	}

	@Keyword
	public static String getDireccionDobleSlash(String direccion){
		return direccion.replaceAll("////", "////////");
	}

	@Keyword
	public static String voltearSlash(String direccion){
		/* groovylint-disable-next-line DuplicateStringLiteral */
		return direccion.replaceAll("////", "\\\\ ");
	}

	@Keyword
	public static String voltearSlashInvertido(String direccion){
		return direccion.replaceAll("\\\\", "/");
	}

	@Keyword
	public static String getDireccionReporteNodeJS(){
		//file:///D:/IDE/Katalon/KatalonStudio/ServiciosWebSemillitas/reportecucumber/cucumber.html/index.html
		String direccionReporteIncompleta = this.voltearSlashInvertido(this.getDireccionActual() + "/reportecucumber/cucumber.html");
		String direccionReporteCompleta = "file:///" + direccionReporteIncompleta;
		return direccionReporteCompleta;
	}

	@Keyword
	public static String getDireccionImagenDeEjemplo(){
		//    D:\\\\IDE\\\\Katalon\\\\KatalonStudio\\\\Imagenes\\\\BackOffice\\\\Fotocopia_cedula_ciudadania.jpg
		String direccionCompletaDeImagen = this.voltearSlash(this.getDireccionActual() + "\\\\Imagenes\\\\Fotocopia_cedula_ciudadania.jpg");
		return direccionCompletaDeImagen;
	}
	@Keyword
	public static String getDireccionCarpeta(){
		File miDir = new File (".");
		try {
			String dirCar = miDir.getCanonicalPath().toString();
			return dirCar;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
