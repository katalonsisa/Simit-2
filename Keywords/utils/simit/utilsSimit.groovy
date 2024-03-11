package utils.simit

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.org.apache.bcel.internal.generic.RETURN

import internal.GlobalVariable
import model.cargaArchivos.evidencias.DocumentoCargaArchivosDTO
import util.ConnectionDataBase
import util.Dates
import util.Placas
import util.PropertiesManager
import util.TemporaryVariables
import utils.simit.constantesSimit.modulos
import utils.simit.constantesSimit.DocumentoCargaArchivo

public class utilsSimit {


	//------------------------ conexiones
	private static ConnectionDataBase conexion = new ConnectionDataBase();



	//private static WebDriver driver = DriverFactory.getWebDriver();

	public static String obtenerCodigo(){
		String codigo

		String currentPage = WebUI.getUrl()

		int currentTab = WebUI.getWindowIndex()

		if(!TemporaryVariables.gmailSeccionOpen) {

			WebDriver driver = DriverFactory.getWebDriver()

			JavascriptExecutor js = ((driver) as JavascriptExecutor)

			js.executeScript('window.open();')
		}
		WebUI.switchToWindowIndex(currentTab + 1)

		WebUI.navigateToUrl(currentPage)

		WebUI.navigateToUrl("https://mail.google.com/mail/u/0/?tab=wm#inbox")



		WebUI.maximizeWindow()

		if(!TemporaryVariables.gmailSeccionOpen) {
			WebUI.delay(GlobalVariable.delay)
			WebUI.setText(findTestObject('Object Repository/GeneralObjects/Gmail/loginGmail/input_Correo'), GlobalVariable.emailUser)
			WebUI.click(findTestObject('Object Repository/GeneralObjects/Gmail/loginGmail/button_SiguienteCorreo'))
			WebUI.delay(GlobalVariable.delay)
			WebUI.setEncryptedText(findTestObject('Object Repository/GeneralObjects/Gmail/loginGmail/input_Password'), GlobalVariable.emailPassword)
			WebUI.click(findTestObject('Object Repository/GeneralObjects/Gmail/loginGmail/button_Siguiente'))
			TemporaryVariables.gmailSeccionOpen = true
		}


		WebUI.delay(GlobalVariable.delay)
		//		Calendar cal = Calendar.getInstance()
		//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		//		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)- 1)
		//		String strDate = formatter.format(cal.getTime())
		WebUI.click(findTestObject("Object Repository/GeneralObjects/Gmail/span_Correo", [("hora"): Dates.getTimeStamp("HH"), ("correo"): "se ha generado un código de verificación para tu"]), FailureHandling.OPTIONAL)
		//WebUI.click(findTestObject("GeneralObjects/Gmail/span_correo", [("hora"): strDate, ("correo"): "se ha generado un código de verificación para tu"]), FailureHandling.OPTIONAL)


		WebUI.scrollToElement(findTestObject('Object Repository/GeneralObjects/Gmail/p_CodigoPleaceToPay'), GlobalVariable.shortDelay)
		codigo = WebUI.getText(findTestObject('Object Repository/GeneralObjects/Gmail/p_CodigoPleaceToPay'))

		WebUI.switchToWindowIndex(currentTab)

		//		println codigo

		return codigo
	}


	/**
	 * Metodo que realiza un delay mientras que el proceso de las cargas aun no este terminado 
	 * @param radicado
	 */

	public static void TiempoRadicado(String radicado,int tipoArchivo) {

		Object[] objectoConsulta = [radicado]
		int estadoRadicado = 1
		int contadorConexiones = 0
		//MODULO_SMTCARGUES

		conexion.connectDBProfileUser("SMTCARGUES")
		LocalDateTime tiempoInicio = LocalDateTime.now();
		println 'La toma de tiempo del prosesado del archivo empezo el ' + tiempoInicio
		ArrayList<String> estado = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.estadoCarga", objectoConsulta ))
		estadoRadicado = Integer.parseInt(estado.get(1).toString())

		while(estadoRadicado <= 4) {
			if(conexion.connection == null || conexion.connection.isClosed()) {
				conexion.connectDBProfileUser("SMTCARGUES")
			}
			estado = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.estadoCarga", objectoConsulta ))
			WebUI.delay(GlobalVariable.delay)
			estadoRadicado = Integer.parseInt(estado.get(1).toString())

			if(contadorConexiones == 10 ) {
				conexion.closeDatabaseConnection()
				conexion.connectDBProfileUser("SMTCARGUES")
				estado = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.estadoCarga", objectoConsulta ))
				contadorConexiones = 0
				println 'se realiza una nueva conexion por tiempo de espera demaciado largo a las ' + new  Date()

				if(consultaErrorProsesoAtomatico(radicado,tipoArchivo)) {
					conexion.closeDatabaseConnection()
					estadoRadicado = 8
					WebUI.comment('Error en el proseso automatico mas detalles consultar el documento en ' + DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS + DocumentoCargaArchivo.NOMBRE_DOCUMENTO )
					DocumentoCargaArchivosEvidencias.guardarEvidencia()
					KeywordUtil.markErrorAndStop('Error en el proseso automatico mas detalles consultar el documento en ' + DocumentoCargaArchivo.PATH_CARGA_ARCHIVOS_EVIDENCIAS + DocumentoCargaArchivo.NOMBRE_DOCUMENTO )
				}
			}

			contadorConexiones ++
		}
		conexion.closeDatabaseConnection()

		LocalDateTime TiempoFin = LocalDateTime.now()
		println 'El proceso del archivo termino a las ' + TiempoFin
		long diff = tiempoInicio.until(TiempoFin, ChronoUnit.MINUTES);
		println 'En total tomo un tiempo de ' + diff + ' Minutos'


		switch(tipoArchivo){
			case 1:
				DocumentoCargaArchivosDTO comparendoEstado = new DocumentoCargaArchivosDTO()
				comparendoEstado.setCOMRadicado(radicado+'')
				comparendoEstado.setCOMEstadoRadicado(estadoRadicado+'')
				comparendoEstado.setCOMIDCargaInformacion(estado.get(0).toString())
				DocumentoCargaArchivosEvidencias.setComEstadoRadicado(comparendoEstado)
				break

			case 2:
				DocumentoCargaArchivosDTO resolucionEstado = new DocumentoCargaArchivosDTO()
				resolucionEstado.setRESRadicadoResolucion(radicado+'')
				resolucionEstado.setRESEstadoResolucion(estadoRadicado+'')
				resolucionEstado.setRESIDCargaInformacionResolucion(estado.get(0).toString())
				DocumentoCargaArchivosEvidencias.setRESEstadoRadicado(resolucionEstado)
				break
			case 3:
				DocumentoCargaArchivosDTO RecaudoEstado = new DocumentoCargaArchivosDTO()
				RecaudoEstado.setRECRadicadoRecaudoLocal(radicado+'')
				RecaudoEstado.setRECEstadoRecaudoLocal(estadoRadicado+'')
				RecaudoEstado.setRECIDCargaInformacionRacudoLocal(estado.get(0).toString())
				DocumentoCargaArchivosEvidencias.setRECEstadoRadicado(RecaudoEstado)
				break
			default:
				println 'tipo archivo no valido'

				break
		}



	}


	/**
	 * Metodo para consultar si el radicado del archivo registrado presenta algun error 
	 * 
	 * @param radicado 
	 * @return si no hay error retorna false 
	 * de en contrar algun error retorna true 
	 */
	public static boolean consultaErrorRadicado(String radicado, int tipoArchivo) {

		ArrayList<DocumentoCargaArchivosDTO> ListaErrores = new ArrayList<DocumentoCargaArchivosDTO>()

		Object[] objectoConsulta
		ArrayList<DocumentoCargaArchivosDTO> listComparendos
		ArrayList<DocumentoCargaArchivosDTO> listResoluciones
		ArrayList<DocumentoCargaArchivosDTO> listRecaudo

		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCARGUES")
		switch(tipoArchivo){
			case 1:
				listComparendos = DocumentoCargaArchivosEvidencias.getListComparendos(radicado)
				objectoConsulta =  [listComparendos.get(0).COMIDCargaInformacion]
				break

			case 2:
				listResoluciones = DocumentoCargaArchivosEvidencias.getListResolucion(radicado)
				objectoConsulta =  [listResoluciones.get(0).RESIDCargaInformacionResolucion]
				break
			case 3:
				listRecaudo = DocumentoCargaArchivosEvidencias.getListRecaudoLocal(radicado)
				objectoConsulta =  [listRecaudo.get(0).RECIDCargaInformacionRacudoLocal]
				break
			default:
				println 'tipo archivo no valido'
				return true
				break
		}

		ArrayList<String> ConsultaError = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.consultaErrorCarga", objectoConsulta ))
		conexion.closeDatabaseConnection()





		if(consultaErrorProsesoAtomatico(radicado, tipoArchivo)) {
			return true
		}

		if(ConsultaError.empty) {
			println 'El radicado no presento ningun error'
			return false
		}else {
			if(!ConsultaError.get(0).getClass().getName().equals(LinkedList.class.getName())) {
				DocumentoCargaArchivosDTO EvidenciaError = new DocumentoCargaArchivosDTO()

				switch(tipoArchivo){
					case 1:
						def consecutivo = Integer.parseInt(ConsultaError.get(4).toString())
						EvidenciaError.setCOMConsecutivo(consecutivo.toString())
						EvidenciaError.setCOMRadicado(radicado)
						EvidenciaError.setCOMDetalleError('NOMBRE_CAMPO_ERROR: '+ ConsultaError.get(1) + ' , DESCRIPCION:  ' + ConsultaError.get(2) + ' , POSICION_CAMPO_ERROR: ' + ConsultaError.get(3))
						ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						DocumentoCargaArchivosEvidencias.setComErrorRadicado(ListaErrores)

						break

					case 2:
						def consecutivo = Integer.parseInt(ConsultaError.get(4).toString())
						EvidenciaError.setCOMConsecutivo(consecutivo.toString())
						EvidenciaError.setRESRadicadoResolucion(radicado)
						EvidenciaError.setRESDetalleErrorResolucion('NOMBRE_CAMPO_ERROR: '+ ConsultaError.get(1) + ' , DESCRIPCION:  ' + ConsultaError.get(2) + ' , POSICION_CAMPO_ERROR: ' + ConsultaError.get(3))
						ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						DocumentoCargaArchivosEvidencias.setRESErrorRadicado(ListaErrores)
						break
					case 3:
						def consecutivo = Integer.parseInt(ConsultaError.get(4).toString())
						EvidenciaError.setCOMConsecutivo(consecutivo.toString())
						EvidenciaError.setRECRadicadoRecaudoLocal(radicado)
						EvidenciaError.setRECDetalleErroRecaudoLocal('NOMBRE_CAMPO_ERROR: '+ ConsultaError.get(1) + ' , DESCRIPCION:  ' + ConsultaError.get(2) + ' , POSICION_CAMPO_ERROR: ' + ConsultaError.get(3))
						ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						DocumentoCargaArchivosEvidencias.setRECErrorRadicadoLocal(ListaErrores)
						break
					default:
						println 'tipo archivo no valido'
						return true
						break
				}



			}else {
				switch(tipoArchivo){
					case 1:
						for (var in ConsultaError) {
							DocumentoCargaArchivosDTO EvidenciaError = new DocumentoCargaArchivosDTO()
							EvidenciaError.setCOMConsecutivo(var.get(4).toString())
							EvidenciaError.setCOMRadicado(radicado)
							EvidenciaError.setCOMDetalleError('NOMBRE_CAMPO_ERROR: '+ var.get(1) + ' , DESCRIPCION:  ' + var.get(2) + ' , POSICION_CAMPO_ERROR: ' + var.get(3))
							ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						}
						DocumentoCargaArchivosEvidencias.setComErrorRadicado(ListaErrores)

						break

					case 2:
						for (var in ConsultaError) {
							DocumentoCargaArchivosDTO EvidenciaError = new DocumentoCargaArchivosDTO()
							EvidenciaError.setCOMConsecutivo(var.get(4).toString())
							EvidenciaError.setRESRadicadoResolucion(radicado)
							EvidenciaError.setRESDetalleErrorResolucion('NOMBRE_CAMPO_ERROR: '+ var.get(1) + ' , DESCRIPCION:  ' + var.get(2) + ' , POSICION_CAMPO_ERROR: ' + var.get(3))
							ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						}
						DocumentoCargaArchivosEvidencias.setRESErrorRadicado(ListaErrores)
						break
					case 3:
						for (var in ConsultaError) {
							DocumentoCargaArchivosDTO EvidenciaError = new DocumentoCargaArchivosDTO()
							EvidenciaError.setCOMConsecutivo(var.get(4).toString())
							EvidenciaError.setRECRadicadoRecaudoLocal(radicado)
							EvidenciaError.setRECDetalleErroRecaudoLocal('NOMBRE_CAMPO_ERROR: '+ var.get(1) + ' , DESCRIPCION:  ' + var.get(2) + ' , POSICION_CAMPO_ERROR: ' + var.get(3))
							ListaErrores.add(new DocumentoCargaArchivosDTO(EvidenciaError))
						}
						DocumentoCargaArchivosEvidencias.setRECErrorRadicadoLocal(ListaErrores)
						break
					default:
						println 'tipo archivo no valido'
						return true
						break
				}

			}
			println 'El radicado presento un error porfavor validar con el documento de evidencias'
			return true
		}
	}


	/** Consulta sin tubo algun problema en el pl 
	 *   
	 * @param radicado
	 * @param tipoArchivo
	 * @return si no hay error retorna false 
	 * de en contrar algun error retorna true 
	 */

	public static boolean consultaErrorProsesoAtomatico(String radicado,int tipoArchivo) {
		Object[] objectoConsulta
		ArrayList<DocumentoCargaArchivosDTO> listComparendos
		ArrayList<DocumentoCargaArchivosDTO> listResoluciones
		ArrayList<DocumentoCargaArchivosDTO> listRecaudo

		switch(tipoArchivo){
			case 1:
				listComparendos = DocumentoCargaArchivosEvidencias.getListComparendos(radicado)
				objectoConsulta =  [listComparendos.get(0).COMRadicado]
				break

			case 2:
				listResoluciones = DocumentoCargaArchivosEvidencias.getListResolucion(radicado)
				objectoConsulta =  [listResoluciones.get(0).RESRadicadoResolucion]

				break

			case 3:
				listRecaudo = DocumentoCargaArchivosEvidencias.getListRecaudoLocal(radicado)
				objectoConsulta =  [listRecaudo.get(0).RECRadicadoRecaudoLocal]
				break
			default:
				println 'tipo archivo no valido'
				return true
				break
		}
		ArrayList<DocumentoCargaArchivosDTO> ListaErrores = new ArrayList<DocumentoCargaArchivosDTO>()


		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCARGUES")
		ArrayList<String> ConsultaErrorPL = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.consultaErrorProcesoAutomatico", objectoConsulta ))
		conexion.closeDatabaseConnection()

		if(!ConsultaErrorPL.empty) {


			switch(tipoArchivo){
				case 1:
					for (var in listComparendos) {
						DocumentoCargaArchivosDTO comparendoError = new DocumentoCargaArchivosDTO()
						comparendoError.setCOMConsecutivo(var.COMConsecutivo.toString())
						comparendoError.setCOMRadicado(var.COMRadicado.toString())
						comparendoError.setCOMDetalleError('Error en la tarea automatica: ' + ConsultaErrorPL.get(0))
						ListaErrores.add(new DocumentoCargaArchivosDTO(comparendoError))
					}
					DocumentoCargaArchivosEvidencias.setComErrorRadicado(ListaErrores)
					break

				case 2:
					for (var in listResoluciones) {
						DocumentoCargaArchivosDTO comparendoError = new DocumentoCargaArchivosDTO()
						comparendoError.setCOMConsecutivo(var.COMConsecutivo.toString())
						comparendoError.setRESRadicadoResolucion(var.RESRadicadoResolucion.toString())
						comparendoError.setRESDetalleErrorResolucion('Error en la tarea automatica: ' + ConsultaErrorPL.get(0))
						ListaErrores.add(new DocumentoCargaArchivosDTO(comparendoError))
					}
					DocumentoCargaArchivosEvidencias.setRESErrorRadicado(ListaErrores)
					break

				case 3:
					for (var in listResoluciones) {
						DocumentoCargaArchivosDTO comparendoError = new DocumentoCargaArchivosDTO()
						comparendoError.setCOMConsecutivo(var.COMConsecutivo.toString())
						comparendoError.setRECRadicadoRecaudoLocal(var.RECRadicadoRecaudoLocal.toString())
						comparendoError.setRECDetalleErroRecaudoLocal('Error en la tarea automatica: ' + ConsultaErrorPL.get(0))
						ListaErrores.add(new DocumentoCargaArchivosDTO(comparendoError))
					}
					DocumentoCargaArchivosEvidencias.setRESErrorRadicado(ListaErrores)
					break
				default:
					println 'tipo archivo no valido'
					return true
					break
			}


			println 'El proceso automatico presento un error porfavor validar con el documento de evidencias'
			return true
		}
		println 'El proceso automatico no presento ningun error'
		return false
	}



	/**
	 * Click Web element
	 */
	@Keyword
	public static clickUsingJS(TestObject to) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement element = WebUiCommonHelper.findWebElement(to,30)
		JavascriptExecutor executor = ((driver) as JavascriptExecutor)
		executor.executeScript('arguments[0].click()',element)
	}

	/**
	 * 
	 * @return documento de una persona con obligaciones pendientes
	 */
	public static String documentoPersonaObligacion() {
		Object[] objectoConsulta

		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCOMPARENDOS")
		ArrayList<String> consultaDocumento = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS,"estadoCuenta.documentoPendienteObligacion", objectoConsulta ))
		conexion.closeDatabaseConnection()

		if(consultaDocumento.isEmpty()) {
			KeywordUtil.markErrorAndStop('No hay documentos con obligaciones pendientes')
		}


		return consultaDocumento.get(0)
	}

	/**
	 * 
	 * @return documento de una persona con obligaciones pendientes
	 */
	public static String documentoPersonaCursoVial() {
		Object[] objectoConsulta

		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCOMPARENDOS")
		ArrayList<String> consultaDocumento = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS,"estadoCuenta.documentoCursoVial", objectoConsulta ))
		conexion.closeDatabaseConnection()


		if(consultaDocumento.isEmpty()) {
			KeywordUtil.markErrorAndStop('No hay documentos con cursosViales')
		}

		return consultaDocumento.get(0)
	}


	public static String getEstandarTransferencia(int tipoArchivo, int clasificacion,  String subSede){
		Object[] objectoConsulta = [clasificacion, tipoArchivo, subSede]

		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCARGUES")
		ArrayList<String> estandarTransferencia = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES,"cargaArchivos.estandarTransferencia", objectoConsulta ))
		conexion.closeDatabaseConnection()


		if(estandarTransferencia.isEmpty() || estandarTransferencia == null) {
			KeywordUtil.markErrorAndStop("No hay estandar de transferencia del tipo archivo ${tipoArchivo} con clasificacion ${clasificacion} configurado para la secretaria selecionada ${subSede} ")
		}

		return estandarTransferencia.get(0)
	}



	public static String aplicarEstandarTranferencia(String estandarTransferencia, Object modelo) {
		String objectoEstandarAplicado = ""
		if(estandarTransferencia.equals(null) || estandarTransferencia.equals("")) {
			KeywordUtil.markError("No se recivido ningun estandar de trasferecia")
		}
		if(modelo.equals(null) || modelo == null) {
			KeywordUtil.markError("El objecto a aplicar trasferencia esta vacio")
		}
		List<String> listaCampoEstandar = estandarTransferencia.split(', ')

		for(campo in listaCampoEstandar) {
			objectoEstandarAplicado += modelo.getAt(campo)
			if(!campo.equals(listaCampoEstandar.get(listaCampoEstandar.size()-1))){
				objectoEstandarAplicado +=  ","
			}
		}
		return  objectoEstandarAplicado
	}


	private static String consultaValorAdicional(int conceptoArchivo,String idSubSede) {
		Object[] objectoConsulta = [(Dates.getTimeStamp('YYYY/MM/dd') +""), conceptoArchivo, idSubSede]
		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTCARGUES")
		ArrayList<String> valorAdicional = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCARGUES, 'cargaArchivos.consultaValorAdicional', objectoConsulta ))
		conexion.closeDatabaseConnection()
		if(valorAdicional == null || valorAdicional.equals(null) || valorAdicional.isEmpty()) {
			return "0"
		}
		return valorAdicional.get(0)
	}
	private static String consultaDescuentoLey (int conceptoArchivo,String idSubSede) {
		Object[] objectoConsulta = [(Dates.getTimeStamp('dd/MM/YYYY') ), conceptoArchivo, idSubSede]
		//MODULO_SMTCARGUES
		conexion.connectDBProfileUser("SMTRECAUDOS")
		ArrayList<String> descuentoLey = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTRECAUDOS, 'cargaArchivos.consultaDescuento', objectoConsulta ))
		conexion.closeDatabaseConnection()
		if(descuentoLey == null || descuentoLey.equals(null) || descuentoLey.isEmpty()) {
			return "0"
		}

		return descuentoLey.get(0)
	}


	public static String ConsultaReferenciaLiquidacion (DocumentoCargaArchivosDTO documentoEvidencia, int tipoArchivo = 1) {
		ArrayList<String> id_referencia
		Object[] objectoConsulta = [[
				1:documentoEvidencia.COMNumeroComparendo, 2:documentoEvidencia.RESNumeroResolucion, 3:documentoEvidencia.RESNumeroResolucion]]
		if(tipoArchivo ==1) {
			//MODULO_SMTCOMPARENDOS
			conexion.connectDBProfileUser("SMTCOMPARENDOS")
			println PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS, 'consultaComparendo.idComparendo', objectoConsulta[0][tipoArchivo])
			id_referencia= conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS, 'consultaComparendo.idComparendo', objectoConsulta[0][tipoArchivo] ))
			conexion.closeDatabaseConnection()
		}else {
			//MODULO_SMTCOMPARENDOS
			conexion.connectDBProfileUser("SMTNOVEDADES")
			id_referencia= conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTNOVEDADES, 'consultaRecaudo.idRecaudo', objectoConsulta[0][tipoArchivo] ))
			conexion.closeDatabaseConnection()
		}

		assert !id_referencia.toString().isAllWhitespace()

		objectoConsulta = [id_referencia.get(0).toString(), tipoArchivo]

		conexion.connectDBProfileUser("SMTRECAUDOS")
		println(PropertiesManager.getStringSql(modulos.MODULO_SMTRECAUDOS, 'liquidacion.numeroLiquidacion', objectoConsulta))
		ArrayList<String> NumeroLiquidacion = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTRECAUDOS, 'liquidacion.numeroLiquidacion', objectoConsulta))
		conexion.closeDatabaseConnection()

		documentoEvidencia.setCOMReferenciaPago(NumeroLiquidacion.get(0).toString())
		DocumentoCargaArchivosEvidencias.setCOMReferenciaPago(documentoEvidencia)
		DocumentoCargaArchivosEvidencias.guardarEvidencia()

		return NumeroLiquidacion.get(0).toString()
	}

	public static ArrayList<String> ConsultaReferenciaLiquidacion (ArrayList<DocumentoCargaArchivosDTO> documentoEvidencia, int tipoArchivo = 1) {
		ArrayList<String> id_referencia
		def campoconsuta = [1:"COMNumeroComparendo", 2:"RESNumeroResolucion", 3:"RESNumeroResolucion"]

		Object[] objectoConsulta = [("'" +documentoEvidencia.each {  it.getAt(campoconsuta[tipoArchivo].toString()) }.getAt(campoconsuta[tipoArchivo]).join("','") + "'")]
		if(tipoArchivo ==1) {
			//MODULO_SMTCOMPARENDOS
			conexion.connectDBProfileUser("SMTCOMPARENDOS")
			println PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS, 'consultaComparendo.idComparendoLote', objectoConsulta)
			id_referencia= conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTCOMPARENDOS, 'consultaComparendo.idComparendoLote', objectoConsulta ))
			conexion.closeDatabaseConnection()
		}else {
			//MODULO_SMTNOVEDADES
			conexion.connectDBProfileUser("SMTNOVEDADES")
			id_referencia= conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTNOVEDADES, 'consultaRecaudo.idRecaudoLote', objectoConsulta))
			conexion.closeDatabaseConnection()
		}

		assert !id_referencia.toString().isAllWhitespace()

		objectoConsulta = ["'"+id_referencia.join("','").toString().replace("[", "").replace("]", "")+"'", tipoArchivo]

		conexion.connectDBProfileUser("SMTRECAUDOS")
		println(PropertiesManager.getStringSql(modulos.MODULO_SMTRECAUDOS, 'liquidacion.numeroLiquidacionLote', objectoConsulta))
		ArrayList<String> NumeroLiquidacion = conexion.executeQuery(PropertiesManager.getStringSql(modulos.MODULO_SMTRECAUDOS, 'liquidacion.numeroLiquidacionLote', objectoConsulta))
		conexion.closeDatabaseConnection()

		for (int i =0 ; i < documentoEvidencia.size(); i ++) {
			documentoEvidencia.get(i).setCOMReferenciaPago(NumeroLiquidacion.get(i).toString().replace("[", "").replace("]", ""))
			DocumentoCargaArchivosEvidencias.setCOMReferenciaPago(documentoEvidencia.get(i))
		}
		DocumentoCargaArchivosEvidencias.guardarEvidencia()

		return NumeroLiquidacion
	}



}
