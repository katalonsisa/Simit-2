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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import groovy.xml.MarkupBuilder
import internal.GlobalVariable

public class BuildWSAVVILLAS {

	/**
	 * Genera el XLM para el servicio Pago Factura de AVVILLAS
	 * @param numeroLiquidacion
	 * @param Valor
	 * @return
	 */
	@Keyword
	static def buildXMLPagoFacturaEstandar(String numeroLiquidacion, String Valor) {
		def fecha = new Date().format('YYYYMMdd')
		def xmlWriter = new StringWriter()
		def builder = new MarkupBuilder(xmlWriter)
		builder.setDoubleQuotes(true)
		builder.'soapenv:Envelope'('xmlns:soapenv':"http://schemas.xmlsoap.org/soap/envelope/",'xmlns:wses':"http://organizacion.com/wsEstandar/"){
			'soapenv:Header'()
			'soapenv:Body'{
				'wses:oe_pagarFacturaEstandar'{
					'codBancoOrigen'('052')
					'codCanal'('0')
					'nroProducto'('086044328')
					'codOficinaOrigen'('1314')
					'codCiudad'('11001000')
					'fechaTransaccion'(fecha)
					'horaTransaccion'(new Date().format('HHmm'))
					'fechaCompensacion'(fecha)
					'referencia1'(numeroLiquidacion)
					'referencia2'()
					'referencia3'()
					'referencia4'()
					'valorEfectivo'('24984000')
					'valorCheque'()
					'valorTotal'("${Valor}00")
					'nroAutorizacion'('1')
				}
			}
		}





		return xmlWriter.toString()
	}


	@Keyword
	static def buildXMLConsultaFactura(String numeroLiqudacion) {
		def fecha = new Date().format('YYYYMMdd')
		def xmlWriter = new StringWriter()
		def builder = new MarkupBuilder(xmlWriter)
		builder.setDoubleQuotes(true)
		builder.'soapenv:Envelope'('xmlns:soapenv':"http://schemas.xmlsoap.org/soap/envelope/",'xmlns:wses':"http://organizacion.com/wsEstandar/"){
			'soapenv:Header'()
			'soapenv:Body'{
				'wses:oe_consultarFacturaEstandar'{
					'codBancoOrigen'('052')
					'codCanal'('0')
					'nroProducto'('086044328')
					'codOficinaOrigen'('1314')
					'codCiudad'('05001000')
					'fechaTransaccion'(fecha)
					'horaTransaccion'(new Date().format('HHmm'))
					'fechaCompensacion'(fecha)
					'referencia1'(numeroLiqudacion)
					'referencia2'()
					'referencia3'()
					'referencia4'()
				}
			}
		}





		return xmlWriter.toString()
	}
}
