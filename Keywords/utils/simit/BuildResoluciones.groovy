package utils.simit

import com.kms.katalon.core.util.KeywordUtil

import model.cargaArchivos.csv.ComparendoDTO
import model.cargaArchivos.csv.ResolucionDTO
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.ConnectionDataBase
import util.Dates
import util.PropertiesManager
import utils.simit.constantesSimit.modulos

public class BuildResoluciones {


	//------------------------ conexiones
	public static ConnectionDataBase conexion = new ConnectionDataBase();

	//----------------------------- variables
	public static int consecutivo = 1
	public static long NumeroResolucion = 0
	public static String localidad = ''
	public static String subSede = ''
	public static String idSubSede = ''
	public static ArrayList<DocumentoCargaArchivosDTO> listaComparendos


	//------------------------------ Builders

	static ResolucionDTO resolucionSimpleTipo1(String usuario ) {
		getData(usuario)

		ArrayList<DocumentoCargaArchivosDTO> listaComparendosPP = DocumentoCargaArchivosEvidencias.getListComparendosSinLiquidar()

		if(listaComparendosPP.isEmpty() || listaComparendosPP.size() < 1) {
			listaComparendosPP = listaComparendos
		}


		def resoluciondto = new ResolucionDTO()

		resoluciondto.setRESCONSECUTIVO(consecutivo+"")
		resoluciondto.setRESNUMERO(Dates.getTimeStamp('ddmmyyyy').toString() +'10'+ NumeroResolucion)
		resoluciondto.setRESFECHA(Dates.getTimeStamp('dd/mm/yyyy'))
		resoluciondto.setRESTIPORES('1')
		resoluciondto.setRESCOMP(listaComparendosPP.get(consecutivo-1).COMNumeroComparendo)
		resoluciondto.setRESCOMPF(listaComparendosPP.get(consecutivo-1).COMFechaComparendo)
		resoluciondto.setRESNIPINFRAC(listaComparendosPP.get(consecutivo-1).COMDocumentoInfractor)
		resoluciondto.setRESTIPODOC(listaComparendosPP.get(consecutivo-1).COMTipoDocumento)
		resoluciondto.setRESNOMBRE(listaComparendosPP.get(consecutivo-1).COMNombreInfractor)
		resoluciondto.setRESAPELLIDO(listaComparendosPP.get(consecutivo-1).COMApellidoInfractor)
		resoluciondto.setRESDIRINFRACTOR('')
		resoluciondto.setRESTELEINFRACTOR('')
		resoluciondto.setRESIDCIUDAD(listaComparendosPP.get(consecutivo-1).COMDivipo)
		resoluciondto.setRESVALOR(listaComparendosPP.get(consecutivo-1).COMValor)
		resoluciondto.setRESVALAD('0')
		resoluciondto.setFOTOMULTA('N')
		resoluciondto.setRESFOTODETECCION('N')
		resoluciondto.setRESORGANISMO('')
		resoluciondto.setRESCOMPOLCA('N')
		resoluciondto.setRESINFRACCION('')
		resoluciondto.setRESVALINF(listaComparendosPP.get(consecutivo-1).COMValor)
		resoluciondto.setRESVALPAG(listaComparendosPP.get(consecutivo-1).COMValor)
		resoluciondto.setID_TIPO_DOC_TUTOR('')
		resoluciondto.setRESFECHAMANDTO('1/01/1900')


		return resoluciondto
	}



	public static ResolucionDTO resolucionSimpleTipo16(String usuario) {


		def resoluciondto = resolucionSimpleTipo1(usuario)

		resoluciondto.setRESNUMANT("")

		return resoluciondto
	}



	private static getData(String usuario) {
		//cargaArchivos.resoluciones.comparendoPendiente
		Object[] objectoConsulta = [usuario]

		//SMTADMINISTRACION
		conexion.connectDBProfileUser("SMTADMINISTRACION")
		ArrayList<String> secretaria = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTADMINISTRACION, 'comparendos.sudsede', objectoConsulta ))

		if(secretaria == null || secretaria.equals(null)) {
			KeywordUtil.markError("El usuario no existe en usuario_sistema")
		}

		this.subSede = secretaria.get(0)
		this.localidad = secretaria.get(1)
		if(localidad.contains('Bogota')) {
			localidad = 'Bogota'
		}

		this.idSubSede = secretaria.get(2)

		conexion.connectDBProfileUser("SMTCOMPARENDOS")

		objectoConsulta = [Dates.getFechaActual(-20) , localidad]
		ArrayList<String> comparendos =  conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS, 'cargaArchivos.resoluciones.comparendoPendiente', objectoConsulta))

		conexion.closeDatabaseConnection()

		def temporal = new ArrayList<DocumentoCargaArchivosDTO>()
		temporal.add( new DocumentoCargaArchivosDTO(new ComparendoDTO(COMNUMERO: comparendos.get(0),COMFECHA: comparendos.get(1),COMTIPODOC: comparendos.get(2), COMIDENTIFICACION: comparendos.get(3),COMNOMBRE: comparendos.get(4), COMAPELLIDO: comparendos.get(5), COMVALOR: comparendos.get(6))))
		listaComparendos = temporal

		// STMNOVEDADES
		conexion.connectDBProfileUser("SMTNOVEDADES")
		objectoConsulta = [Dates.getTimeStamp('yyyymmdd')]
		ArrayList<String> ultimaConsecutivoResolucion = conexion.executeQuery(PropertiesManager.getStringSql( modulos.MODULO_SMTNOVEDADES, 'cargaArchivos.resoluciones.ultimoConsecutivoResolucion', objectoConsulta))
		conexion.closeDatabaseConnection()

		if(!ultimaConsecutivoResolucion.isEmpty() || ultimaConsecutivoResolucion.size() != 0) {
			this.NumeroResolucion = Long.parseLong(ultimaConsecutivoResolucion.get(0).toString().replace(Dates.getTimeStamp('yyyymmdd').toString(), ''))
		}else {
			this.NumeroResolucion = 1
		}

	}
}
