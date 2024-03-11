<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_ReversarFacturaEstandar</name>
   <tag></tag>
   <elementGuidId>a611ddea-04eb-416d-9ed2-94384963c492</elementGuidId>
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
      <value>http://organizacion.com/wsEstandar/reversarFacturaEstandar</value>
      <webElementGuid>9d7546a0-f0ed-48bd-b50a-5a81233cc943</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>4c96a333-02c7-4b90-b02b-78665102aa62</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:wses=&quot;http://organizacion.com/wsEstandar/&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;wses:oe_reversarFacturaEstandar>
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
      &lt;/wses:oe_reversarFacturaEstandar>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/wsRecaudoBancoAvVilla?wsdl</soapServiceEndpoint>
   <soapServiceFunction>reversarFacturaEstandar</soapServiceFunction>
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
