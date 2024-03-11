package util

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

public class Utilsbase64 {

	/**
	 * Este metodo Codifica en base 64 un texto
	 * @param texto
	 * @return El texto en base 64
	 * @author kevin.muentes
	 */

	@Keyword
	public static String encodeText(String texto){
		return texto.bytes.encodeBase64().toString()
	}


	/**
	 * Este metodo codifica en base 64 un archivo 
	 * @param ruta
	 * @return Retorna el contenido del archivo en base 64 
	 * @author kevin.muentes
	 */
	@Keyword
	public static String encodeTextFile(String ruta){
		try {
			if(!ruta.empty && !ruta.equals("")){
				File archivo = new File(ruta)
				if (archivo.exists()) {
					return encodeText(archivo.getText('UTF-8'))
				}else{
					KeywordUtil.markFailedAndStop("El archivo no existe en la ruta indicada")
				}
			}else{
				KeywordUtil.markFailedAndStop("La ruta esta vacia porfavor mandar una ruta")
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
	/**
	 * @param path
	 * @return 
	 */
	@Keyword
	public static String encodeFile(String path){
		try {
			if(!path.empty && !path.equals("")){
				File archivo = new File(path)
				if (archivo.exists()) {
					byte[] input_file = FileUtils.readFileToByteArray(archivo)
					byte[] encodedBytes = Base64.getEncoder().encode(input_file)
					return new String(encodedBytes)
				}else{
					KeywordUtil.markFailedAndStop("El archivo no existe en la ruta indicada")
				}
			}else{
				KeywordUtil.markFailedAndStop("La ruta esta vacia porfavor mandar una ruta")
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}

	/**
	 * Metodo para decodificar un texto en base 64
	 * @param texto
	 * @return texto desifrado 
	 * @author kevin.muentes
	 */
	@Keyword
	public static String decodeText (String texto){
		return texto.decodeBase64()
	}

	/**
	 * Metodo para desifrar un archivo en base 64
	 * @param ruta
	 * @return texto del documento desifrado
	 * @author kevin.muentes
	 */
	@Keyword
	public static String decodeTextFile(String ruta){
		try {
			if(!ruta.empty){
				File archivo = new File(ruta)
				if (archivo.exists()) {
					return decodeText(archivo.getText('UTF-8'))
				}else{
					KeywordUtil.markFailedAndStop("El archivo no existe en la ruta indicada")
				}
			}else{
				KeywordUtil.markFailedAndStop("La ruta esta vacia porfavor mandar una ruta")
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}
}
