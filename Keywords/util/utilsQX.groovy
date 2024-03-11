package util

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import org.apache.commons.lang.RandomStringUtils
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



public class utilsQX {

	/**
	 * realiza la verifiacion de las alertas emergentes en el front 
	 */
	@Keyword
	public static void verifyAlerts(){
		WebDriver driver = DriverFactory.getWebDriver()
		List<WebElement> obj = driver.findElements(By.xpath("//div[@class ='uk-notify-message']/div"))
		for(WebElement element:obj) {
			WebUI.comment("Verific alert")
			String res = element.getAttribute("class")
			if(res.equalsIgnoreCase("uk-notify-message-success")){
				KeywordUtil.markPassed("Exitoso: " + res)
				break
			}else if(res.equalsIgnoreCase("uk-notify-message-danger")){
				KeywordUtil.markFailedAndStop("Fallo: " + res)
			}
		}
	}

	/**
	 * Retorna una cadena de letras
	 * @param length
	 * @return
	 */
	@Keyword
	private static String getLetra(int length) {
		String charset = (('A'..'Z')).join()
		String randomString = RandomStringUtils.random(length, charset.toCharArray())
		return randomString
	}

	/**
	 * Retorna un numero aleatorio 
	 * @param length 
	 * @return
	 */
	@Keyword
	private static String getNumero(int length) {
		String charset = (('0'..'9')).join()
		String randomString = RandomStringUtils.random(length, charset.toCharArray())
		return randomString
	}

	/**
	 * Metodo para que el json que se resive no queden espacios o datos en null
	 * @param json
	 * @return json sin datos vacios 
	 */
	@Keyword
	public static String JsonNoNull(String json){
		def aux = json.replaceAll("\"\\w+\": null,", "")
		aux = aux.replaceAll("\"\\w+\":null,","")
		aux = aux.replaceAll("\"\\w+\": \"null\",", "")
		aux = aux.replaceAll("\"\\w+\":\"null\",", "")
		aux = aux.replaceAll("\"\\w+\":\"null\"", "")
		aux = aux.replaceAll("\"\\w+\": \"null\"", "")
		aux = aux.replaceAll("\"\\w+\":0,","")
		aux = aux.replaceAll("\"\\w+\":0","")
		aux = aux.replaceAll("\"\\w+\": 0,","")
		aux = aux.replaceAll("\"\\w+\": 0","")
		aux = aux.replaceAll("\"\\w+\":\\[\\],","")
		aux = aux.replaceAll("\"\\w+\":\\[\\]","")
		aux = aux.replaceAll("\"\\w+\": \\[\\],","")
		aux = aux.replaceAll("\"\\w+\": \\[\\]","")
		aux = aux.replaceAll(",}", "}")
		aux = aux.replaceAll(", }", "}")
		aux = aux.replaceAll(",  }", "}")
		return aux
	}

	/**
	 * Click Web element
	 */
	@Keyword
	public static clickUsingJS(TestObject to) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to,30)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()',element)
	}

	/**
	 * uploadFile in web 
	 * @param to 
	 * @param filePath
	 * @return
	 */
	@Keyword
	public static uploadFile(TestObject to, String filePath) {
		WebUI.click(to)
		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * 
	 * Metodo que crea un documento de identidad aleatorio
	 * @param tipoDocumento
	 * @return 
	 */
	public static String GenerateNIT(int tipoDocumento) {
		def tiposDocumento = [1:10, 2:10, 3:6, 4:10, 5:11, 6:14, 7:9, 8:11, 9:11]

		String Documento = utilsQX.getNumero(tiposDocumento[tipoDocumento])

		if(tipoDocumento == 7 ) {
			Documento = "F" + Documento
		}

		if (Documento.startsWith('0')) {
			println Documento + ' starts with 0'
			Documento = GenerateNIT(tipoDocumento)
		}
		return Documento
	}
}
