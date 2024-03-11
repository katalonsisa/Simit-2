package util

import com.kms.katalon.core.annotation.Keyword

public class Reportes {
	@Keyword
	def generarReporte(String jsonDir, String repPath, String timeIni, String timeFin, titulo,app,modulo,version){
		Runtime rt = Runtime.getRuntime()
		//def commands = ["node","multi"]
		def String sSistemaOperativo = System.getProperty("os.name");
		def String vSistemaOperativo = System.getProperty("os.version");
		def command = "node multi" + ' ' + jsonDir + ' ' + repPath +' "' + timeIni + '" "'+ timeFin +'" ' + sSistemaOperativo +' ' + vSistemaOperativo +' "'+ titulo+'"'+' "'+ app+'"'+' "'+ modulo+'"'+' "'+ version+'"'
		Process proc = rt.exec(command)
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()))
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream())) // read the output from the command
		println("Here is the standard output of the command:\n")
		def String s = null
		while ((s = stdInput.readLine()) != null) { System.out.println(s) } // read any errors from the attempted command
		println("Here is the standard error of the command (if any):\n")
		while ((s = stdError.readLine()) != null) { System.out.println(s) }
	}
}
