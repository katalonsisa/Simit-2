package util

import java.nio.file.Paths

import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.ftp.FTPFile
import org.apache.commons.net.ftp.FTPReply
import org.apache.commons.net.ftp.FTPSClient

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import sun.net.ftp.FtpClient

public class ConnectionFTP {

	/**
	 * Metodo para conectarse con el ftp y iniciar sesión <br> la conexion esta determinadad a durar 3 minutos pero se recomienta cerrar sesion al teminar  
	 * @param modulo <br> modulo donde estan las configuraciones del ftp
	 * @return <b>FTPClient</b> retorna una conexion al ftp con FTPClient 
	 * @throws IOException
	 */
	@Keyword
	private static FTPClient connectAndLogin(String modulo) throws IOException {

		int THREEMINUTE = 3*60000
		int port = ((int) Integer.parseInt(PropertiesManager.getStringSetting(modulo, "setting.ftp.port")))

		FTPClient ftpClient
		if (PropertiesManager.getStringSetting(modulo, "setting.ftp.isFtps").equalsIgnoreCase("true")) {
			if(PropertiesManager.getStringSetting(modulo, "setting.ftp.isExplicito").equalsIgnoreCase("true")){
				ftpClient = new FTPSClient(false)
			}else{
				ftpClient =  new FTPSClient(true)
			}
		} else {
			ftpClient = new FTPClient(true)
		}



		ftpClient.setConnectTimeout(THREEMINUTE)
		ftpClient.setDefaultTimeout(THREEMINUTE)

		ftpClient.connect(PropertiesManager.getStringSetting(modulo, "setting.ftp.host"),port)
		int replyCode = ftpClient.getReplyCode()
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			KeywordUtil.markFailedAndStop("No se pudo obtener una conexion con el ftp")
			return null
		}
		if (PropertiesManager.getStringSetting(modulo, "setting.ftp.isFtps").equals("true")) {
			((FTPSClient) ftpClient).execPBSZ(0)
			((FTPSClient) ftpClient).execPROT("P")
		}

		ftpClient.login(PropertiesManager.getStringSetting(modulo, "setting.ftp.user"),
				PropertiesManager.getStringSetting(modulo, "setting.ftp.pass"))
		ftpClient.enterLocalPassiveMode()
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE)

		ftpClient.setDefaultPort(22)

		replyCode = ftpClient.getReplyCode()
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			KeywordUtil.markFailedAndStop("No se pudo autentificar en el ftp por favor revisar credenciales")
			return null
		}
		showServerReply(ftpClient)
		return ftpClient
	}

	/**
	 * Metodo para buscar en el ftp si existe un documeto o archivo 
	 * @param ftpClient <br> conexion del ftp
	 * @param path <br> ruta donde esta el archivo/documento
	 * @param document <br> nombre del archivo/documentos
	 * @return Si encuentra el documento retorna verdadero de lo contrario retorna falso 
	 * @author kevin.muentes
	 */
	@Keyword
	public static boolean checkFile(FTPClient ftpClient,String path, String document){
		println  ftpClient.printWorkingDirectory()
		showServerReply(ftpClient)
		println ftpClient.getRemotePort()
		ftpClient.changeWorkingDirectory(path)
		showServerReply(ftpClient)
		println  ftpClient.printWorkingDirectory()
		FTPFile[] files = ftpClient.listFiles()
		showServerReply(ftpClient)
		for (FTPFile file : files) {
			println  file.getName()
			if(file.getName().equalsIgnoreCase(document)){
				CloseConnetion(ftpClient)
				return true
			}
		}
		CloseConnetion(ftpClient)
		//KeywordUtil.markFailedAndStop("No se encuentra el archivo buscado en la ruta indicada ")
		return false
	}

	/**
	 * Almacena documetos en el ftp
	 * @param ftpClient </br> conexion con el ftp 
	 * @param path </br> ruta donde se almacenar el documento en el ftp
	 * @param document </br> ruta donde esta el documento con su estencion 
	 * @param nameDocument </br> nombre del documento con el cual se almacenara en el ftp 
	 */

	@Keyword
	public static saveFile(FTPClient ftpClient,String path, String document, String nameDocument){
		println  ftpClient.printWorkingDirectory()
		showServerReply(ftpClient)
		println ftpClient.getRemotePort()
		ftpClient.changeWorkingDirectory(path)
		showServerReply(ftpClient)
		println  ftpClient.printWorkingDirectory()

		try {
			BufferedInputStream buffIn = null;
			buffIn = new BufferedInputStream(new FileInputStream(document));//Ruta del archivo para enviar
			ftpClient.enterLocalPassiveMode();
			showServerReply(ftpClient)
			boolean storeFile = ftpClient.storeFile(nameDocument, buffIn);//Ruta completa de alojamiento en el FTP
			showServerReply(ftpClient)
			if(storeFile){
				println "almaceno"
			}
			buffIn.close(); //Cerrar envio de arcivos al FTP
			ftpClient.logout()
			ftpClient.disconnect()
		} catch (Exception e) {
			e.printStackTrace()
			KeywordUtil.markFailedAndStop("No se pudo almacenar el documento en el ftp: "+ e.getMessage())
		}
	}

	/**
	 *  verifica la conexion al ftp 
	 * @param ftpClient
	 * @return
	 */
	private static boolean checkConnection(FTPClient ftpClient){
		try {
			boolean success = ftpClient
			if(success)
				return true;
			else
				return false;
		}catch(Exception e){

		}
	}

	/**
	 * Mirar el resultado de las peticion al ftp
	 * @param ftpClient
	 */
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

	/**
	 * cierra la connexion con el FTP 
	 * @param connexion <br> conexion al ftp a cerrar 
	 */
	@Keyword
	public static CloseConnetion(FTPClient connexion){
		connexion.logout()
		connexion.disconnect()
	}
}
