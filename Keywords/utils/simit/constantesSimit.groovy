package utils.simit
public class constantesSimit {

	public static final class modulos {
		public static final String MODULO_SMTADMINISTRACION = commonPath.PATH_MODULOS +'SMTADMINISTRACION/'
		public static final String MODULO_SMTCOMPARENDOS = commonPath.PATH_MODULOS +'SMTCOMPARENDOS/'
		public static final String MODULO_SMTNOVEDADES = commonPath.PATH_MODULOS +'SMTNOVEDADES/'
		public static final String MODULO_SMTRECAUDOS = commonPath.PATH_MODULOS +'SMTRECAUDOS/'
		public static final String MODULO_SMTCARGUES = commonPath.PATH_MODULOS +'STMCARGUES/'
		public static final String MODULO_MANTENIMIENTO = commonPath.PATH_MODULOS +'MANTENIMIENTO/'
	}


	public static final class commonPath{
		public static final String PATH_EVIDENCIAS = 'Include/assets/Evidencias/'
		public static final String PATH_MODULOS = 'Include/assets/properties/'
	}

	public static final class DocumentoCargaArchivo{
		public static final String PATH = 'Data Files/cargaArchivos/'
		public static final String NOMBRE_DOCUMENTO = 'DocumentCargaArchivos'
		public static final String PATH_CARGA_ARCHIVOS_EVIDENCIAS = commonPath.PATH_EVIDENCIAS + 'CargaArchivos/'
	}

	public static final class CursosViales{
		public static final String PATH_CURSOS_VIALES_EVICENCIAS =	commonPath.PATH_EVIDENCIAS + 'CursosViales/'
	}

	public static final class documentosExtenciones {

		public static final String PDF = '.pdf'
		public static final String XML = '.xml'
		public static final String TXT = '.txt'
		public static final String XLSX = '.xlsx'
		public static final String CSV = '.csv'
	}

	public static final class jmeterIntegracion {
		public static final String PATH = 'jmeter/simit2/'
		public static final String PATH_DATOS_LIQUIDACION = PATH + 'Include/assets/Datos de prueba/Liquidacion/'
	}


	public static final enum letraConstantes {
		S,
		N;
	}
}