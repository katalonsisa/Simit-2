package commons

import com.kms.katalon.core.util.KeywordUtil

import util.Placas

public class utilidades {

	public static int randomEnSelect2(int numeroDeItems){
		int numero;
		numero = (int) (Math.random() * numeroDeItems + 1);
		if(numero == numeroDeItems){
			numero--
		}
		return numero;
	}

	public static void clearDirectori(String dir){
		try {
			File fil = new  File(dir)
			File[] documentos = fil.listFiles()
			if(documentos != null){
				for (doc in documentos) {
					if(!doc.getName().equals(".meta")){
						if(doc.delete()){
							println  "se elimino crectamente"
						}else{
							KeywordUtil.markWarning("No se pudo eliminar el archivo")
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace()
		}
	}


	public static void moveFile(String originPath, String destinationPath){
		File fil = new File(originPath)
		if(fil.renameTo(new File(destinationPath+fil.getName()))){
			fil.delete();
			KeywordUtil.logInfo("File is moved successful from "+ originPath +" to "+ destinationPath)
		}else{
			KeywordUtil.markWarning("File is failed to move")
		}
	}

	public static String randomStringEspecialCharter(int lengh){
		String result = ""
		for(int i =0 ; i <= lengh; i++){
			int ramdon = (Math.random() * 4)
			switch(ramdon){
				case 1:
					result += Placas.generarVocal();
					break
				case 2:
					result += Placas.generarConsonante();
					break
				case 3:
					result += Placas.generarRandomChar('/:*¿?"<>|')
					break
				default:
					result += Placas.generarRandomChar('/:*¿?"<>|')
					break
			}
		}
		return result
	}
}
