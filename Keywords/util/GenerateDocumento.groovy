package util

public class GenerateDocumento {


	/**
	 * Escribe un archivo con los datos mandados y si hay datos en el los remplaza 
	 * @param valor <br> valor 
	 * @param path <br> ruta donde se almacenara el documento <br> <code>Example:</code> Archivos/ejemplos/
	 * @param name <br> nombre del archivo con el tipo de archivo <br> <code>Example:</code> archivo.txt
	 * @author yordan.quintero
	 */

	public static String newfile(String valor, String path, String name ) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		File directory = new File(path);

		if (!directory.exists()){
			assert directory.mkdirs();
		}

		try {
			fichero = new FileWriter(path+name);
			pw = new PrintWriter(fichero);
			pw.println(valor);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
