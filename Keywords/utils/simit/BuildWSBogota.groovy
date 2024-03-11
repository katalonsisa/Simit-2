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

public class BuildWSBogota {

	/**
	 * Genera el xml para el servicio soap de consultarFacturaPorNumero
	 * @param numeroLiquidacion
	 * @return <b>XML</b>
	 */
	@Keyword
	static def BuildXMLConsultarFacturaPorNumero(String numeroLiquidacion) {
		def fecha = new Date().format('YYYYMMdd')
		def xmlWriter = new StringWriter()
		def xmlWriter2 = new StringWriter()
		def builder = new MarkupBuilder(xmlWriter)
		def builder2 = new MarkupBuilder(xmlWriter2)
		builder.setDoubleQuotes(true)
		builder2.setDoubleQuotes(true)

		//------------------------CadenaIFX
		builder2.mkp.xmlDeclaration(version: "1.0", encoding: "utf-8") +
		builder2.'IFX'{
			'SignonRq'{
				'SignonPswd'{
					'CustId'{
						'SPName'('Empresa Pruebas')
						'CustPermId'('0212')
						'CustLoginId'('1234567890')
					}
				}
				'ClientDt'(11964253032)
			}
			'PresSvcRq'{
				'BillInqRq'{
					'MsgRqHdr'{
						'NetworkTrnInfo'{
							'NetworkOwner'('OFC')
							'OriginatorName'('1029')
							'BankId'('001')
						}
					}
					'BillerId'{
						'SPName'('Empresa Pruebas')
						'BillerNum'('1234')
						'BillInfo'{
							'BillRefInfo1'(numeroLiquidacion)
							'BillRefInfo2'(0)
							'BillRefInfo3'(0)
							'BillRefInfo4'(0)
							'BillRefInfo5'(0)
						}
					}
					'SelRangeDt'{
						'StartDt'(fecha)
					}
				}
			}
			'SignoffRq'{
				'CustId'{
					'SPName'('Empresa Pruebas')
					'CustPermId'('0212')
					'CustLoginId'('100023340100')
				}
			}
		}

		//----------xml principal

		builder.'soapenv:Envelope'('xmlns:soapenv':"http://schemas.xmlsoap.org/soap/envelope/",'xmlns:web':"http://webservices.qxmultas.quipux.com.co", 'xmlns:xsd':"http://model.recaudos.qxmultas.quipux.com.co/xsd"){
			'soapenv:Header'()
			'soapenv:Body'{
				'web:consultarFacturaPorNumero'{
					'web:consultarFacturaPorNumeroDocument'{
						'xsd:cadenaIFX'{
							builder.mkp.yieldUnescaped("<![CDATA[" +xmlWriter2.toString() + "]]>")
						}
					}
				}
			}
		}
		return xmlWriter.toString()
	}

	/**
	 * Genera el Xml para el servicio soap de RegistrarPagosIFX
	 * @param numeroLiquidacion
	 * @param Valor
	 * @return <b>XML</b> 
	 */
	@Keyword
	static def buildXmlRegistrarPagoIFX(String numeroLiquidacion, String Valor) {
		def fecha = new Date().format('YYYYMMdd')
		def fechacompleja = new Date().format('YYYYMMddHHmms')
		def xmlWriter = new StringWriter()
		def xmlWriter2 = new StringWriter()
		def builder = new MarkupBuilder(xmlWriter)
		def builder2 = new MarkupBuilder(xmlWriter2)
		builder.setDoubleQuotes(true)
		builder2.setDoubleQuotes(true)
		
		//-------------- Cadena pagoIFX
		
		builder2.mkp.xmlDeclaration(version: "1.0", encoding: "utf-8") +
		builder2.'IFX'{
			'SignonRq'{
				'SignonPswd'{
					'CustId'{
						'SPName'('Empresa Pruebas')
						'CustPermId'('0212')
						'CustLoginId'('1234567890')
					}
				}
				'ClientDt'(fechacompleja)
			}
			'PaySvcRq'{
				'PmtAddRq'{
					'RqUID'('0246')
					'MsgRqHdr'{
						'NetworkTrnInfo'{
							'NetworkOwner'('OFC')
							'OriginatorName'('0510')
							'BankId'('01')
						}
					}
					'PmtInfo'{
						'BillerId'{
							'BillerNum'('7709998003415')
						}
						'Category'('0646')
						'CurAmt'{
							'CurCode'('COP')
							'Amt'(Valor + '00')
							'Efc'('00')
							'Chq'('00')
						}
						'DepAcctIdTo'{
							'AcctId'(numeroLiquidacion)
							'AcctType'('1')
						}
						'ImmediatePmt'('0')
						'DepAcctIdFrom'{
							'BankInfo'{
								'BankId'('01')
							}
						}
						'PrcDt'(fecha)
						'RemitInfo'{
							'BillRefInfo'('1234')
							'RefInfo'{
								'RefId1'(numeroLiquidacion)
								'RefId2'('0')
								'RefId3'('0')
								'RefId4'('0')
								'RefId5'('0')
							}
						}
					}
				}
			}
			'SignoffRq'{
				'CustId'{
					'SPName'('Empresa')
					'CustPermId'('0212')
					'CustLoginId'(1036942095)
				}
			}
		}
		
		//--------------------------------- Estructura general 
	
		builder.'soapenv:Envelope'('xmlns:soapenv':"http://schemas.xmlsoap.org/soap/envelope/",'xmlns:web':"http://webservices.qxmultas.quipux.com.co", 'xmlns:xsd':"http://model.recaudos.qxmultas.quipux.com.co/xsd"){
			'soapenv:Header'()
			'soapenv:Body'{
				'web:registrarPagoIFX'{
					'web:registrarPagoIFXDocument'{
						'xsd:cadenaPagoIFX'{
							builder.mkp.yieldUnescaped("<![CDATA[" +xmlWriter2.toString() + "]]>")
						}
					}
				}
			}
		}
		
		return xmlWriter.toString()
	}
}
