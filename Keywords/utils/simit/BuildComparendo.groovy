package utils.simit

import org.apache.commons.lang.RandomStringUtils

import com.kms.katalon.core.util.KeywordUtil

import commons.utilidades
import internal.GlobalVariable
import model.cargaArchivos.csv.ComparendoDTO as CSVComparendo
import util.ConnectionDataBase
import util.Dates
import util.DirrecionesAleatorias
import util.Placas
import util.PropertiesManager
import util.RandomNames
import util.utilsQX
import utils.simit.constantesSimit.modulos
import utils.simit.utilsSimit

public class BuildComparendo {

	//------------------------ Variables finales

	//------------------------ conexiones
	private static ConnectionDataBase conexion = new ConnectionDataBase()

	//------------------------ Variables staticas
	public static long valorSalarioAnno = 0
	public static long NumeroComparendo = 0
	public static String ajusteNumeros = ''
	public static int consecutivo = 1
	public static String localidad = ''
	public static String subSede = ''
	public static String idSubSede = ''
	public static List<String> comparendo = []
	public static List<String> infraciones = []
	public static boolean tutorMenorEdad = false
	public static boolean fotoDeteccion = false
	public static boolean comparendoDigital = false
	public static boolean comparendoElectronico = false
	public static boolean fechaNotificacion = false
	public static String valorAdicional = ""


	//------------------------ Construtores

	public static CSVComparendo BuildComparendoSimple(String usuario) {
		getDatos(usuario)

		int infraccionSeleccionado  = utilidades.randomEnSelect2(this.infraciones.size())
		int tipoDocumentoSelect = utilidades.randomEnSelect2(5)
		if(valorAdicional.equals("")) {
			valorAdicional = utilsSimit.consultaValorAdicional(1, idSubSede)
		}
		String valorInfracion = calcularValorInfracion(this.infraciones.get(infraccionSeleccionado).get(1).toString())

		if (tipoDocumentoSelect == 5 || tipoDocumentoSelect == 8   ) {
			tipoDocumentoSelect++
		}
		def DocumentoInfractor = utilsQX.GenerateNIT(tipoDocumentoSelect)


		def comparendo = new CSVComparendo()
		comparendo.setCOMCONSECUTIVO('' + consecutivo++)
		comparendo.setCOMNUMERO(subSede + ajusteNumeros + (NumeroComparendo+consecutivo))
		comparendo.setCOMFECHA(Dates.dateRank(utilidades.randomEnSelect2(-15)))
		comparendo.setCOMHORA(Dates.getTimeStamp('hhmm'))
		comparendo.setCOMDIR(DirrecionesAleatorias.getRandomDireccion(false))
		comparendo.setCOMDIVIPOMUNI(subSede + '')
		comparendo.setCOMLOCALIDADCOMUNA(localidad + '')
		comparendo.setCOMPLACA(Placas.generarPlaca())
		comparendo.setCOMDIVIPOMATRI(subSede + '')
		comparendo.setCOMTIPOVEHI(utilidades.randomEnSelect2(10) + '')
		comparendo.setCOMTIPOSER(utilidades.randomEnSelect2(6) + '')
		comparendo.setCOMCODIGORADIO('0')
		comparendo.setCOMFECHAVENCE(Dates.dateRank(utilidades.randomEnSelect2(360)))
		comparendo.setCOMCODIGOMODALIDAD('1')
		comparendo.setCOMCODIGOPASAJEROS('1')
		comparendo.setCOMINFRACTOR(DocumentoInfractor)
		comparendo.setCOMTIPODOC(tipoDocumentoSelect + '')
		comparendo.setCOMNOMBRE(RandomNames.getName())
		comparendo.setCOMAPELLIDO(RandomNames.getLastName())
		comparendo.setCOMEDADINFRACTOR(utilsQX.getNumero(2))
		comparendo.setCOMDIRINFRACTOR(DirrecionesAleatorias.getRandomDireccion(true))
		comparendo.setCOMNOMBREPROP(RandomNames.getFullName())
		comparendo.setCOMEMAIL('')
		comparendo.setCOMTELEINFRACTOR(utilsQX.getNumero(10))
		comparendo.setCOMIDCIUDAD(subSede + '')
		comparendo.setCOMLICENCIA(utilsQX.getNumero(10))
		comparendo.setCOMCATEGORIA('1')
		comparendo.setCOMSECREEXPIDE(subSede + '')
		comparendo.setCOMTIPOINFRAC('1')
		comparendo.setCOMPLICTRANSITO(utilsQX.getNumero(14))
		comparendo.setCOMDIVIPOLICEN(subSede + '')
		comparendo.setCOMIDENTIFICACION(DocumentoInfractor)
		comparendo.setCOMIDTIPODOCPROP(tipoDocumentoSelect + '')
		comparendo.setCOMPPLACAAGENTE('999')
		comparendo.setCOMOBSERVA('PRUEBA AUTOMATIZADA')
		comparendo.setCOMFUGA('N')
		comparendo.setCOMACCI('N')
		comparendo.setCOMINMOV('N')
		comparendo.setCOMPLICTRANSITO(utilsQX.getNumero(10))
		comparendo.setCOMPATIOINMOVILIZA('N')
		comparendo.setCOMVALOR(valorInfracion)
		comparendo.setCOMVALAD(valorAdicional)
		comparendo.setCOMORGANISMO(subSede + '')
		comparendo.setCOMESTADOCOM('1')
		comparendo.setCOMPOLCA('N')
		comparendo.setCOMINFRACCION(this.infraciones.get(infraccionSeleccionado).get(0))
		comparendo.setCOMVALINFRA("" +  (Long.parseLong(valorInfracion) +  Long.parseLong(valorAdicional)))


		if(this.tutorMenorEdad == true) {
			comparendo.setCOMNOMBRETUTOR(RandomNames.getName())
			comparendo.setCOMAPELLIDOTUTOR(RandomNames.getLastName())
			comparendo.setCOMNRODOCTUTOR(utilsQX.getNumero(10))
			comparendo.setCOMTIPODOCTUTOR("1")
			comparendo.setCOMTIPODOC("2")
		}
		if(this.fotoDeteccion == true) {
			comparendo.setCOMFOTODETECCION("S")
			comparendo.setCOMFECHANOTIFICACION(Dates.dateRank(utilidades.randomEnSelect2(-10)))
		}

		if(this.comparendoDigital == true) {
			comparendo.setCOMFUENTECOMPARENDO("1")
			comparendo.setCOMLATITUD("13")
			comparendo.setCOMLONGITUD("25")
		}

		if(this.comparendoElectronico == true) {
			comparendo.setCOMFOTODETECCION("S")
			comparendo.setCOMFECHANOTIFICACION(Dates.dateRank(utilidades.randomEnSelect2(-10)))
		}
		if(this.fechaNotificacion == true) {
			comparendo.setCOMFECHANOTIFICACION(Dates.dateRank(utilidades.randomEnSelect2(-10)))
		}

		if(comparendo.COMINFRACCION.equals("F")) {
			comparendo.setCOMGRADOALCOHOL(""+utilidades.randomEnSelect2(3))
		}



		return comparendo
	}

	public static List<CSVComparendo> BuildComparendoSimpleMaxivo (String usuario, int cantidad) {
		ArrayList<CSVComparendo> listaComparendos = []

		for (int i = 0; i < cantidad; i++) {
			listaComparendos.add(BuildComparendoSimple(usuario))
		}

		return listaComparendos
	}

	public static CSVComparendo BuildComparendoTutor (String usuario) {
		this.consecutivo = 1
		CSVComparendo comparendoTutor = BuildComparendoSimple(usuario)

		comparendoTutor.setCOMNOMBRETUTOR(RandomNames.getName())
		comparendoTutor.setCOMAPELLIDOTUTOR(RandomNames.getLastName())
		comparendoTutor.setCOMNRODOCTUTOR(utilsQX.getNumero(10))
		comparendoTutor.setCOMTIPODOCTUTOR("1")
		comparendoTutor.setCOMTIPODOC("2")

		return comparendoTutor
	}

	//------------------------ metodos

	private static String calcularValorInfracion(String catidadSalariosMinimos) {
		//return '' + (Long.parseLong(catidadSalariosMinimos) * 3008)
		ArrayList<String> valorUVT
		ArrayList<String> valorSalario
		Object[] objectoConsulta
		if (this.valorSalarioAnno == 0) {
			conexion.connectDBProfileUser("MANTENIMIENTO")
			objectoConsulta = [idSubSede, Dates.getTimeStamp('dd/MM/YYYY')]
			//println PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.uvtActivo', objectoConsulta)
			ArrayList<String> UVTActivo  = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.uvtActivo', objectoConsulta))
			objectoConsulta = [Dates.getTimeStamp('YYYY')]
			if(!UVTActivo.isEmpty() || UVTActivo.size() != 0) {
				valorUVT = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.valorUVT', objectoConsulta ))
			}else {
				valorSalario = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.valorSalarioAnno', objectoConsulta ))
			}
			conexion.closeDatabaseConnection()
			if(!UVTActivo.isEmpty() || UVTActivo.size() != 0) {
				if(valorUVT.get(0).toString().isEmpty() || valorUVT.get(0).toString().equals(null)) {
					KeywordUtil.markError('no se trajo el valor anno')
				}
				this.valorSalarioAnno = Long.parseLong(valorUVT.get(0).toString())
			}else {
				if(valorSalario.get(0).toString().isEmpty() || valorSalario.get(0).toString().equals(null)) {
					KeywordUtil.markError('no se trajo el valor anno')
				}
				this.valorSalarioAnno = Long.parseLong(valorSalario.get(0).toString())
			}
		}
		return '' + Long.parseLong((catidadSalariosMinimos.toInteger() * valorSalarioAnno.toInteger()).toString())

	}





	//------------------------ llamadas a base de datos
	private static getDatos(String usuario) {
		if (!this.infraciones.isEmpty()) {
			return
		}

		Object[] objectoConsulta = [usuario]

		//MANTENIMIENTO
		//conexion.connectDBProfileUser("MANTENIMIENTO")
		conexion.connectDBProfileUser("MANTENIMIENTO")
	//	ArrayList<String> secretaria = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.sudsede', objectoConsulta ))
		ArrayList<String> secretaria = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.sudsede', objectoConsulta ))

		if(secretaria == null && secretaria.isEmpty()) {
			KeywordUtil.markError("El usuario no existe en usuario_sistema")
		}

		this.subSede = secretaria.get(0)
		this.localidad = secretaria.get(1)
		this.idSubSede = secretaria.get(2)
		objectoConsulta = [idSubSede, "1673,1717,1718,1769,1770"]
		ArrayList<String> configuracionesAdicionales = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'comparendos.configuracionSubSede', objectoConsulta ))

		conexion.closeDatabaseConnection()

		for(configuracion in configuracionesAdicionales) {
			switch (configuracion.get(0)) {
				case 1673:
					this.tutorMenorEdad = Boolean.parseBoolean(configuracion.get(1))
					break
				case 1717:
					this.fotoDeteccion = Boolean.parseBoolean(configuracion.get(1))
					break
				case 1718:
					this.comparendoDigital = Boolean.parseBoolean(configuracion.get(1))
					break
				case 1769:
					this.comparendoElectronico = Boolean.parseBoolean(configuracion.get(1))
					break
				case 1770:
					this.fechaNotificacion = Boolean.parseBoolean(configuracion.get(1))
					break
				default :
					break
			}
		}


		// STMNOVEDADES
		conexion.connectDBProfileUser("MANTENIMIENTO")
		objectoConsulta = [this.subSede]
		ArrayList<String> ultimoComparendoSecretaria = conexion.executeQuery(PropertiesManager.getStringSql( modulos.MODULO_MANTENIMIENTO, 'cargaArchivos.comparendos.ulitmoConsecutivoComparendo', objectoConsulta))
		conexion.closeDatabaseConnection()

		if(ultimoComparendoSecretaria == null || ultimoComparendoSecretaria.equals(null)) {
			KeywordUtil.markError("No se pudo obtener el ultimo consecutivo del comparendo")
		}

		String numeroComparendoString = ultimoComparendoSecretaria.get(0).replace("${subSede}", '')
		this.NumeroComparendo =   Long.parseLong(numeroComparendoString)
		while ((ajusteNumeros + NumeroComparendo.toString()).size() < 12) {
			ajusteNumeros += "0"
		}


		// STMCOMPARENDOS
		conexion.connectDBProfileUser("MANTENIMIENTO")

		objectoConsulta = []
		ArrayList<String> infraciones =  conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_MANTENIMIENTO, 'cargaArchivos.comparendos.infracion', objectoConsulta))

		conexion.closeDatabaseConnection()

		if(infraciones == null || infraciones.equals(null)) {
			KeywordUtil.markError("No se encontro ninguna infraccion")
		}

		this.infraciones = infraciones
	}

}
