package utils.simit

import com.kms.katalon.core.annotation.Keyword

import groovy.xml.MarkupBuilder

public class BuildWSATH {

	/**
	 * Genera la estructura xml para el pago de la Liquidacion 
	 * @param numeroLiquidacion 
	 * @param Valor del comparendo sin los centavos  
	 * @return String 
	 */
	@Keyword
	public static BuildXMLPagoLiquidacion(String numeroLiquidacion, String Valor) {
		def fecha = new Date().format('YYYY-MM-dd')
		def xmlWriter = new StringWriter()
		def builder = new MarkupBuilder(xmlWriter)
		builder.setDoubleQuotes(true)
		builder.'soapenv:Envelope'('xmlns:soapenv':"http://schemas.xmlsoap.org/soap/envelope/",'xmlns:web':"http://webservices.qxmultas.quipux.com.co",'xmlns:xsd':"http://onlinebilling.biller.webservices.qxmultas.quipux.com.co/xsd"){
			'soapenv:Header'()
			'soapenv:Body'{
				'web:sendPmtNotification'{
					'web:pmtNotificationRequest' {
						'xsd:currentDatetime'("${fecha}T00:00:00.445Z")
						'xsd:inqDate'("${fecha}T00:00:00.000Z")
						'xsd:paidInvoices'{
							'xsd:agreementId'("1")
							'xsd:bankAuthCode'("001")
							'xsd:bankSrc'("0314")
							'xsd:invoiceId'("${numeroLiquidacion}")
							'xsd:paidValue'("${Valor}00")
							'xsd:valuesDetail'{
								'xsd:description'("1")
								'xsd:value'('1')
							}
						}
						'xsd:requestId'("1")
					}
				}
			}
		}
		return  xmlWriter.toString()
	}


	/**
	 * Genera estructura XML para el consumo de GetBill de ATH 
	 * @param numeroLiquidacion
	 * @return
	 */
	@Keyword
	public static buildGetbill(String numeroLiquidacion) {
		def Fecha = new Date().format('YYYY-MM-DD')
		def builder = new MarkupBuilder()
		return builder."soapenv:Envelope"("xmlns:soapenv":"http://schemas.xmlsoap.org/soap/envelope/","xmlns:web":"http://webservices.qxmultas.quipux.com.co","xmlns:xsd":"http://onlinebilling.biller.webservices.qxmultas.quipux.com.co/xsd"){
			"soapenv:Header"()
			"soapenv:Body"{
				"web:getBill"{
					"web:billRequest"{
						"xsd:agreementId"(100)
						"xsd:currentDatetime"(Fecha+"T00:00:00.445Z")
						"xsd:inqDate"(Fecha+"T00:00:00.445Z")
						"xsd:invoiceId"(numeroLiquidacion)
						"xsd:reference"{
							"xsd:message"(1)
							"xsd:name"(1)
						}
						"xsd:requestId"(1)
						"xsd:searchType"(1)
					}
				}
			}
		}
	}
}
