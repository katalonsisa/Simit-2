package utils.simit

import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonSlurper
import model.cursoVial.json.recibirCursoDTO
import util.ConnectionDataBase
import util.Dates
import util.PropertiesManager
import util.utilsQX
import utils.simit.constantesSimit.modulos

public class BuildJsonRecibirCurso {

	private static ConnectionDataBase conexion = new ConnectionDataBase();


	public static String idCia
	public static String Ciudad
	public static String runtCia
	public static String funcionario
	public static String infractor
	public static String documentoInfractor
	public static String Comparendo
	public static int tipoDocumentoInfractor


	public static buildJsonCompleto(){
		getData()

		def curso = new recibirCursoDTO()

		curso.setIdCia(runtCia)
		curso.setIdCiudadCia(Ciudad)
		curso.setCodigoSedeCia(runtCia)
		curso.setIdFuncionarioPago(funcionario)
		curso.setIdFuncionarioRegistra(funcionario)
		curso.setIdTipoDocumentoInfractor(tipoDocumentoInfractor)
		curso.setIdentificacionInfractor(documentoInfractor)
		curso.setNumeroComparendo(Comparendo)
		curso.setFechaComparendo(Dates.getTimeStamp("dd/MM/YYYY"))
		curso.setIdSecretaria(Ciudad)
		curso.setReferenciaDescuento(1)
		curso.setValorCia(400000)
		curso.setValorFCM(0)
		curso.setCodigoCurso(utilsQX.getNumero(5))
		curso.setFechaPago(Dates.getTimeStamp("dd/MM/YYYY"))
		curso.setFechaCurso(Dates.getTimeStamp("dd/MM/YYYY"))
		curso.setNumeroCertificado(utilsQX.getNumero(5))

		return curso.toString()
	}


	public static buildSinCampos(String campo){
		def curso = buildJsonCompleto()
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(curso)
		result instanceof Map

		result."${campo}" = ''

		return utilsQX.JsonNoNull(result.toString())
	}

	public static buildCamposMaximos(String campo){

		def curso = buildJsonCompleto()
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(curso)
		result instanceof Map

		result."${campo}" = utilsQX.getNumero(100)

		return utilsQX.JsonNoNull(result.toString())
	}


	public static buildCamposMinimos(String campo){
		def curso = buildJsonCompleto()
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(curso)
		result instanceof Map

		result."${campo}" = utilsQX.getNumero(1)

		return utilsQX.JsonNoNull(result.toString())
	}



	public static getData(){
		def objectoConsulta = []
		//----------------SMTADMINISTRACION
		conexion.connectDBProfileUser("SMTADMINISTRACION")
		ArrayList<String> funcionario = conexion.executeQuery(PropertiesManager.getStringSql( modulos.MODULO_SMTADMINISTRACION, 'cursosViales.funcionario', objectoConsulta))
		ArrayList<String> cia = conexion.executeQuery(PropertiesManager.getStringSql( modulos.MODULO_SMTADMINISTRACION, 'cursosViales.CIA', objectoConsulta))
		conexion.closeDatabaseConnection()
		//-----------------SMTCOMPARENDOS
		conexion.connectDBProfileUser("SMTCOMPARENDOS")
		objectoConsulta = new Date(new Date().getTime() - 86400000 * 15 ).format('YYYY-MM-dd').toString()
		println PropertiesManager.getStringSql( modulos.MODULO_SMTCOMPARENDOS, 'cursoVial.comparendo', objectoConsulta)
		ArrayList<String> infractor = conexion.executeQuery(PropertiesManager.getStringSql( modulos.MODULO_SMTCOMPARENDOS, 'cursoVial.comparendo', objectoConsulta))
		conexion.closeDatabaseConnection()

		Comparendo = infractor.get(0)
		tipoDocumentoInfractor = Integer.parseInt(infractor.get(1).toString())
		documentoInfractor = infractor.get(2)
		Ciudad = infractor.get(3)

		idCia = cia.get(0)
		runtCia = cia.get(1)

		this.funcionario = funcionario.get(0)

	}
}
