package util

import internal.GlobalVariable

public class TemporaryVariables {

	//-------------------------------- VARIABLES DE FINALES
	public static final String FECHA_EJECUCION_PRUEBA = new Date().toString().replaceAll(" ", "_").replaceAll(":", "")
	


	//------------------------------ VARIABLES TEMPORALES
	public static int noTakeScreen = 0
	public static boolean gmailSeccionOpen = false
	public static String token = " "
	public static String usuario = ""
	public static String clave = ""
	public static int cantidadCarga = 0
	public static String radicadoCargaArchivos = ""
	public static String radicadoCargaArchivosResolucion = ""
	public static String radicadoCargaArchivosRecaudoLocal = ""
}
