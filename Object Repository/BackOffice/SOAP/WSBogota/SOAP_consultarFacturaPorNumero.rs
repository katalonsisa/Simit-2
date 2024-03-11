<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>SOAP_consultarFacturaPorNumero</name>
   <tag></tag>
   <elementGuidId>2d04252c-bab8-4251-b5ad-571748af2940</elementGuidId>
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
      <value>urn:consultarFacturaPorNumero</value>
      <webElementGuid>d197575f-b0bf-4230-919e-bf14e78343a7</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>false</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>text/xml; charset=utf-8</value>
      <webElementGuid>e9eda404-affe-4b6f-884f-42074f1f6a98</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <restRequestMethod></restRequestMethod>
   <restUrl></restUrl>
   <serviceType>SOAP</serviceType>
   <soapBody>&lt;soapenv:Envelope xmlns:soapenv=&quot;http://schemas.xmlsoap.org/soap/envelope/&quot; xmlns:web=&quot;http://webservices.qxmultas.quipux.com.co&quot; xmlns:xsd=&quot;http://model.recaudos.qxmultas.quipux.com.co/xsd&quot;>
   &lt;soapenv:Header/>
   &lt;soapenv:Body>
      &lt;web:consultarFacturaPorNumero>
         &lt;web:consultarFacturaPorNumeroDocument>
            &lt;xsd:cadenaIFX>gero et&lt;/xsd:cadenaIFX>
         &lt;/web:consultarFacturaPorNumeroDocument>
      &lt;/web:consultarFacturaPorNumero>
   &lt;/soapenv:Body>
&lt;/soapenv:Envelope></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod>SOAP</soapRequestMethod>
   <soapServiceEndpoint>https://${GlobalVariable.dominio}/Simit/services/WsBancoBogota?wsdl</soapServiceEndpoint>
   <soapServiceFunction>consultarFacturaPorNumero</soapServiceFunction>
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
   <wsdlAddress>https://${GlobalVariable.dominio}/Simit/services/WsBancoBogota?wsdl</wsdlAddress>
</WebServiceRequestEntity>
