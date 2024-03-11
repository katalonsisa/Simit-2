<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_PagarFacturaEstandar</name>
   <tag></tag>
   <elementGuidId>2d291f3a-5a47-4577-8f1d-192ef77048dd</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>SOAPAction</name>
      <type>Main</type>
      <value>http://organizacion.com/wsEstandar/pagarFacturaEstandar</value>
      <webElementGuid>e94d588f-bd70-4c38-97b8-70a3f69f5591</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>47d241ac-3dc0-4d14-80e9-9e29e07d6799</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:wses=&quot;http://organizacion.com/wsEstandar/&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;wses:oe_pagarFacturaEstandar>
         &lt;codBancoOrigen>3&lt;/codBancoOrigen>
         &lt;codCanal>gero et&lt;/codCanal>
         &lt;nroProducto>sonoras imperio&lt;/nroProducto>
         &lt;codOficinaOrigen>3&lt;/codOficinaOrigen>
         &lt;codCiudad>quae divum incedo&lt;/codCiudad>
         &lt;fechaTransaccion>verrantque per auras&lt;/fechaTransaccion>
         &lt;horaTransaccion>per auras&lt;/horaTransaccion>
         &lt;fechaCompensacion>circum claustra&lt;/fechaCompensacion>
         &lt;referencia1>nimborum in&lt;/referencia1>
         &lt;referencia2>foedere certo&lt;/referencia2>
         &lt;referencia3>profundum quippe ferant&lt;/referencia3>
         &lt;referencia4>et carcere&lt;/referencia4>
         &lt;valorEfectivo>iovis rapidum iaculata&lt;/valorEfectivo>
         &lt;valorCheque>speluncis abdidit&lt;/valorCheque>
         &lt;valorTotal>bella gero et&lt;/valorTotal>
         &lt;nroAutorizacion>3&lt;/nroAutorizacion>
      &lt;/wses:oe_pagarFacturaEstandar>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/wsRecaudoBancoAvVilla?wsdl</soapServiceEndpoint>
   <soapServiceFunction>pagarFacturaEstandar</soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>false</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress>https://${GlobalVariable.dominio}/Simit/services/wsRecaudoBancoAvVilla?wsdl</wsdlAddress>
</WebServiceRequestEntity>
