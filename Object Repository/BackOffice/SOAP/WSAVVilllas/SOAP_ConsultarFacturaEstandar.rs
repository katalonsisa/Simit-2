<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_ConsultarFacturaEstandar</name>
   <tag></tag>
   <elementGuidId>d7aa1e43-62ab-4a1c-a6a5-3c2c91fa521f</elementGuidId>
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
      <value>http://organizacion.com/wsEstandar/consultarFacturaEstandar</value>
      <webElementGuid>ee3a25b7-ebe1-420e-9b77-77a8c98734e7</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>62fdfba4-a647-401c-a36d-d19237b48a6a</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:wses=&quot;http://organizacion.com/wsEstandar/&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;wses:oe_consultarFacturaEstandar>
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
      &lt;/wses:oe_consultarFacturaEstandar>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/wsRecaudoBancoAvVilla?wsdl</soapServiceEndpoint>
   <soapServiceFunction>consultarFacturaEstandar</soapServiceFunction>
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
