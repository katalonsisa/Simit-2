package util
import org.openqa.selenium.WebDriver

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory


class Imagen {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	@Keyword
	def static String getFilePath(String nombre) {
		/*user.dir sirve para obtener la ruta del archivo donde esta parado*/
		String userDir = System.getProperty("user.dir")
		String filePath = userDir + "\\" + nombre
		return filePath;
	}
}