package utils.simit

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import model.cargaArchivos.csv.RecaudoLocalDTO
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.ConnectionDataBase
import util.Dates
import util.PropertiesManager
import utils.simit.constantesSimit.modulos
import util.utilsQX
import utils.simit.utilsSimit

public class BuildRecaudoLocal {

	//------------------------ conexiones
	public static ConnectionDataBase conexion = new ConnectionDataBase();

	//------------------------ Variables
	private static int consecutivo = 0
	private static boolean parametroValoresAdicionales = false
	private static boolean parametroDescuentoPorley = false
	private static long valorAdicional = 0
	private static int porsentajeDescuento = 0
	private static String idSubSede = ""




	//------------------------ metodos
	public static RecaudoLocalDTO buildRecaudoLocalSimple(String usuario,boolean isComparendo,String radicadoReferencia) {
		List lista
		if(!radicadoReferencia.empty || !radicadoReferencia.equals("") ) {
			if(isComparendo) {
				lista = DocumentoCargaArchivosEvidencias.getListComparendos(radicadoReferencia)
			}else {
				lista = DocumentoCargaArchivosEvidencias.getListResolucion(radicadoReferencia)
			}
		}else {
			if(isComparendo) {
				lista = DocumentoCargaArchivosEvidencias.getListComparendosSinLiquidar()
			}else {
				lista = DocumentoCargaArchivosEvidencias.getListResolucionesSinLiquidar()
			}
		}
		consultaInicial(usuario)

		DocumentoCargaArchivosDTO datoReferencia = lista.get(consecutivo)
		RecaudoLocalDTO recaudoLocal = new RecaudoLocalDTO()
		recaudoLocal.setRECCONSECUTIVO(""+ ++consecutivo)
		recaudoLocal.setRECFAPL(Dates.getTimeStamp("dd/MM/YYYY"))
		recaudoLocal.setRECHORA(Dates.getTimeStamp("hhmm"))
		recaudoLocal.setRECFTRAN(Dates.getTimeStamp("dd/MM/YYYY"))
		recaudoLocal.setRECCANAL("0")
		recaudoLocal.setRECORIGEN("TAQUILLA TRANSITO")
		recaudoLocal.setRECPOLCA("N")
		recaudoLocal.setRECCHEQUE("0")
		recaudoLocal.setRECNROCUOTA("1")
		recaudoLocal.setRECNUM(utilsQX.getNumero(15))
		recaudoLocal.setRECVINTERES("0")
		recaudoLocal.setRECSUBSEDESECRET(datoReferencia.COMDivipo)
		recaudoLocal.setRECVADICIONAL("" + 0)
		recaudoLocal.setRECVDESCUENTO("" +( Long.parseLong(datoReferencia.COMValor) * (porsentajeDescuento/100)) )
		recaudoLocal.setRECVDESCINTERES('0')
		if(isComparendo) {
			recaudoLocal.setRECEFECTIVO(datoReferencia.COMValor)
			recaudoLocal.setRECTOTAL("" + ( Long.parseLong(datoReferencia.COMValor)) )
			recaudoLocal.setRECNUMEROREFERENCIA(datoReferencia.COMNumeroComparendo)
			recaudoLocal.setRECNIP(datoReferencia.COMDocumentoInfractor)
			recaudoLocal.setRECTIPOREC("1")

			recaudoLocal.setRECSUBSEDESECRET(datoReferencia.COMDivipo)
			recaudoLocal.setRECTIPODOC(datoReferencia.COMTipoDocumento)
		}else {
			recaudoLocal.setRECEFECTIVO(datoReferencia.COMValor)
			recaudoLocal.setRECTOTAL("" + ( Long.parseLong(datoReferencia.COMValor)))
			recaudoLocal.setRECNUMEROREFERENCIA(datoReferencia.RESNumeroResolucion)
			recaudoLocal.setRECNIP(datoReferencia.COMDocumentoInfractor)
			recaudoLocal.setRECTIPOREC("2")
			recaudoLocal.setRECSUBSEDESECRET(datoReferencia.COMDivipo)
			recaudoLocal.setRECTIPODOC(datoReferencia.COMTipoDocumento)
		}



		return recaudoLocal
	}


	public static List<RecaudoLocalDTO> buildRecaudoLocaldatoReferencia(int cantidad,String usuario,boolean isComparendo,String radicadoReferencia){
		ArrayList<RecaudoLocalDTO> recauolista = new ArrayList<RecaudoLocalDTO>()
		for (int i = 0; i < cantidad; i++) {

			recauolista.add(buildRecaudoLocalSimple(usuario, isComparendo, radicadoReferencia))

		}


		return recauolista
	}



	//------------------------ consultas
	public static consultaInicial(String usuario) {
		if(!idSubSede.equals("") ) {
			return
		}
		Object[] objectoConsulta = [usuario]

		//SMTADMINISTRACION
		conexion.connectDBProfileUser("SMTADMINISTRACION")
		ArrayList<String> secretaria = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTADMINISTRACION, 'comparendos.sudsede', objectoConsulta ))

		if(secretaria == null || secretaria.equals(null)) {
			KeywordUtil.markError("El usuario no existe en usuario_sistema")
		}

		def subSede = secretaria.get(0)
		def localidad = secretaria.get(1)
		this.idSubSede = secretaria.get(2)
		objectoConsulta = [idSubSede, "1662,1760"]
		ArrayList<Object> configuracionesAdicionales = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTADMINISTRACION, 'comparendos.configuracionSubSede', objectoConsulta ))
		conexion.closeDatabaseConnection()

		//println configuracionesAdicionales.size()

		if (configuracionesAdicionales.size() == 2) {
			switch (configuracionesAdicionales.get(0)) {
				case 1662:
					this.parametroValoresAdicionales = configuracionesAdicionales.get(1)
					break
				case 1760:
					this.parametroDescuentoPorley = configuracionesAdicionales.get(1)
					break
				default :
					break
			}
		}else {
			for(configuracion in configuracionesAdicionales) {
				switch (configuracion) {
					case 1662:
						this.parametroValoresAdicionales = configuracion.get(2)
						break
					case 1760:
						this.parametroDescuentoPorley = configuracion.get(2)
						break
					default :
						break
				}
			}
		}
		if(this.parametroValoresAdicionales == true) {
			this.valorAdicional = Long.parseLong(utilsSimit.consultaValorAdicional(1,idSubSede))
			this.porsentajeDescuento = Integer.parseInt(utilsSimit.consultaDescuentoLey(1, idSubSede))

		}

		if(this.parametroDescuentoPorley == true) {
			this.porsentajeDescuento = Integer.parseInt(utilsSimit.consultaDescuentoLey(1, idSubSede))
		}
	}


}
