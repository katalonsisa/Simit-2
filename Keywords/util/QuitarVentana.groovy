package util

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


import com.kms.katalon.core.annotation.Keyword

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class QuitarVentana {

	@Keyword
	def quitarVentana(){

		WebUI.openBrowser(GlobalVariable.url)
		WebUI.maximizeWindow()

		WebDriver driver = DriverFactory.getWebDriver()


		List<WebElement> lista = driver.findElements(By.xpath('//*[@id="modalInformativo"]/div/div/div[1]/button'));
		if(lista.get(0).isDisplayed()){

			WebUI.click(findTestObject('frontOfficePublico/button_cerrar'))
		}
	}
}
