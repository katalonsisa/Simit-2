package util

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.Scenario

public class CapturaPantalla {
	@Keyword
	def capturarPantalla(Scenario scenario) {
		try {
			scenario.write("Pantalla del Paso")
			BufferedImage screenshot = ImageIO.read(new File(WebUI.takeScreenshot()))
			/*Transforma la imagen en bytes*/
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( screenshot, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			scenario.embed(imageInByte, "image/png")
		} catch (IOException e) {
		}
	}
}
