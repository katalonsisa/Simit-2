package util

import java.util.regex.Pattern

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class PropertiesManager {

	//private static final String RUTA = "Include/assets/properties/SqlQuerys.properties"

	/**
	 * Metodo que recupera un SQL ubicado en el sqlQuerys.properties
	 * @param propiedad  <br> el nombre con el cual se hace referencía al SQL que se desea recuperar 
	 * @param parametros <br> se envia los parametros solicitados por el SQL en el orden definidó en el propertis de la siguiente forma <b>Ejemplo </b> <br> ["cadena"] <br> [12] <br> [true] <br> ["cadena",12,true] <br> cuando no se envían parametros se debe mandar null 
	 * @param modulo <br> donde esta ubicadó el sqlQuerys.properties 
	 * @return Retorna un String (cadena) con el SQL modificadó con los parametros enviados  
	 * @author kevin.muentes
	 */
	@Keyword
	public static String getStringSql(String modulo, String propiedad, Object[] parametros){
		String string = null
		InputStream inputStream = null
		try {
			inputStream = new FileInputStream(new File(modulo + "SqlQuerys.properties"))
			Properties properties = new Properties()
			properties.load(inputStream)

			// Retorno el string asociado a la cadena especificada
			if (properties.containsKey(propiedad)) {
				string = properties.getProperty(propiedad);
			} else {
				KeywordUtil.markErrorAndStop("Propiedad " + propiedad + " no encontrada en " + modulo)
				throw new Exception("Propiedad " + propiedad + " no encontrada en " + modulo);
			}

			if (parametros != null) {
				for (int i = 0; i < parametros.length; i++) {
					string = string.replaceAll(Pattern.quote("{" + i + "}"), parametros[i].toString())
				}
			}
		} catch (IOException io) {
			KeywordUtil.markErrorAndStop("No existe el archivo: "+ modulo)
			//KeywordUtil.markErrorAndStop(io.detailMessage)
		} finally {
			if (inputStream != null) {
				inputStream.close()
			}
		}
		return string
	}
	/**
	 * Metodo para llamar una propiedad de las configuraciones del modulo 
	 * @param modulo
	 * @param propiedad
	 * @return retorna el valor de la configuracion 
	 * @author kevin.muentes
	 */
	@Keyword
	public static String getStringSetting(String modulo, String propiedad){
		String string = null
		InputStream inputStream = null
		try {
			inputStream = new FileInputStream(new File(modulo+"Configuracion.properties"))
			Properties properties = new Properties()
			properties.load(inputStream)
			string = properties.getProperty(propiedad)
		} catch (IOException io) {
			KeywordUtil.markError("No existe el archivo: "+modulo+"Configuracion.properties")
			KeywordUtil.markErrorAndStop(io.detailMessage)
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return string
	}


	/**
	 * traer el mensaje de error 
	 * @param modulo
	 * @param propiedad
	 * @return retorna el valor de la configuracion
	 * @author kevin.muentes
	 */
	@Keyword
	public static String getMessages(String modulo, String propiedad, Object[] parametros){
		String string = null
		InputStream inputStream = null
		try {
			inputStream = new FileInputStream(new File(modulo+"Messages.properties"))
			Properties properties = new Properties()
			properties.load(inputStream)

			// Retorno el string asociado a la cadena especificada
			if (properties.containsKey(propiedad)) {
				string = properties.getProperty(propiedad);
			} else {
				KeywordUtil.markErrorAndStop("Propiedad " + propiedad + " no encontrada en " + modulo+"Messages.properties")
				throw new Exception("Propiedad " + propiedad + " no encontrada en " + modulo+"Messages.properties");
			}
			if (parametros != null) {
				for (int i = 0; i < parametros.length; i++) {
					string = string.replaceAll(Pattern.quote("{" + i + "}"), parametros[i][0].toString())
				}
			}
		} catch (IOException io) {
			KeywordUtil.markError("No existe el archivo: "+modulo+"Messages.properties")
			KeywordUtil.markErrorAndStop(io.detailMessage)
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return string
	}
}
